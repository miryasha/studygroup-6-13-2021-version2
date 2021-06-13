package com.company.demo.model;

import java.util.Objects;

public class Car {

    private int id;
    private String carMake;
    private String color;
    private int nameId;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNameId() {
        return nameId;
    }

    public void setNameId(int nameId) {
        this.nameId = nameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && nameId == car.nameId && Objects.equals(carMake, car.carMake) && Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carMake, color, nameId);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carMake='" + carMake + '\'' +
                ", color='" + color + '\'' +
                ", nameId=" + nameId +
                '}';
    }
}
