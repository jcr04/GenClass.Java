package com.projetos.CestAlunos.domain.service;

import com.projetos.CestAlunos.domain.entity.Avaliacao;
import com.projetos.CestAlunos.infrastructure.exceptions.EntityNotFoundException;
import com.projetos.CestAlunos.infrastructure.repository.AlunoRepository;
import com.projetos.CestAlunos.infrastructure.repository.AvaliacaoRepository;
import com.projetos.CestAlunos.infrastructure.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final AlunoRepository alunoRepository; // Adicionado
    private final ProfessorRepository professorRepository; // Adicionado

    @Autowired // Este construtor injeta as dependências necessárias
    public AvaliacaoService(
            AvaliacaoRepository avaliacaoRepository,
            AlunoRepository alunoRepository, // Adicionado
            ProfessorRepository professorRepository // Adicionado
    ) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.alunoRepository = alunoRepository; // Adicionado
        this.professorRepository = professorRepository; // Adicionado
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

    @Transactional
    public Avaliacao registrarAvaliacao(Avaliacao novaAvaliacao) {
        // Regra de negócio: A nota deve estar entre 0 e 10.
        if (novaAvaliacao.getNota() < 0 || novaAvaliacao.getNota() > 10) {
            throw new IllegalArgumentException("A nota deve estar entre 0 e 10.");
        }

        // Regra de negócio: A data da avaliação não pode ser futura.
        if (novaAvaliacao.getDataAvaliacao().after(new Date())) {
            throw new IllegalArgumentException("A data da avaliação não pode ser futura.");
        }

        // Regra de negócio: Aluno e professor devem existir.
        if (!alunoRepository.existsById(novaAvaliacao.getAluno().getId())) {
            throw new EntityNotFoundException("Aluno não encontrado com ID: " + novaAvaliacao.getAluno().getId());
        }
        if (!professorRepository.existsById(novaAvaliacao.getProfessor().getId())) {
            throw new EntityNotFoundException("Professor não encontrado com ID: " + novaAvaliacao.getProfessor().getId());
        }

        // Se todas as regras forem cumpridas, salve a avaliação.
        return avaliacaoRepository.save(novaAvaliacao);
    }


    @Transactional
    public Avaliacao atualizarFrequencia(Long avaliacaoId, boolean presenca) {
        return avaliacaoRepository.findById(avaliacaoId)
                .map(avaliacaoExistente -> {
                    avaliacaoExistente.setPresenca(presenca);
                    return avaliacaoRepository.save(avaliacaoExistente);
                })
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada com ID: " + avaliacaoId));
    }

    @Transactional(readOnly = true)
    public List<Avaliacao> buscarAvaliacoesPorProfessorId(Long professorId) {
        // Implementar método no repositório para buscar por professor
        return avaliacaoRepository.buscarAvaliacoesPorProfessorId(professorId);
    }

    // Outros métodos conforme necessário
}
