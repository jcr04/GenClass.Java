package com.projetos.CestAlunos.infrastructure.repository;

import com.projetos.CestAlunos.domain.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    @Query("SELECT p FROM Professor p WHERE p.nome = :nome")
    List<Professor> buscarPorNome(@Param("nome") String nome);

    // Método para buscar professores por curso
    @Query("SELECT p FROM Professor p JOIN p.cursos c WHERE c.nome = :nomeCurso")
    List<Professor> buscarPorNomeCurso(@Param("nomeCurso") String nomeCurso);

    // Método para buscar um professor por matrícula
    Optional<Professor> findByMatricula(String matricula);

}
