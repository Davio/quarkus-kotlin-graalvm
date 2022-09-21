package nl.openweb.quarkus

import javax.enterprise.context.ApplicationScoped
import javax.validation.Valid
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/movies")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class MovieResource(
    private val movieService: MovieService
) {

    @GET
    fun getMovies() = movieService.getMovies()

    @GET
    @Path("/{id}")
    fun getMovie(id: Long) = movieService.getMovie(id)

    @POST
    fun createMovie(@Valid movieDto: MovieDTO) = movieService.createMovie(movieDto)
}
