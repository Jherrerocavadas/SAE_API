package br.com.jherrerocavadas.saeapi.dto;

import br.com.jherrerocavadas.saeapi.enums.DiaSemana;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaCurso {
//TODO: Criar um DTO para manipulação das requests sem expor a entidade do banco de dados

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



//    @Column(insertable=true, updatable=true)
//    private Long cursoId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "cursoId", referencedColumnName = "id",
//            insertable=false, updatable=false,
            foreignKey = @ForeignKey(name = "UK_DISCIPLINACURSO_CURSO", value = ConstraintMode.CONSTRAINT))
    private Curso curso;

//    @Column(insertable=true, updatable=true)
//    private Long disciplinaId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "disciplinaId", referencedColumnName = "id",
//            insertable=false, updatable=false,
            foreignKey = @ForeignKey(name = "UK_DISCIPLINACURSO_DISCIPLINA", value = ConstraintMode.CONSTRAINT))

    private Disciplina disciplina;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "horarioAula1_id", referencedColumnName = "id",
//            insertable=false, updatable=false,
            foreignKey = @ForeignKey(name = "UK_DISCIPLINACURSO_HORARIOAULA1", value = ConstraintMode.CONSTRAINT))
    private HorarioAula horaAula1;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "horarioAula2_id", referencedColumnName = "id",
//            insertable=false, updatable=false,
            foreignKey = @ForeignKey(name = "UK_DISCIPLINACURSO_HORARIOAULA2", value = ConstraintMode.CONSTRAINT))
    private HorarioAula horaAula2;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "horarioAula3_id", referencedColumnName = "id",
//            insertable=false, updatable=false,
            foreignKey = @ForeignKey(name = "UK_DISCIPLINACURSO_HORARIOAULA3", value = ConstraintMode.CONSTRAINT))
    private HorarioAula horaAula3;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "horarioAula4_id", referencedColumnName = "id",
//            insertable=false, updatable=false,
            foreignKey = @ForeignKey(name = "UK_DISCIPLINACURSO_HORARIOAULA4", value = ConstraintMode.CONSTRAINT))
    private HorarioAula horaAula4;

    private Integer semestre;
    private DiaSemana diaDaSemana;


}
