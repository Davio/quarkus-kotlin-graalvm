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
package nl.openweb.quarkus.samples.petclinic.visit;

import nl.openweb.quarkus.samples.petclinic.Resource;

import javax.transaction.Transactional;
import javax.ws.rs.Path;
import java.util.List;

@Path("/visits")
public class VisitResource implements Resource<Visit>  {

    @Override
    public List<Visit> getAll() {
        return Visit.listAll();
    }

    @Override
    public Visit getById(long id) {
        return Visit.findById(id);
    }

    @Transactional
    @Override
    public Visit create(Visit restEntity) {
        restEntity.persist();
        return restEntity;
    }

    @Transactional
    @Override
    public Visit update(long id, Visit restEntity) {
        Visit entity = Visit.findById(id);
        entity.setDescription(restEntity.getDescription());
        entity.setDate(restEntity.getDate());
        entity.setPet(restEntity.getPet());
        return entity;
    }

    @Transactional
    @Override
    public void delete(long id) {
        Visit.findById(id).delete();
    }
}
