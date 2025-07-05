Esta version de la revision del TP esta implementada SIN usar tuplas en la ColaPrioridad, en cambio implementa una nueva interfaz Identificable y la aplica a Usuarios y Transacciones

Identificable extiende Comparable y fuerza a una clase a tener los metodos compareTo de Comparable y el nuevo getId

************

La Cola Prioridad luego extiende esta Interfaz nueva en sus parametros T aceptados

************

La clase BloqueDeTransacciones tiene un atributo interno que es la Cola de Prioridad (nombrado CPT) mientras que todo lo demas lo guarda de manera externa

/// De manera externa se guardan el tamaño original de la cola de prioridad, el monto total, el booleano de creacion, y el CPT

/// La cola de prioridad por su parte esta diseñada para tolerar tecnicamente cualquier clase T que cumpla con Identificable, pero en implementacion solo hay Usuario y Transaccion
/// En la Cola de Prioridad se guardan el tamaño del heap (lo cual desincluye a los eliminados), tanto el array del heap como el de los handles, y ademas el "corrimiento"


************

Falta aun hacer el Actualizar Saldos (A fecha 5/7 a las 12AM), adjunto el mensaje con el que se suben los archivos que explica el problema restante con Saldos

"De momento esta medio bug, y llama al método actualizar... 
...pero no de saldos, sino al actualizar de la colaPrioridad, lo cual si bien **no da errores al compilar** esta extremadamente MAL, pero no quise hacerlo ahora (debería tardar maximo 1 hora)"


************

Falta además actualizar los casos de test nuestros para que funcionen con los nuevos cambios!!

************

Todo lo de esta revision del TP se encuentra en la carpeta "AED-TP2-avena-V2" ignorar el resto (creo que es lo de main, y por las dudas no quise borrarlo, no se como funciona bien)
