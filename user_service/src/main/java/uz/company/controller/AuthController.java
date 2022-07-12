package uz.company.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.company.config.AuthService;
import uz.company.dto.user.UserLoginDto;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(path ="/login")
    public ResponseEntity<?>login(@Valid @RequestBody UserLoginDto loginDto){
        return authService.loginUsrNameAndPassword(loginDto);
    }
}
