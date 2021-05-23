package kaminsky.visionapi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class CloudVisionApplication extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        CloudVisionService service = new CloudVisionServiceFactory().newInstance(false);
        CloudVisionController controller = new CloudVisionController(service);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cloudvision_application.fxml"));
        loader.setController(controller);
        Parent parent = loader.load();
        parent.setStyle("-fx-background-color: WHITE;");
        Scene scene = new Scene(parent, 1000, 600);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                controller.getImageDescription();
            }
        });
        
        stage.setTitle("Vision API");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
