package com.projetos.CestAlunos.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String matricula;

    @Column(nullable = false)
    private String cursoNome; // Changed from 'curso' to 'cursoNome'

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    private String email;

    private String telefone;

    private String endereco;

    private String sexo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataMatricula;

    @ManyToMany(mappedBy = "alunos")
    private Set<Curso> cursos = new HashSet<>(); // Initialized the Set

    // Constructors, getters, and setters are handled by Lombok annotations
}
