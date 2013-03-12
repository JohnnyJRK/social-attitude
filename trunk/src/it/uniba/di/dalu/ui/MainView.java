package it.uniba.di.dalu.ui;


import it.uniba.di.dalu.boot.BusinessDelegate;
import it.uniba.di.dalu.prosody.ProsodiaFileAudio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
/**
 * @author David
 *
 */


public class MainView {
	
	//Display
	protected static Display display;

	
	//Config propriety
	protected static Properties props = new Properties();
	protected OutputStream propOut;
	static protected String directory; 
	
	//Shells
	protected Shell shlVoiceClassifier;
	
	//Text area
	protected Text itemAudioSelected;
	protected Text txtValencePredict;
	protected Text txtArousalPredict;
	protected Label lblAudioList;
	protected Label lblLoadingAudio;
	
	//Progress Bar
	protected ProgressBar loadingBar;
	
	//Commands
	protected Button recButton;
	protected Button playButton;
	protected Button stopButton;
	protected Button btnClassify;
	protected Button btnAddExample;
	
	//List
	protected ArrayList<String> fileList = new ArrayList<String>();
	protected ListViewer audioList;
	protected ListViewer propertyList;
	protected ArrayList<ProsodiaFileAudio> prosodiaList;
	
	//Combo Real Value 
	protected Combo comboValence;
	protected Combo comboArousal;
	
	//Menu Item
	protected MenuItem mntmDirectory;
	protected MenuItem mntmRec;
	protected MenuItem Exit;
	
	protected MenuItem mntmRefresh;
	protected MenuItem mntmEditItem;
	protected MenuItem mntmDeleteItem;
	
	protected MenuItem popupRefresh;
	protected MenuItem popupEdit;
	protected MenuItem popupDelete;
	protected MenuItem mntmAboutVoiceclassifier;

	
	protected String gender = "Auto";
	
	//Position center
	
	protected static int x;
	protected static int y;


	

	/**
	 * Open the window.
	 */
	public void open() {
		System.out.println("OS: "+System.getProperty("os.name"));

		//Open splash screen
		display = Display.getDefault();			
	    final int[] count = new int[1];
	    final Image image = new Image(display, 300, 300);
	    GC gc = new GC(image);
	    Image splashImage = SWTResourceManager.getImage("icons"+File.separator+"splash.png");
	    gc.drawImage(splashImage, 0, 0);
	    gc.dispose();
	    
	    final Shell splash = new Shell(SWT.ON_TOP);
	    final ProgressBar bar = new ProgressBar(splash, SWT.NONE);
	  
	    
	    bar.setMaximum(count[0]);
	    Label label = new Label(splash, SWT.NONE);
	    label.setImage(image);
	    FormLayout layout = new FormLayout();
	    splash.setLayout(layout);
	    FormData labelData = new FormData();
	    labelData.right = new FormAttachment(100, 0);
	    labelData.bottom = new FormAttachment(100, 0);
	    label.setLayoutData(labelData);
	    FormData progressData = new FormData();
	    progressData.left = new FormAttachment(0, 5);
	    progressData.right = new FormAttachment(100, -5);
	    progressData.bottom = new FormAttachment(100, -5);
	    bar.setLayoutData(progressData);
	    splash.pack();
	    Rectangle splashRect = splash.getBounds();
	    Rectangle displayRect = display.getBounds();
	    x = (displayRect.width - splashRect.width) / 2;
	    y = (displayRect.height - splashRect.height) / 2;
	            	    
	    splash.setLocation(x, y);
	    splash.open();
        
	  //set audio file directory
	    try {
			setDirectory(splash);
			setRecProperty(splash, x , y, "splash");
			directory =getDirectory();
				fileList.addAll(BusinessDelegate.getFileList(directory));
			prosodiaList= new ArrayList<ProsodiaFileAudio>();
			if (fileList.size() != 0)
			count[0] = (fileList.size());
			else
				count[0] = 1;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    bar.setMaximum(count[0]);
		display.asyncExec(new Runnable() {
	      public void run() {
	    	
	  	    
	  		Shell[] shells = new Shell[count[0]];
	        
	        for (int i = 0; i < count[0]; i++) {
	          shells[i] = new Shell(display);
	          shells[i].setSize(300, 300);
	          shells[i].addListener(SWT.Close, new Listener() {
	            public void handleEvent(Event e) {
	              --count[0];
	            }
	          });
	          bar.setSelection(i + 1);
	          try {
	        	if (fileList.size() != 0){  
	        		prosodiaList.add(i,BusinessDelegate.getAudio(fileList.get(i), gender));}
	        	else
	        		Thread.sleep(1000);
	          } catch (Throwable e) {
	          }
	        }
            try {
            	Thread.sleep(300);
            	bar.setSelection(bar.getMaximum());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        splash.close();
	        image.dispose();
	        
	        
	        //open application window
			createContents();

			shlVoiceClassifier.open();
			shlVoiceClassifier.layout();
			addListeners();
	      }
	    });

	    while (count[0] != 0) {
	        if (!display.readAndDispatch())
	          display.sleep();
	      }
	      display.dispose();
	      System.exit(0);
	    }
	
	public void open3() {
		System.out.println("OS: "+System.getProperty("os.name"));

		//Open splash screen
		final int[] count = new int[1];
		final Shell splash = new Shell(SWT.ON_TOP);
		display = Display.getDefault();	
		System.out.println("Display.getdefault()\n");
	   
        
	  //set audio file directory
	    try {
			setDirectory(splash);
			setRecProperty(splash, x , y, "splash");
			directory =getDirectory();
			System.out.println("directoryyyyyyyyyy"+directory);
				fileList.addAll(BusinessDelegate.getFileList(directory));
			prosodiaList= new ArrayList<ProsodiaFileAudio>();
			if (fileList.size() != 0)
			count[0] = (fileList.size());
			else
				count[0] = 1;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	   // bar.setMaximum(count[0]);
		display.asyncExec(new Runnable() {
	      public void run() {
	    	
	  	    
	  		Shell[] shells = new Shell[count[0]];
	        
	        for (int i = 0; i < count[0]; i++) {
	          shells[i] = new Shell(display);
	          shells[i].setSize(300, 300);
	          shells[i].addListener(SWT.Close, new Listener() {
	            public void handleEvent(Event e) {
	              --count[0];
	            }
	          });
	          //bar.setSelection(i + 1);
	          try {
	        	if (fileList.size() != 0){  
	        		prosodiaList.add(i,BusinessDelegate.getAudio(fileList.get(i), gender));}
	        	else
	        		Thread.sleep(1000);
	          } catch (Throwable e) {
	          }
	        }
            try {
            	Thread.sleep(300);
            	//bar.setSelection(bar.getMaximum());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        splash.close();
	        System.out.println("splash.close()");
	       // image.dispose();
	        
	        
	        //open application window
			//createContents();
			//System.out.println("createContents()");

			//shlVoiceClassifier.open();
			//shlVoiceClassifier.layout();
			//addListeners();
	      }
	    });

	    while (count[0] != 0) {
	        if (!display.readAndDispatch())
	          display.sleep();
	        System.out.println("display.sleep()");
	      }
	      display.dispose();
	      System.exit(0);
	    }
	protected void addListeners(){
		
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlVoiceClassifier = new Shell(SWT.TITLE | SWT.CLOSE | SWT.MIN);
		shlVoiceClassifier.setBackgroundImage(SWTResourceManager.getImage("icons"+File.separator+"background.png"));
		shlVoiceClassifier.setImage(SWTResourceManager.getImage("icons"+File.separator+"icon.png"));
		shlVoiceClassifier.setToolTipText("This application classifies audio files in real time");
		shlVoiceClassifier.setSize(800, 600);
		shlVoiceClassifier.setText("Voice Classifier");
		shlVoiceClassifier.setLocation(x-200, y-150);
		
		// Toolbar and menu
		Menu menu = new Menu(shlVoiceClassifier, SWT.BAR);
		shlVoiceClassifier.setMenuBar(menu);
		
		MenuItem FileMenu = new MenuItem(menu, SWT.CASCADE);
		FileMenu.setText("File");
		
		Menu fileMenuCascade = new Menu(FileMenu);
		FileMenu.setMenu(fileMenuCascade);
		
		MenuItem mntmNewSubmenu = new MenuItem(fileMenuCascade, SWT.CASCADE);
		mntmNewSubmenu.setText("Property");
		
		Menu propertyCascade = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(propertyCascade);
		
		mntmDirectory = new MenuItem(propertyCascade, SWT.NONE);
		mntmDirectory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		mntmDirectory.setText("Set Directory");
		mntmDirectory.setImage(SWTResourceManager.getImage("icons"+File.separator+"directory.png"));
		
		mntmRec = new MenuItem(propertyCascade, SWT.NONE);
		mntmRec.setText("Set Recording");
		mntmRec.setImage(SWTResourceManager.getImage("icons"+File.separator+"wizard.png"));
		
		Exit = new MenuItem(fileMenuCascade, SWT.NONE);
		Exit.setText("&Exit\tCRTL+Q");
		Exit.setAccelerator(SWT.CTRL+'Q');
		Exit.setImage(SWTResourceManager.getImage("icons"+File.separator+"exit.png"));
				
		MenuItem mntmAudioList = new MenuItem(menu, SWT.CASCADE);
		mntmAudioList.setText("Audio List");
		
		Menu adioListMenuCascade = new Menu(mntmAudioList);
		mntmAudioList.setMenu(adioListMenuCascade);
		
		mntmRefresh = new MenuItem(adioListMenuCascade, SWT.NONE);
		mntmRefresh.setText("&Refresh\tF5");
		mntmRefresh.setAccelerator(SWT.F5);
		mntmRefresh.setImage(SWTResourceManager.getImage("icons"+File.separator+"refresh.png"));
		
		MenuItem menuItem = new MenuItem(adioListMenuCascade, SWT.SEPARATOR);
		menuItem.setText("audioListMuenuSeparator");
		
		mntmEditItem = new MenuItem(adioListMenuCascade, SWT.NONE);
		mntmEditItem.setEnabled(false);
		mntmEditItem.setText("&Edit Item\tCTRL+E");
		mntmEditItem.setAccelerator(SWT.CTRL+'E');
		mntmEditItem.setImage(SWTResourceManager.getImage("icons"+File.separator+"editItem.png"));
		
		mntmDeleteItem = new MenuItem(adioListMenuCascade, SWT.NONE);
		mntmDeleteItem.setEnabled(false);
		mntmDeleteItem.setText("&Delete Item\tCTRL+E");
		mntmDeleteItem.setAccelerator(SWT.CTRL+'D');
		mntmDeleteItem.setImage(SWTResourceManager.getImage("icons"+File.separator+"delItem.png"));
		
		MenuItem mntmHelp = new MenuItem(menu, SWT.CASCADE);
		mntmHelp.setText("Help");
		
		Menu menu_1 = new Menu(mntmHelp);
		mntmHelp.setMenu(menu_1);
		
		mntmAboutVoiceclassifier = new MenuItem(menu_1, SWT.NONE);
		mntmAboutVoiceclassifier.setText("About VoiceClassifier");
		
		//Composite of Audio Recording and feature
		Composite top = new Composite(shlVoiceClassifier, SWT.BORDER);
		
		recButton = new Button(top, SWT.PUSH);
		recButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		recButton.setImage(SWTResourceManager.getImage("icons"+File.separator+"REC.png"));
		recButton.setToolTipText("Click to start recording");
		recButton.setText("REC");
		
		playButton = new Button(top, SWT.PUSH);
		playButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		playButton.setImage(SWTResourceManager.getImage("icons"+File.separator+"PLAY.png"));
		playButton.setToolTipText("Click to play selected recording");
		playButton.setText("PLAY");
		playButton.setEnabled(false);
				
		stopButton = new Button(top, SWT.PUSH);
		stopButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		stopButton.setImage(SWTResourceManager.getImage("icons"+File.separator+"STOP.png"));
		stopButton.setToolTipText("Click to stop selected recording");
		stopButton.setText("STOP");
		stopButton.setEnabled(false);
		
		lblAudioList = new Label(top, SWT.NONE);
		lblAudioList.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblAudioList.setText("Audio List: | " + directory +" |");
		
		Label lblAudioProperty = new Label(top, SWT.NONE);
		lblAudioProperty.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_DARK_SHADOW));
		lblAudioProperty.setText("Audio Property");
		
		Label topSeparator = new Label(top, SWT.SEPARATOR | SWT.HORIZONTAL | SWT.CENTER);
		
		loadingBar = new ProgressBar(top, SWT.NONE);
		loadingBar.setVisible(false);
		
		lblLoadingAudio = new Label(top, SWT.CENTER);
		lblLoadingAudio.setVisible(false);
		lblLoadingAudio.setText("Loading Audio");
		
		audioList = new ListViewer(top, SWT.BORDER | SWT.V_SCROLL);
				
		Menu menuPopup = new Menu(audioList.getList());
		audioList.getList().setMenu(menuPopup);
		
		popupRefresh = new MenuItem(menuPopup, SWT.NONE);
		popupRefresh.setText("&Refresh\tF5");
		popupRefresh.setImage(SWTResourceManager.getImage("icons"+File.separator+"refresh.png"));
		
		new MenuItem(menuPopup, SWT.SEPARATOR);
		
		popupEdit = new MenuItem(menuPopup, SWT.NONE);
		popupEdit.setText("&Edit Item\tCTRL+E");
		popupEdit.setImage(SWTResourceManager.getImage("icons"+File.separator+"editItem.png"));
		popupEdit.setEnabled(false);
		
		popupDelete = new MenuItem(menuPopup, SWT.NONE);
		popupDelete.setText("&Delete Item\tCTRL+D");
		popupDelete.setImage(SWTResourceManager.getImage("icons"+File.separator+"delItem.png"));
		popupDelete.setEnabled(false);
				
		inizializeAudioList();
		
		
		propertyList = new ListViewer(top, SWT.BORDER | SWT.V_SCROLL);
		
		Label lblRecplay = new Label(top, SWT.SEPARATOR | SWT.VERTICAL);
		lblRecplay.setText("rec/play");
		
		Label lblStopload = new Label(top, SWT.SEPARATOR | SWT.VERTICAL);
		lblStopload.setText("stop/load");
		
		Label lblrec = new Label(top, SWT.SEPARATOR | SWT.VERTICAL);
		lblrec.setText("/rec");
		
		Label lblLoad = new Label(top, SWT.SEPARATOR | SWT.VERTICAL);
		lblLoad.setText("load/");
		
		Label lblUp = new Label(top, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblUp.setText("up");
		
		Composite bottom = new Composite(shlVoiceClassifier, SWT.BORDER);
		bottom.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite left = new Composite(bottom, SWT.NONE);
		
		Label lblNewLabel = new Label(left, SWT.CENTER);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblNewLabel.setText("   Audio Item");
		
		itemAudioSelected = new Text(left, SWT.BORDER | SWT.CENTER);
		itemAudioSelected.setText("selected item");
		itemAudioSelected.setEditable(false);
		
		btnClassify = new Button(left, SWT.NONE);
		btnClassify.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnClassify.setToolTipText("Click to classifier audio item");
		btnClassify.setText("Classify");
		btnClassify.setImage(SWTResourceManager.getImage("icons"+File.separator+"CLASSIFY.png"));
		btnClassify.setEnabled(false);
		
		Label lblBottomseparator = new Label(left, SWT.SEPARATOR | SWT.VERTICAL);
		lblBottomseparator.setText("bottomSeparator");
		GroupLayout gl_left = new GroupLayout(left);
		gl_left.setHorizontalGroup(
			gl_left.createParallelGroup(GroupLayout.LEADING)
				.add(gl_left.createSequentialGroup()
					.add(11)
					.add(gl_left.createParallelGroup(GroupLayout.LEADING)
						.add(lblNewLabel, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
						.add(gl_left.createSequentialGroup()
							.add(123)
							.add(itemAudioSelected, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
						.add(gl_left.createSequentialGroup()
							.add(123)
							.add(btnClassify, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
					.add(6)
					.add(lblBottomseparator))
		);
		gl_left.setVerticalGroup(
			gl_left.createParallelGroup(GroupLayout.LEADING)
				.add(gl_left.createSequentialGroup()
					.add(13)
					.add(lblNewLabel)
					.add(5)
					.add(itemAudioSelected, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.add(8)
					.add(btnClassify, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
				.add(lblBottomseparator, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
		);
		left.setLayout(gl_left);
		
		Composite right = new Composite(bottom, SWT.NONE);
		
		txtValencePredict = new Text(right, SWT.BORDER);
		txtValencePredict.setEditable(false);
		txtValencePredict.setText("VelancePredict");
		
		comboValence = new Combo(right, SWT.READ_ONLY);
		comboValence.setItems(new String[] {"Positive", "Neutral", "Negative", "VeryNeg"});
		
		txtArousalPredict = new Text(right, SWT.BORDER);
		txtArousalPredict.setEditable(false);
		txtArousalPredict.setText("ArousalPredict");
		
		comboArousal = new Combo(right, SWT.READ_ONLY);
		comboArousal.setItems(new String[] {"High", "Medium", "Low"});
		
		comboArousal.setEnabled(false);
		comboValence.setEnabled(false);
		
		Label lblColumnsept = new Label(right, SWT.SEPARATOR | SWT.VERTICAL);
		lblColumnsept.setText("columnSept1");
		
		Label columnSept = new Label(right, SWT.SEPARATOR);
		columnSept.setText("columnSept1");
		
		Label lblRowsept = new Label(right, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblRowsept.setText("rowSept");
		
		Label lblValence = new Label(right, SWT.NONE);
		lblValence.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		lblValence.setText("Valence");
		
		Label lblArousal = new Label(right, SWT.NONE);
		lblArousal.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_BLUE));
		lblArousal.setText("Arousal");

		btnAddExample = new Button(right, SWT.NONE);
		btnAddExample.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		btnAddExample.setText("Add Example");
		btnAddExample.setEnabled(false);
		btnAddExample.setToolTipText("Click to add the example to model");
		btnAddExample.setImage(SWTResourceManager.getImage("icons"+File.separator+"ADD_EXAMPLE.png"));
		
		Label lblPredict = new Label(right, SWT.NONE);
		lblPredict.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		lblPredict.setText("Predict Category");
		
		Label lblReal = new Label(right, SWT.NONE);
		lblReal.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		lblReal.setText("Real Category");
		
		Label lblRowsept_1 = new Label(right, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblRowsept_1.setText("rowSept1");
		
		Label lblRowsept_2 = new Label(right, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblRowsept_2.setText("rowSept2");
		
		Label label = new Label(right, SWT.SEPARATOR | SWT.VERTICAL);
		
		Label lblLl = new Label(right, SWT.SEPARATOR | SWT.VERTICAL);
		lblLl.setText("ll");
		
		Label lblSup = new Label(right, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblSup.setText("sup");
		GroupLayout gl_right = new GroupLayout(right);
		gl_right.setHorizontalGroup(
			gl_right.createParallelGroup(GroupLayout.LEADING)
				.add(gl_right.createSequentialGroup()
					.add(10)
					.add(gl_right.createParallelGroup(GroupLayout.LEADING)
						.add(gl_right.createSequentialGroup()
							.add(109)
							.add(lblSup, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE))
						.add(gl_right.createSequentialGroup()
							.add(134)
							.add(lblPredict, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.add(lblRowsept_1, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
						.add(gl_right.createSequentialGroup()
							.add(24)
							.add(lblArousal, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
						.add(gl_right.createSequentialGroup()
							.add(265)
							.add(comboArousal, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
						.add(label)
						.add(gl_right.createSequentialGroup()
							.add(265)
							.add(comboValence, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
						.add(gl_right.createSequentialGroup()
							.add(132)
							.add(txtArousalPredict, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
						.add(gl_right.createSequentialGroup()
							.add(24)
							.add(lblValence, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
						.add(lblRowsept, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE)
						.add(gl_right.createSequentialGroup()
							.add(132)
							.add(txtValencePredict, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
						.add(gl_right.createSequentialGroup()
							.add(109)
							.add(columnSept))
						.add(gl_right.createSequentialGroup()
							.add(280)
							.add(lblReal, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.add(gl_right.createSequentialGroup()
							.add(250)
							.add(lblColumnsept)))
					.add(lblLl))
				.add(gl_right.createSequentialGroup()
					.add(10)
					.add(lblRowsept_2, GroupLayout.PREFERRED_SIZE, 375, GroupLayout.PREFERRED_SIZE))
				.add(gl_right.createSequentialGroup()
					.add(124)
					.add(btnAddExample, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
		);
		gl_right.setVerticalGroup(
			gl_right.createParallelGroup(GroupLayout.LEADING)
				.add(gl_right.createSequentialGroup()
					.add(5)
					.add(gl_right.createParallelGroup(GroupLayout.LEADING)
						.add(gl_right.createSequentialGroup()
							.add(lblSup)
							.add(3)
							.add(lblPredict)
							.add(7)
							.add(lblRowsept_1)
							.add(gl_right.createParallelGroup(GroupLayout.LEADING)
								.add(gl_right.createSequentialGroup()
									.add(60)
									.add(lblArousal))
								.add(gl_right.createSequentialGroup()
									.add(57)
									.add(comboArousal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.add(label, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.add(gl_right.createSequentialGroup()
									.add(13)
									.add(comboValence, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.add(gl_right.createSequentialGroup()
									.add(57)
									.add(txtArousalPredict, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.add(gl_right.createSequentialGroup()
									.add(16)
									.add(lblValence))
								.add(gl_right.createSequentialGroup()
									.add(46)
									.add(lblRowsept))))
						.add(gl_right.createSequentialGroup()
							.add(42)
							.add(txtValencePredict, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.add(columnSept, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
						.add(gl_right.createSequentialGroup()
							.add(5)
							.add(lblReal))
						.add(lblColumnsept, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
						.add(lblLl, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
					.add(lblRowsept_2)
					.add(20)
					.add(btnAddExample, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
		);
		right.setLayout(gl_right);
		GroupLayout gl_shlVoiceClassifier = new GroupLayout(shlVoiceClassifier);
		gl_shlVoiceClassifier.setHorizontalGroup(
			gl_shlVoiceClassifier.createParallelGroup(GroupLayout.LEADING)
				.add(gl_shlVoiceClassifier.createSequentialGroup()
					.add(5)
					.add(gl_shlVoiceClassifier.createParallelGroup(GroupLayout.LEADING)
						.add(top, GroupLayout.PREFERRED_SIZE, 784, GroupLayout.PREFERRED_SIZE)
						.add(bottom, GroupLayout.PREFERRED_SIZE, 784, GroupLayout.PREFERRED_SIZE)))
		);
		gl_shlVoiceClassifier.setVerticalGroup(
			gl_shlVoiceClassifier.createParallelGroup(GroupLayout.LEADING)
				.add(gl_shlVoiceClassifier.createSequentialGroup()
					.add(5)
					.add(top, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
					.add(5)
					.add(bottom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		GroupLayout gl_top = new GroupLayout(top);
		gl_top.setHorizontalGroup(
			gl_top.createParallelGroup(GroupLayout.LEADING)
				.add(gl_top.createSequentialGroup()
					.add(11)
					.add(gl_top.createParallelGroup(GroupLayout.LEADING)
						.add(gl_top.createSequentialGroup()
							.add(lblrec)
							.add(15)
							.add(recButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.add(140)
							.add(stopButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
						.add(gl_top.createSequentialGroup()
							.add(128)
							.add(lblRecplay))
						.add(gl_top.createSequentialGroup()
							.add(151)
							.add(playButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
						.add(lblUp, GroupLayout.PREFERRED_SIZE, 757, GroupLayout.PREFERRED_SIZE)
						.add(gl_top.createSequentialGroup()
							.add(432)
							.add(loadingBar, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE))
						.add(gl_top.createSequentialGroup()
							.add(439)
							.add(lblLoadingAudio, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
						.add(gl_top.createSequentialGroup()
							.add(756)
							.add(lblLoad))
						.add(gl_top.createSequentialGroup()
							.add(366)
							.add(lblStopload))
						.add(topSeparator, GroupLayout.PREFERRED_SIZE, 758, GroupLayout.PREFERRED_SIZE)))
				.add(gl_top.createSequentialGroup()
					.add(12)
					.add(lblAudioList, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
					.add(7)
					.add(lblAudioProperty, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
				.add(gl_top.createSequentialGroup()
					.add(10)
					.add(audioList.getList(), GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(propertyList.getList(), GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE))
		);
		gl_top.setVerticalGroup(
			gl_top.createParallelGroup(GroupLayout.LEADING)
				.add(gl_top.createSequentialGroup()
					.add(3)
					.add(gl_top.createParallelGroup(GroupLayout.LEADING)
						.add(lblrec, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.add(gl_top.createSequentialGroup()
							.add(10)
							.add(recButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.add(gl_top.createSequentialGroup()
							.add(10)
							.add(stopButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.add(lblRecplay, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.add(gl_top.createSequentialGroup()
							.add(10)
							.add(playButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.add(lblUp)
						.add(gl_top.createSequentialGroup()
							.add(33)
							.add(loadingBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.add(gl_top.createSequentialGroup()
							.add(7)
							.add(lblLoadingAudio, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.add(lblLoad, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.add(lblStopload, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.add(gl_top.createSequentialGroup()
							.add(61)
							.add(topSeparator)))
					.add(8)
					.add(gl_top.createParallelGroup(GroupLayout.LEADING)
						.add(lblAudioList)
						.add(lblAudioProperty))
					.add(8)
					.add(gl_top.createParallelGroup(GroupLayout.LEADING)
						.add(audioList.getList(), GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
						.add(propertyList.getList(), GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)))
		);
		top.setLayout(gl_top);
		shlVoiceClassifier.setLayout(gl_shlVoiceClassifier);

	}
	
	
	protected void setDirectory( Shell inputShell) throws IOException{
		
		 DirectoryDialog dlg = new DirectoryDialog(inputShell, SWT.TITLE | SWT.APPLICATION_MODAL);
		 	File propFile = new File("props.stat");
		 	
			String text="";
		 	
			if (propFile.exists()){
		 	props.load(new FileInputStream(propFile));
			String directory = props.getProperty("fileDirectory");
			
			if (directory!="")
				text = directory;
			}
			// Set the initial filter path according
	        // to anything they've selected or typed in
	        dlg.setFilterPath(text);

	        // Change the title bar text
	        dlg.setText("Audio File Directory");

	        // Customizable message displayed in the dialog
	        dlg.setMessage("Select a directory");

	        // Calling open() will open and run the dialog.
	        // It will return the selected directory, or
	        // null if user cancels
	        String dir = dlg.open();
	        System.out.println("dlg.opennnnnnnnnn "+dir);
	        if (dir != null) {
	          // Set the text box to the new selection
	        props.setProperty("fileDirectory", dir);
	        props.store(new FileOutputStream(propFile), "Configuration Properties");
	        
	        }
		
	}
	
	protected String getDirectory() throws IOException{
		
		props.load(new FileInputStream("props.stat"));
		String directory = props.getProperty("fileDirectory");
		
		return directory;
		
	}
	
	protected void setRecProperty( Shell inputShell, int x , int y, String recShell) throws IOException{
		
		String[] recLast = null;
		File propFile = new File("props.stat");
		if (propFile.exists()){
			recLast = getRecProperty();
			}
		
		 PropertyDialog dlg = new PropertyDialog(inputShell, x, y, recLast, recShell);
		
	        String[] rec = (String[]) dlg.open();
	        if (rec[0] != null) {
	          	// Set the text box to the new selection
	        	props.setProperty("sampleRate", rec[0]);
	        	props.store(new FileOutputStream(propFile), "Configuration Properties");
	        
	        }
	        if (rec[1] != null) {
		          // Set the text box to the new selection
		        props.setProperty("bit", rec[1]);
		        props.store(new FileOutputStream(propFile), "Configuration Properties");
		        
		        }
	        if (rec[2] != null) {
		          // Set the text box to the new selection
		        props.setProperty("channel", rec[2]);
		        props.store(new FileOutputStream(propFile), "Configuration Properties");
		        
		        }
	        if (rec[3] != null) {
		          // Set the text box to the new selection
		        props.setProperty("bytesFrame", rec[3]);
		        props.store(new FileOutputStream(propFile), "Configuration Properties");
		        
		        }

	}
	
	public static String[] getRecProperty() throws IOException{
		
		String[] rec = new  String[4];
		
		props.load(new FileInputStream("props.stat"));
		rec[0] = props.getProperty("sampleRate");
		rec[1] = props.getProperty("bit");
		rec[2] = props.getProperty("channel");
		rec[3] = props.getProperty("bytesFrame");
		
		return rec;
		
	}

	
	protected void changeAudioList(ProgressBar bar, String genderFile){
		
		prosodiaList = new ArrayList<ProsodiaFileAudio>();
		if (fileList.size() != 0){
			System.out.println("filelength: "+fileList.size());
		bar.setMaximum(fileList.size());	
		for (int i = 0; i < bar.getMaximum(); i++) {
	          bar.setSelection(i + 1);
	          try {
	        	 
	      	    prosodiaList.add(BusinessDelegate.getAudio(fileList.get(i), genderFile));

	      	    audioList.getList().add(prosodiaList.get(i).getNomeFileAudio());
	          } catch (Throwable e1) {
	        	  e1.printStackTrace();
	          }
	        }
		} else { 
			try {
				bar.setMaximum(1);
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
        try {
        	Thread.sleep(300);
        	bar.setSelection(bar.getMaximum());
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.print("items loaded : ");
        for (int i = 0; i < fileList.size(); i++) {
	      	  System.out.print(prosodiaList.get(i).getNomeFileAudio()+" ");
        }
        System.out.print(";\n");
		}
	
	private void inizializeAudioList(){
		audioList.getList().removeAll();
		
		for (int i=0; i< fileList.size(); i++){
			audioList.getList().add(prosodiaList.get(i).getNomeFileAudio());
		}
		System.out.print("items loaded : ");
        for (int i = 0; i < fileList.size(); i++) {
	      	  System.out.print(prosodiaList.get(i).getNomeFileAudio()+" ");
        }
        System.out.print(";\n");
	}
	
	protected void changePropertyList(int index){
		propertyList.getList().removeAll();
		
		
		String PicthMinMaxDifLog = String.valueOf(Math.abs(Float.parseFloat(prosodiaList.get(index).getParteNumerica(prosodiaList.get(index).getPitchMassimoLogaritmico()))
															- Float.parseFloat(prosodiaList.get(index).getParteNumerica(prosodiaList.get(index).getPitchMinimoLogaritmico()))));
		
		String EnergyMinMaxDif = String.valueOf(Math.abs(Float.parseFloat(prosodiaList.get(index).getParteNumerica(prosodiaList.get(index).getIntesityMassimo()))
				- Float.parseFloat(prosodiaList.get(index).getParteNumerica(prosodiaList.get(index).getIntensityMinimo()))));
		
		String gender;
		if (prosodiaList.get(index).getGender().equalsIgnoreCase("m"))
			gender = "Male";
		else
			gender = "Female";
		propertyList.add("Speaker's Gender : " + gender);
		propertyList.add("Audio Duration : " + prosodiaList.get(index).getDuration());
		propertyList.add("Minimum Pitch : " + prosodiaList.get(index).getPitchMinimo());
		propertyList.add("Medium Pitch : " + prosodiaList.get(index).getPitchMedio());
		propertyList.add("Maximum Pitch : " + prosodiaList.get(index).getPitchMassimo());
		propertyList.add("Minimum logarithmic Pitch : " + prosodiaList.get(index).getPitchMinimoLogaritmico());
		propertyList.add("Medium logarithmic Pitch : " + prosodiaList.get(index).getPitchMedioLogaritmico());
		propertyList.add("Maximum logarithmic Pitch : " + prosodiaList.get(index).getPitchMassimoLogaritmico());
		propertyList.add("Pitch Logarithmic Difference Min and Max : " + PicthMinMaxDifLog);
		propertyList.add("Pitch Standard Deviation : " + prosodiaList.get(index).getPitchDeviazioneStandard());
		propertyList.add("Pitch Slope : " + prosodiaList.get(index).getPitchSlope());
		
		propertyList.add("Minimum Energy : " + prosodiaList.get(index).getIntensityMinimo());
		propertyList.add("Medium Energy : " + prosodiaList.get(index).getIntensityMedia());
		propertyList.add("Maximum Energy : " + prosodiaList.get(index).getIntesityMassimo());
		propertyList.add("Energy Standard Deviation : " + prosodiaList.get(index).getIntensityDeviazioneStandard());
		propertyList.add("Energy Difference Min and Max : " + EnergyMinMaxDif);
				
		propertyList.add("Spectrum Central Moment : " + prosodiaList.get(index).getSpectrumCentralMoment());
		propertyList.add("Spectrum Standard Deviation : " + prosodiaList.get(index).getSpectrumDeviazioneStandard());
		propertyList.add("Spectrum Gravity Center : " + prosodiaList.get(index).getSpectrumGravityCentre());
		propertyList.add("Spectrum Kurtosis : " + prosodiaList.get(index).getSpectrumKurtosis());
		propertyList.add("Spectrum Skewness : " + prosodiaList.get(index).getSpectrumSkewness());
		
		propertyList.add("Harmonicity Standard Deviation : " + prosodiaList.get(index).getHarmonicityDeviazioneStandard());
		propertyList.add("Minimum Harmonicity : " + prosodiaList.get(index).getHarmonicityMinimo());
		propertyList.add("Medium Harmonicity : " + prosodiaList.get(index).getHarmonicityMedia());
		propertyList.add("Maximum Harmonicity : " + prosodiaList.get(index).getHarmonicityMassimo());
	}
}
