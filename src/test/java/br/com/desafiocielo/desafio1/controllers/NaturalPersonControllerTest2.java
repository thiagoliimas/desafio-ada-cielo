//package br.com.desafiocielo.desafio1.controllers;
//
//import br.com.desafiocielo.desafio1.domain.models.NaturalPerson;
//import br.com.desafiocielo.desafio1.domain.models.User;
//import br.com.desafiocielo.desafio1.domain.models.dtos.NaturalPersonDto;
//import br.com.desafiocielo.desafio1.services.NaturalPersonService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.UUID;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(NaturalPersonController.class)
//@DataJpaTest
//class NaturalPersonControllerTest2 {
//
//    @MockBean
//    NaturalPersonService naturalPersonService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    ObjectMapper mapper = new ObjectMapper();
//
//    NaturalPerson naturalPerson = new NaturalPerson();
//
//    NaturalPersonDto naturalPersonDto = new NaturalPersonDto();
//
//    @BeforeEach
//    public void setup(){
//
//        naturalPersonDto = new NaturalPersonDto(
//                "02587456936", 1478L, "UserTest", "teste@mail.com");
//
//        naturalPerson = new NaturalPerson(
//                UUID.randomUUID(), naturalPersonDto.getMerchantCategoryCode(), naturalPersonDto.getName(), naturalPersonDto.getCpf(), naturalPersonDto.getEmail());
//    }
//
//    @Test
//    @DisplayName("deveRetornarSucesso_QuandoSalvarUmUsuario")
//    @Order(1)
//    void saveNaturalPersonSucess() throws Exception {
//
//        when(naturalPersonService.createUser(ArgumentMatchers.any(NaturalPersonDto.class))).thenReturn(naturalPerson);
//
//        mockMvc.perform(post("/cliente/pessoa-fisica")
//                .content(mapper.writeValueAsString(naturalPerson))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name").value(naturalPersonDto.getName()));
//    }
//
//    @Test
//    @DisplayName("deveRetornarSucesso_QuandoAtualizarUmCliente")
//    @Order(5)
//    void ugetNaturalPersonByIdSucess() throws Exception {
//
//        when(naturalPersonService.getNaturalPersonById(UUID.randomUUID())).thenReturn(naturalPerson);
//
//        mockMvc.perform((MockMvcRequestBuilders.get("/cliente/pessoa-juridica{id}", UUID.randomUUID())
//                .contentType(MediaType.APPLICATION_JSON)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @DisplayName("deveRetornarSucesso_QuandoDeletarUmCliente")
//    @Order(7)
//    void deleteNaturalPersonSucess() throws Exception {
//        when(naturalPersonService.updateNaturalPerson(ArgumentMatchers.any(UUID.class), ArgumentMatchers.any(NaturalPersonDto.class))).thenReturn(naturalPerson);
//
//        mockMvc.perform(post("/cliente/pessoa-fisica")
//                .content(mapper.writeValueAsString(naturalPerson))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name").value(naturalPersonDto.getName()));
//    }
//}