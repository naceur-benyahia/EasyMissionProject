package controllers;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import clienEasyMission.Main;
import clienEasyMission.MainFx;
import entities.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import services.NotificationServiceEJBRemote;

public class NotificationController implements Initializable {
	MainFx main;

	@FXML
	private VBox vbox;

	@FXML
	private TableView<Notification> notificationsView;

	@FXML
	private TableColumn<Notification, String> colContent;

	@FXML
	private TableColumn<Notification, Date> colDate;

	@FXML
	private JFXDrawer drawer;

	@FXML
	private JFXHamburger hamburger;

	private ObservableList<Notification> data;

	@SuppressWarnings({ "null", "unchecked" })
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		ContextMenu cm = new ContextMenu();
		MenuItem mi1 = new MenuItem("See");
		cm.getItems().add(mi1);

		mi1.setOnAction(new EventHandler() {

			public void handle(Event event) {

				main.gotoMessage();

			}

		});

		notificationsView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent t) {
				if (t.getButton() == MouseButton.SECONDARY) {
					cm.show(notificationsView, t.getScreenX(), t.getScreenY());
				}
			}
		});
		InitialContext ctx;

		try {
			ctx = new InitialContext();

			NotificationServiceEJBRemote proxy = (NotificationServiceEJBRemote) ctx.lookup(
					"/easyMission-ear/easyMission-ejb/NotificationServiceEJB!services.NotificationServiceEJBRemote");

			colContent.setCellValueFactory(new PropertyValueFactory<Notification, String>("content"));
			colDate.setCellValueFactory(new PropertyValueFactory<Notification, Date>("sendDate"));
			System.out.println(main.person);

			data = FXCollections.observableArrayList(proxy.getAllNotificationByUser(main.person));
			System.out.println(data);

			// tableView.setItems(null);
			notificationsView.setItems(data);

		} catch (NamingException e) {
			e.printStackTrace();
		}

		drawer.setSidePane();
	
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

		
	}

	public void setApp(MainFx application) {
		this.main = application;
	}

	@FXML
	void Messages(ActionEvent event) {
		main.gotoMessage();
	}

	@FXML
	void logOut(ActionEvent event) {
		main.gotoLogin();
	}

	@FXML
	void ShowTable(ActionEvent event) {
		
	}

	@FXML
	void clearNotifications(ActionEvent event) {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			NotificationServiceEJBRemote proxy = (NotificationServiceEJBRemote) ctx.lookup(
					"/easyMission-ear/easyMission-ejb/NotificationServiceEJB!services.NotificationServiceEJBRemote");
			notificationsView.getItems().clear();
			proxy.deleteAllNotification();
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
