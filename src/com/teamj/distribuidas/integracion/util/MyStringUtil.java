/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.integracion.util;

/**
 *
 * @author LuisAlberto
 */
public class MyStringUtil {
    
    /**
	 * Retorna un arreglo de String recortado por un varias longitudes
	 * 
	 * @param str
	 *            The string to be splitted
	 * @param lenghts
	 *            An array of integer lengths to Split by
	 * @return The array of splitted Strings
	 */
	public static String[] splitByFixedLengths(String str, int[] lenghts) throws Exception {
		if (str != null && !str.isEmpty() && lenghts != null && lenghts.length != 0) {
			int sum = 0;
			for (int i = 0; i < lenghts.length; i++) {
				sum += lenghts[i];
			}

			if (sum == str.length()) {

				String values[] = new String[lenghts.length];
				int index = 0;
				for (int i = 0; i < lenghts.length; i++) {
					values[i] = str.substring(index, index = index + lenghts[i]);
				}
				return values;
			} else {
				throw new Exception(
						"La suma de las longitudes ingresadas supera a la longitud de la cadena de caracteres");

			}
		}
		return null;
	}

	public static void main(String[] args) {
		try {
			String test[] = splitByFixedLengths("hola1234decem890", new int[] { 4, 4, 5, 3 });
			for (int i = 0; i < test.length; i++) {
				System.out.println(test[i]);

			}
                        System.out.println(""+NetUtil.getLocalIPAddress());
		} catch (Exception e) { //
			e.printStackTrace();
		}

	}
}
