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
public class Faculdade {

    //TODO: Criar um DTO para manipulação das requests sem expor a entidade do banco de dados


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codFaculdade;
    private String nomeFaculdade;
    private String siglaFaculdade;
    private String cidade;
    private String endereco;

    public Faculdade(Long id) {
        this.id = id;
        }
    }
