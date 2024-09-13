package com.local.um.logic;

import com.local.um.ui.AdminUI;
import com.local.um.ui.LoginUI;
import com.local.um.ui.RegisterUI;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class UIMediator {
    private final UserController controller;
    private LoginUI loginUI;
    private AdminUI adminUI;
    private RegisterUI registerUI;
    private User user, userLogin;
    private String username, password;
    private Long role;

    public UIMediator(UserController controller) {
        this.controller = controller;
    }

    public void showLoginUI() {
        if (loginUI == null) {
            loginUI = new LoginUI(this);
        }
        loginUI.setLocationRelativeTo(null);
        loginUI.setVisible(true);
    }

    public void showAdminUI() {
        if (adminUI == null) {
            adminUI = new AdminUI(this);
        }
        adminUI.refreshTable();
        adminUI.setLocationRelativeTo(loginUI);
        adminUI.setVisible(true);
    }

    public void showRegisterUI() {
        if (registerUI == null) {
            registerUI = new RegisterUI(this);
        }
        registerUI.setLocationRelativeTo(adminUI);
        registerUI.setVisible(true);
    }

    public void userLogin() {
        username = loginUI.getUsernameField();
        password = loginUI.getPasswordField();

        if (!username.isEmpty() || !password.isEmpty()) {
            if (controller.validateLogin(username, password)) {
                userLogin = controller.getUser(username, password);
                msg("¡Bienvenido/a! " + userLogin.getUserName());
                showAdminUI();
                loginUI.dispose();
            } else {
                msg("Usuario o contraseña no validos");
            }
        }
        toCleanFields();
    }

    public void saveUser(JTextField userNameField, JTextField passwordField, JComboBox<String> comboBox) {
        username = userNameField.getText();
        password = passwordField.getText();
        role = (long) comboBox.getSelectedIndex();

        if (validateFields()) {
            if (user == null) {
                controller.add(username, password, role);
                showAdminUI();
                registerUI.dispose();
                adminUI.refreshTable();
            }

            if (user != null) {
                user.setUserName(username);
                user.setPassword(password);
                user.setRole(controller.getRole(role));
                controller.edit(user);
                showAdminUI();
                registerUI.dispose();
                adminUI.refreshTable();
            }
        }
    }

    public List<User> getAllUsers() {
        return controller.getAll();
    }

    public boolean validateRole() {
        return 1L == userLogin.getRole().getId();
    }

    public boolean noBlankSpace() {
        return !username.concat(password).matches(".*\\s+.*");
    }

    public boolean validateFields() {
        if (fieldIsNotEmpty()) {
            if (noBlankSpace()) {
                return createIfNotExists();
            }
            msg("El nombre de usuario o contraseña \nno debe contener espacios en blanco");
        }
        return false;
    }

    public boolean createIfNotExists() {
        boolean ok = controller.createIfNotExists(username);
        if (user != null && !ok) {
            if (!user.getUserName().equals(username)) {
                msg("El nombre de usuario " + username + " ya existe");
            } else {
                return true;
            }
        } else if (!ok) {
            msg("El nombre de usuario " + username + " ya existe");
        }
        return ok;
    }

    public boolean fieldIsNotEmpty() {
        if (username.isEmpty() || password.isBlank() || role == 0L) {
            msg("Todos los campos son obligatorios");
            return false;
        }
        return true;
    }

    public void deleteUser() {
        controller.remove(adminUI.selectedRow());
    }

    public String getUserLogin() {
        return userLogin.getUserName();
    }

    public boolean disableUser(Long id) {
        return !Objects.equals(userLogin.getId(), id);
    }

    public void showCreateUI() {
        user = null;
        showRegisterUI();
        toCleanFields();
        registerUI.setTitleLabel("Create User");
        adminUI.dispose();
    }

    public void showEditUI() {
        showRegisterUI();
        registerUI.setTitleLabel("Edit User");
        adminUI.dispose();
    }

    public void setDataInFields() {
        user = controller.findUser(adminUI.selectedRow());
        registerUI.setUserNameField(user.getUserName());
        registerUI.setPasswordField(user.getPassword());
        registerUI.setComboBox(Integer.valueOf(user.getRole().getId().toString()));
    }

    public void toCleanFields() {
        if (registerUI != null) {
            registerUI.toCleanFields();
        } else {
            loginUI.toCleanFields();
        }
    }

    public void msg(String message) {
        JOptionPane.showMessageDialog(null, message, "",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
