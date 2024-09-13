package com.local.um.ui;

import com.local.um.logic.UIMediator;
import java.awt.*;
import javax.swing.*;

public class LoginUI extends JFrame{

    private JPanel contentPane, labelPane, fieldPane, headerPane, footerPane;
    private JLabel titleLabel, userLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton btnLogin, btnClean;
    UIMediator mediator;


    public LoginUI(UIMediator mediator) {
        this.mediator = mediator;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 260);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponent();
        setListeners();
    }


    private void initComponent() {
        contentPane = new JPanel(new BorderLayout(10,5));
        headerPane = new JPanel();
        labelPane = new JPanel(new GridLayout(0, 1));
        fieldPane = new JPanel(new GridLayout(0, 1, 0, 5));
        footerPane = new JPanel(new GridLayout(1, 2,5,0));

        titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        userLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        btnLogin = new JButton("Log In");
        btnClean = new JButton("Clean Up");

        headerPane.add(titleLabel);
        headerPane.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        labelPane.add(userLabel);
        fieldPane.add(usernameField);
        labelPane.add(passwordLabel);
        fieldPane.add(passwordField);

        footerPane.add(btnLogin);
        footerPane.add(btnClean);
        footerPane.setPreferredSize(new Dimension(300, 40));

        contentPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        contentPane.add(headerPane, BorderLayout.NORTH);
        contentPane.add(labelPane, BorderLayout.WEST);
        contentPane.add(fieldPane, BorderLayout.CENTER);
        contentPane.add(footerPane, BorderLayout.SOUTH);

        setContentPane(contentPane);
        setVisible(true);
    }

    private void setListeners() {
        btnLogin.addActionListener((e) -> mediator.userLogin());
        btnClean.addActionListener((e) -> toCleanFields());
    }

    public void toCleanFields() {
        usernameField.setText("");
        passwordField.setText("");
    }

    public String getUsernameField() {
        return usernameField.getText();
    }

    public String getPasswordField() {
        return String.valueOf(passwordField.getPassword());
    }
}

