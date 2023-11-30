package com.projetos.CestAlunos.domain.service;

import com.projetos.CestAlunos.domain.entity.Curso;
import com.projetos.CestAlunos.domain.entity.Professor;
import com.projetos.CestAlunos.infrastructure.exceptions.EntityNotFoundException;
import com.projetos.CestAlunos.infrastructure.repository.CursoRepository;
import com.projetos.CestAlunos.infrastructure.repository.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final CursoRepository cursoRepository;

    public ProfessorService(ProfessorRepository professorRepository, CursoRepository cursoRepository) {
        this.professorRepository = professorRepository;
        this.cursoRepository = cursoRepository;
    }

    @Transactional
    public Professor salvar(Professor professor, Set<Long> cursoIds) {
        // Associar cursos ao professor antes de salvar
        Set<Curso> cursos = new HashSet<>();
        for (Long cursoId : cursoIds) {
            Curso curso = cursoRepository.findById(cursoId)
                    .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com ID: " + cursoId));
            cursos.add(curso);
        }
        professor.setCursos(cursos);
        return professorRepository.save(professor);
    }

    @Transactional
    public Professor atualizar(Professor professor, Set<Long> cursoIds) {
        Professor professorExistente = professorRepository.findById(professor.getId())
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado com ID: " + professor.getId()));

        // Atualizar propriedades do professor
        professorExistente.setNome(professor.getNome());
        professorExistente.setMatricula(professor.getMatricula());
        professorExistente.setDepartamento(professor.getDepartamento());
        professorExistente.setEmail(professor.getEmail());
        professorExistente.setTelefone(professor.getTelefone());
        professorExistente.setAvaliacoes(professor.getAvaliacoes());


        // Atualizar lista de cursos associados
        if (cursoIds != null && !cursoIds.isEmpty()) {
            Set<Curso> cursos = new HashSet<>();
            for (Long cursoId : cursoIds) {
                Curso curso = cursoRepository.findById(cursoId)
                        .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com ID: " + cursoId));
                cursos.add(curso);
            }
            professorExistente.setCursos(cursos);
        }

        return professorRepository.save(professorExistente);
    }

    @Transactional
    public void excluir(Long id) {
        professorRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Professor> buscarPorId(Long id) {
        return professorRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Professor> buscarTodos() {
        return professorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Professor> buscarProfessoresPorNome(String nome) {
        return professorRepository.buscarPorNome(nome);
    }
    // Aqui, você pode adicionar mais métodos conforme necessário, como busca por nome, departamento, etc.
}
