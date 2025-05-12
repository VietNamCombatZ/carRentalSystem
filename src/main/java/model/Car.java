package main.java.model;

public class Car {
    private int id;
    private String name;
    private CarBrand brand;
    private CarStatus status;
    private double pricePerDay;
    private String carNumber;


    public Car() {
    }
    public Car( String name, CarBrand brand, CarStatus status, double pricePerDay, String carNumber) {

        this.name = name;
        this.brand = brand;
        this.status = status;
        this.pricePerDay = pricePerDay;
        this.carNumber = carNumber;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public CarBrand getBrand() {return brand;}
    public void setBrand(CarBrand brand) {this.brand = brand;}

    public CarStatus getStatus() {return status;}
    public void setStatus(CarStatus status) {this.status = status;}

    public double getPricePerDay() {return pricePerDay;}
    public void setPricePerDay(double pricePerDay) {this.pricePerDay = pricePerDay;}

    public String getCarNumber() {return carNumber;}
    public void setCarNumber(String carNumber) {this.carNumber = carNumber;}
}
