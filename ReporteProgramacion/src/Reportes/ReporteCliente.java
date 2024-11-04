/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

/**
 *
 * @author IngeMayk
 */
public class ReporteCliente {

    private String nombrePersona;
    private Integer cedula;
    private Integer edad;
    private String direccionCliente;
    private String telefonoCliente;

    public ReporteCliente(String nombrePersona, Integer cedula, Integer edad, String direccionCliente, String telefonoCliente) {
        this.nombrePersona = nombrePersona;
        this.cedula = cedula;
        this.edad = edad;
        this.direccionCliente = direccionCliente;
        this.telefonoCliente = telefonoCliente;
    }

    /**
     * @return the nombrePersona
     */
    public String getNombrePersona() {
        return nombrePersona;
    }

    /**
     * @param nombrePersona the nombrePersona to set
     */
    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    /**
     * @return the cedula
     */
    public Integer getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the edad
     */
    public Integer getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    /**
     * @return the direccionCliente
     */
    public String getDireccionCliente() {
        return direccionCliente;
    }

    /**
     * @param direccionCliente the direccionCliente to set
     */
    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    /**
     * @return the telefonoCliente
     */
    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    /**
     * @param telefonoCliente the telefonoCliente to set
     */
    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

}
