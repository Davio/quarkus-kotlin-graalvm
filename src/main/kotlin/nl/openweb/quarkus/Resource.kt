package nl.openweb.quarkus

import io.quarkus.hibernate.orm.panache.PanacheRepository
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
abstract class Resource<T>(
   private val repository: PanacheRepository<T>
) {
    @GET
    @Path("")
    fun getAll(): List<T> = repository.listAll()

    @GET
    @Path("{id}")
    fun getById(@PathParam("id") id: Long): T = repository.findById(id)

    @POST
    @Path("")
    open fun create(restEntity: T) = restEntity.apply { repository.persist(this) }

    @PUT
    @Path("{id}")
    abstract fun update(@PathParam("id") id: Long, restEntity: T): T

    @DELETE
    @Path("{id}")
    open fun delete(@PathParam("id") id: Long) = repository.delete(getById(id))
}
