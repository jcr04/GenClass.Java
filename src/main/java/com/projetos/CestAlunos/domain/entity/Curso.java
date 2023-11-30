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

    // Você pode adicionar outros campos e métodos conforme necessário
}
