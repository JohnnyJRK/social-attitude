package it.uniba.di.dalu.ui;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class InputRecDialog extends Dialog {

	protected String[] result = new String[2];
	protected Shell shell;
	private Combo genderCombo;
	private Text fileNametxt;
	private String message;
	private String initialValue;
	private Boolean rename;
	
    private int indexX = MainView.x;
    private int indexY = MainView.y;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public InputRecDialog(Shell parent, String title, String question, String Value , Boolean rename) {
		super(parent);
		setText(title);
		message = question;
		initialValue = Value;
		this.rename = rename;
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
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
		shell.setSize(464, 229);
		shell.setText(getText());
		shell.setLocation(indexX , indexY);
		
		Label lblMessage = new Label(shell, SWT.NONE);
		lblMessage.setBounds(10, 10, 438, 63);
		lblMessage.setText(message);
		
		Label lblGender = new Label(shell, SWT.NONE);
		lblGender.setBounds(10, 82, 55, 15);
		lblGender.setText("Gender :");
		
		genderCombo = new Combo(shell, SWT.READ_ONLY);
		genderCombo.setTextLimit(9999);
		genderCombo.setToolTipText("Select Gender's speaker");
		genderCombo.setItems(new String[] {"Male", "Female", "Auto"});
		genderCombo.setBounds(109, 79, 156, 23);
		genderCombo.select(2);
		if (this.rename){
			lblGender.setVisible(false);
			genderCombo.setVisible(false);
		}
		Label lblFileName = new Label(shell, SWT.NONE);
		lblFileName.setBounds(10, 122, 70, 15);
		lblFileName.setText("File Name : ");
		
		fileNametxt = new Text(shell, SWT.BORDER);
		fileNametxt.setBounds(109, 119, 339, 18);
		fileNametxt.setText(initialValue);
		
		Button btnCancel = new Button(shell, SWT.NONE);
		btnCancel.setBounds(373, 166, 75, 25);
		btnCancel.setText("Cancel");
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				result[0]= "null";
				result[1]= "null";
				shell.close();
			}
		});		
		
		Button btnOk = new Button(shell, SWT.NONE);
		btnOk.setBounds(292, 166, 75, 25);
		btnOk.setText("OK");
		shell.setDefaultButton(btnOk);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String gender;
				if (genderCombo.getItem(genderCombo.getSelectionIndex()).equalsIgnoreCase("Male"))
					gender = "m";
					else if(genderCombo.getItem(genderCombo.getSelectionIndex()).equalsIgnoreCase("Female"))
						gender = "f";
					else
						gender = "Auto";
				result[0]= gender;
				result[1]= fileNametxt.getText();
				shell.close();
			}
		});

	}
}
