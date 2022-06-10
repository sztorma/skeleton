package com.sztorma.skeleton.common.dao;

import com.sztorma.skeleton.common.model.Identity;

public interface EntityDao<Entity extends Identity> {

    Entity getById(Long id);

    void deleteById(Long id);

    Entity save(Entity entity);
}
