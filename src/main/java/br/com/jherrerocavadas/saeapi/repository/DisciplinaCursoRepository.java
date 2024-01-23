package br.com.jherrerocavadas.saeapi.repository;

import br.com.jherrerocavadas.saeapi.dto.Curso;
import br.com.jherrerocavadas.saeapi.dto.DisciplinaCurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplinaCursoRepository  extends JpaRepository<DisciplinaCurso, Long> {

    List<DisciplinaCurso> getDisciplinaCursoByCurso(Curso curso);

    List<DisciplinaCurso> getDisciplinaCursoByCursoNomeCurso(String nomeCurso);

    List<DisciplinaCurso> getDisciplinaCursoByCursoSiglaCurso(String siglaCurso);

    List<DisciplinaCurso> getDisciplinaCursoByCursoSiglaCursoAndFaculdadeCodFaculdade(String siglaCurso, String codFaculdade);
}
