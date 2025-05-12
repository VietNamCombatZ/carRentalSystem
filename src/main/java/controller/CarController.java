package main.java.controller;

import main.java.dao.*;
import main.java.model.*;
import java.util.*;

public class CarController {
    private CarDAO carDAO;

    public CarController() {
        this.carDAO = new CarDAO();
    }

    /**
     * Lấy danh sách xe có sẵn
     * @return danh sách xe có sẵn
     */
    public List<Car> getAvailableCars() {
        return carDAO.getAllAvailableCars();
    }
}
