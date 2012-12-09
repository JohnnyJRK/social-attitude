package gui;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
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

	private static final long serialVersionUID = 1L;
	private JPanel pnlEvidenze;
	private JLabel lblContx;
	private JLabel lblLeng;
	private ChartPanel chartPanel2;
	private ChartPanel chartPanel1;
	private JPanel jPanel4;
	private JButton jButton3;
	private JLabel jLabel9;
	private JButton jButton2;
	private JButton jButton1;
	private JComboBox cmbHands;
	private JComboBox cmbLegs;
	private JLabel lblHands;
	private JLabel lblLegs;
	private JComboBox cmbArms;
	private JLabel lblArms;
	private JPanel jPanel3;
	private JPanel jPanel1;
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
	private JPanel pnlSA;
	private ChartPanel pnlAnd;
	private ChartPanel pnlIst;

	private HuginInterface hi;
	private ArrayList<double[]> sa_history;
	private JPanel pnlResult;
	private JPanel pnlSAgen;
	private JPanel pnlGenerale;
	private JPanel pnlGesti;
	private JPanel pnlAudio;
	private JTabbedPane jTabbedPane1;
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
			sa_history = new ArrayList<double[]>();
			sa_iniziale = hi.getSA();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setResizable(false);
			this.setTitle("Hugin Framework");
			getContentPane().setLayout(null);
			{
				jTabbedPane1 = new JTabbedPane();
				getContentPane().add(jTabbedPane1);
				jTabbedPane1.setBounds(0, 0, 1024, 618);
				jTabbedPane1.setSize(1024, 600);
				{
					pnlGenerale = new JPanel();
					jTabbedPane1.addTab("Generale", null, pnlGenerale, null);
					pnlGenerale.setPreferredSize(new java.awt.Dimension(939, 556));
					pnlGenerale.setSize(1015, 592);
					{
						pnlResult = new JPanel();
						pnlGenerale.add(pnlResult);
						pnlResult.setPreferredSize(new java.awt.Dimension(499, 573));
						pnlResult.setBorder(BorderFactory.createTitledBorder("Risultato"));
					}
					{
						pnlSAgen = new JPanel();
						BoxLayout thisLayout = new BoxLayout(pnlSAgen, javax.swing.BoxLayout.Y_AXIS);
						pnlGenerale.add(pnlSAgen);
						pnlSAgen.setFont(new java.awt.Font("Dialog",0,12));
						pnlSAgen.setPreferredSize(new java.awt.Dimension(493,568));
						pnlSAgen.setBorder(BorderFactory.createTitledBorder("Social Attitude"));
						pnlSAgen.setLayout(thisLayout);
						pnlSAgen.setBounds(519, 12, 487, 548);
					}
				}
				{
					pnlLinguaggio = new JPanel();
					jTabbedPane1.addTab("Linguaggio", null, pnlLinguaggio, null);
					pnlLinguaggio.setBounds(0, 0, 994, 548);
					pnlLinguaggio.setSize(1024, 650);
					pnlLinguaggio.setPreferredSize(new java.awt.Dimension(1019, 581));
					//pnlLinguaggio.setTabTitle("Linguaggio");
					{
						pnlEvidenze = new JPanel();
						pnlLinguaggio.add(pnlEvidenze);
						pnlEvidenze.setBounds(12, 14, 503, 544);
						pnlEvidenze.setBorder(BorderFactory.createTitledBorder(null, "Evidenze", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI",3,12), new java.awt.Color(0,0,0)));
						pnlEvidenze.setLayout(null);
						pnlEvidenze.setPreferredSize(new java.awt.Dimension(505, 569));
						{
							pnlContesto = new JPanel();
							pnlEvidenze.add(pnlContesto);
							pnlContesto.setBounds(5, 233, 487, 98);
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
							pnlEvidenze.add(pnlSegni);
							pnlSegni.setBounds(5, 337, 487, 136);
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
							btnAggiungi = new JButton();
							pnlEvidenze.add(btnAggiungi);
							btnAggiungi.setText("Aggiungi Mossa");
							btnAggiungi.setBounds(361, 496, 129, 28);
							btnAggiungi.setToolTipText("Imposta le evidenze definite in una nuova mossa del dialogo.");
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
										
										if(sa_history.size()>0)
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
										sa_history.add(sa);
										
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
							btnReset = new JButton();
							pnlEvidenze.add(btnReset);
							btnReset.setText("Reset Dialogo");
							btnReset.setBounds(7, 496, 136, 28);
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
										sa_history = new ArrayList<double[]>();
										
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
							pnlEvidenze.add(jScrollPane1);
							jScrollPane1.setBounds(11, 49, 475, 172);
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
							pnlEvidenze.add(lblStorico);
							lblStorico.setText("Storico evidenze mosse del dialogo");
							lblStorico.setBounds(17, 28, 221, 16);
						}
						{
							btnRimuovi = new JButton();
							pnlEvidenze.add(btnRimuovi);
							btnRimuovi.setText("Rimuovi Mossa");
							btnRimuovi.setBounds(223, 496, 133, 28);
							btnRimuovi.setToolTipText("Rimuove l'ultima mossa del dialogo.");
							btnRimuovi.setEnabled(false);
							btnRimuovi.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									
									//Rimuove l'ultima mossa dallo storico
									tblEvidenzeModel.removeRow(tblEvidenzeModel.getRowCount()-1);
									
									//Rimuove gli ultimi valori di SA memorizzati
									sa_history.remove(sa_history.size()-1);
									
									// Attiva/disattiva i pulsanti appropriati
									if(sa_history.size()==0)
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
						pnlSA = new JPanel();
						pnlLinguaggio.add(pnlSA);
						pnlSA.setBounds(519, 12, 487, 548);
						pnlSA.setBorder(BorderFactory.createTitledBorder("Social Attitude"));
						pnlSA.setFont(new java.awt.Font("Dialog",0,12));
						pnlSA.setLayout(null);
						pnlSA.setPreferredSize(new java.awt.Dimension(493, 568));
						//pnlSA.setTabTitle("");
						disegnaGrafici();
					}
				}
				{
					pnlAudio = new JPanel();
					jTabbedPane1.addTab("Audio", null, pnlAudio, null);
				}
				{
					pnlGesti = new JPanel();
					FlowLayout pnlGestiLayout = new FlowLayout();
					//FlowLayout pnlGestiLayout = new FlowLayout();
					jTabbedPane1.addTab("Gesti", null, pnlGesti, null);
					pnlGesti.setLayout(pnlGestiLayout);
					pnlGesti.setPreferredSize(new java.awt.Dimension(797, 574));
					{
						jPanel1 = new JPanel();
						BoxLayout jPanel1Layout = new BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS);
						jPanel1.setLayout(jPanel1Layout);
						pnlGesti.add(jPanel1);
						jPanel1.setPreferredSize(new java.awt.Dimension(505,569));
						jPanel1.setBorder(BorderFactory.createTitledBorder(null,"Evidenze",TitledBorder.LEADING,TitledBorder.DEFAULT_POSITION,new java.awt.Font("Segoe UI",3,12),new java.awt.Color(0,0,0)));
						jPanel1.setLayout(null);
						jPanel1.setBounds(12, 14, 503, 544);
						{
							jPanel3 = new JPanel();
							jPanel1.add(jPanel3);
							jPanel3.setBorder(BorderFactory.createTitledBorder(null,"Segni Linguistici",TitledBorder.LEADING,TitledBorder.DEFAULT_POSITION,new java.awt.Font("Segoe UI",1,12)));
							jPanel3.setLayout(null);
							jPanel3.setBounds(1, 97, 487, 136);
							{
								lblArms = new JLabel();
								jPanel3.add(lblArms);
								lblArms.setText("Braccia");
								lblArms.setFont(new java.awt.Font("Arial",0,12));
								lblArms.setBounds(9, 69, 149, 16);
							}
							{
								ComboBoxModel cmbArmsModel = 
										new DefaultComboBoxModel(
												new String[] { "Braccia1", "Braccia2", "Braccia3" });
								cmbArms = new JComboBox();
								jPanel3.add(cmbArms);
								cmbArms.setModel(cmbArmsModel);
								cmbArms.setToolTipText("<html>Identifica la posizione delle braccia dell'utente <br>es: incrociate, aperte, ecc..</html>");
								cmbArms.setBounds(158, 66, 81, 23);
							}
							{
								lblLegs = new JLabel();
								jPanel3.add(lblLegs);
								lblLegs.setText("Gambe");
								lblLegs.setFont(new java.awt.Font("Arial",0,12));
								lblLegs.setBounds(9, 105, 149, 16);
							}
							{
								lblHands = new JLabel();
								jPanel3.add(lblHands);
								lblHands.setText("Mani");
								lblHands.setFont(new java.awt.Font("Arial",0,12));
								lblHands.setBounds(9, 36, 143, 16);
							}
							{
								ComboBoxModel cmbLegsModel = 
										new DefaultComboBoxModel(
												new String[] { "Gambe1", "Gambe2", "Gambe3" });
								cmbLegs = new JComboBox();
								jPanel3.add(cmbLegs);
								cmbLegs.setModel(cmbLegsModel);
								cmbLegs.setToolTipText("<html>Identifica la posizione delle gambe dell'utente <br>es: divaricate, incrociate, ecc.. </html>");
								cmbLegs.setBounds(158, 102, 81, 23);
							}
							{
								ComboBoxModel cmbHandsModel = 
										new DefaultComboBoxModel(
												new String[] { "Mani1", "Mani2", "Mani3" });
								cmbHands = new JComboBox();
								jPanel3.add(cmbHands);
								cmbHands.setModel(cmbHandsModel);
								cmbHands.setToolTipText("<html>Identifica la posizione delle mani dell'utente <br>es: sul volto, sul naso, sulla bocca ecc..</html>");
								cmbHands.setBounds(158, 33, 81, 23);
							}
						}
						{
							jButton1 = new JButton();
							jPanel1.add(jButton1);
							jButton1.setText("Aggiungi Mossa");
							jButton1.setToolTipText("Imposta le evidenze definite in una nuova mossa del dialogo.");
							jButton1.setBounds(361, 496, 129, 28);
							jButton1.addActionListener(new ActionListener() {
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
										
										if(sa_history.size()>0)
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
										sa_history.add(sa);
										
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
							jButton2 = new JButton();
							jPanel1.add(jButton2);
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
										sa_history = new ArrayList<double[]>();
										
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
							jLabel9 = new JLabel();
							jPanel1.add(jLabel9);
							jLabel9.setText("Storico evidenze mosse del dialogo");
							jLabel9.setBounds(17, 28, 221, 16);
						}
						{
							jButton3 = new JButton();
							jPanel1.add(jButton3);
							jButton3.setEnabled(false);
							jButton3.setText("Rimuovi Mossa");
							jButton3.setToolTipText("Rimuove l'ultima mossa del dialogo.");
							jButton3.setBounds(223, 496, 133, 28);
							jButton3.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent evt) {
									
									//Rimuove l'ultima mossa dallo storico
									tblEvidenzeModel.removeRow(tblEvidenzeModel.getRowCount()-1);
									
									//Rimuove gli ultimi valori di SA memorizzati
									sa_history.remove(sa_history.size()-1);
									
									// Attiva/disattiva i pulsanti appropriati
									if(sa_history.size()==0)
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
						jPanel4 = new JPanel();
						pnlGesti.add(jPanel4);
						jPanel4.setFont(new java.awt.Font("Dialog",0,12));
						jPanel4.setPreferredSize(new java.awt.Dimension(493,568));
						jPanel4.setBorder(BorderFactory.createTitledBorder("Social Attitude"));
					    jPanel4.setLayout(null);
						jPanel4.setBounds(519, 12, 487, 548);
						{
							chartPanel1 = new ChartPanel(istogramma, true);
							jPanel4.add(chartPanel1);
							chartPanel1.setBounds(17, 295, 453, 261);
						}
						{
							chartPanel2 = new ChartPanel(andamento, true);
							jPanel4.add(chartPanel2);
							chartPanel2.setBounds(17, 33, 453, 234);
						}
					}
				}
			}
			pack();
			this.setSize(1024, 650);
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
			pnlSA.remove(pnlAnd);
		if(pnlIst!=null)
			pnlSA.remove(pnlIst);
		
		
		// Crea e disegna l'istogramma
		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		if(sa_history.size()>0)
		{
			double last_sa[] = sa_history.get(sa_history.size()-1);
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

		pnlSA.add(pnlIst);
		pnlIst.setBounds(17, 295, 453, 261);

		
		DefaultCategoryDataset dsPositive = new DefaultCategoryDataset();
		DefaultCategoryDataset dsNeutral = new DefaultCategoryDataset();
		DefaultCategoryDataset dsBad = new DefaultCategoryDataset();

		dsPositive.addValue(sa_iniziale[0], "Positiva", "0");
		dsNeutral.addValue(sa_iniziale[1], "Neutrale", "0");
		dsBad.addValue(sa_iniziale[2], "Negativa", "0");

		for (Integer i=1; i <= sa_history.size(); i++)
		{
			dsPositive.addValue(sa_history.get(i-1)[0],"Positiva",i.toString());
			dsNeutral.addValue(sa_history.get(i-1)[1],"Neutrale",i.toString());
			dsBad.addValue(sa_history.get(i-1)[2],"Negativa",i.toString());
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
		
		
		pnlSA.add(pnlAnd);
		pnlAnd.setBounds(17, 33, 453, 234);

	}
}
