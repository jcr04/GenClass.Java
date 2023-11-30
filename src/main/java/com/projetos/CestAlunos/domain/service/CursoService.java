package com.projetos.CestAlunos.domain.service;

import com.projetos.CestAlunos.domain.entity.Curso;
import com.projetos.CestAlunos.infrastructure.exceptions.EntityNotFoundException;
import com.projetos.CestAlunos.infrastructure.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;

    @Transactional
    public Curso salvar(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Transactional
    public Curso atualizar(Curso curso) {
        return cursoRepository.findById(curso.getId())
                .map(cursoExistente -> {
                    cursoExistente.setNome(curso.getNome());
                    cursoExistente.setCodigo(curso.getCodigo());
                    // Adicione outras propriedades conforme necessário
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
    public Optional<Curso> buscarPorId(Long id) {
        return cursoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Curso> buscarTodos() {
        return cursoRepository.findAll();
    }

    // Aqui, você pode adicionar mais métodos conforme necessário, por exemplo, para buscar cursos por professor
}
