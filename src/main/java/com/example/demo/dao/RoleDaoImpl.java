package com.example.demo.dao;

import com.example.demo.models.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Role> getAll() {
        return entityManager.createQuery("select u from Role u", Role.class).getResultList();
    }

    @Override
    public void add(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void delete(Role role) {
        entityManager.remove(roleById(role.getId()));
    }

    @Override
    public void edit(Role role) {
        entityManager.merge(role);
    }

    @Override
    public Role roleById(Long id) {
        return entityManager
                .createQuery("select r from Role r where r.id = :id", Role.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Role roleByName(String name) {
        return entityManager
                .createQuery("select r from Role r where r.name = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public Set<Role> setOfRole(String[] role) {
        Set<Role> set = new HashSet<>();
        for (String str : role) {
            set.add(roleByName(str));
        }
        return set;
    }


}
