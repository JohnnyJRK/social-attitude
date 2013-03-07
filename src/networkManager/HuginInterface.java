package networkManager;

import COM.hugin.HAPI.Domain;
import COM.hugin.HAPI.ExceptionHugin;
import COM.hugin.HAPI.LabelledDCNode;
import COM.hugin.HAPI.IntervalDCNode;



/** Classe per l'interfacciamento alla RPC */
public class HuginInterface
{
	static String path_rete;			// Path della rete
	Domain rete;										// Rete su cui è possibile effettuare ragionamenti
	double valori_SA[];
	
	
	
	
	/** Costruttore di classe. Crea il primo nodo. */
	public HuginInterface(int r)
	{
		switch(r){
		
		case 1:path_rete = "reti/Rete.hkb";addNodo(r);break;
		case 2:path_rete = "reti/socialattitude_audio.hkb";addNodo(r);break;
		case 3:path_rete = "reti/socialattitude_gesti.hkb";addNodo(r);break;
		case 4:path_rete = "reti/socialattitude_generale.hkb";addNodo(r);break;
		
		
		}
	}
	
	
	
	
	/** Crea un nuovo nodo della rete dinamica*/
	public void addNodo(int r)
	{
		try 
		{
			System.out.println("Add Nodo");
			rete = new Domain(path_rete);
			System.out.println(path_rete);
			
			rete.triangulate(Domain.H_TM_FILL_IN_WEIGHT);
			rete.compile();
			if (valori_SA!=null)
			{
				LabelledDCNode sa_prev = (LabelledDCNode)rete.getNodeByName(Nodes.SOCIAL_ATTITUDE_PREC);
				sa_prev.getTable().setDataItem(sa_prev.getStateIndex(Nodes.SA_POSITIVE), valori_SA[0]);
				System.out.println("ad "+sa_prev.getTable().getDataItem(0));
				sa_prev.getTable().setDataItem(sa_prev.getStateIndex(Nodes.SA_NEUTRAL), valori_SA[1]);
				System.out.println("ad "+sa_prev.getTable().getDataItem(1));
				sa_prev.getTable().setDataItem(sa_prev.getStateIndex(Nodes.SA_BAD), valori_SA[2]);
				System.out.println("ad "+sa_prev.getTable().getDataItem(2));
				
			}
		} 
		catch (ExceptionHugin e) 
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/** Restituisce le probabilità di Social Attitude ed imposta le attuali come ultimi valori rilevati.
	 * 
	 * @return Probabilità per la Social Attitude rilevate.
	 */
	public double[] getSA()
	{
		try 
		{
			System.out.println("getSA");
			System.out.println(rete.getFileName());
			rete.propagate(Domain.H_EQUILIBRIUM_SUM, Domain.H_EVIDENCE_MODE_NORMAL);
			LabelledDCNode sa = (LabelledDCNode)rete.getNodeByName(Nodes.SOCIAL_ATTITUDE);
			valori_SA = new double[3];
			valori_SA[0] = sa.getBelief(sa.getStateIndex(Nodes.SA_POSITIVE));
			System.out.println(valori_SA[0]);
			valori_SA[1] = sa.getBelief(sa.getStateIndex(Nodes.SA_NEUTRAL));
			System.out.println(valori_SA[1]);
			valori_SA[2] = sa.getBelief(sa.getStateIndex(Nodes.SA_BAD));
			System.out.println(valori_SA[2]);
			return valori_SA;
		} 
		catch (ExceptionHugin e) 
		{
			System.out.println("eccezzione getSA");
			e.printStackTrace();
			return null;
		}
	}
	
	
	

	
	
	
	/** Imposta un'evidenza su un nodo previsto della rete
	 * 
	 * @param nodo Nodo su cui impostare l'evidenza
	 * @param valore Valore dell'evidenza da impostare
	 * @return Esito. True = evidenza impostata correttamente. False = Errore nell'impostare l'evidenza
	 */
	public boolean setLingEvidenza(String nodo, String valore)
	{
		boolean codRitorno;
		
		if(rete==null)
			codRitorno = false;
		else
		{
			try
			{
				if(nodo.equals(Nodes.LUNGHEZZA))
				{System.out.println("Lunghezza");
					if(!valore.equals(""))
					{System.out.println("lunghezza settata");
						IntervalDCNode temp = (IntervalDCNode)rete.getNodeByName(nodo);
						temp.selectState(temp.getStateIndex(new Integer(valore)));
						
					}
				}
				else if(nodo.equals(Nodes.MOSSA_PREC_SISTEMA) || nodo.equals(Nodes.MOSSA_UTENTE))
				{
					if(Nodes.getMossa(valore)!=null)
					{System.out.println("mossa prec sist o mossa utente");
						LabelledDCNode temp = (LabelledDCNode)rete.getNodeByName(nodo);
						temp.selectState(temp.getStateIndex(Nodes.getMossa(valore)));
						
					}
				}
				else
				{
					if(Nodes.getValore(valore)!=null)
					{
						LabelledDCNode temp = (LabelledDCNode)rete.getNodeByName(nodo);
						temp.selectState(temp.getStateIndex(Nodes.getValore(valore)));
					}
				}
				codRitorno = true;	
			}
			catch(Exception e)
			{System.out.println("eccezione setlingevidenza");
				e.printStackTrace();
				codRitorno = false;
			}
		}
		return codRitorno;
	}
	
	public boolean setAudioEvidenza(String nodo, String valore)
	{
		boolean codRitorno;
		LabelledDCNode temp=null;
		if(rete==null)
			codRitorno = false;
		else
		{try {
		temp=(LabelledDCNode)rete.getNodeByName(nodo);
		temp.selectState(temp.getStateIndex(Nodes.getMossa(valore)));
		System.out.println("setaudioevidenza");
		codRitorno = true;
		
		} catch (ExceptionHugin e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("eccezione setaudioevidenza");
			codRitorno = false;
		}
	}
		return codRitorno;
	}
	
	/** Resetta la rete dinamica e ricrea il primo nodo. */
	public void reset()
	{
		valori_SA = null;
		addNodo(0);
	}
}
		
		

