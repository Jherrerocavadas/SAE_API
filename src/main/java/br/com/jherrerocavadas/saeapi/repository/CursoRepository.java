package br.com.jherrerocavadas.saeapi.repository;

import br.com.jherrerocavadas.saeapi.dto.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {


    Curso getCursoByNomeCurso(String nomeCurso);

    Curso getCursoBySiglaCurso(String siglaCurso);

    List<Curso> getCursoByQtdSemestres(Integer qtdSemestres);
}
