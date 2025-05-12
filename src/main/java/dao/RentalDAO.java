package main.java.dao;

import main.java.model.*;
import main.java.util.DBConnection;
import java.sql.*;
import java.util.*;

public class RentalDAO {
    public void createRent(Rental rental) {
        String sql = "INSERT INTO rentals (userId, carId, rentDate, returnDate) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, rental.getUserId());
            stmt.setInt(2, rental.getCarId());
            stmt.setDate(3, new java.sql.Date(rental.getRentDate().getTime()));
            stmt.setDate(4, new java.sql.Date(rental.getReturnDate().getTime()));
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                rental.setId(rs.getInt(1));
            }

        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<Rental> getRentalsByUserId(int userId) {
        List<Rental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM rentals WHERE userId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Rental rental = new Rental();
                rental.setId(rs.getInt("id"));
                rental.setUserId(rs.getInt("userId"));
                rental.setCarId(rs.getInt("carId"));
                rental.setRentDate(rs.getDate("rentDate"));
                rental.setReturnDate(rs.getDate("returnDate"));
                rentals.add(rental);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return rentals;
    }

    public List<Rental> getAllRent(){
        List<Rental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM rentals";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Rental rental = new Rental();
                rental.setId(rs.getInt("id"));
                rental.setUserId(rs.getInt("userId"));
                rental.setCarId(rs.getInt("carId"));
                rental.setRentDate(rs.getDate("rentDate"));
                rental.setReturnDate(rs.getDate("returnDate"));
                rentals.add(rental);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return rentals;
    }

    public void updateRentByField(int rentalId, Map<String, Object> fieldsToUpdate) {
        if (fieldsToUpdate == null || fieldsToUpdate.isEmpty()) return;

        StringBuilder sql = new StringBuilder("UPDATE rentals SET ");
        List<Object> values = new ArrayList<>();

        for (String column : fieldsToUpdate.keySet()) {
            sql.append(column).append(" = ?, ");
            values.add(fieldsToUpdate.get(column));
        }

        // Xoá dấu phẩy cuối và thêm điều kiện WHERE
        sql.setLength(sql.length() - 2);
        sql.append(" WHERE id = ?");
        values.add(rentalId);

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < values.size(); i++) {
                stmt.setObject(i + 1, values.get(i));
            }
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void deleteRentById(int rentalId) {
        String sql = "DELETE FROM rentals WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, rentalId);
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
