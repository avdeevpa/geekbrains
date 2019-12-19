package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.dtos.UserDTO;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("/users")
public interface UserClient extends RestService {
    @GET
    @Path("/")
    void getAllUsers(@HeaderParam("Authorization") String token, MethodCallback<List<UserDTO>> items);

    @GET
    @Path("executors")
    void getExecutors(@HeaderParam("Authorization") String token, MethodCallback<List<UserDTO>> items);

    @GET
    @Path("initiators")
    void getInitiators(@HeaderParam("Authorization") String token, MethodCallback<List<UserDTO>> items);

    @GET
    @Path("id={id}")
    void getUser(@HeaderParam("Authorization") String token, @PathParam("id") Long id, MethodCallback<UserDTO> item);
}
