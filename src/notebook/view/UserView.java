package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.model.repository.impl.UserRepository;
import notebook.util.Commands;

import java.util.Scanner;

public class UserView {
    private final UserController userController;
    private final UserRepository userRepository;


    public UserView(UserController userController, UserRepository userRepository) {
        this.userController = userController;
        this.userRepository = userRepository;
    }

    public void run(){
        Commands com;

        while (true) {
            String command = userRepository.prompt("Введите команду: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    User u = userRepository.createUser();
                    userController.saveUser(u);
                    break;
                case READ:
                    String id = userRepository.prompt("Идентификатор пользователя: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case LIST:
                    System.out.println(userController.readAll());
                    break;
                case DELETE:
                    String deleteId = userRepository.prompt("Enter user id: ");
                    userController.deleteUser(deleteId);
                    break;
                case UPDATE:
                    String userId = userRepository.prompt("Enter user id: ");
                    userController.updateUser(userId, userRepository.createUser());
            }
        }
    }


}
