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
        Label lblTipValue = new Label("Tip Amount:");
        Label lblTotal = new Label("Total Amount:");

        TextField txtAmount = new TextField();

        Slider tipSlider = new Slider(0, 100, 10);

        Label lblPercentDisplay = new Label("10%");

        TextField txtTip = new TextField();
        txtTip.setEditable(false);

        TextField txtTotal = new TextField();
        txtTotal.setEditable(false);

        tipSlider.valueProperty().addListener((obs, oldVal, newVal) -> {

            double amount = 0;

            try {
                amount = Double.parseDouble(txtAmount.getText());
            } catch (Exception e) {
                return;
            }

            int percent = (int) Math.round(newVal.doubleValue());

            lblPercentDisplay.setText(percent + "%");

            double tip = amount * percent / 100;
            double total = amount + tip;

            txtTip.setText(String.format("%.2f", tip));
            txtTotal.setText(String.format("%.2f", total));
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(15);

        grid.add(lblAmount, 0, 0);
        grid.add(txtAmount, 1, 0);

        grid.add(lblTipPercent, 0, 1);
        grid.add(tipSlider, 1, 1);
        grid.add(lblPercentDisplay, 2, 1);

        grid.add(lblTipValue, 0, 2);
        grid.add(txtTip, 1, 2);

        grid.add(lblTotal, 0, 3);
        grid.add(txtTotal, 1, 3);

        Scene scene = new Scene(grid, 420, 220);

        stage.setTitle("Tip Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}