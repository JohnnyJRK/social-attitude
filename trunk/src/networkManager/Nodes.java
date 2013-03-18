package networkManager;

/** Classe che contiene i nomi dei nodi di interesse della rete e i possibili stati assumibili*/
public class Nodes 
{
	public static String SOCIAL_ATTITUDE = "Social_Attitude";
	public static String SOCIAL_ATTITUDE_PREC = "Social_Attitude_prev";
	public static String SA_BAD = "B";
	public static String SA_NEUTRAL = "N";
	public static String SA_POSITIVE = "P";
	
	public static String SESSO = "gender";
	private static String SESSO_M_VAL = "M";
	private static String SESSO_F_VAL = "F";
	
	public static String SESSO_MASCHIO = "Maschio";
	public static String SESSO_FEMMINA = "Femmina";
	public static String SESSO_NONSO = "Incerto";
	
	
	public static String ESPR_CONFIDENZIALI = "conf";
	public static String LUNGHEZZA = "leng";
	public static String ESPR_2PERSONA = "you";
	public static String ESPR_1PERSONA = "me";
	public static String P_INTERROGATIVO = "qmar";
	public static String ESPR_SALUTO = "ciao";
	public static String MOSSA_PREC_SISTEMA = "contx";
	public static String MOSSA_UTENTE = "mtyp";
	
	private static String MOSSA_SP_VAL = "SP";
	private static String MOSSA_QU_VAL = "QU";
	private static String MOSSA_SU_VAL = "SU";
	private static String MOSSA_NA_VAL = "NA";
	private static String MOSSA_AN_VAL = "AN";
	private static String MOSSA_FA_VAL = "FA";
	private static String MOSSA_CO_VAL = "CO";
	
	public static String MOSSA_SP = "Self Presentation";
	public static String MOSSA_QU = "Question";
	public static String MOSSA_SU = "Suggestion";
	public static String MOSSA_NA = "No Answer";
	public static String MOSSA_AN = "Answer";
	public static String MOSSA_FA = "Goodbye";
	public static String MOSSA_CO = "Comment";
	
	private static String VALORE_YES_VAL = "Y";
	private static String VALORE_NO_VAL = "N";

	public static String VALORE_YES = "Yes";
	public static String VALORE_NO = "No";
	public static String VALORE_NONSO = "Incerto";
	
	
	
	
	
	
	
	
	
	//rete audio
	
	public static String VALENZA = "Valenza";
	public static String VALORE_POSITIVO_VAL = "Positivo";
	public static String VALORE_NEUTRO_VAL = "Neutro";
	public static String VALORE_NEGATIVO_VAL = "Negativo";
	public static String VALORE_MOLTO_NEGATIVO_VAL = "Molto negativo";
	
	public static String VALORE_POSITIVO = "Positive";
	public static String VALORE_NEUTRO= "Neutral";
	public static String VALORE_NEGATIVO = "Negative";
	public static String VALORE_MOLTO_NEGATIVO = "VeryNeg";
	
	public static String AROUSAL = "Arousal";
	public static String VALORE_ALTO_VAL = "Alto";
	public static String VALORE_MEDIO_VAL = "Medio";
	public static String VALORE_BASSO_VAL = "Basso";
	
	public static String VALORE_ALTO = "High";
	public static String VALORE_MEDIO = "Medium";
	public static String VALORE_BASSO = "Low";
	public static String SOCIAL_ATTITUDE_LING = "Social_Attitude_ling";
	public static String SOCIAL_ATTITUDE_AUDIO = "Social_Attitude_audio";
	public static String SOCIAL_ATTITUDE_GESTI = "Social_Attitude_gesti";
	
//	Metodi da utilizzare in fase di test di un dataset	
//	public static String getMossa(String descr_mossa)
//	{
//		return descr_mossa;
//	}
//	
//	public static String getValore(String descr_valore)
//	{
//		return descr_valore;
//	}
//	
//	public static String getSesso(String sesso)
//	{
//		return sesso;
//	}
	
	
	
	
	/** Restituisce lo stato di un nodo (contx o mtype) a partire dalla descrizione testuale
	 * 
	 * @param descr_mossa Descrizione testuale dello stato
	 * @return Stato del nodo
	 */
	public static String getMossa(String descr_mossa)
	{
		if(descr_mossa.equals(MOSSA_SP))
			return MOSSA_SP_VAL;
		else if(descr_mossa.equals(MOSSA_QU))
			return MOSSA_QU_VAL;
		else if(descr_mossa.equals(MOSSA_SU))
			return MOSSA_SU_VAL;
		else if(descr_mossa.equals(MOSSA_NA))
			return MOSSA_NA_VAL;
		else if(descr_mossa.equals(MOSSA_AN))
			return MOSSA_AN_VAL;
		else if(descr_mossa.equals(MOSSA_FA))
			return MOSSA_FA_VAL;
		else if(descr_mossa.equals(MOSSA_CO))
			return MOSSA_CO_VAL;
		
		else if(descr_mossa.equals(VALORE_POSITIVO))
			return VALORE_POSITIVO_VAL;
		else if(descr_mossa.equals(VALORE_NEUTRO))
			return VALORE_NEUTRO_VAL;
		else if(descr_mossa.equals(VALORE_NEGATIVO))
			return VALORE_NEGATIVO_VAL;
		else if(descr_mossa.equals(VALORE_MOLTO_NEGATIVO))
			return VALORE_MOLTO_NEGATIVO_VAL;
		else if(descr_mossa.equals(VALORE_ALTO))
			return VALORE_ALTO_VAL;
		else if(descr_mossa.equals(VALORE_MEDIO))
			return VALORE_MEDIO_VAL;
		else if(descr_mossa.equals(VALORE_BASSO))
			return VALORE_BASSO_VAL;
		else
			return null;
	}
	
	
	
	
	
	/** Restituisce lo stato di un nodo (avente stato Y o N) a partire dalla descrizione testuale
	 * 
	 * @param descr_valore Descrizione testuale dello stato
	 * @return Stato del nodo.
	 */
	public static String getValore(String descr_valore)
	{
		if(descr_valore.equals(VALORE_YES))
			return VALORE_YES_VAL;
		else if(descr_valore.equals(VALORE_NO))
			return VALORE_NO_VAL;
		else
			return null;
	}
	
	
	
	
	
	/** Restituisce lo stato di un nodo (gender) a partire dalla descrizione testuale
	 * 
	 * @param sesso Descrizione testuale dello stato
	 * @return Stato del nodo.
	 */
	public static String getSesso(String sesso)
	{
		if(sesso.equals(SESSO_MASCHIO))
			return SESSO_M_VAL;
		else if(sesso.equals(SESSO_FEMMINA))
			return SESSO_F_VAL;
		else
			return null;
	}
}
