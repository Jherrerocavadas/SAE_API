package br.com.jherrerocavadas.saeapi.entity;

import br.com.jherrerocavadas.saeapi.enums.Periodo;
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
public class HorarioAula {
//TODO: Criar um DTO para manipulação das requests sem expor a entidade do banco de dados

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Periodo periodo;
    private Integer numeroAula;
    private String inicioAula;
    private String fimAula;
    private Boolean isIntervalo;

    public HorarioAula(Long id) {
        this.id = id;
    }
}
