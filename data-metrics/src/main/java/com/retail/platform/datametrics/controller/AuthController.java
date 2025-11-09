package com.retail.platform.datametrics.controller;

import com.retail.platform.datametrics.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String username,
                                                     @RequestParam String password) {
        String token = authService.authenticateAndGetToken(username, password);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
