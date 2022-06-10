package com.sztorma.skeleton.common.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.sztorma.skeleton.common.model.Identity;

public class EntityDaoImpl<Entity extends Identity, Repository extends CrudRepository<Entity, Long>>
        implements EntityDao<Entity> {

    @Autowired
    protected Repository repository;

    @Override
    public Entity getById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Entity save(Entity entity) {
        return repository.save(entity);
    }

}
