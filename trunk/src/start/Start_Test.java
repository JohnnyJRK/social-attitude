package start;


import networkManager.HuginInterface;
import networkManager.Nodes;

import com.csvreader.*;

/** Classe di avvio dell'applicazione*/
public class Start_Test 
{
	static String path_csv = "test/datasetTest.csv";
	public static void main(String args[])
	{
		System.loadLibrary("hapi73");
		HuginInterface hi;		
		CsvReader reader;
		String sa;
		double ris[];
		
		try 
		{
			reader = new CsvReader(path_csv,';');
			reader.readHeaders();
			
			int esatti = 0;
			int inconsistenti = 0;
			double i = 0;
			double max;
			String maxSA;
			
			while (reader.readRecord())
			{
				i++;
				hi = new HuginInterface();
				
				sa = reader.get(Nodes.SOCIAL_ATTITUDE);

				hi.setEvidenza(Nodes.LUNGHEZZA, reader.get(Nodes.LUNGHEZZA));
				hi.setEvidenza(Nodes.MOSSA_PREC_SISTEMA, reader.get(Nodes.MOSSA_PREC_SISTEMA));
				hi.setEvidenza(Nodes.MOSSA_UTENTE, reader.get(Nodes.MOSSA_UTENTE));
				hi.setEvidenza(Nodes.ESPR_SALUTO, reader.get(Nodes.ESPR_SALUTO));
				hi.setEvidenza(Nodes.P_INTERROGATIVO, reader.get(Nodes.P_INTERROGATIVO));
				hi.setEvidenza(Nodes.ESPR_CONFIDENZIALI, reader.get(Nodes.ESPR_CONFIDENZIALI));
				hi.setEvidenza(Nodes.ESPR_2PERSONA, reader.get(Nodes.ESPR_2PERSONA));
				hi.setEvidenza(Nodes.ESPR_1PERSONA, reader.get(Nodes.ESPR_1PERSONA));
				
				
				try
				{
					ris = hi.getSA();
					max = ris[0];
					maxSA = Nodes.SA_POSITIVE;
					
					if (ris[1]>max)
					{
						max = ris[1];
						maxSA = Nodes.SA_NEUTRAL;
					}
					if (ris[2]>max)
					{
						max = ris[2];
						maxSA = Nodes.SA_BAD;
					}
					if(maxSA.equals(sa))
						esatti++;
				}
				catch(Exception ex)
				{
					inconsistenti++;
				}
			}
			reader.close();
			
			System.out.println("Accuratezza: "+(esatti/i));
			System.out.println("Evidenze considerate inconsistenti: "+ inconsistenti);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
