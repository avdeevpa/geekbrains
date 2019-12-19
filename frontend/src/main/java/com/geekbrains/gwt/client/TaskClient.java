package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.dtos.TaskDTO;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;
import java.util.List;

@Path("/")
public interface TaskClient extends RestService {
    @GET
    @Path("tasks")
    void getAllTasks(@HeaderParam("Authorization") String token, MethodCallback<List<TaskDTO>> items);

    @GET
    @Path("tasks?id={id}&caption={caption}&owner={owner}&assigned={assigned}&status={status}&descriprion={descriprion}")
    void getTasks(
            @HeaderParam("Authorization") String token,
            @PathParam("id") String id,
            @PathParam("caption") String caption,
            @PathParam("owner") String owner,
            @PathParam("assigned") String assigned,
            @PathParam("status") String status,
            @PathParam("descriprion") String descriprion,
            MethodCallback<List<TaskDTO>> items
    );

    @DELETE
    @Path("tasks?id={id}")
    void removeTask(@HeaderParam("Authorization") String token, @PathParam("id") String id, MethodCallback<Void> result);
}
