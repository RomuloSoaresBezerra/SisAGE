package com.ifpb.agendaeletronica.controle;

import com.ifpb.agendaeletronica.interfaces.UsuarioDao;
import com.ifpb.agendaeletronica.conexaobanco.ConFactory;
import com.ifpb.agendaeletronica.modelo.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que contém o CRUD em banco de dados para a classe<code>Usuario<code>
 *
 * @author Jozimar Soares da Costa
 * @author Rômulo Soares Bezerra
 * @see ArrayList
 * @see Usuario
 */
public class UsuarioDaoBanco implements UsuarioDao {

    /**
     * Construtor da classe UsuarioDaoBanco
     */
    public UsuarioDaoBanco() {
    }

    /**
     * Método criado para retornar um usuário através do email, caso o mesmo
     * exista
     *
     * @param email representa o email do usuário a ser retornado
     * @return o usuário caso o mesmo exista no banco, caso contrário null
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public Usuario readUsuario(String email) throws ClassNotFoundException, SQLException {

        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM usuario WHERE email = ?");

        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Usuario usuario = new Usuario();

            usuario.setNome(rs.getString("nome"));
            usuario.setNascimento(rs.getDate("nascimento").toLocalDate());
            usuario.setSexo(rs.getString("sexo").charAt(0));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));

            con.close();
            return usuario;
        } else {
            con.close();
            return null;
        }

    }

    /**
     * Método criado para listar os usuarios presentes no banco de dados
     *
     * @return uma lista contendo todos os usuários cadastrados no banco
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public List<Usuario> listUsuario() throws ClassNotFoundException, SQLException {
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM usuario");

        ResultSet rs = stmt.executeQuery();
        List<Usuario> usuarios = new ArrayList<>();

        while (rs.next()) {

            Usuario usuario = new Usuario();
            usuario.setNome(rs.getString("nome"));
            usuario.setNascimento(rs.getDate("nascimento").toLocalDate());
            usuario.setSexo(rs.getString("sexo").charAt(0));
            usuario.setEmail(rs.getString("email"));
            usuario.setSenha(rs.getString("senha"));
            usuarios.add(usuario);
        }

        con.close();
        return usuarios;

    }

    /**
     * Método criado para adicionar um usuário no banco de dados
     *
     * @param u representa o usuário a ser adicionado
     * @return true caso o usuário seja adicionado, caso contrário false
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public boolean createUsuario(Usuario u) throws ClassNotFoundException, SQLException {
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO usuario (nome, nascimento, sexo,"
                + " email, senha) VALUES (?,?,?,?,?)");

        stmt.setString(1, u.getNome());
        stmt.setDate(2, Date.valueOf(u.getNascimento()));
        stmt.setString(3, "" + u.getSexo());
        stmt.setString(4, u.getEmail());
        stmt.setString(5, u.getSenha());

        boolean retorno = stmt.executeUpdate() > 0;
        con.close();
        return retorno;

    }

    /**
     * Método criado para deletar um usuário do banco de dados
     *
     * @param email representa o email do usuário a ser deletado
     * @return true caso o usuário seja deletado, caso contrário false
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public boolean deleteUsuario(String email) throws ClassNotFoundException, SQLException {
        Connection con = ConFactory.getConnection();

        PreparedStatement stmt = con.prepareStatement(
                "DELETE FROM usuario WHERE email = ?");
        stmt.setString(1, email);

        boolean retorno = stmt.executeUpdate() > 0;
        con.close();
        return retorno;

    }

    /**
     * Método criado para atualizar um usuário presente no banco
     *
     * @param u represneta o usuário a ser atualizado
     * @return true caso o usuário seja atualizado, caso contrário false
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public boolean updateUsuario(Usuario u) throws ClassNotFoundException, SQLException {
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "UPDATE usuario SET (nome, nascimento, sexo, senha)"
                + " = (?,?,?,?) WHERE email = ?");

        stmt.setString(1, u.getNome());
        stmt.setDate(2, Date.valueOf(u.getNascimento()));
        stmt.setString(3, "" + u.getSexo());
        stmt.setString(4, u.getSenha());
        stmt.setString(5, u.getEmail());

        boolean retorno = stmt.executeUpdate() > 0;
        con.close();
        return retorno;
    }
}
