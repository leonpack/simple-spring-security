package dev.nathan.ss2024.service;

import dev.nathan.ss2024.dto.BaseAuthRequest;
import dev.nathan.ss2024.entity.User;
import dev.nathan.ss2024.repository.UserRepo;
import dev.nathan.ss2024.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepo ourUserRepo;
    private final JWTUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public BaseAuthRequest signUp(BaseAuthRequest registerRequestDTO) {
        BaseAuthRequest resp = new BaseAuthRequest();
        try {
            User ourUsers = new User();
            ourUsers.setEmail(registerRequestDTO.getEmail());
            ourUsers.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
            ourUsers.setRole(registerRequestDTO.getRole());

            User ourUsersResult = ourUserRepo.save(ourUsers);
            if(ourUsersResult != null && ourUsersResult.getId()>0) {
                resp.setOurUsers(ourUsersResult);
                resp.setMessage("User save successfully");
                resp.setStatusCode(200);
            }
        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    @Override
    public BaseAuthRequest signIn(BaseAuthRequest registerRequestDTO) {
        BaseAuthRequest response = new BaseAuthRequest();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(registerRequestDTO.getEmail(), registerRequestDTO.getPassword()));
            var user = ourUserRepo.findByEmail(registerRequestDTO.getEmail()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hour");
            response.setMessage("Successfully Sign In");
        } catch (Exception ex) {
            response.setStatusCode(500);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    @Override
    public BaseAuthRequest refreshToken(BaseAuthRequest registerRequestDTO) {
        BaseAuthRequest response = new BaseAuthRequest();
        String ourEmail = jwtUtils.extractUsername(registerRequestDTO.getToken());
        User users = ourUserRepo.findByEmail(ourEmail).orElseThrow();
        if(jwtUtils.isTokenValid(registerRequestDTO.getToken(), users)) {
            var jwt = jwtUtils.generateToken(users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(registerRequestDTO.getToken());
            response.setExpirationTime("24h");
            response.setMessage("Successfully Refresh Token");
        } else {
            response.setStatusCode(500);
        }
        return response;
    }


}
