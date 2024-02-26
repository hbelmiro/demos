package com.hbelmiro.demos;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    private final MyAiService myAiService;

    @Inject
    public GreetingResource(MyAiService myAiService) {
        this.myAiService = myAiService;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return myAiService.greet();
    }
}
