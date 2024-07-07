
public class Product {
	private int itemNo;
	private String productName;
	private int qtyAvail;
	private double price;
	private boolean status;
	
	//constructor
	public Product(int itemNo, String productName, int qtyAvail, double price,boolean status){
		    this.itemNo = itemNo;
		    this.productName = productName;
		    this.qtyAvail = qtyAvail;
		    this.price = price;
		    this.status = true; // Product is active
		   
	}
	
	//accessor
	public int getItemNo(){
		return itemNo;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public int getQtyAvail() {
		return qtyAvail;
	}
	
	public double getPrice() {
		return price;
	}

	public boolean getStatus() {
		return status;
	}
	
	//mutator
	public void setItemNo(int itemNo) {
		this.itemNo=itemNo;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public void setQtyAvail(int qtyAvail) {
		this.qtyAvail = qtyAvail;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	//Method to calculate the value of stock of Product 
	public double getInvValue() {
		return price*qtyAvail;
	}
	
	//Method to add the quantity of product in stock
	public void addQty(int quantity) {
		if(status) { //check if product is available
			qtyAvail+=quantity;
		}
		else
			System.out.println("Cannot add stock to a discontinued product.");
	}
	

	//Method to deduct the quantity of product in stock

	
	//Override toString() method to return information of the Product object
	@Override
	public String toString() {
		String formattedPrice = String.format("%.2f", price);
		return "\nItem Number\t\t\t: " + itemNo + 
				"\nProduct Name\t\t\t: " + productName + 
				"\nQuantity Available\t\t: " + qtyAvail
	            + "\nPrice (RM)\t\t\t: RM " + formattedPrice + 
	            "\nInventory Value (RM)\t: RM " + getInvValue() + 
	            "\nStatus\t\t\t: " +(getStatus() ? "Active" : "Discontinued");	
	}


}