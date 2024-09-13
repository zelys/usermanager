package com.local.um.ui;

import com.local.um.logic.UIMediator;
import java.awt.*;
import javax.swing.*;


public class RegisterUI extends JFrame {
    JPanel headerPane, labelPane, contentPane, fieldPane, buttonPane;
    JButton btnSave, btnCancel;
    JLabel userNameLabel, passwordLabel, roleLabel, titleLabel;
    JTextField usernameField, passwordField;
    JComboBox<String> comboBox;

    UIMediator mediator;

    public RegisterUI(UIMediator mediator) {
        this.mediator = mediator;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 280);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponent();
        setListeners();
    }

    private void initComponent() {
        titleLabel = new JLabel();
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        userNameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");
        roleLabel = new JLabel("Role");

        usernameField = new JTextField(20);
        passwordField = new JTextField(20);
        comboBox = new JComboBox<>(new String [] {"", "ADMIN", "USER"});

        btnCancel = new JButton("Cancel");
        btnSave = new JButton("Save");

        headerPane = new JPanel();
        labelPane = new JPanel(new GridLayout(0,1,0,5));
        fieldPane = new JPanel(new GridLayout(0,1,0,5));
        buttonPane = new JPanel(new GridLayout(1,3));
        contentPane = new JPanel(new BorderLayout());
        headerPane.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));

        labelPane.add(userNameLabel);
        labelPane.add(passwordLabel);
        labelPane.add(roleLabel);

        fieldPane.add(usernameField);
        fieldPane.add(passwordField);
        fieldPane.add(comboBox);

        headerPane.add(titleLabel);
        buttonPane.add(new JLabel(""));
        buttonPane.add(btnCancel);
        buttonPane.add(btnSave);
        buttonPane.setPreferredSize(new Dimension(300, 40));

        contentPane.add(headerPane, BorderLayout.NORTH);
        contentPane.add(labelPane, BorderLayout.WEST);
        contentPane.add(fieldPane, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.SOUTH);

        setContentPane(contentPane);
        setVisible(true);
    }

    private void setListeners() {
        btnCancel.addActionListener((e) -> cancelButton());
        btnSave.addActionListener((e) -> saveButton());
    }

    private void saveButton() {
        mediator.saveUser(usernameField, passwordField, comboBox);
    }

    private void cancelButton() {
        mediator.showAdminUI();
        dispose();
    }

    public void toCleanFields() {
        usernameField.setText("");
        passwordField.setText("");
        comboBox.setSelectedIndex(0);
    }

    public void setTitleLabel(String title) {
        this.titleLabel.setText(title);
    }

    public void setUserNameField(String username) {
        this.usernameField.setText(username);
    }

    public void setPasswordField(String password) {
        this.passwordField.setText(password);
    }

    public void setComboBox(Integer index) {
        this.comboBox.setSelectedIndex(index);
    }


}
