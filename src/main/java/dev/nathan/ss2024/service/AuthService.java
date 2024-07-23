package dev.nathan.ss2024.service;

import dev.nathan.ss2024.dto.BaseAuthRequest;

public interface AuthService {
    //because this is mostly focused on security part, any other part will be as simple as possible for the simplicity purpose
    BaseAuthRequest signUp(BaseAuthRequest authRequest);
    BaseAuthRequest signIn(BaseAuthRequest authRequest);
    BaseAuthRequest refreshToken(BaseAuthRequest authRequest);
}
