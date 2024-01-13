package br.com.jherrerocavadas.saeapi.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Curso curso;
    private Disciplina disciplina;
    private HorarioAula horaAula1;
    private HorarioAula horaAula2;
    private HorarioAula horaAula3;
    private HorarioAula horaAula4;


}
