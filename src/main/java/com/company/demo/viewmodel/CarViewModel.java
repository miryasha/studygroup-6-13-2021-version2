package com.company.demo.viewmodel;

import com.company.demo.model.Name;

import java.time.LocalDate;
import java.util.Objects;

public class CarViewModel {

    private int id;
    private String carMake;
    private String carColor;
    private Name name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarViewModel that = (CarViewModel) o;
        return id == that.id && Objects.equals(carMake, that.carMake) && Objects.equals(carColor, that.carColor) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carMake, carColor, name);
    }

    @Override
    public String toString() {
        return "CarViewModel{" +
                "id=" + id +
                ", carMake='" + carMake + '\'' +
                ", carColor='" + carColor + '\'' +
                ", name=" + name +
                '}';
    }
}
