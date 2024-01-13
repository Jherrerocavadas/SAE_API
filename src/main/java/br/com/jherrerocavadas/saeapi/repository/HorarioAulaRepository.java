package br.com.jherrerocavadas.saeapi.repository;

import br.com.jherrerocavadas.saeapi.dto.HorarioAula;
import br.com.jherrerocavadas.saeapi.enums.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioAulaRepository extends JpaRepository<HorarioAula, Long> {

    List<HorarioAula> getHorarioAulaByPeriodo(Periodo periodo);
    List<HorarioAula> getHorarioAulaByNumeroAula(Integer numeroAula);

    List<HorarioAula> getHorarioAulaByIsIntervalo(Boolean isIntervalo);


}
