package main.java.dao;


import main.java.model.*;
import main.java.util.DBConnection;

import java.util.*;
import java.sql.*;
public class UserDAO {

    public void addUser(User user) {
        String sql = "INSERT INTO users (username, password, fullName, phoneNumber, role) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFullName());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setString(5, user.getRole().name());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                u.setFullName(rs.getString("fullName"));
                u.setPhoneNumber(rs.getString("phoneNumber"));
                u.setRole(UserRole.valueOf(rs.getString("role")));
                return u;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public void updateUserFields(int userId, Map<String, Object> fieldsToUpdate) {
        if (fieldsToUpdate == null || fieldsToUpdate.isEmpty()) return;

        StringBuilder sql = new StringBuilder("UPDATE users SET ");
        List<Object> values = new ArrayList<>();

        for (String column : fieldsToUpdate.keySet()) {
            sql.append(column).append(" = ?, ");
            values.add(fieldsToUpdate.get(column));
        }

        // Xoá dấu phẩy cuối và thêm điều kiện WHERE
        sql.setLength(sql.length() - 2);
        sql.append(" WHERE id = ?");
        values.add(userId);

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < values.size(); i++) {
                stmt.setObject(i + 1, values.get(i));
            }

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUserById(int userId) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

}
