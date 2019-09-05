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

@Path("/owners")
public class OwnerResource implements Resource<Owner> {

    @Override
    public List<Owner> getAll() {
        return Owner.listAll();
    }

    @Override
    public Owner getById(long id) {
        return Owner.findById(id);
    }

    @Transactional
    @Override
    public Owner create(Owner restEntity) {
        restEntity.persist();
        return restEntity;
    }

    @Transactional
    @Override
    public Owner update(long id, Owner owner) {
        Owner entity = Owner.findById(id);
        entity.setFirstName(owner.getFirstName());
        entity.setLastName(owner.getLastName());
        entity.setAddress(owner.getAddress());
        entity.setCity(owner.getCity());
        entity.setTelephone(owner.getTelephone());
        entity.setPets(owner.getPets());
        return entity;
    }

    @Transactional
    @Override
    public void delete(long id) {
        Owner.findById(id).delete();
    }
}
