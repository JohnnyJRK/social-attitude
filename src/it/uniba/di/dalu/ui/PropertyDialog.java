package it.uniba.di.dalu.ui;

import java.util.Vector;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
/**
 * @author David
 *
 */
public class PropertyDialog extends Dialog {

	private String shell;
	protected String[] result = new String[4];
	protected AudioFormat[] configurations;
	
	protected Shell shlRecProperty;
	
	private int indexX;
	private int indexY;
	private String[] recLast = {"8000","16","2","4"};

	/**
	   * InputDialog constructor
	   *
	   * @param parent the parent
	   */
	  public PropertyDialog(Shell parent , int x, int y, String[] rec, String item) {
	    // Pass the default styles here
	    this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL );
	    shell = item;
	    indexX = x;
	    indexY = y;
	    if (rec.length!=0){
	    recLast = rec;
	    }
	    setText("REC Property");
	  }
	 
	  /**
	   * InputDialog constructor
	   *
	   * @param parent the parent
	   * @param style the style
	   */
	  public PropertyDialog(Shell parent, int style) {
	    // Let users override the default styles
	    super(parent, style);
	    setText("REC Property");
	  }
	

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlRecProperty.open();
		shlRecProperty.layout();
		Display display = getParent().getDisplay();
		while (!shlRecProperty.isDisposed()) {
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
		shlRecProperty = new Shell(getParent(), SWT.BORDER | SWT.TITLE | SWT.APPLICATION_MODAL);
		shlRecProperty.setLocation(indexX , indexY);
		shlRecProperty.setSize(258, 208);
		shlRecProperty.setText("REC Property");
		shlRecProperty.setLayout(null);
		
		Label lblLine = new Label(shlRecProperty, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblLine.setText("line");
		lblLine.setBounds(10, 57, 232, 2);
		
		final Combo param = new Combo(shlRecProperty, SWT.READ_ONLY);
		param.setBounds(10, 97, 232, 25);
		
		
		Vector<AudioFormat> checks = getSupportedFormats(TargetDataLine.class);
		configurations = new AudioFormat[checks.size()];
		int choise = 0;
		for(int i = 0; i< checks.size(); i++){
			configurations[i] = checks.get(i);
			
			String strConf = (int) configurations[i].getFrameRate()+" Hz; "+ (int) configurations[i].getSampleSizeInBits()+" bits; "+
			((configurations[i].getChannels()== 1) ? "Mono;" : "Stereo;");
			
			if( ("" + configurations[i].getFrameRate()).equalsIgnoreCase(recLast[0]) && ("" + configurations[i].getSampleSizeInBits()).equalsIgnoreCase(recLast[1]) && ("" + configurations[i].getChannels()).equalsIgnoreCase(recLast[3]))
				choise = i;
			
			param.add(strConf, i);
		}
		param.select(choise);
		
		Button btnSet = new Button(shlRecProperty, SWT.NONE);
		btnSet.setBounds(10, 147, 75, 25);
		btnSet.setText("SET");
		shlRecProperty.setDefaultButton(btnSet);
		btnSet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int indexSel = param.getSelectionIndex();
				
				result[0] = "" + configurations[indexSel].getFrameRate();
				result[1] = "" + (int) configurations[indexSel].getSampleSizeInBits();
				result[2] = "" + configurations[indexSel].getChannels();
				result[3] = "" + (((int) configurations[indexSel].getSampleSizeInBits()*configurations[indexSel].getChannels())/8);
				shlRecProperty.close();
			}
		});
		
		
		Button btnCancel = new Button(shlRecProperty, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (shell.equalsIgnoreCase("splash")){
				result[0] = "8000.0F";
				result[1] = "16";
				result[2] = "2";
				result[3] = "4";
				shlRecProperty.close();
				} else
					{
					result[0] = recLast[0];
					result[1] = recLast[1];
					result[2] = recLast[2];
					result[3] = recLast[3];
					shlRecProperty.close();
						}
			}
		});
		btnCancel.setBounds(167, 147, 75, 25);
		btnCancel.setText("CANCEL");
		
		Label lblIstructions = new Label(shlRecProperty, SWT.NONE);
		lblIstructions.setBounds(10, 10, 232, 81);
		
		if (shell.equalsIgnoreCase("splash"))
		lblIstructions.setText("Select of REC properties\rthen press SET to confirm or\r\nCANCEL to set the default\n\nSample Rate; Bits in channel; Channel; ");
		else
			lblIstructions.setText("Select REC properties\rthen press SET to confirm or\r\nCANCEL to confirm the last configuration\n\nSample Rate; Bits in channel; Channel; ");

	}

    public static Vector<AudioFormat> getSupportedFormats(Class<?> dataLineClass) {
    /*
     * These define our criteria when searching for formats supported
     * by Mixers on the system.
     */
    float sampleRates[] = { (float) 8000.0, (float) 16000.0, (float) 22050.0, (float) 44100.0, (float) 48000.0 };
    int channels[] = { 1, 2 };
    int bytesPerSample[] = {1, 2, 4};

    AudioFormat format;

    Vector<AudioFormat> formats = new Vector<AudioFormat>();

    for (int a = 0; a < sampleRates.length; a++) {
            for (int b = 0; b < channels.length; b++) {
                for (int c = 0; c < bytesPerSample.length; c++) {
                    format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                            sampleRates[a], 8 * bytesPerSample[c], channels[b], channels[b]*bytesPerSample[c],
                            sampleRates[a], false);
                        /*
                         * TODO: To perform an exhaustive search on supported lines, we should open
                         * TODO: each Mixer and get the supported lines. Do this if this approach
                         * TODO: doesn't give decent results. For the moment, we just work with whatever
                         * TODO: the unopened mixers tell us.
                         */
                    	
                            try{
                                DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
                                @SuppressWarnings("unused")
								TargetDataLine line = (TargetDataLine) AudioSystem.getLine(info);
                                formats.add(format);
                                }catch(Exception e){}
                        
                    }
                }
            }
        
    
    return formats;
}


}
