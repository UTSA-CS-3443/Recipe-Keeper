package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
	ComboBox servingSize = new ComboBox();

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
		
		// addIngredient onClickListener
		addIngredient.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("addIngredient Press");
				try {
					if (ingreName.getText() == null || ingreQty.getText() == null || ingreUnit.getText() == null ||
						ingreName.getText() == "" || ingreQty.getText() == "" || ingreUnit.getText() == "") 
						throw new Exception();
					// else ....
					String name, unit;
					int qty;
					name = ingreName.getText();
					qty = Integer.valueOf(ingreQty.getText());
					unit = ingreUnit.getText();
					Ingredient i = new Ingredient (name, qty, unit);
					ingredients.add(i);
					
				} catch (Exception e) {
					System.out.println("One of the field is empty");
				}
			}
		});

		delIngredient.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("delIngredient Press");
			}
		});
	}

	
}
