package com.example.exam201930421.service;

import com.example.exam201930421.dto.SignInResultDto;
import com.example.exam201930421.dto.SignUpResultDto;

public interface SignService {

    SignUpResultDto signUp(String id, String password, String name, String email, String role);

    SignInResultDto signIn(String id, String password) throws RuntimeException;

}
