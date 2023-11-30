package com.projetos.CestAlunos.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String codigo;

    // Relacionamento ManyToMany com a entidade Professor
    @ManyToMany(mappedBy = "cursos")
    private Set<Professor> professores = new HashSet<>();

    // Relacionamento com a entidade Aluno
    @ManyToMany
    @JoinTable(
            name = "curso_aluno",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private Set<Aluno> alunos = new HashSet<>();

    public void adicionarProfessor(Professor professor) {
        professores.add(professor);
        professor.getCursos().add(this);
    }

    public void removerProfessor(Professor professor) {
        professores.remove(professor);
        professor.getCursos().remove(this);
    }

    // Você pode adicionar outros campos e métodos conforme necessário
}
