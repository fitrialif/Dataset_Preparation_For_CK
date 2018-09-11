package it.polito.s223833;

import org.opencv.core.Core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			//Carico il layout FXML.
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Layout.fxml"));
			//Creo un riferimento all'elemento di root così che il controller possa usarlo.
			BorderPane rootElement = (BorderPane) loader.load();
			//Creo la scena.
			Scene scene = new Scene(rootElement, 960, 540);
			//Creo lo stage con il titolo dato e con la scena creata in precedenza.
			primaryStage.setTitle("Dataset Preparation for CK+ - made by Antonio Marceddu");
			primaryStage.setScene(scene);
			//Setto l'icona.
			setIcon(primaryStage);
			//Mostro la GUI.
			primaryStage.show();	
			
			//Creo un oggetto di tipo Controller.
			Controller controller = loader.getController();	
			
			controller.setStage(primaryStage);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//Funzione che prova a settare l'icona dell'applicazione.
	private void setIcon(Stage primaryStage)
	{
		try
		{
			primaryStage.getIcons().add(new Image("/icon.png"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		//Carico la libreria nativa di OpenCV.
		//N.B. la libreria viene cercata nella cartella lib, accanto al file .jar.
		String opencvpath = System.getProperty("user.dir") + "\\lib\\";
		System.load(opencvpath + Core.NATIVE_LIBRARY_NAME + ".dll");
		
		launch(args);
	}
}
