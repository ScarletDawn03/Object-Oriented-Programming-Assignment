
public class Stove extends Product {

	private int burnerNum;
	private String color;
	private String material;
	
	//constructor
	public Stove(int itemNo,String productName, int qtyAvail, double price ,boolean status, int burnerNum, String color, String material) {
		super(itemNo, productName, qtyAvail, price,status);
		this.setBurnerNum(burnerNum);
		this.setColor(color);
		this.setMaterial(material);
	}
	
	//accessor
	public int getBurnerNum() {
		return burnerNum;
	}
	
	public String getColor() {
		return color;
	}
	
	public String getMaterial() {
		return material;
	}
	
    //mutator
	public void setBurnerNum(int burnerNum) {
		this.burnerNum=burnerNum;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public void setMaterial(String material) {
		this.material = material;
	}
	
	//Method to calculate the value of the stock of Refrigerator
	public double stoveStockVal() {
		return getQtyAvail()*getPrice();
	}
	
	//Override toString() method to return information of Refrigerator object
	@Override
    public String toString() {
		String formattedPrice = String.format("%.2f", getPrice());
		String formattedInventoryPrice = String.format("%.2f", stoveStockVal());
		 return "Item Number\t\t\t: " + getItemNo() +
                "\nProduct Name\t\t\t: " + getProductName() +
                "\nNumber of Burner\t\t: " + burnerNum +
                "\nColor\t\t\t\t: " + color +
                "\nMaterial\t\t\t\t: " + material  +
                "\nQuantity Available\t\t: " + getQtyAvail() +
                "\nPrice (RM)\t\t\t:RM " + formattedPrice +
                "\nInventory Value (RM) \t:RM " + formattedInventoryPrice +
                "\nProduct Status\t\t\t: " + (getStatus() ? "Active" : "Discontinued");
                
    }
}
