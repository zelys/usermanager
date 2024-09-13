package com.local.um.persistence;

import com.local.um.logic.Role;
import com.local.um.logic.User;
import com.local.um.persistence.exceptions.NonexistentEntityException;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersistenceController {

    private final String PU = "usermanagerPU";
    UserJpaController userJpa;
    RoleJpaController roleJpa;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);

    public PersistenceController() {
        userJpa = new UserJpaController(emf);
        roleJpa = new RoleJpaController(emf);
    }

    public boolean validateLogin(String userName, String password) {
        return findPassword(password) && findUserName(userName);
    }

    public boolean findUserName(String userName) {
        return userJpa.findUserEntities().stream()
                .anyMatch(u -> u.getUserName().equals(userName));
    }

    public boolean findPassword(String password) {
        return userJpa.findUserEntities().stream()
                .anyMatch(u -> u.getPassword().equals(password));
    }

    public List<User> getAll() {
        return userJpa.findUserEntities();
    }

    public User getUser(String userName, String password) {
        return getAll().stream()
                .filter(u -> u.getUserName().equals(userName)
                        && u.getPassword().equals(password))
                .findFirst().orElse(null);
    }

    public void remove(Long selectedRow) {
        try {
            userJpa.destroy(selectedRow);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void edit(User user) {
        try {
            userJpa.edit(user);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Role getRole(Long roleId) {
        return roleJpa.findRole(roleId);
    }

    public void add(User user) {
        userJpa.create(user);
    }

    public User findUser(Long id) {
        return userJpa.findUser(id);
    }
}

