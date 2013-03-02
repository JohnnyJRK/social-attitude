package networkManager;
import COM.hugin.HAPI.Domain;
import COM.hugin.HAPI.ExceptionHugin;
import COM.hugin.HAPI.LabelledDCNode;
import COM.hugin.HAPI.IntervalDCNode;

public class provaHugin {
	static String path_rete = "reti/socialattitude_audio.hkb";			// Path della rete
	Domain rete;										// Rete su cui è possibile effettuare ragionamenti
	double valori_SA[],v[];
	LabelledDCNode valenza=null,arousal=null,sa=null,sa_prev=null;
	
	public static void main(String args[]){
		provaHugin p=new provaHugin();
	}
	
	
	/** Costruttore di classe. Crea il primo nodo. */
	public provaHugin()
	{
		initNetwork();
	}
	public void initNetwork()
	{
		try 
		{
			v=new double[3];
			valori_SA=new double[3];
			System.out.println("initialize network");
			rete = new Domain(path_rete);
			rete.triangulate(Domain.H_TM_FILL_IN_WEIGHT);
			rete.compile();
			//recupera i nodi della rete e li assegna ad un oggetto LabelledDCNode
			valenza=(LabelledDCNode)rete.getNodeByName("Valenza");
			arousal=(LabelledDCNode)rete.getNodeByName("Arousal");
			sa_prev=(LabelledDCNode)rete.getNodeByName("Social_Attitude_prev");
			//setta l'evidenza sullo stato S del nodo N
			valenza.selectState(valenza.getStateIndex("Positivo"));
			arousal.selectState(arousal.getStateIndex("Basso"));
			sa_prev.selectState(sa_prev.getStateIndex("N"));
			//controlla se sono state settate le evidenze
			if(valenza.evidenceIsEntered()&& arousal.evidenceIsEntered())
				System.out.println("si");
			else
				System.out.println("no");
			//propaga le evidenze
			rete.propagate(Domain.H_EQUILIBRIUM_SUM, Domain.H_EVIDENCE_MODE_NORMAL);
			//recupera il nodo principale 
			sa=(LabelledDCNode)rete.getNodeByName("Social_Attitude");
			//recupera i valori degli stati dopo la propagazione delle evidenze e li salva nel vettore v[]
			v[0]=sa.getBelief(0);
			v[1]=sa.getBelief(sa.getStateIndex("N"));//recupera il numero di indice dello stato con nome "xyz"
			v[2]=sa.getBelief(2);
			//v=getSA();
			System.out.println(v[0]);
			System.out.println(v[1]);
			System.out.println(v[2]);
			//dovrebbe inserire i valori del nodo social_attitude in social_attitude_prev
			sa_prev.getTable().setDataItem(0, v[0]);
			sa_prev.getTable().setDataItem(1, v[1]);
			sa_prev.getTable().setDataItem(2, v[2]);
			
			valori_SA[0]=sa_prev.getBelief(0);
			valori_SA[1]=sa_prev.getBelief(1);
			valori_SA[2]=sa_prev.getBelief(2);
			
			System.out.println("Valori sa_prev\n");
			System.out.println(valori_SA[0]);
			System.out.println(valori_SA[1]);
			System.out.println(valori_SA[2]);
			
			
			//System.out.println("loaded network file: "+rete.getFileName()+" "+node.getAttribute("P"));
			} 
		catch (ExceptionHugin e) 
		{
			e.printStackTrace();
		}
		
}
	
	
	public void addNodo()
	{
		try 
		{
			System.out.println("ciao");
			LabelledDCNode sa_prev=null;
			rete = new Domain(path_rete);
			rete.triangulate(Domain.H_TM_FILL_IN_WEIGHT);
			rete.compile();
			if (valori_SA!=null)
			{
				sa_prev = (LabelledDCNode)rete.getNodeByName(Nodes.SOCIAL_ATTITUDE_PREC);
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
}