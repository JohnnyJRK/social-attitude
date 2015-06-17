
# Introduzione al sistema #

Il sistema "Social Attitude" è un estensione di un precedente progetto, riguardante soltanto l'interfacciamento grafico con la rete bayesiana del linguaggio, che estende e migliora simili funzionalità anche per quanto riguarda il calcolo della social attitude ottenuta da audio e gesti. Inoltre, cosa fondamentale, è che il sistema permette il calcolo finale della social attitude generale ottenuta da più sorgenti(es. audio, testi, gesti).
In considerazione dell'esistenza di un progetto che classifica file audio ottenendo i rispettivi valori di arousal e valenza, è stato opportunamente collegato e integrato nel sistema in modo da ricavare tali valori per poi settarli nella rete bayesiana dell'audio.

# Interfaccia e funzionalità #

Il sistema presenta un'interfaccia costituita da 4 tab:

  1. General: consente l'accesso ai tab Language, Speech, Gesture e al calcolo finale della social attitude con visualizzazione del risultato

  1. Language: configurazione dei parametri relativi a alle frasi del dialogo per calcolarne la social attitude

  1. Speech: caricamento file audio, calcolo di arousal e valenza tramite VoiceClassifier, calcolo della social attitude

  1. Gesture: impostazioni dei gesti(mani, braccia, gambe)