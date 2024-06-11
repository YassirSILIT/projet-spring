package com.iSoftNetwork.inventoryservice.dto;

public class CountName {
    private Double percentage;
    private String name;

    public CountName(Double percentage, String name) {
        this.percentage = percentage;
        this.name = name;
    }

    // Getters and Setters
    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
