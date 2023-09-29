package br.com.desafiocielo.desafio1.config;

import br.com.desafiocielo.desafio1.domain.models.NaturalPerson;
import br.com.desafiocielo.desafio1.repositories.NaturalPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class TesteConfig implements CommandLineRunner {

    @Autowired
    private NaturalPersonRepository naturalPersonRepository;

    @Override
    public void run(String... args) throws Exception {
        NaturalPerson person = new NaturalPerson(
                UUID.randomUUID(),1587L,"Thiago","02589654785","thiago@email.com");
        naturalPersonRepository.save(person);
    }
}
