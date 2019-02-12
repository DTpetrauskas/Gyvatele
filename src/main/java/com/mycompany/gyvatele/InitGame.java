/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gyvatele;


import javax.swing.Timer;

/**
 *
 * @author TAURAS
 */
public class InitGame extends Gameplay {
    
    

    public InitGame() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
    

        timer = new Timer(delay, this);
        timer.start();

    }

}
