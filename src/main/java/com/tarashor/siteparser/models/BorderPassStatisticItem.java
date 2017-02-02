package com.tarashor.siteparser.models;

/**
 * Created by Taras on 02.02.2017.
 */
public class BorderPassStatisticItem {
    private String name;
    private int carsCountBeforeBorder;
    private int carsCountOnBorder;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCarsCountBeforeBorder() {
        return carsCountBeforeBorder;
    }

    public void setCarsCountBeforeBorder(int carsCountBeforeBorder) {
        this.carsCountBeforeBorder = carsCountBeforeBorder;
    }

    public int getCarsCountOnBorder() {
        return carsCountOnBorder;
    }

    public void setCarsCountOnBorder(int carsCountOnBorder) {
        this.carsCountOnBorder = carsCountOnBorder;
    }
}
