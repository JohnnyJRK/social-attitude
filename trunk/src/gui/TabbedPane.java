package gui;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedPane extends JFrame implements ActionListener{
	JTabbedPane tab;
	JButton button;
	int index=1;

	public TabbedPane(String title) throws HeadlessException {
		super(title);
		JPanel panel1,panel2,panel3;
		JLayeredPane lpane = new JLayeredPane();
		tab = new JTabbedPane();
		panel1 = new JPanel();
		panel1.add(new JLabel("UNO"));
		tab.addTab("uno", panel1);
		panel2 = new JPanel();
		panel2.add(new JLabel("DUE"));
		//tab.addTab("due", panel);
		panel3 = new JPanel();
		panel3.add(new JLabel("TRE"));
		lpane.add(panel2);
		lpane.add(panel3);
		tab.addTab("tre", lpane);
		button = new JButton("cambia");
		button.addActionListener(this);
		getContentPane().add(tab,BorderLayout.CENTER);
		getContentPane().add(button, BorderLayout.SOUTH);
		this.setSize(450, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);	
        
	}

	public static void main(String...args) {
		TabbedPane pane = new TabbedPane("Test");
	}

	public void actionPerformed(ActionEvent e) {
		int tabs =tab.getTabCount();
		tab.setSelectedIndex(index++);
		if(index==tabs)index=0;
	}
}