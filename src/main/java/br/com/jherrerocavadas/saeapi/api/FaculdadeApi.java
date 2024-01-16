package br.com.jherrerocavadas.saeapi.api;

import br.com.jherrerocavadas.saeapi.dto.Faculdade;
import br.com.jherrerocavadas.saeapi.repository.FaculdadeRepository;

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
public class FaculdadeApi {

    private final FaculdadeRepository faculdadeRepository;

    @Autowired
    private FaculdadeApi(FaculdadeRepository faculdadeRepository){
        this.faculdadeRepository = faculdadeRepository;
    }

    @Operation(summary =  "Verificar o serviço de faculdades")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Serviço OK"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/check-faculdade-service")
    public ResponseEntity<String> verificaServico(){
        return ResponseEntity.ok().body("Serviço operacional");
    }

    @Operation(summary =  "Retornar todas as faculdades cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Faculdades retornadas"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/faculdades")
    public List<Faculdade> retornarFaculdades(){
        return faculdadeRepository.findAll();
    }



    @Operation(summary =  "Retornar faculdade de acordo com o nome da faculdade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "faculdade retornada"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/faculdades/{nomeFaculdade}")
    public ResponseEntity<Faculdade> retornarFaculdadePorNomeFaculdade(@PathVariable String nomeFaculdade){
        Faculdade faculdade = faculdadeRepository.getFaculdadeByNomeFaculdade(nomeFaculdade);
        if(Objects.nonNull(faculdade)){
            return ResponseEntity.ok(faculdade);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary =  "Cadastrar uma nova faculdade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Faculdade salva"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @PostMapping("/faculdades")
    public void salvarFaculdade(@RequestBody Faculdade faculdade){
        faculdadeRepository.save(faculdade);
    }




    @Operation(summary =  "Editar uma faculdade existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Faculdade alterada e salva"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @PutMapping("/faculdades/{id}")
    public void editarFaculdade(@RequestBody Faculdade faculdade, @PathVariable Long id){
        faculdade.setId(id);
        faculdadeRepository.save(faculdade);
    }



    @Operation(summary =  "Remover uma faculdade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Faculdade removida"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/faculdades/{id}")
    public void removerFaculdade(@PathVariable Long id){
        faculdadeRepository.deleteById(id);
    }

}
