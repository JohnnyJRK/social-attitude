package start;

import gui.Finestra;

/** Classe di avvio dell'applicazione*/
public class Start 
{
	public static void main(String args[])
	{
		System.loadLibrary("hapi73");
		Finestra finestra = new Finestra();
		finestra.setVisible(true);	
	}
}
