/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.openweb.quarkus

import javax.inject.Inject
import javax.transaction.Transactional
import javax.ws.rs.Path

@Path("/movies")
class MovieResource(
        @Inject
        private val movieRepository: MovieRepository
) : Resource<Movie>(movieRepository) {

    @Transactional
    override fun create(restEntity: Movie) = super.create(restEntity)

    @Transactional
    override fun update(id: Long, restEntity: Movie): Movie {
        return getById(id).apply {
            title = restEntity.title
            year = restEntity.year
            movieRepository.persist(this)
        }
    }

    @Transactional
    override fun delete(id: Long) = super.delete(id)
}
