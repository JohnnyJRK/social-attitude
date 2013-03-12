call caricaFileAudio
call inizializzaFileXML
call scriviNomeFile
call inizializzaSound
  call scriviMedia
  call scriviMedieLocali
  call scriviMinimo
  call scriviMinimiLocali
  call scriviMassimo
  call scriviMassimiLocali
  call scriviTempoMinimo
  call scriviTempiMinimiLocali
  call scriviTempoMassimo
  call scriviTempiMassimiLocali
  call scriviValoreEfficace
  call scriviDeviazioneStandard
  call scriviDeviazioniStandardLocali
  call scriviEnergia
  call scriviPotenza
  call scriviEnergiaAria
  call scriviPotenzaAria
  call scriviIntensita
call finalizzaSound
call inizializzaPitch
  call scriviMedia
  call scriviMedieLocali
  call scriviMinimo
  call scriviMinimiLocali
  call scriviMassimo
  call scriviMassimiLocali
  call scriviTempoMinimo
  call scriviTempiMinimiLocali
  call scriviTempoMassimo
  call scriviTempiMassimiLocali
  call scriviDeviazioneStandard
  call scriviDeviazioniStandardLocali
  call scriviSlope
  call scriviNumeroCampioni
call finalizzaPitch
call inizializzaIntensity
  call scriviMedia
  call scriviMedieLocali
  call scriviMinimo
  call scriviMinimiLocali
  call scriviMassimo
  call scriviMassimiLocali
  call scriviTempoMinimo
  call scriviTempiMinimiLocali
  call scriviTempoMassimo
  call scriviTempiMassimiLocali
  call scriviDeviazioneStandard
  call scriviDeviazioniStandardLocali
call finalizzaIntensity
call inizializzaSpectrum
  call scriviGravityCentre
  call scriviDeviazioneStandard
  call scriviSkewness
  call scriviKurtosis
  call scriviCentralMoment
call finalizzaSpectrum
call inizializzaHarmonicity
  call scriviMedia
  call scriviMedieLocali
  call scriviMinimo
  call scriviMinimiLocali
  call scriviMassimo
  call scriviMassimiLocali
  call scriviTempoMinimo
  call scriviTempiMinimiLocali
  call scriviTempoMassimo
  call scriviTempiMassimiLocali
  call scriviDeviazioneStandard
  call scriviDeviazioniStandardLocali
call finalizzaHarmonicity
call finalizzaFileXML

procedure caricaFileAudio
  form Inserisci dati
    text fileName
	text fileOutput
  endform
  Read from file... 'fileName$'
  name$ = selected$ ("Sound", 1)
  type$ =  "Sound"
  numSegmentiGlobale = 25
endproc

procedure inizializzaFileXML
  daScrivere$ = "<?xml version=""1.0"" encoding=""ISO-8859-1""?>'newline$'"
  daScrivere$ > 'fileOutput$'
  daScrivere$ = "<analisi>'newline$'"
  daScrivere$ >> 'fileOutput$'
endproc

procedure inizializzaSound
  select Sound 'name$'
  daScrivere$ = "'tab$'<sound>'newline$'"
  daScrivere$ >> 'fileOutput$'
  type$ = "Sound"
endproc

procedure finalizzaSound
  daScrivere$ = "'tab$'</sound>'newline$'"
  daScrivere$ >> 'fileOutput$'
endproc

procedure scriviNomeFile
  daScrivere$ = "'tab$'<nome_file>'fileName$'</nome_file>'newline$'"
  daScrivere$ >> 'fileOutput$'
endproc

procedure scriviTempiMassimiLocali
  if type$ = "Sound"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get time of maximum... cursore (cursore+durataSegmento) Parabolic
      else
      	valTemp'i' = Get time of maximum... cursore (cursore+durataSegmento) Parabolic
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<tempi_massimi_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</tempi_massimi_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  elif type$ = "Pitch"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get time of maximum... cursore (cursore+durataSegmento) Hertz Parabolic
      else
      	valTemp'i' = Get time of maximum... cursore (cursore+durataSegmento) Hertz Parabolic
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<tempi_massimi_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</tempi_massimi_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  elif type$ = "Intensity"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get time of maximum... cursore (cursore+durataSegmento) Parabolic
      else
      	valTemp'i' = Get time of maximum... cursore (cursore+durataSegmento) Parabolic
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<tempi_massimi_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</tempi_massimi_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  elif type$ = "Harmonicity"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get time of maximum... cursore (cursore+durataSegmento) Parabolic
      else
      	valTemp'i' = Get time of maximum... cursore (cursore+durataSegmento) Parabolic
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<tempi_massimi_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</tempi_massimi_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  endif
endproc

procedure scriviTempiMinimiLocali
  if type$ = "Sound"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get time of minimum... cursore (cursore+durataSegmento) Parabolic
      else
      	valTemp'i' = Get time of minimum... cursore (cursore+durataSegmento) Parabolic
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<tempi_minimi_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</tempi_minimi_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  elif type$ = "Pitch"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get time of minimum... cursore (cursore+durataSegmento) Hertz Parabolic
      else
      	valTemp'i' = Get time of minimum... cursore (cursore+durataSegmento) Hertz Parabolic
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<tempi_minimi_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</tempi_minimi_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  elif type$ = "Intensity"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get time of minimum... cursore (cursore+durataSegmento) Parabolic
      else
      	valTemp'i' = Get time of minimum... cursore (cursore+durataSegmento) Parabolic
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<tempi_minimi_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</tempi_minimi_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  elif type$ = "Harmonicity"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get time of minimum... cursore (cursore+durataSegmento) Parabolic
      else
      	valTemp'i' = Get time of minimum... cursore (cursore+durataSegmento) Parabolic
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<tempi_minimi_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</tempi_minimi_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  endif
endproc

procedure scriviDeviazioniStandardLocali
  if type$ = "Sound"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get standard deviation... Average cursore (cursore+durataSegmento)
      else
      	valTemp'i' = Get standard deviation... Average cursore (cursore+durataSegmento)
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<deviazioni_standard_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</deviazioni_standard_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  elif type$ = "Pitch"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get standard deviation... cursore (cursore+durataSegmento) Hertz
      else
      	valTemp'i' = Get standard deviation... cursore (cursore+durataSegmento) Hertz
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<deviazioni_standard_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</deviazioni_standard_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  elif type$ = "Intensity"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get standard deviation... cursore (cursore+durataSegmento)
      else
      	valTemp'i' = Get standard deviation... cursore (cursore+durataSegmento)
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<deviazioni_standard_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</deviazioni_standard_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  elif type$ = "Harmonicity"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get standard deviation... cursore (cursore+durataSegmento)
      else
      	valTemp'i' = Get standard deviation... cursore (cursore+durataSegmento)
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<deviazioni_standard_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</deviazioni_standard_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  endif
endproc

procedure scriviMassimiLocali
  if type$ = "Sound"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get maximum... cursore (cursore+durataSegmento) Parabolic
      else
      	valTemp'i' = Get maximum... cursore (cursore+durataSegmento) Parabolic
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<massimi_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</massimi_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  elif type$ = "Pitch"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get maximum... cursore (cursore+durataSegmento) Hertz Parabolic
      else
      	valTemp'i' = Get maximum... cursore (cursore+durataSegmento) Hertz Parabolic
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<massimi_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</massimi_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################

    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get maximum... cursore (cursore+durataSegmento) "Hertz (logarithmic)" Parabolic
      else
      	valTemp'i' = Get maximum... cursore (cursore+durataSegmento) "Hertz (logarithmic)" Parabolic
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<massimi_logaritmici_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</massimi_logaritmici_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  elif type$ = "Intensity"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get maximum... cursore (cursore+durataSegmento) Parabolic
      else
      	valTemp'i' = Get maximum... cursore (cursore+durataSegmento) Parabolic
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<massimi_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</massimi_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  elif type$ = "Harmonicity"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get maximum... cursore (cursore+durataSegmento) Parabolic
      else
      	valTemp'i' = Get maximum... cursore (cursore+durataSegmento) Parabolic
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<massimi_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</massimi_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  endif
endproc

procedure scriviMinimiLocali
  if type$ = "Sound"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get minimum... cursore (cursore+durataSegmento) Parabolic
      else
      	valTemp'i' = Get minimum... cursore (cursore+durataSegmento) Parabolic
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<minimi_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</minimi_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  elif type$ = "Pitch"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get minimum... cursore (cursore+durataSegmento) Hertz Parabolic
      else
      	valTemp'i' = Get minimum... cursore (cursore+durataSegmento) Hertz Parabolic
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<minimi_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</minimi_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################

    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get minimum... cursore (cursore+durataSegmento) "Hertz (logarithmic)" Parabolic
      else
      	valTemp'i' = Get minimum... cursore (cursore+durataSegmento) "Hertz (logarithmic)" Parabolic
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<minimi_logaritmici_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</minimi_logaritmici_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  elif type$ = "Intensity"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get minimum... cursore (cursore+durataSegmento) Parabolic
      else
      	valTemp'i' = Get minimum... cursore (cursore+durataSegmento) Parabolic
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<minimi_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</minimi_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  elif type$ = "Harmonicity"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get minimum... cursore (cursore+durataSegmento) Parabolic
      else
      	valTemp'i' = Get minimum... cursore (cursore+durataSegmento) Parabolic
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<minimi_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</minimi_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  endif
endproc

procedure scriviMedieLocali
  if type$ = "Sound"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get mean... all cursore (cursore+durataSegmento)
      else
      	valTemp'i' = Get mean... all cursore (cursore+durataTotale)
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<medie_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</medie_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  elif type$ = "Pitch"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get mean... cursore (cursore+durataSegmento) Hertz
      else
      	valTemp'i' = Get mean... cursore (cursore+durataSegmento) Hertz
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<medie_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</medie_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################

    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get mean... cursore (cursore+durataSegmento) Hertz (logarithmic)
      else
      	valTemp'i' = Get mean... cursore (cursore+durataSegmento) Hertz (logarithmic)
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<medie_logaritmiche_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</medie_logaritmiche_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  elif type$ = "Intensity"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get mean... cursore (cursore+durataSegmento) dB
      else
      	valTemp'i' = Get mean... cursore (cursore+durataSegmento) dB
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<medie_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</medie_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  elif type$ = "Harmonicity"
    ###########################################################
    #Inizializza le variabili
    cursore = 0.0
    durataTotale = Get total duration
    durataSegmento = durataTotale / numSegmentiGlobale
    numSegmenti = numSegmentiGlobale
    

    #Riempe un vettore numerico con i valori dei vari segmenti	
    for i from 0 to numSegmenti - 1
      if (cursore + durataSegmento) < durataTotale
      	valTemp'i' = Get mean... cursore (cursore+durataSegmento)
      else
      	valTemp'i' = Get mean... cursore (cursore+durataSegmento)
      endif
      cursore = cursore + durataSegmento
    endfor
    
    #Si prepara a scrivere l'output sul file
    daScrivere$ = "'tab$''tab$'<medie_locali>"
    for i from 0 to numSegmenti - 1
      temp = valTemp'i'
      if (i = (numSegmenti - 1))
        daScrivere$ = "'daScrivere$''temp:5'"
      else
        daScrivere$ = "'daScrivere$''temp:5',"
      endif
    endfor
    daScrivere$ = "'daScrivere$'</medie_locali>'newline$'"
    
    #Scrive sul file
    daScrivere$ >> 'fileOutput$'
    ###########################################################
  endif
endproc

procedure scriviMedia
  if type$ = "Sound"
    daScrivere = Get mean... all 0 0 
    daScrivere$ = "'tab$''tab$'<media>'daScrivere:5' Pascal</media>'newline$'"
    daScrivere$ >> 'fileOutput$'
  elif type$ = "Pitch"
    daScrivere = Get mean... 0 0 Hertz
    daScrivere$ = "'tab$''tab$'<media>'daScrivere:5' Hz</media>'newline$'"
    daScrivere$ >> 'fileOutput$'
    daScrivere = Get mean... 0 0 Hertz (logarithmic)
    daScrivere$ = "'tab$''tab$'<media_logaritmica>'daScrivere:5' Hz</media_logaritmica>'newline$'"
    daScrivere$ >> 'fileOutput$'
  elif type$ = "Intensity"
    daScrivere = Get mean... 0 0 dB
    daScrivere$ = "'tab$''tab$'<media>'daScrivere:5' dB</media>'newline$'"
    daScrivere$ >> 'fileOutput$'
  elif type$ = "Harmonicity"
    daScrivere = Get mean... 0 0
    daScrivere$ = "'tab$''tab$'<media>'daScrivere:5' dB</media>'newline$'"
    daScrivere$ >> 'fileOutput$'
  endif
endproc

procedure scriviMinimo
  if type$ = "Sound"
    daScrivere = Get minimum... 0 0 Parabolic
    daScrivere$ = "'tab$''tab$'<minimo>'daScrivere:5' Pascal</minimo>'newline$'"
    daScrivere$ >> 'fileOutput$'
  elif type$ = "Pitch"
    daScrivere = Get minimum... 0 0 Hertz Parabolic
    daScrivere$ = "'tab$''tab$'<minimo>'daScrivere:5' Hz</minimo>'newline$'"
    daScrivere$ >> 'fileOutput$'
    daScrivere = Get minimum... 0 0 "Hertz (logarithmic)" Parabolic
    daScrivere$ = "'tab$''tab$'<minimo_logaritmico>'daScrivere:5' Hz</minimo_logaritmico>'newline$'"
    daScrivere$ >> 'fileOutput$'
  elif type$ = "Intensity"
    daScrivere = Get minimum... 0 0 Parabolic
    daScrivere$ = "'tab$''tab$'<minimo>'daScrivere:5' dB</minimo>'newline$'"
    daScrivere$ >> 'fileOutput$'
  elif type$ = "Harmonicity"
    daScrivere = Get minimum... 0 0 Parabolic
    daScrivere$ = "'tab$''tab$'<minimo>'daScrivere:5' dB</minimo>'newline$'"
    daScrivere$ >> 'fileOutput$'
  endif
endproc

procedure scriviMassimo
  if type$ = "Sound"
    daScrivere = Get maximum... 0 0 Parabolic
    daScrivere$ = "'tab$''tab$'<massimo>'daScrivere:5' Pascal</massimo>'newline$'"
    daScrivere$ >> 'fileOutput$'
  elif type$ = "Pitch"
    daScrivere = Get maximum... 0 0 Hertz Parabolic
    daScrivere$ = "'tab$''tab$'<massimo>'daScrivere:5' Hz</massimo>'newline$'"
    daScrivere$ >> 'fileOutput$'
    daScrivere = Get maximum... 0 0 "Hertz (logarithmic)" Parabolic
    daScrivere$ = "'tab$''tab$'<massimo_logaritmico>'daScrivere:5' Hz</massimo_logaritmico>'newline$'"
    daScrivere$ >> 'fileOutput$'
  elif type$ = "Intensity"
    daScrivere = Get maximum... 0 0 Parabolic
    daScrivere$ = "'tab$''tab$'<massimo>'daScrivere:5' dB</massimo>'newline$'"
    daScrivere$ >> 'fileOutput$'
  elif type$ = "Harmonicity"
    daScrivere = Get maximum... 0 0 Parabolic
    daScrivere$ = "'tab$''tab$'<massimo>'daScrivere:5' dB</massimo>'newline$'"
    daScrivere$ >> 'fileOutput$'
  endif
endproc

procedure scriviTempoMinimo
  if type$ = "Sound"
    daScrivere = Get time of minimum... 0 0 Parabolic
    daScrivere$ = "'tab$''tab$'<tempo_del_minimo>'daScrivere:5' seconds</tempo_del_minimo>'newline$'"
    daScrivere$ >> 'fileOutput$'
  elif type$ = "Pitch"
    daScrivere = Get time of minimum... 0 0 Hertz Parabolic
    daScrivere$ = "'tab$''tab$'<tempo_del_minimo>'daScrivere:5' seconds</tempo_del_minimo>'newline$'"
    daScrivere$ >> 'fileOutput$'
  elif type$ = "Intensity"
    daScrivere = Get time of minimum... 0 0 Parabolic
    daScrivere$ = "'tab$''tab$'<tempo_del_minimo>'daScrivere:5' seconds</tempo_del_minimo>'newline$'"
    daScrivere$ >> 'fileOutput$'
  elif type$ = "Harmonicity"
    daScrivere = Get time of minimum... 0 0 Parabolic
    daScrivere$ = "'tab$''tab$'<tempo_del_minimo>'daScrivere:5' seconds</tempo_del_minimo>'newline$'"
    daScrivere$ >> 'fileOutput$'
  endif
endproc

procedure scriviTempoMassimo
  if type$ = "Sound"
    daScrivere = Get time of maximum... 0 0 Parabolic
    daScrivere$ = "'tab$''tab$'<tempo_del_massimo>'daScrivere:5' seconds</tempo_del_massimo>'newline$'"
    daScrivere$ >> 'fileOutput$'
  elif type$ = "Pitch"
    daScrivere = Get time of maximum... 0 0 Hertz Parabolic
    daScrivere$ = "'tab$''tab$'<tempo_del_massimo>'daScrivere:5' seconds</tempo_del_massimo>'newline$'"
    daScrivere$ >> 'fileOutput$'
  elif type$ = "Intensity"
    daScrivere = Get time of maximum... 0 0 Parabolic
    daScrivere$ = "'tab$''tab$'<tempo_del_massimo>'daScrivere:5' seconds</tempo_del_massimo>'newline$'"
    daScrivere$ >> 'fileOutput$'
  elif type$ = "Harmonicity"
    daScrivere = Get time of maximum... 0 0 Parabolic
    daScrivere$ = "'tab$''tab$'<tempo_del_massimo>'daScrivere:5' seconds</tempo_del_massimo>'newline$'"
    daScrivere$ >> 'fileOutput$'
  endif
endproc

procedure scriviValoreEfficace
  if type$ = "Sound"
    daScrivere = Get root-mean-square... 0 0
    daScrivere$ = "'tab$''tab$'<valore_efficace>'daScrivere:5' Pascal</valore_efficace>'newline$'"
    daScrivere$ >> 'fileOutput$'
  endif
endproc

procedure scriviDeviazioneStandard
  if type$ = "Sound"
    daScrivere = Get standard deviation... Average 0 0
    daScrivere$ = "'tab$''tab$'<deviazione_standard>'daScrivere:5' Pascal</deviazione_standard>'newline$'"
    daScrivere$ >> 'fileOutput$'
  elif type$ = "Pitch"
    daScrivere = Get standard deviation... 0 0 Hertz
    daScrivere$ = "'tab$''tab$'<deviazione_standard>'daScrivere:5' Hz</deviazione_standard>'newline$'"
    daScrivere$ >> 'fileOutput$'
  elif type$ = "Intensity"
    daScrivere = Get standard deviation... 0 0
    daScrivere$ = "'tab$''tab$'<deviazione_standard>'daScrivere:5' dB</deviazione_standard>'newline$'"
    daScrivere$ >> 'fileOutput$'
  elif type$ = "Spectrum"
    daScrivere = Get standard deviation... 2.0
    daScrivere$ = "'tab$''tab$'<deviazione_standard>'daScrivere:5' Hertz</deviazione_standard>'newline$'"
    daScrivere$ >> 'fileOutput$'
  elif type$ = "Harmonicity"
    daScrivere = Get standard deviation... 0 0
    daScrivere$ = "'tab$''tab$'<deviazione_standard>'daScrivere:5' dB</deviazione_standard>'newline$'"
    daScrivere$ >> 'fileOutput$'
  endif
endproc

procedure scriviEnergia
  if type$ = "Sound"
    daScrivere = Get energy... 0 0
    daScrivere$ = "'tab$''tab$'<energia>'daScrivere:5' Pa2 sec</energia>'newline$'"
    daScrivere$ >> 'fileOutput$'
  endif
endproc

procedure scriviPotenza
  if type$ = "Sound"
    daScrivere = Get power... 0 0
    daScrivere$ = "'tab$''tab$'<potenza>'daScrivere:5' Pa2</potenza>'newline$'"
    daScrivere$ >> 'fileOutput$'
  endif
endproc

procedure scriviEnergiaAria
  if type$ = "Sound"
    daScrivere = Get energy in air
    daScrivere$ = "'tab$''tab$'<energia_in_aria>'daScrivere:5' Joule/m2</energia_in_aria>'newline$'"
    daScrivere$ >> 'fileOutput$'
  endif
endproc

procedure scriviPotenzaAria
  if type$ = "Sound"
    daScrivere = Get power in air
    daScrivere$ = "'tab$''tab$'<potenza_in_aria>'daScrivere:5' Watt/m2</potenza_in_aria>'newline$'"
    daScrivere$ >> 'fileOutput$'
  endif
endproc

procedure scriviIntensita
  if type$ = "Sound"
    daScrivere = Get intensity (dB)
    daScrivere$ = "'tab$''tab$'<intensita_db>'daScrivere:5' dB</intensita_db>'newline$'"
    daScrivere$ >> 'fileOutput$'
  endif
endproc

procedure scriviNumeroCampioni
  if type$ = "Pitch"
    daScrivere = Count voiced frames
    daScrivere$ = "'tab$''tab$'<numero_campioni>'daScrivere' voiced frames</numero_campioni>'newline$'"
    daScrivere$ >> 'fileOutput$'
  endif
endproc

procedure scriviSlope
  if type$ = "Pitch"
    daScrivere = Get mean absolute slope... Hertz
    daScrivere$ = "'tab$''tab$'<slope>'daScrivere:5' Hertz/s</slope>'newline$'"
    daScrivere$ >> 'fileOutput$'
  endif
endproc

procedure scriviGravityCentre
  if type$ = "Spectrum"
    daScrivere = Get centre of gravity... 2.0
    daScrivere$ = "'tab$''tab$'<gravity_centre>'daScrivere:5' Hertz</gravity_centre>'newline$'"
    daScrivere$ >> 'fileOutput$'
  endif
endproc

procedure scriviSkewness
  if type$ = "Spectrum"
    daScrivere = Get skewness... 2.0
    daScrivere$ = "'tab$''tab$'<skewness>'daScrivere:5' skewness</skewness>'newline$'"
    daScrivere$ >> 'fileOutput$'
  endif
endproc

procedure scriviKurtosis
  if type$ = "Spectrum"
    daScrivere = Get kurtosis... 2.0
    daScrivere$ = "'tab$''tab$'<kurtosis>'daScrivere:5' kurtosis</kurtosis>'newline$'"
    daScrivere$ >> 'fileOutput$'
  endif
endproc

procedure scriviCentralMoment
  if type$ = "Spectrum"
    daScrivere = Get central moment... 3.0 2.0
    daScrivere$ = "'tab$''tab$'<central_moment>'daScrivere:5' Hertz</central_moment>'newline$'"
    daScrivere$ >> 'fileOutput$'
  endif
endproc

procedure inizializzaPitch
  select Sound 'name$'
  To Pitch (ac)... 0 75 15 true 0.03 0.45 0.01 0.35 0.14 600
  type$ =  "Pitch"
  daScrivere$ = "'tab$'<pitch>'newline$'"
  daScrivere$ >> 'fileOutput$'
endproc

procedure finalizzaPitch
  daScrivere$ = "'tab$'</pitch>'newline$'"
  daScrivere$ >> 'fileOutput$'
endproc

procedure inizializzaIntensity
  select Sound 'name$'
  To Intensity... 100 0 true
  type$ =  "Intensity"
  daScrivere$ = "'tab$'<intensity>'newline$'"
  daScrivere$ >> 'fileOutput$'
endproc

procedure finalizzaIntensity
  daScrivere$ = "'tab$'</intensity>'newline$'"
  daScrivere$ >> 'fileOutput$'
endproc

procedure inizializzaSpectrum
  select Sound 'name$'
  To Spectrum... true
  type$ =  "Spectrum"
  daScrivere$ = "'tab$'<spectrum>'newline$'"
  daScrivere$ >> 'fileOutput$'
endproc

procedure finalizzaSpectrum
  daScrivere$ = "'tab$'</spectrum>'newline$'"
  daScrivere$ >> 'fileOutput$'
endproc

procedure inizializzaHarmonicity
  select Sound 'name$'
  To Harmonicity (ac)... 0.01 75 0.1 4.5
  type$ =  "Harmonicity"
  daScrivere$ = "'tab$'<harmonicity>'newline$'"
  daScrivere$ >> 'fileOutput$'
endproc

procedure finalizzaHarmonicity
  daScrivere$ = "'tab$'</harmonicity>'newline$'"
  daScrivere$ >> 'fileOutput$'
endproc

procedure finalizzaFileXML
  daScrivere$ = "</analisi>"
  daScrivere$ >> 'fileOutput$'
endproc