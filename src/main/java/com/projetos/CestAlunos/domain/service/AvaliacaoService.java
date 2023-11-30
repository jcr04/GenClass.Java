package com.projetos.CestAlunos.domain.service;

import com.projetos.CestAlunos.domain.entity.Avaliacao;
import com.projetos.CestAlunos.infrastructure.exceptions.EntityNotFoundException;
import com.projetos.CestAlunos.infrastructure.repository.AvaliacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @Transactional
    public Avaliacao salvar(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    @Transactional
    public Avaliacao atualizar(Avaliacao avaliacao) {
        // Verifica se a avaliação existe antes de atualizar
        return avaliacaoRepository.findById(avaliacao.getId())
                .map(avaliacaoExistente -> {
                    // Atualiza a avaliação existente
                    avaliacaoExistente.setNota(avaliacao.getNota());
                    avaliacaoExistente.setAluno(avaliacao.getAluno());
                    avaliacaoExistente.setProfessor(avaliacao.getProfessor());
                    // Adicione outras propriedades conforme necessário
                    return avaliacaoRepository.save(avaliacaoExistente);
                })
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada com ID: " + avaliacao.getId()));
    }


    @Transactional
    public void excluir(Long id) {
        avaliacaoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Avaliacao> buscarPorId(Long id) {
        return avaliacaoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Avaliacao> buscarAvaliacoesPorAlunoId(Long alunoId) {
        return avaliacaoRepository.buscarAvaliacoesPorAlunoId(alunoId);
    }

    // Outros métodos conforme necessário
}
