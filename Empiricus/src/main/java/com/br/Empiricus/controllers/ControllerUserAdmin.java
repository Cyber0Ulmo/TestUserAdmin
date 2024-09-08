package com.br.Empiricus.controllers;

import com.br.Empiricus.domain.DTO.UserDTO;
import com.br.Empiricus.domain.DTO.UserResponseDTO;
import com.br.Empiricus.domain.exceptions.UserNotFoundException;
import com.br.Empiricus.domain.user.User;
import com.br.Empiricus.services.interfaces.ServiceUser;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class ControllerUserAdmin {

    @Autowired
    private ServiceUser userService;

    @PostMapping("/register")
    public ResponseEntity resgister(@RequestBody @Valid UserDTO userData){
        if(userService.loadUserByUsername(userData.cpf()) != null) return ResponseEntity.badRequest().build();
        userService.registerUser(userData);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getbycpf")
    public ResponseEntity getUser(@RequestParam @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve estar no formato XXX.XXX.XXX-XX") String cpf) {
        try {
            return ResponseEntity.ok(userService.getUserByCpf(cpf));
        } catch (UserNotFoundException exception){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getall")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/update")
    public ResponseEntity updateUser(@RequestBody @Valid UserDTO userDTO){
        try {
            userService.updateUser(userDTO);
            return ResponseEntity.ok().build();
        }
        catch (UserNotFoundException exception){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestParam @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve estar no formato XXX.XXX.XXX-XX") String cpf){
        try {
            userService.deleteUser(cpf);
            return ResponseEntity.ok().build();
        }catch (UserNotFoundException exception){
            return ResponseEntity.notFound().build();
        }
    }
}
