/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author IngeMayk
 */
public class Session {
    private static String nombreUsuario;

    /**
     * @return the nombreUsuario
     */
    public static String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param aNombreUsuario the nombreUsuario to set
     */
    public static void setNombreUsuario(String aNombreUsuario) {
        nombreUsuario = aNombreUsuario;
    }
    
    
}
