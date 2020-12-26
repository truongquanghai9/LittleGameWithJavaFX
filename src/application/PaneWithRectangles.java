package application;

import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import java.lang.*;


public class PaneWithRectangles extends BorderPane
{
private ComboBox<String> primaryColorCombo;
private ComboBox<String> backColorCombo;
private ComboBox<String> widthCombo;

private Color primaryColor, secondaryColor, backgroundColor;
private double selectWidth;
private String color;

private Rectangle[][] rectArray;
private Rectangle r1;

private Label primary, backGround, stroke;
private GridPane grid;
public PaneWithRectangles()

//Color;
{
primaryColor = Color.BLACK;
secondaryColor = Color.GRAY;
backgroundColor = Color.WHITE;
selectWidth = 1.0;
color = "";
//Instantiate and initialize labels, combo boxes
primaryColorCombo = new ComboBox<String>();
primaryColorCombo.getItems().addAll("BLACK", "BLUE", "RED", "GREEN");
primaryColorCombo.setValue("BLACK");
primaryColorCombo.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

backColorCombo = new ComboBox<String>();
backColorCombo.getItems().addAll("WHITE", "YELLOW", "ORANGE");
backColorCombo.setValue("WHITE");
backColorCombo.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


widthCombo = new ComboBox<String>();
widthCombo.getItems().addAll("1", "3", "5", "7");
widthCombo.setValue("1");
widthCombo.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


primary = new Label("PrimaryColor");
primary.setFont(Font.font("Times New Roman",15));

backGround = new Label("BackgroundColor");
backGround.setFont(Font.font("Times New Roman",15));

stroke = new Label ("StrokeWidth");
stroke.setFont(Font.font("Times New Roman",15));



//Instantiate and initialize the two dimensional array rectArray
//to contain 7 columns and 7 rows (49 rectangles total)
//It is recommended to use nested loops
rectArray = new Rectangle[7][7];


//grid is a GridPane containing 49 rectangles.
grid = new GridPane();
//---- add 49 rectangles to the grid pane, it is recommended to use nested loops
for (int index = 0; index < rectArray.length; index++) {
	for (int index1 = 0; index1 < rectArray[1].length; index1++) {
		r1 = new Rectangle();
		r1.setStroke(Color.BLACK);
		r1.setStrokeWidth(1);
		r1.setWidth(470/7);
		r1.setHeight(390/7);
		rectArray[index1][index] = r1;
		rectArray[index1][index].setFill(Color.WHITE);
		grid.add(rectArray[index1][index], index1, index);
		grid.setHgap(0.7);
		grid.setVgap(0.55);
	}
}


//leftPane is a VBox, it should contain labels and combo boxes
VBox leftPane = new VBox();
leftPane.setSpacing(20);
leftPane.setPadding(new Insets(10, 15, 10, 0));
leftPane.getChildren().addAll(primary, primaryColorCombo, backGround, backColorCombo, stroke, widthCombo);


//add the left pane to the left of the pane
//and the grid pane contains rectangles at the center
this.setLeft(leftPane);
this.setCenter(grid);
this.setPadding(new Insets(200,300,200,300));
//register/link the source nodes with its handler objects
grid.setOnMouseDragged(new MouseHandler());
backColorCombo.setOnAction(new BackColorHandler());
widthCombo.setOnAction(new WidthHandler());
primaryColorCombo.setOnAction(new PrimColorHandler());
grid.setOnMouseReleased(new MouseHandler());
}


//Step #2(A) - MouseHandler
private class MouseHandler implements EventHandler<MouseEvent>
{
public void handle(MouseEvent event)
{
 //handle MouseEvent here
 //Note: you can use if(event.getEventType()== MouseEvent.MOUSE_DRAGGED)
 //to check whether the mouse key is dragged
 //write your own codes here
	
	if(event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
		//to set up the background color again whenever the mouse is dragged
		for (int num = 0; num < rectArray.length; num++) {
			for (int num1 = 0; num1 < rectArray[1].length; num1++) {
				rectArray[num1][num].setFill(backgroundColor);
			}
		}
		//to get the x coordinate and y coordinate
		double pointX = event.getX();
		double pointY = event.getY();
		
		//when the mouse is dragged, the color will change
	for (double index = 0, index2 =0;index < 481.9; index+=481.9/7,index2++) {
		for (double index1 = 0 , index3 = 0; index1 < 400.85; index1 += 400.85/7, index3++) {
			int x = (int)index2;
			int y = (int)index3;
			if ( (pointX >= index && pointX < index + 470/7) && (pointY >= index1 && pointY < index1 + 390/7)) {
					rectArray[x][y].setFill(primaryColor);
					if (x > 0) {
					rectArray[x-1][y].setFill(secondaryColor);
					}
					if (y > 0) {
					rectArray[x][y-1].setFill(secondaryColor);
					}
					if (x < 6) {
					rectArray[x+1][y].setFill(secondaryColor);
					}
					if (y < 6) {
					rectArray[x][y+1].setFill(secondaryColor);
					}
			}
			}
		}
	}
	//when the mouse is released, the color disappear
	if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
		for (int index = 0; index < rectArray.length; index++) {
			for (int index1 = 0; index1 < rectArray[1].length; index1++) {
				rectArray[index1][index].setFill(backgroundColor);
			}
	}
	}

}//end handle()

}//end MouseHandler


//A handler class used to handle primary and secondary colors
private class PrimColorHandler implements EventHandler<ActionEvent>
{
public void handle(ActionEvent event)
{
//write your own codes here
	color = primaryColorCombo.getValue();
	switch (color) {
	case "BLACK":
		primaryColor = Color.BLACK;
		secondaryColor = Color.GREY;
		break;
	case "BLUE": 
		primaryColor = Color.BLUE;
		secondaryColor = Color.POWDERBLUE;

		break;
	case "RED":
		primaryColor = Color.RED;
		secondaryColor = Color.PINK;
		break;
	
	case "GREEN":
		primaryColor = Color.GREEN;
		secondaryColor = Color.LIGHTGREEN;
		break;
	}
}
}//end PrimColorHandler

//A handler class used to handle background color
private class BackColorHandler implements EventHandler<ActionEvent>
{
public void handle(ActionEvent event)
{
	color = backColorCombo.getValue();
	for (int index = 0; index < rectArray.length; index++) {
		for (int index1 = 0; index1 < rectArray[1].length; index1++) {
				switch(color) {
					case "WHITE":
						backgroundColor = Color.WHITE;
						rectArray[index1][index].setFill(backgroundColor);
						break;
					case "YELLOW":
						backgroundColor = Color.YELLOW;
						rectArray[index1][index].setFill(backgroundColor);
						break;
					case "ORANGE":
						backgroundColor = Color.ORANGE;
						rectArray[index1][index].setFill(backgroundColor);		
	}
	}
	}
}
}//end BackColorHandler

//A handler class used to handle stroke width
private class WidthHandler implements EventHandler<ActionEvent>
{
public void handle(ActionEvent event)
{
	
	selectWidth = Double.parseDouble(widthCombo.getValue());
	for (int index = 0; index < rectArray.length; index++) {
		for (int index1 = 0; index1 < rectArray[1].length; index1++) {
	if (selectWidth == 1) {
		rectArray[index1][index].setStrokeWidth(1);
		//minus 1 to make the inside rectangle smaller so the pane will not change it size
		rectArray[index1][index].setWidth((470/7)-1);
		rectArray[index1][index].setHeight((390/7)-1);

	}
	else if (selectWidth == 3) {
		rectArray[index1][index].setStrokeWidth(3);
		//minus 3 to make the inside rectangle smaller so the pane will not change it size
		rectArray[index1][index].setWidth((470/7)-3);
		rectArray[index1][index].setHeight((390/7)-3);
	}
	else if (selectWidth == 5) {
		rectArray[index1][index].setStrokeWidth(5);
		//minus 5 to make the inside rectangle smaller so the pane will not change it size
		rectArray[index1][index].setWidth((470/7)-5);
		rectArray[index1][index].setHeight((390/7)-5);
	}
	else if (selectWidth == 7) {
		rectArray[index1][index].setStrokeWidth(7);
		//minus 7 to make the inside rectangle smaller so the pane will not change it size
		rectArray[index1][index].setWidth((470/7)-7);
		rectArray[index1][index].setHeight((390/7)-7);
	}
		}
	}
}
}//end WidthHandler
} //end of PaneWithRectangles
