package com.local.um.ui;

import com.local.um.logic.UIMediator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class AdminUI extends JFrame {

    JPanel headerPane, mainPane, contentPane, tablePane, buttonPane;
    JButton btnCreate, btnEdit, btnDelete, btnExit;
    JLabel titleLabel, userLabel;
    JTable userTable;
    DefaultTableModel tableModel;

    UIMediator mediator;

    public AdminUI(UIMediator mediator) {
        this.mediator = mediator;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponent();
        setListeners();
    }

    private void initComponent() {
        titleLabel = new JLabel("User Manager System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        userLabel = new JLabel("¡Hola, " + mediator.getUserLogin() + "!");
        userLabel.setHorizontalAlignment(JLabel.RIGHT);

        btnCreate = new JButton("Create New");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnExit = new JButton("Exit");

        headerPane = new JPanel(new BorderLayout());
        headerPane.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        headerPane.add(titleLabel, BorderLayout.CENTER);
        headerPane.add(userLabel, BorderLayout.NORTH);

        setButtons();
        buttonPane = new JPanel(new GridLayout(6, 1, 0, 2));
        buttonPane.add(btnCreate);
        buttonPane.add(btnEdit);
        buttonPane.add(btnDelete);
        buttonPane.add(new JLabel(""));
        buttonPane.add(new JLabel(""));
        buttonPane.add(btnExit);

        tablePane = new JPanel(new BorderLayout(5, 0));
        tablePane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        tablePane.add(setTablePane(), BorderLayout.CENTER);
        tablePane.add(buttonPane, BorderLayout.EAST);

        mainPane = new JPanel(new GridLayout(1,0));
        mainPane.setBorder(BorderFactory.createEtchedBorder());
        mainPane.add(tablePane);

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        contentPane.add(headerPane, BorderLayout.NORTH);
        contentPane.add(mainPane, BorderLayout.CENTER);

        setContentPane(contentPane);
        setVisible(true);
    }

    private void setListeners() {

        btnCreate.addActionListener((e) -> createButton());
        btnEdit.addActionListener((e) -> editButton());
        btnDelete.addActionListener((e) -> deleteButton());
        btnExit.addActionListener((e) -> System.exit(0));
    }

    private DefaultTableModel customTableModel() {
        String[] headerName = {"ID", "USERNAME", "ROLE"};
        tableModel = new DefaultTableModel(headerName, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return tableModel;
    }

    private JScrollPane setTablePane() {
        userTable = new JTable(customTableModel());
        userTable.setAutoResizeMode(0);
        TableColumnModel columnModel = userTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(190);
        columnModel.getColumn(2).setPreferredWidth(190);
        return new JScrollPane(userTable);
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        var users = mediator.getAllUsers();
        if (users != null) {
            for (var u : users) {
                if (mediator.disableUser(u.getId())) {
                    tableModel.addRow(new Object[]{
                            u.getId(),
                            u.getUserName(),
                            u.getRole().getRoleName()});
                }
            }
        }
    }

    public void setButtons() {
        if (!mediator.validateRole()) {
            btnCreate.setVisible(false);
            btnDelete.setVisible(false);
            btnEdit.setVisible(false);
        }
    }

    public void createButton() {
        mediator.showCreateUI();
    }

    public void editButton(){
        if (selectedRow() > 0) {
            mediator.showEditUI();
            mediator.setDataInFields();
        }
    }

    public void deleteButton() {
        if (selectedRow() > 0) {
            mediator.deleteUser();
            refreshTable();
        }
    }

    public Long selectedRow() {
        if (userTable.getRowCount() > 0) {
            if (userTable.getSelectedRow() != -1) {
                return (Long) userTable.getValueAt(userTable.getSelectedRow(),0);
            }
        }
        return -1L;
    }
}
