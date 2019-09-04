package com.example.demo.entity;

import lombok.Data;
import org.springframework.lang.NonNull;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity  //บอกว่าเป็น class entity class ที่เก็บขอมูล
@Data  // lombox จะสร้าง method getter setter ให้เอง
@Table(name="Program_Review") //ชื่อตาราง
public class Review {
    @Id
    @SequenceGenerator(name = "review_seq", sequenceName = "review_seq")   // ทำให้ id gen ของตัวเองเองไม่แย่งกันใช้
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq")
    @Column(name = "REVIEW_ID", unique = true, nullable = false)

    private @NonNull
    Long reviewID;

    @Size(min = 3,max = 50)
    @Pattern(regexp = "^[ก-๙]*") // ขึ้นต้นด้วยภาษาไทยตัวต่อไปจะมีก็ได้ไม่มีก็ได้
    private  String comment;

    @ManyToOne                  //joinความสัมพันธ์ *-1
    @JoinColumn(name = "statusID")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "carDataID")
    private CarData carData;

    @ManyToOne
    @JoinColumn(name = "colorID")
    private CarColor carColor;

    @ManyToOne
    @JoinColumn(name = "carTypeID" ,nullable = false)
    private CarType carType;

    public Review() {
    }
    public Review(CarData carData, CarColor carColor, CarType carType,Status status,String comment) {
        this.carData = carData;
        this.carColor = carColor;
        this.carType = carType;
        this.status = status;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CarData getCarData() {
        return carData;
    }

    public void setCarData(CarData carData) {
        this.carData = carData;
    }

    public CarColor getCarColor() {
        return carColor;
    }

    public void setCarColor(CarColor carColor) {
        this.carColor = carColor;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }
}
