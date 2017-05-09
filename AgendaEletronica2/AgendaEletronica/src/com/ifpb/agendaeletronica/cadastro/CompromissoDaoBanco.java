/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.agendaeletronica.cadastro;

import com.ifpb.agendaeletronica.conexaobanco.ConFactory;
import com.ifpb.agendaeletronica.entidades.Compromisso;
import com.ifpb.agendaeletronica.interfacegrafica.TelaInicial;
import com.ifpb.agendaeletronica.interfaces.CompromissoDao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Familia
 */
public class CompromissoDaoBanco implements CompromissoDao{

    @Override
    public Compromisso readCompromisso(LocalDate data, LocalTime hora) throws ClassNotFoundException, SQLException, IOException {
       Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM compromisso WHERE data = ? AND hora = ?");

        stmt.setDate(1, (java.sql.Date.valueOf(data)));
        stmt.setTime(2, java.sql.Time.valueOf(hora));
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Compromisso compromisso = new Compromisso();

            compromisso.setData(rs.getDate("data").toLocalDate());
            compromisso.setHora(rs.getTime("hora").toLocalTime());
            compromisso.setDescricao(rs.getString("descricao"));
            compromisso.setLocal(rs.getString("local"));
            compromisso.setEmail(rs.getString("email"));
            compromisso.setNome(rs.getString("nome"));

            con.close();
            return compromisso;
        } else {
            con.close();
            return null;
        }
    }

    @Override
    public  List<Compromisso> listarCompromissos() throws ClassNotFoundException, SQLException, IOException {
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM compromisso");

        ResultSet rs = stmt.executeQuery();
        List<Compromisso> compromissos = new ArrayList<>();

        while (rs.next()) {

            Compromisso compromisso = new Compromisso();
            compromisso.setData(rs.getDate("data").toLocalDate());
            compromisso.setHora(rs.getTime("hora").toLocalTime());
            compromisso.setDescricao(rs.getString("descricao"));
            compromisso.setLocal(rs.getString("local"));
            compromisso.setEmail(rs.getString("email"));
            compromisso.setNome(rs.getString("nome"));
            compromissos.add(compromisso);
        }

        con.close();
        return compromissos;

    }

    @Override
    public boolean createCompromisso(Compromisso c) throws ClassNotFoundException, SQLException, IOException {
       Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO compromisso (data, hora, descricao,"
                + " local, email, nome) VALUES (?,?,?,?,?,?)");
        
        stmt.setDate(1, java.sql.Date.valueOf(c.getData()));
        stmt.setTime(2, java.sql.Time.valueOf(c.getHora()));
        stmt.setString(3, c.getDescricao());
        stmt.setString(4, c.getLocal());
        stmt.setString(5, c.getEmail());
        stmt.setString(6, c.getNome());
        

        boolean retorno = stmt.executeUpdate() > 0;
        con.close();
        return retorno;
    }

    @Override
    public boolean deleteCompromisso(Compromisso c) throws ClassNotFoundException, SQLException, IOException {
    Connection con = ConFactory.getConnection();

        PreparedStatement stmt = con.prepareStatement(
                "DELETE FROM compromisso WHERE data = ? AND hora = ?");
        stmt.setDate(1, java.sql.Date.valueOf(c.getData()));
        stmt.setTime(2, java.sql.Time.valueOf(c.getHora()));

        boolean retorno = stmt.executeUpdate() > 0;
        con.close();
        return retorno;
    }

    @Override
    public boolean updateCompromisso(Compromisso c) throws ClassNotFoundException, SQLException, IOException {
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "UPDATE compromisso SET (descricao, local, email, nome)"
                + " = (?,?,?,?) WHERE data = ? AND hora = ?");

        stmt.setString(1, c.getDescricao());
        stmt.setString(2, c.getLocal());
        stmt.setString(3, c.getEmail());
        stmt.setString(4, c.getNome());
        stmt.setDate(5, java.sql.Date.valueOf(c.getData()));
        stmt.setTime(6, java.sql.Time.valueOf(c.getHora()));
        
        boolean retorno = stmt.executeUpdate() > 0;
        con.close();
        return retorno;
    }
    
    public List<Compromisso> compIntervaloDataAgendas(String nomeAgenda, LocalDate dataInicio, LocalDate dataFim)
            throws IOException, ClassNotFoundException, SQLException {
        
        List<Compromisso> listaCompromissos = listarCompromissos();
        List<Compromisso> comp = new ArrayList<>();

        if (nomeAgenda.equals("Todas")) {
            for (int i = 0; i < listaCompromissos.size(); i++) {
                if ((listaCompromissos.get(i).getEmail().equals(TelaInicial.usuarioLogado.getEmail()))
                        && (listaCompromissos.get(i).getData().compareTo(dataInicio) >= 0)
                        && (listaCompromissos.get(i).getData().compareTo(dataFim) <= 0)) {
                    comp.add(listaCompromissos.get(i));
                }
            }
        }else {
            for (int i = 0; i < listaCompromissos.size(); i++) {
                if ((listaCompromissos.get(i).getEmail().equals(TelaInicial.usuarioLogado.getEmail()))
                        && listaCompromissos.get(i).getNome().equals(nomeAgenda)
                        && (listaCompromissos.get(i).getData().compareTo(dataInicio) >= 0)
                        && (listaCompromissos.get(i).getData().compareTo(dataFim) <= 0)) {
                    comp.add(listaCompromissos.get(i));
                }
            }
    }
    return comp ;
}
    
    }
    

