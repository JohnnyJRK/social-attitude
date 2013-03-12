/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.uniba.di.dalu.prosody;

/**
 *
 * @author Sergio
 */
public class ProsodiaFileAudio {

    private String gender;
    private String duration;
	private String nomeFileAudio;
    private String soundMedia;
    private String[] soundMedieLocali;
    private String soundMinimo;
    private String[] soundMinimiLocali;
    private String soundMassimo;
    private String[] soundMassimiLocali;
    private String soundTempoMinimo;
    private String[] soundTempiMinimiLocali;
    private String soundTempoMassimo;
    private String[] soundTempiMassimiLocali;
    private String soundValoreEfficace;
    private String soundDeviazioneStandard;
    private String[] soundDeviazioniStandardLocali;
    private String soundEnergia;
    private String soundPotenza;
    private String soundEnergiaAria;
    private String soundPotenzaAria;
    private String soundIntensitaDB;
    private String pitchMinimo;
    private String[] pitchMinimiLocali;
    private String pitchMinimoLogaritmico;
    private String[] pitchMinimiLogaritmiciLocali;
    private String pitchMedio;
    private String[] pitchMedieLocali;
    private String pitchMedioLogaritmico;
    private String[] pitchMedieLogaritmicheLocali;
    private String pitchMassimo;
    private String[] pitchMassimiLocali;
    private String pitchMassimoLogaritmico;
    private String[] pitchMassimiLogaritmiciLocali;
    private String pitchTempoMinimo;
    private String[] pitchTempiMinimiLocali;
    private String pitchSlope;
    private String pitchTempoMassimo;
    private String[] pitchTempiMassimiLocali;
    private String pitchDeviazioneStandard;
    private String[] pitchDeviazioniStandardLocali;
    private String pitchNumeroCampioni;
    private String intensityMedia;
    private String[] intensityMedieLocali;
    private String intensityMinimo;
    private String[] intensityMinimiLocali;
    private String intesityMassimo;
    private String[] intensityMassimiLocali;
    private String intensityTempoMinimo;
    private String[] intensityTempiMinimiLocali;
    private String intensityTempoMassimo;
    private String[] intensityTempiMassimiLocali;
    private String intensityDeviazioneStandard;
    private String[] intensityDeviazioniStandardLocali;
    private String spectrumGravityCentre;
    private String spectrumDeviazioneStandard;
    private String spectrumSkewness;
    private String spectrumKurtosis;
    private String spectrumCentralMoment;
    private String harmonicityMinimo;
    private String[] harmonicityMinimiLocali;
    private String harmonicityMedia;
    private String[] harmonicityMedieLocali;
    private String harmonicityMassimo;
    private String[] harmonicityMassimiLocali;
    private String harmonicityDeviazioneStandard;
    private String[] harmonicityDeviazioniStandardLocali;
    private String harmonicityTempoMinimo;
    private String[] harmonicityTempiMinimiLocali;
    private String harmonicityTempoMassimo;
    private String[] harmonicityTempiMassimiLocali;

    private boolean soundNormalizzato = false;
    private boolean pitchNormalizzato = false;
    private boolean intensityNormalizzato = false;
    private boolean spectrumNormalizzato = false;
    private boolean harmonicityNormalizzato = false;

    private Double[] normalizeSeries(Double[] series) {
        Double Norma = 0d;
        for (int i = 0; i < series.length; i++) {
            Norma += Math.pow(series[i], 2);
        }
        Norma = Math.sqrt(Norma);
        for (int i = 0; i < series.length; i++) {
            series[i] = series[i] / Norma;
//            series[i] = series[i] * 10000000000d;
        }
        return series;
    }

    public void normalize() {
        if (soundMedia != null && !soundMedia.equals("") &&
                soundMinimo != null && !soundMinimo.equals("") &&
                soundMassimo != null && !soundMassimo.equals("") &&
                soundDeviazioneStandard != null && !soundDeviazioneStandard.equals("") &&
                soundValoreEfficace != null && !soundValoreEfficace.equals("") &&
                !soundNormalizzato
                ) {
            Double dSoundMedia, dSoundMinimo, dSoundMassimo, dSoundDeviazioneStandard, dSoundValoreEfficace;
            dSoundMedia = Double.parseDouble(getParteNumerica(soundMedia));
            dSoundMinimo = Double.parseDouble(getParteNumerica(soundMinimo));
            dSoundMassimo = Double.parseDouble(getParteNumerica(soundMassimo));
            dSoundDeviazioneStandard = Double.parseDouble(getParteNumerica(soundDeviazioneStandard));
            dSoundValoreEfficace = Double.parseDouble(getParteNumerica(soundValoreEfficace));

            Double[] normalizedVector = normalizeSeries(new Double [] {dSoundMedia, dSoundMinimo, dSoundMassimo, dSoundDeviazioneStandard, dSoundValoreEfficace});
            
            soundMedia = normalizedVector[0] + " Pascal";
            soundMinimo = normalizedVector[1] + " Pascal";
            soundMassimo = normalizedVector[2] + " Pascal";
            soundDeviazioneStandard = normalizedVector[3] + " Pascal";
            soundValoreEfficace = normalizedVector[4] + " Pascal";

            soundNormalizzato = true;
        }

        if (pitchMinimo != null && !pitchMinimo.equals("") &&
                pitchMedio != null && !pitchMedio.equals("") &&
                pitchMassimo != null && !pitchMassimo.equals("") &&
                pitchMassimoLogaritmico != null && !pitchMassimoLogaritmico.equals("") &&
                pitchMedioLogaritmico != null && !pitchMedioLogaritmico.equals("") &&
                pitchMinimoLogaritmico != null && !pitchMinimoLogaritmico.equals("") &&
                pitchSlope != null && !pitchSlope.equals("") &&
                pitchDeviazioneStandard != null && !pitchDeviazioneStandard.equals("") &&
                !pitchNormalizzato
                ) {
            Double dPitchMinimo, dPitchMedio, dPitchMassimo, dPitchMassimoLogaritmico, dPitchMedioLogaritmico;
            Double dPitchMinimoLogaritmico, dPitchSlope, dPitchDeviazioneStandard;
            dPitchMinimo = Double.parseDouble(getParteNumerica(pitchMinimo));
            dPitchMedio = Double.parseDouble(getParteNumerica(pitchMedio));
            dPitchMassimo = Double.parseDouble(getParteNumerica(pitchMassimo));
            dPitchMassimoLogaritmico = Double.parseDouble(getParteNumerica(pitchMassimoLogaritmico));
            dPitchMedioLogaritmico = Double.parseDouble(getParteNumerica(pitchMedioLogaritmico));
            dPitchMinimoLogaritmico = Double.parseDouble(getParteNumerica(pitchMinimoLogaritmico));
            dPitchSlope = Double.parseDouble(getParteNumerica(pitchSlope));
            dPitchDeviazioneStandard = Double.parseDouble(getParteNumerica(pitchDeviazioneStandard));
            

            Double[] normalizedVector = normalizeSeries(new Double [] {dPitchMinimo, dPitchMedio, dPitchMassimo, 
            dPitchMassimoLogaritmico, dPitchMedioLogaritmico, dPitchMinimoLogaritmico, dPitchSlope, dPitchDeviazioneStandard});
            
            pitchMinimo = normalizedVector[0] + " Hz";
            pitchMedio = normalizedVector[1] + " Hz";
            pitchMassimo = normalizedVector[2] + " Hz";
            pitchMassimoLogaritmico = normalizedVector[3] + " Hz";
            pitchMedioLogaritmico = normalizedVector[4] + " Hz";
            pitchMinimoLogaritmico = normalizedVector[5] + " Hz";
            pitchSlope = normalizedVector[6] + " Hertz/s";
            pitchDeviazioneStandard = normalizedVector[7] + " Hz";

            pitchNormalizzato = true;
        }

        if (intensityMinimo != null && !intensityMinimo.equals("") &&
                intensityMedia != null && !intensityMedia.equals("") &&
                intesityMassimo != null && !intesityMassimo.equals("") &&
                intensityDeviazioneStandard != null && !intensityDeviazioneStandard.equals("") &&
                !intensityNormalizzato
        ) {

            Double dIntensityMinimo, dIntensityMedia, dIntesityMassimo, dIntensityDeviazioneStandard;
            dIntensityMinimo = Double.parseDouble(getParteNumerica(intensityMinimo));
            dIntensityMedia = Double.parseDouble(getParteNumerica(intensityMedia));
            dIntesityMassimo = Double.parseDouble(getParteNumerica(intesityMassimo));
            dIntensityDeviazioneStandard = Double.parseDouble(getParteNumerica(intensityDeviazioneStandard));

            Double[] normalizedVector = normalizeSeries(new Double [] {dIntensityMinimo, dIntensityMedia, dIntesityMassimo, dIntensityDeviazioneStandard});
            
            intensityMinimo = normalizedVector[0] + " dB";
            intensityMedia = normalizedVector[1] + " dB";
            intesityMassimo = normalizedVector[2] + " dB";
            intensityDeviazioneStandard = normalizedVector[3] + " dB";

            intensityNormalizzato = true;
        }

        if (spectrumGravityCentre != null && !spectrumGravityCentre.equals("") &&
                spectrumDeviazioneStandard != null && !spectrumDeviazioneStandard.equals("") &&
                spectrumCentralMoment != null && !spectrumCentralMoment.equals("") &&
                !spectrumNormalizzato
                ) {
            Double dSpectrumGravityCentre, dSpectrumDeviazioneStandard, dSpectrumCentralMoment;
            dSpectrumGravityCentre = Double.parseDouble(getParteNumerica(spectrumGravityCentre));
            dSpectrumDeviazioneStandard = Double.parseDouble(getParteNumerica(spectrumDeviazioneStandard));
            dSpectrumCentralMoment = Double.parseDouble(getParteNumerica(spectrumCentralMoment));

            Double[] normalizedVector = normalizeSeries(new Double [] {dSpectrumGravityCentre, dSpectrumDeviazioneStandard, dSpectrumCentralMoment});

            spectrumGravityCentre = normalizedVector[0] + " Hertz";
            spectrumDeviazioneStandard = normalizedVector[1] + " Hertz";
            spectrumCentralMoment = normalizedVector[2] + " Hertz";

            spectrumNormalizzato = true;
        }

        if (harmonicityMinimo != null && !harmonicityMinimo.equals("") &&
                harmonicityMedia != null && !harmonicityMedia.equals("") &&
                harmonicityMassimo != null && !harmonicityMassimo.equals("") &&
                harmonicityDeviazioneStandard != null && !harmonicityDeviazioneStandard.equals("") &&
                !harmonicityNormalizzato
                ) {
            Double dHarmonicityMinimo, dHarmonicityMedia, dHarmonicityMassimo, dHarmonicityDeviazioneStandard;
            dHarmonicityMinimo = Double.parseDouble(getParteNumerica(harmonicityMinimo));
            dHarmonicityMedia = Double.parseDouble(getParteNumerica(harmonicityMedia));
            dHarmonicityMassimo = Double.parseDouble(getParteNumerica(harmonicityMassimo));
            dHarmonicityDeviazioneStandard = Double.parseDouble(getParteNumerica(harmonicityDeviazioneStandard));

            Double[] normalizedVector = normalizeSeries(new Double [] {dHarmonicityMinimo, dHarmonicityMedia, dHarmonicityMassimo, dHarmonicityDeviazioneStandard});

            harmonicityMinimo = normalizedVector[0] + " dB";
            harmonicityMedia = normalizedVector[1] + " dB";
            harmonicityMassimo = normalizedVector[2] + " dB";
            harmonicityDeviazioneStandard = normalizedVector[3] + " dB";

            harmonicityNormalizzato = true;
        }
    }

    public ProsodiaFileAudio(String nomeFileAudio) {
        this.nomeFileAudio = nomeFileAudio;
    }

    public void getNomeFileAudio(String inputNomeFileAudio) {
    	this.nomeFileAudio = inputNomeFileAudio;
    }
    /**
     * @return the nomeFileAudio
     */
    public String getNomeFileAudio() {
        return nomeFileAudio;
    }
    
    public void setGender(String inputGender){
    	gender = inputGender;
    }
    
    public String getGender(){
    	return gender;
    }
    
    public void setDuration(String inputDuration){
    	duration = inputDuration;
    }
    
    public String getDuration(){
    	return duration;
    }

    public String getParteNumerica(String parametro) {
        if ((parametro == null) || (parametro.equals(""))) {
            return "";
        }
        if (parametro.equals("--undefined--"))
            parametro = "0.0";
        return parametro.split(" ")[0];
    }

    /**
     * @param nomeFileAudio the nomeFileAudio to set
     */
    public void setNomeFileAudio(String nomeFileAudio) {
        this.nomeFileAudio = nomeFileAudio;
    }

    /**
     * @return the soundMedia
     */
    public String getSoundMedia() {
        return soundMedia;
    }

    /**
     * @param soundMedia the soundMedia to set
     */
    public void setSoundMedia(String soundMedia) {
        this.soundMedia = soundMedia;
    }

    /**
     * @return the soundMinimo
     */
    public String getSoundMinimo() {
        return soundMinimo;
    }

    /**
     * @param soundMinimo the soundMinimo to set
     */
    public void setSoundMinimo(String soundMinimo) {
        this.soundMinimo = soundMinimo;
    }

    /**
     * @return the soundMassimo
     */
    public String getSoundMassimo() {
        return soundMassimo;
    }

    /**
     * @param soundMassimo the soundMassimo to set
     */
    public void setSoundMassimo(String soundMassimo) {
        this.soundMassimo = soundMassimo;
    }

    /**
     * @return the soundTempoMinimo
     */
    public String getSoundTempoMinimo() {
        return soundTempoMinimo;
    }

    /**
     * @param soundTempoMinimo the soundTempoMinimo to set
     */
    public void setSoundTempoMinimo(String soundTempoMinimo) {
        this.soundTempoMinimo = soundTempoMinimo;
    }

    /**
     * @return the soundTempoMassimo
     */
    public String getSoundTempoMassimo() {
        return soundTempoMassimo;
    }

    /**
     * @param soundTempoMassimo the soundTempoMassimo to set
     */
    public void setSoundTempoMassimo(String soundTempoMassimo) {
        this.soundTempoMassimo = soundTempoMassimo;
    }

    /**
     * @return the soundValoreEfficace
     */
    public String getSoundValoreEfficace() {
        return soundValoreEfficace;
    }

    /**
     * @param soundValoreEfficace the soundValoreEfficace to set
     */
    public void setSoundValoreEfficace(String soundValoreEfficace) {
        this.soundValoreEfficace = soundValoreEfficace;
    }

    /**
     * @return the soundDeviazioneStandard
     */
    public String getSoundDeviazioneStandard() {
        return soundDeviazioneStandard;
    }

    /**
     * @param soundDeviazioneStandard the soundDeviazioneStandard to set
     */
    public void setSoundDeviazioneStandard(String soundDeviazioneStandard) {
        this.soundDeviazioneStandard = soundDeviazioneStandard;
    }

    /**
     * @return the soundEnergia
     */
    public String getSoundEnergia() {
        return soundEnergia;
    }

    /**
     * @param soundEnergia the soundEnergia to set
     */
    public void setSoundEnergia(String soundEnergia) {
        this.soundEnergia = soundEnergia;
    }

    /**
     * @return the soundPotenza
     */
    public String getSoundPotenza() {
        return soundPotenza;
    }

    /**
     * @param soundPotenza the soundPotenza to set
     */
    public void setSoundPotenza(String soundPotenza) {
        this.soundPotenza = soundPotenza;
    }

    /**
     * @return the soundEnergiaAria
     */
    public String getSoundEnergiaAria() {
        return soundEnergiaAria;
    }

    /**
     * @param soundEnergiaAria the soundEnergiaAria to set
     */
    public void setSoundEnergiaAria(String soundEnergiaAria) {
        this.soundEnergiaAria = soundEnergiaAria;
    }

    /**
     * @return the soundPotenzaAria
     */
    public String getSoundPotenzaAria() {
        return soundPotenzaAria;
    }

    /**
     * @param soundPotenzaAria the soundPotenzaAria to set
     */
    public void setSoundPotenzaAria(String soundPotenzaAria) {
        this.soundPotenzaAria = soundPotenzaAria;
    }

    /**
     * @return the soundIntensitaDB
     */
    public String getSoundIntensitaDB() {
        return soundIntensitaDB;
    }

    /**
     * @param soundIntensitaDB the soundIntensitaDB to set
     */
    public void setSoundIntensitaDB(String soundIntensitaDB) {
        this.soundIntensitaDB = soundIntensitaDB;
    }

    /**
     * @return the pitchMinimo
     */
    public String getPitchMinimo() {
        return pitchMinimo;
    }

    /**
     * @param pitchMinimo the pitchMinimo to set
     */
    public void setPitchMinimo(String pitchMinimo) {
        this.pitchMinimo = pitchMinimo;
    }

    /**
     * @return the pitchMedio
     */
    public String getPitchMedio() {
        return pitchMedio;
    }

    /**
     * @param pitchMedio the pitchMedio to set
     */
    public void setPitchMedio(String pitchMedio) {
        this.pitchMedio = pitchMedio;
    }

    /**
     * @return the pitchMassimo
     */
    public String getPitchMassimo() {
        return pitchMassimo;
    }

    /**
     * @param pitchMassimo the pitchMassimo to set
     */
    public void setPitchMassimo(String pitchMassimo) {
        this.pitchMassimo = pitchMassimo;
    }

    /**
     * @return the pitchTempoMinimo
     */
    public String getPitchTempoMinimo() {
        return pitchTempoMinimo;
    }

    /**
     * @param pitchTempoMinimo the pitchTempoMinimo to set
     */
    public void setPitchTempoMinimo(String pitchTempoMinimo) {
        this.pitchTempoMinimo = pitchTempoMinimo;
    }

    /**
     * @return the pitchTempoMassimo
     */
    public String getPitchTempoMassimo() {
        return pitchTempoMassimo;
    }

    /**
     * @param pitchTempoMassimo the pitchTempoMassimo to set
     */
    public void setPitchTempoMassimo(String pitchTempoMassimo) {
        this.pitchTempoMassimo = pitchTempoMassimo;
    }

    /**
     * @return the pitchDeviazioneStandard
     */
    public String getPitchDeviazioneStandard() {
        return pitchDeviazioneStandard;
    }

    /**
     * @param pitchDeviazioneStandard the pitchDeviazioneStandard to set
     */
    public void setPitchDeviazioneStandard(String pitchDeviazioneStandard) {
        this.pitchDeviazioneStandard = pitchDeviazioneStandard;
    }

    /**
     * @return the pitchNumeroCampioni
     */
    public String getPitchNumeroCampioni() {
        return pitchNumeroCampioni;
    }

    /**
     * @param pitchNumeroCampioni the pitchNumeroCampioni to set
     */
    public void setPitchNumeroCampioni(String pitchNumeroCampioni) {
        this.pitchNumeroCampioni = pitchNumeroCampioni;
    }

    /**
     * @return the intensityMedia
     */
    public String getIntensityMedia() {
        return intensityMedia;
    }

    /**
     * @param intensityMedia the intensityMedia to set
     */
    public void setIntensityMedia(String intensityMedia) {
        this.intensityMedia = intensityMedia;
    }

    /**
     * @return the intensityMinimo
     */
    public String getIntensityMinimo() {
        return intensityMinimo;
    }

    /**
     * @param intensityMinimo the intensityMinimo to set
     */
    public void setIntensityMinimo(String intensityMinimo) {
        this.intensityMinimo = intensityMinimo;
    }

    /**
     * @return the intesityMassimo
     */
    public String getIntesityMassimo() {
        return intesityMassimo;
    }

    /**
     * @param intesityMassimo the intesityMassimo to set
     */
    public void setIntesityMassimo(String intesityMassimo) {
        this.intesityMassimo = intesityMassimo;
    }

    /**
     * @return the intensityTempoMinimo
     */
    public String getIntensityTempoMinimo() {
        return intensityTempoMinimo;
    }

    /**
     * @param intensityTempoMinimo the intensityTempoMinimo to set
     */
    public void setIntensityTempoMinimo(String intensityTempoMinimo) {
        this.intensityTempoMinimo = intensityTempoMinimo;
    }

    /**
     * @return the intensityTempoMassimo
     */
    public String getIntensityTempoMassimo() {
        return intensityTempoMassimo;
    }

    /**
     * @param intensityTempoMassimo the intensityTempoMassimo to set
     */
    public void setIntensityTempoMassimo(String intensityTempoMassimo) {
        this.intensityTempoMassimo = intensityTempoMassimo;
    }

    /**
     * @return the intensityDeviazioneStandard
     */
    public String getIntensityDeviazioneStandard() {
        return intensityDeviazioneStandard;
    }

    /**
     * @param intensityDeviazioneStandard the intensityDeviazioneStandard to set
     */
    public void setIntensityDeviazioneStandard(String intensityDeviazioneStandard) {
        this.intensityDeviazioneStandard = intensityDeviazioneStandard;
    }

    /**
     * @return the pitchMinimoLogaritmico
     */
    public String getPitchMinimoLogaritmico() {
        return pitchMinimoLogaritmico;
    }

    /**
     * @param pitchMinimoLogaritmico the pitchMinimoLogaritmico to set
     */
    public void setPitchMinimoLogaritmico(String pitchMinimoLogaritmico) {
        this.pitchMinimoLogaritmico = pitchMinimoLogaritmico;
    }

    /**
     * @return the pitchMedioLogaritmico
     */
    public String getPitchMedioLogaritmico() {
        return pitchMedioLogaritmico;
    }

    /**
     * @param pitchMedioLogaritmico the pitchMedioLogaritmico to set
     */
    public void setPitchMedioLogaritmico(String pitchMedioLogaritmico) {
        this.pitchMedioLogaritmico = pitchMedioLogaritmico;
    }

    /**
     * @return the pitchMassimoLogaritmico
     */
    public String getPitchMassimoLogaritmico() {
        return pitchMassimoLogaritmico;
    }

    /**
     * @param pitchMassimoLogaritmico the pitchMassimoLogaritmico to set
     */
    public void setPitchMassimoLogaritmico(String pitchMassimoLogaritmico) {
        this.pitchMassimoLogaritmico = pitchMassimoLogaritmico;
    }

    /**
     * @return the pitchSlope
     */
    public String getPitchSlope() {
        return pitchSlope;
    }

    /**
     * @param pitchSlope the pitchSlope to set
     */
    public void setPitchSlope(String pitchSlope) {
        this.pitchSlope = pitchSlope;
    }

    /**
     * @return the spectrumGravityCentre
     */
    public String getSpectrumGravityCentre() {
        return spectrumGravityCentre;
    }

    /**
     * @param spectrumGravityCentre the spectrumGravityCentre to set
     */
    public void setSpectrumGravityCentre(String spectrumGravityCentre) {
        this.spectrumGravityCentre = spectrumGravityCentre;
    }

    /**
     * @return the spectrumDeviazioneStandard
     */
    public String getSpectrumDeviazioneStandard() {
        return spectrumDeviazioneStandard;
    }

    /**
     * @param spectrumDeviazioneStandard the spectrumDeviazioneStandard to set
     */
    public void setSpectrumDeviazioneStandard(String spectrumDeviazioneStandard) {
        this.spectrumDeviazioneStandard = spectrumDeviazioneStandard;
    }

    /**
     * @return the spectrumSkewness
     */
    public String getSpectrumSkewness() {
        return spectrumSkewness;
    }

    /**
     * @param spectrumSkewness the spectrumSkewness to set
     */
    public void setSpectrumSkewness(String spectrumSkewness) {
        this.spectrumSkewness = spectrumSkewness;
    }

    /**
     * @return the spectrumKurtosis
     */
    public String getSpectrumKurtosis() {
        return spectrumKurtosis;
    }

    /**
     * @param spectrumKurtosis the spectrumKurtosis to set
     */
    public void setSpectrumKurtosis(String spectrumKurtosis) {
        this.spectrumKurtosis = spectrumKurtosis;
    }

    /**
     * @return the spectrumCentralMoment
     */
    public String getSpectrumCentralMoment() {
        return spectrumCentralMoment;
    }

    /**
     * @param spectrumCentralMoment the spectrumCentralMoment to set
     */
    public void setSpectrumCentralMoment(String spectrumCentralMoment) {
        this.spectrumCentralMoment = spectrumCentralMoment;
    }

    /**
     * @return the harmonicityMinimo
     */
    public String getHarmonicityMinimo() {
        return harmonicityMinimo;
    }

    /**
     * @param harmonicityMinimo the harmonicityMinimo to set
     */
    public void setHarmonicityMinimo(String harmonicityMinimo) {
        this.harmonicityMinimo = harmonicityMinimo;
    }

    /**
     * @return the harmonicityMedia
     */
    public String getHarmonicityMedia() {
        return harmonicityMedia;
    }

    /**
     * @param harmonicityMedia the harmonicityMedia to set
     */
    public void setHarmonicityMedia(String harmonicityMedia) {
        this.harmonicityMedia = harmonicityMedia;
    }

    /**
     * @return the harmonicityMassimo
     */
    public String getHarmonicityMassimo() {
        return harmonicityMassimo;
    }

    /**
     * @param harmonicityMassimo the harmonicityMassimo to set
     */
    public void setHarmonicityMassimo(String harmonicityMassimo) {
        this.harmonicityMassimo = harmonicityMassimo;
    }

    /**
     * @return the harmonicityDeviazioneStandard
     */
    public String getHarmonicityDeviazioneStandard() {
        return harmonicityDeviazioneStandard;
    }

    /**
     * @param harmonicityDeviazioneStandard the harmonicityDeviazioneStandard to set
     */
    public void setHarmonicityDeviazioneStandard(String harmonicityDeviazioneStandard) {
        this.harmonicityDeviazioneStandard = harmonicityDeviazioneStandard;
    }

    /**
     * @return the harmonicityTempoMinimo
     */
    public String getHarmonicityTempoMinimo() {
        return harmonicityTempoMinimo;
    }

    /**
     * @param harmonicityTempoMinimo the harmonicityTempoMinimo to set
     */
    public void setHarmonicityTempoMinimo(String harmonicityTempoMinimo) {
        this.harmonicityTempoMinimo = harmonicityTempoMinimo;
    }

    /**
     * @return the harmonicityTempoMassimo
     */
    public String getHarmonicityTempoMassimo() {
        return harmonicityTempoMassimo;
    }

    /**
     * @param harmonicityTempoMassimo the harmonicityTempoMassimo to set
     */
    public void setHarmonicityTempoMassimo(String harmonicityTempoMassimo) {
        this.harmonicityTempoMassimo = harmonicityTempoMassimo;
    }

    /**
     * @return the soundMedieLocali
     */
    public String[] getSoundMedieLocali() {
        return soundMedieLocali;
    }

    /**
     * @param soundMedieLocali the soundMedieLocali to set
     */
    public void setSoundMedieLocali(String[] soundMedieLocali) {
        this.soundMedieLocali = soundMedieLocali;
    }

    /**
     * @return the soundMinimiLocali
     */
    public String[] getSoundMinimiLocali() {
        return soundMinimiLocali;
    }

    /**
     * @param soundMinimiLocali the soundMinimiLocali to set
     */
    public void setSoundMinimiLocali(String[] soundMinimiLocali) {
        this.soundMinimiLocali = soundMinimiLocali;
    }

    /**
     * @return the soundMassimiLocali
     */
    public String[] getSoundMassimiLocali() {
        return soundMassimiLocali;
    }

    /**
     * @param soundMassimiLocali the soundMassimiLocali to set
     */
    public void setSoundMassimiLocali(String[] soundMassimiLocali) {
        this.soundMassimiLocali = soundMassimiLocali;
    }

    /**
     * @return the soundTempiMinimiLocali
     */
    public String[] getSoundTempiMinimiLocali() {
        return soundTempiMinimiLocali;
    }

    /**
     * @param soundTempiMinimiLocali the soundTempiMinimiLocali to set
     */
    public void setSoundTempiMinimiLocali(String[] soundTempiMinimiLocali) {
        this.soundTempiMinimiLocali = soundTempiMinimiLocali;
    }

    /**
     * @return the soundTempiMassimiLocali
     */
    public String[] getSoundTempiMassimiLocali() {
        return soundTempiMassimiLocali;
    }

    /**
     * @param soundTempiMassimiLocali the soundTempiMassimiLocali to set
     */
    public void setSoundTempiMassimiLocali(String[] soundTempiMassimiLocali) {
        this.soundTempiMassimiLocali = soundTempiMassimiLocali;
    }

    /**
     * @return the soundDeviazioniStandardLocali
     */
    public String[] getSoundDeviazioniStandardLocali() {
        return soundDeviazioniStandardLocali;
    }

    /**
     * @param soundDeviazioniStandardLocali the soundDeviazioniStandardLocali to set
     */
    public void setSoundDeviazioniStandardLocali(String[] soundDeviazioniStandardLocali) {
        this.soundDeviazioniStandardLocali = soundDeviazioniStandardLocali;
    }

    /**
     * @return the pitchMinimiLocali
     */
    public String[] getPitchMinimiLocali() {
        return pitchMinimiLocali;
    }

    /**
     * @param pitchMinimiLocali the pitchMinimiLocali to set
     */
    public void setPitchMinimiLocali(String[] pitchMinimiLocali) {
        this.pitchMinimiLocali = pitchMinimiLocali;
    }

    /**
     * @return the pitchMinimiLogaritmiciLocali
     */
    public String[] getPitchMinimiLogaritmiciLocali() {
        return pitchMinimiLogaritmiciLocali;
    }

    /**
     * @param pitchMinimiLogaritmiciLocali the pitchMinimiLogaritmiciLocali to set
     */
    public void setPitchMinimiLogaritmiciLocali(String[] pitchMinimiLogaritmiciLocali) {
        this.pitchMinimiLogaritmiciLocali = pitchMinimiLogaritmiciLocali;
    }

    /**
     * @return the pitchMedieLocali
     */
    public String[] getPitchMedieLocali() {
        return pitchMedieLocali;
    }

    /**
     * @param pitchMedieLocali the pitchMedieLocali to set
     */
    public void setPitchMedieLocali(String[] pitchMedieLocali) {
        this.pitchMedieLocali = pitchMedieLocali;
    }

    /**
     * @return the pitchMedieLogaritmicheLocali
     */
    public String[] getPitchMedieLogaritmicheLocali() {
        return pitchMedieLogaritmicheLocali;
    }

    /**
     * @param pitchMedieLogaritmicheLocali the pitchMedieLogaritmicheLocali to set
     */
    public void setPitchMedieLogaritmicheLocali(String[] pitchMedieLogaritmicheLocali) {
        this.pitchMedieLogaritmicheLocali = pitchMedieLogaritmicheLocali;
    }

    /**
     * @return the pitchMassimiLocali
     */
    public String[] getPitchMassimiLocali() {
        return pitchMassimiLocali;
    }

    /**
     * @param pitchMassimiLocali the pitchMassimiLocali to set
     */
    public void setPitchMassimiLocali(String[] pitchMassimiLocali) {
        this.pitchMassimiLocali = pitchMassimiLocali;
    }

    /**
     * @return the pitchMassimiLogaritmiciLocali
     */
    public String[] getPitchMassimiLogaritmiciLocali() {
        return pitchMassimiLogaritmiciLocali;
    }

    /**
     * @param pitchMassimiLogaritmiciLocali the pitchMassimiLogaritmiciLocali to set
     */
    public void setPitchMassimiLogaritmiciLocali(String[] pitchMassimiLogaritmiciLocali) {
        this.pitchMassimiLogaritmiciLocali = pitchMassimiLogaritmiciLocali;
    }

    /**
     * @return the pitchTempiMinimiLocali
     */
    public String[] getPitchTempiMinimiLocali() {
        return pitchTempiMinimiLocali;
    }

    /**
     * @param pitchTempiMinimiLocali the pitchTempiMinimiLocali to set
     */
    public void setPitchTempiMinimiLocali(String[] pitchTempiMinimiLocali) {
        this.pitchTempiMinimiLocali = pitchTempiMinimiLocali;
    }

    /**
     * @return the pitchTempiMassimiLocali
     */
    public String[] getPitchTempiMassimiLocali() {
        return pitchTempiMassimiLocali;
    }

    /**
     * @param pitchTempiMassimiLocali the pitchTempiMassimiLocali to set
     */
    public void setPitchTempiMassimiLocali(String[] pitchTempiMassimiLocali) {
        this.pitchTempiMassimiLocali = pitchTempiMassimiLocali;
    }

    /**
     * @return the pitchDeviazioniStandardLocali
     */
    public String[] getPitchDeviazioniStandardLocali() {
        return pitchDeviazioniStandardLocali;
    }

    /**
     * @param pitchDeviazioniStandardLocali the pitchDeviazioniStandardLocali to set
     */
    public void setPitchDeviazioniStandardLocali(String[] pitchDeviazioniStandardLocali) {
        this.pitchDeviazioniStandardLocali = pitchDeviazioniStandardLocali;
    }

    /**
     * @return the intensityMedieLocali
     */
    public String[] getIntensityMedieLocali() {
        return intensityMedieLocali;
    }

    /**
     * @param intensityMedieLocali the intensityMedieLocali to set
     */
    public void setIntensityMedieLocali(String[] intensityMedieLocali) {
        this.intensityMedieLocali = intensityMedieLocali;
    }

    /**
     * @return the intensityMinimiLocali
     */
    public String[] getIntensityMinimiLocali() {
        return intensityMinimiLocali;
    }

    /**
     * @param intensityMinimiLocali the intensityMinimiLocali to set
     */
    public void setIntensityMinimiLocali(String[] intensityMinimiLocali) {
        this.intensityMinimiLocali = intensityMinimiLocali;
    }

    /**
     * @return the intensityMassimiLocali
     */
    public String[] getIntensityMassimiLocali() {
        return intensityMassimiLocali;
    }

    /**
     * @param intensityMassimiLocali the intensityMassimiLocali to set
     */
    public void setIntensityMassimiLocali(String[] intensityMassimiLocali) {
        this.intensityMassimiLocali = intensityMassimiLocali;
    }

    /**
     * @return the intensityTempiMinimiLocali
     */
    public String[] getIntensityTempiMinimiLocali() {
        return intensityTempiMinimiLocali;
    }

    /**
     * @param intensityTempiMinimiLocali the intensityTempiMinimiLocali to set
     */
    public void setIntensityTempiMinimiLocali(String[] intensityTempiMinimiLocali) {
        this.intensityTempiMinimiLocali = intensityTempiMinimiLocali;
    }

    /**
     * @return the intensityTempiMassimiLocali
     */
    public String[] getIntensityTempiMassimiLocali() {
        return intensityTempiMassimiLocali;
    }

    /**
     * @param intensityTempiMassimiLocali the intensityTempiMassimiLocali to set
     */
    public void setIntensityTempiMassimiLocali(String[] intensityTempiMassimiLocali) {
        this.intensityTempiMassimiLocali = intensityTempiMassimiLocali;
    }

    /**
     * @return the intensityDeviazioniStandardLocali
     */
    public String[] getIntensityDeviazioniStandardLocali() {
        return intensityDeviazioniStandardLocali;
    }

    /**
     * @param intensityDeviazioniStandardLocali the intensityDeviazioniStandardLocali to set
     */
    public void setIntensityDeviazioniStandardLocali(String[] intensityDeviazioniStandardLocali) {
        this.intensityDeviazioniStandardLocali = intensityDeviazioniStandardLocali;
    }

    /**
     * @return the harmonicityMinimiLocali
     */
    public String[] getHarmonicityMinimiLocali() {
        return harmonicityMinimiLocali;
    }

    /**
     * @param harmonicityMinimiLocali the harmonicityMinimiLocali to set
     */
    public void setHarmonicityMinimiLocali(String[] harmonicityMinimiLocali) {
        this.harmonicityMinimiLocali = harmonicityMinimiLocali;
    }

    /**
     * @return the harmonicityMedieLocali
     */
    public String[] getHarmonicityMedieLocali() {
        return harmonicityMedieLocali;
    }

    /**
     * @param harmonicityMedieLocali the harmonicityMedieLocali to set
     */
    public void setHarmonicityMedieLocali(String[] harmonicityMedieLocali) {
        this.harmonicityMedieLocali = harmonicityMedieLocali;
    }

    /**
     * @return the harmonicityMassimiLocali
     */
    public String[] getHarmonicityMassimiLocali() {
        return harmonicityMassimiLocali;
    }

    /**
     * @param harmonicityMassimiLocali the harmonicityMassimiLocali to set
     */
    public void setHarmonicityMassimiLocali(String[] harmonicityMassimiLocali) {
        this.harmonicityMassimiLocali = harmonicityMassimiLocali;
    }

    /**
     * @return the harmonicityDeviazioniStandardLocali
     */
    public String[] getHarmonicityDeviazioniStandardLocali() {
        return harmonicityDeviazioniStandardLocali;
    }

    /**
     * @param harmonicityDeviazioniStandardLocali the harmonicityDeviazioniStandardLocali to set
     */
    public void setHarmonicityDeviazioniStandardLocali(String[] harmonicityDeviazioniStandardLocali) {
        this.harmonicityDeviazioniStandardLocali = harmonicityDeviazioniStandardLocali;
    }

    /**
     * @return the harmonicityTempiMinimiLocali
     */
    public String[] getHarmonicityTempiMinimiLocali() {
        return harmonicityTempiMinimiLocali;
    }

    /**
     * @param harmonicityTempiMinimiLocali the harmonicityTempiMinimiLocali to set
     */
    public void setHarmonicityTempiMinimiLocali(String[] harmonicityTempiMinimiLocali) {
        this.harmonicityTempiMinimiLocali = harmonicityTempiMinimiLocali;
    }

    /**
     * @return the harmonicityTempiMassimiLocali
     */
    public String[] getHarmonicityTempiMassimiLocali() {
        return harmonicityTempiMassimiLocali;
    }

    /**
     * @param harmonicityTempiMassimiLocali the harmonicityTempiMassimiLocali to set
     */
    public void setHarmonicityTempiMassimiLocali(String[] harmonicityTempiMassimiLocali) {
        this.harmonicityTempiMassimiLocali = harmonicityTempiMassimiLocali;
    }
    
}
