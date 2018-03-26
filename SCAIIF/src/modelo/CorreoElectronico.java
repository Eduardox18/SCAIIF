/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author andres
 */
public class CorreoElectronico {
    
    private static final String EMISOR = null;
    private static final String CONTRASENA = null;
    
    public static boolean enviarCorreo(String[] destinatarios, String asunto, String motivo) {
        boolean resultado = true;

        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            
            Session session = Session.getInstance(properties,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(EMISOR, CONTRASENA);
                        }
                    });
            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMISOR));
            
            for (String correo: destinatarios){
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            }
            message.setSubject(asunto);
            message.setText(motivo);
            
            Transport.send(message);
        } catch (Exception ex) {
            resultado = false;
        } 
        
        return resultado;
    }
}
