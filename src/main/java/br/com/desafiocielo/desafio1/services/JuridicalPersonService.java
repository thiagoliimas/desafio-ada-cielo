package br.com.desafiocielo.desafio1.services;

import br.com.desafiocielo.desafio1.domain.models.JuridicalPerson;
import br.com.desafiocielo.desafio1.domain.models.User;
import br.com.desafiocielo.desafio1.domain.models.dtos.JuridicalPersonDto;
import br.com.desafiocielo.desafio1.repositories.JuridicalPersonRepository;
import br.com.desafiocielo.desafio2.ProspectQueue;
import br.com.desafiocielo.desafio2.Queue;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class JuridicalPersonService {

    private final JuridicalPersonRepository repository;

    Queue<User> queue = new ProspectQueue<>();

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
        queue.addQ(juridicalPerson);
        return juridicalPerson;
    }

    public void saveJuridicalPerson(JuridicalPerson juridicalPerson) {
        this.repository.save(juridicalPerson);
    }

    public void updateJuridicalPerson(UUID id, JuridicalPersonDto juridicalPersonDTO) {
        Optional<JuridicalPerson> juridicalPerson = repository.findById(id);

        if(juridicalPerson.isPresent()){
            juridicalPerson.get().setCnpj(juridicalPersonDTO.getCnpj());
            juridicalPerson.get().setCompanyName(juridicalPersonDTO.getCompanyName());
            juridicalPerson.get().setId(id);
            juridicalPerson.get().setEmail(juridicalPersonDTO.getEmail());
            juridicalPerson.get().setCpf(juridicalPersonDTO.getCpf());
            juridicalPerson.get().setMerchantCategoryCode(juridicalPersonDTO.getMerchantCategoryCode());
            juridicalPerson.get().setName(juridicalPersonDTO.getName());
            repository.save(juridicalPerson.get());
            if(!queue.exist(juridicalPerson.get())) queue.addQ(juridicalPerson.get());
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
