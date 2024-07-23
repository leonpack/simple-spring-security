package dev.nathan.ss2024.controller;

import dev.nathan.ss2024.dto.BaseAuthRequest;
import dev.nathan.ss2024.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<BaseAuthRequest> signUp(@RequestBody BaseAuthRequest registerRequestDTO) {
        return ResponseEntity.ok(authService.signUp(registerRequestDTO));
    }

    @PostMapping("/signin")
    public ResponseEntity<BaseAuthRequest> signIn(@RequestBody BaseAuthRequest registerRequestDTO) {
        return ResponseEntity.ok(authService.signIn(registerRequestDTO));
    }

    @PostMapping("/refresh")
    public ResponseEntity<BaseAuthRequest> refreshToken(@RequestBody BaseAuthRequest registerRequestDTO) {
        return ResponseEntity.ok(authService.refreshToken(registerRequestDTO));
    }

}
