/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pointofsale.point_of_sale;

/**
 * The class that defines the article object.
 */

public class Article {

    private long Id;
    private String name;
    private double cost;

    public long getId() { return Id; }
    public String getName() { return name; }
    public double getCost() { return cost; }

    public void setId(long Id) { this.Id = Id; }
    public void setName(String name) { this.name = name; }
    public void setCost(double cost) { this.cost = cost; }

    public Article(long Id, String name, double cost) {
        this.Id = Id;
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return Id + " " + name + " " + cost;
    }
}
