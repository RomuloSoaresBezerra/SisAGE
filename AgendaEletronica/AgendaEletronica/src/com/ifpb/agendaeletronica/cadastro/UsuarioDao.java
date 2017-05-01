package com.ifpb.agendaeletronica.cadastro;

import com.ifpb.agendaeletronica.entidades.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que contem o CRUD para a Classe <code>Usuario</code>.
 *
 * @author Rômulo Soares Bezerra
 * @author Jozimar Soares da Costa
 * @see ArrayList
 * @see Usuario
 */
public interface UsuarioDao {

    public Usuario readUsuario(String email) throws ClassNotFoundException, SQLException, IOException;

    public List<Usuario> listUsuario() throws ClassNotFoundException, SQLException, IOException;

    public boolean createUsuario(Usuario usuario) throws ClassNotFoundException, SQLException, IOException;

    public boolean deleteUsuario(String email) throws ClassNotFoundException, SQLException, IOException;

    public boolean updateUsuario(Usuario usuario) throws ClassNotFoundException, SQLException, IOException;

}
