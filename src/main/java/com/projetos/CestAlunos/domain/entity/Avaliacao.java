package com.projetos.CestAlunos.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @Min(0)
    @Max(10)
    private double nota;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dataAvaliacao;

    private boolean presenca;

    @Column(length = 500) // Comentários podem ser longos
    private String comentarios;

    // Outros campos e métodos conforme necessário
}
