//package br.com.desafiocielo.desafio1.services;
//
//import br.com.desafiocielo.desafio1.domain.models.NaturalPerson;
//import br.com.desafiocielo.desafio1.repositories.NaturalPersonRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class NaturalPersonServiceTest {
//
//    @Autowired
//    private NaturalPersonService service;
//
//    @Mock
//    private NaturalPersonRepository repository;
//
//    NaturalPerson naturalPerson = new NaturalPerson();
//
//    @BeforeEach
//    void setup(){
////        MockitoAnnotations.openMocks(this);
////        this.service = new NaturalPersonService(repository);
////        naturalPerson = new NaturalPerson(
////                UUID.randomUUID(),15874L, "Thiago","25896325478","thiago@email.com");
////    }
//
//    @Test
//    void getNaturalPersonById() {
//        Mockito.when(repository.findById(UUID.randomUUID()))
//                .thenReturn(Optional.ofNullable(naturalPerson));
//
//        var response = service.getNaturalPersonById(UUID.randomUUID());
//
//        Mockito.verify(repository).findById(UUID.randomUUID());
//
//        assertNotNull(response);
//    }
//
//    @Test
//    void createUser() {
//    }
//
//    @Test
//    void saveNaturalPerson() {
//    }
//
//    @Test
//    void updateNaturalPerson() {
//    }
//
//    @Test
//    void deletarCliente() {
//    }
//
//    @Test
//    void prospect() {
//    }
//}