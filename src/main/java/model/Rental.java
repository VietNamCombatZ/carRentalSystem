package main.java.model;

import java.sql.Date;

public class Rental {
    private int id;
    private int userId;
    private int carId;
    private Date rentDate;
    private Date returnDate;

//    private float pricePerDay;

    public Rental() {
    }
    public Rental( int userId, int carId, Date rentDate, Date returnDate) {

        this.userId = userId;
        this.carId = carId;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
//        this.pricePerDay = pricePerDay;
    }

    public int getId(){return this.id;}
    public void setId(int id){this.id = id;}

    public int getUserId(){return this.userId;}
    public void setUserId(int userId){this.userId = userId;}

    public int getCarId(){return this.carId;}
    public void setCarId(int carId){this.carId = carId;}

    public Date getRentDate(){return this.rentDate;}
    public void setRentDate(Date rentDate){this.rentDate = rentDate;}

    public Date getReturnDate(){return this.returnDate;}
    public void setReturnDate(Date returnDate){this.returnDate = returnDate;}
//
//    public float getPricePerDay(){return this.pricePerDay;}
//    public void setPricePerDay(float pricePerDay){this.pricePerDay = pricePerDay;}


}
