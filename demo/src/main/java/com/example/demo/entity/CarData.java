package com.example.demo.entity;

import lombok.Data;
import lombok.NonNull;
import javax.persistence.*;

@Entity
@Data
@Table(name="Program_Car")
public class CarData {
    @Id
    @SequenceGenerator(name="carData_seq",sequenceName="carData_seq")   // ทำให้ id gen ของตัวเองเองไม่แย่งกันใช้
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="carData_seq")
    @Column(name="carDataID")
    private @NonNull Long carID;

    private String model;
    private  String cC;

    public CarData() {
    }
    public CarData(String model, String cC) {

        this.model = model;
        this.cC = cC;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getcC() {
        return cC;
    }
    public void setcC(String cC) {
        this.cC = cC;
    }
}

