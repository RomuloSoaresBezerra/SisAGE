/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.agendaeletronica.cadastro;

import com.ifpb.agendaeletronica.conexaobanco.ConFactory;
import com.ifpb.agendaeletronica.entidades.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Familia
 */
public class UsuarioDaoBanco implements UsuarioDao {

    public UsuarioDaoBanco() {
    }

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
