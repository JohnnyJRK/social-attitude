/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.uniba.di.dalu.datasource;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.n3.nanoxml.XMLException;

/**
 *
 * @author Sergio
 */
public class DataMapper {


    private String percorsoXMLFileAudio;
    private String percorsoFileTrainingSet;
    private String percorsoDataSet;
    private String estensioneFileARFF;
    private XMLManager xmlManager;

    public DataMapper() {
        percorsoXMLFileAudio = "." + File.separator + "xmlFileAudio";
        percorsoDataSet = "." + File.separator + "apprendimento";
        estensioneFileARFF = ".VALENZA.arff";
    }

    /**
     * Serve per impostare il percorso dei file XML relativi all'audio. Questa variabile
     * è già impostata con un valore di default nel costruttore.
     * @param percorsoXMLFileAudio Percorso dei file XML relativi all'audio
     */
    public void setPercorsoXMLFileAudio(String percorsoXMLFileAudio) {
        this.percorsoXMLFileAudio = percorsoXMLFileAudio;
    }
    
    public void setFileXMLFileAudio(String percorsoXMLFileAudio, String nomeFile) throws FileNotFoundException{
    	this.percorsoXMLFileAudio = percorsoXMLFileAudio;
    	setFileXMLFileAudio(nomeFile);
    }
    
    public void setFileXMLFileAudio(String nomeFile) throws FileNotFoundException{
    	checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
    }

    /**
     *
     * Crea il file di test .arff da cui weka deve fare la previsione di
     * intenzione
     * @param nomeFile Nome del file ARFF senza estensione
     * @param istanza La serie di valori che componogono il file . arff da cui
     * weka estrarrà la previsione di intenzione
     */
    public void creaFileARFF(String nomeFile, String[] istanza, boolean append) throws FileNotFoundException, IOException {
        WEKAManager wekaManager = new WEKAManager(percorsoFileTrainingSet);
        String arffHeader = wekaManager.getARFFHeader();
        wekaManager = new WEKAManager(percorsoDataSet + File.separator + nomeFile + estensioneFileARFF);
        wekaManager.creaFileARFF(arffHeader, istanza, append);
    }

//     public void TEST_creaTrainingSet(String nomeTrainingSet, String[] istanza) throws FileNotFoundException, IOException {
//        WEKAManager wekaManager = new WEKAManager(percorsoFileTrainingSet);
//        String arffHeader = wekaManager.getARFFHeader();
//        wekaManager = new WEKAManager(nomeTrainingSet + ".arff");
//        wekaManager.creaFileARFF(arffHeader, istanza, true);
//    }
    /**
     *
     *
     */
    public String getXMLFileAudioName(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        String nomeXMLFileAudio = xmlManager.getValore("analisi", "nome_file");
        if (nomeXMLFileAudio.contains(File.separator)) {
        	if (System.getProperty("os.name").equalsIgnoreCase("Windows 8")||System.getProperty("os.name").equalsIgnoreCase("Windows 7")||System.getProperty("os.name").equalsIgnoreCase("Windows Vista")||System.getProperty("os.name").equalsIgnoreCase("Windows XP"))
            nomeXMLFileAudio = nomeXMLFileAudio.split(File.separator + File.separator)[nomeXMLFileAudio.split(File.separator + File.separator).length - 1];
        	else
        		nomeXMLFileAudio = nomeXMLFileAudio.split(File.separator)[nomeXMLFileAudio.split(File.separator).length - 1];
        }
        return nomeXMLFileAudio;
    }

    public String getIntensityDeviazioneStandard(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "intensity", "deviazione_standard");
    }

    public String[] getIntensityDeviazioniStandardLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "intensity", "deviazioni_standard_locali").split(",");
    }

    public String getIntensityMassimo(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "intensity", "massimo");
    }

    public String[] getIntensityMassimiLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "intensity", "massimi_locali").split(",");
    }

    public String getIntensityMedia(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "intensity", "media");
    }

    public String[] getIntensityMedieLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "intensity", "medie_locali").split(",");
    }

    public String getIntensityMinimo(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "intensity", "minimo");
    }

    public String[] getIntensityMinimiLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "intensity", "minimi_locali").split(",");
    }

    public String getIntensityTempoMassimo(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "intensity", "tempo_del_massimo");
    }

    public String[] getIntensityTempiMassimiLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "intensity", "tempi_massimi_locali").split(",");
    }

    public String getIntensityTempoMinimo(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "intensity", "tempo_del_minimo");
    }

    public String[] getIntensityTempiMinimiLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "intensity", "tempi_minimi_locali").split(",");
    }

    public String getPitchDeviazioneStandard(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "pitch", "deviazione_standard");
    }

    public String[] getPitchDeviazioniStandardLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "pitch", "deviazioni_standard_locali").split(",");
    }

    public String getPitchMassimo(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "pitch", "massimo");
    }

    public String[] getPitchMassimiLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "pitch", "massimi_locali").split(",");
    }

    public String getPitchMedia(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "pitch", "media");
    }

    public String[] getPitchMedieLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "pitch", "medie_locali").split(",");
    }

    public String getPitchMinimo(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "pitch", "minimo");
    }

    public String[] getPitchMinimiLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "pitch", "minimi_locali").split(",");
    }

    public String getPitchMassimoLogaritmico(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "pitch", "massimo_logaritmico");
    }

    public String[] getPitchMassimiLogaritmiciLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "pitch", "massimi_logaritmici_locali").split(",");
    }

    public String getPitchMediaLogaritmica(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "pitch", "media_logaritmica");
    }

    public String[] getPitchMedieLogaritmicheLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "pitch", "medie_logaritmiche_locali").split(",");
    }

    public String getPitchMinimoLogaritmico(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "pitch", "minimo_logaritmico");
    }

    public String[] getPitchMinimiLogaritmiciLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "pitch", "minimi_logaritmici_locali").split(",");
    }

    public String getPitchSlope(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "pitch", "slope");
    }

    public String getPitchNumeroCampioni(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "pitch", "numero_campioni");
    }

    public String getPitchTempoMassimo(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "pitch", "tempo_del_massimo");
    }

    public String[] getPitchTempiMassimiLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "pitch", "tempi_massimi_locali").split(",");
    }

    public String getPitchTempoMinimo(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "pitch", "tempo_del_minimo");
    }

    public String[] getPitchTempiMinimiLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "pitch", "tempi_minimi_locali").split(",");
    }

    public String getSoundDeviazioneStandard(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "sound", "deviazione_standard");
    }

    public String[] getSoundDeviazioniStandardLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "sound", "deviazioni_standard_locali").split(",");
    }

    public String getSoundEnergia(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "sound", "energia");
    }

    public String getSoundEnergiaAria(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "sound", "energia_in_aria");
    }

    public String getSoundIntensitaDB(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "sound", "intensita_db");
    }

    public String getSoundMassimo(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "sound", "massimo");
    }

    public String[] getSoundMassimiLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "sound", "massimi_locali").split(",");
    }

    public String getSoundMedia(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "sound", "media");
    }
    
    public String getGender(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi",  "gender");
    }
    
    public String getDuration(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi",  "duration");
    }

    public String[] getSoundMedieLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "sound", "medie_locali").split(",");
    }

    public String getSoundMinimo(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "sound", "minimo");
    }

    public String[] getSoundMinimiLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "sound", "minimi_locali").split(",");
    }

    public String getSoundPotenza(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "sound", "potenza");
    }

    public String getSoundPotenzaAria(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "sound", "potenza_in_aria");
    }

    public String getSoundTempoMassimo(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "sound", "tempo_del_massimo");
    }

    public String[] getSoundTempiMassimiLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "sound", "tempi_massimi_locali").split(",");
    }

    public String getSoundTempoMinimo(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "sound", "tempo_del_minimo");
    }

    public String[] getSoundTempiMinimiLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "sound", "tempi_minimi_locali").split(",");
    }

    public String getSoundValoreEfficace(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "sound", "valore_efficace");
    }

    public String getSpectrumGravityCentre(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "spectrum", "gravity_centre");
    }

    public String getSpectrumDeviazioneStandard(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "spectrum", "deviazione_standard");
    }

    public String getSpectrumSkewness(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "spectrum", "skewness");
    }

    public String getSpectrumKurtosis(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "spectrum", "kurtosis");
    }

    public String getSpectrumCentralMoment(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "spectrum", "central_moment");
    }

    public String getHarmonicityMinimo(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "harmonicity", "minimo");
    }

    public String[] getHarmonicityMinimiLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "harmonicity", "minimi_locali").split(",");
    }

    public String getHarmonicityMedia(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "harmonicity", "media");
    }

    public String[] getHarmonicityMedieLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "harmonicity", "medie_locali").split(",");
    }

    public String getHarmonicityMassimo(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "harmonicity", "massimo");
    }

    public String[] getHarmonicityMassimiLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "harmonicity", "massimi_locali").split(",");
    }

    public String getHarmonicityDeviazioneStandard(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "harmonicity", "deviazione_standard");
    }

    public String[] getHarmonicityDeviazioniStandardLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "harmonicity", "deviazioni_standard_locali").split(",");
    }

    public String getHarmonicityTempoMinimo(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "harmonicity", "tempo_del_minimo");
    }

    public String[] getHarmonicityTempiMinimiLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "harmonicity", "tempi_minimi_locali").split(",");
    }

    public String getHarmonicityTempoMassimo(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "harmonicity", "tempo_del_massimo");
    }

    public String[] getHarmonicityTempiMassimiLocali(String nomeFile) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        checkFileExists(percorsoXMLFileAudio + File.separator + nomeFile);
        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFile);
        return xmlManager.getValore("analisi", "harmonicity", "tempi_massimi_locali").split(",");
    }

    /**
     * Imposta la tipologia di TrainingSet
     * NOTA: Se si modifica il nome assegnato a "estensioneFileARFF", modificarlo anche
     * nella funzione "deleteAllFilesAssociatedAudio()"
     * @param set Tipo di TrainingSet
     */
//    public void setTipoTrainingSet(TipoTrainingSet set) {
//        switch (set) {
//            case AROUSAL:
//                percorsoFileTrainingSet = percorsoDataSet + File.separator + "TrainingSetAROUSAL.arff";
//                estensioneFileARFF = "." + set.toString() + ".arff";
//                break;
//            case VALENZA:
//                percorsoFileTrainingSet = percorsoDataSet + File.separator + "TrainingSetVALENZA.arff";
//                estensioneFileARFF = "." + set.toString() + ".arff";
//                break;
//            case SESSO:
//                percorsoFileTrainingSet = percorsoDataSet + File.separator + "TrainingSetSESSO.arff";
//                estensioneFileARFF = "." + set.toString() + ".arff";
//                break;
//            default:
//                percorsoFileTrainingSet = percorsoDataSet + File.separator + "TrainingSet.arff";
//                estensioneFileARFF = "." + set.toString() + ".arff";
//        }
//
//    }

    /**
     *
     * Si occupa di leggere il TrainingSet da un file e di caricarlo nell'oggetto
     * @return Oggetto di tipo Instances che rappresenta il training-set
     */
//    public Instances getTrainingSet() throws Exception, FileNotFoundException {
//        WEKAManager wekaManager = new WEKAManager(percorsoFileTrainingSet);
//        return wekaManager.getDataSet();
//    }

    /**
     *
     * Legge il data-set da classificare dal rispettivo file relativo al file
     * audio specificato
     * @param nomeFile Nome del file audio a cui deve essere associato un file .arff
     * @return Oggetto di tipo Instances che rappresenta il dataset da clasificare
     */
//    public Instances getUnclassifiedSet(String nomeFile) throws Exception, FileNotFoundException {
//        WEKAManager wekaManager = new WEKAManager(percorsoDataSet + File.separator + nomeFile + estensioneFileARFF);
//        return wekaManager.getDataSet();
//    }

    /**
     *
     * Cancella tutti i file con estensione differete da wav associati al file
     * audio
     * @param nomeFile  Nome del file audio
     */
//    public void deleteAllFilesAssociatedAudio(String nomeFileAudio) {
//        for (TipoTrainingSet set : TipoTrainingSet.values()) {
//            String estensione = "." + set.toString() + ".arff";
//            WEKAManager wekaManager = new WEKAManager(percorsoDataSet + File.separator + nomeFileAudio + estensione);
//            wekaManager.deleteARFFFile();
//        }
//        XMLManager xmlManager = new XMLManager(percorsoXMLFileAudio + File.separator + nomeFileAudio + ".xml");
//        FileAudioList fileAudioList = new FileAudioList();
//        File fileRecogDaCancellare = new File(fileAudioList.getPercorsoFileAudio(nomeFileAudio) + ".RECOG");
//        xmlManager.deleteXMLFile();
//        fileAudioList = null;
//        if (fileRecogDaCancellare.exists()) {
//            fileRecogDaCancellare.delete();
//        }
//    }

    /**
     *
     *
     */
    private void checkFileExists(String nomeFile) throws FileNotFoundException {
        File file = new File(nomeFile);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
    }
    


    public String getIntensityDeviazioneStandard() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "intensity", "deviazione_standard");
    }

    public String[] getIntensityDeviazioniStandardLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "intensity", "deviazioni_standard_locali").split(",");
    }

    public String getIntensityMassimo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "intensity", "massimo");
    }

    public String[] getIntensityMassimiLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "intensity", "massimi_locali").split(",");
    }

    public String getIntensityMedia() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "intensity", "media");
    }

    public String[] getIntensityMedieLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "intensity", "medie_locali").split(",");
    }

    public String getIntensityMinimo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "intensity", "minimo");
    }

    public String[] getIntensityMinimiLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "intensity", "minimi_locali").split(",");
    }

    public String getIntensityTempoMassimo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "intensity", "tempo_del_massimo");
    }

    public String[] getIntensityTempiMassimiLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "intensity", "tempi_massimi_locali").split(",");
    }

    public String getIntensityTempoMinimo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "intensity", "tempo_del_minimo");
    }

    public String[] getIntensityTempiMinimiLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "intensity", "tempi_minimi_locali").split(",");
    }

    public String getPitchDeviazioneStandard() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "pitch", "deviazione_standard");
    }

    public String[] getPitchDeviazioniStandardLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "pitch", "deviazioni_standard_locali").split(",");
    }

    public String getPitchMassimo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "pitch", "massimo");
    }

    public String[] getPitchMassimiLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "pitch", "massimi_locali").split(",");
    }

    public String getPitchMedia() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "pitch", "media");
    }

    public String[] getPitchMedieLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "pitch", "medie_locali").split(",");
    }

    public String getPitchMinimo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "pitch", "minimo");
    }

    public String[] getPitchMinimiLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "pitch", "minimi_locali").split(",");
    }

    public String getPitchMassimoLogaritmico() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "pitch", "massimo_logaritmico");
    }

    public String[] getPitchMassimiLogaritmiciLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "pitch", "massimi_logaritmici_locali").split(",");
    }

    public String getPitchMediaLogaritmica() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "pitch", "media_logaritmica");
    }

    public String[] getPitchMedieLogaritmicheLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "pitch", "medie_logaritmiche_locali").split(",");
    }

    public String getPitchMinimoLogaritmico() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "pitch", "minimo_logaritmico");
    }

    public String[] getPitchMinimiLogaritmiciLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "pitch", "minimi_logaritmici_locali").split(",");
    }

    public String getPitchSlope() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "pitch", "slope");
    }

    public String getPitchNumeroCampioni() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "pitch", "numero_campioni");
    }

    public String getPitchTempoMassimo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "pitch", "tempo_del_massimo");
    }

    public String[] getPitchTempiMassimiLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "pitch", "tempi_massimi_locali").split(",");
    }

    public String getPitchTempoMinimo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "pitch", "tempo_del_minimo");
    }

    public String[] getPitchTempiMinimiLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "pitch", "tempi_minimi_locali").split(",");
    }

    public String getSoundDeviazioneStandard() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "sound", "deviazione_standard");
    }

    public String[] getSoundDeviazioniStandardLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "sound", "deviazioni_standard_locali").split(",");
    }

    public String getSoundEnergia() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "sound", "energia");
    }

    public String getSoundEnergiaAria() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "sound", "energia_in_aria");
    }

    public String getSoundIntensitaDB() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "sound", "intensita_db");
    }

    public String getSoundMassimo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "sound", "massimo");
    }

    public String[] getSoundMassimiLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "sound", "massimi_locali").split(",");
    }

    public String getSoundMedia() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "sound", "media");
    }
    
    public String getGender() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi",  "gender");
    }
    
    public String getDuration() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi",  "duration");
    }

    public String[] getSoundMedieLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "sound", "medie_locali").split(",");
    }

    public String getSoundMinimo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "sound", "minimo");
    }

    public String[] getSoundMinimiLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "sound", "minimi_locali").split(",");
    }

    public String getSoundPotenza() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "sound", "potenza");
    }

    public String getSoundPotenzaAria() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "sound", "potenza_in_aria");
    }

    public String getSoundTempoMassimo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "sound", "tempo_del_massimo");
    }

    public String[] getSoundTempiMassimiLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "sound", "tempi_massimi_locali").split(",");
    }

    public String getSoundTempoMinimo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "sound", "tempo_del_minimo");
    }

    public String[] getSoundTempiMinimiLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "sound", "tempi_minimi_locali").split(",");
    }

    public String getSoundValoreEfficace() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "sound", "valore_efficace");
    }

    public String getSpectrumGravityCentre() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "spectrum", "gravity_centre");
    }

    public String getSpectrumDeviazioneStandard() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "spectrum", "deviazione_standard");
    }

    public String getSpectrumSkewness() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "spectrum", "skewness");
    }

    public String getSpectrumKurtosis() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "spectrum", "kurtosis");
    }

    public String getSpectrumCentralMoment() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "spectrum", "central_moment");
    }

    public String getHarmonicityMinimo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "harmonicity", "minimo");
    }

    public String[] getHarmonicityMinimiLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "harmonicity", "minimi_locali").split(",");
    }

    public String getHarmonicityMedia() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "harmonicity", "media");
    }

    public String[] getHarmonicityMedieLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "harmonicity", "medie_locali").split(",");
    }

    public String getHarmonicityMassimo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "harmonicity", "massimo");
    }

    public String[] getHarmonicityMassimiLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "harmonicity", "massimi_locali").split(",");
    }

    public String getHarmonicityDeviazioneStandard() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "harmonicity", "deviazione_standard");
    }

    public String[] getHarmonicityDeviazioniStandardLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "harmonicity", "deviazioni_standard_locali").split(",");
    }

    public String getHarmonicityTempoMinimo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "harmonicity", "tempo_del_minimo");
    }

    public String[] getHarmonicityTempiMinimiLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "harmonicity", "tempi_minimi_locali").split(",");
    }

    public String getHarmonicityTempoMassimo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "harmonicity", "tempo_del_massimo");
    }

    public String[] getHarmonicityTempiMassimiLocali() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException, XMLException {
        return xmlManager.getValore("analisi", "harmonicity", "tempi_massimi_locali").split(",");
    }
    
}
