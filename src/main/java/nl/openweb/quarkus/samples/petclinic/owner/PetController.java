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
package nl.openweb.quarkus.samples.petclinic.owner;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/pets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PetController {

    @GET
    public List<Pet> getPets() {
        return Pet.listAll();
    }

    @GET
    @Path("{id}")
    public Pet getPetById(@PathParam("id") long id) {
        return Pet.findById(id);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Pet updatePet(@PathParam("id") long id, Pet pet) {
        Pet entity = Pet.findById(id);
        entity.setName(pet.getName());
        entity.setBirthDate(pet.getBirthDate());
        entity.setType(pet.getType());
        return entity;
    }
}
