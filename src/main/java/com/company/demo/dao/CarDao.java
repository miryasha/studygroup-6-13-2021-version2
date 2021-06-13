package com.company.demo.dao;

import com.company.demo.model.Car;

import java.util.List;

public interface CarDao {
    Car addCar(Car car);

    Car getCarMake(int id);

    List<Car> getAllCars();

    void deleteCar(int id);

    void updateCar(Car car);

}
