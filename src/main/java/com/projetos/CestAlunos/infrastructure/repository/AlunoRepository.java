package com.projetos.CestAlunos.infrastructure.repository;

import com.projetos.CestAlunos.domain.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
