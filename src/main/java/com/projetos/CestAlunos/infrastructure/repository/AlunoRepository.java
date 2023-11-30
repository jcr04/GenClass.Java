package com.projetos.CestAlunos.infrastructure.repository;

import com.projetos.CestAlunos.domain.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    // Encontrar alunos por nome
    List<Aluno> findByNomeContaining(String nome);

    // Encontrar alunos por curso
    List<Aluno> findByCurso(String curso);

    // Busca personalizada com @Query para encontrar alunos por um trecho do nome
    @Query("SELECT a FROM Aluno a WHERE lower(a.nome) LIKE lower(concat('%', :nome, '%'))")
    List<Aluno> buscarPorNome(@Param("nome") String nome);

    // Busca personalizada com @Query para encontrar alunos por curso
    @Query("SELECT a FROM Aluno a WHERE a.curso = :curso")
    List<Aluno> buscarPorCurso(@Param("curso") String curso);

    // Você pode adicionar outros métodos de busca conforme necessário
}
