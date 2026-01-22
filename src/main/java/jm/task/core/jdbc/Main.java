package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Артур", "Пирожков", (byte) 45);
        userService.saveUser("ГарикБульдог", "Харламов", (byte) 47);
        userService.saveUser("Илья", "Карпенко", (byte) 47);
        userService.saveUser("Давид", "Демурчан", (byte) 47);

        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
