package working;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class App extends Application {
   private Stage stage;
   private DatabaseManager db;

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
      db = new DatabaseManager();
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

      searchButton.setOnAction(e -> {
         setScene(searchStudent(), 370, 330, "Find A Student");
      });

      allButton.setOnAction(e -> {
         setScene(showStudents(), 370, 330, "All Students");
      });
      Group root = new Group(box);
      return root;
   }

   public Group searchStudent(){
      TextField searchbar = new TextField();
      Label results = new Label();
      Button searchButton = new Button("Search");
      Button backButton = new Button("Back");
      Button delete = new Button("Delete Student");
      Button edit = new Button("Edit Student");

      Text text = new Text("");
      
      Alert a = new Alert(AlertType.NONE);
      
      HBox editS = new HBox(5);
      editS.setPadding(new Insets(0, 10, 10, 30));
      editS.getChildren().addAll(edit, delete);
      editS.setVisible(false);

      // Setting font to the label
      Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10);
      text.setFont(font);
      text.setFill(Color.BROWN);
      text.maxWidth(580);
      text.setWrappingWidth(580);
      
      backButton.setOnAction(e -> {
         returnToHome();
      });
      
      searchButton.setOnAction(e -> {
    	  if(searchbar.getText().matches("\\d*") && searchbar.getText().length()==8) {
    		  text.setText(db.findStudent(Long.parseLong(searchbar.getText())));
        	  if(text.getText()!="null") {
        		  editS.setVisible(true);
        	  }
    	  }else {
    		  a.setAlertType(AlertType.ERROR);
    		  a.setContentText("Search using Student number which is an 8 digit number.");
    		  a.show();
    	  }
      });
      
      delete.setOnAction(e -> {
    	  db.deleteStudent(Long.parseLong(searchbar.getText()));
    	  returnToHome();
    	  a.setAlertType(AlertType.INFORMATION);
    	  a.setContentText("Student "+searchbar.getText()+" Succesfully deleted.");
    	  a.show();
      });
      
      edit.setOnAction(e -> {
    	  Student st = db.findStudentO(Long.parseLong(searchbar.getText()));
    	  setScene(editStudent(st.getName(), st.getSurname(), st.getDegree(), Long.toString(st.getStudentNumber())), 260, 330, "Edit A Student");
      });

      HBox search = new HBox(5);
      search.setPadding(new Insets(0, 10, 10, 30));
      search.getChildren().addAll(searchbar, searchButton, backButton);
      
      VBox res = new VBox(5);
      res.setPadding(new Insets(25, 10, 10, 10));
      res.getChildren().addAll(search, text, editS);

      Group root = new Group(res);
      return root;
   }

   public Group showStudents(){;
      Button backButton = new Button("Back");
      Button clearButton = new Button("Clear Database");

      Text text = new Text(db.displayAllStudents());

      Alert a = new Alert(AlertType.NONE);
      
      // Setting font to the label
      Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10);
      text.setFont(font);
      text.setFill(Color.BROWN);
      text.maxWidth(580);
      text.setWrappingWidth(580);
      
      backButton.setOnAction(e -> {
         returnToHome();
      });
      
      clearButton.setOnAction(e -> {
          db.clearDB();
          returnToHome();
    	  a.setAlertType(AlertType.INFORMATION);
    	  a.setContentText("All Students Succesfully deleted.");
    	  a.show();
       });

      HBox search = new HBox(5);
      search.setPadding(new Insets(25, 10, 10, 30));
      search.getChildren().addAll(backButton, clearButton);
      
      VBox res = new VBox(5);
      res.setPadding(new Insets(25, 10, 10, 10));
      res.getChildren().addAll(text, search);

      Group root = new Group(res);
      return root;
   }

   public Group addStudent() {
      // Creating nodes
      TextField textFieldName = new TextField();
      TextField textFieldSurname = new TextField();
      TextField textFieldDegree = new TextField();
      TextField textFieldSNumber = new TextField();
      Button button = new Button("Add Student");
      Button bbutton = new Button("Back");

      Alert a = new Alert(AlertType.NONE);
      // button.setTranslateX(100);
      // button.setTranslateY(270);

      // Creating labels
      Label labelName = new Label("Name: ");
      Label labelSurname = new Label("Surname: ");
      Label labelDegree = new Label("Degree: ");
      Label labelSNumber = new Label("Student Number: ");
      
      // Displaying the message
      bbutton.setOnAction(e -> {
         returnToHome();
      });
      
      button.setOnAction(e ->{
    	  if(textFieldSNumber.getText().matches("\\d*") && textFieldSNumber.getText().length()==8) {
    		  String sn = db.findStudent(Long.parseLong(textFieldSNumber.getText()));
    		  if(sn!="null") {
    			  a.setAlertType(AlertType.ERROR);
        		  a.setContentText("Student Number already in use");
        		  a.show();
    		  }else {
    			  db.addStudent(textFieldName.getText(), textFieldSurname.getText(), textFieldDegree.getText(), Long.parseLong(textFieldSNumber.getText()));
    	    	  returnToHome();
    	    	  a.setAlertType(AlertType.INFORMATION);
    	    	  a.setContentText("Student "+textFieldName.getText()+" Succesfully added.");
    	    	  a.show();
    		  }
    	  } else {
    		  a.setAlertType(AlertType.ERROR);
    		  a.setContentText("Student Number must be an 8 digit number");
    		  a.show();
    	  }
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
   
   public Group editStudent(String n, String sn, String d, String snu) {
	      // Creating nodes
	      TextField textFieldName = new TextField(n);
	      TextField textFieldSurname = new TextField(sn);
	      TextField textFieldDegree = new TextField(d);
	      Label textFieldSNumber = new Label(snu);
	      Button button = new Button("Save Changes");
	      Button bbutton = new Button("Cancel");
	      
	      Alert a = new Alert(AlertType.NONE);

	      // button.setTranslateX(100);
	      // button.setTranslateY(270);

	      // Creating labels
	      Label labelName = new Label("Name: ");
	      Label labelSurname = new Label("Surname: ");
	      Label labelDegree = new Label("Degree: ");
	      Label labelSNumber = new Label("Student Number: ");

	      // Displaying the message
	      bbutton.setOnAction(e -> {
	         returnToHome();
	      });
	      
	      button.setOnAction(e ->{
	    	  db.update( Long.parseLong(snu), textFieldName.getText(), textFieldSurname.getText(), textFieldDegree.getText());
	    	  returnToHome();
	    	  a.setAlertType(AlertType.INFORMATION);
	    	  a.setContentText("Student "+textFieldName.getText()+" Succesfully updated.");
	    	  a.show();
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
	  com.objectdb.Enhancer.enhance("working.*");
      launch(args);
   }
}