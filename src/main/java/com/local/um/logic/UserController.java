package com.local.um.logic;

import com.local.um.persistence.PersistenceController;
import java.util.List;

public class UserController {

    PersistenceController controller;

    public UserController() {
        controller = new PersistenceController();
    }

    public boolean validateLogin(String userName, String password) {
        return controller.validateLogin(userName, password);
    }

    public List<User> getAll() {
        return controller.getAll();
    }

    public User getUser(String userName, String password) {
        return controller.getUser(userName, password);
    }

    public void remove(Long selectedRow) {
        controller.remove(selectedRow);
    }

    public void add(String userName, String password, Long roleId) {
        controller.add(new User(userName, password, getRole(roleId)));
    }

    public void edit(User user) {
        controller.edit(user);
    }

    public Role getRole(Long roleId) {
        return controller.getRole(roleId);
    }

    public User findUser(Long selectedRow) {
        return controller.findUser(selectedRow);
    }

    public boolean createIfNotExists(String username) {
        return !controller.findUserName(username);
    }
}

