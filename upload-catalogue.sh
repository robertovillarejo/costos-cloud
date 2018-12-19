curl -X POST --header 'Content-Type: application/json' --header 'Accept: */*' --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTU0NzgzNDc1OH0.xGmVwITIreI-A4GSG2QvG6NCmhRH7eW0R_XW0RL1-hU7U8Arws0HgRHFTIU2SqLk3620RTLptctEpdJHJiyWuw' -d '[ \ 
   { \ 
     "nombre": "EQUIPO DE COMPUTO A", \ 
     "sede": null, \ 
     "mes": null, \ 
     "anio": null, \ 
     "valor": 1090.66 \ 
   }, \ 
   { \ 
     "nombre": "EQUIPO DE COMPUTO B", \ 
     "sede": null, \ 
     "mes": null, \ 
     "anio": null, \ 
     "valor": 1725.66 \ 
   }, \ 
   { \ 
     "nombre": "EQUIPO DE COMPUTO C", \ 
     "sede": null, \ 
     "mes": null, \ 
     "anio": null, \ 
     "valor": 3727.66 \ 
   }, \ 
   { \ 
     "nombre": "EQUIPO DE COMPUTO D", \ 
     "sede": null, \ 
     "mes": null, \ 
     "anio": null, \ 
     "valor": 2977.66 \ 
   }, \ 
   { \ 
     "nombre": "DEPRECIACION", \ 
     "sede": "CDMX", \ 
     "mes": null, \ 
     "anio": 2017, \ 
     "valor": 49.48 \ 
   }, \ 
   { \ 
     "nombre": "CONCUMO ELECTRICO OFICINA", \ 
     "sede": "CDMX", \ 
     "mes": 1, \ 
     "anio": 2017, \ 
     "valor": 14.35 \ 
   }, \ 
   { \ 
     "nombre": "COSTO PROMEDIO ANUAL DEL KWH", \ 
     "sede": "CDMX", \ 
     "mes": null, \ 
     "anio": null, \ 
     "valor": 1.73 \ 
   }, \ 
   { \ 
     "nombre": "HORAS MENSUALES CONSIDERADAS", \ 
     "sede": null, \ 
     "mes": null, \ 
     "anio": null, \ 
     "valor": 730 \ 
   }, \ 
   { \ 
     "nombre": "CONSUMO ELECTRICO POR M2", \ 
     "sede": "CDMX", \ 
     "mes": null, \ 
     "anio": null, \ 
     "valor": 0.4 \ 
   }, \ 
   { \ 
     "nombre": "ESPACIO BOVEDA", \ 
     "sede": "CDMX", \ 
     "mes": null, \ 
     "anio": null, \ 
     "valor": 34 \ 
   }, \ 
   { \ 
     "nombre": "ESPACIO DE OFICINA CD", \ 
     "sede": "CDMX", \ 
     "mes": null, \ 
     "anio": null, \ 
     "valor": 9 \ 
   } \ 
 ]' 'http://localhost:8081/api/factorBasico-list'


 curl -X POST --header 'Content-Type: application/json' --header 'Accept: */*' --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTU0NzgzNDc1OH0.xGmVwITIreI-A4GSG2QvG6NCmhRH7eW0R_XW0RL1-hU7U8Arws0HgRHFTIU2SqLk3620RTLptctEpdJHJiyWuw' -d '[ \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP0949", \ 
     "mes": 1, \ 
     "anio": 2017, \ 
     "porcentaje": 0.2, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP1019", \ 
     "mes": 1, \ 
     "anio": 2017, \ 
     "porcentaje": 0.05, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP0949", \ 
     "mes": 2, \ 
     "anio": 2017, \ 
     "porcentaje": 0.2, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP1019", \ 
     "mes": 2, \ 
     "anio": 2017, \ 
     "porcentaje": 0.05, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP0949", \ 
     "mes": 3, \ 
     "anio": 2017, \ 
     "porcentaje": 0.2, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP1019", \ 
     "mes": 3, \ 
     "anio": 2017, \ 
     "porcentaje": 0.05, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP0949", \ 
     "mes": 4, \ 
     "anio": 2017, \ 
     "porcentaje": 0.2, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP1019", \ 
     "mes": 4, \ 
     "anio": 2017, \ 
     "porcentaje": 0.05, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP0949", \ 
     "mes": 5, \ 
     "anio": 2017, \ 
     "porcentaje": 0.2, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP1019", \ 
     "mes": 5, \ 
     "anio": 2017, \ 
     "porcentaje": 0.05, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP0949", \ 
     "mes": 6, \ 
     "anio": 2017, \ 
     "porcentaje": 0.2, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP1019", \ 
     "mes": 6, \ 
     "anio": 2017, \ 
     "porcentaje": 0.05, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP0949", \ 
     "mes": 7, \ 
     "anio": 2017, \ 
     "porcentaje": 0.2, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP1019", \ 
     "mes": 7, \ 
     "anio": 2017, \ 
     "porcentaje": 0.05, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP0949", \ 
     "mes": 8, \ 
     "anio": 2017, \ 
     "porcentaje": 0.2, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP1019", \ 
     "mes": 8, \ 
     "anio": 2017, \ 
     "porcentaje": 0.05, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP0949", \ 
     "mes": 9, \ 
     "anio": 2017, \ 
     "porcentaje": 0.2, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP1019", \ 
     "mes": 9, \ 
     "anio": 2017, \ 
     "porcentaje": 0.05, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP0949", \ 
     "mes": 10, \ 
     "anio": 2017, \ 
     "porcentaje": 0.2, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP1019", \ 
     "mes": 10, \ 
     "anio": 2017, \ 
     "porcentaje": 0.05, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP0949", \ 
     "mes": 11, \ 
     "anio": 2017, \ 
     "porcentaje": 0.2, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP1019", \ 
     "mes": 11, \ 
     "anio": 2017, \ 
     "porcentaje": 0.05, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP0949", \ 
     "mes": 12, \ 
     "anio": 2017, \ 
     "porcentaje": 0.2, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   }, \ 
   { \ 
     "claveServicio": 70, \ 
     "nombre": "PSP1019", \ 
     "mes": 12, \ 
     "anio": 2017, \ 
     "porcentaje": 0.05, \ 
     "subtipoCosto": "HONORARIOS", \ 
     "observacion": "", \ 
     "fechaObservacion": "12/12/2018" \ 
   } \ 
 ]' 'http://localhost:8081/api/proveedores-list'

 curl -X POST --header 'Content-Type: application/json' --header 'Accept: */*' --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTU0NzgzNDc1OH0.xGmVwITIreI-A4GSG2QvG6NCmhRH7eW0R_XW0RL1-hU7U8Arws0HgRHFTIU2SqLk3620RTLptctEpdJHJiyWuw' -d '[ \ 
   { \ 
     "tipoCosto": "RH", \ 
     "subtipoCosto": "HONORARIOS", \ 
     "partida": 33104, \ 
     "subPartida": null, \ 
     "subSubPartida": null \ 
   }, \ 
   { \ 
     "tipoCosto": "ESPACIO", \ 
     "subtipoCosto": "ESPACIO DE OFICINA", \ 
     "partida": 12610, \ 
     "subPartida": "12610-1", \ 
     "subSubPartida": null \ 
   }, \ 
   { \ 
     "tipoCosto": "ESPACIO", \ 
     "subtipoCosto": "ESPACIO DE CD", \ 
     "partida": 12610, \ 
     "subPartida": "12610-3", \ 
     "subSubPartida": null \ 
   }, \ 
   { \ 
     "tipoCosto": "CONSUMO ELECTRICO", \ 
     "subtipoCosto": "CONSUMO ELECTRICO CD", \ 
     "partida": 31101, \ 
     "subPartida": "31101-3", \ 
     "subSubPartida": null \ 
   }, \ 
   { \ 
     "tipoCosto": "CONSUMO ELECTRICO", \ 
     "subtipoCosto": "CONSUMO ELECTRICO  OFICINA", \ 
     "partida": 31101, \ 
     "subPartida": "31101-1", \ 
     "subSubPartida": null \ 
   }, \ 
   { \ 
     "tipoCosto": "OTROS GASTOS OPERATIVOS", \ 
     "subtipoCosto": "OTROS GASTOS OPERATIVOS", \ 
     "partida": "DTXX1", \ 
     "subPartida": "DTXX1-1", \ 
     "subSubPartida": null \ 
   }, \ 
   { \ 
     "tipoCosto": "CAPACITACION", \ 
     "subtipoCosto": "CAPACITACION", \ 
     "partida": "DTXX2", \ 
     "subPartida": "DTXX2-1", \ 
     "subSubPartida": null \ 
   }, \ 
   { \ 
     "tipoCosto": "SERVICIO ADMINISTRATIVOS DADT OPERACI�N", \ 
     "subtipoCosto": "SERVICIO ADMINISTRATIVOS DADT OPERACI�N", \ 
     "partida": "DT002", \ 
     "subPartida": "DT002-1", \ 
     "subSubPartida": null \ 
   }, \ 
   { \ 
     "tipoCosto": "ARRENDAMIENTO DE BIENES INFORMATICOS", \ 
     "subtipoCosto": "ARRENDAMIENTO DE BIENES INFORMATICOS", \ 
     "partida": 32301, \ 
     "subPartida": null, \ 
     "subSubPartida": null \ 
   } \ 
 ]' 'http://localhost:8081/api/partidaConcepto-list'

 curl -X POST --header 'Content-Type: application/json' --header 'Accept: */*' --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTU0NzgzNDc1OH0.xGmVwITIreI-A4GSG2QvG6NCmhRH7eW0R_XW0RL1-hU7U8Arws0HgRHFTIU2SqLk3620RTLptctEpdJHJiyWuw' -d '[ \ 
   { \ 
     "tipoComputadora": "B", \ 
     "proveedor": "PSP0949" \ 
   }, \ 
   { \ 
     "tipoComputadora": "A", \ 
     "proveedor": "PSP1019" \ 
   }, \ 
   { \ 
     "tipoComputadora": "B", \ 
     "proveedor": "PSP1019" \ 
   } \ 
 ]' 'http://localhost:8081/api/equipoComputo-list'
