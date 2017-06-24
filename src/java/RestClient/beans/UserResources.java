/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestClient.beans;

import RestClient.Ejb.UserRepository;
import RestClient.Entity.User;
import RestClient.Exception.ErrorMessage;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author wilzone
 */
@Path("user")
public class UserResources {

    @EJB
    private UserRepository userRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response SignIn(@Valid final User user) {
        user.setSignInTime(new Date());
        User savedUser = userRepository.signIn(user);
        return Response.ok().entity(savedUser).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response SignOut(User user) {
        System.out.println(user.toString());
        User savedUser = userRepository.signOut(user.getId(), new Date());
        if(savedUser != null)
            return Response.ok().entity(savedUser).build();
        else
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorMessage("No user found with id : " + user.getId())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetSignedInUsers() {
        GenericEntity<List<User>> userWrapper = new GenericEntity<List<User>>(userRepository.getUsers()) {
        };
        return Response.ok(userWrapper).build();

    }
}
