package com.nayan.demos;

public class Create {
	
	public boolean create(Coin coin) {
		
		DataAccess dataAccess = new Percistance();
        return dataAccess.addNew(coin);
	}
	
}
