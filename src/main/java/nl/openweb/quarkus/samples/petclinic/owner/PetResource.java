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

import nl.openweb.quarkus.samples.petclinic.Resource;

import javax.transaction.Transactional;
import javax.ws.rs.Path;
import java.util.List;

@Path("/pets")
public class PetResource implements Resource<Pet>  {

    @Override
    public List<Pet> getAll() {
        return Pet.listAll();
    }

    @Override
    public Pet getById(long id) {
        return Pet.findById(id);
    }

    @Transactional
    @Override
    public Pet create(Pet restEntity) {
        restEntity.persist();
        return restEntity;
    }

    @Transactional
    @Override
    public Pet update(long id, Pet pet) {
        Pet entity = Pet.findById(id);
        entity.setName(pet.getName());
        entity.setBirthDate(pet.getBirthDate());
        entity.setType(pet.getType());
        return entity;
    }

    @Transactional
    @Override
    public void delete(long id) {
        Pet.findById(id).delete();
    }
}
