package com.projetos.CestAlunos.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    private double nota;

    @Temporal(TemporalType.DATE)
    private Date dataAvaliacao;

    private boolean presenca;

    @Column(length = 500) // Comentários podem ser longos, então definimos um limite adequado
    private String comentarios;

    // Outros campos e métodos conforme necessário
}
