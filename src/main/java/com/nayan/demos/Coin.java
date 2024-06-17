package com.nayan.demos;

import java.time.LocalDate;

public class Coin {
    private String name;
    private int quantity;
    private String country;
    private LocalDate mfd;

    public Coin(String name, int quantity, String country, LocalDate mfd) {
        this.name = name;
        this.quantity = quantity;
        this.country = country;
        this.mfd = mfd;
    }

	public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCountry() {
        return country;
    }

    public LocalDate getMfd() {
        return mfd;
    }

	public void setName(String name) {
		this.name = name;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setMfd(LocalDate mfd) {
		this.mfd = mfd;
	}
}