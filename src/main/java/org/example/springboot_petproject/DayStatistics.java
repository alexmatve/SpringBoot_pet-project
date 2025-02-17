package org.example.springboot_petproject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

public class DayStatistics {
    private String date;
    private Integer cargoCount;
    private Integer totalCost;

    public DayStatistics(String date, Integer cargoCount, Integer totalCost) {
        this.date = date;
        this.cargoCount = cargoCount;
        this.totalCost = totalCost;
    }

    // Getters and Setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getCargoCount() {
        return cargoCount;
    }

    public void setCargoCount(Integer cargoCount) {
        this.cargoCount = cargoCount;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }
}

