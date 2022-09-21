package nl.openweb.quarkus

import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class MovieDTO(
    @field:NotBlank val title: String,
    @field:Min(1800) @field:Max(2200) val year: Int
)
