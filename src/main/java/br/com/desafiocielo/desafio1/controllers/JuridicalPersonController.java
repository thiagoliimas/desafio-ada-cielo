package br.com.desafiocielo.desafio1.controllers;

import br.com.desafiocielo.desafio1.domain.models.User;
import br.com.desafiocielo.desafio1.domain.models.dtos.JuridicalPersonDto;
import br.com.desafiocielo.desafio1.services.JuridicalPersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Pessoa Jurídica")
public class JuridicalPersonController {

    private final JuridicalPersonService service;

    @Autowired
    public JuridicalPersonController(JuridicalPersonService service) {
        this.service = service;
    }

    @PostMapping("/pessoa-juridica")
    public ResponseEntity<User> saveJuridicalPerson(@Valid @RequestBody JuridicalPersonDto juridicalPersonDto){
        return new ResponseEntity<>(service.createUser(juridicalPersonDto), HttpStatus.CREATED);
    }

    @GetMapping("/pessoa-juridica/{id}")
    public ResponseEntity<User> getJuridicalPersonById(@PathVariable("id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getJuridicalPersonById(id));
    }

    @GetMapping("/pessoa-juridica/prospect")
    public ResponseEntity prospect() throws JsonProcessingException {
        return new ResponseEntity<>(service.prospect(), HttpStatus.OK);
    }


    @PutMapping("/pessoa-juridica/{id}")
    public ResponseEntity updateJuridicalPerson(@PathVariable("id") UUID id, @Valid @RequestBody JuridicalPersonDto juridicalPersonDto){
        service.updateJuridicalPerson(id, juridicalPersonDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/pessoa-juridica/{id}")
    public ResponseEntity deleteJuridicalPerson(@PathVariable ("id") UUID id){
        service.deletarCliente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
