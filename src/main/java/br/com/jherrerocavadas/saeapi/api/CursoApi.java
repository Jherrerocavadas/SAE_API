package br.com.jherrerocavadas.saeapi.api;


import br.com.jherrerocavadas.saeapi.entity.Curso;
import br.com.jherrerocavadas.saeapi.repository.CursoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
public class CursoApi {



    private final CursoRepository cursoRepository;

    @Autowired
    private CursoApi(CursoRepository cursoRepository){
        this.cursoRepository = cursoRepository;
    }

    @Operation(summary =  "Verificar o serviço de cursos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Serviço OK"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/check-cursos-service")
    public ResponseEntity<String> verificaServico(){
        return ResponseEntity.ok().body("Serviço operacional");
    }

    @Operation(summary =  "Retornar todos as cursos cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "cursos retornados"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/cursos")
    public List<Curso> retornarCursos(){
        return cursoRepository.findAll();
    }

//Caso o curso venha a ter um código, usar esse endpoint

//    @Operation(summary =  "Retornar a curso de acordo com seu código")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description =  "Curso retornado"),
//            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
//            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
//            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
//    })
//    @GetMapping("/cursos/codigoCurso/{codigoCurso}")
//    public ResponseEntity<Curso> retornarCursoPorCodigo(@PathVariable String codigoCurso){
//        Curso curso = cursoRepository.getCursoByCodCurso(codigoCurso);
//        if(Objects.nonNull(curso)){
//            return ResponseEntity.ok(curso);
//        }
//        else{
//            return ResponseEntity.notFound().build();
//        }
//    }

    @Operation(summary =  "Retornar a curso de acordo com seu nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Curso retornado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/cursos/nomeCurso/{nomeCurso}")
    public ResponseEntity<Curso> retornarCursoPorNome(@PathVariable String nomeCurso){
        Curso curso = cursoRepository.getCursoByNomeCurso(nomeCurso);
        if(Objects.nonNull(curso)){
            return ResponseEntity.ok(curso);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary =  "Retornar a curso de acordo com sua sigla")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Curso retornado"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/cursos/siglaCurso/{siglaCurso}")
    public ResponseEntity<Curso> retornarCursoPorSigla(@PathVariable String siglaCurso){
        Curso curso = cursoRepository.getCursoBySiglaCurso(siglaCurso);
        if(Objects.nonNull(curso)){
            return ResponseEntity.ok(curso);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }






    @Operation(summary =  "Retornar todas as cursos de acordo com a quantidade de semestres")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Cursos retornados"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/cursos/quantidadeSemestres/{quantidadeSemestres}")
    public ResponseEntity<List<Curso>> retornarCursoPorNumeroAula(@PathVariable Integer quantidadeSemestres){
        List<Curso> cursos = cursoRepository.getCursoByQtdSemestres(quantidadeSemestres);
        if(Objects.nonNull(cursos)){
            return ResponseEntity.ok(cursos);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary =  "Cadastrar um novo curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Curso salvo"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @PostMapping("/cursos")
    public void salvarCurso(@RequestBody Curso curso){
        cursoRepository.save(curso);
    }




    @Operation(summary =  "Editar um curso existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Curso alterada e salvo"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @PutMapping("/cursos/{id}")
    public void editarCurso(@RequestBody Curso curso, @PathVariable Long id){
        curso.setId(id);
        cursoRepository.save(curso);
    }



    @Operation(summary =  "Remover um curso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Curso removido"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/cursos/{id}")
    public void removerCurso(@PathVariable Long id){
        cursoRepository.deleteById(id);
    }

}
