package controllers;

import java.awt.Insets;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import clienEasyMission.MainFx;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AdminMenuController extends AnchorPane implements Initializable{


	    @FXML
	    private ImageView imgv;

	    @FXML
	    private JFXDrawer drawer;

	    @FXML
	    private VBox box;

	    @FXML
	    private JFXHamburger hamburger;
	    
	    @FXML
	    private JFXButton idforum;

	
    
     MainFx application; 
    public MainFx getApplication() {
        return application;
    }

    

    
    public void setApplication(MainFx application) {
        this.application = application;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
		
		
    	drawer.setSidePane(box);
    	
    	
    	HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
        }
    @FXML
    void forumm(ActionEvent event) {
    //	application.gotoForum();
    }
    @FXML
    void Messages(ActionEvent event) {
    	application.gotoMessages();
    }
}