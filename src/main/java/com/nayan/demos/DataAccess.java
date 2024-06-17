package com.nayan.demos;

import java.util.List;

public interface DataAccess {
	boolean addNew(Coin coin);
    List<Coin> getAll();
    Coin get(String name);
    boolean update(Coin coin);
    boolean delete(String name);
}