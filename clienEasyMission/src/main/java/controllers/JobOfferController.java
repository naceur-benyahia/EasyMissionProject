package controllers;

import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
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

public class JobOfferController extends AnchorPane implements Initializable{

		@FXML
		private Label dateP;

    	@FXML
    	private Label BeginH;
    
    	@FXML
    	private Label lCity;

    	@FXML
    	private Label lContractType;
    	@FXML
    	private Label lTitle;

    	@FXML
    	private Label lType;
    	 @FXML
    	 private Label competence;

    	 @FXML
    	 private Label hPerMonth;
    	 @FXML
    	 private Label exigence;
    	 @FXML
    	 private Label studies;

    	@FXML
    	private Label maxS;
    	@FXML
    	private Label owner;
    	@FXML
    	private Label advantage;
    	@FXML
    	private Label minS;

    	@FXML
    	private Label finishH;

    	@FXML
    	private Label experience;
	    @FXML
	    private ImageView imgv;

	    @FXML
	    private JFXDrawer drawer;

	    @FXML
	    private VBox box;

	    @FXML
	    private JFXHamburger hamburger;
	    
	    @FXML
	    private JFXButton forum;
	    @FXML
	    private TableColumn<JobOffer,Date> date;
	    @FXML
	    private JFXButton valid;
	   
	    @FXML
	    private TableView<JobOffer> JobOfferTable;
	    @FXML
	    private TableColumn<JobOffer,String> approuved;
	    @FXML
	    private TableColumn<JobOffer,String> city;

	    @FXML
	    private TableColumn<JobOffer,String> contractType;

	    @FXML
	    private TableColumn<JobOffer,String> title;
	    
	    @FXML
	    private JFXTextField search;
	    @FXML
	    private Label Sector;
	    @FXML
	    private JFXTextArea rejectionCause;
	    @FXML
	    private JFXButton reject;
	    @FXML
	    private JFXButton approuve;
	    @FXML
	    private JFXComboBox<String> items;
	    @FXML
	    private JFXComboBox<String> sortContractType;
	    @FXML
	    private JFXComboBox<String> sortType;
	    @FXML
	    private TableColumn<JobOffer,String> type;
	    @FXML
	    private JFXButton notification;
	    @FXML
	    private JFXButton event;
	    @FXML
	    private JFXButton message;
	    @FXML
	    private JFXButton logOut;
	    ObservableList<JobOffer> data;
    
     MainFx application; 
    public MainFx getApplication() {
        return application;
    }

    public void setApplication(MainFx application) {
        this.application = application;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	search.setStyle("-fx-text-inner-color: white;");
    	
    	 drawer.setSidePane(box);
    	//////////
    	 ObservableList<String> item = FXCollections.observableArrayList("All","waiting for approuval");
	     items.setItems(item);
	    //////////
	     ObservableList<String> typeSort = FXCollections.observableArrayList("parttime","fulltime","flextime","intership");
	     sortType.setItems(typeSort);
	        
    	////////////
	     ObservableList<String> contractTypeSort = FXCollections.observableArrayList("unprofessional","professional");
	     sortContractType.setItems(contractTypeSort);
    	/////
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
        ////////////////////
       
			title.setCellValueFactory(new PropertyValueFactory<JobOffer, String>("title"));
			date.setCellValueFactory(new PropertyValueFactory<JobOffer, Date>("PostDate"));
			city.setCellValueFactory(new PropertyValueFactory<JobOffer, String>("city"));
			contractType.setCellValueFactory(new PropertyValueFactory<JobOffer, String>("ContractType"));
			type.setCellValueFactory(new PropertyValueFactory<JobOffer, String>("type"));
			approuved.setCellValueFactory(new PropertyValueFactory<JobOffer,String>("approuved"));
			

		
        
        }
    @FXML
    void forum(ActionEvent event) {
    	application.gotoForum();
    }
    @FXML
    void search(KeyEvent event) throws NamingException {
    	 	InitialContext ctx;
			ctx = new InitialContext();
			JobOfferEJBRemote proxy;
			proxy = (JobOfferEJBRemote) ctx.lookup("/easyMission-ear/easyMission-ejb/JobOfferEJB!services.JobOfferEJBRemote");
			ObservableList<JobOffer> data = FXCollections.observableList(proxy.advancedsearchJobOffer(search.getText()));
			JobOfferTable.setItems(data);
    }
    
    @FXML
    void validation(ActionEvent event) {
    	application.gotoJobOfferValidation();
    }
    
    @FXML
    void get() {
    	int i=JobOfferTable.getSelectionModel().getSelectedIndex();
    	
    	JobOffer o = new JobOffer();
    	
    	o = JobOfferTable.getItems().get(i);
    	
    	lTitle.setText(o.getTitle());
    	lCity.setText(o.getCity());
    	dateP.setText(""+o.getPostDate());
    	lContractType.setText(o.getContractType());
    	lType.setText(o.getType());
    	Sector.setText(o.getSector());
    	studies.setText(o.getStudyLevel());
    	experience.setText(o.getExperience());
    	owner.setText(o.getRecruiter().getFirstName()+" "+o.getRecruiter().getLastName());
    	competence.setText(o.getCompetence());
    	exigence.setText(o.getExigence());
    	advantage.setText(o.getAdvantages());
    	BeginH.setText(o.getBeginingHour());
    	finishH.setText(o.getFinishingHour());
    	hPerMonth.setText(o.getHoursPerMonth());
    	minS.setText(""+o.getMinSalary());
    	maxS.setText(""+o.getMaxSalary());

    }
    @FXML
    void approuveOffer(ActionEvent event) throws NamingException {
    	int i = JobOfferTable.getSelectionModel().getSelectedIndex();
		if( JobOfferTable.getSelectionModel().isSelected(i)){
    		InitialContext ctx;
			ctx = new InitialContext();
			JobOfferEJBRemote proxy;
			proxy = (JobOfferEJBRemote) ctx.lookup("/easyMission-ear/easyMission-ejb/JobOfferEJB!services.JobOfferEJBRemote");
			JobOffer o = JobOfferTable.getItems().get(i);
			o.setApprouved(true);
			proxy.updateJobOffer(o);
			data = FXCollections.observableArrayList(proxy.FindByState(false));
			JobOfferTable.setItems(data);
			String mail="oussama.chraief22@gmail.com";
		       String pass="esprit2016";
		       String[] to={o.getRecruiter().getEmail()};
		       String subject="From Easy Mission Administration";
		       String body="we're sending  you this mail to inform you that the job offer that you have posted '"+o.getTitle()
		       +"' was approuved. \n Cordially ";
		       SendMailTLS sendMail = new SendMailTLS();
		       sendMail.sendFromGMail(mail,pass,to,subject,body);
		        System.out.println("done");	  
		}
    	
    }

    @FXML
    void rejectOffer(ActionEvent event) throws NamingException {
    	int i = JobOfferTable.getSelectionModel().getSelectedIndex();
		if( JobOfferTable.getSelectionModel().isSelected(i)){
    		InitialContext ctx;
			ctx = new InitialContext();
			JobOfferEJBRemote proxy;
			proxy = (JobOfferEJBRemote) ctx.lookup("/easyMission-ear/easyMission-ejb/JobOfferEJB!services.JobOfferEJBRemote");
			JobOffer o = JobOfferTable.getItems().get(i);
    		String mail="oussama.chraief22@gmail.com";
	       String pass="esprit2016";
	       String[] to={o.getRecruiter().getEmail()};
	       String subject="From Easy Mission Administration";
	       String body="we're sending  you this mail to inform you that the job offer that you have posted '"+o.getTitle()
	       +"' was rejected."+"you need to change because of"+rejectionCause.getText()+ "\n Cordially ";
	       SendMailTLS sendMail = new SendMailTLS();
	       sendMail.sendFromGMail(mail,pass,to,subject,body);
	        System.out.println("done");	 
	        rejectionCause.clear();
		}
    }
    

		  @FXML
		    void Show(ActionEvent event) {
			    if(items.getSelectionModel().getSelectedItem().equals("All")){
			    	
					try {
						InitialContext ctx = new InitialContext();
						JobOfferEJBRemote proxy = (JobOfferEJBRemote) ctx
								.lookup("/easyMission-ear/easyMission-ejb/JobOfferEJB!services.JobOfferEJBRemote");
						data = FXCollections.observableArrayList(proxy.FindAllJobOffers());
						JobOfferTable.setItems(data);
						
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}}
					if(items.getSelectionModel().getSelectedItem().equals("waiting for approuval")){
						try {
							InitialContext ctx = new InitialContext();
							JobOfferEJBRemote proxy = (JobOfferEJBRemote) ctx
									.lookup("/easyMission-ear/easyMission-ejb/JobOfferEJB!services.JobOfferEJBRemote");
							data = FXCollections.observableArrayList(proxy.FindByState(false));
							JobOfferTable.setItems(data);
							
						} catch (NamingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						
			     }
		    }
		  
		 
		  
		  @FXML
		    void sortType(ActionEvent event) throws NamingException {
			  InitialContext ctx;
			  ctx = new InitialContext();
				JobOfferEJBRemote proxy;
				proxy = (JobOfferEJBRemote) ctx.lookup("/easyMission-ear/easyMission-ejb/JobOfferEJB!services.JobOfferEJBRemote");
				data = FXCollections.observableArrayList(proxy.FindByType(sortType.getSelectionModel().getSelectedItem()));
				JobOfferTable.setItems(data);
			     sortType.setPromptText("sort by");
		    }

		    @FXML
		    void sortContractType(ActionEvent event) throws NamingException {
		    	 InitialContext ctx;
				  ctx = new InitialContext();
					JobOfferEJBRemote proxy;
					proxy = (JobOfferEJBRemote) ctx.lookup("/easyMission-ear/easyMission-ejb/JobOfferEJB!services.JobOfferEJBRemote");
					data = FXCollections.observableArrayList(proxy.FindByContractType(sortContractType.getSelectionModel().getSelectedItem()));
					JobOfferTable.setItems(data);
				     sortContractType.setPromptText("sort by");
		    }
		    @FXML
		    void goToEvent(ActionEvent event) {
		    	application.gotoEvent();
		    }
		    @FXML
		    void goToMessage(ActionEvent event) {
		    	application.gotoMessage();
		    }
		    @FXML
		    void goToNotf(ActionEvent event) {
		    	application.gotoNotification();
		    }
		    @FXML
		    void goToLogin(ActionEvent event) {
		    	application.person=null;
		    	application.gotoLogin();
		    }
		    

}