package com.projetos.CestAlunos.infrastructure.repository;

import com.projetos.CestAlunos.domain.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
