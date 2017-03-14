package clienEasyMission;

import java.io.InputStream;
import java.util.logging.Level;

import controllers.AdminMenuController;
import controllers.MessageConroller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainFx extends Application {
	Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		gotoAdminMenu();
		primaryStage.setTitle("EASY_MISSION");
		primaryStage.show();
	}

	public void gotoAdminMenu() {
		try {
			AdminMenuController a = (AdminMenuController) replaceSceneContent("/interfaces/AdminMenu.fxml");
			a.setApplication(this);
		} catch (Exception ex) {
			java.util.logging.Logger.getLogger(MainFx.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void gotoMessages() {
		try {
			MessageConroller a = (MessageConroller) replaceSceneContent("/interfaces/Message.fxml");
			a.setAppp(this);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/*
	 * public void gotoForum() { try { ForumController a = (ForumController)
	 * replaceSceneContent("/ressources/views/gui/Forum.fxml");
	 * a.setApplication(this); } catch (Exception ex) {
	 * java.util.logging.Logger.getLogger(MainFx.class.getName()).log(Level.
	 * SEVERE, null, ex); } }
	 */
	public static void main(String[] args) {
		launch(args);
	}

	private Initializable replaceSceneContent(String fxml) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		InputStream in = MainFx.class.getResourceAsStream(fxml);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(MainFx.class.getResource(fxml));
		AnchorPane page;
		try {
			page = (AnchorPane) loader.load(in);
		} finally {
			in.close();
		}
		Scene scene = new Scene(page);
		stage.setScene(scene);
		stage.sizeToScene();
		return (Initializable) loader.getController();
	}
}
