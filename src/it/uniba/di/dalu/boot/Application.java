package it.uniba.di.dalu.boot;

import it.uniba.di.dalu.datasource.XMLManager;
import it.uniba.di.dalu.ui.InputRecDialog;
import it.uniba.di.dalu.ui.MainView;
import it.uniba.di.dalu.ui.RecDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Scanner;

import net.n3.nanoxml.IXMLElement;
import net.n3.nanoxml.IXMLParser;
import net.n3.nanoxml.IXMLReader;
import net.n3.nanoxml.StdXMLReader;
import net.n3.nanoxml.XMLException;
import net.n3.nanoxml.XMLParserFactory;
import net.n3.nanoxml.XMLWriter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
/**
 * @author David
 *
 */

public class Application extends MainView {
	

	/**
	 * Launch the application.
	 * @param args
	 */
		public Application(){
			try {
			this.open();
		} catch (Exception e) {
			e.printStackTrace();
		}}
		
	public static void main(String[] args) {
		try {
			Application window = new Application();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean checkStop = true;
	protected int playerIndex;
	
	
	protected void addListeners(){
	
	recButton.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			stopButton.setEnabled(false);
			recButton.setEnabled(false);
			playButton.setEnabled(false);
			audioList.getList().setEnabled(false);
			
			String[] setting  = askFreeQuestion("File Name", "Please,select speaker's gender and type the text referring to the file name,\nthen press Ok." +
	                "\nOtherwise press cancel to discard the operation.", "File Name (" + fileList.size() + ")" ,false);
			String fileName = setting[1];
			
			String fileWav = fileName + ".wav";
			
			boolean success = false|(fileList.size()==0);
			while(!success){			
			for (int i=0; i < fileList.size(); i++ ){
				if ((directory + File.separator + fileWav).compareTo(fileList.get(i)) == 0){
					setting  = askFreeQuestion("File Name", "Type other name different from the previous and press Ok." +
			                "\nOtherwise press cancel to discard the operation.", "File Name (" + fileList.size() + ")" ,false);
					fileName = setting[1];
					fileWav = fileName + ".wav";
					success = false;
					break;
				}
				else{
					success = true;
				}
			}
			}
			if (!fileName.equalsIgnoreCase("null")){
			boolean StartREC = BusinessDelegate.startRecording(directory, fileWav);
			gender = setting[0];
			System.out.println("REC success: "+ StartREC);
			
			RecDialog wait = new RecDialog(shlVoiceClassifier, x, y);
			
			wait.open();
			MessageBox alert = null;
			if(new File(directory + File.separator + fileWav).exists())
				refreshAdd(fileWav);
			else{
				alert = new MessageBox(shlVoiceClassifier, SWT.OK);
				alert.setText("Alert");
				alert.setMessage("The recording is silence,\n therefore it will be delected.");
				alert.open();
			}
			BusinessDelegate.stopRecording();
			}
			
			recButton.setEnabled(true);
			audioList.getList().setEnabled(true);
		
		}		
	});	

	playButton.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			playerIndex = audioList.getList().getSelectionIndex();
			audioList.getList().setItem(playerIndex, audioList.getList().getItem(playerIndex)+": [PLAYED]" );
			String file = fileList.get(audioList.getList().getSelectionIndex());
			System.out.println("play file: " + file);

			playButton.setEnabled(false);
			stopButton.setEnabled(true);
			
			checkStop = !BusinessDelegate.playFile(file, Application.this);
			
		}
	});	
	
	stopButton.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			
			BusinessDelegate.stopFile();
			checkStop = true;
		}
	});
	
	
	btnClassify.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			btnAddExample.setEnabled(true);
			
			try {
				txtValencePredict.setText(BusinessDelegate.classifiesValence(prosodiaList.get(audioList.getList().getSelectionIndex()), 
																										"TrainingsetAROUSALeVALENZA.arff"));
				System.out.println("audio listttttttttttttt "+audioList.getList().getSelectionIndex());
				txtArousalPredict.setText(BusinessDelegate.classifiesArousal(prosodiaList.get(audioList.getList().getSelectionIndex()), 
																										"TrainingsetAROUSALeVALENZA.arff"));
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			comboArousal.setEnabled(true);
			comboValence.setEnabled(true);

			for (int i=0; i<comboArousal.getItemCount(); i++){
			if (comboArousal.getItem(i).equalsIgnoreCase(txtArousalPredict.getText())){
					comboArousal.select(i);
				}
			}
			
			for (int i=0; i<comboValence.getItemCount(); i++){
			if (comboValence.getItem(i).equalsIgnoreCase(txtValencePredict.getText())){
					comboValence.select(i);
				}
			}
			
		}
	});
	
	
	btnAddExample.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			
			
			Boolean success = BusinessDelegate.include(prosodiaList.get(audioList.getList().getSelectionIndex()),
															comboArousal.getItem(comboArousal.getSelectionIndex()),
																	comboValence.getItem(comboValence.getSelectionIndex()),
																						"TrainingsetAROUSALeVALENZA.arff");
			
			System.out.println("add example to training set: "+prosodiaList.get(audioList.getList().getSelectionIndex()).getNomeFileAudio()+" "+success);
			if (success){
				BusinessDelegate.resetModels();
				MessageBox messageBox = new MessageBox(shlVoiceClassifier, SWT.ICON_INFORMATION);
			    messageBox.setMessage(prosodiaList.get(audioList.getList().getSelectionIndex()).getNomeFileAudio()+" has added in Training Set.");
			    messageBox.open();
			}
				
		}
	});
	
	
	audioList.getList().addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			
			if(audioList.getList().getSelectionIndex()>=0){
				
			if (checkStop)	
				playButton.setEnabled(true);
			
			btnClassify.setEnabled(true);
			
			changePropertyList(audioList.getList().getSelectionIndex());
			
			itemAudioSelected.setText(prosodiaList.get(audioList.getList().getSelectionIndex()).getNomeFileAudio());
			txtValencePredict.setText("VelancePredict");
			txtArousalPredict.setText("ArousalPredict");
			comboArousal.deselectAll();
			comboValence.deselectAll();
			comboArousal.setEnabled(false);
			comboValence.setEnabled(false);
			btnAddExample.setEnabled(false);
			
			mntmEditItem.setEnabled(true);
			mntmDeleteItem.setEnabled(true);
			popupEdit.setEnabled(true);
			popupDelete.setEnabled(true);
			}
		}
	});
	
	mntmDirectory.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			
			try {
				setDirectory(shlVoiceClassifier);
				propertyList.getList().removeAll();
				propertyList.getList().redraw();
				
				directory =getDirectory();
				fileList.clear();
				fileList.addAll(BusinessDelegate.getFileList(directory));
				
				
				lblAudioList.setText("Audio List: | " + directory +" |");					

				refresh();
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	
	mntmRec.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			
			try {
				setRecProperty(shlVoiceClassifier, MainView.x , MainView.y, "app");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	Exit.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			MessageBox messageBox = new MessageBox(shlVoiceClassifier, SWT.ICON_QUESTION
		            | SWT.YES | SWT.NO);
		        messageBox.setMessage("Do you really want to exit?");
		        messageBox.setText("Exiting Application");
		        int response = messageBox.open();
		        if (response == SWT.YES){
		          System.exit(0);
		        }
		}
	});
	
	shlVoiceClassifier.addListener(SWT.Close, new Listener() {
        public void handleEvent(Event event) {
			MessageBox messageBox = new MessageBox(shlVoiceClassifier, SWT.ICON_QUESTION
		            | SWT.YES | SWT.NO);
		        messageBox.setMessage("Do you really want to exit?");
		        messageBox.setText("Exiting Application");
		        if (messageBox.open() == SWT.YES){
		        	event.doit = true;
		        	System.exit(0);
		        } else{
		        	event.doit = false;
		        }
        }
      });
	
	mntmRefresh.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			
			refresh();
		}
	});
	
	popupRefresh.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			
			refresh();
		}
	});
	
	mntmEditItem.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			String file = fileList.get(audioList.getList().getSelectionIndex());

			String[] oldName = prosodiaList.get(audioList.getList().getSelectionIndex()).getNomeFileAudio().split(".wav");
			String[] setting  = askFreeQuestion("File Name", "Please,type the text referring to the file name, then press Ok." +
	                "\nOtherwise press cancel to discard the operation.", oldName[0] ,true);
			String fileName = setting[1];
			
			String fileWav = fileName + ".wav";
			
			boolean success = false|(fileList.size()==0);
			while(!success){			
			for (int i=0; i < fileList.size(); i++ ){
				if ((directory + File.separator + fileWav).compareTo(fileList.get(i)) == 0){
					setting  = askFreeQuestion("File Name", "Type other name different from the previous and press Ok." +
			                "\nOtherwise press cancel to discard the operation.", "File Name (" + fileList.size() + ")" ,true);
					fileName = setting[1];
					fileWav = fileName + ".wav";
					success = false;
					break;
				}
				else{
					success = true;
				}
			}
			}
			if (!fileName.equalsIgnoreCase("null")){
				
				Boolean checkXML = editXMLFile(file, fileWav);	
				
				File oldfile =new File(file);
				File newfile =new File(directory + File.separator + fileWav);	
				
				Boolean check = null;
				try {
					check = renameFile(file, directory + File.separator + fileWav);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Edited: "+ oldfile +" in : "+ newfile +" = "+checkXML+ " " +check);
				refreshEdit(audioList.getList().getSelectionIndex(),fileWav);
			}
		}
	});	

	popupEdit.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			String file = fileList.get(audioList.getList().getSelectionIndex());

			String[] oldName = prosodiaList.get(audioList.getList().getSelectionIndex()).getNomeFileAudio().split(".wav");
			String[] setting  = askFreeQuestion("File Name", "Please,type the text referring to the file name, then press Ok." +
	                "\nOtherwise press cancel to discard the operation.", oldName[0] , true);
			String fileName = setting[1];
			
			String fileWav = fileName + ".wav";
			
			boolean success = false|(fileList.size()==0);
			while(!success){			
			for (int i=0; i < fileList.size(); i++ ){
				if ((directory + File.separator + fileWav).compareTo(fileList.get(i)) == 0){
					setting  = askFreeQuestion("File Name", "Type other name different from the previous and press Ok." +
			                "\nOtherwise press cancel to discard the operation.", "File Name (" + fileList.size() + ")" , true);
					fileName = setting[1];
					fileWav = fileName + ".wav";
					success = false;
					break;
				}
				else{
					success = true;
				}
			}
			}
			if (!fileName.equalsIgnoreCase("null")){
				
				Boolean checkXML = editXMLFile(file, fileWav);	
				
				File oldfile =new File(file);
				File newfile =new File(directory + File.separator + fileWav);	
				
				Boolean check = null;
				try {
					check = renameFile(file, directory + File.separator + fileWav);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Edited: "+ oldfile +" in : "+ newfile +" = "+checkXML+ " " +check);
				refreshEdit(audioList.getList().getSelectionIndex(),directory + File.separator + fileWav);
			}
		}
	});	

	mntmDeleteItem.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
		MessageBox messageBox = new MessageBox(shlVoiceClassifier, SWT.ICON_QUESTION
		            | SWT.YES | SWT.NO);
        messageBox.setMessage("Do you really want to delete " +
        		prosodiaList.get(audioList.getList().getSelectionIndex()).getNomeFileAudio() + "?");
        messageBox.setText("Delete Audio File");
		int response = messageBox.open();
		if (response == SWT.YES){	
			
			String file = fileList.get(audioList.getList().getSelectionIndex());
			String[] old = null;
			if (System.getProperty("os.name").equalsIgnoreCase("Windows 7")||System.getProperty("os.name").equalsIgnoreCase("Windows Vista")||System.getProperty("os.name").equalsIgnoreCase("Windows XP"))
			old = file.split(File.separator+File.separator, Integer.MAX_VALUE);
			else
				old = file.split(File.separator, Integer.MAX_VALUE);
	        	        
			String directory = "";
	        for(int i = 0; i < old.length-1; i++){
	        	directory += (old[i] + File.separator);
	        }
	        int hash = directory.hashCode();
	        String nomeFileXML = old[old.length -1] + ".xml";
	        
	        deleteFile("xmlAudio" + File.separator + hash + File.separator + nomeFileXML, true);
	        deleteFile(file, false);
	        
	        refreshDelete(audioList.getList().getSelectionIndex());
			}
		}	
	});

	popupDelete.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
		MessageBox messageBox = new MessageBox(shlVoiceClassifier, SWT.ICON_QUESTION
		            | SWT.YES | SWT.NO);
        messageBox.setMessage("Do you really want to delete " +
        		prosodiaList.get(audioList.getList().getSelectionIndex()).getNomeFileAudio() + "?");
        messageBox.setText("Delete Audio File");
		int response = messageBox.open();
		if (response == SWT.YES){	
			
			String file = fileList.get(audioList.getList().getSelectionIndex());
			String[] old = null;
			if (System.getProperty("os.name").equalsIgnoreCase("Windows 7")||System.getProperty("os.name").equalsIgnoreCase("Windows Vista")||System.getProperty("os.name").equalsIgnoreCase("Windows XP"))
			old = file.split(File.separator+File.separator, Integer.MAX_VALUE);
			else
				old = file.split(File.separator, Integer.MAX_VALUE);
	        	        
			String directory = "";
	        for(int i = 0; i < old.length-1; i++){
	        	directory += (old[i] + File.separator);
	        }
	        int hash = directory.hashCode();
	        String nomeFileXML = old[old.length -1] + ".xml";
	        
	        deleteFile("xmlAudio" + File.separator + hash + File.separator + nomeFileXML, true);
	        deleteFile(file, false);
	        
	        refreshDelete(audioList.getList().getSelectionIndex());
			}
		}	
	});
	
	mntmAboutVoiceclassifier.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			
			
			MessageBox about = new MessageBox(shlVoiceClassifier, SWT.OK);
			about.setText("About VoiceClassifier");
			about.setMessage("VoiceClassifier\n\nDesktop application Java-based for: \n\t\t\t\t- voice analysis\n\t\t\t\t- classification of valence and arousal\n\n\n" +
					"Version : 1.5.6\n\nAuthors:\n\tDavide Sirago\tLuigi Brigida ");
			about.open();
		}
	});

	}
		

	//Service Method

	public String[] askFreeQuestion( final String title, final String question, final String initialValue , Boolean rename) {
	    InputRecDialog input = new InputRecDialog( shlVoiceClassifier, title, question, initialValue, rename);
	    String[] result = (String[]) input.open();
	    
	    return result;
	}
	
	private void refreshEdit(int index, String newNomeFile){
		propertyList.getList().removeAll();
		propertyList.getList().redraw();
		
		fileList.set(index, directory + File.separator + newNomeFile);
		prosodiaList.get(index).setNomeFileAudio(newNomeFile);
		audioList.getList().setItem(index, prosodiaList.get(index).getNomeFileAudio());
		audioList.getList().redraw();
		
	}

	private void refreshAdd(String fileWav){
		propertyList.getList().removeAll();
		propertyList.getList().redraw();
		loadingBar.setVisible(true); 
		loadingBar.setMinimum(0);
		loadingBar.setMaximum(2);
		loadingBar.setSelection(1);
		
		fileList.add(directory + File.separator + fileWav);
			
		      loadingBar.setSelection(loadingBar.getMaximum());
	          try {
		      		lblLoadingAudio.setVisible(true);

		      		lblLoadingAudio.setText("Loading " + fileWav);
		      		
		      	    	prosodiaList.add(BusinessDelegate.getAudio(directory + File.separator + fileWav, gender));
		      	    
		      	    for(int i=0; i<prosodiaList.size(); i++)
		      	    if (prosodiaList.get(i).getNomeFileAudio().equalsIgnoreCase(fileWav))	
		      	    audioList.getList().add(prosodiaList.get(i).getNomeFileAudio());
		      	  loadingBar.setSelection(loadingBar.getMaximum());
		      	  Thread.sleep(1000);
	          } catch (Throwable e1) {
	        	  e1.printStackTrace();
	          }
	          
	    lblLoadingAudio.setVisible(false);
	    loadingBar.setVisible(false);
		audioList.getList().redraw();
		
		// reset interface
		mntmEditItem.setEnabled(false);
		mntmDeleteItem.setEnabled(false);
		popupEdit.setEnabled(false);
		popupDelete.setEnabled(false);
		
		itemAudioSelected.setText("selected item");
		txtValencePredict.setText("VelancePredict");
		txtArousalPredict.setText("ArousalPredict");
		btnClassify.setEnabled(false);
		btnAddExample.setEnabled(false);
		comboArousal.setEnabled(false);
		comboValence.setEnabled(false);
		comboArousal.deselectAll();
		comboValence.deselectAll();
		
	}
	
	private void refreshDelete(int index){
		propertyList.getList().removeAll();
		propertyList.getList().redraw();
		
		fileList.remove(index);
		prosodiaList.remove(index);
		audioList.getList().remove(index);
		audioList.getList().redraw();
		
		// reset interface
		mntmEditItem.setEnabled(false);
		mntmDeleteItem.setEnabled(false);
		popupEdit.setEnabled(false);
		popupDelete.setEnabled(false);
		
		itemAudioSelected.setText("selected item");
		txtValencePredict.setText("VelancePredict");
		txtArousalPredict.setText("ArousalPredict");
		btnClassify.setEnabled(false);
		btnAddExample.setEnabled(false);
		comboArousal.setEnabled(false);
		comboValence.setEnabled(false);
		comboArousal.deselectAll();
		comboValence.deselectAll();
		
	}
	
	private void refresh(){
		
		audioList.getList().removeAll();
		propertyList.getList().removeAll();
		propertyList.getList().redraw();
		playButton.setEnabled(false);
		stopButton.setEnabled(false);
		
		fileList.clear();
		fileList.addAll(BusinessDelegate.getFileList(directory));
		
		int nFile = fileList.size();
		lblLoadingAudio.setVisible(true);
		if (nFile != 0)
			lblLoadingAudio.setText("Loading " + nFile + " Audio files");
		
		loadingBar.setVisible(true);
					
		changeAudioList(loadingBar, gender);
		
		// reset loading process
		lblLoadingAudio.setVisible(false);
		loadingBar.setVisible(false);
		lblLoadingAudio.setText(""); 
		
		mntmEditItem.setEnabled(false);
		mntmDeleteItem.setEnabled(false);
		popupEdit.setEnabled(false);
		popupDelete.setEnabled(false);
		
		itemAudioSelected.setText("selected item");
		txtValencePredict.setText("VelancePredict");
		txtArousalPredict.setText("ArousalPredict");
		btnClassify.setEnabled(false);
		btnAddExample.setEnabled(false);
		comboArousal.setEnabled(false);
		comboValence.setEnabled(false);
		comboArousal.deselectAll();
		comboValence.deselectAll();
	}
	
	private Boolean editXMLFile(String oldFile, String newFile){
		String[] old = null;
		if (System.getProperty("os.name").equalsIgnoreCase("Windows 7")||System.getProperty("os.name").equalsIgnoreCase("Windows Vista")||System.getProperty("os.name").equalsIgnoreCase("Windows XP"))
		old = oldFile.split(File.separator+File.separator, Integer.MAX_VALUE);
		else
			old = oldFile.split(File.separator, Integer.MAX_VALUE);

		String directory = "";
        for(int i = 0; i < old.length-1; i++){
        	directory += (old[i] + File.separator);
        }
        int hash = directory.hashCode();
        String nomeFileXML = old[old.length -1] + ".xml";
        File dir = new File("xmlAudio" + File.separator + hash);
        if(!dir.exists())
        	dir.mkdir();

        Boolean edited = null;
		try {
			edited = renameFile("xmlAudio" + File.separator + hash + File.separator + nomeFileXML, "xmlAudio" + File.separator + hash + File.separator + newFile + ".xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			editXMLFileName("xmlAudio" + File.separator + hash + File.separator + newFile + ".xml", directory + File.separator + newFile);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return edited;
	}
	
	public static Boolean renameFile(String oldName, String newName) throws IOException {
	    File srcFile = new File(oldName);
	    boolean bSucceeded = false;
	    try {
	        File destFile = new File(newName);
	        if (destFile.exists()) {
	            if (!destFile.delete()) {
	                throw new IOException(oldName + " was not successfully renamed (delete) to " + newName); 
	            }
	        }
	        if (!srcFile.renameTo(destFile))        {
	            throw new IOException(oldName + " was not successfully renamed (renameTo) to " + newName);
	        } else {
	                bSucceeded = true;
	        }
	    } finally {
	          if (bSucceeded) {
	                srcFile.delete();
	          }
	    }
	    return bSucceeded;
	}
	
	public static void deleteFile(String oldFile , Boolean xml){
		
		if (!xml){
		// A File object to represent the filename
	    File f = new File(oldFile);

	    // Make sure the file or directory exists and isn't write protected
	    if (!f.exists())
	      throw new IllegalArgumentException(
	          "Delete: no such file or directory: " + oldFile);

	    if (!f.canWrite())
	      throw new IllegalArgumentException("Delete: write protected: "
	          + oldFile);

	    // If it is a directory, make sure it is empty
	    if (f.isDirectory()) {
	      String[] files = f.list();
	      if (files.length > 0)
	        throw new IllegalArgumentException(
	            "Delete: directory not empty: " + oldFile);
	    }

	    // Attempt to delete it
	    boolean success = f.delete();
	    System.out.println("file : "+oldFile+" deleted = "+ success);
	    
	    if (!success)
	      throw new IllegalArgumentException("Delete: deletion failed");}
		else{
			XMLManager xmlManager = new XMLManager(oldFile);
			boolean successXML = xmlManager.deleteXMLFile();
			System.out.print("XML: "+ successXML+" and ");
		}
		
	}
	
	@SuppressWarnings({ "rawtypes" })
	private void editXMLFileName(String xmlFile, String newFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLException{
		
		String data = "";
        FileReader readerFile = null;
        try {
			readerFile =  new FileReader(xmlFile);
			Scanner scanner = new Scanner(readerFile);
			
			while(scanner.hasNextLine()){
				
				data += scanner.nextLine();
			}
			
			 IXMLParser parser = XMLParserFactory.createDefaultXMLParser();
		        IXMLReader reader = StdXMLReader.stringReader(data);
		        parser.setReader(reader);
		        IXMLElement nodoPrimoLivello = (IXMLElement) parser.parse();
		        if (nodoPrimoLivello.getFullName().equals("analisi")) {
		            Enumeration xmlSecondoLivello = nodoPrimoLivello.enumerateChildren();
		            IXMLElement nodoSecondoLivello;
		            while (xmlSecondoLivello.hasMoreElements()) {
		                nodoSecondoLivello = (IXMLElement) xmlSecondoLivello.nextElement();
		                if (nodoSecondoLivello.getFullName().equals("nome_file")) {
		                    nodoSecondoLivello.setContent(newFile);
		                    reader.close();
		                    
		                    OutputStream outputStream = new FileOutputStream(xmlFile);
		                    XMLWriter xmlWriter = new XMLWriter(outputStream);
		                    xmlWriter.write(nodoPrimoLivello, true);
		                    outputStream.close();
		                }
		            }
		        }
		        reader.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			readerFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
              
	}
	
	public void resetStopButton(){
		Display.getDefault().syncExec(new Runnable() {
		    public void run() {
		    					
				audioList.getList().setItem(playerIndex, prosodiaList.get(playerIndex).getNomeFileAudio() );
				checkStop = true;
				playButton.setEnabled(true);
				stopButton.setEnabled(false);
		    }
		});

	}

	
	
}




