package Application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			 Parent root = FXMLLoader.load(getClass().getResource(Constants.fxml_filepath + "/Landing.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
	        primaryStage.show();
	        primaryStage.setTitle("Welcome page");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
