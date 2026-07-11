package org.bluebridge;

import atlantafx.base.theme.PrimerDark; // 引入深色主题
import atlantafx.base.theme.Styles;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author lingwh
 * @desc AtlantaFX组件示例
 * @date 2026/1/22 13:43
 */
public class AtlantaFXComponents extends Application {

    @Override
    public void start(Stage stage) {
        // 关键：全局应用主题
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());

        VBox root = new VBox(15);
        root.setPadding(new Insets(40));
        root.setAlignment(Pos.CENTER);

        // 普通输入框，应用了 AtlantaFX 的样式
        TextField textField = new TextField();
        textField.setPromptText("请输入用户名...");
        textField.setMaxWidth(250);

        // 使用 Styles 类添加特定的 CSS 类名
        Button successBtn = new Button("成功状态按钮");
        successBtn.getStyleClass().add(Styles.SUCCESS); // 变成绿色

        Button dangerBtn = new Button("危险操作按钮");
        dangerBtn.getStyleClass().add(Styles.DANGER); // 变成红色

        Button accentBtn = new Button("强调按钮");
        accentBtn.getStyleClass().add(Styles.ACCENT); // 变成主题强调色（蓝色）

        root.getChildren().addAll(textField, accentBtn, successBtn, dangerBtn);

        Scene scene = new Scene(root, 400, 400);
        stage.setTitle("AtlantaFX 视觉示例");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
