package com.projetos.CestAlunos.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String nome;

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String matricula;

    private String departamento;

    @Email
    private String email;

    private String telefone;

    @ManyToMany(mappedBy = "professores", fetch = FetchType.LAZY)
    private Set<Curso> cursos = new HashSet<>();

    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
    private Set<Avaliacao> avaliacoes = new HashSet<>();

    public void adicionarCurso(Curso curso) {
        cursos.add(curso);
        curso.getProfessores().add(this);
    }

    public void removerCurso(Curso curso) {
        cursos.remove(curso);
        curso.getProfessores().remove(this);
    }


    // Outros métodos conforme necessário
}
