public class TV extends Product{
	private String screenType;
	private String resolution;
	private int displaySize;
	
	//constructor
	public TV(int itemNo,String productName, int qtyAvail, double price , boolean status , String screenType, String resolution, int displaySize) {
		super(itemNo, productName, qtyAvail, price,status);
		this.setScreenType(screenType);
		this.setResolution(resolution);
		this.setDisplaySize(displaySize);
	}

	//accessor
	public String getScreenType() {
		return screenType;
	}
	
	public String getResolution() {
		return resolution;
	}
	
	public int getDisplaySize() {
		return displaySize;
	}


	//mutator
	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}
	
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public void setDisplaySize(int displaySize) {
		this.displaySize = displaySize;
	}
	
	//Method to calculate the value of the stock of TV
	public double tvStockVal() {
		return getQtyAvail()*getPrice();
	}
	
	//Override toString() method to return information of the TV object
	@Override
	public String toString() {
		String formattedPrice = String.format("%.2f", getPrice());
		String formattedInventoryPrice = String.format("%.2f", tvStockVal());
       return   "Item Number\t\t\t: " + getItemNo() +
                "\nProduct Name\t\t\t: " + getProductName() +
                "\nScreen Type\t\t\t: " + screenType +
                "\nResolution\t\t\t: " + resolution +
                "\nDisplay Size\t\t\t: " + displaySize + " inches" +
                "\nQuantity Available\t\t: " + getQtyAvail() +
                "\nPrice (RM)\t\t\t:RM " + formattedPrice +
                "\nInventory Value (RM)\t:RM " + formattedInventoryPrice +
                "\nProduct Status\t\t\t: " + (getStatus() ? "Active" : "Discontinued");
              
    }
	

}
