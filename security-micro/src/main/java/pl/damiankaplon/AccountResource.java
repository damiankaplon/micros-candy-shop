package pl.damiankaplon;

import lombok.RequiredArgsConstructor;
import pl.damiankaplon.service.SecurityService;

import javax.security.auth.login.LoginException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static pl.damiankaplon.service.SecurityService.Credentials;
import static pl.damiankaplon.service.SecurityService.BearerToken;

@Path("/api/v1/security/account")
@RequiredArgsConstructor
public class AccountResource {

    private final SecurityService securityService;

    @POST
    @Path("token")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnBearerToken(Credentials credentials) throws LoginException {
        BearerToken token = securityService.getBearerToken(credentials);
        return Response.ok().entity(token).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(SecurityService.Registration dto) throws LoginException {

        return Response.ok()
                .entity("{ id: " + securityService.register(dto).toString() + " }")
                .build();
    }

    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByAccountById(@PathParam("id") String id) {
        return Response.ok()
                .entity(securityService.getAccountById(id))
                .build();
    }
}