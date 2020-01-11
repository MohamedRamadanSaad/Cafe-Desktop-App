/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;



import DBConnect.Connector;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.ResolutionSyntax;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterName;
import javax.print.attribute.standard.PrinterResolution;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlDigesterFactory;
import net.sf.jasperreports.view.JasperViewer;

// import com.mycompany.helper.* ;
// import com.mycompany.dbi.*;

public class ReportGenerator {
    
    public ReportGenerator(int ID ,java.sql.Date DateFrom,java.sql.Date DateTo,int ReportType){
         HashMap hm = null;
  // System.out.println("Usage: ReportGenerator ....");

 
  
  try {
   System.out.println("Start ....");
   // Get jasper report
 /*  String jrxmlFileName = "E:/WorkSpace/Reports/MyReports/MenuList/ManulistReport.jrxml";
   String jasperFileName = "E:/WorkSpace/Reports/MyReports/MenuList/ManulistReport.jasper";
   String pdfFileName = "C:/reports/C1_report.pdf";*/
   // String jrxmlFileName = "C:/reports/print.jrxml";//"E:/WorkSpace/Reports/MyReports/print.jrxml";
 String jrxmlFileName="";
 String jasperFileName="";
 String pdfFileName="";
   if(ReportType == 1){
    jrxmlFileName = "C:/Reports/print.jrxml";
    jasperFileName = "C:/Reports/print.jasper";
    pdfFileName = "/reports/C1_report.pdf";
     hm = new HashMap();
   hm.put("ID", ID);
  } else if (ReportType == 2){
         jrxmlFileName = "C:/Reports/printbar.jrxml";
     jasperFileName = "C:/Reports/printbar.jasper";
    pdfFileName = "/reports/C1_report.pdf";
     hm = new HashMap();
   hm.put("ID", ID);
  } else if (ReportType == 3 ){
       jrxmlFileName = "C:/Reports/safe.jrxml";
     jasperFileName = "C:/Reports/safe.jasper";
    pdfFileName = "/reports/C1_report.pdf";
     hm = new HashMap();
   hm.put("DateFrom",DateFrom);
   hm.put("DateTo", DateTo);
       System.out.println("Parameters: "+hm);
  }
   JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);
  
   
  Connector DB = new Connector();
  Connection conn = DB.open_connection();

   // Create arguments
   // Map params = new HashMap();
  
  

   // Generate jasper print
   JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(jasperFileName, hm, conn);

   // Export pdf file
//   JasperExportManager.exportReportToPdfFile(jprint, pdfFileName);
   JasperViewer.viewReport(jprint,false);
  if(ReportType == 1 || ReportType == 2 ){
   PrintReportToPrinter(jprint);
   
   System.out.println("Done exporting and printing reports to pdf");
  }
  } catch (Exception e) {
   System.out.print("Exceptiion" + e);
  }
    }
    
    

 private  static void PrintReportToPrinter(JasperPrint jp) throws JRException {
    // TODO Auto-generated method stub
     try{
    PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
   // printRequestAttributeSet.add(MediaSizeName.ISO_A4); //setting page size
    printRequestAttributeSet.add(new Copies(1));
    //printRequestAttributeSet.add(MediaSizeName.ISO_A7);

   PrinterName printerName = new PrinterName("80mm Series Printer", null); //gets printer 
    //PrinterName printerName = new PrinterName("HP LaserJet 2420 PCL6 Class Driver", null);
    
   //printRequestAttributeSet.add(new PrinterResolution(600, 600, ResolutionSyntax.DPCM));
    PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
    printServiceAttributeSet.add(printerName);

    JRPrintServiceExporter exporter = new JRPrintServiceExporter();

    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printServiceAttributeSet);
    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
    exporter.exportReport();} catch (Exception e){
         JOptionPane.showMessageDialog(null, "Printer Not Found or it is offline");
    }
}
}