package main.java.dao;

import main.java.model.*;
import main.java.util.DBConnection;


import java.sql.*;
import java.util.*;

public class CarDAO {
    public List<Car> getAllAvailableCars() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE status = 'AVAILABLE'";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setName(rs.getString("name"));
                car.setBrand(CarBrand.valueOf(rs.getString("brand")));
                car.setStatus(CarStatus.valueOf(rs.getString("status")));
                car.setPricePerDay(rs.getDouble("pricePerDay"));
                car.setDescription(rs.getString("description"));
                cars.add(car);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return cars;
    }

    public void addCar(Car car) {
        String sql = "INSERT INTO cars (name, brand, status, price_per_day, description) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, car.getName());
            stmt.setString(2, car.getBrand().name());
            stmt.setString(3, car.getStatus().name());
            stmt.setDouble(4, car.getPricePerDay());
            stmt.setString(5, car.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                car.setId(rs.getInt(1));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public Car getCarById(int carId) {
        String sql = "SELECT * FROM cars WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setName(rs.getString("name"));
                car.setBrand(CarBrand.valueOf(rs.getString("brand")));
                car.setStatus(CarStatus.valueOf(rs.getString("status")));
                car.setPricePerDay(rs.getDouble("price_per_day"));
                car.setDescription(rs.getString("description"));
                return car;
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public void updateCarStatus(int carId, CarStatus status) {
        String sql = "UPDATE cars SET status=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status.name());
            stmt.setInt(2, carId);
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void updateCarByField(int carId, Map<String, Object> fieldsToUpdate) {
        if (fieldsToUpdate == null || fieldsToUpdate.isEmpty()) return;

        StringBuilder sql = new StringBuilder("UPDATE cars SET ");
        List<Object> values = new ArrayList<>();



        for (String column : fieldsToUpdate.keySet()) {
            sql.append(column).append(" = ?, ");
            values.add(fieldsToUpdate.get(column));
        }

        // Remove the last comma and space, and add the WHERE clause
        sql.setLength(sql.length() - 2);
        sql.append(" WHERE id = ?");
        values.add(carId);

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < values.size(); i++) {
                stmt.setObject(i + 1, values.get(i));
            }
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void deleteCarById(int carId) {
        String sql = "DELETE FROM cars WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carId);
            stmt.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
