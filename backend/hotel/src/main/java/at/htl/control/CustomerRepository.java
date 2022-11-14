package at.htl.control;

import at.htl.entity.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class CustomerRepository {

    @Inject
    EntityManager em;

    public Customer save(Customer person) {
        return em.merge(person);
    }

    public List<Customer> findAll() {
        TypedQuery<Customer> query = em
                .createNamedQuery("Person.findAll", Customer.class);
        return query.getResultList();
    }

    public Customer findById(long id) {
        return em.find(Customer.class, id);
    }

    public List<Customer> findByFirstnameAndLastname(
            String firstName,
            String lastName
    ) {
        TypedQuery<Customer> query = em
                .createNamedQuery("Person.findByFirstNameAndLastName", Customer.class)
                .setParameter("FIRST", firstName)
                .setParameter("LAST", lastName);
        return query.getResultList();
    }

}
