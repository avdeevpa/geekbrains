package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.dtos.JwtAuthRequestDto;
import com.geekbrains.gwt.common.dtos.JwtAuthResponseDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;

public interface AuthClient extends RestService {
    @POST
    @Path("http://localhost:8189/gwt-rest/authenticate")
    void authenticate(@BeanParam() JwtAuthRequestDto authRequest, MethodCallback<JwtAuthResponseDto> result);

    @POST
    @Path("http://localhost:8189/gwt-rest/authenticate")
    void authenticate(@FormParam("username") String username, @FormParam("password") String password, MethodCallback<String> result);

    @POST
    @Path("http://localhost:8189/gwt-rest/logout")
    void logout(MethodCallback<JwtAuthResponseDto> result);
}