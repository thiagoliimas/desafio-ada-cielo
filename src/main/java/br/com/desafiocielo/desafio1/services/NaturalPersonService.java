package br.com.desafiocielo.desafio1.services;

import br.com.desafiocielo.desafio1.domain.models.NaturalPerson;
import br.com.desafiocielo.desafio1.domain.models.User;
import br.com.desafiocielo.desafio1.domain.models.dtos.NaturalPersonDto;
import br.com.desafiocielo.desafio1.repositories.NaturalPersonRepository;
import br.com.desafiocielo.desafio1.util.JsonUtil;
import br.com.desafiocielo.desafio2.ProspectQueue;
import br.com.desafiocielo.desafio2.Queue;
import br.com.desafiocielo.desafio3.SqsService_Desafio3;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class NaturalPersonService {

    private final NaturalPersonRepository repository;

    Queue<User> queue_Desafio2 = new ProspectQueue<>();

    private final SqsService_Desafio3 sqsService_Desafio3;

    @Value("${spring.cloud.aws.endpoint-pf}")
    private String urlQueue;

    @Autowired
    public NaturalPersonService(NaturalPersonRepository repository, SqsService sqsService_Desafio3) {
        this.repository = repository;
        this.sqsService_Desafio3 = sqsService_Desafio3;
    }

    public NaturalPerson getNaturalPersonById(UUID id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public NaturalPerson createUser(NaturalPersonDto naturalPersonDto) {
        var naturalPerson = new NaturalPerson(naturalPersonDto);
        this.saveNaturalPerson(naturalPerson);
//        queue_Desafio2.addQ(naturalPerson); //Adicionando o User à fila criada manualmente
        sqsService_Desafio3.sendUserToQueue(urlQueue, naturalPerson); // Persiste o objeto no banco e envia-o para a fila da aws
        return naturalPerson;
    }

    public void saveNaturalPerson(NaturalPerson naturalPerson) {
        this.repository.save(naturalPerson);
    }

    public void updateNaturalPerson(UUID id, NaturalPersonDto pfDTO) {
        Optional<NaturalPerson> naturalPerson = repository.findById(id);

        if(naturalPerson.isPresent()){
            var pf = naturalPerson.get();
            pf.setCpf(pfDTO.getCpf());
            pf.setEmail(pfDTO.getEmail());
            pf.setName(pfDTO.getName());
            pf.setId(id);
            pf.setMerchantCategoryCode(pfDTO.getMerchantCategoryCode());
            repository.save(pf);
            if(!queue_Desafio2.exist(pf)) queue_Desafio2.addQ(pf); //Antes de adicionar verifica se já existe
        } else throw new EntityNotFoundException();
    }

    public void deletarCliente(UUID id){
        repository.findById(id).orElseThrow(EntityNotFoundException::new);;
        repository.deleteById(id);
    }

    public String prospect() throws JsonProcessingException {
//       queue_Desafio2.removeQ(); // Estrutura de dados (fila) criado para o desafio 2
        ReceiveMessageResult result = sqsService_Desafio3.receiveUserFromQueue(urlQueue); //Recebe a mensagem da fila da aws
        String receiptHandle = result.getMessages().get(0).getReceiptHandle(); //Extrai o receipt handle necessário para fazer a exclusão da mensagem na fila
        sqsService_Desafio3.deleteUser(urlQueue, receiptHandle); //Retira a mensagem da fila
        return JsonUtil.extractMessageBody(result.getMessages().get(0).getBody()); //Retorna o corpo da mensagem (User) formatado como Json
    }
}
