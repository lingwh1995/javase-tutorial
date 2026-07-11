package org.bluebridge;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

/**
 * @author lingwh
 * @desc ControlsFX组件示例
 * @date 2026/1/22 13:45
 */
public class ControlsFXComponents extends Application {

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));

        // 1. 多选下拉框 (CheckComboBox)
        CheckComboBox<String> checkComboBox = new CheckComboBox<>();
        checkComboBox.getItems().addAll("Java", "Kotlin", "Python", "Rust", "Go");
        checkComboBox.setTitle("请选择编程语言");

        // 2. 评分控件 (Rating)
        Rating rating = new Rating(5);
        rating.setUpdateOnHover(true);

        // 3. 浮窗通知 (Notifications)
        Button notifyBtn = new Button("弹出右下角通知");
        notifyBtn.setOnAction(
                e -> {
                    Notifications.create()
                            .title("任务完成")
                            .text("您选择的语言是: " + checkComboBox.getCheckModel().getCheckedItems())
                            .showConfirm();
                });

        root.getChildren().addAll(checkComboBox, rating, notifyBtn);

        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("ControlsFX 功能示例");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
