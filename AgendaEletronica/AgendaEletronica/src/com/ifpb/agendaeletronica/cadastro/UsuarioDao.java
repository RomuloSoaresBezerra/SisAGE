package com.ifpb.agendaeletronica.cadastro;

import com.ifpb.agendaeletronica.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 * A Classe <code>UsuarioDao</code> é uma classe que contem o 
 * <b>CRUD<b> para a Classe <code>Usuario</code>.
 * @author Rômulo Soares Bezerra
 * @author Jozimar Soares da Costa
 * @see ArrayList
 * @see Usuario
 */
public final class UsuarioDao {

    private List<Usuario> usuarios;
    
    /**
     * Construtor da classe UsuarioDao
     */
    public UsuarioDao() {
        this.usuarios = new ArrayList<>();
    }
    
    /**
     * Método criado para adicionar um usuáro a lista de usuários, evitando 
     * haver usuário com duplicatas de emais.
     * @param u usuário a ser adicionado a lista de usuários
     * @return true caso o usuário for adionado, false caso negativo
     */
    public boolean createUsuario(Usuario u) {
        for (Usuario user : usuarios) {
            if (user.getEmail().equals(u.getEmail())) {
                return false;
            }
        }
        return usuarios.add(u);
    }
    
    /**
     * Método criado para retornar um usuário dado seu email, caso exista, e null
     * caso negativo.
     * @param email email do usuario
     * @return usuário dado seu email caso exista ou null caso negativo
     */
    public Usuario read(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }
    
    /**
     * Método criado para atualizar as informações de um usuário dado, 
     * caso exista. 
     * @param u representa um usuario
     * @return true caso usuário exista e false caso negativo
     */
    public boolean updateUsuario(Usuario u) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getEmail().equals(u.getEmail())) {
                usuarios.set(i, u);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Método criado para  deletar um dado usuário 
     * @param u usuário a ser deletado
     * @return true se o usuário for deletado e falso caso negativo
     */
    public boolean delete(Usuario u) {
        return usuarios.remove(u);
    }

}
