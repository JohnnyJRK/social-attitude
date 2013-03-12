/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.uniba.di.dalu.audiofiles;

//package com.javasrc.audio;
import it.uniba.di.dalu.ui.MainView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

/**
 * Sample audio recorder
 */
public class Recorder extends Thread {

    private int SILENCE = 0;
	/**
     * The TargetDataLine that we’ll use to read data from
     */
    private TargetDataLine line;
    /**
     * The audio format type that we’ll encode the audio data with
     */
    private AudioFileFormat.Type targetType = AudioFileFormat.Type.WAVE;
    /**
     * The AudioInputStream that we’ll read the audio data from
     */
    @SuppressWarnings("unused")
	private AudioInputStream inputStream;
    /**
     * The file that we’re going to write data out to
     */
    private File file;

    /**
     * Indica se il file sonoro sarà salvato nel formato ALAW oppure in quello di default PCM
     */
    private boolean registerInALAW;
    
    
    AudioFormat audioFormat;
    
    private String [] param;
	
    //Recording manager
    private ByteArrayOutputStream byteArrayOutputStream;
	private AudioInputStream ais;

    private int notSilence = 0;
	
    //Stop silence vars
    private boolean stopCapture = false;
    private int byteFrameLength;
    private int thresholdSilence = 0;
    private int thresholdSpeach = 0;
 
	
	
    /**
     * Creates a new Audio Recorder
     */
    public Recorder(String outputFilename) {
    	byteArrayOutputStream = new ByteArrayOutputStream();
    	try {
			param = MainView.getRecProperty();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
           	
                audioFormat = new AudioFormat(
                        AudioFormat.Encoding.PCM_SIGNED, // Encoding technique
                        Float.valueOf(param[0]), // Sample Rate
                        Integer.parseInt(param[1]), // Number of bits in each channel
                        Integer.parseInt(param[2]), // Number of channels (2=stereo)
                        Integer.parseInt(param[3]), // Number of bytes in each frame
                        Float.valueOf(param[0]), // Number of frames per second
                        false);                            // Big-endian (true) or little-
//            // endian (false)

            if(audioFormat.getSampleSizeInBits() == 8){
            	SILENCE = 4;
            }else{
            	SILENCE = 1024;
            }
                
            // Create our TargetDataLine that will be used to read audio data by first
            // creating a DataLine instance for our audio format type
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);

            // Next we ask the AudioSystem to retrieve a line that matches the
            // DataLine Info
            this.line = (TargetDataLine) AudioSystem.getLine(info);
            
            // Open the TargetDataLine with the specified format
            this.line.open(audioFormat);

            // Create an AudioInputStream that we can use to read from the line
            this.inputStream = new AudioInputStream(this.line);

            // Create the output file
            this.file = new File(outputFilename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startRecording() {
        // Start the TargetDataLine
        this.line.start();

        // Start our thread
        
        start();
        
    }


    public void stopRecording() {
        // Stop and close the TargetDataLine
        this.line.close();      
        System.out.println("stop");

        try {
        	
        	byte[] localByteArray = byteArrayOutputStream.toByteArray();
        	byte[] localFilterByteArray = new byte[(int) (byteFrameLength*(audioFormat.getSampleSizeInBits()/8)-(audioFormat.getFrameRate()))];
        	
        	for(int i=1; i<localFilterByteArray.length; i++){
        		localFilterByteArray[i] = localByteArray[i];
        	}
        	
        	System.out.println("localFilterByteArray: "+ localFilterByteArray.length);
        	ByteArrayInputStream baisInput = new ByteArrayInputStream(localFilterByteArray);
            ais = new AudioInputStream(baisInput, audioFormat, localFilterByteArray.length / (audioFormat.getChannels()*audioFormat.getSampleSizeInBits()/8));
			System.out.println("noSilence: " + notSilence + " th: " + thresholdSpeach);
			System.out.println((notSilence > audioFormat.getFrameRate() && thresholdSpeach > 100));
			System.out.println((notSilence > audioFormat.getFrameRate()));
			System.out.println((thresholdSpeach > 100));
            if(notSilence > (audioFormat.getFrameRate() * audioFormat.getChannels() / 10) && thresholdSpeach > 0)
            	AudioSystem.write(ais, this.targetType, this.file);
            else{
            	this.file.delete();
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.file = null;
    }

    @Override
    public void run() {
    	try {
            Thread.sleep(1000);
         } catch (Exception ex) {}
        try {
        	while(!stopCapture){
        		  //Read data from the internal buffer of
        		  // the data line.
        		byte[] tempBuffer = new byte[8000];
        		  int cnt = line.read(tempBuffer,
        		                        0,
        		                        tempBuffer.length);

        		  if(cnt > 0){
        		    //Save data in output stream object.
  
        		    byteArrayOutputStream.write(tempBuffer,
        		                                0,
        		                                cnt);
        		    byte[] bytePull = new byte[cnt];
        		    for(int ii = 0; ii < cnt; ii++){
        		    	bytePull[ii] = tempBuffer[ii];
        		    }
          			int[] audioData = extractAmplitudeDataFromAmplitudeByteArray(audioFormat, bytePull);
        			for(int i = 0; i < audioData.length; i++){
        				byteFrameLength++;
        				if(audioData[i] < SILENCE && audioData[i] > -SILENCE){
        					thresholdSilence++;
        					if(thresholdSilence > audioFormat.getFrameRate() * audioFormat.getChannels()){
                				
        						this.line.stop();
                				this.line.flush();
                				
                				stopCapture = true;
                				stopRecording();
                				return;
                		        
                			}
        				}else{
        					notSilence++;
        					thresholdSpeach++;
        					if(thresholdSpeach > audioFormat.getFrameRate() / 20){
                    			
                				thresholdSilence = 0;
								thresholdSpeach = 0;
							}
        				}
        			}
        			
        		    
        		    
        		    }//end if
        		  }//end while
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int[] extractAmplitudeDataFromAmplitudeByteArray(AudioFormat format, byte[] audioBytes) {  
        // convert  
        // TODO: calculate duration here  
        int[] audioData = null;  
        if (format.getSampleSizeInBits() == 16) {  
             int nlengthInSamples = audioBytes.length / 2;  
             audioData = new int[nlengthInSamples];  
             if (format.isBigEndian()) {  
                  for (int i = 0; i < nlengthInSamples; i++) {  
                       /* First byte is MSB (high order) */  
                       int MSB = audioBytes[2 * i];  
                       /* Second byte is LSB (low order) */  
                       int LSB = audioBytes[2 * i + 1];  
                       audioData[i] = MSB << 8 | (255 & LSB);  
                  }  
             } else {  
                  for (int i = 0; i < nlengthInSamples; i++) {  
                       /* First byte is LSB (low order) */  
                       int LSB = audioBytes[2 * i];  
                       /* Second byte is MSB (high order) */  
                       int MSB = audioBytes[2 * i + 1];  
                       audioData[i] = MSB << 8 | (255 & LSB);  
                  }  
             }  
        } else if (format.getSampleSizeInBits() == 8) {  
             int nlengthInSamples = audioBytes.length;  
             audioData = new int[nlengthInSamples];  
             if (format.getEncoding().toString().startsWith("PCM_SIGN")) {  
                  // PCM_SIGNED  
                  for (int i = 0; i < audioBytes.length; i++) {  
                       audioData[i] = audioBytes[i];  
                  }  
             } else {  
                  // PCM_UNSIGNED  
                  for (int i = 0; i < audioBytes.length; i++) {  
                       audioData[i] = audioBytes[i] - 128;  
                  }  
             }  
        }// end of if..else  
             // System.out.println("PCM Returned===============" +  
             // audioData.length);  
        return audioData;  
   } 

    /**
     * @return Indica se questa classe è impostata per registrare in modalità ALAW
     * oppure in quella standard PCM
     */
    public boolean isRegisterInALAW() {
        return registerInALAW;
    }

    /**
     * Consente di impostare la registrazione nel formato ALAW oppure nel formato
     * standard
     * @param registerInALAW Indica se deve registrare nel formato ALAW
     */
    public void setRegisterInALAW(boolean registerInALAW) {
        this.registerInALAW = registerInALAW;
    }
    

    public static AudioInputStream getAudioStreamFromPCMArray(int[] audioDataIntArr, AudioFormat format) {
        byte[] data = null;
        int nlengthInSamples = audioDataIntArr.length * 2;
        if (format.getSampleSizeInBits() == 16) {
            // FIXME: debug at full length, signed/unsigned problem
            data = new byte[nlengthInSamples];
            if (format.isBigEndian()) {
                for (int i = 0; i < audioDataIntArr.length; i++) {
                    int temp = Math.abs((short) (audioDataIntArr[i] * 255));
                    data[2 * i + 1] = (byte) temp;
                    data[2 * i + 0] = (byte) (temp >> 8);
                }
            } else {
                for (int i = 0; i < audioDataIntArr.length; i++) {
                    int temp = Math.abs((short) (audioDataIntArr[i] * 255));
                    data[2 * i + 0] = (byte) temp;
                    data[2 * i + 1] = (byte) (temp >> 8);
                }
            }
        } else if (format.getSampleSizeInBits() == 8) {
            nlengthInSamples = audioDataIntArr.length;
            data = new byte[nlengthInSamples];
            if (format.getEncoding().toString().startsWith("PCM_SIGN")) {
                // PCM_SIGNED
                for (int i = 0; i < nlengthInSamples; i++) {
                    data[i] = (byte) audioDataIntArr[i];
                }
            } else {
                // PCM_UNSIGNED
                for (int i = 0; i < nlengthInSamples; i++) {
                    data[i] = (byte) (audioDataIntArr[i] + 128);
                }
            }
        }// end of if..else
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            AudioInputStream ais = new AudioInputStream(bais, format, audioDataIntArr.length);
            return ais;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}