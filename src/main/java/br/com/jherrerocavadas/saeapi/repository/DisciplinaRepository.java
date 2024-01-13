package br.com.jherrerocavadas.saeapi.repository;

import br.com.jherrerocavadas.saeapi.dto.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    Disciplina getDisciplinaByCodDisciplina(String codDisciplina);
    Disciplina getDisciplinaByNomeDisciplina(String nomeDisciplina);
    Disciplina getDisciplinaBySiglaDisciplina(String siglaDisciplina);

    List<Disciplina> getDisciplinaByQuantidadeAulas(Integer quantidadeAulas);
    List<Disciplina> getDisciplinaByIsDisciplinaEspecial(Boolean isDisciplinaEspecial);
}
