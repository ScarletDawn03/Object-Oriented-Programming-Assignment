import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class StockManagement extends Application {
	
	boolean input;
	UserInfo user = new UserInfo();
	ArrayList<Product> product = new ArrayList<Product>();
	//maximum number of product
	int maxNum;
	int choice;
	//counter for each product type
	int refCount = 0;
	int tvCount = 0;
	int washCount=0;
	int stoveCount=0;


	
	
	//Start pane
		@Override
	    public void start(Stage primaryStage) {    
	        LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	        String formattedDateTime = now.format(formatter);

	        BorderPane root = new BorderPane();


	        // Create an ImageView to display the logo
	        ImageView logoImageView = new ImageView("Images/LOGO.jpg");
	        logoImageView.setFitWidth(300); // Adjust as needed
	        logoImageView.setFitHeight(200); // Adjust as needed

	        Font times2 = Font.font("Times New Roman", FontWeight.BOLD, 14);
	        String txt= new String("Wecome to M3 stock management system!");


	        // Add the logo image to the top of the BorderPane
	        Label imageLabel = new Label();
		    imageLabel.setContentDisplay(ContentDisplay.BOTTOM);
		    imageLabel.setPrefSize(200,  100);
		    imageLabel.setGraphic(logoImageView);
		    imageLabel.setText(txt);
		    imageLabel.setAlignment(Pos.CENTER);
		    
		    //Hbox to get the date
	        HBox right = new HBox();
	        right.setAlignment(Pos.BOTTOM_RIGHT);
	        

	        
	        Label dateTime = new Label("Date and time now: \n" + formattedDateTime);
	        dateTime.setFont(times2);
	        right.getChildren().addAll(dateTime);
	        
	        Label developer = new Label("Created by: \n" +  "\nTeng Zhi Kwang" +
	        "\nTeh Yee Jie" + "\nNg Jun Yuan" + "\nSim En Wei"); 
	        developer.setFont(times2);
	        developer.setAlignment(Pos.CENTER_LEFT);

	        Button next = new Button("Next");
	        next.setAlignment(Pos.CENTER_RIGHT);
	        next.setOnAction(e -> Naming(primaryStage));
	        
	        
	        
	        //Hbox to store developer name and button
	        HBox down = new HBox();
	        down.setAlignment(Pos.CENTER);
	        down.spacingProperty().bind(Bindings.divide(primaryStage.widthProperty(),3));
	        down.getChildren().addAll(developer,next);
	        
	        root.setCenter(imageLabel);

	        root.setTop(right);
	        root.setBottom(down);
	        root.setPadding(new Insets(15));

	        Scene scene = new Scene(root, 450, 400);
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Stock Management System");
	        primaryStage.show();
	    }
		
	
	
	//getting name from user
	public void Naming(Stage primaryStage) {
		VBox name = new VBox();
		name.setPadding(new Insets(10));
		// Create an ImageView to display the logo
		ImageView nameGifView = new ImageView("Images/YourName.gif");
        nameGifView.setFitWidth(300); // Adjust as needed
        nameGifView.setFitHeight(250); // Adjust as needed


        // Add the Namegif image to the top of the BorderPane
        Label namegifLabel = new Label();

	    namegifLabel.setPrefSize(200,  100);
	    namegifLabel.setGraphic(nameGifView);
	    namegifLabel.setAlignment(Pos.CENTER);
	    HBox combineName =new HBox();
		Label naming = new Label("Please enter your first name followed by surname (with spaces) :\n");
		naming.setFont(Font.font("Times", FontWeight.NORMAL, 15));
		Label naming2 = new Label("Example: ");
		naming2.setFont(Font.font("Times", FontWeight.NORMAL, 15));
		Label naming3 = new Label("Zhi Kwang Teng");
		naming3.setFont(Font.font("Times", FontWeight.BOLD, 15));
		naming3.setTextFill(Color.PURPLE);
		Label naming4 = new Label(" where ");
		naming4.setFont(Font.font("Times", FontWeight.NORMAL, 15));
		Label naming5 = new Label("Teng ");
		naming5.setFont(Font.font("Times", FontWeight.BOLD, 15));
		naming5.setTextFill(Color.PURPLE);
		Label naming6 = new Label("is your sirname.\n");
		naming6.setFont(Font.font("Times", FontWeight.NORMAL, 15));
		
		combineName.getChildren().addAll(naming2,naming3,naming4,naming5,naming6);
		TextField name1 = new TextField();
		name1.setPrefColumnCount(50);
		
		Button next = new Button("Next");
		
		//to trigger multiple action
		class handler implements EventHandler<ActionEvent> {
	        	@Override
	        	public void handle(ActionEvent e) {
	        		user.detectSpace(name1.getText());
	        		Decision(primaryStage);
	        	}
	    }
	    handler handler1 = new handler();
	       
	    next.setOnAction(handler1); 

		name.getChildren().addAll(namegifLabel,naming,combineName, name1, next);
		
		Scene scene = new Scene(name, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Stock Management System");
        primaryStage.show();
        
       
	}
	
	public void Decision(Stage primaryStage){
		Label choice = new Label("Would you like to add any Product? :\n");
		choice.setFont(Font.font("Times", FontWeight.NORMAL, 15));
		
		Button yes=new Button("Yes");
		Button no=new Button("No");
		
		yes.setOnAction(e->GetMaxProdNum(primaryStage));
		no.setOnAction(e-> {
		JOptionPane.showMessageDialog(null,"Enter 0 to exit the program","Request", JOptionPane.INFORMATION_MESSAGE);
		GetMaxProdNum(primaryStage);
		});
       

    
		
	HBox h=new HBox(10,yes,no);
	h.setAlignment(Pos.CENTER);
	h.setPadding(new Insets(0,0,10,0));
	
	VBox v=new VBox(choice,h);
	v.setAlignment(Pos.CENTER);
	
	Scene scene=new Scene(v,450,400);
	primaryStage.setScene(scene);
	primaryStage.setTitle("Stock Management System");
	primaryStage.show();
		
}
	
	//get maximum number of product
		public void GetMaxProdNum(Stage primaryStage) { 
				
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(10, 15,10,15));
		gridPane.setHgap(5);
		gridPane.setVgap(5);
				

			Label label = new Label();
			TextField text = new TextField();
			text.setPrefColumnCount(5);
				
			// exception handling for input
			Button next = new Button("Next");
			next.setOnAction(e->{
				input = true;
				String num = text.getText();
				try {
					label.setText(" ");

					if (num.isEmpty()) { // Check if input is empty
		                label.setText("Please enter a valid number.");
		                label.setStyle("-fx-text-fill: red");
		                input = false;
		            } else {
		                maxNum = Integer.parseInt(num);
		                if (maxNum < 0) {
		                    label.setText("Invalid input! Please enter a positive integer.");
		                    label.setStyle("-fx-text-fill: red");
		                    input = false;
		                }
				}
				}
				catch(NumberFormatException ex) {
					label.setText("//invalid input, please insert an integer");
					label.setStyle("-fx-text-fill: red");
					input = false;
				};
					
					
				if(input && maxNum > 0) {
					
					AddProd(primaryStage);
					
				}
				else if(input && maxNum == 0)
					exit(primaryStage);
			});
				
				Label getName = new Label("Welcome " + user.getName() + '!');
				getName.setFont(Font.font("Times", FontWeight.BOLD, 15));
				gridPane.add(getName, 0, 1);
				gridPane.add(new Label("Please enter the number of product you want to store: "), 0, 2);
				gridPane.add(text, 0, 3);
				gridPane.add(label, 0, 4);
				gridPane.add(next, 4, 5);
				Scene scene1 = new Scene(gridPane, 500, 250);
				primaryStage.setScene(scene1);
		        primaryStage.setTitle("Stock Management System");
		}

		//choose product type to add into array list
		public void AddProd(Stage primaryStage) { 
			
				Label label2 = new Label("Please choose a product type:");
				label2.setFont(Font.font("Times", FontWeight.BOLD, 15));
				Button addRef = new Button("Add refrigerator");
				Button addTV = new Button("Add TV");
				Button addWash = new Button("Add washing machine");
				Button addStove = new Button("Add stove");
				Button back = new Button("Back");
				
				addRef.setPrefSize(250, 25);
				addTV.setPrefSize(250, 25);
				addWash.setPrefSize(250, 25);
				addStove.setPrefSize(250, 25);
				back.setPrefSize(250, 25);

				
				addRef.setOnAction(e -> {
					AddRef(primaryStage);
				});
				addTV.setOnAction(e -> {
					AddTV(primaryStage);
				});
				addWash.setOnAction(e -> {
					AddWash(primaryStage);
				});
				addStove.setOnAction(e -> {
					AddStove(primaryStage);
				});
				
				back.setOnAction(e -> {
					GetMaxProdNum(primaryStage);
				});

				
				GridPane gridPane = new GridPane();
				gridPane.setPadding(new Insets(10, 15,10,15));
				gridPane.setHgap(8);
				gridPane.setVgap(8);
				gridPane.setAlignment(Pos.CENTER);
				gridPane.add(label2, 0, 0);
				gridPane.add(addRef, 0, 1);
				gridPane.add(addTV, 0, 2); 
				gridPane.add(addWash, 0, 3);
				gridPane.add(addStove, 0, 4); 
				gridPane.add(back, 0, 5);

				
				Scene scene1 = new Scene(gridPane, 300, 250);
				
				primaryStage.setScene(scene1);
		        primaryStage.setTitle("Stock Management System");
				primaryStage.show();
				}
		
		//add refrigerator type product into array list
		public void AddRef(Stage primaryStage) {

			Label label = new Label();
			
			TextField name = new TextField();
			TextField design = new TextField();
			TextField color = new TextField();
			TextField capacity = new TextField();
			TextField quantity = new TextField();
			TextField price = new TextField();
			TextField itemNo = new TextField();
			
			GridPane gridPane = new GridPane();
			gridPane.setAlignment(Pos.CENTER);
			gridPane.setPadding(new Insets(15));
			gridPane.setHgap(8);
			gridPane.setVgap(8);
			
			gridPane.add(name, 1, 1);
			gridPane.add(new Label("Name:"), 0, 1);
			gridPane.add(design, 1, 2);
			gridPane.add(new Label("Door design:"), 0, 2);
			gridPane.add(color, 1, 3);
			gridPane.add(new Label("Color:"), 0, 3);
			gridPane.add(capacity, 1, 4);
			gridPane.add(new Label("Capacity:"), 0, 4);
			gridPane.add(quantity, 1, 5);
			gridPane.add(new Label("Quantity available in stock: "), 0, 5);
			gridPane.add(price, 1, 6);
			gridPane.add(new Label("Price:(RM)"), 0, 6);
			gridPane.add(itemNo, 1, 7);
			gridPane.add(new Label("Item number:"), 0, 7);
			
			//set images
	        ImageView ref = new ImageView("Images/ref.jpg");
	        ref.setFitWidth(200); 
	        ref.setFitHeight(300); 
	        
	        Label imageLabel = new Label();
		    imageLabel.setPrefSize(200,  100);
		    imageLabel.setGraphic(ref);
		    imageLabel.setAlignment(Pos.CENTER_RIGHT);
		    
		    VBox vbox = new VBox();
		    vbox.getChildren().add(imageLabel);
		    
		    Button back = new Button("Back");
		    back.setOnAction(e -> {
		    	refCount--;
		    	AddProd(primaryStage);
		    });
		    
			Button next = new Button("Next");
			next.setOnAction(e -> {
				
				String n = new String(), d = null, c = null;
			    int q = 0, i = 0;
			    double cap = 0,p = 0;
			    
			    try {
			    n = name.getText();
			    d = design.getText();
			    c = color.getText();
			    cap = Double.parseDouble(capacity.getText());
			    q = Integer.parseInt(quantity.getText());
			    i = Integer.parseInt(itemNo.getText());
			    p = Double.parseDouble(price.getText());
			    
			    boolean isDuplicate = checkDuplication(product, i);
			    int result = isDuplicate ? 1 : 0; // Convert boolean to integer (1 for true, 0 for false)

			    switch (result) {
			    
		        	case 0:
					Product products = new Refrigerator(i, n, q, p, true, d, c, cap);
					product.add(products);
					
						if (maxNum > 0) {
						maxNum--;
							if(maxNum == 0)
								menu(primaryStage);
							else
								AddProd(primaryStage);
						}
						
						
		        	break;
			        case 1:
			            label.setText("//Duplicate item number. Please re-enter item number.");
			            label.setStyle("-fx-text-fill: red");
			            break;
			    	}
			    }
			    
			    
			    //exception handling for inputs
			    catch(NumberFormatException ex) {
					label.setText("//invalid input, please insert proper value");
					label.setStyle("-fx-text-fill: red");

				}
	   
			});
			
			refCount++;
			VBox vb = new VBox();
			vb.getChildren().addAll(gridPane, label);
			HBox hbox = new HBox();
			hbox.setSpacing(10); 
	        hbox.setAlignment(Pos.CENTER); 
	        
	        //let the first button move to left
	        Region leftRegion = new Region();
	        HBox.setHgrow(leftRegion, Priority.ALWAYS);
	        
			hbox.getChildren().addAll(back,leftRegion,next);
			BorderPane borderPane = new BorderPane();
			Text Details = new Text("Please enter the details of product Refrigerator " + refCount + ": ");
			Details.setFont(Font.font("Times", FontWeight.BOLD, 15));
			borderPane.setTop(Details);
			borderPane.setPadding(new Insets(15));
			borderPane.setCenter(vb);
			borderPane.setBottom(hbox);
			borderPane.setRight(imageLabel);
			
			
			Scene scene1 = new Scene(borderPane);
			primaryStage.setScene(scene1);
	        primaryStage.setTitle("Stock Management System");
			primaryStage.show();
		}
		
		//add TV type product into array list

		public void AddTV(Stage primaryStage) {

			Label label = new Label();
			
			TextField pctName = new TextField();
			TextField scrType = new TextField();
			TextField resolution = new TextField();
			TextField dispSize = new TextField();
			TextField quantity = new TextField();
			TextField price = new TextField();
			TextField itemNo = new TextField();
			
			GridPane gridPane = new GridPane();
			gridPane.setAlignment(Pos.CENTER);
			gridPane.setPadding(new Insets(15));
			gridPane.setHgap(8);
			gridPane.setVgap(8);
			gridPane.add(pctName, 1, 1); 
			gridPane.add(new Label("Product pctName:"), 0, 1);
			gridPane.add(scrType, 1, 2);
			gridPane.add(new Label("Screen type:"), 0, 2);
			gridPane.add(resolution, 1, 3);
			gridPane.add(new Label("Resolution:"), 0, 3);
			gridPane.add(dispSize, 1, 4);
			gridPane.add(new Label("Display size:"), 0, 4);
			gridPane.add(quantity, 1, 5);
			gridPane.add(new Label("Quantity available in stock: "), 0, 5);
			gridPane.add(price, 1, 6);
			gridPane.add(new Label("Price:(RM)"), 0, 6);
			gridPane.add(itemNo, 1, 7);
			gridPane.add(new Label("Item number:"), 0, 7);
			
			//Add image
	        ImageView TV = new ImageView("Images/TV.jpg");
	        TV.setFitWidth(300); // Adjust as needed
	        TV.setFitHeight(200); // Adjust as needed



	        Label imageLabel = new Label();
		    imageLabel.setPrefSize(200,  300);
		    imageLabel.setGraphic(TV);
		    imageLabel.setAlignment(Pos.CENTER_RIGHT);
		    
		    VBox vbox = new VBox();
		    vbox.getChildren().add(imageLabel);
		    
		    Button back = new Button("Back");
		    back.setOnAction(e -> {
		    	tvCount--;
		    	AddProd(primaryStage);
		    });
		    
			Button next = new Button("Next");
			next.setOnAction(e -> {
				
				String n = new String();
				String s = new String(); // null
			    String r = new String();
				int d = 0, q = 0, i = 0;
			    double p = 0;
			    
			    try {
			    n = pctName.getText();
			    s = scrType.getText();
			    r = resolution.getText();
			    d = Integer.parseInt(dispSize.getText());
			    q = Integer.parseInt(quantity.getText());
			    i = Integer.parseInt(itemNo.getText());
			    p = Double.parseDouble(price.getText());
			    //check duplication
			    boolean isDuplicate = checkDuplication(product, i);
			    int result = isDuplicate ? 1 : 0; // Convert boolean to integer (1 for true, 0 for false)

			    switch (result) {
			    
			    	case 0:
					Product products = new TV(i, n, q, p , true, s, r, d);
					product.add(products);
					
					if (maxNum > 0) {
						maxNum--;
						
						if(maxNum == 0)
							menu(primaryStage);
						else
							AddProd(primaryStage);
					}

		        	break;
		        	
			        case 1:
			            label.setText("//Duplicate item number. Please re-enter item number.");
			            label.setStyle("-fx-text-fill: red");
			            break;

			    	}
			    }
			    //exception handling
			    catch(NumberFormatException ex) {
					label.setText("//invalid input, please insert proper value");
					label.setStyle("-fx-text-fill: red");
				};			
			});
			
			tvCount++;
			VBox vb = new VBox();
			vb.getChildren().addAll(gridPane, label);
			HBox hbox = new HBox();
			hbox.setSpacing(10); 
	        hbox.setAlignment(Pos.CENTER); 
	        
	        //let the first button move to left
	        Region leftRegion = new Region();
	        HBox.setHgrow(leftRegion, Priority.ALWAYS);
	        
			hbox.getChildren().addAll(back,leftRegion,next);
			BorderPane borderPane = new BorderPane();
			Text Details = new Text("Please enter the details of product TV " + tvCount + ": ");
			Details.setFont(Font.font("Times", FontWeight.BOLD, 15));
			borderPane.setTop(Details);
			borderPane.setPadding(new Insets(15));
			borderPane.setCenter(vb);
			borderPane.setBottom(hbox);
			borderPane.setRight(vbox);
			
			
			Scene scene1 = new Scene(borderPane);
			primaryStage.setScene(scene1);
	        primaryStage.setTitle("Stock Management System");
			primaryStage.show();
		}
		
		//Add washing machine type product into array list
		public void AddWash(Stage primaryStage) {

			Label label = new Label();
			
			TextField name = new TextField();
			TextField type = new TextField();
			TextField eConsumption = new TextField();
			TextField size = new TextField();
			TextField quantity = new TextField();
			TextField price = new TextField();
			TextField itemNo = new TextField();
			
			GridPane gridPane = new GridPane();
			gridPane.setAlignment(Pos.CENTER);
			gridPane.setPadding(new Insets(15));
			gridPane.setHgap(8);
			gridPane.setVgap(8);
			
			gridPane.add(name, 1, 1);
			gridPane.add(new Label("Name:"), 0, 1);
			gridPane.add(type, 1, 2);
			gridPane.add(new Label("Type:"), 0, 2);
			gridPane.add(eConsumption, 1, 3);
			gridPane.add(new Label("Electricity Consumption:"), 0, 3);
			gridPane.add(size, 1, 4);
			gridPane.add(new Label("Size:"), 0, 4);
			gridPane.add(quantity, 1, 5);
			gridPane.add(new Label("Quantity available in stock: "), 0, 5);
			gridPane.add(price, 1, 6);
			gridPane.add(new Label("Price:(RM)"), 0, 6);
			gridPane.add(itemNo, 1, 7);
			gridPane.add(new Label("Item number:"), 0, 7);
			
			//set images
	        ImageView wash = new ImageView("Images/Washer.jpeg");
	        wash.setFitWidth(200); 
	        wash.setFitHeight(300); 
	        
	        Label imageLabel = new Label();
		    imageLabel.setPrefSize(200,  100);
		    imageLabel.setGraphic(wash);
		    imageLabel.setAlignment(Pos.CENTER_RIGHT);
		    
		    VBox vbox = new VBox();
		    vbox.getChildren().add(imageLabel);
		    
		    Button back = new Button("Back");
		    back.setOnAction(e -> {
		    	washCount--;
		    	AddProd(primaryStage);
		    });
		    
			Button next = new Button("Next");
			next.setOnAction(e -> {
				
				String n = new String(), t = null, c = null;
			    int q = 0, i = 0;
			    double siz = 0,p = 0;
			    
			    try {
			    n = name.getText();
			    t = type.getText();
			    c = eConsumption.getText();
			    siz = Double.parseDouble(size.getText());
			    q = Integer.parseInt(quantity.getText());
			    i = Integer.parseInt(itemNo.getText());
			    p = Double.parseDouble(price.getText());
			    
			    boolean isDuplicate = checkDuplication(product, i);
			    int result = isDuplicate ? 1 : 0; // Convert boolean to integer (1 for true, 0 for false)

			    switch (result) {
			    
		        	case 0:
					Product products = new WashingMachine(i, n, q, p, true, t, c, siz);
					product.add(products);
					
						if (maxNum > 0) {
						maxNum--;
							if(maxNum == 0)
								menu(primaryStage);
							else
								AddProd(primaryStage);
						}
						
						
		        	break;
			        case 1:
			            label.setText("//Duplicate item number. Please re-enter item number.");
			            label.setStyle("-fx-text-fill: red");
			            break;
			    	}
			    }
			    
			    
			    //exception handling for inputs
			    catch(NumberFormatException ex) {
					label.setText("//invalid input, please insert proper value");
					label.setStyle("-fx-text-fill: red");

				}
	   
			});
			
			washCount++;
			VBox vb = new VBox();
			vb.getChildren().addAll(gridPane, label);
			HBox hbox = new HBox();
			hbox.setSpacing(10); 
	        hbox.setAlignment(Pos.CENTER); 
	        
	        //let the first button move to left
	        Region leftRegion = new Region();
	        HBox.setHgrow(leftRegion, Priority.ALWAYS);
	        
			hbox.getChildren().addAll(back,leftRegion,next);
			BorderPane borderPane = new BorderPane();
			Text Details = new Text("Please enter the details of product Washing Machine " + washCount + ": ");
			Details.setFont(Font.font("Times", FontWeight.BOLD, 15));
			borderPane.setTop(Details);
			borderPane.setPadding(new Insets(15));
			borderPane.setCenter(vb);
			borderPane.setBottom(hbox);
			borderPane.setRight(imageLabel);
			
			
			Scene scene1 = new Scene(borderPane);
			primaryStage.setScene(scene1);
	        primaryStage.setTitle("Stock Management System");
			primaryStage.show();
		}
		
		//Add stove type product to array list
		public void AddStove(Stage primaryStage) {

			Label label = new Label();
			
			TextField name = new TextField();
			TextField burnerNum = new TextField();
			TextField color = new TextField();
			TextField material = new TextField();
			TextField quantity = new TextField();
			TextField price = new TextField();
			TextField itemNo = new TextField();
			
			GridPane gridPane = new GridPane();
			gridPane.setAlignment(Pos.CENTER);
			gridPane.setPadding(new Insets(15));
			gridPane.setHgap(8);
			gridPane.setVgap(8);
			
			gridPane.add(name, 1, 1);
			gridPane.add(new Label("Name:"), 0, 1);
			gridPane.add(burnerNum, 1, 2);
			gridPane.add(new Label("Number of burners:"), 0, 2);
			gridPane.add(color, 1, 3);
			gridPane.add(new Label("Color:"), 0, 3);
			gridPane.add(material, 1, 4);
			gridPane.add(new Label("Material:"), 0, 4);
			gridPane.add(quantity, 1, 5);
			gridPane.add(new Label("Quantity available in stock: "), 0, 5);
			gridPane.add(price, 1, 6);
			gridPane.add(new Label("Price:(RM)"), 0, 6);
			gridPane.add(itemNo, 1, 7);
			gridPane.add(new Label("Item number:"), 0, 7);
			
			//set images
	        ImageView ref = new ImageView("Images/Stove.jpg");
	        ref.setFitWidth(200); 
	        ref.setFitHeight(300); 
	        
	        Label imageLabel = new Label();
		    imageLabel.setPrefSize(200,  100);
		    imageLabel.setGraphic(ref);
		    imageLabel.setAlignment(Pos.CENTER_RIGHT);
		    
		    VBox vbox = new VBox();
		    vbox.getChildren().add(imageLabel);
		    
		    Button back = new Button("Back");
		    back.setOnAction(e -> {
		    	stoveCount--;
		    	AddProd(primaryStage);
		    });
		    
			Button next = new Button("Next");
			next.setOnAction(e -> {
				
				String n = new String(), c = null, m=null;
			    int q = 0, i = 0,b = 0;
			    double p = 0;
			    
			    try {
			    n = name.getText();
			    b = Integer.parseInt(burnerNum.getText());
			    c = color.getText();
			    m = material.getText();
			    q = Integer.parseInt(quantity.getText());
			    i = Integer.parseInt(itemNo.getText());
			    p = Double.parseDouble(price.getText());
			    
			    boolean isDuplicate = checkDuplication(product, i);
			    int result = isDuplicate ? 1 : 0; // Convert boolean to integer (1 for true, 0 for false)

			    switch (result) {
			    
		        	case 0:
					Product products = new Stove(i, n, q, p, true, b, c, m);
					product.add(products);
					
						if (maxNum > 0) {
						maxNum--;
							if(maxNum == 0)
								menu(primaryStage);
							else
								AddProd(primaryStage);
						}
						
						
		        	break;
			        case 1:
			            label.setText("//Duplicate item number. Please re-enter item number.");
			            label.setStyle("-fx-text-fill: red");
			            break;
			    	}
			    }
			    
			    
			    //exception handling for inputs
			    catch(NumberFormatException ex) {
					label.setText("//invalid input, please insert proper value");
					label.setStyle("-fx-text-fill: red");

				}
	   
			});
			
			stoveCount++;
			VBox vb = new VBox();
			vb.getChildren().addAll(gridPane, label);
			HBox hbox = new HBox();
			hbox.setSpacing(10); 
	        hbox.setAlignment(Pos.CENTER); 
	        
	        //let the first button move to left
	        Region leftRegion = new Region();
	        HBox.setHgrow(leftRegion, Priority.ALWAYS);
	        
			hbox.getChildren().addAll(back,leftRegion,next);
			BorderPane borderPane = new BorderPane();
			Text Details = new Text("Please enter the details of product Stove " + stoveCount + ": ");
			Details.setFont(Font.font("Times", FontWeight.BOLD, 15));
			borderPane.setTop(Details);
			borderPane.setPadding(new Insets(15));
			borderPane.setCenter(vb);
			borderPane.setBottom(hbox);
			borderPane.setRight(imageLabel);
			
			
			Scene scene1 = new Scene(borderPane);
			primaryStage.setScene(scene1);
	        primaryStage.setTitle("Stock Management System");
			primaryStage.show();
		}
		


		//display the menu of stock managing action
				public void menu(Stage primaryStage) //display
			    {

			      GridPane gridPane = new GridPane();
			      gridPane.setPadding(new Insets(10));
			      gridPane.setAlignment(Pos.CENTER);
			      gridPane.setVgap(8);
			      gridPane.setHgap(8);
			      ImageView logoImageView = new ImageView("Images/LOGO.jpg");
			      logoImageView.setFitWidth(300); // Adjust as needed
			      logoImageView.setFitHeight(200); // Adjust as needed



			      // Add the logo image to the top of the BorderPane
			      Label imageLabel = new Label();
				  imageLabel.setPrefSize(200,  100);
				  imageLabel.setGraphic(logoImageView);
				  imageLabel.setAlignment(Pos.CENTER);
				  
			      Label label = new Label();
			      
			      Label view = new Label("1. View products");
			      view.setFont(Font.font("Times", FontWeight.BOLD, 15));
			      view.setTextFill(Color.ORANGE);
			      Label add = new Label("2. Add stock");
			      add.setFont(Font.font("Times", FontWeight.BOLD, 15));
			      add.setTextFill(Color.RED);
			      Label deduct = new Label("3. Deduct stock");
			      deduct.setFont(Font.font("Times", FontWeight.BOLD, 15));
			      deduct.setTextFill(Color.CYAN);
			      Label disc = new Label("4. Discontinue product");
			      disc.setFont(Font.font("Times", FontWeight.BOLD, 15));
			      disc.setTextFill(Color.GREEN);
			      Label exit = new Label("0. Exit");
			      exit.setFont(Font.font("Times", FontWeight.BOLD, 15));
			      exit.setTextFill(Color.PURPLE);
			      Label option = new Label("Please select a menu option: ");
			      option.setFont(Font.font("Times", FontWeight.BOLD, 15));
			      
			      
			      TextField text = new TextField();
			      text.setPrefColumnCount(2);
			      
			        text.setOnAction(e -> {
					try {
						String num = text.getText();
						choice = Integer.parseInt(num);
						label.setText(" ");
			            switch (choice) {
			            
		                case 1:
		                	DispCont(product, primaryStage);
		                    break;
		                    
		                case 2:
		                	AddStock(product, primaryStage);
		                    break;
		                    
		                case 3:
		                	DeductStock(product, primaryStage);
		                    break;
		                    
		                case 4:
		                	SetStatus(product, primaryStage);

		                    break;
		                    
		                case 0:
		                	exit(primaryStage);
		                    break;
		                    
		                default:
		                	label.setText("Invalid input! Please enter a number between 0 and 4.");
		                	label.setStyle("-fx-text-fill: red");
		                    break;
			            }	
					}
					
					catch(NumberFormatException ex) {
						label.setText("Invalid input, please insert an integer that between 0 and 4");
						label.setStyle("-fx-text-fill: red");
					};
					
			    });
			        
			      //in case user want to add in more product
			      Button back = new Button("Back");
			      back.setOnAction(e -> {
			    	  maxNum = 0;
			    	  GetMaxProdNum(primaryStage);
			      });
			      HBox options= new HBox();
			      options.getChildren().addAll(option,text);
			      gridPane.add(imageLabel, 0, 0);
			      gridPane.add(view,0,1);
			      gridPane.add(add,0,2);
			      gridPane.add(deduct,0,3);
			      gridPane.add(disc,0,4);
			      gridPane.add(exit,0,5);
			      gridPane.add(options,0,6);
			      gridPane.add(back,0,7);
			      gridPane.add(label, 0, 8);
			      

			    

			      Scene scene = new Scene(gridPane, 400, 500);
			      primaryStage.setTitle("Menu Display");
			      primaryStage.setScene(scene);
			      primaryStage.show();
			        
			    }
				
		//display detail of all inserted products
	public void DispCont(ArrayList<Product> products, Stage primaryStage) 
		    {
	         	String type = " ";
		        StringBuilder sb = new StringBuilder();
		        int tvCount = 0, refCount = 0, washCount=0;
		        
		        for (int i = 0; i < products.size(); i++) 
		        {
		        	int Count = 0;
		            Object product = products.get(i);

		            switch (product.getClass().getSimpleName()) {
		            
		                case "Refrigerator":
		                    refCount++;
		                    Count = refCount;
		                    type = "Refrigerator";
		                    break;
		                    
		                case "TV":
		                    tvCount++;
		                    Count = tvCount;
		                    type = "TV";
		                    break;
		                    
		                case "Washing Machine":
		                    washCount++;
		                    Count = washCount;
		                    type = "Washing Machine";
		                    break;
		            }

	            sb.append(type + " ").append(Count).append("\n").append(products.get(i).toString()).append("\n").append("\n");

		        }

		        Text list = new Text(sb.toString());
		        list.setFont(Font.font("Miriam Fixed",FontWeight.NORMAL,17));
		        GridPane content = new GridPane();
		        content.setPadding(new Insets(10));
		        
		        //to afford long list of product
		        ScrollPane pane = new ScrollPane();
		        pane.setContent(list);
		        pane.setPrefSize(380, 270);
		        
		        Button bt = new Button("OK");
		        bt.setOnAction(e -> menu(primaryStage));
		        
		        content.add(pane, 0, 0);
		        content.add(bt, 0, 1);
		        
		        
		        Scene scene = new Scene(content, 400, 300);
			    primaryStage.setTitle("Product Display");
			    primaryStage.setScene(scene);
			    primaryStage.show();
		        
		        
		    }

		//display product to let user choose which to make changes on
	public static Product DispProduct(ArrayList<Product> products, Stage primaryStage) {
		    List<String> choices = new ArrayList<>();
		    for (Product product : products) {
		        choices.add(product.getProductName());
		    }

		    ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0), choices);
		    dialog.setTitle("Select a Product");
		    dialog.setHeaderText("Select a Product to Update");
		    dialog.setContentText("Choose a product:");

		    Optional<String> result = dialog.showAndWait();
		    if (result.isPresent()) {
		    	for (Product product : products) {
		    	if(product.getStatus()== true) {
		        String selected = result.get();
		        for (int i = 0; i < products.size(); i++) {
		            if (products.get(i).getProductName().equals(selected)) {
		                	return products.get(i);
		                }
		            }
		        }
		    	else {
		            Alert alert = new Alert(AlertType.ERROR);
		            alert.setTitle("Error");
		            alert.setHeaderText("FAILED INPUT");
		            alert.setContentText("The product is DISCONTINUED!");
		            alert.showAndWait();
		    	}
		     }
		   }
		    
		    
		    	
		    
		            
		    return null;
		}

		



	        
		//add stock of the chosen product
		public static void AddStock(ArrayList<Product> products, Stage primaryStage) {
		    Product p = DispProduct(products, primaryStage);
		    if (p != null) {
		        TextInputDialog dialog = new TextInputDialog();
		        dialog.setTitle("Add Stock");
		        dialog.setHeaderText("Enter Stock Values to Add");
		        dialog.setContentText("Enter stock values:");
		        Optional<String> result = dialog.showAndWait();
		        if (result.isPresent()) {
		        	//handle exception for input
		            try {
		                int newval = Integer.parseInt(result.get());
		                p.setQtyAvail(p.getQtyAvail() + newval);
		                
		                
		                if (newval < 0) {
			                throw new NumberFormatException();
			            }
		            } catch (NumberFormatException e) {
		                Alert alert = new Alert(AlertType.ERROR);
		                alert.setTitle("Invalid Input");
		                alert.setHeaderText("Invalid Input");
		                alert.setContentText("Please enter a valid integer value.");
		                alert.showAndWait();
		                //return to add stock pane
		                AddStock(products, primaryStage);
		            }
		        }
		    }
		}

		//deduct stock of the chosen product
		public static void DeductStock(ArrayList<Product> products, Stage primaryStage) {
		    Product p = DispProduct(products, primaryStage);
		    
		    if (p != null) {
		        TextInputDialog dialog = new TextInputDialog();
		        dialog.setTitle("Deduct Stock");
		        dialog.setHeaderText("Enter Stock Values to Deducted");
		        dialog.setContentText("Enter stock values:");
		        Optional<String> result = dialog.showAndWait();
		        if (result.isPresent()) { {
		        //handle exception for input
		        try {
		            int newval = Integer.parseInt(result.get().trim());
		            

		            if (newval < 0 || newval > p.getQtyAvail()) {
		                throw new NumberFormatException();
		            }
		            // Deduct stock value
		            p.setQtyAvail(p.getQtyAvail() - newval);
		        } 
		        catch (NumberFormatException ex) {
		            Alert alert = new Alert(AlertType.ERROR);
		            alert.setTitle("Error");
		            alert.setHeaderText("Invalid input");
		            alert.setContentText("Please enter a positive value"
		            		+ " and make sure that it is smaller than the stock value.");
		            alert.showAndWait();
		            //return to deduct stock pane
		            DeductStock(products, primaryStage);
		        }
		        }
		        }
		    };
		}


		
		

	        
		public static void SetStatus(ArrayList<Product> products, Stage primaryStage) {
		    Product p = DispProduct(products, primaryStage);
		    if (p != null) {
		    	p.setStatus(false);
			    Alert alert = new Alert(AlertType.INFORMATION);
			    alert.setTitle("Status Updated");
			    alert.setHeaderText(null);
			    alert.setContentText("The status of " + p.getProductName() + " has been set to false.");
			    alert.showAndWait();
		    }
		}
		
		//exit scene
		public void exit(Stage primaryStage) {
			Text text1 = new Text("User ID : " + user.getUserID());
			Text text2 = new Text("User Name : " + user.getName());
			
			GridPane exit = new GridPane();
			Button ok = new Button("OK");
			
			ok.setOnAction(e -> Platform.exit());
			
			exit.setPadding(new Insets(10));
			exit.setAlignment(Pos.CENTER);
			exit.add(text1, 0, 0);
			exit.add(text2, 0, 1);
			exit.add(ok, 0, 2);
			
			Scene scene = new Scene(exit, 250, 100);
			primaryStage.setTitle("Check out");
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		
	    
	    //Check duplication of item number in product array list
	    public static boolean checkDuplication(ArrayList<Product> products, int ID){
	    	
	    	for (int i = 0; i < products.size(); i++) {
	    		if (products.get(i).getItemNo() == ID) {
	    			return true;
	    		}
	    	}
	    	return false;
	    }
	    



	    
	   //main function
		public static void main(String[] args) 
		{
			launch(args);

	    }

	}