package com.retail.platform.datametrics.service;

import com.retail.platform.datametrics.model.User;
import com.retail.platform.datametrics.model.UserTenant;
import com.retail.platform.datametrics.repository.UserRepository;
import com.retail.platform.datametrics.repository.UserTenantRepository;
import com.retail.platform.datametrics.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserTenantRepository userTenantRepository;
    private final JwtUtil jwtUtil;

    public String authenticateAndGetToken(String username, String plainPassword) {
        return userRepository.findById(username)
                .map(user -> {
                    if (BCrypt.checkpw(plainPassword, user.getPasswordHash())) {
                        return jwtUtil.generateToken(username);
                    } else {
                        throw new RuntimeException("Invalid credentials");
                    }
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public boolean userHasAccessToTenant(String username, String tenantId) {
        List<UserTenant> uts = userTenantRepository.findByUsername(username);
        return uts.stream().anyMatch(ut -> ut.getTenantId().equals(tenantId));
    }
}
