/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.uniba.di.dalu.datasource;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * Si occupa di gestire l'accesso ai file .arff
 */
public class WEKAManager {

    String percorsoFile;

    public WEKAManager(String percorsoFile) {
        this.percorsoFile = percorsoFile;
    }

    /**
     *
     * Si occupa di restituire il dataset
     * @return  un oggetto di tipo Istance che rappresenta il dataset
     */
    public Instances getDataSet() throws FileNotFoundException, Exception {
        File file = new File(percorsoFile);
        if (!file.exists()) {
            throw new Exception();
        }
        DataSource source = new DataSource(percorsoFile);
        Instances data = source.getDataSet();
        if (data == null)
            return null;
        if (data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1);
        }
        
        return data;
    }

    /**
     *
     * Copia l'intestazione del file .arff del training set.
     * @return  un oggetto di tipo stringa che rappresenta l'intestazione del
     * training set
     */
    public String getARFFHeader() throws FileNotFoundException, IOException {
        BufferedReader input = new BufferedReader(new FileReader(percorsoFile));
        String line;
        String contenuto = new String();
        do {
            line = input.readLine();
            if (line != null) {
                contenuto = contenuto + System.getProperty("line.separator") + line;
            }
        } while (line != null && !line.equalsIgnoreCase("@DATA"));
        input.close();
        return contenuto;
    }

    /**
     *
     * Crea il file di test .arff da cui weka deve fare la previsione di
     * intenzione
     * @param arffHeader L'intestazione del file di test identica a quella del
     * file di training
     * @param istanza La serie di valori che componogono il file da cui weka
     * estrarrà la previsione di intenzione
     * @param append Se esiste già un file ARFF con lo stesso nome, continua
     * a scriverlo se è impostato a TRUE, lo riazzera se è impostato a FALSE
     */
    public void creaFileARFF(String arffHeader, String[] istanza, boolean append) throws FileNotFoundException, IOException {
        boolean alreadyExists = false;
        if (percorsoFile == null) {
            throw new IllegalArgumentException("Il nome del file non deve essere nullo!");
        }
        File fileDaScrivere = new File(percorsoFile);
        alreadyExists = fileDaScrivere.exists();
        Writer output = new BufferedWriter(new FileWriter(fileDaScrivere, append));
        try {
            if (!alreadyExists)
                output.write(arffHeader + System.getProperty("line.separator"));
            else if (!append)
                output.write(arffHeader + System.getProperty("line.separator"));
            else
                output.write(System.getProperty("line.separator"));
            for (int i = 0; i < istanza.length - 1; i++) {
                output.write(istanza[i] + ",");
            }
            output.write(istanza[istanza.length - 1]);
        } finally {
            output.close();
        }
    }

    /**
     *
     *
     */
    public void deleteARFFFile() {
        File file = new File(percorsoFile);
        if (file.exists()) {
            file.delete();
            percorsoFile = null;
        }
    }
}
