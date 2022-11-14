package at.htl.boundary;

import at.htl.control.CustomerRepository;
import at.htl.entity.Customer;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

@Path("/person")
public class CustomerResource {

    @Inject
    Logger logger;

    @Inject
    CustomerRepository customerRepository;

    private List<Customer> persons = new LinkedList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer findById(
            @PathParam("id") long id
    ) {
        logger.info(id);
        return customerRepository.findById(id);
    }

    @GET
    @Path("firstlast")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> findById(
            @QueryParam("first") String firstName,
            @QueryParam("last") String lastName
    ) {
        logger.info(lastName + " " + firstName);
        return customerRepository.findByFirstnameAndLastname(
                firstName,
                lastName
        );
    }



    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Customer person, @Context UriInfo uriInfo) throws Exception {
        Customer saved = customerRepository.save(person);
        logger.info(person.getLastName() + " wird gespeichert");
        URI location = uriInfo
                .getAbsolutePathBuilder()
                .path(saved.getId().toString())
                .build();
        return Response.created(location).build();
    }

    @PATCH
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(String firstName) {

        logger.info(persons);
        logger.info(firstName);
        Customer foundPerson = persons
                .stream()
                .filter(fn -> fn.getFirstName().equals(firstName))
                .findFirst()
                .get();
        logger.info(foundPerson.getFirstName());
        foundPerson.setFirstName("updated");
        return Response.ok(foundPerson).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(Customer person) {
        if (persons.size() > 0) {
            persons.remove(0);
        }
        return Response.noContent().build();
    }
}

