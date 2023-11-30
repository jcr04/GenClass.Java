package com.projetos.CestAlunos.domain.service;

import com.projetos.CestAlunos.domain.entity.Curso;
import com.projetos.CestAlunos.infrastructure.exceptions.EntityNotFoundException;
import com.projetos.CestAlunos.infrastructure.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    @Transactional
    public Curso salvar(Curso curso) {
        // Validações e lógicas antes de salvar, se necessário
        return cursoRepository.save(curso);
    }

    @Transactional
    public Curso atualizar(Curso curso) {
        return cursoRepository.findById(curso.getId())
                .map(cursoExistente -> {
                    cursoExistente.setNome(curso.getNome());
                    cursoExistente.setCodigo(curso.getCodigo());
                    // Atualizar outras propriedades conforme necessário
                    return cursoRepository.save(cursoExistente);
                })
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com ID: " + curso.getId()));
    }

    @Transactional
    public void excluir(Long id) {
        cursoRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Curso não encontrado com ID: " + id));
        cursoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<Curso> buscarTodos() {
        return cursoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Curso> buscarPorNome(String nome) {
        return cursoRepository.findByNomeContaining(nome);
    }

    @Transactional(readOnly = true)
    public List<Curso> buscarCursosPorProfessorId(Long professorId) {
        return cursoRepository.findCursosByProfessorId(professorId);
    }

    @Transactional(readOnly = true)
    public List<Object[]> buscarCursoComQuantidadeDeAlunos() {
        return cursoRepository.findCursoWithCountOfAlunos();
    }

    // Outros métodos conforme necessário
}
