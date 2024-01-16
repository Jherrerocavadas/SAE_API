package br.com.jherrerocavadas.saeapi.repository;

import br.com.jherrerocavadas.saeapi.dto.Faculdade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaculdadeRepository  extends JpaRepository<Faculdade, Long> {

    Faculdade getFaculdadeByNomeFaculdade(String nomeFaculdade);
}
