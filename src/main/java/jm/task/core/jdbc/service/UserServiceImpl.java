package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoJDBCImpl();

    /**
     * Создание таблицы users.
     */
    public void createUsersTable() {
        userDao.createUsersTable();
    }

    /**
     * Удаление таблицы users.
     */
    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    /**
     * Сохранение user в таблицу.
     *
     * @param name     = имя user.
     * @param lastName = фамилия user.
     * @param age      = возраст users.
     */
    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    /**
     * Удаление user по id.
     *
     * @param id = user  которого тербуется удалить.
     */
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    /**
     * Метод полечения списка всех пользователей.
     *
     * @return = возвращаемый результат.
     */
    public List<User> getAllUsers() {
        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }

    /**
     * Метод очистки таблицы users.
     */
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}
