package com.company.demo.dao;


import com.company.demo.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CarDaoTemp implements CarDao{

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_CAR_SQL =
            "insert into car (car_make, car_color, name_id) values (?, ?, ?)";

    private static final String SELECT_CAR_SQL =
            "select * from car where car_id = ?";

    private static final String SELECT_ALL_CARS_SQL =
            "select * from car";

    private static final String UPDATE_CAR_SQL =
            "update car set car_make = ? , car_color = ? where car_id = ?";

    private static final String DELETE_CAR =
            "delete from car where car_id = ?";

    @Autowired
    public CarDaoTemp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    @Transactional
    public Car addCar(Car car) {
        jdbcTemplate.update(
                INSERT_CAR_SQL,
                car.getCarMake(),
                car.getColor(),
                car.getNameId()
        );

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        car.setId(id);

        return car;


    }

    @Override
    public Car getCarMake(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_CAR_SQL, this::mapRowToCar, id);
        } catch (EmptyResultDataAccessException e) {
            // if there is no match for this album id, return null
            return null;
        }
    }

    @Override
    public List<Car> getAllCars() {
        return jdbcTemplate.query(SELECT_ALL_CARS_SQL, this::mapRowToCar);
    }

    @Override
    public void deleteCar(int id) {
        jdbcTemplate.update(DELETE_CAR, id);

    }

    @Override
    public void updateCar(Car car) {
        jdbcTemplate.update(
                UPDATE_CAR_SQL,
                car.getCarMake(),
                car.getColor()
        );

    }


    private Car mapRowToCar(ResultSet rs, int rowNum) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("car_id"));
        car.setCarMake(rs.getString("car_make"));
        car.setColor(rs.getString("car_color"));
        car.setNameId(rs.getInt("name_id"));


        return car;
    }
}
