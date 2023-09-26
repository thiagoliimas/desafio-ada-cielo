package br.com.desafiocielo.services;

import br.com.desafiocielo.domain.models.NaturalPerson;
import br.com.desafiocielo.domain.models.dto.NaturalPersonDto;
import br.com.desafiocielo.repositories.NaturalPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class NaturalPersonService {

    private final NaturalPersonRepository repository;

    @Autowired
    public NaturalPersonService(NaturalPersonRepository repository) {
        this.repository = repository;
    }

    public List<NaturalPersonDto> getAllNaturalPerson() {

        List<NaturalPerson> naturalPersonList =  repository.findAll();
        List<NaturalPersonDto> NaturalPersonUserDTOList = new ArrayList<>();

        if (!naturalPersonList.isEmpty()) {
            naturalPersonList.forEach(user -> NaturalPersonUserDTOList
                    .add(new NaturalPersonDto(
                            user.getCpf(),
                            user.getMerchantCategoryCode(),
                            user.getName(),
                            user.getEmail())));
            return NaturalPersonUserDTOList;
        } else return NaturalPersonUserDTOList;
    }

    public Optional getNaturalPersonById(UUID id) {
        Optional<NaturalPerson> userOptional =  repository.findById(id);

        if (userOptional.isPresent()) {
            var user = userOptional.get();
            return Optional.of(new NaturalPersonDto(user.getCpf(), user.getMerchantCategoryCode(),
                    user.getName(), user.getEmail()));
        } else return userOptional;
    }

    public void saveNaturalPerson(NaturalPersonDto naturalPersonDto) {

        NaturalPerson naturalPerson = convertFromDTO(naturalPersonDto);

        repository.save(naturalPerson);
    }

    private NaturalPerson convertFromDTO(NaturalPersonDto naturalPersonDto){

        var naturalPerson = new NaturalPerson();
        naturalPerson.setId(UUID.randomUUID());
        naturalPerson.setName(naturalPersonDto.getName());
        naturalPerson.setEmail(naturalPersonDto.getEmail());
        naturalPerson.setMerchantCategoryCode(naturalPersonDto.getMerchantCategoryCode());
        naturalPerson.setCpf(naturalPersonDto.getCpf());

        return naturalPerson;
    }

    public void updateNaturalPerson(UUID id, NaturalPersonDto naturalPersonDTO) {
        Optional<NaturalPerson> naturalPerson = repository.findById(id);

        if(naturalPerson.isPresent()){
            naturalPerson.get().setName(naturalPersonDTO.getName());
            naturalPerson.get().setCpf(naturalPersonDTO.getCpf());
            naturalPerson.get().setEmail(naturalPersonDTO.getEmail());
            naturalPerson.get().setMerchantCategoryCode(naturalPersonDTO.getMerchantCategoryCode());
            repository.save(naturalPerson.get());
        }
    }

    public Optional<Boolean> deletarCliente(UUID id) {
        Optional naturalPerson = repository.findById(id);

        return naturalPerson.isPresent() ? Optional.of(true) : Optional.of(false);
    }
}
