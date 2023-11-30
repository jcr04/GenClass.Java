package com.projetos.CestAlunos.domain.service;

import com.projetos.CestAlunos.domain.entity.Professor;
import com.projetos.CestAlunos.infrastructure.exceptions.EntityNotFoundException;
import com.projetos.CestAlunos.infrastructure.repository.ProfessorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Transactional
    public Professor salvar(Professor professor) {
        return professorRepository.save(professor);
    }

    @Transactional
    public Professor atualizar(Professor professor) {
        return professorRepository.findById(professor.getId())
                .map(professorExistente -> {
                    // Aqui você atualiza as propriedades necessárias do professor
                    professorExistente.setNome(professor.getNome());
                    professorExistente.setMatricula(professor.getMatricula());
                    professorExistente.setDepartamento(professor.getDepartamento());
                    // Adicione outras propriedades conforme necessário
                    return professorRepository.save(professorExistente);
                })
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado com ID: " + professor.getId()));
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

    // Aqui, você pode adicionar mais métodos conforme necessário, como busca por nome, departamento, etc.
}
