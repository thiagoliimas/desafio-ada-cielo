package br.com.desafiocielo.desafio1.services;

import br.com.desafiocielo.desafio1.domain.models.JuridicalPerson;
import br.com.desafiocielo.desafio1.domain.models.dtos.JuridicalPersonDto;
import br.com.desafiocielo.desafio1.repositories.JuridicalPersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class JuridicalPersonService {

    private final JuridicalPersonRepository repository;

    @Autowired
    public JuridicalPersonService(JuridicalPersonRepository repository) {
        this.repository = repository;
    }

    public JuridicalPerson getJuridicalPersonById(UUID id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public JuridicalPerson createUser(JuridicalPersonDto juridicalPersonDto) {
        var juridicalPerson = new JuridicalPerson(juridicalPersonDto);
        this.saveJuridicalPerson(juridicalPerson);
        return juridicalPerson;
    }

    public void saveJuridicalPerson(JuridicalPerson juridicalPerson) {
        this.repository.save(juridicalPerson);
    }

    public void updateJuridicalPerson(UUID id, JuridicalPersonDto juridicalPersonDTO) {
        Optional<JuridicalPerson> juridicalPerson = repository.findById(id);

        if(juridicalPerson.isPresent()){
            repository.save(new JuridicalPerson(juridicalPersonDTO));
        } else throw new EntityNotFoundException();
    }

    public void deletarCliente(UUID id){
        repository.findById(id).orElseThrow(EntityNotFoundException::new);;
        repository.deleteById(id);
    }

}
