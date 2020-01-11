/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author MR
 */
class Keylistner implements KeyListener {

    public Keylistner() {
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed( KeyEvent ke )
    {
        System.out.println( "Key pressed: " + ke.getKeyText( ke.getKeyCode()));
        //if(event.getKeyCode()==10)
            //JOptionPane.showMessageDialog(null,"Enter Key Click");
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
