package br.com.desafiocielo.desafio1.services;

import br.com.desafiocielo.desafio1.domain.models.NaturalPerson;
import br.com.desafiocielo.desafio1.domain.models.dtos.NaturalPersonDto;
import br.com.desafiocielo.desafio1.repositories.NaturalPersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class NaturalPersonService {

    private final NaturalPersonRepository repository;

    @Autowired
    public NaturalPersonService(NaturalPersonRepository repository) {
        this.repository = repository;
    }

    public NaturalPerson getNaturalPersonById(UUID id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public NaturalPerson createUser(NaturalPersonDto naturalPersonDto) {
        var naturalPerson = new NaturalPerson(naturalPersonDto);
        this.saveNaturalPerson(naturalPerson);
        return naturalPerson;
    }

    public void saveNaturalPerson(NaturalPerson naturalPerson) {
        this.repository.save(naturalPerson);
    }

    public void updateNaturalPerson(UUID id, NaturalPersonDto naturalPersonDTO) {
        Optional<NaturalPerson> naturalPerson = repository.findById(id);

        if(naturalPerson.isPresent()){
            repository.save(new NaturalPerson(naturalPersonDTO));
        } else throw new EntityNotFoundException();
    }

    public void deletarCliente(UUID id){
        repository.findById(id).orElseThrow(EntityNotFoundException::new);;
        repository.deleteById(id);
    }

}
