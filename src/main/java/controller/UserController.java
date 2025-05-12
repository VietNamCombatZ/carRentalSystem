package main.java.controller;

import main.java.dao.UserDAO;
import main.java.model.*;
import java.util.*;

public class UserController {
    private UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO();
    }

    /**
     * Thực hiện đăng nhập bằng cách kiểm tra username và password.
     * @param username Tên đăng nhập
     * @param password Mật khẩu (chưa mã hoá)
     * @return Trả về đối tượng User nếu đăng nhập thành công, ngược lại trả về null
     */
    public User login(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }


    public User register(String username, String password, String fullName, String phoneNumber, UserRole role) {
         User existedUser = userDAO.findByUsername(username);
        if (existedUser != null) {
            throw new RuntimeException("Username alreadyexists");
        }
        User user = new User(username, password, fullName, phoneNumber, role);
        userDAO.addUser(user);
        return user;
    }

    public User updateUserFields(int userId, Map<String, Object> fieldsToUpdate) {
        User updatedUser =  userDAO.updateUserFields(userId, fieldsToUpdate);
        if (updatedUser != null) {
            return updatedUser;
        } else {
            throw new RuntimeException("Update failed");
        }
    }
}
