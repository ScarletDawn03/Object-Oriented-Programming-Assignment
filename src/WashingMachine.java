
public class WashingMachine extends Product {


	private String type;
	private String eConsumption;
	private double size;
	
	//constructor
	public WashingMachine(int itemNo,String productName, int qtyAvail, double price ,boolean status, String type, String eConsumption, double size) {
		super(itemNo, productName, qtyAvail, price,status);
		this.setType(type);
		this.setEConsumption(eConsumption);
		this.setSize(size);
	}
	
	//accessor
	public String getType() {
		return type;
	}
	
	public String getEConsumption() {
		return eConsumption;
	}
	
	public double getSize() {
		return size;
	}
	
    //mutator
	public void setType(String type) {
		this.type=type;
	}

	public void setEConsumption(String eConsumption) {
		this.eConsumption = eConsumption;
	}
	
	public void setSize(double size) {
		this.size = size;
	}
	
	//Method to calculate the value of the stock of Refrigerator
	public double washStockVal() {
		return getQtyAvail()*getPrice();
	}
	
	//Override toString() method to return information of Refrigerator object
	@Override
    public String toString() {
		String formattedPrice = String.format("%.2f", getPrice());
		String formattedInventoryPrice = String.format("%.2f", washStockVal());
		 return "Item Number\t\t\t: " + getItemNo() +
                "\nProduct Name\t\t\t: " + getProductName() +
                "\nType\t\t\t\t\t: " + type +
                "\nElectricity Consumption\t: " + eConsumption +
                "\nSize Per Wash Cycle\t\t: " + size + " kg" +
                "\nQuantity Available\t\t: " + getQtyAvail() +
                "\nPrice (RM)\t\t\t:RM " + formattedPrice +
                "\nInventory Value (RM) \t:RM " + formattedInventoryPrice +
                "\nProduct Status\t\t\t: " + (getStatus() ? "Active" : "Discontinued");
                
    }
}