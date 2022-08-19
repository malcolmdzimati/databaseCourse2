module prac2 {
	requires objectdb;
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.media;
	requires javafx.base;
	requires javafx.web;
	requires javafx.swing;
	requires javafx.fxml;
	requires java.sql;
	requires javax.persistence;
	
	opens working to javafx.graphics;
}