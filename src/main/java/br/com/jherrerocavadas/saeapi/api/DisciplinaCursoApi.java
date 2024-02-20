package br.com.jherrerocavadas.saeapi.api;

import br.com.jherrerocavadas.saeapi.api.dto.DisciplinaCursoDTO;
import br.com.jherrerocavadas.saeapi.entity.*;
import br.com.jherrerocavadas.saeapi.enums.DiaSemana;
import br.com.jherrerocavadas.saeapi.repository.DisciplinaCursoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
public class DisciplinaCursoApi {



    private final DisciplinaCursoRepository disciplinaCursoRepository;

    @Autowired
    private DisciplinaCursoApi(DisciplinaCursoRepository disciplinaCursoRepository){
        this.disciplinaCursoRepository = disciplinaCursoRepository;
    }

    @Operation(summary =  "Verificar o serviço de disciplinas de um curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Serviço OK"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/check-disciplinas-curso-service")
    public ResponseEntity<String> verificaServico(){
        return ResponseEntity.ok().body("Serviço operacional");
    }

    @Operation(summary =  "Retornar todos as disciplinasCursos cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "DisciplinasCursos retornadas"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/disciplinasCursos")
    public List<DisciplinaCursoDTO> retornarDisciplinaCursos(){
        List<DisciplinaCurso> disciplinasPorCurso = disciplinaCursoRepository.findAll();
        List<DisciplinaCursoDTO> disciplinasCursoDTO = new ArrayList<>();
            for (DisciplinaCurso disciplinaCurso: disciplinasPorCurso) {
                DisciplinaCursoDTO disciplinaCursoDTO = new DisciplinaCursoDTO();
                List<HorarioAula> horasAula = new ArrayList<>();
                List<DiaSemana> diasAula = new ArrayList<>();

                horasAula.add(disciplinaCurso.getHoraAula1());
                horasAula.add(disciplinaCurso.getHoraAula2());
                horasAula.add(disciplinaCurso.getHoraAula3());
                horasAula.add(disciplinaCurso.getHoraAula4());

                diasAula.add(disciplinaCurso.getDiaDeAula1());
                diasAula.add(disciplinaCurso.getDiaDeAula2());

                disciplinaCursoDTO.setId(disciplinaCurso.getId());
                disciplinaCursoDTO.setDisciplina(disciplinaCurso.getDisciplina());
                disciplinaCursoDTO.setFaculdade(disciplinaCurso.getFaculdade());
                disciplinaCursoDTO.setSemestre(disciplinaCurso.getSemestre());
                disciplinaCursoDTO.setCurso(disciplinaCurso.getCurso());
                disciplinaCursoDTO.setHorasAula(horasAula);
                disciplinaCursoDTO.setDiasDeAula(diasAula);

                disciplinasCursoDTO.add(disciplinaCursoDTO);
            }
        return disciplinasCursoDTO;
    }



    @Operation(summary =  "Retornar todas as disciplinas de registradas para determinado nome de curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Disciplinas do curso retornadas"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/disciplinasCursos/cursos/{nomeCurso}")
    public ResponseEntity<List<DisciplinaCursoDTO>> retornarDisciplinaCursoPorNomeCurso(@PathVariable String nomeCurso){
        List<DisciplinaCurso> disciplinasPorCurso = disciplinaCursoRepository.getDisciplinaCursoByCursoNomeCurso(nomeCurso);
        List<DisciplinaCursoDTO> disciplinasCursoDTO = new ArrayList<>();
        if(Objects.nonNull(disciplinasPorCurso)){

            for (DisciplinaCurso disciplinaCurso: disciplinasPorCurso) {
                DisciplinaCursoDTO disciplinaCursoDTO = new DisciplinaCursoDTO();
                List<HorarioAula> horasAula = new ArrayList<>();
                List<DiaSemana> diasAula = new ArrayList<>();

                horasAula.add(disciplinaCurso.getHoraAula1());
                horasAula.add(disciplinaCurso.getHoraAula2());

                if(Objects.nonNull(disciplinaCurso.getHoraAula3()) && Objects.nonNull(disciplinaCurso.getHoraAula4())){
                    horasAula.add(disciplinaCurso.getHoraAula3());
                    horasAula.add(disciplinaCurso.getHoraAula4());
                }

                diasAula.add(disciplinaCurso.getDiaDeAula1());
                if(Objects.nonNull(disciplinaCurso.getDiaDeAula2())){
                    diasAula.add(disciplinaCurso.getDiaDeAula2());
                }


                disciplinaCursoDTO.setId(disciplinaCurso.getId());
                disciplinaCursoDTO.setDisciplina(disciplinaCurso.getDisciplina());
                disciplinaCursoDTO.setFaculdade(disciplinaCurso.getFaculdade());
                disciplinaCursoDTO.setSemestre(disciplinaCurso.getSemestre());
                disciplinaCursoDTO.setCurso(disciplinaCurso.getCurso());
                disciplinaCursoDTO.setHorasAula(horasAula);
                disciplinaCursoDTO.setDiasDeAula(diasAula);

                disciplinasCursoDTO.add(disciplinaCursoDTO);
            }



            return ResponseEntity.ok(disciplinasCursoDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary =  "Retornar todas as disciplinas de registradas para determinado curso (pesquisa por sigla do curso)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Disciplinas do curso retornadas"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/disciplinasCursos/cursos")
    public ResponseEntity<List<DisciplinaCursoDTO>> retornarDisciplinaCursoPorSiglaCurso(@Param("siglaCurso") String siglaCurso){
        List<DisciplinaCurso> disciplinasPorCurso = disciplinaCursoRepository.getDisciplinaCursoByCursoSiglaCurso(siglaCurso);
        List<DisciplinaCursoDTO> disciplinasCursoDTO = new ArrayList<>();
        if(Objects.nonNull(disciplinasPorCurso)){

            for (DisciplinaCurso disciplinaCurso: disciplinasPorCurso) {
                DisciplinaCursoDTO disciplinaCursoDTO = new DisciplinaCursoDTO();
                List<HorarioAula> horasAula = new ArrayList<>();
                List<DiaSemana> diasAula = new ArrayList<>();

                horasAula.add(disciplinaCurso.getHoraAula1());
                horasAula.add(disciplinaCurso.getHoraAula2());

                if(Objects.nonNull(disciplinaCurso.getHoraAula3()) && Objects.nonNull(disciplinaCurso.getHoraAula4())){
                    horasAula.add(disciplinaCurso.getHoraAula3());
                    horasAula.add(disciplinaCurso.getHoraAula4());
                }

                diasAula.add(disciplinaCurso.getDiaDeAula1());
                if(Objects.nonNull(disciplinaCurso.getDiaDeAula2())){
                    diasAula.add(disciplinaCurso.getDiaDeAula2());
                }


                disciplinaCursoDTO.setId(disciplinaCurso.getId());
                disciplinaCursoDTO.setDisciplina(disciplinaCurso.getDisciplina());
                disciplinaCursoDTO.setFaculdade(disciplinaCurso.getFaculdade());
                disciplinaCursoDTO.setSemestre(disciplinaCurso.getSemestre());
                disciplinaCursoDTO.setCurso(disciplinaCurso.getCurso());
                disciplinaCursoDTO.setHorasAula(horasAula);
                disciplinaCursoDTO.setDiasDeAula(diasAula);

                disciplinasCursoDTO.add(disciplinaCursoDTO);
            }



            return ResponseEntity.ok(disciplinasCursoDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary =  "Retornar todas as disciplinas de registradas para determinado curso (pesquisa por sigla do curso)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Disciplinas do curso retornadas"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/disciplinasCursos/cursos/teste")
    public ResponseEntity<List<DisciplinaCursoDTO>> retornarTeste(@Param("siglaCurso") String siglaCurso, @Param("codFaculdade") String codFaculdade){
        List<DisciplinaCurso> disciplinasPorCurso = disciplinaCursoRepository.getDisciplinaCursoByCursoSiglaCursoAndFaculdadeCodFaculdade(siglaCurso, codFaculdade);
        List<DisciplinaCursoDTO> disciplinasCursoDTO = new ArrayList<>();
        if(Objects.nonNull(disciplinasPorCurso)){

            for (DisciplinaCurso disciplinaCurso: disciplinasPorCurso) {
                DisciplinaCursoDTO disciplinaCursoDTO = new DisciplinaCursoDTO();
                List<HorarioAula> horasAula = new ArrayList<>();
                List<DiaSemana> diasAula = new ArrayList<>();

                horasAula.add(disciplinaCurso.getHoraAula1());
                horasAula.add(disciplinaCurso.getHoraAula2());
                horasAula.add(disciplinaCurso.getHoraAula3());
                horasAula.add(disciplinaCurso.getHoraAula4());

                diasAula.add(disciplinaCurso.getDiaDeAula1());
                diasAula.add(disciplinaCurso.getDiaDeAula2());

                disciplinaCursoDTO.setId(disciplinaCurso.getId());
                disciplinaCursoDTO.setDisciplina(disciplinaCurso.getDisciplina());
                disciplinaCursoDTO.setFaculdade(disciplinaCurso.getFaculdade());
                disciplinaCursoDTO.setSemestre(disciplinaCurso.getSemestre());
                disciplinaCursoDTO.setCurso(disciplinaCurso.getCurso());
                disciplinaCursoDTO.setHorasAula(horasAula);
                disciplinaCursoDTO.setDiasDeAula(diasAula);

                disciplinasCursoDTO.add(disciplinaCursoDTO);
            }



            return ResponseEntity.ok(disciplinasCursoDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }



    @Operation(summary =  "Cadastrar um nova relação disciplina-curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Relação disciplina-curso salva"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @PostMapping("/disciplinasCursos")
    public void salvarDisciplinaCurso(@RequestBody DisciplinaCursoDTO disciplinaCursoDTO){
        DisciplinaCurso disciplinaCurso = new DisciplinaCurso();
//        disciplinaCurso.setFaculdade(new Faculdade(disciplinaCursoDTO.getFaculdadeId()));
//        disciplinaCurso.setCurso(new Curso(disciplinaCursoDTO.getCursoId()));
//        disciplinaCurso.setDisciplina(new Disciplina(disciplinaCursoDTO.getDisciplinaId()));


        disciplinaCurso.setFaculdade(disciplinaCursoDTO.getFaculdade());
        disciplinaCurso.setCurso(disciplinaCursoDTO.getCurso());
        disciplinaCurso.setDisciplina(disciplinaCursoDTO.getDisciplina());
//        disciplinaCurso.setHoraAula1(new HorarioAula(disciplinaCursoDTO.getHorasAula().get(0).getId()));
//        disciplinaCurso.setHoraAula1(new HorarioAula(disciplinaCursoDTO.getHorasAula().get(1).getId()));
//        disciplinaCurso.setHoraAula2(new HorarioAula(disciplinaCursoDTO.getHorasAula().get(2).getId()));
//        disciplinaCurso.setHoraAula3(new HorarioAula(disciplinaCursoDTO.getHorasAula().get(3).getId()));

        if(!disciplinaCursoDTO.getHorasAula().isEmpty()){
            disciplinaCurso.setHoraAula1(disciplinaCursoDTO.getHorasAula().get(0));
            disciplinaCurso.setHoraAula2(disciplinaCursoDTO.getHorasAula().get(1));
            disciplinaCurso.setDiaDeAula1(disciplinaCursoDTO.getDiasDeAula().get(0));
            disciplinaCurso.setSemestre(disciplinaCursoDTO.getSemestre());

            if (disciplinaCursoDTO.getHorasAula().size() == 4) {
                disciplinaCurso.setHoraAula3(disciplinaCursoDTO.getHorasAula().get(2));
                disciplinaCurso.setHoraAula4(disciplinaCursoDTO.getHorasAula().get(3));

            }

            if(disciplinaCursoDTO.getDiasDeAula().size() == 2){
                disciplinaCurso.setDiaDeAula2(disciplinaCursoDTO.getDiasDeAula().get(1));
            }
        }


        log.info(String.valueOf(disciplinaCurso));

        disciplinaCursoRepository.save(disciplinaCurso);
    }




    @Operation(summary =  "Editar uma relação disciplina-curso existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Relação disciplina-curso alterada e salva"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @PutMapping("/disciplinasCursos/{id}")
    public void editarDisciplinaCurso(@RequestBody DisciplinaCurso disciplinaCurso, @PathVariable Long id){
        disciplinaCurso.setId(id);
        disciplinaCursoRepository.save(disciplinaCurso);
    }



    @Operation(summary =  "Remover uma relação disciplina-curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Relação disciplina-curso removida"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/disciplinasCursos/{id}")
    public void removerDisciplinaCurso(@PathVariable Long id){
        disciplinaCursoRepository.deleteById(id);
    }

}
