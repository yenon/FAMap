package UI;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import static UI.Logger.*;

/**
 * Created by Game on 01.01.2016.
 */
public class Launcher extends Application{

    public static void main(String[] args){
        Settings.init();
        try {
            logInit(Settings.logger_console_output, Settings.logger_file_output);
        } catch (IOException e) {
            return;
        }
        setNatives();
        launch();
    }

    public static void setNatives(){
        logOut("Using local natives");
        System.setProperty("org.lwjgl.librarypath", "natives");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setOnCloseRequest(event -> {
            // TODO terminate glfw
            ToolboxController.exit();
        });

        primaryStage.setWidth(Settings.tool_window_width);
        primaryStage.setHeight(Settings.tool_window_height);
        primaryStage.setX(Settings.tool_window_posx);
        primaryStage.setY(Settings.tool_window_posy);

        primaryStage.widthProperty().addListener((observable, oldValue, newValue) -> Settings.tool_window_width = newValue.intValue());
        primaryStage.heightProperty().addListener((observable, oldValue, newValue) -> Settings.tool_window_height = newValue.intValue());
        primaryStage.xProperty().addListener((observable, oldValue, newValue) -> Settings.tool_window_posx = newValue.intValue());
        primaryStage.yProperty().addListener((observable, oldValue, newValue) -> Settings.tool_window_posy = newValue.intValue());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Parent p = loader.load();
        ToolboxController controller = loader.getController();
        primaryStage.setTitle("FAHeightmap");
        primaryStage.setScene(new Scene(p));
        controller.setStage(primaryStage);
        primaryStage.show();
    }
}