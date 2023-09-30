package br.com.desafiocielo.desafio1.controllers;

import br.com.desafiocielo.desafio1.domain.models.JuridicalPerson;
import br.com.desafiocielo.desafio1.domain.models.dtos.JuridicalPersonDto;
import br.com.desafiocielo.desafio1.services.JuridicalPersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JuridicalPersonController.class)
class JuridicalPersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    JuridicalPersonController controller;

    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    JuridicalPersonService service;

    JuridicalPersonDto juridicalPersonDto = new JuridicalPersonDto();
    JuridicalPerson juridicalPerson = new JuridicalPerson();

    @BeforeEach
    void setup() {
        juridicalPersonDto = new JuridicalPersonDto("02165896547",1547L,"Thiago","2irmaos@mail.com","03258793000197","Comercial 2 Irmãos");
        juridicalPerson = new JuridicalPerson(UUID.randomUUID(), juridicalPersonDto.getMerchantCategoryCode(),juridicalPersonDto.getName(),juridicalPersonDto.getCpf(),juridicalPersonDto.getEmail(),juridicalPersonDto.getCnpj(),"Comercial 2 Irmãos");
    }

    @Test
    @DisplayName("deveRetornarSucesso_QuandoSalvarUmCliente")
    @Order(1)
    void saveJuridicalPersonSucess() throws Exception {
        when(service.createUser(any(JuridicalPersonDto.class))).thenReturn(juridicalPerson);

        mockMvc.perform(post("/cliente/pessoa-fisica")
                        .content(mapper.writeValueAsString(juridicalPerson))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(juridicalPersonDto.getName()));
    }

    @Test
    @DisplayName("deveRetornarSucesso_QuandoBuscarUmCliente")
    @Order(2)
    void getJuridicalPersonByIdSucess() throws Exception {

        when(service.getJuridicalPersonById(UUID.randomUUID())).thenReturn(juridicalPerson);

        mockMvc.perform(MockMvcRequestBuilders.get("/cliente/pessoa-juridica/{id}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("deveRetornarSucesso_QuandoAtualizarUmCliente")
    @Order(3)
    void updateJuridicalPersonSucess() throws Exception {

        JuridicalPersonDto juridicalPersonDto = new JuridicalPersonDto("02165896547",1547L,"Thiago","2irmaos@mail.com","03258793000197","Comercial 2 Irmãos");

        doNothing().when(service).updateJuridicalPerson(UUID.randomUUID(), juridicalPersonDto);
        mockMvc.perform(MockMvcRequestBuilders.put("/cliente/pessoa-juridica{id}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("deveRetornarSucesso_QuandoDeletarUmCliente")
    @Order(4)
    void deleteJuridicalPersonSucess() throws Exception {
        doNothing().when(service).deletarCliente(UUID.randomUUID());
        mockMvc.perform(MockMvcRequestBuilders.delete("/cliente/pessoa-juridica/{id}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}