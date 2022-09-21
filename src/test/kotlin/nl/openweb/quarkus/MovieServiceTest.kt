package nl.openweb.quarkus

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.matchers.collections.shouldBeSingleton
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.just
import org.junit.jupiter.api.Test

class MovieServiceTest {

    @MockK
    private lateinit var movieRepository: MovieRepository

    @InjectMockKs
    private lateinit var movieService: MovieService

    private val movie = MovieEntity().apply {
        title = "Jurassic Park"
        year = 1995
    }

    @Test
    fun `should get movies`() {
        every { movieRepository.listAll() } returns listOf(movie)
        val movies = movieService.getMovies()
        movies.shouldBeSingleton {
            it.title shouldBe "Jurassic Park"
            it.year shouldBe 1995
        }
    }

    @Test
    fun `should get single movie by ID`() {
        every { movieRepository.findById(any()) } returns movie
        val movie = movieService.getMovie(1L)
        movie.shouldNotBeNull()
        assertSoftly(movie) {
            title shouldBe "Jurassic Park"
            year shouldBe 1995
        }
    }

    @Test
    fun `should create new movie`() {
        every { movieRepository.persist(any<MovieEntity>()) } just Runs
        shouldNotThrowAny {
            movieService.createMovie(MovieDTO("The Karate Kid", 1993))
        }
    }
}
