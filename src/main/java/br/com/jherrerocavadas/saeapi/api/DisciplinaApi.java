package br.com.jherrerocavadas.saeapi.api;


import br.com.jherrerocavadas.saeapi.entity.Disciplina;
import br.com.jherrerocavadas.saeapi.repository.DisciplinaRepository;
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
public class DisciplinaApi {


    private final DisciplinaRepository disciplinaRepository;

    @Autowired
    private DisciplinaApi(DisciplinaRepository disciplinaRepository){
        this.disciplinaRepository = disciplinaRepository;
    }

    @Operation(summary =  "Verificar o serviço de disciplinas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Serviço OK"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/check-disciplinas-service")
    public ResponseEntity<String> verificaServico(){
        return ResponseEntity.ok().body("Serviço operacional");
    }

    @Operation(summary =  "Retornar todas as disciplinas cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "disciplinas retornadas"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/disciplinas")
    public List<Disciplina> retornarDisciplinas(){
        return disciplinaRepository.findAll();
    }


    @Operation(summary =  "Retornar a disciplina de acordo com seu código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Disciplina retornada"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/disciplinas/codigoDisciplina/{codigoDisciplina}")
    public ResponseEntity<Disciplina> retornarDisciplinaPorCodigo(@PathVariable String codigoDisciplina){
        Disciplina disciplina = disciplinaRepository.getDisciplinaByCodDisciplina(codigoDisciplina);
        if(Objects.nonNull(disciplina)){
            return ResponseEntity.ok(disciplina);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary =  "Retornar a disciplina de acordo com seu nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Disciplina retornada"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/disciplinas/nomeDisciplina/{nomeDisciplina}")
    public ResponseEntity<Disciplina> retornarDisciplinaPorNome(@PathVariable String nomeDisciplina){
        Disciplina disciplina = disciplinaRepository.getDisciplinaByNomeDisciplina(nomeDisciplina);
        if(Objects.nonNull(disciplina)){
            return ResponseEntity.ok(disciplina);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary =  "Retornar a disciplina de acordo com sua Sigla")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Disciplina retornada"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/disciplinas/siglaDisciplina/{siglaDisciplina}")
    public ResponseEntity<Disciplina> retornarDisciplinaPorSigla(@PathVariable String siglaDisciplina){
        Disciplina disciplina = disciplinaRepository.getDisciplinaBySiglaDisciplina(siglaDisciplina);
        if(Objects.nonNull(disciplina)){
            return ResponseEntity.ok(disciplina);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }






    @Operation(summary =  "Retornar todas as disciplinas de acordo com a quantidade de aulas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Disciplinas retornados"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/disciplinas/quantidadeAulas/{quantidadeAulas}")
    public ResponseEntity<List<Disciplina>> retornarDisciplinaPorNumeroAula(@PathVariable Integer quantidadeAulas){
        List<Disciplina> disciplinas = disciplinaRepository.getDisciplinaByQuantidadeAulas(quantidadeAulas);
        if(Objects.nonNull(disciplinas)){
            return ResponseEntity.ok(disciplinas);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary =  "Retornar todas as disciplinas de acordo que de acordo com o indicador de disciplina especial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Disciplinas retornadas"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/disciplinas/DisciplinaEspecial")
    public ResponseEntity<List<Disciplina>> retornarDisciplinaPorIndicadorIntervalo(@Param("indicadorDisciplinaEspecial") Boolean indicadorDisciplinaEspecial){
        List<Disciplina> disciplinas = disciplinaRepository.getDisciplinaByIsDisciplinaEspecial(indicadorDisciplinaEspecial);
        if(Objects.nonNull(disciplinas)){
            return ResponseEntity.ok(disciplinas);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary =  "Cadastrar um novo disciplina")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Disciplina salva"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @PostMapping("/disciplinas")
    public void salvarDisciplina(@RequestBody Disciplina disciplina){
        disciplinaRepository.save(disciplina);
    }




    @Operation(summary =  "Editar uma disciplina existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Disciplina alterada e salva"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @PutMapping("/disciplinas/{id}")
    public void editarDisciplina(@RequestBody Disciplina disciplina, @PathVariable Long id){
        disciplina.setId(id);
        disciplinaRepository.save(disciplina);
    }



    @Operation(summary =  "Remover uma disciplina")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Disciplina removida"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/disciplinas/{id}")
    public void removerDisciplina(@PathVariable Long id){
        disciplinaRepository.deleteById(id);
    }

}
