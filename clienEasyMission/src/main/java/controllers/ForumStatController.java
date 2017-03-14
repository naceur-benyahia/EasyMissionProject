package controllers;

import java.awt.TextArea;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import clienEasyMission.MainFx;
import entities.Administrator;
import entities.Comment;
import entities.Topic;
import entities.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import services.CommentEJBRemote;
import services.TopicEJBRemote;


public class ForumStatController extends AnchorPane implements Initializable{
		
		@FXML
		private Label avg;

		@FXML
	    private ImageView imgv;

		 @FXML
		 private ImageView backToForum;
	    
		 final NumberAxis xAxis = new NumberAxis();
		 final CategoryAxis yAxis = new CategoryAxis();
		 final NumberAxis xAxisC = new NumberAxis();
		 final CategoryAxis yAxisC = new CategoryAxis();
		 @FXML
		 private BarChart<String, Number> barchart = new BarChart<String, Number>( yAxis,xAxis);
		 @FXML
		 private BarChart<String, Number> barchartC = new BarChart<String, Number>( yAxisC,xAxisC);
		 @FXML
		 private JFXComboBox<String> statBox;
		 @FXML
		 private TableView<User> UserSuccess;

		  @FXML
		  private TableColumn<User, String> name;
		  @FXML
		    private TableColumn<User,Date> date;
	    
	    MainFx application; 
	    public MainFx getApplication() {
	        return application;
	    }

	    public void setApplication(MainFx application) {
	        this.application = application;
	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			
			backToForum.setOnMouseClicked(a->{
				application.gotoForum();
			});
			
			ObservableList<String> stats = FXCollections.observableArrayList("Number of forum per category" ,"Number of comments per category");
	        statBox.setItems(stats);
			
	        InitialContext ctx;		
			try {
				ctx = new InitialContext();
				TopicEJBRemote proxy = (TopicEJBRemote) ctx.lookup("/easyMission-ear/easyMission-ejb/TopicEJB!services.TopicEJBRemote");
				avg.setText(""+proxy.moyCommPerForum());
				
				List<User> users = new ArrayList<User>();
				users=proxy.userOrderByCommentsNumber();
				date.setCellValueFactory(new PropertyValueFactory<User, Date>("registrationDate"));
				name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {

				    @Override
				    public ObservableValue<String> call(TableColumn.CellDataFeatures<User, String> m) {
				        if (m.getValue() != null) {
				            return new SimpleStringProperty(m.getValue().getFirstName()+" "+m.getValue().getLastName());
				        } else {
				            return new SimpleStringProperty("<no name>");
				        }
				    }
				});
				ObservableList<User> data = FXCollections.observableArrayList(users);
				UserSuccess.setItems(data);
				
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		 @FXML
		    void statBoxFunction(ActionEvent event) throws NamingException {
			 if(statBox.getSelectionModel().getSelectedItem().equals("Number of forum per category"))
			 {	barchartC.setVisible(false);
			 	barchart.setVisible(true);
				 InitialContext ctx;		
					
						ctx = new InitialContext();
						TopicEJBRemote proxy = (TopicEJBRemote) ctx.lookup("/easyMission-ear/easyMission-ejb/TopicEJB!services.TopicEJBRemote");
						XYChart.Series series1 = new XYChart.Series();
				        series1.setName("recruitment");
				        series1.getData().add(new XYChart.Data("0,100", proxy.sortTopicByCategory("recruitment").size()));
				         
				        XYChart.Series series2 = new XYChart.Series();
				        series2.setName("Student life");
				        series2.getData().add(new XYChart.Data("0,100", proxy.sortTopicByCategory("Student life").size()));
				      
				          XYChart.Series series3 = new XYChart.Series();
				        series3.setName("Professional life");
				        series3.getData().add(new XYChart.Data("0,100", proxy.sortTopicByCategory("Professional life").size()));
				      
				          XYChart.Series series4 = new XYChart.Series();
				        series4.setName("Website quality of services");
				        series4.getData().add(new XYChart.Data("0,100",proxy.sortTopicByCategory("Website quality of services").size() ));
				      
				        barchart.getData().addAll(series1,series2,series3,series4);
				       barchart.setBackground(Background.EMPTY);
				        barchart.setTitle("Forum Number per category");
				        barchart.setStyle("-fx-background-color: transparent;");
				        
				        

			 }
				 if(statBox.getSelectionModel().getSelectedItem().equals("Number of comments per category")){
					    barchart.setVisible(false);
					    barchartC.setVisible(true);
					 	InitialContext ctx1 = new InitialContext();
						Object obj1 =ctx1.lookup("/easyMission-ear/easyMission-ejb/CommentEJB!services.CommentEJBRemote");
						CommentEJBRemote prox = (CommentEJBRemote) obj1;
						XYChart.Series series5 = new XYChart.Series();
				        series5.setName("recruitment");
				        series5.getData().add(new XYChart.Data("0,100", prox.sortCommentByCategory("recruitment").size()));
				         
				        XYChart.Series series6 = new XYChart.Series();
				        series6.setName("Student life");
				        series6.getData().add(new XYChart.Data("0,100", prox.sortCommentByCategory("Student life").size()));
				      
				          XYChart.Series series7 = new XYChart.Series();
				        series7.setName("Professional life");
				        series7.getData().add(new XYChart.Data("0,100", prox.sortCommentByCategory("Professional life").size()));
				      
				          XYChart.Series series8 = new XYChart.Series();
				        series8.setName("Website quality of services");
				        series8.getData().add(new XYChart.Data("0,100",prox.sortCommentByCategory("Website quality of services").size() ));
				      
				        barchartC.getData().addAll(series5,series6,series7,series8);
				       barchartC.setBackground(Background.EMPTY);
				        barchartC.setTitle("Comments Number per category");
				        barchartC.setStyle("-fx-background-color: transparent;");
				 }
			 
		    }
		

}		 
