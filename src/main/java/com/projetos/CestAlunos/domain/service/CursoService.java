package com.projetos.CestAlunos.domain.service;

import com.projetos.CestAlunos.domain.entity.Curso;
import com.projetos.CestAlunos.infrastructure.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    public Curso salvar(Curso curso) {
        cursoRepository.save(curso);
        return curso;
    }

    public Curso atualizar(Curso curso) {
        cursoRepository.save(curso);
        return curso;
    }

    public void excluir(Long id) {
        cursoRepository.deleteById(id);
    }

    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    public List<Curso> buscarTodos() {
        return cursoRepository.findAll();
    }

}
