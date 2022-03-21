package com.project.currencytest.controller;

import com.project.currencytest.model.request.AuthRequest;
import com.project.currencytest.model.response.AuthResponse;
import com.project.currencytest.util.JwtUtil;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;

@Slf4j
@RequestMapping("/v1/user")
@Controller
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Single<AuthResponse> getAuthToken(@RequestBody AuthRequest request) {
        log.info("*** Start PersonController getAuthToken ***");
        return Single.just(authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())))
                .flatMap(a -> jwtUtil.generateToken(request.getUserName())
                        .map(token -> buildAuthResponse.apply(token)))
                .doOnError(throwable -> log.error("Error Service getAuthToken"));
    }

    Function<String, AuthResponse> buildAuthResponse = token -> new AuthResponse(token, true);
}