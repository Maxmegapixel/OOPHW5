package notebook.controller;

import notebook.model.User;
import notebook.model.dao.impl.FileOperation;
import notebook.model.repository.GBRepository;
import notebook.model.repository.impl.UserRepository;
import notebook.view.UserView;

import java.util.List;
import java.util.Objects;

import static notebook.util.DBConnector.DB_PATH;

public class UserController {
    private final GBRepository repository;

    public UserController(GBRepository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        repository.create(user);
    }

    public User readUser(Long userId) throws Exception {
        List<User> users = repository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getId(), userId)) {
                return user;
            }
        }

        throw new RuntimeException("User not found");
    }

    public void updateUser(String userId, User update) {
        update.setId(Long.parseLong(userId));
        repository.update(Long.parseLong(userId), update);
    }

    public List<User> readAll(){

        return repository.findAll();
    }
    public void deleteUser(String deleteId) {
        repository.delete(Long.parseLong(deleteId));
    }

    public static void runApp() {
        FileOperation fileOperation = new FileOperation(DB_PATH);
        var repository = new UserRepository(fileOperation);
        UserController controller = new UserController(repository);
        UserView view = new UserView(controller, repository);
        view.run();
    }
}
