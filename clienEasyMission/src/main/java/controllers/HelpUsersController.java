package controllers;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXTextField;

import clienEasyMission.Main;
import clienEasyMission.MainFx;
import entities.Message;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import services.MessageServiceEJBRemote;
import services.UserEJBRemote;

public class HelpUsersController implements Initializable {

	@FXML
	private TableColumn<User, String> colLastName;

	@FXML
	private TableColumn<User, Integer> colPhoneNumber;

	@FXML
	private TableView<User> usersView;

	@FXML
	private TableColumn<User, String> colEmail;

	@FXML
	private TableColumn<User, String> colFirstName;

	@FXML
	private TableColumn<User, String> colLogin;

	@FXML
	private JFXTextField searchNameField;

	private ObservableList<User> data;

	private MainFx main;
	private MessageConroller messageCont;
	private static String txt;

	static public String gettxt() {
		return txt;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		InitialContext ctx;
		try {

			ctx = new InitialContext();

			UserEJBRemote proxy1 = (UserEJBRemote) ctx
					.lookup("/easyMission-ear/easyMission-ejb/UserEJB!services.UserEJBRemote");

			colEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
			colFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
			colLastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
			colLogin.setCellValueFactory(new PropertyValueFactory<User, String>("login"));

			colPhoneNumber.setCellValueFactory(new PropertyValueFactory<User, Integer>("phoneNumber"));

			data = FXCollections.observableArrayList(proxy1.getAllUser());

			System.out.println(data);
			for (User message : data) {
				System.out.println(message.getLogin());
			}
			// tableView.setItems(null);
			usersView.setItems(data);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	void Messages(ActionEvent event) {
		main.gotoMessage();

	}

	@FXML
	void SelectMessages(ActionEvent event) {
		Integer selected1 = usersView.getSelectionModel().getSelectedIndex();

		if (usersView.getSelectionModel().isSelected(selected1)) {

			txt = usersView.getItems().get(selected1).getLogin();
			System.out.println(txt);
			main.gotoMessage();

		}

	}
	
	@FXML
	void searchAction(KeyEvent event) {
		InitialContext ctx;

		try {
			ctx = new InitialContext();

			UserEJBRemote proxy1 = (UserEJBRemote) ctx
					.lookup("/easyMission-ear/easyMission-ejb/UserEJB!services.UserEJBRemote");

			ObservableList<User> data = FXCollections.observableList(proxy1.getUserByfirstLastName(searchNameField.getText()));
			
			usersView.setItems(data);
						
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	public void setApp(MainFx application) {
		this.main = application;

	}

}
