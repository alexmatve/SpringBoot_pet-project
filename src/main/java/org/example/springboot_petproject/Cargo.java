package org.example.springboot_petproject;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String content;
    private Integer price;
    private String city_send;
    private String date_send;
    private String city_arrive;
    private String date_arrive;

    // Геттеры
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public Integer getPrice() {
        return price;
    }

    public String getCity_send() {
        return city_send;
    }

    public String getDate_send() {
        return date_send;
    }

    public String getCity_arrive() {
        return city_arrive;
    }

    public String getDate_arrive() {
        return date_arrive;
    }

    // Сеттеры
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setCity_send(String city_send) {
        this.city_send = city_send;
    }

    public void setDate_send(String date_send) {
        this.date_send = date_send;
    }

    public void setCity_arrive(String city_arrive) {
        this.city_arrive = city_arrive;
    }

    public void setDate_arrive(String date_arrive) {
        this.date_arrive = date_arrive;
    }
}
