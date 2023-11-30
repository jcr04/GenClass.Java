package com.projetos.CestAlunos.infrastructure.repository;

import com.projetos.CestAlunos.domain.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    @Query("SELECT p FROM Professor p WHERE p.nome = :nome")
    List<Professor> buscarPorNome(@Param("nome") String nome);

    @Modifying
    @Query(value = "INSERT INTO professor (nome, matricula, departamento) VALUES (:nome, :matricula, :departamento)", nativeQuery = true)
    void inserirProfessor(@Param("nome") String nome, @Param("matricula") String matricula, @Param("departamento") String departamento);


}
