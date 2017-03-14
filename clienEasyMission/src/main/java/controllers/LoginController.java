package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import clienEasyMission.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import services.UserEJBRemote;

public class LoginController implements Initializable{
	    @FXML
	    private JFXPasswordField password;

	    @FXML
	    private JFXButton signIn;

	    @FXML
	    private JFXTextField Login;

	
	Main application; 
    public Main getApplication() {
        return application;
    }

    public void setApplication(Main application) {
        this.application = application;
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	@FXML
    void connect(ActionEvent event) throws NamingException {
		
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			Object obj = ctx.lookup("/easyMission-ear/easyMission-ejb/UserEJB!services.UserEJBRemote");
			UserEJBRemote proxy = (UserEJBRemote) obj ;
			application.person = proxy.authenticate(Login.getText(), password.getText());
			if(application.person.equals(null))
			{
				application.gotoLogin();
			}
			else{
				application.gotoMessage();
				System.out.println(application.person.getLogin());
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
    }

}
