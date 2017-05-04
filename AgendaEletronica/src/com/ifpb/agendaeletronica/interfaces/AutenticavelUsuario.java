package com.ifpb.agendaeletronica.interfaces;

/**
 * Interface que valida o login de um usuário. A classe <code>Usuario</code> 
 * implementa essa interface para autenticar o email e a senha de um usuário.
 * @author Rômulo Soares Bezerra
 * @author Jozimar Soares da Costa
 */
public interface AutenticavelUsuario {
    /**Método autenticarUsuario();
     * Método criado para realizar a autentificação de um usuário
     * @param email email do usuário
     * @param senha senha do usuário
     * @return true se autenticado e falso caso negativo
     */
    public boolean autenticarUsuario(String email, String senha);
}
