package uz.company.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.company.service.AuthService;
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
    @GetMapping(path ="/refresh-token")
    public ResponseEntity<?>login(@Valid @RequestParam(value = "access_token") String access_token){
        return authService.generateTokenWithAccessToken(access_token);
    }

}
