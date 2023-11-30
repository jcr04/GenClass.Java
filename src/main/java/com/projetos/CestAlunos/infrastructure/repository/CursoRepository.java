package com.projetos.CestAlunos.infrastructure.repository;

import com.projetos.CestAlunos.domain.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Buscar cursos por nome
    List<Curso> findByNomeContaining(String nome);

    // Buscar cursos por código
    Curso findByCodigo(String codigo);

    // Buscar todos os cursos com a quantidade de alunos
    @Query("SELECT c, count(a.id) FROM Curso c LEFT JOIN c.alunos a GROUP BY c")
    List<Object[]> findCursoWithCountOfAlunos();

    // Buscar cursos de um professor específico
    @Query("SELECT c FROM Curso c JOIN c.professores p WHERE p.id = :professorId")
    List<Curso> findCursosByProfessorId(@Param("professorId") Long professorId);

    // Você pode adicionar outros métodos conforme necessário
}
