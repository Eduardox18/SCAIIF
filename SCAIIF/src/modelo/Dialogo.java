/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author andres
 */
public class Dialogo extends Alert{
    
    public Dialogo(AlertType alertType, String title, String contentText, ButtonType... buttons) {
        super(alertType, contentText, buttons);
        this.setHeaderText(null);
        this.setTitle(title);
    }
    
}
