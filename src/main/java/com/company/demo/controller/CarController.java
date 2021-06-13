package com.company.demo.controller;


import com.company.demo.model.Car;
import com.company.demo.sevice.ServiceLayer;
import com.company.demo.viewmodel.CarViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    @Autowired
    private ServiceLayer serviceLayer;

    @RequestMapping(value = "/car", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CarViewModel createCar(@RequestBody CarViewModel carViewModel) {
        return serviceLayer.saveCar(carViewModel);
    }

    @RequestMapping(value = "/car", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<CarViewModel> listOfCarModels() {

        return serviceLayer.findAllCars();
    }



}
