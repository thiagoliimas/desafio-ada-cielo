package br.com.desafiocielo.desafio1.controllers;

import br.com.desafiocielo.desafio1.domain.models.User;
import br.com.desafiocielo.desafio1.domain.models.dtos.NaturalPersonDto;
import br.com.desafiocielo.desafio1.services.NaturalPersonService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Pessoa Física")
public class NaturalPersonController {

    private final NaturalPersonService service;

    @Autowired
    public NaturalPersonController(NaturalPersonService service) {
        this.service = service;
    }

    @PostMapping("/pessoa-fisica")
    public ResponseEntity<User> saveNaturalPerson(@Valid @RequestBody NaturalPersonDto naturalPersonDto){
        return new ResponseEntity<>(service.createUser(naturalPersonDto), HttpStatus.CREATED);
    }

    @GetMapping("/pessoa-fisica/{id}")
    public ResponseEntity<User> getNaturalPersonById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(service.getNaturalPersonById(id), HttpStatus.OK);
    }

    @GetMapping("/pessoa-fisica/prospect")
    public ResponseEntity<User> prospect() throws NoSuchFieldException {
        return new ResponseEntity<>(service.prospect(), HttpStatus.OK);
    }


    @PutMapping("/pessoa-fisica/{id}")
    public ResponseEntity updateNaturalPerson(@PathVariable("id") UUID id,  @RequestBody @Valid NaturalPersonDto pessoaFisicaDTO){
        service.updateNaturalPerson(id, pessoaFisicaDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/pessoa-fisica/{id}")
    public ResponseEntity deleteNaturalPerson(@PathVariable ("id") UUID id){
        service.deletarCliente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
