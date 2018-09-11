package it.polito.s223833;

import java.io.File;
import javafx.application.Platform;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;


public class PrepareData implements Runnable
{
	private Controller controller;
	
	private Size imageSize;
	
	//Dimensione minima volto;
	private int absoluteFaceSize;
	
	private CascadeClassifier faceCascade;
	
	private String path;
	
	@Override
	public void run() 
	{
		searchForImages(path);		
	}	

	public PrepareData(Controller controller, String path, int size)
	{
		this.controller = controller;		
		this.path=path;
		//Dimensione minima del volto da cercare.
		absoluteFaceSize=200;
		//Dimensione dell'immagine da creare.
		imageSize = new Size(size,size);
		//istanzio l'algoritmo di face detection.
		String haarclassifierpath = System.getProperty("user.dir") + "\\lib\\";
		faceCascade = new CascadeClassifier(haarclassifierpath + "haarcascade_frontalface_alt.xml");
	}
	
	//Questa funzione cicla su tutte le directory e le subdirectory del path fornito alla ricerca di immagini.
	//Per ognuna di esse, si verifica se è presente il volto di una persona, e in caso positivo la foto viene ritagliata al solo volto.
	public void searchForImages(String path)
	{
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		Platform.runLater(() -> controller.setText("\nDirectory: "+path));
		for (File file : listOfFiles) 
		{
		    //Se ho delle immagini le analizzo e vedo se ci son volti al loro interno.
		    if (file.isFile() && (file.getName().contains(".png") || file.getName().contains(".jpg") || file.getName().contains(".jpeg"))) 
		    {
		        //Verifico se nella directory corrente ci sono persone.
		        searchForPerson(file);
		    }
		    //Se ho dei file che non sono immagini mostro un messaggio di errore nel log.
		    else if (file.isFile() && (file.getName().contains(".png")==false || file.getName().contains(".jpg")==false || file.getName().contains(".jpeg")==false))
		    {
				Platform.runLater(() -> controller.setText("\n"+file.getName() +" is not a photo or a directory!"));
		    }
		    //Se ho una directory, ricorro al suo interno.
		    else if (file.isDirectory())
		    {
		    	searchForImages(file.getAbsolutePath());
		    }
		}
		Platform.runLater(() -> controller.setText("\nThe search in the directory "+path+" is ended."));
		
		//Riabilito il button.
		controller.enableButton();
	}
	
	public void searchForPerson(File file)
	{
		Rect rectCrop=null;
		MatOfRect faces = new MatOfRect();
		//Apro l'immagine da analizzare.
		Mat image = Imgcodecs.imread(file.getAbsolutePath());
		//Se la checkBox è selezionata rendo le immagini in bianco e nero.
		if(controller.checkBoxSelected())
		{
			Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2GRAY);
			//Equalizzo l'istogramma per migliorare il risultato.
			Imgproc.equalizeHist(image, image);
		}
		//Verifico se son presenti volti nell'immagine.
		faceCascade.detectMultiScale(image, faces, 1.1, 2, 0 | Objdetect.CASCADE_SCALE_IMAGE,
				new Size(absoluteFaceSize, absoluteFaceSize), new Size());

		Rect[] facesArray = faces.toArray();
		//Se viene rilevato almeno un volto entro.
		if(facesArray.length>0)
		{
			//Salvo solo la prima faccia: se ho una foto con più volti, gli altri vengono persi.
			if(facesArray[0].width>facesArray[0].height)
			{
				rectCrop = new Rect(facesArray[0].x, facesArray[0].y, facesArray[0].width, facesArray[0].width);
			}
			else
			{
				rectCrop = new Rect(facesArray[0].x, facesArray[0].y, facesArray[0].height, facesArray[0].height);
			}
			
			//Creo un'immagine dal ritaglio del volto.
			Mat face = new Mat(image,rectCrop);
			//Creo una nuova matrice contenente il volto ridimensionato.
			Mat resizedFace = Mat.zeros(imageSize,CvType.CV_8UC1);
			//Scalo la foto in modo che abbiano tutte la stessa dimensione.
			Imgproc.resize(face, resizedFace, imageSize);
			//Sovrascrivo la foto.
			Imgcodecs.imwrite(file.getAbsolutePath(),resizedFace);	
			//Aggiorno il log.
			Platform.runLater(() -> controller.setText("\n"+file.getName()+" done."));
		}
		else
		{
			//Foto non trovata: aggiorno il log.
			controller.setText("\n"+file.getName()+": face not found.");
		}
	}
}
