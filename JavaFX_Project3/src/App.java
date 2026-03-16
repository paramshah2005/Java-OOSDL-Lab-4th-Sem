import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        Label lblAssign = new Label("Assignment Marks:");
        Label lblMid = new Label("Midterm Marks:");
        Label lblFinal = new Label("Final Exam Marks:");

        Label lblPercent = new Label("Percentage:");
        Label lblGrade = new Label("Grade:");

        TextField txtAssign = new TextField();
        TextField txtMid = new TextField();
        TextField txtFinal = new TextField();

        TextField txtPercent = new TextField();
        txtPercent.setEditable(false);

        TextField txtGrade = new TextField();
        txtGrade.setEditable(false);

        Button btnCalculate = new Button("Calculate");
        Button btnClear = new Button("Clear");

        btnCalculate.setOnAction(e -> {

            try {

                double assign = Double.parseDouble(txtAssign.getText());
                double mid = Double.parseDouble(txtMid.getText());
                double fin = Double.parseDouble(txtFinal.getText());

                double total = assign + mid + fin;
                double percent = total / 3;

                txtPercent.setText(String.format("%.2f", percent));

                String grade;

                if (percent >= 90)
                    grade = "A";
                else if (percent >= 80)
                    grade = "B";
                else if (percent >= 70)
                    grade = "C";
                else if (percent >= 60)
                    grade = "D";
                else
                    grade = "F";

                txtGrade.setText(grade);

            } catch (Exception ex) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("Please enter numeric values.");
                alert.showAndWait();

            }

        });

        btnClear.setOnAction(e -> {

            txtAssign.clear();
            txtMid.clear();
            txtFinal.clear();
            txtPercent.clear();
            txtGrade.clear();

        });

        GridPane grid = new GridPane();

        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(12);

        grid.add(lblAssign, 0, 0);
        grid.add(txtAssign, 1, 0);

        grid.add(lblMid, 0, 1);
        grid.add(txtMid, 1, 1);

        grid.add(lblFinal, 0, 2);
        grid.add(txtFinal, 1, 2);

        grid.add(lblPercent, 0, 3);
        grid.add(txtPercent, 1, 3);

        grid.add(lblGrade, 0, 4);
        grid.add(txtGrade, 1, 4);

        grid.add(btnCalculate, 0, 5);
        grid.add(btnClear, 1, 5);

        Scene scene = new Scene(grid, 350, 250);

        stage.setTitle("Student Grade Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}