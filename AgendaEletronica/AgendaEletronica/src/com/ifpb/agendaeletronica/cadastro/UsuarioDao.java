package com.ifpb.agendaeletronica.cadastro;

import com.ifpb.agendaeletronica.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
/**
 * Essa classe contém métodos para a realização 
 * do CRUD da classe Usuario
 * @author Jozimar Soares
 * @author Rômulo Bezerra
 * @see ArrayList
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
 * Adiciona um Usuario no ArrayList
 * @param u um Usuario será adicionado na lista
 * @return a confirmação da inclusão
 * @see UsuarioDao
 */
    public boolean create(Usuario u) {
        for (Usuario user : usuarios) {
            if (user.getEmail().equals(u.getEmail())) {
                return false;
            }
        }
        return usuarios.add(u);
    }
/**
 * Busca um Usuario pelo atributo email
 * @param email representa o E-mail do Usuario
 * @return o usuario se encontrado ou null caso não encontre
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
 * Atualiza o Usuario através do seu E-mail cadastrado
 * @param u representa o Usuario
 * @return a confirmação de possibilidade de atualização
 */
    public boolean update(Usuario u) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getEmail().equals(u.getEmail())) {
                usuarios.set(i, u);
                return true;
            }
        }
        return false;
    }
/**
 * Deleta um Usuario da lista 
 * @param u representa um Usuario
 * @return a remoção do Usuario
 */
    public boolean delete(Usuario u) {
        return usuarios.remove(u);
    }

}
