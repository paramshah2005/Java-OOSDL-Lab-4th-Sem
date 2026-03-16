import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        Label lblAmount = new Label("Bill Amount:");
        Label lblTipPercent = new Label("Tip Percentage:");
        Label lblPeople = new Label("Number of People:");
        Label lblTip = new Label("Tip Amount:");
        Label lblTotal = new Label("Total Bill:");
        Label lblPerPerson = new Label("Per Person:");

        TextField txtAmount = new TextField();
        TextField txtPeople = new TextField("1");

        TextField txtTip = new TextField();
        txtTip.setEditable(false);

        TextField txtTotal = new TextField();
        txtTotal.setEditable(false);

        TextField txtPerPerson = new TextField();
        txtPerPerson.setEditable(false);

        Slider tipSlider = new Slider(0, 100, 10);

        Label lblPercentDisplay = new Label("10%");

        Button btnCalculate = new Button("Calculate");
        Button btnClear = new Button("Clear");

        tipSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            int percent = (int)Math.round(newVal.doubleValue());
            lblPercentDisplay.setText(percent + "%");
        });

        btnCalculate.setOnAction(e -> {

            try {

                double amount = Double.parseDouble(txtAmount.getText());
                int percent = (int)Math.round(tipSlider.getValue());
                int people = Integer.parseInt(txtPeople.getText());

                double tip = amount * percent / 100;
                double total = amount + tip;
                double perPerson = total / people;

                txtTip.setText(String.format("%.2f", tip));
                txtTotal.setText(String.format("%.2f", total));
                txtPerPerson.setText(String.format("%.2f", perPerson));

            } catch (Exception ex) {
                System.out.println("Invalid input");
            }

        });

        btnClear.setOnAction(e -> {
            txtAmount.clear();
            txtPeople.setText("1");
            txtTip.clear();
            txtTotal.clear();
            txtPerPerson.clear();
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(12);

        grid.add(lblAmount, 0, 0);
        grid.add(txtAmount, 1, 0);

        grid.add(lblTipPercent, 0, 1);
        grid.add(tipSlider, 1, 1);
        grid.add(lblPercentDisplay, 2, 1);

        grid.add(lblPeople, 0, 2);
        grid.add(txtPeople, 1, 2);

        grid.add(lblTip, 0, 3);
        grid.add(txtTip, 1, 3);

        grid.add(lblTotal, 0, 4);
        grid.add(txtTotal, 1, 4);

        grid.add(lblPerPerson, 0, 5);
        grid.add(txtPerPerson, 1, 5);

        grid.add(btnCalculate, 0, 6);
        grid.add(btnClear, 1, 6);

        Scene scene = new Scene(grid, 450, 300);

        stage.setTitle("Restaurant Tip Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}