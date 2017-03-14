package clienEasyMission;

import java.io.InputStream;
import java.util.logging.Level;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import controllers.AdminMenuController;
import controllers.MessageConroller;
import controllers.NotificationController;
import entities.Administrator;
import entities.Notification;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.NotificationServiceEJBRemote;
import controllers.EventController;
import controllers.HelpUsersController;
import controllers.LoginController;

public class MainFx extends Application {
	Stage stage;
	public static Administrator person;
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		gotoLogin();
		primaryStage.setTitle("EASY_MISSION");
		primaryStage.show();
	}
	public void gotoEvent() {
        try {
            EventController a = (EventController) replaceSceneContent("/interfaces/Events.fxml");
            a.setApplication(this);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MainFx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	public void gotoAdminMenu() {
		try {
			AdminMenuController a = (AdminMenuController) replaceSceneContent("/interfaces/AdminMenu.fxml");
			a.setApplication(this);
		} catch (Exception ex) {
			java.util.logging.Logger.getLogger(MainFx.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@FXML
    public void gotoMessage() {
        try {
            MessageConroller message = (MessageConroller) replaceSceneContent("/interfaces/Message.fxml");
            message.setApp(this);
            
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
    }
	public void gotoLogin() {
    	try {
            LoginController notification = (LoginController) replaceSceneContent("/interfaces/Login.fxml");
            notification.setApplication(this);
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
	}
	@FXML
    public void gotoHelp() {
        try {
        	
        	HelpUsersController help = (HelpUsersController) replaceSceneContent("/interfaces/HelpUsers.fxml");
        	
            help.setApp(this);
            
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
    }
	@FXML
    public void gotoNotification() {
        try {
    		InitialContext ctx;
    		
			try {
				ctx = new InitialContext();
			
			NotificationServiceEJBRemote proxy = (NotificationServiceEJBRemote) ctx
					.lookup("/easyMission-ear/easyMission-ejb/NotificationServiceEJB!services.NotificationServiceEJBRemote");
			for (Notification notification : proxy.getAllNotificationByUserBySeen(person)) {
				if(notification.getSeen()==false)
					{
						notification.setSeen(true);
						proxy.updateNotification(notification);
					}
			}
			

			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        	
            NotificationController notification = (NotificationController) replaceSceneContent("/interfaces/Notifications.fxml");
            notification.setApp(this);
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
