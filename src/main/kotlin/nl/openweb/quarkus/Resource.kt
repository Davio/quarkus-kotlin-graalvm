package nl.openweb.quarkus

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
interface Resource<T> {

    @GET
    @Path("")
    fun getAll(): List<T>

    @GET
    @Path("{id}")
    fun getById(@PathParam("id") id: Long): T

    @POST
    @Path("")
    fun create(restEntity: T): T

    @PUT
    @Path("{id}")
    fun update(@PathParam("id") id: Long, restEntity: T): T

    @DELETE
    @Path("{id}")
    fun delete(@PathParam("id") id: Long)
}
