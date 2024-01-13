package br.com.jherrerocavadas.saeapi.api;

import br.com.jherrerocavadas.saeapi.dto.HorarioAula;
import br.com.jherrerocavadas.saeapi.enums.Periodo;
import br.com.jherrerocavadas.saeapi.repository.HorarioAulaRepository;
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
public class HorarioAulaApi {

    private final HorarioAulaRepository horarioAulaRepository;

    @Autowired
    private HorarioAulaApi(HorarioAulaRepository horarioAulaRepository){
        this.horarioAulaRepository = horarioAulaRepository;
    }

    @Operation(summary =  "Verificar o serviço de horários de aula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Serviço OK"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/check-horario-aula-service")
    public ResponseEntity<String> verificaServico(){
        return ResponseEntity.ok().body("Serviço operacional");
    }

    @Operation(summary =  "Retornar todos os horários de aula cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Horarios de aula retornados"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/horariosAula")
    public List<HorarioAula> retornarHorariosAula(){
        return horarioAulaRepository.findAll();
    }


    @Operation(summary =  "Retornar todos os horarios de aula de acordo com um período")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Horarios de aula retornados"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/horariosAula/periodos/{periodo}")
    public ResponseEntity<List<HorarioAula>> retornarHorarioAulaPorPeriodo(@PathVariable Periodo periodo){
        List<HorarioAula> horariosAula = horarioAulaRepository.getHorarioAulaByPeriodo(periodo);
        if(Objects.nonNull(horariosAula)){
            return ResponseEntity.ok(horariosAula);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary =  "Retornar todos os horarios de aula de acordo com o número da aula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Horarios de aula retornados"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/horariosAula/numeroAula/{numeroAulas}")
    public ResponseEntity<List<HorarioAula>> retornarHorarioAulaPorNumeroAula(@PathVariable Integer numeroAulas){
        List<HorarioAula> horariosAula = horarioAulaRepository.getHorarioAulaByNumeroAula(numeroAulas);
        if(Objects.nonNull(horariosAula)){
            return ResponseEntity.ok(horariosAula);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary =  "Retornar todos os horarios de aula de acordo que de acordo com o indicador de intervalo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Horarios de aula retornados"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @GetMapping("/horariosAula/indicadorIntervalo")
    public ResponseEntity<List<HorarioAula>> retornarHorarioAulaPorIndicadorIntervalo(@Param("indicadorIntervalo") Boolean indicadorIntervalo){
        List<HorarioAula> horariosAula = horarioAulaRepository.getHorarioAulaByIsIntervalo(indicadorIntervalo);
        if(Objects.nonNull(horariosAula)){
            return ResponseEntity.ok(horariosAula);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary =  "Cadastrar um novo horário de aula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Horário de aula salvo"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @PostMapping("/horariosAula")
    public void salvarHorarioAula(@RequestBody HorarioAula horarioAula){
        horarioAulaRepository.save(horarioAula);
    }




    @Operation(summary =  "Editar um horário de aula existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Horário de aula alterado e salvo"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @PutMapping("/horariosAula/{id}")
    public void editarHorarioAula(@RequestBody HorarioAula horarioAula, @PathVariable Long id){
        horarioAula.setId(id);
        horarioAulaRepository.save(horarioAula);
    }



    @Operation(summary =  "Remover um horário de aula")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description =  "Horário de aula removido"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/horariosAula/{id}")
    public void removerHorarioAula(@PathVariable Long id){
        horarioAulaRepository.deleteById(id);
    }

}
