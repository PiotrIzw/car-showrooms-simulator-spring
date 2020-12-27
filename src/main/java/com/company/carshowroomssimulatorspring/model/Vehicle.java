package com.company.carshowroomssimulatorspring.model;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Vehicle implements Comparable<Vehicle>, Comparator<Vehicle>, Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private long id;
    @ColumnsNames("Brand")
    private String brand;
    @ColumnsNames("Model")
    private String model;
    @Column(name = "item_condition")
    @ColumnsNames("Item Condition")
    private ItemCondition condition;
    @ColumnsNames("Price")
    private double price;
    @ColumnsNames("Year of Production")
    private int yearOfProduction;
    @ColumnsNames("Mileage")
    private double mileage;
    @ColumnsNames("Engine capacity")
    private double engineCapacity;
    @ColumnsNames("Amount")
    private int amount;
    @ColumnsNames("Is Reserved")
    private boolean isReserved;
    @Transient
    @Column(name = "showroom_id")
    private long showroomId;


    public Vehicle(String brand, String model, ItemCondition condition, double price,
                   int yearOfProduction, double mileage, double engineCapacity, int amount, boolean isReserved) {
        this.brand = brand;
        this.model = model;
        this.condition = condition;
        this.price = price;
        this.yearOfProduction = yearOfProduction;
        this.mileage = mileage;
        this.engineCapacity = engineCapacity;
        this.amount = amount;
        this.isReserved = isReserved;
    }

    public Vehicle(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCondition(ItemCondition condition) {
        this.condition = condition;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public long getShowroomId() {
        return showroomId;
    }

    public void setShowroomId(long showroomId) {
        this.showroomId = showroomId;
    }

    public void print() {
        System.out.println(
                "Vehicle information:" + System.lineSeparator() +
                        "Brand: " + brand + System.lineSeparator() +
                        "Model: " + model + System.lineSeparator() +
                        "Condition: " + condition + System.lineSeparator() +
                        "Price: " + price + System.lineSeparator() +
                        "Year of Production: " + yearOfProduction + System.lineSeparator() +
                        "Mileage: " + mileage + System.lineSeparator() +
                        "Engine Capacity: " + engineCapacity + System.lineSeparator() +
                        "Amount: " + amount + System.lineSeparator()
        );
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getAmount() {
        return amount;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public ItemCondition getCondition() {
        return condition;
    }

    public void changeVehicleAmountByOne(boolean doAdd) {
        if (doAdd) amount++;
        else amount--;
    }

    public double getMileage() {
        return mileage;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        if (Double.compare(vehicle.price, price) != 0) return false;
        if (yearOfProduction != vehicle.yearOfProduction) return false;
        if (Double.compare(vehicle.mileage, mileage) != 0) return false;
        if (Double.compare(vehicle.engineCapacity, engineCapacity) != 0) return false;
        if (amount != vehicle.amount) return false;
        if (brand != null ? !brand.equals(vehicle.brand) : vehicle.brand != null) return false;
        if (model != null ? !model.equals(vehicle.model) : vehicle.model != null) return false;
        return condition == vehicle.condition;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = brand != null ? brand.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + yearOfProduction;
        temp = Double.doubleToLongBits(mileage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(engineCapacity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + amount;
        return result;
    }

    @Override
    public int compareTo(Vehicle o) {
        if (brand.compareToIgnoreCase(o.brand) == 0 && model.compareToIgnoreCase(o.model) == 0)
            return 0;
        else
            return brand.compareToIgnoreCase(o.brand);
    }

    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        int value = o1.amount - o2.amount;
        if(value == 0){
            return 0;
        }
        else if(value < 0){
            return 1;
        }
        else{
            return -1;
        }

    }

}