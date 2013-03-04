package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;


import networkManager.HuginInterface;
import networkManager.Nodes;
//prova svn
/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Finestra extends javax.swing.JFrame {

	/*{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
*/

	private static final long serialVersionUID = 1L;
	private JPanel pnlLingEvidenze;
	private JLabel lblContx;
	private JLabel lblLeng;
	private JList jListFrasi;
	private JPanel jpnlGestiImp;
	private JPanel jpnlAudioImp;
	private JCheckBox jrbGesti;
	private JCheckBox jrbLing;
	private JCheckBox jrbAudio;
	private JPanel jpnlLingImp;
	private ChartPanel chartPanel3;
	private JButton btnAudioRimuovi;
	private JButton btnAudioAggiungi;
	private JLabel jLabel10;
	private JTable tblAudioEvidenze;
	private JScrollPane jScrollPane3;
	private JButton btnAudioReset;
	private JComboBox cmbAr;
	private JComboBox cmbVal;
	private JLabel lblAr;
	private JLabel lblVal;
	private JPanel pnlAudioAV;
	private JButton jButton1;
	private JLabel lblGestiFrasi;
	private JLabel lblAudioFrasi;
	private JScrollPane jScrollPane4;
	private ChartPanel chartPanel4;
	private JPanel jPanel1;
	private JList jListAudioFrasi;
	private JList jListGestiFrasi;
	private JScrollPane jScrollPane2;
	private JLabel jLabel1;
	private JPanel pnlAudioEvidenze;
	private ButtonGroup btnGrp;
	private JLabel lblLingFrasi;
	private DefaultListModel jListFrasiModel;
	private JScrollPane jscrollpFrasi;
	ListModel jList1Model;
	private JButton jbtGesti;
	private JButton jbtLing;
	private JButton jbtAudio;
	private ChartPanel chartPanel2;
	private ChartPanel chartPanel1;
	private JPanel pnlGestiSA;
	private JButton jButton3;
	private JComboBox cmbHands;
	private JComboBox cmbLegs;
	private JLabel lblHands;
	private JLabel lblLegs;
	private JComboBox cmbArms;
	private JLabel lblArms;
	private JPanel pnlSegniLing;
	private JPanel pnlGestiEvidenze;
	private JScrollPane jScrollPane1;
	private JTable tblEvidenze;
	private JButton btnReset;
	private JButton btnAggiungi;
	private JComboBox cmbQmar;
	private JLabel lblQmar;
	private JTextField txtLeng;
	private JComboBox cmbYou;
	private JComboBox cmbMe;
	private JLabel lblYou;
	private JLabel lblMe;
	private JComboBox cmbCiao;
	private JLabel lblCiao;
	private JComboBox cmbConf;
	private JLabel lblConf;
	private JPanel pnlSegni;
	private JComboBox cmbMtype;
	private JComboBox cmbContx;
	private JLabel lblMtype;
	private JPanel pnlContesto;
	private JPanel pnlLingSA;
	private ChartPanel pnlAnd;
	private ChartPanel pnlIst;

	private HuginInterface hi;
	private ArrayList<double[]> sa_ling_history;
	private ArrayList<double[]> sa_audio_history;
	private ArrayList<double[]> sa_gesti_history;
	private JPanel pnlGenResult;
	private JPanel pnlGenSA;
	private JPanel pnlGenerale;
	private JPanel pnlGesti;
	private JPanel pnlAudio;
	private JTabbedPane jTabbedPane;
	private JPanel pnlLinguaggio;
	private JButton btnRimuovi;
	private double[] sa_iniziale;
	private JLabel lblStorico;
	private DefaultTableModel tblLingEvidenzeModel;
	private DefaultTableModel tblAudioEvidenzeModel;
	private JFreeChart istogramma;
	private JFreeChart andamento;

	public Finestra() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			
			hi = new HuginInterface(1);
			sa_ling_history = new ArrayList<double[]>();
			sa_audio_history = new ArrayList<double[]>();
			sa_gesti_history = new ArrayList<double[]>();
			sa_iniziale = hi.getSA();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setResizable(false);
			this.setTitle("Hugin Framework");
			this.setSize (1100, 700);
			
			getContentPane().setLayout(null);
			{
				jTabbedPane = new JTabbedPane();
				getContentPane().add(jTabbedPane);
				jTabbedPane.setBounds(0, 0, 1030, 790);
				
				{
					pnlGenerale = new JPanel();
					jTabbedPane.addTab("General", null, pnlGenerale, null);
					pnlGenerale.setPreferredSize(new java.awt.Dimension(939, 556));
					pnlGenerale.setSize(1015, 592);
					{
						pnlGenSA = new JPanel();
						pnlGenerale.add(pnlGenSA);
						pnlGenSA.setFont(new java.awt.Font("Dialog",0,12));
						pnlGenSA.setPreferredSize(new java.awt.Dimension(499, 543));
						pnlGenSA.setBorder(BorderFactory.createTitledBorder("Social Attitude"));
						pnlGenSA.setLayout(null);
						pnlGenSA.setBounds(519, 12, 487, 548);
						getBtnGrp();
						{
							jpnlLingImp = new JPanel();
							pnlGenSA.add(jpnlLingImp);
							jpnlLingImp.setBounds(10, 30, 472, 50);
							jpnlLingImp.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
							jpnlLingImp.setLayout(null);
							{
								jrbLing = new JCheckBox();
								//btnGrp.add(jrbLing);
								jpnlLingImp.add(jrbLing);
								jrbLing.setText("Language Settings");
								
								jrbLing.setBounds(14, 2, 164, 46);
								jrbLing.setFont(new java.awt.Font("Segoe UI",0,16));
								jrbLing.addChangeListener(new ChangeListener(){

									@Override
									public void stateChanged(ChangeEvent arg0) {
										if(jrbLing.isSelected())
											jbtLing.setEnabled(true);
										else
											jbtLing.setEnabled(false);
										
									}});
								/*jrbLing.addActionListener(new ActionListener(){

									public void actionPerformed(ActionEvent evt) {
										jbtALGActionPerformed(evt,4);
										
									}});*/
							}
							{
								jbtLing = new JButton();
								jpnlLingImp.add(jbtLing);
								jbtLing.setText("Go");
								jbtLing.setBounds(405, 15, 55, 25);
								jbtLing.setEnabled(false);
								jbtLing.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										jbtALGActionPerformed(evt,1);
									}
								});
							}
						}
						{
							jpnlAudioImp = new JPanel();
							pnlGenSA.add(jpnlAudioImp);
							jpnlAudioImp.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
							jpnlAudioImp.setLayout(null);
							jpnlAudioImp.setBounds(10, 121, 472, 50);
							{
								jrbAudio = new JCheckBox();
								//btnGrp.add(jrbAudio);
								jpnlAudioImp.add(jrbAudio);
								jrbAudio.setText("Audio Settings");
								jrbAudio.setBounds(14, 4, 141, 44);
								jrbAudio.setFont(new java.awt.Font("Segoe UI",0,16));
								jrbAudio.addChangeListener(new ChangeListener(){

									@Override
									public void stateChanged(ChangeEvent arg0) {
										if(jrbAudio.isSelected())
											jbtAudio.setEnabled(true);
										else
											jbtAudio.setEnabled(false);
										
									}});
								/*jrbAudio.addActionListener(new ActionListener(){

									public void actionPerformed(ActionEvent evt) {
										jbtALGActionPerformed(evt,5);
										
									}});*/
							}
							{
								jbtAudio = new JButton();
								jpnlAudioImp.add(jbtAudio);
								jbtAudio.setText("Go");
								jbtAudio.setBounds(405, 15, 55, 25);
								jbtAudio.setEnabled(false);
								jbtAudio.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										jbtALGActionPerformed(evt,2);
									}
								});
							}
						}
						{
							jpnlGestiImp = new JPanel();
							pnlGenSA.add(jpnlGestiImp);
							pnlGenSA.add(getJButton1());
							jpnlGestiImp.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
							jpnlGestiImp.setLayout(null);
							jpnlGestiImp.setBounds(10, 210, 472, 50);
							{
								jrbGesti = new JCheckBox();
								//btnGrp.add(jrbGesti);
								jpnlGestiImp.add(jrbGesti);
								jrbGesti.setText("Gesture Settings");
								jrbGesti.setBounds(14, 5, 146, 43);
								jrbGesti.setFont(new java.awt.Font("Segoe UI",0,16));
								jrbGesti.addChangeListener(new ChangeListener(){

									@Override
									public void stateChanged(ChangeEvent arg0) {
										if(jrbGesti.isSelected())
											jbtGesti.setEnabled(true);
										else
											jbtGesti.setEnabled(false);
										
									}});
								/*jrbGesti.addActionListener(new ActionListener(){

									public void actionPerformed(ActionEvent evt) {
										jbtALGActionPerformed(evt,6);
										
									}});*/
							}
							{
								jbtGesti = new JButton();
								jpnlGestiImp.add(jbtGesti);
								jbtGesti.setText("Go");
								jbtGesti.setBounds(405, 15, 55, 25);
								jbtGesti.setEnabled(false);
								jbtGesti.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										jbtALGActionPerformed(evt,3);
									}
								});
							}
						}
					}
					{
						pnlGenResult = new JPanel();
						pnlGenerale.add(pnlGenResult);
						pnlGenResult.setPreferredSize(new java.awt.Dimension(499, 543));
						pnlGenResult.setBorder(BorderFactory.createTitledBorder("Results"));
						pnlGenResult.setLayout(null);
					}
				}
				{
					pnlLinguaggio = new JPanel();
					jTabbedPane.addTab("Language", null, pnlLinguaggio, null);
					pnlLinguaggio.setBounds(0, 0, 545, 548);
					pnlLinguaggio.setSize(1044, 616);
					pnlLinguaggio.setPreferredSize(new java.awt.Dimension(1044, 494));
					pnlLinguaggio.setAutoscrolls(true);
					pnlLinguaggio.setLayout(null);
					//pnlLinguaggio.setTabTitle("Linguaggio");
					{
						pnlLingEvidenze = new JPanel();
						pnlLinguaggio.add(pnlLingEvidenze);
						pnlLingEvidenze.setBounds(12, 0, 505, 545);
						pnlLingEvidenze.setBorder(BorderFactory.createTitledBorder(null, "Evidenze", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",3,12), new java.awt.Color(0,0,0)));
						pnlLingEvidenze.setLayout(null);
						
						{//gestione lista frasi caricate dal file .txt 
							setFrasiList(1);
							pnlContesto = new JPanel();
							pnlLingEvidenze.add(pnlContesto);
							pnlContesto.setBounds(5, 255, 487, 98);
							pnlContesto.setBorder(BorderFactory.createTitledBorder(null, "Context", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",1,12)));
							pnlContesto.setLayout(null);
							{
								lblContx = new JLabel();
								pnlContesto.add(lblContx);
								lblContx.setText("Previous System Move");
								lblContx.setBounds(11, 26, 184, 16);
								lblContx.setFont(new java.awt.Font("Arial",0,12));
							}
							{
								lblMtype = new JLabel();
								pnlContesto.add(lblMtype);
								lblMtype.setText("User Move");
								lblMtype.setBounds(11, 58, 166, 16);
								lblMtype.setFont(new java.awt.Font("Arial",0,12));
							}
							{
								ComboBoxModel cmbContxModel = 
										new DefaultComboBoxModel(
												new String[] { "Undecided", "Self Presentation", "Answer", "Comment", "Question", "Suggestion", "No Answer", "See You Later"  });
								cmbContx = new JComboBox();
								pnlContesto.add(cmbContx);
								cmbContx.setModel(cmbContxModel);
								cmbContx.setBounds(207, 23, 261, 23);
								cmbContx.setToolTipText("<html>Identifica la tipologia dell'ultima mossa del <br>dialogo eseguita dal sistema</html>");
							}
							{
								ComboBoxModel cmbMtypeModel = 
										new DefaultComboBoxModel(
												new String[] { "Undecided", "Self Presentation", "Answer", "Comment", "Question", "See You Later" });
								cmbMtype = new JComboBox();
								pnlContesto.add(cmbMtype);
								cmbMtype.setModel(cmbMtypeModel);
								cmbMtype.setBounds(207, 55, 261, 23);
								cmbMtype.setToolTipText("<html>Identifica la tipologia della mossa del <br>dialogo eseguita dall'utente</html>");
							}
						}
						{
							pnlSegni = new JPanel();
							pnlLingEvidenze.add(pnlSegni);
							pnlSegni.setBounds(5, 355, 487, 136);
							pnlSegni.setBorder(BorderFactory.createTitledBorder(null, "Lingustic Signs", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",1,12)));
							pnlSegni.setLayout(null);
							{
								lblConf = new JLabel();
								pnlSegni.add(lblConf);
								lblConf.setText("Friendly Expr");
								lblConf.setBounds(9, 69, 149, 16);
								lblConf.setFont(new java.awt.Font("Arial",0,12));
							}
							{
								ComboBoxModel cmbConfModel = 
										new DefaultComboBoxModel(
												new String[] { "Undecided","Yes","No" });
								cmbConf = new JComboBox();
								pnlSegni.add(cmbConf);
								cmbConf.setModel(cmbConfModel);
								cmbConf.setBounds(158, 66, 81, 23);
								cmbConf.setToolTipText("<html>Identifica l'uso di espressioni confidenziali, <br>proverbiali, dialettali, ecc.. da parte dell'utente</html>");
							}
							{
								lblCiao = new JLabel();
								pnlSegni.add(lblCiao);
								lblCiao.setText("Greeting Expr");
								lblCiao.setBounds(263, 69, 123, 16);
								lblCiao.setFont(new java.awt.Font("Arial",0,12));
							}
							{
								ComboBoxModel cmbCiaoModel = 
										new DefaultComboBoxModel(
												new String[] { "Undecided","Yes","No" });
								cmbCiao = new JComboBox();
								pnlSegni.add(cmbCiao);
								cmbCiao.setModel(cmbCiaoModel);
								cmbCiao.setBounds(387, 66, 81, 23);
								cmbCiao.setToolTipText("<html>Identifica il fatto che l'utente abbia <br>utilizzato espressioni di saluto (es. Ciao, Arrivederci)</html>");
							}
							{
								lblMe = new JLabel();
								pnlSegni.add(lblMe);
								lblMe.setText("1st Person Form");
								lblMe.setBounds(9, 105, 149, 16);
								lblMe.setFont(new java.awt.Font("Arial",0,12));
							}
							{
								lblYou = new JLabel();
								pnlSegni.add(lblYou);
								lblYou.setText("2nd Person Form");
								lblYou.setBounds(263, 105, 142, 16);
								lblYou.setFont(new java.awt.Font("Arial",0,12));
							}
							{
								ComboBoxModel cmbMeModel = 
										new DefaultComboBoxModel(
												new String[] { "Undecided","Yes","No"});
								cmbMe = new JComboBox();
								pnlSegni.add(cmbMe);
								cmbMe.setModel(cmbMeModel);
								cmbMe.setBounds(158, 102, 81, 23);
								cmbMe.setToolTipText("<html>Identifica l'uso di espressioni e <br>forme verbali in prima persona</html>");
							}
							{
								ComboBoxModel cmbYouModel = 
										new DefaultComboBoxModel(
												new String[] { "Undecided","Yes","No" });
								cmbYou = new JComboBox();
								pnlSegni.add(cmbYou);
								cmbYou.setModel(cmbYouModel);
								cmbYou.setBounds(387, 102, 81, 23);
								cmbYou.setToolTipText("<html>Identifica l'uso di espressioni e <br>forme verbali in seconda persona</html>");
							}
							{
								lblLeng = new JLabel();
								pnlSegni.add(lblLeng);
								lblLeng.setText("Message Length");
								lblLeng.setBounds(9, 33, 149, 16);
								lblLeng.setFont(new java.awt.Font("Arial",0,12));
							}
							{
								txtLeng = new JTextField();
								pnlSegni.add(txtLeng);
								txtLeng.setBounds(158, 30, 81, 23);
								txtLeng.setToolTipText("<html>Lunghezza del messaggio <br>dell'utente (espresso in caratteri)</html>");
							}
							{
								lblQmar = new JLabel();
								pnlSegni.add(lblQmar);
								lblQmar.setText("Question Points");
								lblQmar.setBounds(263, 33, 124, 16);
								lblQmar.setFont(new java.awt.Font("Arial",0,12));
							}
							{
								ComboBoxModel cmqQmarModel = 
										new DefaultComboBoxModel(
												new String[] { "Undecided","Yes","No" });
								cmbQmar = new JComboBox();
								pnlSegni.add(cmbQmar);
								cmbQmar.setModel(cmqQmarModel);
								cmbQmar.setBounds(387, 30, 81, 23);
								cmbQmar.setToolTipText("<html>Identifica il fatto che l'utente abbia utilizzato o <br>meno il punto interrogativo nell'ultimo messaggio</html>");
							}
						}

						{
							btnReset = new JButton();
							pnlLingEvidenze.add(btnReset);
							btnReset.setText("Reset Dialog");
							btnReset.setBounds(7, 500, 130, 28);
							btnReset.setEnabled(false);
							btnReset.setToolTipText("Resetta il dialogo, eliminando tutte le mosse definite.");
							btnReset.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									
									int risp = JOptionPane.showConfirmDialog(null, "Si è sicuri di voler resettare lo storico del dialogo?", "Reset", JOptionPane.YES_NO_OPTION);
									if(risp == JOptionPane.YES_OPTION)
									{
										// Attiva/disattiva i pulsanti appropriati
										btnReset.setEnabled(false);
										btnRimuovi.setEnabled(false);
										
										// Resetta lo storico della Social Attitude
										sa_ling_history = new ArrayList<double[]>();
										
										// Svuota la tabella dello storico
										for (int i=tblLingEvidenzeModel.getRowCount()-1;i>=0;i--)
											tblLingEvidenzeModel.removeRow(i);
										
										// Resetta le opzioni di default delle evidenze
										cmbContx.setSelectedIndex(0);
										cmbMtype.setSelectedIndex(0);
										txtLeng.setText("");
										cmbQmar.setSelectedIndex(0);
										cmbConf.setSelectedIndex(0);
										cmbCiao.setSelectedIndex(0);
										cmbMe.setSelectedIndex(0);
										cmbYou.setSelectedIndex(0);
										
										// Resetta la rete
										hi.reset();
										
										// Ridisegna i grafici
										disegnaGrafici();
									}
								}
							});
						}
						{
							jScrollPane1 = new JScrollPane();
							pnlLingEvidenze.add(jScrollPane1);
							jScrollPane1.setBounds(7, 170, 485, 77);
							{
								tblLingEvidenzeModel = 
										new DefaultTableModel(
												new String[][] {},
												new String[] { "M.Sys","M.Utente","Lung", "P.Interr.", "Conf.","Saluto","Forme 1a", "Forme 2a" });
								tblEvidenze = new JTable();
								jScrollPane1.setViewportView(tblEvidenze);
								tblEvidenze.setModel(tblLingEvidenzeModel);
								tblEvidenze.setBounds(11, 28, 481, 199);
								tblEvidenze.setEnabled(false);
							}
						}
						{
							lblStorico = new JLabel();
							pnlLingEvidenze.add(lblStorico);
							lblStorico.setText("Evidence History");
							lblStorico.setBounds(7, 145, 221, 18);
						}
						{
							btnAggiungi = new JButton();
							pnlLingEvidenze.add(btnAggiungi);
							btnAggiungi.setText("Add Move");
							btnAggiungi.setBounds(362, 500, 130, 28);
							btnAggiungi.setToolTipText("Imposta le evidenze definite in una nuova mossa del dialogo.");
							btnAggiungi.setEnabled(false);
							btnAggiungi.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									
									try
									{
										if (!txtLeng.getText().equals(""))
										{
											if(Integer.parseInt(txtLeng.getText())<=0)
												throw new NumberFormatException();
										}
										
										// Attiva/disattiva i pulsanti appropriati
										btnReset.setEnabled(true);
										btnRimuovi.setEnabled(true);
										
										if(sa_ling_history.size()>0)
											hi.addNodo();
										
										// Setta le evidenze
										hi.setEvidenza(Nodes.MOSSA_PREC_SISTEMA, cmbContx.getSelectedItem().toString());
										hi.setEvidenza(Nodes.MOSSA_UTENTE, cmbMtype.getSelectedItem().toString());
										hi.setEvidenza(Nodes.LUNGHEZZA,txtLeng.getText());
										hi.setEvidenza(Nodes.P_INTERROGATIVO, cmbQmar.getSelectedItem().toString());
										hi.setEvidenza(Nodes.ESPR_CONFIDENZIALI, cmbConf.getSelectedItem().toString());
										hi.setEvidenza(Nodes.ESPR_SALUTO, cmbCiao.getSelectedItem().toString());
										hi.setEvidenza(Nodes.ESPR_1PERSONA, cmbMe.getSelectedItem().toString());
										hi.setEvidenza(Nodes.ESPR_2PERSONA, cmbYou.getSelectedItem().toString());
										
										// Ottiene il valore di Social Attitude
										double[] sa = hi.getSA();
										sa_ling_history.add(sa);
										
										// Scrive la riga sulla tabella
										tblLingEvidenzeModel.addRow(new String[]{getValoreTabella(cmbContx.getSelectedItem().toString()),getValoreTabella(cmbMtype.getSelectedItem().toString()),getValoreTabella(txtLeng.getText()),getValoreTabella(cmbQmar.getSelectedItem().toString()),getValoreTabella(cmbConf.getSelectedItem().toString()),getValoreTabella(cmbCiao.getSelectedItem().toString()),getValoreTabella(cmbMe.getSelectedItem().toString()),getValoreTabella(cmbYou.getSelectedItem().toString()) });
										
										// Disegna il grafico
										disegnaGrafici();
									}
									catch(NumberFormatException e)
									{
										JOptionPane.showMessageDialog(null, "La lunghezza del messaggio deve essere un numero intero maggiore di 0");
									}
								}
							});
						}
						{
							btnRimuovi = new JButton();
							pnlLingEvidenze.add(btnRimuovi);
							btnRimuovi.setText("Remove Move");
							btnRimuovi.setBounds(214, 500, 130, 28);
							btnRimuovi.setToolTipText("Rimuove l'ultima mossa del dialogo.");
							btnRimuovi.setEnabled(false);
							btnRimuovi.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									
									//Rimuove l'ultima mossa dallo storico
									tblLingEvidenzeModel.removeRow(tblLingEvidenzeModel.getRowCount()-1);
									
									//Rimuove gli ultimi valori di SA memorizzati
									sa_ling_history.remove(sa_ling_history.size()-1);
									
									// Attiva/disattiva i pulsanti appropriati
									if(sa_ling_history.size()==0)
									{
										btnRimuovi.setEnabled(false);
										btnReset.setEnabled(false);
									}
									
									//Aggiorna i grafici
									disegnaGrafici();
								}
							});
						}
						
						
						
					}
					{
						pnlLingSA = new JPanel();
						pnlLinguaggio.add(pnlLingSA);
						pnlLingSA.setBounds(528, 0, 505, 545);
						pnlLingSA.setBorder(BorderFactory.createTitledBorder("Social Attitude"));
						pnlLingSA.setFont(new java.awt.Font("Dialog",0,12));
						pnlLingSA.setLayout(null);
						//pnlLingSA.setTabTitle("");
						disegnaGrafici();
					}
				}
////////////////////////////////////////////AUDIO////////////////////////////////////////
{
pnlAudioEvidenze = new JPanel();
pnlAudioEvidenze.setBorder(BorderFactory.createTitledBorder(null,"Evidences",TitledBorder.LEADING,TitledBorder.DEFAULT_POSITION,new java.awt.Font("Segoe UI",3,12),new java.awt.Color(0,0,0)));
pnlAudioEvidenze.setBounds(12, 0, 505, 545);
pnlAudioEvidenze.setLayout(null);
pnlAudioAV = new JPanel();
pnlAudioAV.setBorder(BorderFactory.createTitledBorder(null,"A&V",TitledBorder.LEADING,TitledBorder.DEFAULT_POSITION,new java.awt.Font("Segoe UI",1,12)));
pnlAudioAV.setBounds(5, 270, 487, 98);
pnlAudioAV.setLayout(null);
lblVal = new JLabel();
lblVal.setText("Valence Value:");
lblVal.setFont(new java.awt.Font("Arial",0,12));
lblVal.setBounds(17, 27, 84, 15);
pnlAudioAV.add(lblVal);
lblAr = new JLabel();
lblAr.setText("Arousal Value:");
lblAr.setFont(new java.awt.Font("Arial",0,12));
lblAr.setBounds(17, 66, 99, 15);
pnlAudioAV.add(lblAr);
ComboBoxModel cmbValModel = 
new DefaultComboBoxModel(
new String[] { "Undecided", "Positive", "Neutral", "Negative", "Very Negative" });
cmbVal = new JComboBox();
cmbVal.setModel(cmbValModel);
cmbVal.setToolTipText("<html>Identifica la tipologia dell'ultima mossa del <br>dialogo eseguita dal sistema</html>");
cmbVal.setBounds(123, 23, 103, 23);
cmbVal.addItemListener(new ItemListener(){

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
		int index = cmbVal.getSelectedIndex();
		if(index!=0){
			btnAudioAggiungi.setEnabled(true);
			}
		else
		{
		 btnAudioAggiungi.setEnabled(false);
		}
		
	}});
pnlAudioAV.add(cmbVal);
ComboBoxModel cmbArModel = 
new DefaultComboBoxModel(
new String[] { "Undecided","High", "Medium", "Low" });
cmbAr = new JComboBox();
cmbAr.setModel(cmbArModel);
cmbAr.setToolTipText("<html>Identifica la tipologia della mossa del <br>dialogo eseguita dall'utente</html>");
cmbAr.setBounds(123, 62, 103, 23);
cmbAr.addItemListener(new ItemListener(){

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
		int index = cmbAr.getSelectedIndex();
		if(index!=0){
			btnAudioAggiungi.setEnabled(true);
			}
		else
		{
		 btnAudioAggiungi.setEnabled(false);
		}
		
	}});
pnlAudioAV.add(cmbAr);

pnlAudioEvidenze.add(pnlAudioAV);
btnAudioReset = new JButton();
btnAudioReset.setEnabled(false);
btnAudioReset.setText("Reset Dialog");
btnAudioReset.setToolTipText("Resetta il dialogo, eliminando tutte le mosse definite.");
btnAudioReset.setBounds(7, 500, 130, 28);
btnAudioReset.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent evt) {

int risp = JOptionPane.showConfirmDialog(null, "Si è sicuri di voler resettare lo storico del dialogo?", "Reset", JOptionPane.YES_NO_OPTION);
if(risp == JOptionPane.YES_OPTION)
{
// Attiva/disattiva i pulsanti appropriati
btnReset.setEnabled(false);
btnRimuovi.setEnabled(false);

// Resetta lo storico della Social Attitude
sa_ling_history = new ArrayList<double[]>();

// Svuota la tabella dello storico
for (int i=tblLingEvidenzeModel.getRowCount()-1;i>=0;i--)
tblLingEvidenzeModel.removeRow(i);

// Resetta le opzioni di default delle evidenze
cmbContx.setSelectedIndex(0);
cmbMtype.setSelectedIndex(0);
txtLeng.setText("");
cmbQmar.setSelectedIndex(0);
cmbConf.setSelectedIndex(0);
cmbCiao.setSelectedIndex(0);
cmbMe.setSelectedIndex(0);
cmbYou.setSelectedIndex(0);

// Resetta la rete
hi.reset();

// Ridisegna i grafici
disegnaGrafici();
}
}
});

pnlAudioEvidenze.add(btnAudioReset);
jScrollPane3 = new JScrollPane();
jScrollPane3.setBounds(7, 187, 485, 77);
tblAudioEvidenzeModel = 
new DefaultTableModel(
new String[][] {},
new String[] { "#Phrase", "Arousal", "Valence" });
tblAudioEvidenze = new JTable();
tblAudioEvidenze.setModel(tblAudioEvidenzeModel);
tblAudioEvidenze.setEnabled(false);
tblAudioEvidenze.setBounds(11, 28, 481, 199);
jScrollPane3.setViewportView(tblAudioEvidenze);
pnlAudioEvidenze.add(jScrollPane3);
jLabel10 = new JLabel();
jLabel10.setText("Evidences history");
jLabel10.setBounds(5, 163, 221, 18);
pnlAudioEvidenze.add(jLabel10);
btnAudioAggiungi = new JButton();
btnAudioAggiungi.setEnabled(false);
btnAudioAggiungi.setText("Add Move");
btnAudioAggiungi.setToolTipText("Imposta le evidenze definite in una nuova mossa del dialogo.");
btnAudioAggiungi.setBounds(362, 500, 130, 28);
btnAudioAggiungi.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent evt) {

try
{

// Attiva/disattiva i pulsanti appropriati
btnReset.setEnabled(true);
btnRimuovi.setEnabled(true);

if(sa_audio_history.size()>0)
hi.addNodo();//modificare con rete audio

// Setta le evidenze
//modificare con rete audio
hi.setEvidenza(Nodes.MOSSA_PREC_SISTEMA, cmbContx.getSelectedItem().toString());
hi.setEvidenza(Nodes.MOSSA_UTENTE, cmbMtype.getSelectedItem().toString());
hi.setEvidenza(Nodes.LUNGHEZZA,txtLeng.getText());
hi.setEvidenza(Nodes.P_INTERROGATIVO, cmbQmar.getSelectedItem().toString());
hi.setEvidenza(Nodes.ESPR_CONFIDENZIALI, cmbConf.getSelectedItem().toString());
hi.setEvidenza(Nodes.ESPR_SALUTO, cmbCiao.getSelectedItem().toString());
hi.setEvidenza(Nodes.ESPR_1PERSONA, cmbMe.getSelectedItem().toString());
hi.setEvidenza(Nodes.ESPR_2PERSONA, cmbYou.getSelectedItem().toString());

// Ottiene il valore di Social Attitude
double[] sa = hi.getSA();
sa_ling_history.add(sa);

// Scrive la riga sulla tabella
//prendere i valori da Voice Classifier
tblAudioEvidenzeModel.addRow(new String[]{"",cmbAr.getSelectedItem().toString(),cmbVal.getSelectedItem().toString()});

// Disegna il grafico
disegnaGrafici();
}
catch(NumberFormatException e)
{
JOptionPane.showMessageDialog(null, "La lunghezza del messaggio deve essere un numero intero maggiore di 0");
}
}
});
pnlAudioEvidenze.add(btnAudioAggiungi);
btnAudioRimuovi = new JButton();
btnAudioRimuovi.setEnabled(false);
btnAudioRimuovi.setText("Remove Move");
btnAudioRimuovi.setToolTipText("Rimuove l'ultima mossa del dialogo.");
btnAudioRimuovi.setBounds(214, 500, 130, 28);
btnAudioRimuovi.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent evt) {

//Rimuove l'ultima mossa dallo storico
tblLingEvidenzeModel.removeRow(tblLingEvidenzeModel.getRowCount()-1);

//Rimuove gli ultimi valori di SA memorizzati
sa_ling_history.remove(sa_ling_history.size()-1);

// Attiva/disattiva i pulsanti appropriati
if(sa_ling_history.size()==0)
{
btnRimuovi.setEnabled(false);
btnReset.setEnabled(false);
}

//Aggiorna i grafici
disegnaGrafici();
}
});

pnlAudioEvidenze.add(btnAudioRimuovi);
pnlAudioEvidenze.add(getJScrollPane2());
pnlAudioEvidenze.add(getLblAudioFrasi());

pnlAudio = new JPanel();
					jTabbedPane.addTab("Audio", null, pnlAudio, null);
					pnlAudio.setLayout(null);
					pnlAudio.add(pnlAudioEvidenze);
					pnlAudio.add(getJPanel1());
}
					
				
////////////////////////////////////////GESTI///////////////////////////////////////////////////
				{
					pnlGesti = new JPanel();
					//FlowLayout pnlGestiLayout = new FlowLayout();
					jTabbedPane.addTab("Gesture", null, pnlGesti, null);
					pnlGesti.setLayout(null);
					//pnlGesti.setPreferredSize(new java.awt.Dimension(1033, 744));
					{
						pnlGestiEvidenze = new JPanel();
						pnlGesti.add(pnlGestiEvidenze);
						BoxLayout jPanel1Layout = new BoxLayout(pnlGestiEvidenze, javax.swing.BoxLayout.Y_AXIS);
						pnlGestiEvidenze.setBorder(BorderFactory.createTitledBorder(null,"Evidences",TitledBorder.LEADING,TitledBorder.DEFAULT_POSITION,new java.awt.Font("Segoe UI",3,12),new java.awt.Color(0,0,0)));
						pnlGestiEvidenze.setLayout(null);
						pnlGestiEvidenze.setBounds(12, 0, 505, 545);
						//gestione lista frasi caricate dal file .txt 
							//setFrasiList();
						{
							pnlSegniLing = new JPanel();
							pnlGestiEvidenze.add(pnlSegniLing);
							pnlSegniLing.setBorder(BorderFactory.createTitledBorder(null,"Gesture",TitledBorder.LEADING,TitledBorder.DEFAULT_POSITION,new java.awt.Font("Segoe UI",1,12)));
							pnlSegniLing.setLayout(null);
							pnlSegniLing.setBounds(7, 172, 482, 136);
							{
								lblArms = new JLabel();
								pnlSegniLing.add(lblArms);
								lblArms.setText("Arms");
								lblArms.setFont(new java.awt.Font("Arial",0,12));
								lblArms.setBounds(9, 69, 149, 16);
							}
							{
								//modificare in defaultmodel
								ComboBoxModel cmbArmsModel = 
										new DefaultComboBoxModel(
												new String[] { "Braccia1", "Braccia2", "Braccia3" });
								cmbArms = new JComboBox();
								pnlSegniLing.add(cmbArms);
								cmbArms.setModel(cmbArmsModel);
								cmbArms.setToolTipText("<html>Identifica la posizione delle braccia dell'utente <br>es: incrociate, aperte, ecc..</html>");
								cmbArms.setBounds(158, 66, 81, 23);
							}
							{
								lblLegs = new JLabel();
								pnlSegniLing.add(lblLegs);
								lblLegs.setText("Legs");
								lblLegs.setFont(new java.awt.Font("Arial",0,12));
								lblLegs.setBounds(9, 105, 149, 16);
							}
							{
								lblHands = new JLabel();
								pnlSegniLing.add(lblHands);
								lblHands.setText("Hands");
								lblHands.setFont(new java.awt.Font("Arial",0,12));
								lblHands.setBounds(9, 36, 143, 16);
							}
							{
								ComboBoxModel cmbLegsModel = 
										new DefaultComboBoxModel(
												new String[] { "Gambe1", "Gambe2", "Gambe3" });
								cmbLegs = new JComboBox();
								pnlSegniLing.add(cmbLegs);
								cmbLegs.setModel(cmbLegsModel);
								cmbLegs.setToolTipText("<html>Identifica la posizione delle gambe dell'utente <br>es: divaricate, incrociate, ecc.. </html>");
								cmbLegs.setBounds(158, 102, 81, 23);
							}
							{
								ComboBoxModel cmbHandsModel = 
										new DefaultComboBoxModel(
												new String[] { "Mani1", "Mani2", "Mani3" });
								cmbHands = new JComboBox();
								pnlSegniLing.add(cmbHands);
								cmbHands.setModel(cmbHandsModel);
								cmbHands.setToolTipText("<html>Identifica la posizione delle mani dell'utente <br>es: sul volto, sul naso, sulla bocca ecc..</html>");
								cmbHands.setBounds(158, 33, 81, 23);
							}
						}
						
					
						{
							jButton3 = new JButton();
							pnlGestiEvidenze.add(jButton3);
							pnlGestiEvidenze.add(getJScrollPane4());
							pnlGestiEvidenze.add(getLblGestiFrasi());
							jButton3.setEnabled(false);
							jButton3.setText("Rimuovi Mossa");
							jButton3.setToolTipText("Rimuove l'ultima mossa del dialogo.");
							jButton3.setBounds(223, 496, 133, 28);
							jButton3.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									
									//Rimuove l'ultima mossa dallo storico
									tblLingEvidenzeModel.removeRow(tblLingEvidenzeModel.getRowCount()-1);
									
									//Rimuove gli ultimi valori di SA memorizzati
									sa_ling_history.remove(sa_ling_history.size()-1);
									
									// Attiva/disattiva i pulsanti appropriati
									if(sa_ling_history.size()==0)
									{
										btnRimuovi.setEnabled(false);
										btnReset.setEnabled(false);
									}
									
									//Aggiorna i grafici
									disegnaGrafici();
								}
							});
						}
					}
					{
						pnlGestiSA = new JPanel();
						pnlGesti.add(pnlGestiSA);
						pnlGestiSA.setFont(new java.awt.Font("Dialog",0,12));
						pnlGestiSA.setPreferredSize(new java.awt.Dimension(493,568));
						pnlGestiSA.setBorder(BorderFactory.createTitledBorder("Social Attitude"));
					    pnlGestiSA.setLayout(null);
						pnlGestiSA.setBounds(528, 0, 505, 545);
						{
							chartPanel1 = new ChartPanel(istogramma, true);
							pnlGestiSA.add(chartPanel1);
							chartPanel1.setBounds(17, 253, 471, 261);
						}
						{
							chartPanel2 = new ChartPanel(andamento, true);
							pnlGestiSA.add(chartPanel2);
							chartPanel2.setBounds(17, 25, 471, 204);
						}
					}
				}
			}
			pack();
			this.setSize(1044, 616);
			setLocationRelativeTo(null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getValoreTabella(String valore)
	{
		if(valore.equals(Nodes.VALORE_NONSO) || valore.equals(""))
			return "?";
		else
			return valore;
	}

	// Metodo per il disegno dei grafici
	private void disegnaGrafici()
	{
		if(pnlAnd!=null)
			pnlLingSA.remove(pnlAnd);
		if(pnlIst!=null)
			pnlLingSA.remove(pnlIst);
		
		
		// Crea e disegna l'istogramma
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		if(sa_ling_history.size()>0)
		{
			double last_sa[] = sa_ling_history.get(sa_ling_history.size()-1);
			ds.addValue(last_sa[2],"Negative","Social Attitude");
			ds.addValue(last_sa[1],"Neutral","Social Attitude");
			ds.addValue(last_sa[0],"Positive","Social Attitude");
		}
		else
		{
			ds.addValue(sa_iniziale[2],"Negative","Social Attitude");
			ds.addValue(sa_iniziale[1],"Neutral","Social Attitude");
			ds.addValue(sa_iniziale[0],"Positive","Social Attitude");
		}
		istogramma = ChartFactory.createBarChart("Current Social Attitude", "Move", "Probability", ds, PlotOrientation.VERTICAL, true, true, false);
		pnlIst = new ChartPanel(istogramma, true);
		
		CategoryPlot plotIst = (CategoryPlot)istogramma.getPlot();
		plotIst.getRangeAxis().setUpperBound(1);
		plotIst.setBackgroundPaint(new Color(230,230,230));
		plotIst.setRangeGridlinePaint(Color.black);
		((BarRenderer)(plotIst.getRenderer())).setSeriesPaint(0, new Color(150,0,0));
		((BarRenderer)(plotIst.getRenderer())).setSeriesPaint(1, new Color(80,150,220));
		((BarRenderer)(plotIst.getRenderer())).setSeriesPaint(2, new Color(0,150,0));
	
		CategoryItemRenderer renderLabel = plotIst.getRenderer();
		CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator("{2}", new DecimalFormat("0.00"));
		renderLabel.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER,TextAnchor.CENTER));
		renderLabel.setBaseItemLabelGenerator(generator);
		renderLabel.setBaseItemLabelFont(new Font("TimesRoman",Font.BOLD,13));
		renderLabel.setBaseItemLabelsVisible(true);

		pnlLingSA.add(pnlIst);
		pnlIst.setBounds(17, 253, 471, 261);

		
		DefaultCategoryDataset dsPositive = new DefaultCategoryDataset();
		DefaultCategoryDataset dsNeutral = new DefaultCategoryDataset();
		DefaultCategoryDataset dsBad = new DefaultCategoryDataset();

		dsPositive.addValue(sa_iniziale[0], "Positive", "0");
		dsNeutral.addValue(sa_iniziale[1], "Neutral", "0");
		dsBad.addValue(sa_iniziale[2], "Negative", "0");

		for (Integer i=1; i <= sa_ling_history.size(); i++)
		{
			dsPositive.addValue(sa_ling_history.get(i-1)[0],"Positive",i.toString());
			dsNeutral.addValue(sa_ling_history.get(i-1)[1],"Neutral",i.toString());
			dsBad.addValue(sa_ling_history.get(i-1)[2],"Negative",i.toString());
		}
		andamento = ChartFactory.createLineChart("Social Attitude Trend", "Move", "Probability", dsBad, PlotOrientation.VERTICAL, true, true, false);
		
		CategoryPlot plotAnd = (CategoryPlot)andamento.getPlot();
		plotAnd.setDataset(1,dsNeutral);
		plotAnd.setDataset(2,dsPositive);
		plotAnd.setRenderer(1,new LineAndShapeRenderer());
		plotAnd.setRenderer(2,new LineAndShapeRenderer());
		plotAnd.getRangeAxis().setUpperBound(1);
		plotAnd.setBackgroundPaint(new Color(230,230,230));
		plotAnd.setRangeGridlinePaint(Color.black);

		pnlAnd = new ChartPanel(andamento, true);
		((LineAndShapeRenderer)(plotAnd.getRenderer(1))).setSeriesPaint(0,new Color(80,150,220));
		((LineAndShapeRenderer)(plotAnd.getRenderer(2))).setSeriesPaint(0, new Color(0,150,0));
		
		
		pnlLingSA.add(pnlAnd);
		pnlAnd.setBounds(17, 25, 471, 204);
	}
	
	private void jbtALGActionPerformed(ActionEvent evt,int i) {
		switch(i){
		case 1:
		//linguaggio
		JFileChooser fileChooser = new JFileChooser();
		String s=null;
		DefaultListModel jListFrasiModel = new DefaultListModel();
		File f=null;
		FileFilter filter1 = new ExtensionFileFilter("Testuale", new String[] { "txt"});
		fileChooser.setFileFilter(filter1);
		int result = fileChooser.showOpenDialog(Finestra.this);
		int l=1;
		switch (result) {
		    case JFileChooser.APPROVE_OPTION:
		    	 f = fileChooser.getSelectedFile();
				 try {
						BufferedReader inStream = new BufferedReader(new FileReader(f));
						
						while((s = inStream.readLine())!= null){
							
							jListFrasiModel.addElement(s);
							l++;
							System.out.println(s);
							
							}
						jListFrasi.setModel(jListFrasiModel);
						jListAudioFrasi.setModel(jListFrasiModel);
						jListFrasi.setSelectedIndex(0);
						jListGestiFrasi.setModel(jListFrasiModel);
						jListAudioFrasi.setSelectedIndex(0);
						
				  } catch (FileNotFoundException e) {
						
						e.printStackTrace();
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				  System.out.println(i);
					jTabbedPane.setSelectedIndex(i);
					btnAggiungi.setEnabled(true);
		      System.out.println("Approve (Open or Save) was clicked");
		      break;
		    case JFileChooser.CANCEL_OPTION:
		      System.out.println("Cancel or the close-dialog icon was clicked");
		      break;
		    case JFileChooser.ERROR_OPTION:
		      System.out.println("Error");
		      break;
		    }
		case 2: System.out.println("Bottone Audio");jTabbedPane.setSelectedIndex(i);break;
				
		case 3: System.out.println("Bottone Gesti");jTabbedPane.setSelectedIndex(i);break;
		
		case 4: {System.out.println("Checkbox Linguaggio");
				jbtLing.setEnabled(true);
				//jbtAudio.setEnabled(false);
				//jbtGesti.setEnabled(false);
				break;}
				
		case 5: {System.out.println("Checkbox Audio");
				//jbtLing.setEnabled(false);
				jbtAudio.setEnabled(true);
				//jbtGesti.setEnabled(false);
				break;}
				
		case 6: {System.out.println("Checkbox Gesti");
				//jbtLing.setEnabled(false);
				//jbtAudio.setEnabled(false);
				jbtGesti.setEnabled(true);
				break;}
		
				
		}
		  
		  
		
		//TODO add your code for jbtAudio.actionPerformed
	}
	
	private void setFrasiList(int choose){
		lblLingFrasi = new JLabel();
		pnlLingEvidenze.add(lblLingFrasi);
		lblLingFrasi.setText("User phrases");
		lblLingFrasi.setBounds(7, 23, 90, 17);
		jscrollpFrasi = new JScrollPane();
		pnlLingEvidenze.add(jscrollpFrasi);
		jscrollpFrasi.setBounds(7, 45, 485, 98);
		{
			jListFrasi = new JList();
			jListFrasi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jscrollpFrasi.setViewportView(jListFrasi);
			
			
			//gestire la visibilità del pulsante aggiungi mossa
			jListFrasi.addListSelectionListener(new ListSelectionListener(){
		
				public void valueChanged(ListSelectionEvent arg0) {
					Integer length=0;
					int i=0,space=0;
					
					String st=(String) jListFrasi.getSelectedValue();
					length=st.length();
					while(i<length){
						if(st.charAt(i)== '?')
							//cmbQmar.setSelectedIndex(1);
							cmbQmar.setSelectedItem("SI");
						else 
							cmbQmar.setSelectedItem("NO");
						//conta il numero di spazi da sottrarre al numero totale dei caratteri
						if(st.charAt(i)==' ')
							space++;
						
						i++;
					}
					length=length-space;
					txtLeng.setText(length.toString());

				}
			});
			
		}
	}
	
	private ButtonGroup getBtnGrp() {
		if(btnGrp == null) {
			btnGrp = new ButtonGroup();
		}
		return btnGrp;
	}
	
	private JScrollPane getJScrollPane2() {
		if(jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setBounds(7, 45, 485, 110);
			jScrollPane2.setViewportView(getJListAudioFrasi());
		}
		return jScrollPane2;
	}
	
	private JList getJListAudioFrasi() {
		if(jListAudioFrasi == null) {
			jListAudioFrasi = new JList();

		}
		return jListAudioFrasi;
	}

	private JPanel getJPanel1() {
		if(jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setFont(new java.awt.Font("Dialog",0,12));
			jPanel1.setPreferredSize(new java.awt.Dimension(493,568));
			jPanel1.setBorder(BorderFactory.createTitledBorder("Social Attitude"));
			
			jPanel1.setBounds(528, 0, 505, 545);
			jPanel1.setLayout(null);
			jPanel1.add(getChartPanel4());
			jPanel1.add(getChartPanel3());
		}
		return jPanel1;
	}
	
	private ChartPanel getChartPanel4() {
		if(chartPanel4 == null) {
			chartPanel4 = new ChartPanel(andamento, true);
			chartPanel4.setBounds(17, 25, 471, 204);
		}
		return chartPanel4;
	}
	
	private JScrollPane getJScrollPane4() {
		if(jScrollPane4 == null) {
			jScrollPane4 = new JScrollPane();
			jScrollPane4.setBounds(7, 45, 485, 110);
			jScrollPane4.setViewportView(getJList1());
		}
		return jScrollPane4;
	}
	
	private JList getJList1() {
		if(jListGestiFrasi == null) {
			jListGestiFrasi = new JList();
			
		}
		return jListGestiFrasi;
	}
	
	private JLabel getLblAudioFrasi() {
		if(lblAudioFrasi == null) {
			lblAudioFrasi = new JLabel();
			lblAudioFrasi.setText("User phrases");
			lblAudioFrasi.setBounds(7, 23, 77, 16);
		}
		return lblAudioFrasi;
	}
	
	private JLabel getLblGestiFrasi() {
		if(lblGestiFrasi == null) {
			lblGestiFrasi = new JLabel();
			lblGestiFrasi.setText("User phrases");
			lblGestiFrasi.setBounds(7, 23, 94, 16);
		}
		return lblGestiFrasi;
	}

	private JButton getJButton1() {
		if(jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Get Social Attitude");
			jButton1.setBounds(312, 492, 164, 31);
			jButton1.setEnabled(false);
		}
		return jButton1;
	}

	private ChartPanel getChartPanel3() {
		if(chartPanel3 == null) {
			chartPanel3 = new ChartPanel(istogramma, true);
			chartPanel3.setBounds(17, 253, 471, 261);
		}
		return chartPanel3;
	}

}
