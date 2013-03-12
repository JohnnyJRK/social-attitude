/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.uniba.di.dalu.boot;

import it.uniba.di.dalu.audiofiles.Player;
import it.uniba.di.dalu.audiofiles.Recorder;
import it.uniba.di.dalu.datasource.WEKAManager;
import it.uniba.di.dalu.prosody.Praat;
import it.uniba.di.dalu.prosody.ProsodiaFileAudio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import net.n3.nanoxml.XMLException;
import weka.classifiers.Classifier;
//import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
/**
 * @author David
 *
 */

public class BusinessDelegate {
    
    private static Recorder recorder = null;
    private static Player player = null;

	
    private static Classifier classifierValence;
    private static Classifier classifierArousal;
    
    
    public static boolean startRecording(String directory, String file){
        if(recorder == null){
            recorder = new Recorder(directory + File.separator + file);
            recorder.setPriority(3);
			try {
                        Thread.sleep(500);
                     } catch (Exception ex) {
      
                    } 
                
            recorder.startRecording();
            return true;
        }
        else{
            return false;
        }
    }
    
    
    public static boolean isStoppingRecorder(){
    	return !recorder.isAlive();
    }
    
    

    public static boolean stopRecording(){
        if(recorder != null){
            recorder = null;
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean playFile(String fileName, Application application) {
    	player = new Player(application);

      	return player.playFile(fileName);
    }

    public static boolean stopFile(){
		return player.stopFile();

	}


    public static ArrayList<String> getFileList(String directory){
        File dir = new File(directory);
        ArrayList<String> list = new ArrayList<String>();
        if(dir.isDirectory()){
            String[] f = dir.list();
            for(int i = 0; i < f.length; i++){
                if(f[i].endsWith(".wav")){
                    list.add(directory + File.separator + f[i]);
                }
            }
            String[] file = new String[list.size()];
            list.toArray(file);
//            return file;
            return list;
        }
        else{
//            return new String[0];
        	return list;
        }
    }
/*
    public static ProsodiaFileAudio[] getAudioList(String directory, String gender) throws IllegalAccessException, XMLException, Exception{
        return getAudioList(getFileList(directory), gender);
    }

    public static ProsodiaFileAudio[] getAudioList(String [] file, String gender) throws IllegalAccessException, XMLException, Exception{
    	ProsodiaFileAudio[] prosodie = new ProsodiaFileAudio[file.length];
        for(int i = 0; i < file.length; i++){
            Praat praat = new Praat(file[i], false, gender);
            try {
                prosodie[i] = praat.getDatiProsodici();
            } catch (Exception ex) {
                ex.printStackTrace();
                prosodie[i] = null;
            }
        }
        return prosodie;
    }
 */   
    public static ProsodiaFileAudio getAudio(String file, String gender) throws Throwable, XMLException, Exception{
    	ProsodiaFileAudio prosodia = null;
        Praat praat = new Praat(file, false, gender);
            try {
                prosodia = praat.getDatiProsodici();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        return prosodia;
    }


    public static String classifiesArousal(ProsodiaFileAudio prosodia, String trainingSet) throws Exception{
    	// attributi da utilizzare per l'addestramento e la classificazione
    	// ottenuti direttamente da weka dopo l'applicazione della Feature Selection
//    	int[] selectedAttributes = {7, 9, 10, 12, 13, 14, 15, 18, 19, 23}; 
    	if(classifierArousal == null){
    		DataSource data = new DataSource(trainingSet);
    		Instances instances = data.getDataSet();
    		instances.setClassIndex(instances.numAttributes() -2);
    		instances.deleteAttributeAt(instances.numAttributes() -1);
//    		instances = deleteAttribute(instances, selectedAttributes);
    	
//    		classifierArousal = new MultilayerPerceptron();
//    		String[] option = {"-L", "0.3",  "-M", "0.2",  "-N", "500",  "-V", "0",  "-S", "0",  "-E", "20",  "-H", "a"};
//    		classifierArousal.setOptions(option);
    		classifierArousal = new J48();
    		classifierArousal.buildClassifier(instances);
    	}
    	WEKAManager weka = new WEKAManager(trainingSet);
    	String header = weka.getARFFHeader();
    	weka = new WEKAManager("model.arff");
    	weka.creaFileARFF(header, retrievesData(prosodia), false);
    	Instances is = weka.getDataSet();
    	is.instance(0);
    	is.setClassIndex(is.numAttributes() -2);
    	is.deleteAttributeAt(is.numAttributes() -1);
    	
//    	is = deleteAttribute(is, selectedAttributes);
    	Instance instance = is.firstInstance();
    	weka.deleteARFFFile();
    	double classe = classifierArousal.classifyInstance(instance);
    	return is.classAttribute().value((int) classe);
    	}


	public static String classifiesValence(ProsodiaFileAudio prosodia, String trainingSet) throws Exception{
		// attributi da utilizzare per l'addestramento e la classificazione
    	// ottenuti direttamente da weka dopo l'applicazione della Feature Selection
//		int[] selectedAttributes = {2, 3, 9, 10, 12, 13, 14, 15, 19, 21, 23};
    	if(classifierValence == null){ 
    		DataSource data = new DataSource(trainingSet);
    		Instances instances = data.getDataSet();
    		instances.setClassIndex(instances.numAttributes() -1);
    		instances.deleteAttributeAt(instances.numAttributes() -2);
//    		instances = deleteAttribute(instances, selectedAttributes);
    	
//    		classifierValence = new MultilayerPerceptron();
//    		String[] option = {"-L", "0.4",  "-M", "0.3",  "-N", "500",  "-V", "0",  "-S", "0",  "-E", "20",  "-H", "a"};
//    		classifierValence.setOptions(option);
    		classifierValence = new J48();
    		classifierValence.buildClassifier(instances);
    	}
    	WEKAManager weka = new WEKAManager(trainingSet);
    	String header = weka.getARFFHeader();
    	weka = new WEKAManager("model.arff");
    	weka.creaFileARFF(header, retrievesData(prosodia), false);
    	Instances is = weka.getDataSet();
    	is.setClassIndex(is.numAttributes() -1);
    	is.deleteAttributeAt(is.numAttributes() -2);
//    	is = deleteAttribute(is, selectedAttributes);
    	Instance instance = is.firstInstance();
    	weka.deleteARFFFile();
    	double classe = classifierValence.classifyInstance(instance);
    	return is.classAttribute().value((int) classe);
    	}
  /*  
    private static Instances deleteAttribute(Instances is, int[] selectedAttributes) {
		// TODO Auto-generated method stub
    	int numAttr = is.numAttributes();
    	int k = 0;
    	int j = 0;
    	for(int i = 0; i < numAttr; i++){
    		if(k < selectedAttributes.length && (i+1) == selectedAttributes[k]){ // (i+1) necessario dovuto al fatto che nella gui di weka gli indici partono da 1 mentre nelle api partono da 0
    			k++;
    		} else{
    			if(is.classIndex() != (i-j)){
    				is.deleteAttributeAt(i-j);
    				j++;
    			}
    		}
    	}
    	return is;
	}
    */
	public static void resetModels(){
    	classifierArousal = null;
    	classifierValence = null;
    }
    
    
    
    private static String[] retrievesData(ProsodiaFileAudio prosodia){
    	String[] instance = new String[26];
        instance[0] = prosodia.getGender();
//      istanza[1] = prosodia.getParteNumerica(prosodia.getSoundMinimo();
//       istanza[1] = prosodia.getParteNumerica(prosodia.getSoundMedia());
//      istanza[3] = prosodia.getParteNumerica(prosodia.getSoundMassimo();
//      istanza[4] = prosodia.getParteNumerica(prosodia.getSoundTempoMinimo();
//      istanza[5] = prosodia.getParteNumerica(prosodia.getSoundTempoMassimo();
//      istanza[6] = prosodia.getParteNumerica(prosodia.getSoundValoreEfficace();
//      istanza[7] = prosodia.getParteNumerica(prosodia.getSoundDeviazioneStandard();
//      istanza[8] = prosodia.getParteNumerica(prosodia.getSoundEnergia();
//      istanza[9] = prosodia.getParteNumerica(prosodia.getSoundPotenza();
      instance[1] = prosodia.getParteNumerica(prosodia.getPitchMinimo());
      instance[2] = prosodia.getParteNumerica(prosodia.getPitchMedio());
      instance[3] = prosodia.getParteNumerica(prosodia.getPitchMassimo());
      instance[4] = String.valueOf(Math.abs(Float.parseFloat(prosodia.getParteNumerica(prosodia.getPitchMassimoLogaritmico())) - Float.parseFloat(prosodia.getParteNumerica(prosodia.getPitchMinimoLogaritmico()))));
      instance[5] = prosodia.getParteNumerica(prosodia.getPitchMinimoLogaritmico());
      instance[6] = prosodia.getParteNumerica(prosodia.getPitchMedioLogaritmico());
      instance[7] = prosodia.getParteNumerica(prosodia.getPitchMassimoLogaritmico());
//      istanza[17] = prosodia.getParteNumerica(prosodia.getPitchTempoMinimo();
//      istanza[18] = prosodia.getParteNumerica(prosodia.getPitchTempoMassimo();
      instance[8] = prosodia.getParteNumerica(prosodia.getPitchDeviazioneStandard());
      instance[9] = prosodia.getParteNumerica(prosodia.getPitchSlope());
      instance[10] = prosodia.getParteNumerica(prosodia.getIntensityMinimo());
      instance[11] = prosodia.getParteNumerica(prosodia.getIntensityMedia());
      instance[12] = prosodia.getParteNumerica(prosodia.getIntesityMassimo());
      instance[13] = String.valueOf(Math.abs(Float.parseFloat(prosodia.getParteNumerica(prosodia.getIntesityMassimo())) - Float.parseFloat(prosodia.getParteNumerica(prosodia.getIntensityMinimo()))));
//      istanza[25] = prosodia.getParteNumerica(prosodia.getIntensityTempoMinimo();
//      istanza[26] = prosodia.getParteNumerica(prosodia.getIntensityTempoMassimo();
      instance[14] = prosodia.getParteNumerica(prosodia.getIntensityDeviazioneStandard());
      instance[15] = prosodia.getParteNumerica(prosodia.getSpectrumCentralMoment());
      instance[16] = prosodia.getParteNumerica(prosodia.getSpectrumDeviazioneStandard());
      instance[17] = prosodia.getParteNumerica(prosodia.getSpectrumGravityCentre());
      instance[18] = prosodia.getParteNumerica(prosodia.getSpectrumKurtosis());
      instance[19] = prosodia.getParteNumerica(prosodia.getSpectrumSkewness());
      instance[20] = prosodia.getParteNumerica(prosodia.getHarmonicityDeviazioneStandard());
      instance[21] = prosodia.getParteNumerica(prosodia.getHarmonicityMassimo());
      instance[22] = prosodia.getParteNumerica(prosodia.getHarmonicityMedia());
      instance[23] = prosodia.getParteNumerica(prosodia.getHarmonicityMinimo());
      instance[24] = "Low";
      instance[25] = "Neutral";
      
      return instance;
    }
    
    

    public static boolean include(ProsodiaFileAudio prosodia, String arousal, String valence, String trainingSetFile){
    	String[] instance = BusinessDelegate.retrievesData(prosodia);
    	
    	instance[24] = arousal;
    	instance[25] = valence;
    	WEKAManager manager = new WEKAManager(trainingSetFile); 
    	try {
			manager.creaFileARFF("", instance, true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
        return true;
    }

}

