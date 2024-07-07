
public class Refrigerator extends Product {
	private String doorDesign;
	private String color;
	private double capacity;
	
	//constructor
	public Refrigerator(int itemNo,String productName, int qtyAvail, double price ,boolean status, String doorDesign, String color, double capacity) {
		super(itemNo, productName, qtyAvail, price,status);
		this.setDoorDesign(doorDesign);
		this.setColor(color);
		this.setCapacity(capacity);
	}
	
	//accessor
	public String getDoorDesign() {
		return doorDesign;
	}
	
	public String getColor() {
		return color;
	}
	
	public double getCapacity() {
		return capacity;
	}
	
    //mutator
	public void setDoorDesign(String doorDesign) {
		this.doorDesign=doorDesign;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
	
	//Method to calculate the value of the stock of Refrigerator
	public double refStockVal() {
		return getQtyAvail()*getPrice();
	}
	
	//Override toString() method to return information of Refrigerator object
	@Override
    public String toString() {
		String formattedPrice = String.format("%.2f", getPrice());
		String formattedInventoryPrice = String.format("%.2f", refStockVal());
		 return "Item Number\t\t\t: " + getItemNo() +
                "\nProduct Name\t\t\t: " + getProductName() +
                "\nDoor Design\t\t\t: " + doorDesign +
                "\nColor\t\t\t\t: " + color +
                "\nCapacity\t\t\t\t: " + capacity + " liters" +
                "\nQuantity Available\t\t: " + getQtyAvail() +
                "\nPrice (RM)\t\t\t:RM " + formattedPrice +
                "\nInventory Value (RM) \t:RM " + formattedInventoryPrice +
                "\nProduct Status\t\t\t: " + (getStatus() ? "Active" : "Discontinued");
                
    }
}