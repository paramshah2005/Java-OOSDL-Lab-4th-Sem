import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

class Room {

    private int roomNumber;
    private String type;
    private double price;
    private boolean available;

    public Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.available = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean status) {
        this.available = status;
    }
}

public class App extends Application {

    ObservableList<Room> rooms = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {

        Button btnAddRoom = new Button("Add Room");
        Button btnViewRooms = new Button("View Rooms");
        Button btnBookRoom = new Button("Book Room");
        Button btnCheckout = new Button("Checkout");

        VBox layout = new VBox(15);

        layout.setPadding(new Insets(20));

        layout.getChildren().addAll(
                btnAddRoom,
                btnViewRooms,
                btnBookRoom,
                btnCheckout);

        Scene scene = new Scene(layout, 250, 200);

        stage.setTitle("Hotel Management System");
        stage.setScene(scene);
        stage.show();

        btnAddRoom.setOnAction(e -> openAddRoomWindow());

        btnViewRooms.setOnAction(e -> openViewRooms());

        btnBookRoom.setOnAction(e -> openBookingWindow());

        btnCheckout.setOnAction(e -> openCheckoutWindow());
    }

    void openAddRoomWindow() {

        Stage stage = new Stage();

        Label lblNumber = new Label("Room Number");
        TextField txtNumber = new TextField();

        Label lblType = new Label("Room Type");
        ComboBox<String> comboType = new ComboBox<>();

        comboType.getItems().addAll("Single", "Double", "Deluxe");

        Label lblPrice = new Label("Price");
        TextField txtPrice = new TextField();

        Button btnSave = new Button("Save");

        btnSave.setOnAction(e -> {

            int number = Integer.parseInt(txtNumber.getText());
            String type = comboType.getValue();
            double price = Double.parseDouble(txtPrice.getText());

            rooms.add(new Room(number, type, price));

            stage.close();
        });

        GridPane grid = new GridPane();

        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(lblNumber, 0, 0);
        grid.add(txtNumber, 1, 0);

        grid.add(lblType, 0, 1);
        grid.add(comboType, 1, 1);

        grid.add(lblPrice, 0, 2);
        grid.add(txtPrice, 1, 2);

        grid.add(btnSave, 1, 3);

        Scene scene = new Scene(grid, 300, 200);

        stage.setTitle("Add Room");
        stage.setScene(scene);
        stage.show();
    }

    void openViewRooms() {

        Stage stage = new Stage();

        TableView<Room> table = new TableView<>();

        TableColumn<Room, Integer> colNumber = new TableColumn<>("Room No");
        colNumber.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(
                cellData.getValue().getRoomNumber()).asObject());

        TableColumn<Room, String> colType = new TableColumn<>("Type");
        colType.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getType()));

        TableColumn<Room, Double> colPrice = new TableColumn<>("Price");
        colPrice.setCellValueFactory(cellData -> new javafx.beans.property.SimpleDoubleProperty(
                cellData.getValue().getPrice()).asObject());

        TableColumn<Room, Boolean> colAvail = new TableColumn<>("Available");
        colAvail.setCellValueFactory(cellData -> new javafx.beans.property.SimpleBooleanProperty(
                cellData.getValue().isAvailable()));

        table.getColumns().addAll(colNumber, colType, colPrice, colAvail);

        table.setItems(rooms);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.getChildren().add(table);

        Scene scene = new Scene(layout, 420, 300);

        stage.setTitle("Room List");
        stage.setScene(scene);
        stage.show();
    }

    void openBookingWindow() {

        Stage stage = new Stage();

        Label lblRoom = new Label("Room Number");
        TextField txtRoom = new TextField();

        Button btnBook = new Button("Book");

        btnBook.setOnAction(e -> {

            int roomNo = Integer.parseInt(txtRoom.getText());

            for (Room r : rooms) {

                if (r.getRoomNumber() == roomNo && r.isAvailable()) {

                    r.setAvailable(false);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Room booked successfully");
                    alert.show();

                    break;
                }
            }
        });

        VBox layout = new VBox(10);

        layout.setPadding(new Insets(20));

        layout.getChildren().addAll(lblRoom, txtRoom, btnBook);

        Scene scene = new Scene(layout, 250, 150);

        stage.setTitle("Book Room");
        stage.setScene(scene);
        stage.show();
    }

    void openCheckoutWindow() {

        Stage stage = new Stage();

        Label lblRoom = new Label("Room Number");
        TextField txtRoom = new TextField();

        Button btnCheckout = new Button("Checkout");

        btnCheckout.setOnAction(e -> {

            int roomNo = Integer.parseInt(txtRoom.getText());

            for (Room r : rooms) {

                if (r.getRoomNumber() == roomNo) {

                    r.setAvailable(true);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Room checked out");
                    alert.show();

                    break;
                }
            }
        });

        VBox layout = new VBox(10);

        layout.setPadding(new Insets(20));

        layout.getChildren().addAll(lblRoom, txtRoom, btnCheckout);

        Scene scene = new Scene(layout, 250, 150);

        stage.setTitle("Checkout");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}