package org.example.rest;

import io.swagger.annotations.Api;
import org.example.Dto.*;

import org.example.cassiomolin.user.api.model.QueryUserResult;
import org.example.cassiomolin.user.api.resource.UserResource;
import org.example.cassiomolin.user.domain.User;
import org.example.cassiomolin.user.service.UserService;
import org.example.model.Loanedbook;
import org.example.model.Users;
import org.example.service.AdminService;
import org.example.service.UserPasswordService;
import sun.jvm.hotspot.runtime.linux_sparc.LinuxSPARCJavaThreadPDAccess;


import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.GeneratedValue;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.stream.Collectors;

@Path("/admin")
@Api(value = "Library System")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminEndpoint {
    @EJB
    AdminService adminService;
    @Inject
    UserResource userResource;
    @Context
    private SecurityContext securityContext;

    @Inject
    private UserService userService;
    @EJB
    UserPasswordService userPasswordService;
    @Path("/viewLoan")
    @GET
    //@RolesAllowed({"ADMIN"})
    public List<LoanDto> ViewLoandata(@QueryParam("start") Integer start, @QueryParam("max") Integer max){
        return adminService.ViewLoanData(start, max);
    }
    @Path("/dailyBorowedList")
    @GET
   // @RolesAllowed({"ADMIN"})
    public List<UserLoanedDto> getDailyBorrowed(@QueryParam("start") Integer start, @QueryParam("max") Integer max){
        return adminService.AllBoarowedList(start, max);
    }
    @Path("/Signup")
    @POST
    public RegistrationResponseDto createAccount(RegistrationRequestDto registerUserIn){
        return userPasswordService.addUser(registerUserIn);
    }

    @POST
    @Path("/confirmation")
    //@RolesAllowed({"ADMIN"})
    public Loanedbook confirm(ConfirmationDto confirmationDto){
        return adminService.Confirmation(confirmationDto);
    }

    @GET
    @Path("/AllUser")
    @Produces(MediaType.APPLICATION_JSON)
   /// @RolesAllowed({"ADMIN"})
 public Response getUsers() {
        return this.userResource.getUsers();
    }
    @POST
    @Path("/registeringBook")
    public RegistrationResponseDto BookRegistering(BookRegistrationDto bookRegistrationDto){
        return this.adminService.RegisteringBook(bookRegistrationDto);
}

}

