/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.agendaeletronica.interfaces;

import javax.swing.JOptionPane;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Familia
 */
public interface EnviarEmail {
   public default void sendEmail() throws EmailException {
    
   SimpleEmail email = new SimpleEmail();
   //Utilize o hostname do seu provedor de email
   email.setHostName("smtp.gmail.com");
   //Quando a porta utilizada não é a padrão (gmail = 465)
   email.setSmtpPort(465);
   //Adicione os destinatários
   email.addTo("beta10297@gmail.com", "Jozimar");
   //Configure o seu email do qual enviará
   email.setFrom("beta10297@gmail.com", "Jozimar");
   //Adicione um assunto
   email.setSubject("Confirmação do Cadastro no Sistema");
   //Adicione a mensagem do email
   email.setMsg("Seu Cadastro Foi Efetuado Com Sucesso");
   //Para autenticar no servidor é necessário chamar os dois métodos abaixo
   email.setSSL(true);
   email.setAuthentication("beta10297@gmail.com", "beta12345");
   JOptionPane.showMessageDialog(null, "Enviando E-mail...", "Enviando", JOptionPane.INFORMATION_MESSAGE);
   email.send();
   JOptionPane.showMessageDialog(null, "E-mail Enviado!", "E-mail", JOptionPane.INFORMATION_MESSAGE);
}
}
