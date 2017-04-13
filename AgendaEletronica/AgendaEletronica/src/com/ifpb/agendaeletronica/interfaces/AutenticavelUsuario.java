package com.ifpb.agendaeletronica.interfaces;
/**
 * Interface que representa a autentificação 
 * do Usuario
 * @author Jozimar Soares
 * @author Rômulo Bezerra
 */
public interface AutenticavelUsuario {
/**
 * Realiza a autentificação do Usuario
 * @param email representa o E-mail do Usuario
 * @param senha representa a senha do Usuario
 * @return a confirmação da autentificação do Usuario
 */
    public boolean autenticarUsuario(String email, String senha);
}
