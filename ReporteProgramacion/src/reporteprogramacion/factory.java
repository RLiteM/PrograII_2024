/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reporteprogramacion;

import Reportes.ReporteCliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IngeMayk
 */
public class factory {
//cada uno tendra su get y set y reporte 
    private static ArrayList<ReporteCliente> reportecliente = new ArrayList<ReporteCliente>();

    public static List reporteCliente() {
        return getReportecliente();
    }

    /**
     * @return the reportecliente
     */
    public static ArrayList<ReporteCliente> getReportecliente() {
        return reportecliente;
    }

    /**
     * @param aReportecliente the reportecliente to set
     */
    public static void setReportecliente(ArrayList<ReporteCliente> aReportecliente) {
        reportecliente = aReportecliente;
    }
//// es decir cada uno tendra esto, 
}
