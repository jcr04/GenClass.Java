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
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String matricula;

    private String departamento;

    // Novos campos
    private String email;
    private String telefone;

    // Relacionamento ManyToMany com a entidade Curso
    @ManyToMany(mappedBy = "professores")
    private Set<Curso> cursos = new HashSet<>();

    @OneToMany(mappedBy = "professor")
    private Set<Avaliacao> avaliacoes;

    // Métodos para adicionar ou remover cursos
    public void adicionarCurso(Curso curso) {
        cursos.add(curso);
        curso.getProfessores().add(this);
    }

    public void removerCurso(Curso curso) {
        cursos.remove(curso);
        curso.getProfessores().remove(this);
    }

    // Outros métodos conforme necessário, por exemplo, para adicionar ou remover avaliações
}
