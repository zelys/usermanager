package com.local.um;

import com.local.um.logic.UIMediator;
import com.local.um.logic.UserController;
import javax.swing.*;

public class AppUserManager {
    public static void main(String[] args) {
        UserController controller = new UserController();
        UIMediator mediator = new UIMediator(controller);
        SwingUtilities.invokeLater(mediator::showLoginUI);
    }
}
