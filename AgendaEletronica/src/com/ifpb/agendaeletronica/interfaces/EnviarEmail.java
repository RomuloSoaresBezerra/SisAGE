package com.ifpb.agendaeletronica.interfaces;

import javax.swing.JOptionPane;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public interface EnviarEmail {

    public default boolean sendEmail(String emailUsuario, String senha, String nome) throws EmailException {

        SimpleEmail email = new SimpleEmail();

        email.setHostName("smtp.gmail.com");

        email.setSmtpPort(465);

        email.addTo(emailUsuario, nome);

        email.setFrom("jzymarsoares@gmail", "Jozimar");

        email.setSubject("Confirmação do Cadastro no Sistema");

        email.setMsg("Seu Cadastro Foi Efetuado Com Sucesso");

        email.setSSL(true);
        email.setAuthentication(emailUsuario, senha);
        JOptionPane.showMessageDialog(null, "Enviando E-mail...", "Enviando", JOptionPane.INFORMATION_MESSAGE);
        email.send();
        JOptionPane.showMessageDialog(null, "E-mail Enviado!", "E-mail", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
}
