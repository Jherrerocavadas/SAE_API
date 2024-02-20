package br.com.jherrerocavadas.saeapi.entity;

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
public class Disciplina {
    //TODO: Criar um DTO para manipulação das requests sem expor a entidade do banco de dados

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codDisciplina;
    private String nomeDisciplina;
    private String siglaDisciplina;
    private Integer quantidadeAulas;
    private Boolean isDisciplinaEspecial;
    private String corDisciplina; //Cor Hexadecimal (no padrão #xxxxxx) personalizada para a disciplina, para utilização no front


    public Disciplina(Long id) {
        this.id = id;
    }
}
