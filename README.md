# Dataset Preparation For CK+

<p align="center">
	Screenshot of the Dataset Preparation For CK+ program.
   <img src="https://github.com/AnthonyMarc/Dataset_Preparation_For_CK/blob/master/resources/dpCK+.png"> 
</p>

Description available in english and italian below.


## English
This program is the Dataset Preparation software version that allows you to prepare the Cohn-Kanade Extended (CK +) dataset (but you can use other datasets if you want) to train a neural network capable of recognizing human emotions.
In detail, the Dataset Preparation for CK+ program performs the following operations:

- cycle through all the folders and open each photo individually;
- perform a Haar Detection in order to cut the image to the face;
- equalizes the histogram to improve contrast;
- overwrites the original photo.

In order to work, in the same folder in which the .jar file is created it is also necessary to insert a lib folder (in case you can copy the one in this repository) containing the OpenCV library, the respective .dll and the file of the Haar Detection in .xml format.

The program has been developed and tested on the Windows platform: to run in Linux or Mac you need to make changes to the OpenCV library.

The Cohn-Kanade is a dataset made by T. Kanade, J.F. Cohn and Y. Tian and consists of 486 grayscale photo sequences taken at 97 different people; it is available on request and solely for research purposes at the following link: http://www.pitt.edu/~emotion/ck-spread.htm. The Cohn-Kanade Extended (CK +) is the updated version of this dataset that also presents color photos and extends the number of sequences by 22% and the number of people portrayed by 27%.

At this link you can find the Dataset Preparation software version for the Fer2013 dataset:
https://github.com/AnthonyMarc/Dataset_Preparation_For_Fer2013


## Italiano
Questo programma è la versione del software Dataset Preparation che consente di preparare il dataset Cohn-Kanade Extended (CK+) (ma volendo può essere usato anche per altri dataset) per l'addestramento di una rete neurale capace di riconoscere le emozioni umane.
In dettaglio, il programma Dataset Preparation for CK+ effettua le seguenti operazioni:

- cicla per tutte le cartelle ed apre singolarmente ogni foto;
- effettua una Haar Detection in modo da ritagliare l'immagine al solo volto;
- equalizza l'istogramma in modo da migliorare il contrasto;
- sovrascrive la foto originale.

Per poter funzionare, nella stessa cartella in cui viene creato il file .jar occorre anche inserire una cartella lib (nel caso si può copiare quella presente in questo repository) contenente la libreria di OpenCV, la rispettiva .dll e il file della Haar Detection in formato .xml.

Il programma è stato sviluppato e testato su piattaforma Windows: per girare in ambiente Linux o Mac occorre modificare la libreria relativa ad OpenCV.

Il Cohn-Kanade è un dataset realizzato da T. Kanade, J.F. Cohn e Y. Tian ed è composto da 486 sequenze di foto in scala di grigi scattate a 97 differenti persone; esso è reperibile su richiesta ed unicamente per scopi di ricerca al seguente link: http://www.pitt.edu/~emotion/ck-spread.htm . Il Cohn-Kanade Extended (CK+) è la versione aggiornata di questo dataset che presenta anche foto a colori ed estende del 22%  il numero di sequenze e del 27% il numero di persone ritratte. 

A questo link è presente la versione del software Dataset Preparation per il dataset Fer2013:
https://github.com/AnthonyMarc/Dataset_Preparation_For_Fer2013