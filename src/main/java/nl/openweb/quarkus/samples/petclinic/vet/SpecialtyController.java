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
package nl.openweb.quarkus.samples.petclinic.vet;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/specialties")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SpecialtyController {

    @GET
    public List<Specialty> getSpecialties() {
        return Specialty.listAll();
    }

    @GET
    @Path("{id}")
    public Specialty getSpecialtyById(@PathParam("id") long id) {
        return Specialty.findById(id);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Specialty updateSpecialty(@PathParam("id") long id, Specialty specialty) {
        Specialty entity = Specialty.findById(id);
        entity.setName(specialty.getName());
        return entity;
    }
}
