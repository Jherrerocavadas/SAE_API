package br.com.jherrerocavadas.saeapi.api;

import br.com.jherrerocavadas.saeapi.enums.DiaSemana;
import br.com.jherrerocavadas.saeapi.enums.Periodo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class UtilsApi {

    @Operation(summary =  "Verificar o serviço de utils")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Serviço OK"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/check-utils-service")
    public ResponseEntity<String> verificaServico(){
        return ResponseEntity.ok().body("Serviço operacional");
    }


    //Utilizar para retornar as strings no front
    @Operation(summary =  "Retornar todos os periodos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "periodos retornados"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/periodos")
    public List<String> retornarPeriodos(){

        List<String> periodos = new ArrayList<>();

        for (Periodo periodosEnum:
                List.of(Periodo.values())) {
            periodos.add(periodosEnum.getNomePeriodo());
        }
        return periodos;
    }


    //Utilizar para retornar as strings no front
    @Operation(summary =  "Retornar todos os dias da semana cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Dias da semana retornados"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/diasSemana")
    public List<String> retornarDiasdaSemana(){
        List<String> dias = new ArrayList<>();
        
        for (DiaSemana diaSemanaEnum:
                List.of(DiaSemana.values())) {
            dias.add(diaSemanaEnum.getDiaDaSemana());
        }
        
        return dias;
    }
}
