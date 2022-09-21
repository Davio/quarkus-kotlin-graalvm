package nl.openweb.quarkus

import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class MovieService(
    private val movieRepository: MovieRepository
) {

    fun getMovies() = movieRepository.listAll().map { it.toDto() }

    fun getMovie(id: Long) = movieRepository.findById(id)?.toDto()

    @Transactional
    fun createMovie(movieDto: MovieDTO) = movieRepository.persist(movieDto.toEntity())

    fun MovieEntity.toDto() = MovieDTO(title = title, year = year)
    fun MovieDTO.toEntity() = MovieEntity().also {
        it.title = this.title
        it.year = this.year
    }
}
