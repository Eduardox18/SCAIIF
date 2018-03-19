/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javafx.scene.control.Alert; 
import javafx.scene.control.ButtonType; 
import javafx.stage.StageStyle;

/**
 *
 * @author andres
 */
public class Dialogo extends Alert{
     
    public Dialogo(AlertType alertType, String contentText, String titulo, ButtonType... buttons) { 
        super(alertType, contentText, buttons); 
        this.setHeaderText(null); 
        this.setTitle(titulo); 
        this.setResizable(false); 
        this.initStyle(StageStyle.UNDECORATED);
    } 
}
