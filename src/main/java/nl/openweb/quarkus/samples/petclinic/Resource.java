package nl.openweb.quarkus.samples.petclinic;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface Resource<T extends PanacheEntityBase> {

    @GET
    List<T> getAll();

    @GET
    @Path("{id}")
    T getById(@PathParam("id") long id);

    @POST
    T create(T restEntity);

    @PUT
    @Path("{id}")
    T update(@PathParam("id") long id, T restEntity);

    @PUT
    @Path("{id}")
    void delete(@PathParam("id") long id);
}
