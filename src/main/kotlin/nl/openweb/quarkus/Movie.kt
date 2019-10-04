package nl.openweb.quarkus

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotEmpty

@Entity
@Table(name = "movies")
class Movie (

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_seq")
    var id: Long? = null,

    @Column(name = "title")
    @NotEmpty
    var title: String,

    @Column(name = "year")
    @NotEmpty
    var year: String
)
