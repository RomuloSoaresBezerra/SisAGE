package com.ifpb.agendaeletronica.interfaces;

import com.ifpb.agendaeletronica.modelo.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UsuarioDao {

    public Usuario readUsuario(String email) throws ClassNotFoundException, SQLException, IOException;

    public List<Usuario> listUsuario() throws ClassNotFoundException, SQLException, IOException;

    public boolean createUsuario(Usuario usuario) throws ClassNotFoundException, SQLException, IOException;

    public boolean deleteUsuario(String email) throws ClassNotFoundException, SQLException, IOException;

    public boolean updateUsuario(Usuario usuario) throws ClassNotFoundException, SQLException, IOException;

}
