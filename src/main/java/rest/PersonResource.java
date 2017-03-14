/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Person;
import exception.PersonNotFoundException;
import facade.FacadeCurrent;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author christian
 */
@Path("person")
public class PersonResource {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public PersonResource() {
    }
    /**
     * Retrieves representation of an instance of rest.PersonResource
     *
     * @return an instance of java.lang.String
     */
    FacadeCurrent f = new FacadeCurrent(Persistence.createEntityManagerFactory("pu"));

    @GET
    public String Hello() {
        return "Hello from Person Resource";
    }

    @GET
    @Path("complete")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getAllPersons() {
        throw new UnsupportedOperationException();
    }

    @GET
    @Path("complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonByID(@PathParam("id") long id) throws PersonNotFoundException {
        Person p = f.getPerson(id);
        if (p == null) {
            throw new PersonNotFoundException("No Person found");
        }
        return gson.toJson(p);
    }

    @GET
    @Path("contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getContact() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deletePerson(@PathParam("id") long id) throws PersonNotFoundException {
        Person p = f.getPerson(id);
        if (p != null) {
            f.deletePerson(p.getId());
        } else {
            throw new PersonNotFoundException("Found no person to delete. The person may not excist.");
        }
    }

    /**
     * PUT method for updating or creating an instance of CompanyResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updatePersonByID(String content) {
    }

}
