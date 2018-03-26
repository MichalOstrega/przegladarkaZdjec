package przegladarka;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    public static void display(String title, String messege) {
        Stage windows = new Stage();

        windows.initModality(Modality.APPLICATION_MODAL);
        windows.setTitle(title);
        windows.setMinWidth(250);

        Label label = new Label();
        label.setText(messege);

        Button buttonStay = new Button("Nie");
        Button buttonClose = new Button("Tak");

        VBox layout = new VBox(10);
        HBox layout1 = new HBox(40);
        layout.getChildren().addAll(label,layout1);
        layout.setAlignment(Pos.CENTER);

        layout1.getChildren().addAll(buttonClose,buttonStay);
        layout1.setAlignment(Pos.CENTER);

        buttonStay.setOnAction(event -> {
            windows.close();
        });

        buttonClose.setOnAction(event -> {
            System.exit(0);
        });

        Scene scene = new Scene(layout);
        windows.setScene(scene);
        windows.showAndWait();
    }
}
