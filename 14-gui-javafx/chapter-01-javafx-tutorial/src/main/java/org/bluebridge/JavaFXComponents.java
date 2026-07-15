package org.bluebridge;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * JavaFX组件示例
 *
 * @author lingwh
 * @date 2025/8/4 18:31
 */
public class JavaFXComponents extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        // 文本框
        TextField textField = new TextField("文本框");

        // 文本区域
        TextArea textArea = new TextArea("多行文本区域");
        textArea.setPrefRowCount(5);

        // 复选框
        CheckBox checkBox = new CheckBox("复选框");

        // 单选按钮
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton radio1 = new RadioButton("选项1");
        RadioButton radio2 = new RadioButton("选项2");
        radio1.setToggleGroup(toggleGroup);
        radio2.setToggleGroup(toggleGroup);

        // 下拉列表
        ObservableList<String> items = FXCollections.observableArrayList("选项A", "选项B", "选项C");
        ComboBox<String> comboBox = new ComboBox<>(items);

        // 列表视图
        ListView<String> listView = new ListView<>();
        ObservableList<String> listItems = FXCollections.observableArrayList("项目1", "项目2", "项目3");
        listView.setItems(listItems);

        // 添加组件
        root.getChildren().addAll(textField, textArea, checkBox, radio1, radio2, comboBox, listView);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("JavaFX组件示例");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
