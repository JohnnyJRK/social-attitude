/**
 * 
 */
package it.uniba.di.dalu.ui;

import java.io.File;

import it.uniba.di.dalu.boot.BusinessDelegate;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * @author David
 *
 */
public class RecDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private int indexX;
	private int indexY;
	private Display display;
	private boolean finish = false;
	
	private GC shellGC;
	private Color shellBackground;
	private ImageLoader loader;
	private ImageData[] imageDataArray;
	private volatile Thread animateThread;
	private Image image;
	private final boolean useGIFBackground = false;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	
	  public RecDialog(Shell parent , int x, int y) {
		    // Pass the default styles here
		    this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL );
		    
		    indexX = x;
		    indexY = y;
		    setText("Recording...");
		  }
	
	public RecDialog(Shell parent, int style) {
		super(parent, style);
		setText("Recording...");
	}
	
	
	private void waitStop(){
		
		drawGIF("icons"+File.separator+"recording3a.gif");
		while(!BusinessDelegate.isStoppingRecorder()){
			
		}
		animateThread = null;
		finish = true;
		result = true;
		shell.close();
	}
	
	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
	    shellGC = new GC(shell);
	    shellBackground = shell.getBackground();
		Display display = getParent().getDisplay();
		waitStop();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.BORDER | SWT.TITLE);
		shell.setLocation(indexX , indexY);
		shell.setSize(336, 277);
		shell.setText(getText());
		
		Label lblDescription = new Label(shell, SWT.NONE);
		lblDescription.setBounds(10, 10, 310, 30);
		lblDescription.setText("VoiceCLassifier is capturing your voice,\r\nthe recording stops automatically when it captures silence.");
		
		Label lblLine = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblLine.setText("line");
		lblLine.setBounds(10, 47, 310, 2);
		
		Label lblImage = new Label(shell, SWT.NONE);
	    lblImage.setBounds(148, 113, 40, 40);
	    lblImage.setText("");
	    lblImage.setImage(SWTResourceManager.getImage("icons"+File.separator+"recDlg.png"));


	}
	
	private void drawGIF(String fileName){ 
	 if (fileName != null) {
	      loader = new ImageLoader();
	      try {
	    	imageDataArray = loader.load(fileName);
	        if (imageDataArray.length > 1) {
	          animateThread = new Thread("Animation") {
	            @SuppressWarnings("unused")
				public void run() {
	              /* Create an off-screen image to draw on, and fill it with the shell background. */
	              Image offScreenImage = new Image(display, loader.logicalScreenWidth, loader.logicalScreenHeight);
	              GC offScreenImageGC = new GC(offScreenImage);
	              offScreenImageGC.setBackground(shellBackground);
	              offScreenImageGC.fillRectangle(0, 0, loader.logicalScreenWidth, loader.logicalScreenHeight);
	                
	              try {
	                /* Create the first image and draw it on the off-screen image. */
	                int imageDataIndex = 0;  
	                ImageData imageData = imageDataArray[imageDataIndex];
	                if (image != null && !image.isDisposed()) image.dispose();
	                image = new Image(display, imageData);
	                offScreenImageGC.drawImage(
	                  image,
	                  0,
	                  0,
	                  imageData.width,
	                  imageData.height,
	                  imageData.x,
	                  imageData.y,
	                  imageData.width,
	                  imageData.height);

	                /* Now loop through the images, creating and drawing each one
	                 * on the off-screen image before drawing it on the shell. */
	                int repeatCount = loader.repeatCount;
	                while (loader.repeatCount == 0 || repeatCount > 0) {
	                if(finish)
	                	return;
	                  switch (imageData.disposalMethod) {
	                  case SWT.DM_FILL_BACKGROUND:
	                    /* Fill with the background color before drawing. */
	                    Color bgColor = null;
	                    if (useGIFBackground && loader.backgroundPixel != -1) {
	                      bgColor = new Color(display, imageData.palette.getRGB(loader.backgroundPixel));
	                    }
	                    offScreenImageGC.setBackground(bgColor != null ? bgColor : shellBackground);
	                    offScreenImageGC.fillRectangle(imageData.x, imageData.y, imageData.width, imageData.height);
	                    if (bgColor != null) bgColor.dispose();
	                    break;
	                  case SWT.DM_FILL_PREVIOUS:
	                    /* Restore the previous image before drawing. */
	                    offScreenImageGC.drawImage(
	                      image,
	                      0,
	                      0,
	                      imageData.width,
	                      imageData.height,
	                      imageData.x,
	                      imageData.y,
	                      imageData.width,
	                      imageData.height);
	                    break;
	                  }
	                            
	                  imageDataIndex = (imageDataIndex + 1) % imageDataArray.length;
	                  imageData = imageDataArray[imageDataIndex];
	                  image.dispose();
	                  image = new Image(display, imageData);
	                  offScreenImageGC.drawImage(
	                    image,
	                    0,
	                    0,
	                    imageData.width,
	                    imageData.height,
	                    imageData.x,
	                    imageData.y,
	                    imageData.width,
	                    imageData.height);
	                  
	                  /* Draw the off-screen image to the shell. */
	                  shellGC.drawImage(offScreenImage, 93, 61);
	                  
	                  /* Sleep for the specified delay time (adding commonly-used slow-down fudge factors). */
	                  try {
	                    int ms = imageData.delayTime * 10;
	                    if (ms < 20) ms += 30;
	                    if (ms < 30) ms += 10;
	                    Thread.sleep(ms);
	                  } catch (InterruptedException e) {
	                  }
	                  
	                  /* If we have just drawn the last image, decrement the repeat count and start again. */
	                  if (imageDataIndex == imageDataArray.length - 1) repeatCount--;
	                }
	              } catch (SWTException ex) {
	                System.out.println("There was an error animating the GIF");
	              } finally {
	                if (offScreenImage != null && !offScreenImage.isDisposed()) offScreenImage.dispose();
	                if (offScreenImageGC != null && !offScreenImageGC.isDisposed()) offScreenImageGC.dispose();
	                if (image != null && !image.isDisposed()) image.dispose();
	              }
	            }
	          };
	          animateThread.setDaemon(true);
	          animateThread.start();
	        }
	      } catch (SWTException ex) {
	        System.out.println("There was an error loading the GIF");
	      }
	    }

	}
}
