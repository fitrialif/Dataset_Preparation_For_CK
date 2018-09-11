package it.polito.s223833;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Controller 
{
	// the FXML button.
	@FXML private Button chooseDirectoryButton;
	
	// the FXML checkbox.
	@FXML private CheckBox colorToGrayscaleCheckBox;
	
	// the FXML textarea.
	@FXML private TextArea logTextArea;
	
	// the FXML textfield.
	@FXML private TextField sizeTextField;
	
	private Stage primaryStage=null;
	
	public Controller()
	{
		
	}
	
	//Funzione di set dello stage principale.
	public void setStage(Stage stage)
	{
		primaryStage=stage;
	}
	
	//Funzione richiamata dal bottone "Choose Directory".
	public void chooseFolder()
	{
		DirectoryChooser dirChooser = new DirectoryChooser();
		dirChooser.setTitle("Select Root Directory");
		dirChooser.setInitialDirectory(
	            new File(System.getProperty("user.home"))
	        ); 
		File selectedDirectory = dirChooser.showDialog(primaryStage);
		if(selectedDirectory!=null)
		{
			logTextArea.setText("Log:\n");
			//Istanzio un nuovo thread di preparazione dati.
			try
			{	
		     	//Leggo il numero dal textfield.
				int size=Integer.parseInt(sizeTextField.getText());
				//Istanzio il worker thread.
				PrepareData pd=new PrepareData(this,selectedDirectory.getAbsolutePath(), size);
		    	Thread myTaskThread = new Thread(pd);
		    	//Avvio il worker thread.
		     	myTaskThread.start();
				//Disabilito il button.
		     	chooseDirectoryButton.setDisable(true);
			}
			catch(Exception e)
			{
				if(sizeTextField.getText().equals(""))
				{
					logTextArea.setText("Please choose an image size before proceeding.\n");
				}
				else
				{
					logTextArea.setText("You must write an integer number before proceeding.\n");
				}
			}
		}		
	}
	
	//Funzione di scrittura testo.
	public void setText(String text)
	{
		try
		{
			logTextArea.appendText(text);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//Funzione di riabilitazione del button.
	public void enableButton()
	{
     	chooseDirectoryButton.setDisable(false);
	}
	
	//Funzione di verifica selezione checkbox.
	public boolean checkBoxSelected()
	{
		return colorToGrayscaleCheckBox.isSelected();
	}
}
