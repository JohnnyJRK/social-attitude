package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
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
	private JRadioButton jrbGesti;
	private JRadioButton jrbLing;
	private JRadioButton jrbAudio;
	private JPanel jpnlLingImp;
	private ButtonGroup btnGrp;
	private JLabel lblFrasi;
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
	private JButton jButton2;
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
	private DefaultTableModel tblEvidenzeModel;
	private JFreeChart istogramma;
	private JFreeChart andamento;

	public Finestra() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			
			hi = new HuginInterface();
			sa_ling_history = new ArrayList<double[]>();
			sa_iniziale = hi.getSA();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setResizable(true);
			this.setTitle("Hugin Framework");
			this.setSize (1100, 700);
			getContentPane().setLayout(null);
			{
				jTabbedPane = new JTabbedPane();
				getContentPane().add(jTabbedPane);
				jTabbedPane.setBounds(0, 0, 1030, 790);
				{
					pnlGenerale = new JPanel();
					jTabbedPane.addTab("Generale", null, pnlGenerale, null);
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
							jpnlLingImp.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
							jpnlLingImp.setLayout(null);
							{
								jrbLing = new JRadioButton();
								btnGrp.add(jrbLing);
								jpnlLingImp.add(jrbLing);
								jrbLing.setText("Impostazioni Linguaggio");
								
								jrbLing.setBounds(14, 12, 218, 25);
								jrbLing.setFont(new java.awt.Font("Segoe UI",0,16));
								jrbLing.addActionListener(new ActionListener(){

									public void actionPerformed(ActionEvent evt) {
										jbtALGActionPerformed(evt,4);
										
									}});
							}
							{
								jbtLing = new JButton();
								jpnlLingImp.add(jbtLing);
								jbtLing.setText("Vai");
								jbtLing.setBounds(405, 15, 55, 25);
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
							jpnlAudioImp.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
							jpnlAudioImp.setLayout(null);
							jpnlAudioImp.setBounds(10, 95, 472, 50);
							{
								jrbAudio = new JRadioButton();
								btnGrp.add(jrbAudio);
								jpnlAudioImp.add(jrbAudio);
								jrbAudio.setText("Impostazioni Audio");
								jrbAudio.setBounds(14, 13, 191, 23);
								jrbAudio.setFont(new java.awt.Font("Segoe UI",0,16));
								jrbAudio.addActionListener(new ActionListener(){

									public void actionPerformed(ActionEvent evt) {
										jbtALGActionPerformed(evt,5);
										
									}});
							}
							{
								jbtAudio = new JButton();
								jpnlAudioImp.add(jbtAudio);
								jbtAudio.setText("Vai");
								jbtAudio.setBounds(405, 14, 55, 25);
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
							jpnlGestiImp.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
							jpnlGestiImp.setLayout(null);
							jpnlGestiImp.setBounds(10, 165, 472, 50);
							{
								jrbGesti = new JRadioButton();
								btnGrp.add(jrbGesti);
								jpnlGestiImp.add(jrbGesti);
								jrbGesti.setText("Impostazioni Gesti");
								jrbGesti.setBounds(14, 12, 155, 20);
								jrbGesti.setFont(new java.awt.Font("Segoe UI",0,16));
								jrbGesti.addActionListener(new ActionListener(){

									public void actionPerformed(ActionEvent evt) {
										jbtALGActionPerformed(evt,6);
										
									}});
							}
							{
								jbtGesti = new JButton();
								jpnlGestiImp.add(jbtGesti);
								jbtGesti.setText("Vai");
								jbtGesti.setBounds(405, 12, 55, 25);
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
						pnlGenResult.setBorder(BorderFactory.createTitledBorder("Risultato"));
					}
				}
				{
					pnlLinguaggio = new JPanel();
					jTabbedPane.addTab("Linguaggio", null, pnlLinguaggio, null);
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
							setFrasiList();
							pnlContesto = new JPanel();
							pnlLingEvidenze.add(pnlContesto);
							pnlContesto.setBounds(5, 255, 487, 98);
							pnlContesto.setBorder(BorderFactory.createTitledBorder(null, "Contesto", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",1,12)));
							pnlContesto.setLayout(null);
							{
								lblContx = new JLabel();
								pnlContesto.add(lblContx);
								lblContx.setText("Mossa precedente del sistema:");
								lblContx.setBounds(11, 26, 184, 16);
								lblContx.setFont(new java.awt.Font("Arial",0,12));
							}
							{
								lblMtype = new JLabel();
								pnlContesto.add(lblMtype);
								lblMtype.setText("Mossa dell'utente:");
								lblMtype.setBounds(11, 58, 166, 16);
								lblMtype.setFont(new java.awt.Font("Arial",0,12));
							}
							{
								ComboBoxModel cmbContxModel = 
										new DefaultComboBoxModel(
												new String[] { Nodes.VALORE_NONSO, Nodes.MOSSA_SP, Nodes.MOSSA_AN, Nodes.MOSSA_CO, Nodes.MOSSA_QU, Nodes.MOSSA_SU, Nodes.MOSSA_NA, Nodes.MOSSA_FA  });
								cmbContx = new JComboBox();
								pnlContesto.add(cmbContx);
								cmbContx.setModel(cmbContxModel);
								cmbContx.setBounds(207, 23, 261, 23);
								cmbContx.setToolTipText("<html>Identifica la tipologia dell'ultima mossa del <br>dialogo eseguita dal sistema</html>");
							}
							{
								ComboBoxModel cmbMtypeModel = 
										new DefaultComboBoxModel(
												new String[] { Nodes.VALORE_NONSO, Nodes.MOSSA_SP, Nodes.MOSSA_AN, Nodes.MOSSA_CO, Nodes.MOSSA_QU, Nodes.MOSSA_FA });
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
							pnlSegni.setBorder(BorderFactory.createTitledBorder(null, "Segni Linguistici", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",1,12)));
							pnlSegni.setLayout(null);
							{
								lblConf = new JLabel();
								pnlSegni.add(lblConf);
								lblConf.setText("Espressioni confidenziali:");
								lblConf.setBounds(9, 69, 149, 16);
								lblConf.setFont(new java.awt.Font("Arial",0,12));
							}
							{
								ComboBoxModel cmbConfModel = 
										new DefaultComboBoxModel(
												new String[] { Nodes.VALORE_NONSO,Nodes.VALORE_YES,Nodes.VALORE_NO });
								cmbConf = new JComboBox();
								pnlSegni.add(cmbConf);
								cmbConf.setModel(cmbConfModel);
								cmbConf.setBounds(158, 66, 81, 23);
								cmbConf.setToolTipText("<html>Identifica l'uso di espressioni confidenziali, <br>proverbiali, dialettali, ecc.. da parte dell'utente</html>");
							}
							{
								lblCiao = new JLabel();
								pnlSegni.add(lblCiao);
								lblCiao.setText("Espressioni di saluto:");
								lblCiao.setBounds(263, 69, 123, 16);
								lblCiao.setFont(new java.awt.Font("Arial",0,12));
							}
							{
								ComboBoxModel cmbCiaoModel = 
										new DefaultComboBoxModel(
												new String[] { Nodes.VALORE_NONSO,Nodes.VALORE_YES,Nodes.VALORE_NO });
								cmbCiao = new JComboBox();
								pnlSegni.add(cmbCiao);
								cmbCiao.setModel(cmbCiaoModel);
								cmbCiao.setBounds(387, 66, 81, 23);
								cmbCiao.setToolTipText("<html>Identifica il fatto che l'utente abbia <br>utilizzato espressioni di saluto (es. Ciao, Arrivederci)</html>");
							}
							{
								lblMe = new JLabel();
								pnlSegni.add(lblMe);
								lblMe.setText("Forme in 1a persona:");
								lblMe.setBounds(9, 105, 149, 16);
								lblMe.setFont(new java.awt.Font("Arial",0,12));
							}
							{
								lblYou = new JLabel();
								pnlSegni.add(lblYou);
								lblYou.setText("Forme in 2a persona:");
								lblYou.setBounds(263, 105, 142, 16);
								lblYou.setFont(new java.awt.Font("Arial",0,12));
							}
							{
								ComboBoxModel cmbMeModel = 
										new DefaultComboBoxModel(
												new String[] { Nodes.VALORE_NONSO,Nodes.VALORE_YES,Nodes.VALORE_NO });
								cmbMe = new JComboBox();
								pnlSegni.add(cmbMe);
								cmbMe.setModel(cmbMeModel);
								cmbMe.setBounds(158, 102, 81, 23);
								cmbMe.setToolTipText("<html>Identifica l'uso di espressioni e <br>forme verbali in prima persona</html>");
							}
							{
								ComboBoxModel cmbYouModel = 
										new DefaultComboBoxModel(
												new String[] { Nodes.VALORE_NONSO,Nodes.VALORE_YES,Nodes.VALORE_NO });
								cmbYou = new JComboBox();
								pnlSegni.add(cmbYou);
								cmbYou.setModel(cmbYouModel);
								cmbYou.setBounds(387, 102, 81, 23);
								cmbYou.setToolTipText("<html>Identifica l'uso di espressioni e <br>forme verbali in seconda persona</html>");
							}
							{
								lblLeng = new JLabel();
								pnlSegni.add(lblLeng);
								lblLeng.setText("Lunghezza messaggio: ");
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
								lblQmar.setText("Punto interrogativo: ");
								lblQmar.setBounds(263, 33, 124, 16);
								lblQmar.setFont(new java.awt.Font("Arial",0,12));
							}
							{
								ComboBoxModel cmqQmarModel = 
										new DefaultComboBoxModel(
												new String[] { Nodes.VALORE_NONSO,Nodes.VALORE_YES,Nodes.VALORE_NO });
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
							btnReset.setText("Reset Dialogo");
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
										for (int i=tblEvidenzeModel.getRowCount()-1;i>=0;i--)
											tblEvidenzeModel.removeRow(i);
										
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
								tblEvidenzeModel = 
										new DefaultTableModel(
												new String[][] {},
												new String[] { "M.Sys","M.Utente","Lung", "P.Interr.", "Conf.","Saluto","Forme 1a", "Forme 2a" });
								tblEvidenze = new JTable();
								jScrollPane1.setViewportView(tblEvidenze);
								tblEvidenze.setModel(tblEvidenzeModel);
								tblEvidenze.setBounds(11, 28, 481, 199);
								tblEvidenze.setEnabled(false);
							}
						}
						{
							lblStorico = new JLabel();
							pnlLingEvidenze.add(lblStorico);
							lblStorico.setText("Storico evidenze mosse del dialogo");
							lblStorico.setBounds(7, 145, 221, 18);
						}
						{
							btnAggiungi = new JButton();
							pnlLingEvidenze.add(btnAggiungi);
							btnAggiungi.setText("Aggiungi Mossa");
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
										tblEvidenzeModel.addRow(new String[]{getValoreTabella(cmbContx.getSelectedItem().toString()),getValoreTabella(cmbMtype.getSelectedItem().toString()),getValoreTabella(txtLeng.getText()),getValoreTabella(cmbQmar.getSelectedItem().toString()),getValoreTabella(cmbConf.getSelectedItem().toString()),getValoreTabella(cmbCiao.getSelectedItem().toString()),getValoreTabella(cmbMe.getSelectedItem().toString()),getValoreTabella(cmbYou.getSelectedItem().toString()) });
										
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
							btnRimuovi.setText("Rimuovi Mossa");
							btnRimuovi.setBounds(214, 500, 130, 28);
							btnRimuovi.setToolTipText("Rimuove l'ultima mossa del dialogo.");
							btnRimuovi.setEnabled(false);
							btnRimuovi.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									
									//Rimuove l'ultima mossa dallo storico
									tblEvidenzeModel.removeRow(tblEvidenzeModel.getRowCount()-1);
									
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
				{
					pnlAudio = new JPanel();
					jTabbedPane.addTab("Audio", null, pnlAudio, null);
				}
				{
					pnlGesti = new JPanel();
					//FlowLayout pnlGestiLayout = new FlowLayout();
					jTabbedPane.addTab("Gesti", null, pnlGesti, null);
					pnlGesti.setLayout(null);
					//pnlGesti.setPreferredSize(new java.awt.Dimension(1033, 744));
					{
						pnlGestiEvidenze = new JPanel();
						pnlGesti.add(pnlGestiEvidenze);
						BoxLayout jPanel1Layout = new BoxLayout(pnlGestiEvidenze, javax.swing.BoxLayout.Y_AXIS);
						pnlGestiEvidenze.setBorder(BorderFactory.createTitledBorder(null,"Evidenze",TitledBorder.LEADING,TitledBorder.DEFAULT_POSITION,new java.awt.Font("Segoe UI",3,12),new java.awt.Color(0,0,0)));
						pnlGestiEvidenze.setLayout(null);
						pnlGestiEvidenze.setBounds(12, 0, 505, 545);
						//gestione lista frasi caricate dal file .txt 
							setFrasiList();
						{
							pnlSegniLing = new JPanel();
							pnlGestiEvidenze.add(pnlSegniLing);
							pnlSegniLing.setBorder(BorderFactory.createTitledBorder(null,"Segni Linguistici",TitledBorder.LEADING,TitledBorder.DEFAULT_POSITION,new java.awt.Font("Segoe UI",1,12)));
							pnlSegniLing.setLayout(null);
							pnlSegniLing.setBounds(13, 100, 482, 136);
							{
								lblArms = new JLabel();
								pnlSegniLing.add(lblArms);
								lblArms.setText("Braccia");
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
								lblLegs.setText("Gambe");
								lblLegs.setFont(new java.awt.Font("Arial",0,12));
								lblLegs.setBounds(9, 105, 149, 16);
							}
							{
								lblHands = new JLabel();
								pnlSegniLing.add(lblHands);
								lblHands.setText("Mani");
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
						/*{
							//jButton1 = new JButton();
							//pnlGestiEvidenze.add(jButton1);
							btnAggiungi.setText("Aggiungi Mossa");
							btnAggiungi.setToolTipText("Imposta le evidenze definite in una nuova mossa del dialogo.");
							btnAggiungi.setBounds(361, 496, 129, 28);
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
										tblEvidenzeModel.addRow(new String[]{getValoreTabella(cmbContx.getSelectedItem().toString()),getValoreTabella(cmbMtype.getSelectedItem().toString()),getValoreTabella(txtLeng.getText()),getValoreTabella(cmbQmar.getSelectedItem().toString()),getValoreTabella(cmbConf.getSelectedItem().toString()),getValoreTabella(cmbCiao.getSelectedItem().toString()),getValoreTabella(cmbMe.getSelectedItem().toString()),getValoreTabella(cmbYou.getSelectedItem().toString()) });
										 int index = jListFrasi.getSelectedIndex();
										    //jListFrasiModel.remove(index);
										
										// Disegna il grafico
										disegnaGrafici();
										
									}
									catch(NumberFormatException e)
									{
										JOptionPane.showMessageDialog(null, "La lunghezza del messaggio deve essere un numero intero maggiore di 0");
									}
								}
							});
						}*/
					/*	{
							jButton2 = new JButton();
							pnlGestiEvidenze.add(jButton2);
							jButton2.setEnabled(false);
							jButton2.setText("Reset Dialogo");
							jButton2.setToolTipText("Resetta il dialogo, eliminando tutte le mosse definite.");
							jButton2.setBounds(7, 496, 136, 28);
							jButton2.addActionListener(new ActionListener() {
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
										for (int i=tblEvidenzeModel.getRowCount()-1;i>=0;i--)
											tblEvidenzeModel.removeRow(i);
										
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
						}*/
						{
							jButton3 = new JButton();
							pnlGestiEvidenze.add(jButton3);
							jButton3.setEnabled(false);
							jButton3.setText("Rimuovi Mossa");
							jButton3.setToolTipText("Rimuove l'ultima mossa del dialogo.");
							jButton3.setBounds(223, 496, 133, 28);
							jButton3.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									
									//Rimuove l'ultima mossa dallo storico
									tblEvidenzeModel.removeRow(tblEvidenzeModel.getRowCount()-1);
									
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
							chartPanel1.setBounds(17, 295, 453, 261);
						}
						{
							chartPanel2 = new ChartPanel(andamento, true);
							pnlGestiSA.add(chartPanel2);
							chartPanel2.setBounds(17, 33, 453, 234);
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
			ds.addValue(last_sa[2],"Negativa","Social Attitude");
			ds.addValue(last_sa[1],"Neutrale","Social Attitude");
			ds.addValue(last_sa[0],"Positiva","Social Attitude");
		}
		else
		{
			ds.addValue(sa_iniziale[2],"Negativa","Social Attitude");
			ds.addValue(sa_iniziale[1],"Neutrale","Social Attitude");
			ds.addValue(sa_iniziale[0],"Positiva","Social Attitude");
		}
		istogramma = ChartFactory.createBarChart("Social Attitude corrente", "", "Probabilità", ds, PlotOrientation.VERTICAL, true, true, false);
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
		pnlIst.setBounds(17, 254, 453, 252);

		
		DefaultCategoryDataset dsPositive = new DefaultCategoryDataset();
		DefaultCategoryDataset dsNeutral = new DefaultCategoryDataset();
		DefaultCategoryDataset dsBad = new DefaultCategoryDataset();

		dsPositive.addValue(sa_iniziale[0], "Positiva", "0");
		dsNeutral.addValue(sa_iniziale[1], "Neutrale", "0");
		dsBad.addValue(sa_iniziale[2], "Negativa", "0");

		for (Integer i=1; i <= sa_ling_history.size(); i++)
		{
			dsPositive.addValue(sa_ling_history.get(i-1)[0],"Positiva",i.toString());
			dsNeutral.addValue(sa_ling_history.get(i-1)[1],"Neutrale",i.toString());
			dsBad.addValue(sa_ling_history.get(i-1)[2],"Negativa",i.toString());
		}
		andamento = ChartFactory.createLineChart("Andamento Social Attitude", "Mossa", "Probabilità", dsBad, PlotOrientation.VERTICAL, true, true, false);
		
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
		pnlAnd.setBounds(17, 25, 453, 204);
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
						jListFrasi.setSelectedIndex(0);
						
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
		case 2: System.out.println("Bottone Audio");break;
				
		case 3: System.out.println("Bottone Gesti");break;
		case 4: {System.out.println("Radio Bottone Linguaggio");
				jbtLing.setEnabled(true);
				jbtAudio.setEnabled(false);
				jbtGesti.setEnabled(false);
				break;}
				
		case 5: {System.out.println("Radio Bottone Audio");
				jbtLing.setEnabled(false);
				jbtAudio.setEnabled(true);
				jbtGesti.setEnabled(false);
				break;}
				
		case 6: {System.out.println("Radio Bottone Gesti");
				jbtLing.setEnabled(false);
				jbtAudio.setEnabled(false);
				jbtGesti.setEnabled(true);
				break;}
		
				
		}
		  
		  
		
		//TODO add your code for jbtAudio.actionPerformed
	}
	private void setFrasiList(){
		lblFrasi = new JLabel();
		pnlLingEvidenze.add(lblFrasi);
		lblFrasi.setText("Lista frasi utente");
		lblFrasi.setBounds(7, 23, 90, 17);
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

}
