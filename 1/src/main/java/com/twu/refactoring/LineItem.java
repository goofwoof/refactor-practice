package com.twu.refactoring;

public class LineItem {
	private final String description;
	private final double price;
	private final int quantity;

	public LineItem(String description, double price, int quantity) {
		super();
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}

    double totalAmount() {
        return price * quantity;
    }

    public String getItemInfo(){
		String itemInfo = description + '\t' +
				price + '\t' +
				quantity + '\t' +
				this.totalAmount() + '\n';
		return itemInfo;
	}
}