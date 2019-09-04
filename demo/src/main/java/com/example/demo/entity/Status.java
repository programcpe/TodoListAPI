package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Program_Status")
public class Status {
    @Id
    @SequenceGenerator(name="status_seq",sequenceName="status_seq")   // ทำให้ id gen ของตัวเองเองไม่แย่งกันใช้
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="status_seq")
    @Column(name="statusID")
    private @NonNull
    Long statusID;
    private @NonNull
    String status;

    public Long getStatusID() {
        return statusID;
    }

    public void setStatusID(Long statusID) {
        this.statusID = statusID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

