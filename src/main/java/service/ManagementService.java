package service;

import dao.CarDAO;
import model.Car;
import java.sql.SQLException;
import java.util.List;

public class ManagementService {
    private CarDAO carDAO = new CarDAO();

    public void addCar(Car car) throws Exception {
        try {
            carDAO.create(car);
        } catch (SQLException e) {
            throw new Exception("Failed to add car: " + e.getMessage(), e);
        }
    }

    public List<Car> getAllCars() throws Exception {
        try {
            return carDAO.findAll();
        } catch (SQLException e) {
            throw new Exception("Error fetching cars", e);
        }
    }

    public Car getCarById(Long id) throws Exception {
        try {
            return carDAO.find(id);
        } catch (SQLException e) {
            throw new Exception("Error fetching car by id", e);
        }
    }

    public void updateCar(Car car) throws Exception {
        try {
            carDAO.update(car);
        } catch (SQLException e) {
            throw new Exception("Error updating car", e);
        }
    }

    public void deleteCar(Long id) throws Exception {
        try {
            Car car = carDAO.find(id);
            if (car != null) {
                carDAO.delete(car);
            } else {
                throw new Exception("Car not found.");
            }
        } catch (SQLException e) {
            throw new Exception("Error deleting car", e);
        }
    }
}

