package org.bluebridge;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * @author lingwh
 * @desc JavaFX布局示例
 * @date 2025/8/4 18:32
 */
public class JavaFXLayouts extends Application {

    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();

        // BorderPane示例
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new Button("顶部"));
        borderPane.setBottom(new Button("底部"));
        borderPane.setLeft(new Button("左侧"));
        borderPane.setRight(new Button("右侧"));
        borderPane.setCenter(new Button("中央"));
        tabPane.getTabs().add(new Tab("BorderPane", borderPane));

        // HBox示例
        HBox hbox = new HBox(10);
        for (int i = 1; i <= 5; i++) {
            hbox.getChildren().add(new Button("按钮" + i));
        }
        tabPane.getTabs().add(new Tab("HBox", hbox));

        // VBox示例
        VBox vbox = new VBox(10);
        for (int i = 1; i <= 5; i++) {
            vbox.getChildren().add(new Button("按钮" + i));
        }
        tabPane.getTabs().add(new Tab("VBox", vbox));

        // GridPane示例
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                gridPane.add(new Button("格子(" + row + "," + col + ")"), col, row);
            }
        }
        tabPane.getTabs().add(new Tab("GridPane", gridPane));

        Scene scene = new Scene(tabPane, 500, 300);
        primaryStage.setTitle("JavaFX布局示例");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
