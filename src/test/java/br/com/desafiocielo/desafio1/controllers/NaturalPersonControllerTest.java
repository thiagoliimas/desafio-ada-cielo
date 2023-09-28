package br.com.desafiocielo.desafio1.controllers;

import br.com.desafiocielo.desafio1.domain.models.NaturalPerson;
import br.com.desafiocielo.desafio1.domain.models.dtos.NaturalPersonDto;
import br.com.desafiocielo.desafio1.services.NaturalPersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NaturalPersonController.class)
class NaturalPersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    NaturalPersonController controller;

    @MockBean
    NaturalPersonService service;

    NaturalPersonDto naturalPersonDto = new NaturalPersonDto();
    NaturalPerson naturalPerson = new NaturalPerson();

    @BeforeEach
    void setup() {
        naturalPersonDto = new NaturalPersonDto("02165896547",1547L,"Thiago","2irmaos@mail.com");
        naturalPerson = new NaturalPerson(UUID.randomUUID(), naturalPersonDto.getMerchantCategoryCode(),naturalPersonDto.getName(),naturalPersonDto.getCpf(),naturalPersonDto.getEmail());
    }

    @Test
    @DisplayName("deveRetornarSucesso_QuandoSalvarUmCliente")
    @Order(1)
    void saveNaturalPersonSucess() throws Exception {

    }

    @Test
    @DisplayName("deveRetornarSucesso_QuandoBuscarUmCliente")
    @Order(3)
    void getNaturalPersonByIdSucess() throws Exception {

        when(service.getNaturalPersonById(UUID.randomUUID())).thenReturn(naturalPerson);

        mockMvc.perform(MockMvcRequestBuilders.get("/cliente/pessoa-juridica/{id}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

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
        doNothing().when(service).deletarCliente(UUID.randomUUID());
        mockMvc.perform(MockMvcRequestBuilders.delete("/cliente/pessoa-juridica/{id}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}