package br.com.jherrerocavadas.saeapi.api.dto;


import br.com.jherrerocavadas.saeapi.entity.Curso;
import br.com.jherrerocavadas.saeapi.entity.Disciplina;
import br.com.jherrerocavadas.saeapi.entity.Faculdade;
import br.com.jherrerocavadas.saeapi.entity.HorarioAula;
import br.com.jherrerocavadas.saeapi.enums.DiaSemana;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaCursoDTO {
//DONE: Criar um DTO para manipulação das requests sem expor a entidade do banco de dados
//NOTE: DTO Funcionando para as operações de GET.


    private Long id;

    //Dados da faculdade
    private Faculdade faculdade;

//    private Long faculdadeId;

    //Dados do curso
    private Curso curso;

//    private Long cursoId;

    //Dados da disciplina
    private Disciplina disciplina;

    /*--------------------< Dados dos horários de aula da disciplina >--------------------*/


    private List<HorarioAula> horasAula;
    private Integer semestre;
    private List<DiaSemana> diasDeAula;


}

