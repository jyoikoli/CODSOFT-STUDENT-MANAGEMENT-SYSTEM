package STUDENT MANAGEMENT SYSTEM;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class StudentManagementSystemUI extends Application {
    private StudentManagementSystem studentManagementSystem;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        studentManagementSystem = new StudentManagementSystem();

        VBox root = new VBox();
        Button addButton = new Button("Add Student");
        Button searchButton = new Button("Search Student");
        Button displayButton = new Button("Display All Students");
        Button exitButton = new Button("Exit");

        root.getChildren().addAll(addButton, searchButton, displayButton, exitButton);

        addButton.setOnAction(event -> {
            // Open a new window for adding a student
            addStudentWindow();
        });

        searchButton.setOnAction(event -> {
            // Open a new window for searching a student
            searchStudentWindow();
        });

        displayButton.setOnAction(event -> {
            // Display all students
            displayAllStudents();
        });

        exitButton.setOnAction(event -> {
            // Exit the application
            primaryStage.close();
        });

        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.setTitle("Student Management System");
        primaryStage.show();
    }

    private void addStudentWindow() {
        Stage addStage = new Stage();
        VBox addRoot = new VBox();
        TextField nameField = new TextField();
        TextField rollNumberField = new TextField();
        TextField gradeField = new TextField();
        Button addButton = new Button("Add Student");

        addRoot.getChildren().addAll(new Label("Name:"), nameField,
                                      new Label("Roll Number:"), rollNumberField,
                                      new Label("Grade:"), gradeField,
                                      addButton);

        addButton.setOnAction(event -> {
            // Validate input fields
            if (validateInput(nameField.getText(), rollNumberField.getText(), gradeField.getText())) {
                String name = nameField.getText();
                int rollNumber = Integer.parseInt(rollNumberField.getText());
                String grade = gradeField.getText();
                Student newStudent = new Student(name, rollNumber, grade);
                studentManagementSystem.addStudent(newStudent);
                addStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Validation Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all required fields and enter valid data.");
                alert.showAndWait();
            }
        });

        addStage.setScene(new Scene(addRoot, 300, 200));
        addStage.setTitle("Add Student");
        addStage.show();
    }

    private void searchStudentWindow() {
        Stage searchStage = new Stage();
        VBox searchRoot = new VBox();
        TextField rollNumberField = new TextField();
        Button searchButton = new Button("Search");

        searchRoot.getChildren().addAll(new Label("Enter Roll Number:"), rollNumberField, searchButton);

        searchButton.setOnAction(event -> {
            int rollNumber = Integer.parseInt(rollNumberField.getText());
            Student searchedStudent = studentManagementSystem.searchStudent(rollNumber);
            if (searchedStudent != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search Result");
                alert.setHeaderText(null);
                alert.setContentText(searchedStudent.toString());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Search Result");
                alert.setHeaderText(null);
                alert.setContentText("Student with Roll Number " + rollNumber + " not found.");
                alert.showAndWait();
            }
            searchStage.close();
        });

        searchStage.setScene(new Scene(searchRoot, 300, 150));
        searchStage.setTitle("Search Student");
        searchStage.show();
    }

    private void displayAllStudents() {
        Stage displayStage = new Stage();
        VBox displayRoot = new VBox();
        TextArea studentsTextArea = new TextArea();
        List<Student> students = studentManagementSystem.getAllStudents();

        StringBuilder studentInfo = new StringBuilder();
        for (Student student : students) {
            studentInfo.append(student.toString()).append("\n");
        }
        studentsTextArea.setText(studentInfo.toString());
        studentsTextArea.setEditable(false);

        displayRoot.getChildren().addAll(new Label("All Students:"), studentsTextArea);

        displayStage.setScene(new Scene(displayRoot, 400, 300));
        displayStage.setTitle("All Students");
        displayStage.show();
    }

    private boolean validateInput(String name, String rollNumber, String grade) {
        return !name.isEmpty() && name.matches("[a-zA-Z]+") && !rollNumber.isEmpty() && rollNumber.matches("\\d+") && !grade.isEmpty();
    }
}

