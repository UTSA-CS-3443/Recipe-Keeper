package model;

public class Ingredient {
	String name;
	int quantity;
	String unit;
	
	public Ingredient(String name, int qty, String unit) {
		setName(name);
		setQuantity(qty);
		setUnit(unit);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
