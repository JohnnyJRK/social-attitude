package it.uniba.di.dalu.audiofiles;

import it.uniba.di.dalu.boot.Application;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 * @author David
 *
 */
public class Player extends Thread {
	
	private Clip clip = null;
	private AudioInputStream pcmAudioInputStream = null;
	private Application application;
	
	public Player(Application application){
		this.application = application;
		
	}
	
	public Player(){
		super();
	}
	
    public boolean playFile(String fileName) {
        
    	if(clip != null){
			clip.close();
        	try {
				pcmAudioInputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
System.out.println("Ascolta");
        try {
        	
            FileInputStream inputStream= new FileInputStream(fileName);
			byte[] b = new byte[(int) new File(fileName).length()];
			inputStream.read(b);
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(b);
			pcmAudioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream);
			inputStream.close();

            clip = AudioSystem.getClip();
            clip.open(pcmAudioInputStream);

            start();
            clip.start();
           

            
            return true;
        } 
        catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
    }
    
    public boolean stopFile(){
		try{
			application.resetStopButton();
			clip.stop();
			clip.close();
			return true;
		}catch(Exception e){
			return false;
		}
	}
    public boolean stopFile2(){
		try{
			//application.resetStopButton();
			clip.stop();
			clip.close();
			return true;
		}catch(Exception e){
			return false;
		}
	}
    @Override
    public void run() {
    	try {
			Thread.sleep(clip.getMicrosecondLength()/1000);
			
			if(clip.isOpen())
				stopFile();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Clip close: "+ !clip.isOpen());
		}
    }

}
