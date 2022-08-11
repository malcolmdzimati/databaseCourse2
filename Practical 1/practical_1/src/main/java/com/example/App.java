package com.example;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
   private Stage stage;

   public void setScene(Group root, int x, int y, String desc){
      Scene scene = new Scene(root, x, y, Color.BEIGE);
      stage.setTitle(desc);
      stage.setScene(scene);
      stage.show();
   }

   public void returnToHome(){
      setScene(homePage(), 250, 150, "Student Database Home");
   }

   public void start(Stage stag) {
      stage = stag;
      // Setting the stage
      returnToHome();
   }

   public Group homePage() {
      // Create buttons
      Button addButton = new Button("Add A Student");
      Button searchButton = new Button("Find A Student");
      Button allButton = new Button("Display All Students");

      // Align buttons
      VBox box = new VBox(5);
      box.setPadding(new Insets(25, 5, 5, 50));
      box.getChildren().addAll(addButton, searchButton, allButton);

      // Buttons action
      addButton.setOnAction(e -> {
         setScene(addStudent(), 260, 330, "Add A Student");
      });

      addButton.setOnAction(e -> {
         setScene(addStudent(), 260, 330, "Add A Student");
      });
      Group root = new Group(box);
      return root;
   }

   public Group addStudent() {
      // Creating nodes
      TextField textFieldName = new TextField();
      TextField textFieldSurname = new TextField();
      TextField textFieldDegree = new TextField();
      TextField textFieldSNumber = new TextField();
      Button button = new Button("Add");
      Button bbutton = new Button("Back");

      // button.setTranslateX(100);
      // button.setTranslateY(270);

      // Creating labels
      Label labelName = new Label("Name: ");
      Label labelSurname = new Label("Surname: ");
      Label labelDegree = new Label("Degree: ");
      Label labelSNumber = new Label("Student Number: ");

      // Setting the message with read data
      Text text = new Text("");

      // Setting font to the label
      Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10);
      text.setFont(font);
      text.setTranslateX(15);
      text.setTranslateY(125);
      text.setFill(Color.BROWN);
      text.maxWidth(580);
      text.setWrappingWidth(580);

      // Displaying the message
      bbutton.setOnAction(e -> {
         returnToHome();
      });

      // Adding labels for nodes
      VBox box = new VBox(5);
      box.setPadding(new Insets(25, 5, 5, 50));
      box.getChildren().addAll(labelName, textFieldName, labelSurname, textFieldSurname, labelDegree, textFieldDegree,
            labelSNumber, textFieldSNumber);

      // Adding buttons
      HBox buttons = new HBox(5);
      buttons.setPadding(new Insets(0, 10, 10, 50));
      buttons.getChildren().addAll(button, bbutton);
      buttons.setTranslateY(270);

      // Adding to soon
      Group root = new Group(box, buttons);

      return root;
   }

   public static void main(String args[]) {
      launch(args);
   }
}