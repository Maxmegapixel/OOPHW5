package notebook;


import static notebook.controller.UserController.runApp;
import static notebook.util.DBConnector.createDB;

public class Main {
    public static void main(String[] args) {
        createDB();
        runApp();

    }
}
