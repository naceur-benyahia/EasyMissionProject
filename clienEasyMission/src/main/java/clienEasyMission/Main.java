package clienEasyMission;

import java.io.InputStream;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import controllers.HelpUsersController;
import controllers.LoginController;
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

public class Main extends Application{
	
	private Stage stage;
	private Stage stage1;
	public static Administrator person;
	
	public static void main(String[] args) {
        Application.launch(Main.class, (java.lang.String[])null);
    }
    
    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            stage.setTitle("Messages");
            
            
            gotoLogin();
            primaryStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public Stage getPrimaryStage(){
        return stage; 
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
    
    public void gotoLogin() {
    	try {
            LoginController notification = (LoginController) replaceSceneContent("/interfaces/Login.fxml");
            notification.setApplication(this);
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
	}
    
    
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
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
