/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.uniba.di.dalu.prosody;

import it.uniba.di.dalu.datasource.DataMapper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import net.n3.nanoxml.IXMLElement;
import net.n3.nanoxml.IXMLParser;
import net.n3.nanoxml.IXMLReader;
import net.n3.nanoxml.StdXMLReader;
import net.n3.nanoxml.XMLException;
import net.n3.nanoxml.XMLParserFactory;
import net.n3.nanoxml.XMLWriter;

/**
 *
 * Analizzatore Audio. Si utilizza per ricavare informazioni riguardanti la prosodia di uno specifico file audio.
 * 
 */
public class Praat {

    private File xmlDiOutput;


    public Praat(String nomeFileAudioConPercorso, boolean overwriteOldResults, String gender) throws Exception, Exception, IllegalAccessException, XMLException {
		String[] n = null;
		if (System.getProperty("os.name").equalsIgnoreCase("Windows 8")||System.getProperty("os.name").equalsIgnoreCase("Windows 7")||System.getProperty("os.name").equalsIgnoreCase("Windows Vista")||System.getProperty("os.name").equalsIgnoreCase("Windows XP"))
		n = nomeFileAudioConPercorso.split(File.separator+File.separator, Integer.MAX_VALUE);
		else
			n = nomeFileAudioConPercorso.split(File.separator, Integer.MAX_VALUE);
        String directory = "";
        for(int i = 0; i < n.length-1; i++){
        	directory += (n[i] + File.separator);
        }
        int hash = directory.hashCode();
        String nomeFileXML = n[n.length -1] + ".xml";
        File dir = new File("xmlAudio" + File.separator + hash);
        if(!dir.exists())
        	dir.mkdir();
        String nomeFileXMLConPercorso = (new File("").getAbsolutePath()) + File.separator + "xmlAudio" + File.separator + hash + File.separator + nomeFileXML;
        xmlDiOutput = new File("xmlAudio" + File.separator + hash + File.separator + nomeFileXML);
        boolean createResults = !xmlDiOutput.exists();
        System.out.println("Create xml: "+createResults + ", overwrite: " + overwriteOldResults);
        if (!createResults)
            createResults = overwriteOldResults;
        if (createResults) {
            try {
                Process process;
                Boolean processoTerminato = false;
                
                String command = null; 
                if (System.getProperty("os.name").equalsIgnoreCase("Windows 8")||System.getProperty("os.name").equalsIgnoreCase("Windows 7")||System.getProperty("os.name").equalsIgnoreCase("Windows Vista")||System.getProperty("os.name").equalsIgnoreCase("Windows XP"))
                	command = "." + File.separator + "praat" + File.separator + "praatcon.exe ." + File.separator + "praat" + File.separator + "script.praat \""+ nomeFileAudioConPercorso + "\" \"" + nomeFileXMLConPercorso + "\"";
                else{
                	command = new File("").getAbsolutePath() + File.separator + "praat" + File.separator + "praat " + new File("").getAbsolutePath() + File.separator + "praat" + File.separator + "script.praat \""+ nomeFileAudioConPercorso + "\" \"" + nomeFileXMLConPercorso + "\"";
                	try {
                	      File avvia = new File("./praat/avvia.sh");
                	      FileWriter fw = new FileWriter(avvia);
                	      fw.write(command);
                	      fw.flush();
                	      fw.close();
                	    }
                	catch(IOException e) {
                		e.printStackTrace();
                	}
                	command = "sh -c ./praat/avvia.sh";
                }
                process = Runtime.getRuntime().exec(command);
                System.out.println(command);
                
                
                while (!processoTerminato) {
                    try {
                    	Thread.sleep(500);
                        process.exitValue();
                        processoTerminato = true;
                        writeGender("xmlAudio" + File.separator + hash + File.separator + nomeFileXML, gender);
                        writeDuration("xmlAudio" + File.separator + hash + File.separator + nomeFileXML, nomeFileAudioConPercorso);
                    } catch (IllegalThreadStateException ex) {
                        processoTerminato = false;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Praat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Praat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Restituisce tutti i dati prosodici ottenuti dall'analisi del file audio
     * durante l'inizializzazione di questa classe.
     * @return Oggetto contenente tutti le informazioni prosodiche
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     * @throws net.n3.nanoxml.XMLException
     */

	public ProsodiaFileAudio getDatiProsodici() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        DataMapper dataMapper = new DataMapper();
        dataMapper.setFileXMLFileAudio(xmlDiOutput.getParent(), xmlDiOutput.getName());
        ProsodiaFileAudio prosodiaFileAudio = new ProsodiaFileAudio(dataMapper.getXMLFileAudioName(xmlDiOutput.getName()));
        String genderProsodia = dataMapper.getGender(xmlDiOutput.getName());
        if (genderProsodia.equals("Auto")){
        	prosodiaFileAudio.setGender(getGender(dataMapper));	
        }else{
        prosodiaFileAudio.setGender(dataMapper.getGender());
        }
        prosodiaFileAudio.setDuration(dataMapper.getDuration());
        prosodiaFileAudio.setSoundMedia(dataMapper.getSoundMedia());
        prosodiaFileAudio.setSoundMedieLocali(dataMapper.getSoundMedieLocali());
        prosodiaFileAudio.setSoundMinimo(dataMapper.getSoundMinimo());
        prosodiaFileAudio.setSoundMinimiLocali(dataMapper.getSoundMinimiLocali());
        prosodiaFileAudio.setSoundMassimo(dataMapper.getSoundMassimo());
        prosodiaFileAudio.setSoundMassimiLocali(dataMapper.getSoundMassimiLocali());
        prosodiaFileAudio.setSoundTempoMinimo(dataMapper.getSoundTempoMinimo());
        prosodiaFileAudio.setSoundTempiMinimiLocali(dataMapper.getSoundTempiMinimiLocali());
        prosodiaFileAudio.setSoundTempoMassimo(dataMapper.getSoundTempoMassimo());
        prosodiaFileAudio.setSoundTempiMassimiLocali(dataMapper.getSoundTempiMassimiLocali());
        prosodiaFileAudio.setSoundValoreEfficace(dataMapper.getSoundValoreEfficace());
        prosodiaFileAudio.setSoundDeviazioneStandard(dataMapper.getSoundDeviazioneStandard());
        prosodiaFileAudio.setSoundDeviazioniStandardLocali(dataMapper.getSoundDeviazioniStandardLocali());
        prosodiaFileAudio.setSoundEnergia(dataMapper.getSoundEnergia());
        prosodiaFileAudio.setSoundPotenza(dataMapper.getSoundPotenza());
        prosodiaFileAudio.setSoundEnergiaAria(dataMapper.getSoundEnergiaAria());
        prosodiaFileAudio.setSoundPotenzaAria(dataMapper.getSoundPotenzaAria());
        prosodiaFileAudio.setSoundIntensitaDB(dataMapper.getSoundIntensitaDB());
        prosodiaFileAudio.setPitchMinimo(dataMapper.getPitchMinimo());
        prosodiaFileAudio.setPitchMinimiLocali(dataMapper.getPitchMinimiLocali());
        prosodiaFileAudio.setPitchMedio(dataMapper.getPitchMedia());
        prosodiaFileAudio.setPitchMedieLocali(dataMapper.getPitchMedieLocali());
        prosodiaFileAudio.setPitchMassimo(dataMapper.getPitchMassimo());
        prosodiaFileAudio.setPitchMassimiLocali(dataMapper.getPitchMassimiLocali());
        prosodiaFileAudio.setPitchMinimoLogaritmico(dataMapper.getPitchMinimoLogaritmico());
        prosodiaFileAudio.setPitchMinimiLogaritmiciLocali(dataMapper.getPitchMinimiLogaritmiciLocali());
        prosodiaFileAudio.setPitchMedioLogaritmico(dataMapper.getPitchMediaLogaritmica());
        prosodiaFileAudio.setPitchMedieLogaritmicheLocali(dataMapper.getPitchMedieLogaritmicheLocali());
        prosodiaFileAudio.setPitchMassimoLogaritmico(dataMapper.getPitchMassimoLogaritmico());
        prosodiaFileAudio.setPitchMassimiLogaritmiciLocali(dataMapper.getPitchMassimiLogaritmiciLocali());
        prosodiaFileAudio.setPitchSlope(dataMapper.getPitchSlope());
        prosodiaFileAudio.setPitchTempoMinimo(dataMapper.getPitchTempoMinimo());
        prosodiaFileAudio.setPitchTempiMinimiLocali(dataMapper.getPitchTempiMinimiLocali());
        prosodiaFileAudio.setPitchTempoMassimo(dataMapper.getPitchTempoMassimo());
        prosodiaFileAudio.setPitchTempiMassimiLocali(dataMapper.getPitchTempiMassimiLocali());
        prosodiaFileAudio.setPitchDeviazioneStandard(dataMapper.getPitchDeviazioneStandard());
        prosodiaFileAudio.setPitchDeviazioniStandardLocali(dataMapper.getPitchDeviazioniStandardLocali());
        prosodiaFileAudio.setPitchNumeroCampioni(dataMapper.getPitchNumeroCampioni());
        prosodiaFileAudio.setIntensityMinimo(dataMapper.getIntensityMinimo());
        prosodiaFileAudio.setIntensityMinimiLocali(dataMapper.getIntensityMinimiLocali());
        prosodiaFileAudio.setIntensityMedia(dataMapper.getIntensityMedia());
        prosodiaFileAudio.setIntensityMedieLocali(dataMapper.getIntensityMedieLocali());
        prosodiaFileAudio.setIntesityMassimo(dataMapper.getIntensityMassimo());
        prosodiaFileAudio.setIntensityMassimiLocali(dataMapper.getIntensityMassimiLocali());
        prosodiaFileAudio.setIntensityTempoMinimo(dataMapper.getIntensityTempoMinimo());
        prosodiaFileAudio.setIntensityTempiMinimiLocali(dataMapper.getIntensityTempiMinimiLocali());
        prosodiaFileAudio.setIntensityTempoMassimo(dataMapper.getIntensityTempoMassimo());
        prosodiaFileAudio.setIntensityTempiMassimiLocali(dataMapper.getIntensityTempiMassimiLocali());
        prosodiaFileAudio.setIntensityDeviazioneStandard(dataMapper.getIntensityDeviazioneStandard());
        prosodiaFileAudio.setIntensityDeviazioniStandardLocali((dataMapper.getIntensityDeviazioniStandardLocali()));
        prosodiaFileAudio.setHarmonicityDeviazioneStandard(dataMapper.getHarmonicityDeviazioneStandard());
        prosodiaFileAudio.setHarmonicityDeviazioniStandardLocali(dataMapper.getHarmonicityDeviazioniStandardLocali());
        prosodiaFileAudio.setHarmonicityMassimo(dataMapper.getHarmonicityMassimo());
        prosodiaFileAudio.setHarmonicityMassimiLocali(dataMapper.getHarmonicityMassimiLocali());
        prosodiaFileAudio.setHarmonicityMedia(dataMapper.getHarmonicityMedia());
        prosodiaFileAudio.setHarmonicityMedieLocali(dataMapper.getHarmonicityMedieLocali());
        prosodiaFileAudio.setHarmonicityMinimo(dataMapper.getHarmonicityMinimo());
        prosodiaFileAudio.setHarmonicityMinimiLocali(dataMapper.getHarmonicityMinimiLocali());
        prosodiaFileAudio.setHarmonicityTempoMassimo(dataMapper.getHarmonicityTempoMassimo());
        prosodiaFileAudio.setHarmonicityTempiMassimiLocali(dataMapper.getHarmonicityTempiMassimiLocali());
        prosodiaFileAudio.setHarmonicityTempoMinimo(dataMapper.getHarmonicityTempoMinimo());
        prosodiaFileAudio.setHarmonicityTempiMinimiLocali(dataMapper.getHarmonicityTempiMinimiLocali());
        prosodiaFileAudio.setSpectrumCentralMoment(dataMapper.getSpectrumCentralMoment());
        prosodiaFileAudio.setSpectrumDeviazioneStandard(dataMapper.getSpectrumDeviazioneStandard());
        prosodiaFileAudio.setSpectrumGravityCentre(dataMapper.getSpectrumGravityCentre());
        prosodiaFileAudio.setSpectrumKurtosis(dataMapper.getSpectrumKurtosis());
        prosodiaFileAudio.setSpectrumSkewness(dataMapper.getSpectrumSkewness());
        return prosodiaFileAudio;
    }

    /**
     * Restituisce il genere del'interlocutore in base ai dati prosodici ottenuti
     * dall'analisi del file audio durante l'inizializzazione di questa classe.
     * @return Stringa di un solo carattere: "m" per maschio; "f" per femmina.
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     * @throws net.n3.nanoxml.XMLException
     */
    private String getGender(DataMapper dataMapper) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {

        float pitchMedia;
        String gender;
        pitchMedia = Float.parseFloat(dataMapper.getPitchMedia().split(" ")[0]);
        
        if (pitchMedia < 215) {
            gender = "m";
        } else {
            gender = "f";
        }
        
        return gender;
    }
    
	private void writeGender(String xmlFile, String gender) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLException{
		
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
		        	IXMLElement child = nodoPrimoLivello.createElement("gender");
		        	nodoPrimoLivello.addChild(child);
		        			if(gender.equalsIgnoreCase("Auto"))
		                    child.setContent("Auto");
		        			else 
		        				child.setContent(gender);
		        				
		                    reader.close();
		                    
		                    OutputStream outputStream = new FileOutputStream(xmlFile);
		                    XMLWriter xmlWriter = new XMLWriter(outputStream);
		                    xmlWriter.write(nodoPrimoLivello, true);
		                    outputStream.close();
		                
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

	private void writeDuration(String xmlFile, String fileName) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLException{
		
		FileInputStream inputStream;
		int duration = 0;
		try {
			inputStream = new FileInputStream(fileName);

			byte[] b = new byte[(int) new File(fileName).length()];
			inputStream.read(b);
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(b);
			AudioInputStream pcmAudioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream);
			inputStream.close();

         Clip clip = AudioSystem.getClip();
         clip.open(pcmAudioInputStream);
         duration = (int) (clip.getMicrosecondLength()/1000000);
         if(duration == 0)
        	 duration = 1;
         clip.close();
         pcmAudioInputStream.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
		}
		
        
        int minutes = duration/60;
        int seconds = duration%60;
        System.out.println(minutes + " " + seconds); 
        String effectiveDuration = minutes+":"+(seconds>10 ? seconds : "0"+seconds);
        
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
		        	IXMLElement child = nodoPrimoLivello.createElement("duration");
		        	nodoPrimoLivello.addChild(child);
		                    
							child.setContent(effectiveDuration);
		        				
		                    reader.close();
		                    
		                    OutputStream outputStream = new FileOutputStream(xmlFile);
		                    XMLWriter xmlWriter = new XMLWriter(outputStream);
		                    xmlWriter.write(nodoPrimoLivello, true);
		                    outputStream.close();
		                
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
	
	
    public File getFileXML(){
    	return xmlDiOutput;
    }
    
    
}

