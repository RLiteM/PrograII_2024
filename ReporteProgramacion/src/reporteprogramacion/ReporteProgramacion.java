/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reporteprogramacion;

import Reportes.ReporteCliente;
import groovyjarjarcommonscli.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author IngeMayk
 */
public class ReporteProgramacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

    }

    public static List ReporteCliente() throws ParseException {
        List<ReporteCliente> list = new ArrayList<ReporteCliente>();
        for (Iterator it = CRUDs.CRUDCliente.universo().iterator(); it.hasNext();) {
            Object[] item = (Object[]) it.next();
            list.add(new ReporteCliente((String) item[0], (Integer) item[1], (Integer) item[2], (String) item[3], (String) item[4]));
            factory comp = new factory();
            comp.setReportecliente((ArrayList<ReporteCliente>) list);
        }
        return list;
    }
}
