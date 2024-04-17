package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl go = null;
        go.createUsersTable();
        User u1 = new User();
        go.saveUser("2", "2", (byte) 2);
        go.saveUser("3", "3", (byte) 3);
        go.saveUser("4", "4", (byte) 4);
        go.saveUser("1", "1", (byte) 1);
        System.out.println(go.getAllUsers());
        go.cleanUsersTable();
        go.dropUsersTable();
    }
}
