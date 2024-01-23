package br.com.jherrerocavadas.saeapi.api;

import br.com.jherrerocavadas.saeapi.dto.DisciplinaCurso;
import br.com.jherrerocavadas.saeapi.repository.DisciplinaCursoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<DisciplinaCurso> retornarDisciplinaCursos(){
        return disciplinaCursoRepository.findAll();
    }



    @Operation(summary =  "Retornar todas as disciplinas de registradas para determinado nome de curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Disciplinas do curso retornadas"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/disciplinasCursos/cursos/{nomeCurso}")
    public ResponseEntity<List<DisciplinaCurso>> retornarDisciplinaCursoPorNomeCurso(@PathVariable String nomeCurso){
        List<DisciplinaCurso> disciplinasPorCurso = disciplinaCursoRepository.getDisciplinaCursoByCursoNomeCurso(nomeCurso);
        if(Objects.nonNull(disciplinasPorCurso)){
            return ResponseEntity.ok(disciplinasPorCurso);
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
    public ResponseEntity<List<DisciplinaCurso>> retornarDisciplinaCursoPorSiglaCurso(@Param("siglaCurso") String siglaCurso){
        List<DisciplinaCurso> disciplinasPorCurso = disciplinaCursoRepository.getDisciplinaCursoByCursoSiglaCurso(siglaCurso);
        if(Objects.nonNull(disciplinasPorCurso)){
            return ResponseEntity.ok(disciplinasPorCurso);
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
    public ResponseEntity<List<DisciplinaCurso>> retornarTeste(@Param("siglaCurso") String siglaCurso, @Param("codFaculdade") String codFaculdade){
        List<DisciplinaCurso> disciplinasPorCurso = disciplinaCursoRepository.getDisciplinaCursoByCursoSiglaCursoAndFaculdadeCodFaculdade(siglaCurso, codFaculdade);
        if(Objects.nonNull(disciplinasPorCurso)){
            return ResponseEntity.ok(disciplinasPorCurso);
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
    @PostMapping("/disciplinaCursos")
    public void salvarDisciplinaCurso(@RequestBody DisciplinaCurso disciplinaCurso){
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
