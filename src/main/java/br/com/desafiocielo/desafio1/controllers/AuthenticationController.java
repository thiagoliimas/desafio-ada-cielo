package br.com.desafiocielo.desafio1.controllers;

import br.com.desafiocielo.desafio1.domain.models.authentication.UserAuthentication;
import br.com.desafiocielo.desafio1.domain.models.dtos.security.AuthenticationDTO;
import br.com.desafiocielo.desafio1.domain.models.dtos.security.LoginResponseDTO;
import br.com.desafiocielo.desafio1.domain.models.dtos.security.RegisterDTO;
import br.com.desafiocielo.desafio1.infra.security.TokenService;
import br.com.desafiocielo.desafio1.repositories.AuthenticationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    private final AuthenticationRepository authenticationRepository;

    @Autowired
    public AuthenticationController(TokenService tokenService, AuthenticationManager authenticationManager, AuthenticationRepository authenticationRepository) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.authenticationRepository = authenticationRepository;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO){
        var userNamePassword = new UsernamePasswordAuthenticationToken(
                authenticationDTO.logim(), authenticationDTO.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);

        var token = tokenService.generateToken((UserAuthentication) auth.getPrincipal());
        return ResponseEntity.ok().body(new LoginResponseDTO(token));
    }

    @PostMapping("register")
    public ResponseEntity response(@RequestBody @Valid RegisterDTO registerDTO){
        if(this.authenticationRepository.findByLogin(registerDTO.login()) != null){
            return ResponseEntity.badRequest().build();
        } else {
            String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
            UserAuthentication user = new UserAuthentication(registerDTO.login(), encryptedPassword, registerDTO.role());

            this.authenticationRepository.save(user);
            return ResponseEntity.ok().build();
        }
    }
}
