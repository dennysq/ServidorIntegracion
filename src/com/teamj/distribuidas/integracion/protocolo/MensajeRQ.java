/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.integracion.protocolo;

import com.teamj.distribuidas.integracion.protocolo.Cabecera;
import com.teamj.distribuidas.integracion.protocolo.Mensaje;
import com.teamj.distribuidas.integracion.protocolo.consulta.CuentaRQ;
import com.teamj.distribuidas.integracion.protocolo.seguridad.AutenticacionRQ;
import com.teamj.distribuidas.integracion.protocolo.transaccion.DepositoRQ;
import com.teamj.distribuidas.integracion.protocolo.transaccion.RetiroRQ;



/**
 *
 * @author LuisAlberto
 */
public class MensajeRQ extends Mensaje {
	public MensajeRQ() {
	}

	public MensajeRQ(String originador, String idMensaje) {
		this.cabecera = new Cabecera(Mensaje.TIPO_MENSAJE_REQUEST, originador, idMensaje);
	}

	@Override
	public boolean build(String input) {
		boolean result = true;
		if (validate(input)) {
			this.cabecera = new Cabecera();
			// Prueba repositorio GIT
			if (this.cabecera.build(input.substring(0, Cabecera.HEADER_LENGTH))) {
				// se obtiene el resto del mensaje que seria el cuerpo
				String cuerpo = input.substring(Cabecera.HEADER_LENGTH);
				if (this.cabecera.getTipoMensaje().equals(Mensaje.TIPO_MENSAJE_REQUEST)) {
					switch (this.cabecera.getIdMensaje()) {
					case ID_MENSAJE_AUTENTICACION:
						AutenticacionRQ autenticacionRQ = new AutenticacionRQ();
                                                autenticacionRQ.build(cuerpo);
						this.cuerpo = autenticacionRQ;
						break;
					case ID_MENSAJE_CONSULTACUENTA:
						CuentaRQ cuentaRQ = new CuentaRQ();
						cuentaRQ.build(cuerpo);
						this.cuerpo = cuentaRQ;
						break;
					case ID_MENSAJE_DEPOSITO:
						DepositoRQ depositoRQ = new DepositoRQ();
						depositoRQ.build(cuerpo);
						this.cuerpo = depositoRQ;
						break;
					case ID_MENSAJE_RETIRO:
						RetiroRQ retiroRQ = new RetiroRQ();
						retiroRQ.build(cuerpo);
						this.cuerpo = retiroRQ;
						break;
					
					default:
						result = false;
					}
				} else {
					result = false;
				}

			} else {
				result = false;
			}
		} else {
			result = false;
		}
		return result;

	}

}

