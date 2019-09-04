package com.example.demo.entity;

import lombok.Data;
import lombok.NonNull;
import javax.persistence.*;

@Entity
@Data
@Table(name="Program_CarType")
public class CarType {
    @Id
    @SequenceGenerator(name="carType_seq",sequenceName="carType_seq")   // ทำให้ id gen ของตัวเองเองไม่แย่งกันใช้
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="carType_seq")
    @Column(name="TypeID",unique = true, nullable = false)

    private @NonNull Long carTypeID;
    private  String carType;

    public CarType() {
    }
    public CarType(String carType) {
        this.carType = carType;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }
}
