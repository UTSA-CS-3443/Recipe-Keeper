package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import model.Ingredient;


public class NewController implements Initializable{
	@FXML // fx:id="motherPane"
	BorderPane motherPane = new BorderPane();

	@FXML // fx:id="ingreName"
	TextField ingreName = new TextField();

	@FXML // fx:id="ingreQty"
	TextField ingreQty = new TextField();

	@FXML // fx:id="ingreUnit"
	TextField ingreUnit = new TextField();

	@FXML // fx:id="servingSize"
	ComboBox<String> servingSize = new ComboBox<>();

	@FXML // fx:id="addIngredient"
	Button addIngredient = new Button();

	@FXML // fx:id="delIngredient"
	Button delIngredient = new Button();

	@FXML // fx:id="addCategory"
	Button addCategory = new Button();

	@FXML // fx:id="delCategory"
	Button delCategory = new Button();

	@FXML // fx:id="Ingredients"
	TableView<Ingredient> Ingredients = new TableView<>();

	// temporary ingredients list
	ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// setup servingSize drop down menu
		servingSize.getItems().addAll("1", "2", "3");		
		servingSize.setOnAction( e -> {
			servingSize.setPromptText("Serving Size" + servingSize.getValue());
			ObservableList<Ingredient> tempIngredients = ingredients; 
			for (int i = 0; i < ingredients.size(); i++) {
				Ingredient temp = tempIngredients.get(i);
				int tempQty = temp.getQuantity();
				temp.setQuantity(tempQty * Integer.parseInt(servingSize.getValue()));
				tempIngredients.set(i, temp);
			}
			tempIngredients = ingredients;
		});

		// Ingredient column
		TableColumn<Ingredient, String> ingredientColumn = new TableColumn<>("Ingredient");
		ingredientColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		// Quantity column
		TableColumn<Ingredient, String> quantityColumn = new TableColumn<>("Quantity");
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));	

		// Quantity column
		TableColumn<Ingredient, String> unitColumn = new TableColumn<>("Unit");
		unitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));

		// Set Ingredients
		Ingredients.setItems(ingredients);
		Ingredients.getColumns().addAll(ingredientColumn, quantityColumn, unitColumn);

		// Disable if nothing enter in ingreName or ingreQty or ingreUnit is empty
		addIngredient.disabledProperty().and(ingreName.textProperty().isEqualTo("")
				.or(ingreQty.textProperty().isEqualTo("")).or(ingreUnit.textProperty().isEqualTo("")));

		// Add Ingredient
		addIngredient.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) throws IllegalArgumentException {
				try {

					if (ingreName.getText().equals(null) || ingreQty.getText().equals(null) || ingreUnit.getText().equals(null) ||
							ingreName.getText().equals("") || ingreQty.getText().equals("") || ingreUnit.getText().equals("")) 
						throw new IngredientException("One or more fields are empty");
					else if (isNumeric(ingreName.getText())) throw new IngredientException("Ingredient Name");
					else if (!isNumeric(ingreQty.getText())) throw new IngredientException("Ingredient Quantity");
					else if (isNumeric(ingreUnit.getText())) throw new IngredientException("Ingredient Unit");

					String name, unit;
					int qty;
					name = ingreName.getText();
					qty = Integer.valueOf(ingreQty.getText());
					unit = ingreUnit.getText();
					Ingredient i = new Ingredient (name, qty, unit);
					ingredients.add(i);

				} catch (IngredientException e) {  }
			}
		});

		// Delete Ingredient
		delIngredient.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					if (ingredients.size() < 1) throw new RuntimeException(); 

					ingredients.remove(ingredients.size()-1);

				} catch (RuntimeException e) {
					AlertBox.display("Warning", "Ingredient List Is Empty");
				}
			}
		});

	}

	// isNumeric method
	public static boolean isNumeric(String str)  
	{  
		try  
		{  
			Double.parseDouble(str);  
		}  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
	}

}