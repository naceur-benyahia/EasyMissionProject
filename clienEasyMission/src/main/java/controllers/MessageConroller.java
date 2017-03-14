package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.sun.javafx.scene.shape.ObservableFaceArrayImpl;

import clienEasyMission.Main;
import clienEasyMission.MainFx;
import entities.Message;
import entities.Notification;
import entities.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import services.MessageServiceEJBRemote;
import services.NotificationServiceEJBRemote;
import services.UserEJBRemote;

public class MessageConroller implements Initializable {
	Main main;
	MainFx mainfx;
	AdminMenuController box;
	@FXML
	private TableView<Message> messagesView;
	@FXML
	private TableColumn<Message, String> colPerson;
	@FXML
	private TableColumn<Message, String> colContent;
	@FXML
	private TableColumn<Message, Date> colDate;
	@FXML
	private TableView<Message> messagesView1;
	@FXML
	private TableColumn<Message, String> colPerson1;
	@FXML
	private TableColumn<Message, String> colContent1;
	@FXML
	private TableColumn<Message, Date> colDate1;
	@FXML
	private JFXButton notifBtn;
	@FXML
	private JFXHamburger hamburger;
	@FXML
	private VBox vbox;
	@FXML
	private JFXDrawer drawer;
	@FXML
	private JFXTextField textField;
	@FXML
	private JFXTextArea textArea;
	@FXML
	private JFXComboBox<String> comboBox;
	@FXML
	private ImageView image;
	@FXML
	private Label loggesAs;
	@FXML
    private JFXTextField searchTxt;
	@FXML
    private Label number;
	
	private ObservableList<Message> datar;
	private ObservableList<Message> datas;

	HelpUsersController heelp;
	
	public void setApp(Main application) {
		this.main = application;
	}
	
	public void settxt(String txt) {
		textField.setText(txt);
	}
	public String gettxt() {
		return textField.getText();
	}
	
	
	public void setAppp(MainFx applicationn) {
		this.mainfx = applicationn;
	}

	@SuppressWarnings("null")
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		textField.setText(null);

		if(!(heelp.gettxt()==null)){
			textField.setText(heelp.gettxt());
			
		}
		
		loggesAs.setText(main.person.getLogin());

		drawer.setSidePane();
		InitialContext ctx;

		try {
			ctx = new InitialContext();
			NotificationServiceEJBRemote proxy = (NotificationServiceEJBRemote) ctx.lookup(
					"/easyMission-ear/easyMission-ejb/NotificationServiceEJB!services.NotificationServiceEJBRemote");
			if (proxy.getAllNotificationByUserBySeen(main.person).isEmpty()) {
				image.setVisible(false);
				
			}else number.setText(""+proxy.countNotifNotSeen(main.person));
			
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		drawer.setVisible(true);
		drawer.setSidePane(vbox);
		HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
		transition.setRate(-1);
		hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
			transition.setRate(transition.getRate() * -1);
			transition.play();
			if (drawer.isShown())
				drawer.close();
			else
				drawer.open();
		});

		textField.setStyle("-fx-text-inner-color: white;");
		textArea.setStyle("-fx-text-inner-color: white;");
		ObservableList<String> items = FXCollections.observableArrayList("Message", "Warning");

		comboBox.setItems(items);
		comboBox.setValue("Message");

		try {
			ctx = new InitialContext();

			MessageServiceEJBRemote proxy = (MessageServiceEJBRemote) ctx
					.lookup("/easyMission-ear/easyMission-ejb/" + "MessageServiceEJB!services.MessageServiceEJBRemote");

			colContent.setCellValueFactory(new PropertyValueFactory<Message, String>("content"));
			colDate.setCellValueFactory(new PropertyValueFactory<Message, Date>("date"));
			colPerson.setCellValueFactory(
					new Callback<TableColumn.CellDataFeatures<Message, String>, ObservableValue<String>>() {

						@Override
						public ObservableValue<String> call(TableColumn.CellDataFeatures<Message, String> m) {
							if (m.getValue() != null) {
								return new SimpleStringProperty(m.getValue().getSender().getLogin());
							} else {
								return new SimpleStringProperty("<no name>");
							}
						}
					});

			colContent1.setCellValueFactory(new PropertyValueFactory<Message, String>("content"));
			colDate1.setCellValueFactory(new PropertyValueFactory<Message, Date>("date"));

			datar = FXCollections.observableArrayList(proxy.findMessagesReceivedByUser(main.person));

			datas = FXCollections.observableArrayList(proxy.findMessagesSendedByUser(main.person));

			System.out.println(datar);
			for (Message message : datas) {
				System.out.println(message.getContent());
			}
			// tableView.setItems(null);
			messagesView.setItems(datar);
			messagesView1.setItems(datas);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	@FXML
	void notifications(ActionEvent event) {

		main.gotoNotification();
	}

	@FXML
	void deleteMessage(ActionEvent event) {
		InitialContext ctx;
		try {
			ctx = new InitialContext();

			MessageServiceEJBRemote proxy = (MessageServiceEJBRemote) ctx
					.lookup("/easyMission-ear/easyMission-ejb/" + "MessageServiceEJB!services.MessageServiceEJBRemote");
			Integer selected = messagesView.getSelectionModel().getSelectedIndex();
			Integer selected1 = messagesView1.getSelectionModel().getSelectedIndex();

			if (messagesView.getSelectionModel().isSelected(selected)) {

				proxy.deleteMessage(messagesView.getItems().get(selected));
				messagesView.getItems().remove(messagesView.getItems().get(selected));

			}
			if (messagesView1.getSelectionModel().isSelected(selected1)) {

				proxy.deleteMessage(messagesView1.getItems().get(selected1));
				messagesView1.getItems().remove(messagesView1.getItems().get(selected1));

			}

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void sendMessage(ActionEvent event) {
		InitialContext ctx;
		try {
			ctx = new InitialContext();

			MessageServiceEJBRemote proxy = (MessageServiceEJBRemote) ctx
					.lookup("/easyMission-ear/easyMission-ejb/" + "MessageServiceEJB!services.MessageServiceEJBRemote");

			UserEJBRemote proxy1 = (UserEJBRemote) ctx
					.lookup("/easyMission-ear/easyMission-ejb/UserEJB!services.UserEJBRemote");

			NotificationServiceEJBRemote proxy2 = (NotificationServiceEJBRemote) ctx
					.lookup("easyMission-ear/easyMission-ejb/"
							+ "NotificationServiceEJB!services.NotificationServiceEJBRemote");
			
			Message m = new Message();
			Date d = new Date();
			List<Message> listMessage = proxy.findAllMessages();
			List<User> listUser = proxy1.getAllUser();
			List<String> listLogin = new ArrayList<>();
			
			m.setDate(d);
			m.setType(comboBox.getSelectionModel().getSelectedItem());
			m.setContent(textArea.getText() + " ( "+m.getType() +" ) ");
			if(!(heelp.gettxt()==null))
				textField.setText(heelp.gettxt());
			
			for (User user : listUser) {
				listLogin.add(user.getLogin());
			}

			if (textField.getText() == null) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setContentText("Fill the textfield for Login");

				alert.showAndWait();
			} else if (!(listLogin.contains(textField.getText()))) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setContentText("Login not found");

				alert.showAndWait();
				
			} else {
				
				
					User u = proxy1.findUserByLogin(textField.getText());
				
				m.setReceiver(u);
				m.setSender(main.person);
				proxy.addMessage(m);
				Notification n = new Notification();
				if (m.getType().equals("Message"))
					n.setContent("You have received a new message from " + main.person.getFirstName() + " "
							+ main.person.getLastName());
				else
					n.setContent("You have received a Warning !");
				n.setSendDate(new Date());
				n.setUser(u);
				n.setSeen(false);
				proxy2.addNotification(n);

				textArea.clear();
				textField.clear();
				comboBox.setPromptText("Message");

			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void searchAction(KeyEvent event) {
		InitialContext ctx;

		try {
			ctx = new InitialContext();

			MessageServiceEJBRemote proxy = (MessageServiceEJBRemote) ctx
					.lookup("/easyMission-ear/easyMission-ejb/" + "MessageServiceEJB!services.MessageServiceEJBRemote");

			ObservableList<Message> data = FXCollections.observableList(proxy.advancedsearchMessage(searchTxt.getText(),main.person));
			ObservableList<Message> data1 = FXCollections.observableList(proxy.advancedsearchMessageContent(searchTxt.getText(),main.person));
			
			messagesView.setItems(data);
			messagesView1.setItems(data1);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void logOut(ActionEvent event) {
		main.gotoLogin();
		main.person = null;
	}
	
	@FXML
	void Help(ActionEvent event) {
		main.gotoHelp();
		
		
	}
}
