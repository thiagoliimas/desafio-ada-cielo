package br.com.desafiocielo.desafio1.services;

import br.com.desafiocielo.desafio1.domain.models.NaturalPerson;
import br.com.desafiocielo.desafio1.domain.models.User;
import br.com.desafiocielo.desafio1.domain.models.dtos.NaturalPersonDto;
import br.com.desafiocielo.desafio1.repositories.NaturalPersonRepository;
import br.com.desafiocielo.desafio2.ProspectQueue;
import br.com.desafiocielo.desafio2.Queue;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class NaturalPersonService {

    private final NaturalPersonRepository repository;

    Queue<User> queue = new ProspectQueue<>();

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
        queue.addQ(naturalPerson);
        return naturalPerson;
    }

    public void saveNaturalPerson(NaturalPerson naturalPerson) {
        this.repository.save(naturalPerson);
    }

    public void updateNaturalPerson(UUID id, NaturalPersonDto naturalPersonDTO) {
        Optional<NaturalPerson> naturalPerson = repository.findById(id);

        if(naturalPerson.isPresent()){
            naturalPerson.get().setCpf(naturalPersonDTO.getCpf());
            naturalPerson.get().setEmail(naturalPersonDTO.getEmail());
            naturalPerson.get().setName(naturalPersonDTO.getName());
            naturalPerson.get().setId(id);
            naturalPerson.get().setMerchantCategoryCode(naturalPersonDTO.getMerchantCategoryCode());
            repository.save(naturalPerson.get());
            if(!queue.exist(naturalPerson.get())) queue.addQ(naturalPerson.get());
        } else throw new EntityNotFoundException();
    }

    public void deletarCliente(UUID id){
        repository.findById(id).orElseThrow(EntityNotFoundException::new);;
        repository.deleteById(id);
    }

    public User prospect() throws NoSuchFieldException {
       return queue.removeQ();
    }

}
