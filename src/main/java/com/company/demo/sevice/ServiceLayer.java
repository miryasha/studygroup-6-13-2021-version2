package com.company.demo.sevice;

import com.company.demo.dao.CarDao;
import com.company.demo.dao.NameDao;
import com.company.demo.model.Car;
import com.company.demo.model.Name;
import com.company.demo.viewmodel.CarViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ServiceLayer {
    private CarDao carDao;
    private NameDao nameDao;
    @Autowired
    public ServiceLayer(CarDao carDao, NameDao nameDao) {
        this.carDao = carDao;
        this.nameDao = nameDao;
    }

    @Transactional
    public CarViewModel saveCar(CarViewModel carViewModel){

         Name n = new Name();

        Car c = new Car();
        c.setCarMake(carViewModel.getCarMake());
        c.setColor(carViewModel.getCarColor());
        c.setNameId(carViewModel.getName().getId());

        c = carDao.addCar(c);

        n.setName(carViewModel.getName().getName());
        n.setId(c.setNameId());
        n = nameDao.addName(n);

        carViewModel.setId(c.getId());



        return carViewModel;
    }

//    public CarViewModel findCar(int id) {
//        Car car = carDao.getCarMake(id);
//
//        return buildCarViewModel(car);
//    }


    private CarViewModel buildCarViewModel(Car car) {


        Name name = nameDao.getName(car.getNameId());
        CarViewModel carViewModel = new CarViewModel();

        carViewModel.setId(car.getId());
        carViewModel.setCarMake(car.getCarMake());
        carViewModel.setCarColor(car.getColor());
        carViewModel.setName(name);



        return carViewModel;

    }

    public List<CarViewModel> findAllCars(){



        List<Car> carList = carDao.getAllCars();
        List<CarViewModel> cvm = new ArrayList<>();

        CarViewModel carViewModel = new CarViewModel();

        for(Car car : carList){
            CarViewModel viewModel = buildCarViewModel(car);

            cvm.add(viewModel);

        }
        return cvm;
    }
}
