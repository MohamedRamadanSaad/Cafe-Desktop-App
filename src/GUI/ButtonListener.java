/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DBTables.Tables;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author MR
 */

 public   class ButtonListener implements ActionListener {
  
     Tables tb ;
     OpenOrder od;

     public ButtonListener() throws Exception{
     tb = new Tables();
     }
  public void actionPerformed(ActionEvent e)  {
      // 0 red    ,,,,   1 green
      JPanel pan =(JPanel)((JButton)e.getSource()).getParent().getParent().getParent();
                 SplitPaneEXP frame = (SplitPaneEXP) pan.getParent().getParent().getParent().getParent().getParent().getParent().getParent();
     if (e.getActionCommand().equalsIgnoreCase("1")) {
         try {
             if (tb.isTableFree(1)==true){
                 od = new OpenOrder(1,1,frame);
                 
                 
                 
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(1,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
                   
           }  else if (e.getActionCommand().equalsIgnoreCase("2")){
              try {
             if (tb.isTableFree(2)==true){
                 od = new OpenOrder(2,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(2,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
               
           }  else if (e.getActionCommand().equalsIgnoreCase("3")){
               try {
             if (tb.isTableFree(3)==true){
                 od = new OpenOrder(3,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(3,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }else if (e.getActionCommand().equalsIgnoreCase("4")){
               try {
             if (tb.isTableFree(4)==true){
                 od = new OpenOrder(4,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(4,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }else if (e.getActionCommand().equalsIgnoreCase("5")){
               try {
             if (tb.isTableFree(5)==true){
                 od = new OpenOrder(5,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(5,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }else if (e.getActionCommand().equalsIgnoreCase("6")){
               try {
             if (tb.isTableFree(6)==true){
                 od = new OpenOrder(6,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(6,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }else if (e.getActionCommand().equalsIgnoreCase("7")){
               try {
             if (tb.isTableFree(7)==true){
                 od = new OpenOrder(7,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(7,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }else if (e.getActionCommand().equalsIgnoreCase("8")){
               try {
             if (tb.isTableFree(8)==true){
                 od = new OpenOrder(8,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(8,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }else if (e.getActionCommand().equalsIgnoreCase("9")){
               try {
             if (tb.isTableFree(9)==true){
                 od = new OpenOrder(9,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(9,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }else if (e.getActionCommand().equalsIgnoreCase("10")){
               try {
             if (tb.isTableFree(10)==true){
                 od = new OpenOrder(10,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(10,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }else if (e.getActionCommand().equalsIgnoreCase("11")){
               try {
             if (tb.isTableFree(111)==true){
                 od = new OpenOrder(11,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(11,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }else if (e.getActionCommand().equalsIgnoreCase("12")){
               try {
             if (tb.isTableFree(12)==true){
                 od = new OpenOrder(12,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(12,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }else if (e.getActionCommand().equalsIgnoreCase("13")){
               try {
             if (tb.isTableFree(13)==true){
                 od = new OpenOrder(13,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(13,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }else if (e.getActionCommand().equalsIgnoreCase("14")){
               try {
             if (tb.isTableFree(14)==true){
                 od = new OpenOrder(14,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(14,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }else if (e.getActionCommand().equalsIgnoreCase("15")){
               try {
             if (tb.isTableFree(15)==true){
                 od = new OpenOrder(15,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(15,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }else if (e.getActionCommand().equalsIgnoreCase("16")){
               try {
             if (tb.isTableFree(16)==true){
                 od = new OpenOrder(16,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(16,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }else if (e.getActionCommand().equalsIgnoreCase("17")){
               try {
             if (tb.isTableFree(17)==true){
                 od = new OpenOrder(17,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(17,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }else if (e.getActionCommand().equalsIgnoreCase("18")){
               try {
             if (tb.isTableFree(18)==true){
                 od = new OpenOrder(18,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(18,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }else if (e.getActionCommand().equalsIgnoreCase("19")){
               try {
             if (tb.isTableFree(19)==true){
                 od = new OpenOrder(19,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(19,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }else if (e.getActionCommand().equalsIgnoreCase("20")){
               try {
             if (tb.isTableFree(20)==true){
                 od = new OpenOrder(20,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(20,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }else if (e.getActionCommand().equalsIgnoreCase("21")){
               try {
             if (tb.isTableFree(21)==true){
                 od = new OpenOrder(21,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(21,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }else if (e.getActionCommand().equalsIgnoreCase("22")){
               try {
             if (tb.isTableFree(22)==true){
                 od = new OpenOrder(22,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(22,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }else if (e.getActionCommand().equalsIgnoreCase("23")){
               try {
             if (tb.isTableFree(23)==true){
                 od = new OpenOrder(23,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(23,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }else if (e.getActionCommand().equalsIgnoreCase("24")){
               try {
             if (tb.isTableFree(24)==true){
                 od = new OpenOrder(24,1,frame);
                 od.setVisible(true);
                  //refreshTablebuttons();
             }else{
                 od = new OpenOrder(24,0,frame); 
                 od.setVisible(true);
                //  refreshTablebuttons();
             }
         } catch (Exception ex) {
             Logger.getLogger(SplitPaneEXP.class.getName()).log(Level.SEVERE, null, ex);
         }
           }

           
           
   
  }
    }
