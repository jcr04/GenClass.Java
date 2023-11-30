package com.projetos.CestAlunos.infrastructure.repository;

import com.projetos.CestAlunos.domain.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    // Consulta SELECT personalizada
    @Query("SELECT a FROM Avaliacao a WHERE a.aluno.id = :alunoId")
    List<Avaliacao> buscarAvaliacoesPorAlunoId(Long alunoId);

    // Consulta INSERT personalizada
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO avaliacao (nota, aluno_id, professor_id) VALUES (:nota, :alunoId, :professorId)", nativeQuery = true)
    void inserirAvaliacao(double nota, Long alunoId, Long professorId);

    // UPDATE personalizada
    @Modifying
    @Transactional
    @Query("UPDATE Avaliacao a SET a.nota = :nota WHERE a.id = :avaliacaoId")
    void atualizarNotaAvaliacao(Long avaliacaoId, double nota);

    // Consulta DELETE personalizada
    @Modifying
    @Transactional
    @Query("DELETE FROM Avaliacao a WHERE a.id = :avaliacaoId")
    void excluirAvaliacao(Long avaliacaoId);


    // Adicionado: busca avaliações por ID do professor
    @Query("SELECT a FROM Avaliacao a WHERE a.professor.id = :professorId")
    List<Avaliacao> buscarAvaliacoesPorProfessorId(Long professorId);


}
