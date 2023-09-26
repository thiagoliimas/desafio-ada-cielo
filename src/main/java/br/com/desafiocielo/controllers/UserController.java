package br.com.desafiocielo.controllers;

import br.com.desafiocielo.domain.models.User;
import br.com.desafiocielo.domain.models.dto.NaturalPersonDto;
import br.com.desafiocielo.services.NaturalPersonService;
import br.com.desafiocielo.util.error.ApiError;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cliente")
@Tag(name = "User")
public class UserController {


    private final NaturalPersonService service;

    @Autowired
    public UserController(NaturalPersonService service) {
        this.service = service;
    }

    @GetMapping("/pessoa-fisica")
    public ResponseEntity getAllNaturalPerson(){
        List<NaturalPersonDto> cliente = service.getAllNaturalPerson();

        if(cliente.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError("No clients registered"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @GetMapping("/pessoa-fisica/{id}")
    public ResponseEntity getNaturalPersonById(@PathVariable("id") UUID id){
        Optional<NaturalPersonDto> cliente = service.getNaturalPersonById(id);

        if(cliente.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError("Client not found"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @PostMapping("/pessoa-fisica")
    public ResponseEntity<User> saveNaturalPerson(@Valid @RequestBody NaturalPersonDto naturalPersonDto){
        service.saveNaturalPerson(naturalPersonDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/pessoa-fisica/{id}")
    public ResponseEntity updateNaturalPerson(@PathVariable("id") UUID id,
                                              @Valid @RequestBody NaturalPersonDto pessoaFisicaDTO){
        service.updateNaturalPerson(id, pessoaFisicaDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/pessoa-fisica/{id}")
    public ResponseEntity deleteNaturalPerson(@PathVariable ("id") UUID id){
        Optional<Boolean> deleted = service.deletarCliente(id);
        if(deleted.get()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError("Client not found"));
    }
}
