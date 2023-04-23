/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.shift.projetovendas.Model;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author robsonPaiva
 */
public class Utilitarios {
    
    
    //Metodo para limpar campos
    
    public void LimpaTela(JPanel container){
        Component components[] = container.getComponents();
        for(Component component: components){
            if(component instanceof JTextField jTextField){
                jTextField.setText(null);
                jTextField.setBorder(BorderFactory.createLineBorder(Color.black)); 
            }
        }
    }
    
}
