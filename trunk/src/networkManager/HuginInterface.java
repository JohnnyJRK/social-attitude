package networkManager;

import COM.hugin.HAPI.Domain;
import COM.hugin.HAPI.ExceptionHugin;
import COM.hugin.HAPI.LabelledDCNode;
import COM.hugin.HAPI.IntervalDCNode;



/** Classe per l'interfacciamento alla RPC */
public class HuginInterface
{
	static String path_rete = "reti/Rete.hkb";			// Path della rete
	Domain rete;										// Rete su cui è possibile effettuare ragionamenti
	double valori_SA[];
	
	
	
	
	/** Costruttore di classe. Crea il primo nodo. */
	public HuginInterface()
	{
		addNodo();
	}
	
	
	
	
	
	/** Imposta un'evidenza su un nodo previsto della rete
	 * 
	 * @param nodo Nodo su cui impostare l'evidenza
	 * @param valore Valore dell'evidenza da impostare
	 * @return Esito. True = evidenza impostata correttamente. False = Errore nell'impostare l'evidenza
	 */
	public boolean setEvidenza(String nodo, String valore)
	{
		boolean codRitorno;
		
		if(rete==null)
			codRitorno = false;
		else
		{
			try
			{
				if(nodo.equals(Nodes.LUNGHEZZA))
				{
					if(!valore.equals(""))
					{
						IntervalDCNode temp = (IntervalDCNode)rete.getNodeByName(nodo);
						temp.selectState(temp.getStateIndex(new Integer(valore)));
					}
				}
				else if(nodo.equals(Nodes.MOSSA_PREC_SISTEMA) || nodo.equals(Nodes.MOSSA_UTENTE))
				{
					if(Nodes.getMossa(valore)!=null)
					{
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
			{
				e.printStackTrace();
				codRitorno = false;
			}
		}
		return codRitorno;
	}
	
	
	
	
	/** Restituisce le probabilità di Social Attitude ed imposta le attuali come ultimi valori rilevati.
	 * 
	 * @return Probabilità per la Social Attitude rilevate.
	 */
	public double[] getSA()
	{
		try 
		{
			rete.propagate(Domain.H_EQUILIBRIUM_SUM, Domain.H_EVIDENCE_MODE_NORMAL);
			LabelledDCNode sa = (LabelledDCNode)rete.getNodeByName(Nodes.SOCIAL_ATTITUDE);
			valori_SA = new double[3];
			valori_SA[0] = sa.getBelief(sa.getStateIndex(Nodes.SA_POSITIVE));
			valori_SA[1] = sa.getBelief(sa.getStateIndex(Nodes.SA_NEUTRAL));
			valori_SA[2] = sa.getBelief(sa.getStateIndex(Nodes.SA_BAD));
			return valori_SA;
		} 
		catch (ExceptionHugin e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	

	
	/** Crea un nuovo nodo della rete dinamica*/
	public void addNodo()
	{
		try 
		{
			rete = new Domain(path_rete);
			rete.triangulate(Domain.H_TM_FILL_IN_WEIGHT);
			rete.compile();
			if (valori_SA!=null)
			{
				LabelledDCNode sa_prev = (LabelledDCNode)rete.getNodeByName(Nodes.SOCIAL_ATTITUDE_PREC);
				sa_prev.getTable().setDataItem(sa_prev.getStateIndex(Nodes.SA_POSITIVE), valori_SA[0]);
				sa_prev.getTable().setDataItem(sa_prev.getStateIndex(Nodes.SA_NEUTRAL), valori_SA[1]);
				sa_prev.getTable().setDataItem(sa_prev.getStateIndex(Nodes.SA_BAD), valori_SA[2]);
			}
		} 
		catch (ExceptionHugin e) 
		{
			e.printStackTrace();
		}
	}
	
	

	
	
	/** Resetta la rete dinamica e ricrea il primo nodo. */
	public void reset()
	{
		valori_SA = null;
		addNodo();
	}
}
		
		

