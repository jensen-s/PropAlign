package org.jensen.propedit.gui;

import java.net.URL;
import java.util.ResourceBundle;

import org.jensen.propedit.object.Property;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainWindowController implements Initializable{
	@FXML
	private TableView<Property> propertyTable;
	
	@FXML
	protected void handleKeyInput(final InputEvent event) {
		if (event instanceof KeyEvent) {
			final KeyEvent keyEvent = (KeyEvent) event;
			if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.A) {
				provideAboutFunctionality();
			}
		}
	}

	@FXML
	protected void handleAboutAction(final ActionEvent event) {
		provideAboutFunctionality();
	}

	private void provideAboutFunctionality() {
		System.out.println("About");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<TableColumn<Property, ?>> columns = propertyTable.getColumns();
		TableColumn<Property, ?> newCol=new TableColumn<Property, String>();
		newCol.setText("Default");
		newCol.setMinWidth(50);
		columns.add(0, newCol);
	}
}
