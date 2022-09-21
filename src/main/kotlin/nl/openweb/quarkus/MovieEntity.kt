package nl.openweb.quarkus

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

@Entity
@Table(name = "movies")
class MovieEntity {

    @Id
    @GeneratedValue
    var id: Long? = null;

    @Column(name = "title")
    @NotEmpty
    lateinit var title: String

    @Column(name = "year")
    @Min(1800)
    @Max(2200)
    var year: Int = 1800
}
