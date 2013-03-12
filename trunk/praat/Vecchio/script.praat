call caricaFileAudio
call inizializzaFileXML
call scriviNomeFile
call inizializzaSound
  call scriviMedia
  call scriviMinimo
  call scriviMassimo
  call scriviTempoMinimo
  call scriviTempoMassimo
  call scriviValoreEfficace
  call scriviDeviazioneStandard
  call scriviEnergia
  call scriviPotenza
  call scriviEnergiaAria
  call scriviPotenzaAria
  call scriviIntensita
call finalizzaSound
call inizializzaPitch
  call scriviMedia
  call scriviMinimo
  call scriviMassimo
  call scriviTempoMinimo
  call scriviTempoMassimo
  call scriviDeviazioneStandard
  call scriviSlope
  call scriviNumeroCampioni
call finalizzaPitch
call inizializzaIntensity
  call scriviMedia
  call scriviMinimo
  call scriviMassimo
  call scriviTempoMinimo
  call scriviTempoMassimo
  call scriviDeviazioneStandard
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
  call scriviMinimo
  call scriviMassimo
  call scriviTempoMinimo
  call scriviTempoMassimo
  call scriviDeviazioneStandard
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