package br.com.desafiocielo.desafio1.services;

import br.com.desafiocielo.desafio1.domain.models.JuridicalPerson;
import br.com.desafiocielo.desafio1.domain.models.User;
import br.com.desafiocielo.desafio1.domain.models.dtos.JuridicalPersonDto;
import br.com.desafiocielo.desafio1.repositories.JuridicalPersonRepository;
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
public class JuridicalPersonService {

    private final JuridicalPersonRepository repository;

    Queue<User> queue_Desafio2 = new ProspectQueue<>();

    private final SqsService_Desafio3 sqsService_Desafio3;

    @Value("${spring.cloud.aws.endpoint-pj}")
    private String urlQueue;

    @Autowired
    public JuridicalPersonService(JuridicalPersonRepository repository, SqsService_Desafio3 sqsServiceDesafio3) {
        this.repository = repository;
        sqsService_Desafio3 = sqsServiceDesafio3;
    }

    public JuridicalPerson getJuridicalPersonById(UUID id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public JuridicalPerson createUser(JuridicalPersonDto juridicalPersonDto) {
        var juridicalPerson = new JuridicalPerson(juridicalPersonDto);
        this.saveJuridicalPerson(juridicalPerson);
//        queue_Desafio2.addQ(juridicalPerson);  //Adicionando o User à fila criada manualmente
        sqsService_Desafio3
                .sendUserToQueue(urlQueue, juridicalPerson); // Persiste o objeto no banco e envia-o para a fila da aws
        return juridicalPerson;
    }

    public void saveJuridicalPerson(JuridicalPerson juridicalPerson) {
        this.repository.save(juridicalPerson);
    }

    public void updateJuridicalPerson(UUID id, JuridicalPersonDto pjDTO) {
        Optional<JuridicalPerson> juridicalPerson = repository.findById(id);

        if(juridicalPerson.isPresent()){
            var pj = juridicalPerson.get();
            pj.setCnpj(pjDTO.getCnpj());
            pj.setCompanyName(pjDTO.getCompanyName());
            pj.setId(id);
            pj.setEmail(pjDTO.getEmail());
            pj.setCpf(pjDTO.getCpf());
            pj.setMerchantCategoryCode(pjDTO.getMerchantCategoryCode());
            pj.setName(pjDTO.getName());
            repository.save(pj);
            if(!queue_Desafio2.exist(pj)) queue_Desafio2.addQ(pj);
        } else throw new EntityNotFoundException();
    }

    public void deletarCliente(UUID id){
        repository.findById(id).orElseThrow(EntityNotFoundException::new);;
        repository.deleteById(id);
    }

    public String prospect() throws JsonProcessingException {
//        return queue_Desafio2.removeQ(); // Estrutura de dados (fila) criado para o desafio 2
        ReceiveMessageResult result = sqsService_Desafio3.receiveUserFromQueue(urlQueue); //Recebe a mensagem da fila da aws
        String receiptHandle = result
                .getMessages()
                .get(0)
                .getReceiptHandle(); //Extrai o receipt handle necessário para fazer a exclusão da mensagem na fila
        sqsService_Desafio3.deleteUser(urlQueue, receiptHandle); //Retira a mensagem da fila
        return JsonUtil.extractMessageBody(result
                .getMessages()
                .get(0)
                .getBody()); //Retorna o corpo da mensagem (User) formatado como Json
    }

}
