/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.uniba.di.dalu.datasource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;

import net.n3.nanoxml.IXMLElement;
import net.n3.nanoxml.IXMLParser;
import net.n3.nanoxml.IXMLReader;
import net.n3.nanoxml.StdXMLReader;
import net.n3.nanoxml.XMLException;
import net.n3.nanoxml.XMLParserFactory;

/**
 *
 * @author Edivad
 */
public class XMLManager {

    private String percorsoFileXML;
    private String data;

    public XMLManager(String percorsoFileXML) {
        this.percorsoFileXML = percorsoFileXML;
        
        data = "";
        FileReader reader = null;
        try {
			reader =  new FileReader(percorsoFileXML);
			Scanner scanner = new Scanner(reader);
			
			while(scanner.hasNextLine()){
				
				data += scanner.nextLine();
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     *
     * Si occupa di ritornare il valore dell'attributo di secondo livello dando in input
     * i nomi dei nodi da percorrere. Nel caso in cui non dovesse trovare il nodo
     * specifico ritorna il valore null.
     * @param Livello1 Nome del tag di primo livello
     * @param Livello2 Nome del tag di secondo livello da cui prelevare il valore, contenuto nel primo
     * @return Valore contenuto nel tag di secondo livello. Se non viene trovato niente, ritorna null
     */
    @SuppressWarnings("rawtypes")
	public String getValore(String Livello1, String Livello2) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        IXMLParser parser = XMLParserFactory.createDefaultXMLParser();
        IXMLReader reader = StdXMLReader.stringReader(data);
        parser.setReader(reader);
        IXMLElement nodoPrimoLivello = (IXMLElement) parser.parse();
        if (nodoPrimoLivello.getFullName().equals(Livello1)) {
            Enumeration xmlSecondoLivello = nodoPrimoLivello.enumerateChildren();
            IXMLElement nodoSecondoLivello;
            while (xmlSecondoLivello.hasMoreElements()) {
                nodoSecondoLivello = (IXMLElement) xmlSecondoLivello.nextElement();
                if (nodoSecondoLivello.getFullName().equals(Livello2)) {
                    String result = nodoSecondoLivello.getContent();
                    reader.close();
                    return result;
                }
            }
        }
        reader.close();
        return null;
    }

    /**
     *
     * Si occupa di ritornare il valore dell'attributo di terzo livello dando in input
     * i nomi dei nodi da percorrere. Nel caso in cui non dovesse trovare il nodo
     * specifico ritorna il valore null.
     * @param Livello1 Nome del tag di primo livello
     * @param Livello2 Nome del tag di secondo livello contenuto nel primo
     * @param Livello3 Nome del tag di terzo livello da cui prelevare il valore, contenuto nel secondo
     * @return Valore contenuto nel tag di terzo livello. Se non viene trovato niente, ritorna null
     */
    @SuppressWarnings("rawtypes")
	public String getValore(String Livello1, String Livello2, String Livello3) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        IXMLParser parser = XMLParserFactory.createDefaultXMLParser();
        IXMLReader reader = StdXMLReader.stringReader(data);
        parser.setReader(reader);
        IXMLElement nodoPrimoLivello = (IXMLElement) parser.parse();
        if (nodoPrimoLivello.getFullName().equals(Livello1)) {
            Enumeration xmlSecondoLivello = nodoPrimoLivello.enumerateChildren();
            IXMLElement nodoSecondoLivello;
            IXMLElement nodoTerzoLivello;
            while (xmlSecondoLivello.hasMoreElements()) {
                nodoSecondoLivello = (IXMLElement) xmlSecondoLivello.nextElement();
                if (nodoSecondoLivello.getFullName().equals(Livello2)) {
                    Enumeration xmlTerzoLivello = nodoSecondoLivello.enumerateChildren();
                    while (xmlTerzoLivello.hasMoreElements()) {
                        nodoTerzoLivello = (IXMLElement) xmlTerzoLivello.nextElement();
                        if (nodoTerzoLivello.getFullName().equals(Livello3)) {
                        	String result = nodoTerzoLivello.getContent();
                            reader.close();
                            return result;
                        }
                    }
                }
            }
        }
        reader.close();
        return null;
    }

    /**
     *
     * Si occupa di ritornare i valori dei di terzo livello dando in input
     * i nomi dei nodi da percorrere. Nel caso in cui non dovesse trovare il nodo
     * specifico ritorna il valore null.
     * @param Livello1 Nome del tag di primo livello
     * @param Livello2 Nome del tag di secondo livello contenuto nel primo
     * @param indiceLivello2 Numero del tag di secondo livello da analizzare (inizia da 0)
     * @param Livello3 Nome del tag di terzo livello da cui prelevare il valore, contenuto nel secondo
     * @return Valori contenuti nel tag di terzo livello. Se non viene trovato niente, ritorna null
     */
    @SuppressWarnings("rawtypes")
	public Vector<String> getValori(String Livello1,
            String Livello2, int indiceLivello2,
            String Livello3) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        Vector<String> results = new Vector<String>();
        int currentIndexLevel2 = -1;
        IXMLParser parser = XMLParserFactory.createDefaultXMLParser();
        IXMLReader reader = StdXMLReader.stringReader(data);
        parser.setReader(reader);
        IXMLElement nodoPrimoLivello = (IXMLElement) parser.parse();
        if (nodoPrimoLivello.getFullName().equals(Livello1)) {
            Enumeration xmlSecondoLivello = nodoPrimoLivello.enumerateChildren();
            IXMLElement nodoSecondoLivello;
            IXMLElement nodoTerzoLivello;
            while (xmlSecondoLivello.hasMoreElements()) {
                currentIndexLevel2++;
                nodoSecondoLivello = (IXMLElement) xmlSecondoLivello.nextElement();
                if (nodoSecondoLivello.getFullName().equals(Livello2) && currentIndexLevel2 == indiceLivello2) {
                    Enumeration xmlTerzoLivello = nodoSecondoLivello.enumerateChildren();
                    while (xmlTerzoLivello.hasMoreElements()) {
                        nodoTerzoLivello = (IXMLElement) xmlTerzoLivello.nextElement();
                        if (nodoTerzoLivello.getFullName().equals(Livello3)) {
                            results.add(nodoTerzoLivello.getContent());
                        }
                    }
                }
            }
        }
        if (results.size() > 0) {
        	reader.close();
            return results;
        } else {
        	reader.close();
            return null;
        }
    }

    /**
     *
     * Si occupa di ritornare l'insieme dei valori dell'attributo specificato associato ai tag di secondo livello,
     * dando in input i nomi dei nodi da percorrere e il nome dell'attributo da cui prendere il valore.
     * Nel caso in cui non dovesse trovare niente ritorna il valore null.
     * @param Livello1 Nome del tag di primo livello
     * @param Livello2 Nome del tag di secondo livello contenuto nel primo
     * @param nomeAttributoLivello2 Verrà restituito il valore di questo attributo (associato al tag di livello 2)
     * @return Insiene dei valori degli attributi indicati associati ai tag di secondo livello.
     * Se non viene trovato niente, ritorna null.
     */
    @SuppressWarnings("rawtypes")
	public Vector<String> getAttributi(String Livello1, String Livello2, String nomeAttributoLivello2) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        Vector<String> results = new Vector<String>();
        IXMLParser parser = XMLParserFactory.createDefaultXMLParser();
        IXMLReader reader = StdXMLReader.stringReader(data);
        parser.setReader(reader);
        IXMLElement nodoPrimoLivello = (IXMLElement) parser.parse();
        if (nodoPrimoLivello.getFullName().equals(Livello1)) {
            Enumeration xmlSecondoLivello = nodoPrimoLivello.enumerateChildren();
            IXMLElement nodoSecondoLivello;
            while (xmlSecondoLivello.hasMoreElements()) {
                nodoSecondoLivello = (IXMLElement) xmlSecondoLivello.nextElement();
                if (nodoSecondoLivello.getFullName().equals(Livello2) && !nodoSecondoLivello.getAttribute(nomeAttributoLivello2, "").equals("")) {
                    results.add(nodoSecondoLivello.getAttribute(nomeAttributoLivello2, ""));
                }
            }
        }
        if (results.size() > 0) {
        	reader.close();
            return results;
        } else {
        	reader.close();
            return null;
        }
    }
    
    
    
    /**
     *
     *
     */
    public boolean deleteXMLFile() {
        File file = new File(percorsoFileXML);
        
	    // Make sure the file or directory exists and isn't write protected
	    if (!file.exists())
	      throw new IllegalArgumentException(
	          "Delete: no such file or directory: " + percorsoFileXML);

	    if (!file.canWrite())
	      throw new IllegalArgumentException("Delete: write protected: "
	          + percorsoFileXML);

	    // If it is a directory, make sure it is empty
	    if (file.isDirectory()) {
	      String[] files = file.list();
	      if (files.length > 0)
	        throw new IllegalArgumentException(
	            "Delete: directory not empty: " + percorsoFileXML);
	    }

	    // Attempt to delete it
	    boolean success = file.delete();
        
        if (success) {
            percorsoFileXML = null;
        }

	    if (!success)
	      throw new IllegalArgumentException("Delete: deletion failed");
	    return success;
    }
}

