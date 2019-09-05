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

import nl.openweb.quarkus.samples.petclinic.Resource;

import javax.transaction.Transactional;
import javax.ws.rs.Path;
import java.util.List;

@Path("/specialties")
public class SpecialtyResource implements Resource<Specialty> {

    @Override
    public List<Specialty> getAll() {
        return Specialty.listAll();
    }

    @Override
    public Specialty getById(long id) {
        return Specialty.findById(id);
    }

    @Transactional
    @Override
    public Specialty create(Specialty restEntity) {
        restEntity.persist();
        return restEntity;
    }

    @Transactional
    @Override
    public Specialty update(long id, Specialty specialty) {
        Specialty entity = Specialty.findById(id);
        entity.setName(specialty.getName());
        return entity;
    }

    @Transactional
    @Override
    public void delete(long id) {
        Specialty.findById(id).delete();
    }
}
