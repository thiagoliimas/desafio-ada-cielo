package br.com.desafiocielo.desafio1.controllers;

import br.com.desafiocielo.desafio1.domain.models.NaturalPerson;
import br.com.desafiocielo.desafio1.domain.models.dtos.NaturalPersonDto;
import br.com.desafiocielo.desafio1.services.NaturalPersonService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class NaturalPersonControllerTest {

    @LocalServerPort
    private int port;

    @Test
    @DisplayName("deveRetornarSucesso_QuandoSalvarUmCliente")
    @Order(1)
    void saveNaturalPersonSucess() throws Exception {
    }

    @Test
    @DisplayName("deveRetornarSucesso_QuandoBuscarUmCliente")
    @Order(3)
    void getNaturalPersonByIdSucess() throws Exception {
        RestAssured.given()
                    .basePath("cliente/pessoa-fisica/780d47d2-46f3-414c-8efa-d46689d100ed")
                    .accept(ContentType.JSON)
                    .port(port)
                .when()
                    .get()
                .then()
                .statusCode(HttpStatus.OK.value());


    }

    @Test
    @DisplayName("deveRetornarSucesso_QuandoAtualizarUmCliente")
    @Order(5)
    void updateNaturalPersonSucess() throws Exception {

//        NaturalPersonDto NaturalPersonDto = new NaturalPersonDto("02165896547",1547L,"Thiago","2irmaos@mail.com","03258793000197","Comercial 2 Irmãos");
//
//        doNothing().when(service).updateNaturalPerson(UUID.randomUUID(), NaturalPersonDto);
//        mockMvc.perform(MockMvcRequestBuilders.put("/cliente/pessoa-juridica{id}", UUID.randomUUID())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("deveRetornarSucesso_QuandoDeletarUmCliente")
    @Order(7)
    void deleteNaturalPersonSucess() throws Exception {
//        doNothing().when(service).deletarCliente(UUID.randomUUID());
//        mockMvc.perform(MockMvcRequestBuilders.delete("/cliente/pessoa-juridica/{id}", UUID.randomUUID())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
    }
}