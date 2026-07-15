package org.bluebridge;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX入门示例
 *
 * @author lingwh
 * @date 2025/8/4 18:28
 */
public class HelloWorld extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 创建组件
        Label label = new Label("欢迎使用JavaFX!");
        Button button = new Button("点击我");

        // 添加事件处理
        button.setOnAction(e -> {
            label.setText("按钮被点击了!");
        });

        // 布局
        VBox root = new VBox(10); // 间距为10
        root.getChildren().addAll(label, button);

        // 创建场景
        Scene scene = new Scene(root, 300, 200);

        // 设置舞台
        primaryStage.setTitle("我的第一个JavaFX应用");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
