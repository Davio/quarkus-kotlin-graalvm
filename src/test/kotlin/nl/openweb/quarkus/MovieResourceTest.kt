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

class MovieResourceTest {

    @MockK
    private lateinit var movieService: MovieService

    @InjectMockKs
    private lateinit var movieResource: MovieResource

    private val movie = MovieDTO(title = "Jurassic Park", year = 1995)

    @Test
    fun `should get movies`() {
        every { movieService.getMovies() } returns listOf(movie)
        val movies = movieResource.getMovies()
        movies.shouldBeSingleton {
            it.title shouldBe "Jurassic Park"
            it.year shouldBe 1995
        }
    }

    @Test
    fun `should get single movie by ID`() {
        every { movieService.getMovie(any()) } returns movie
        val movie = movieResource.getMovie(1L)
        movie.shouldNotBeNull()
        assertSoftly(movie) {
            title shouldBe "Jurassic Park"
            year shouldBe 1995
        }
    }

    @Test
    fun `should create new movie`() {
        every { movieService.createMovie(any()) } just Runs
        shouldNotThrowAny {
            movieResource.createMovie(movie)
        }
    }
}
