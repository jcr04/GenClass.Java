package com.projetos.CestAlunos.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String matricula;

    @Column(nullable = false)
    private String curso;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    private String email;

    private String telefone;

    private String endereco;

    private String sexo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataMatricula;

    @ManyToMany(mappedBy = "alunos")
    private Set<Curso> cursos;
    // Outros campos e métodos conforme necessário
}
