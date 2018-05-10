import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by Rosana-Constantin on 11-May-18.
 */
public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Procesare imagine");
        Button button = new Button();
        button.setText("Click me");
        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout,1200, 740);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}