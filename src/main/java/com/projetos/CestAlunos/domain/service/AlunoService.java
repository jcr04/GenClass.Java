package com.projetos.CestAlunos.domain.service;

import com.projetos.CestAlunos.domain.entity.Aluno;
import com.projetos.CestAlunos.infrastructure.exceptions.EntityNotFoundException;
import com.projetos.CestAlunos.infrastructure.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    @Transactional
    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @Transactional
    public Aluno atualizar(Aluno aluno) {
        return alunoRepository.findById(aluno.getId())
                .map(alunoExistente -> {
                    alunoExistente.setNome(aluno.getNome());
                    alunoExistente.setMatricula(aluno.getMatricula());
                    alunoExistente.setCurso(aluno.getCurso());
                    // Adicione outras propriedades conforme necessário
                    return alunoRepository.save(alunoExistente);
                })
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + aluno.getId()));
    }

    @Transactional
    public void excluir(Long id) {
        alunoRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Aluno não encontrado com ID: " + id));
        alunoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Aluno> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Aluno> buscarTodos() {
        return alunoRepository.findAll();
    }

}
