package controllers;

import java.awt.Insets;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import clienEasyMission.MainFx;
import entities.JobOffer;
import entities.Topic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.JobOfferEJBRemote;
import services.TopicEJBRemote;

public class JobOfferValidationController extends AnchorPane implements Initializable{

		@FXML
		private Label unprof;
		@FXML
		private Label prof;
	    @FXML
	    private ImageView imgv;

	    @FXML
	    private ImageView retour;
	    @FXML
	    private PieChart pieStat= new PieChart();

    
     MainFx application; 
    public MainFx getApplication() {
        return application;
    }

    public void setApplication(MainFx application) {
        this.application = application;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	retour.setOnMouseClicked(a->{
			application.gotoJobOffer();
		});
    	
    	try {
			pieStat.setData(getChartData());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	InitialContext ctx;
		JobOfferEJBRemote proxy;
		try {
			ctx = new InitialContext();
			proxy = (JobOfferEJBRemote) ctx.lookup("/easyMission-ear/easyMission-ejb/JobOfferEJB!services.JobOfferEJBRemote");
			Float  a = proxy.FindMoyOfferByContractType("professional");
			Integer d=a.intValue();
			prof.setText(""+d+"%");
			Float b = proxy.FindMoyOfferByContractType("unprofessional");
			Integer c= b.intValue();
			unprof.setText(""+c+"%");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
        
    	
        }
    private ObservableList<PieChart.Data> getChartData() throws NamingException {
    	InitialContext ctx;
		ctx = new InitialContext();
		JobOfferEJBRemote proxy;
		proxy = (JobOfferEJBRemote) ctx.lookup("/easyMission-ear/easyMission-ejb/JobOfferEJB!services.JobOfferEJBRemote");
    	   List<String> type = new ArrayList<String>();
    	   type.add("parttime");
    	   type.add("fulltime");
    	   type.add("flextime");
    	   type.add("intership");
    	   
    	  ObservableList<Data> answer = FXCollections.observableArrayList();
    	   for (String  t : type){
    	             answer.add(new PieChart.Data(t, new Double(proxy.FindByType(t).size())));
    	            }
    	        return answer;

    	    }
    
}