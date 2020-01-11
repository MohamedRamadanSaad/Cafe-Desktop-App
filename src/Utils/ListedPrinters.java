/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

/**
 *
 * @author MR
 */
public class ListedPrinters {
    public ListedPrinters(){
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        System.out.println("Number of print services: " + printServices.length);

        for (PrintService printer : printServices)
            System.out.println("Printer: " + printer.getName()); 
    }
    public static void main(String [ ]args){
        ListedPrinters l = new ListedPrinters();
    }
    }

