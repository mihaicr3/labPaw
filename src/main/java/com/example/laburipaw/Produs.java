package com.example.laburipaw;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;

@Entity
public class Produs {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    private Long id;
    @Setter
    private String nume;
    @Setter
    private float cost;

    protected Produs() {}

    public Produs(String nume, int cost) {
        this.nume = nume;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format(
                "Produs[id=%d, nume='%s', cost='%s']",
                id, nume, cost);
    }

    public Long getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public float getCost() {
        return cost;
    }
}