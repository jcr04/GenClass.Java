package com.projetos.CestAlunos.domain.service;

import com.projetos.CestAlunos.domain.entity.Aluno;
import com.projetos.CestAlunos.infrastructure.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public Aluno salvar(Aluno aluno) {
        alunoRepository.save(aluno);
        return aluno;
    }

    public Aluno atualizar(Aluno aluno) {
        alunoRepository.save(aluno);
        return aluno;
    }

    public void excluir(Long id) {
        alunoRepository.deleteById(id);
    }

    public Aluno buscarPorId(Long id) {
        return alunoRepository.findById(id).orElse(null);
    }

    public List<Aluno> buscarTodos() {
        return alunoRepository.findAll();
    }

}
