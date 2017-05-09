/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.agendaeletronica.cadastro;

import com.ifpb.agendaeletronica.conexaobanco.ConFactory;
import com.ifpb.agendaeletronica.entidades.Agenda;
import com.ifpb.agendaeletronica.interfacegrafica.TelaInicial;
import com.ifpb.agendaeletronica.interfaces.AgendaDao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Familia
 */
public class AgendaDaoBanco implements AgendaDao{

    public AgendaDaoBanco() {
    }
    
    @Override
    public List<Agenda> listarAgendas() throws SQLException, IOException, ClassNotFoundException {
        Connection con = ConFactory.getConnection();
        
        PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM agenda");

        ResultSet rs = stmt.executeQuery();
        List<Agenda> agendas = new ArrayList<>();

        while (rs.next()) {

            Agenda agenda = new Agenda();
            agenda.setNomeAgenda(rs.getString("nome"));
            agenda.setEmail(rs.getString("email"));
            agendas.add(agenda);
        }

        con.close();
        return agendas;
    }
    
    @Override
    public List<Agenda> listarAgendasUsuario() throws SQLException, IOException, ClassNotFoundException {
        Connection con = ConFactory.getConnection();
        
        PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM agenda");

        ResultSet rs = stmt.executeQuery();
        List<Agenda> agendas = new ArrayList<>();

        while (rs.next()) {

            Agenda agenda = new Agenda();
            if(rs.getString("email").equals(TelaInicial.usuarioLogado.getEmail())){
            agenda.setNomeAgenda(rs.getString("nome"));
            agenda.setEmail(rs.getString("email"));
            agendas.add(agenda);
            }
        }

        con.close();
        return agendas;
    }

    @Override
    public boolean createAgenda(Agenda a) throws SQLException, IOException, ClassNotFoundException {
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO agenda (nome, email) VALUES (?,?)");
        
        List<Agenda> agendas = listarAgendas();
        
        for (Agenda agen : agendas) {
            if (agen.getNomeAgenda().equals(a.getNomeAgenda()) && agen.getEmail().equals(TelaInicial.usuarioLogado.getEmail())) {
                return false;
            }
        }
        
        stmt.setString(1, a.getNomeAgenda());
        stmt.setString(2, a.getEmail());
        
        boolean retorno = stmt.executeUpdate() > 0;
        con.close();
        return retorno;
    }

    @Override
    public Agenda readAgenda(String nomeAgenda) throws SQLException, IOException, ClassNotFoundException {
        
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM agenda WHERE nome = ?");

        stmt.setString(1, nomeAgenda);
        ResultSet rs = stmt.executeQuery();
        
        List<Agenda> agendas = listarAgendas();
        
         for (Agenda agen : agendas) {
            if (rs.next() && agen.getNomeAgenda().equals(nomeAgenda) && agen.getEmail().equals(TelaInicial.usuarioLogado.getEmail())) {
            Agenda agenda = new Agenda();

            agenda.setNomeAgenda(rs.getString("nome"));
            agenda.setEmail(rs.getString("email"));

            con.close();
            return agenda;
           }  
        }

            con.close();
            return null;
        
    }

    @Override
    public boolean updateAgenda(Agenda a) throws SQLException, IOException, ClassNotFoundException {
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "UPDATE agenda SET (email) = (?) WHERE nome = ?");
        
        stmt.setString(1, a.getEmail());
        stmt.setString(2, a.getNomeAgenda());
        
        List<Agenda> agendas = listarAgendas();
         
         for (int i = 0; i < agendas.size(); i++) {
            if (agendas.get(i).getNomeAgenda().equals(a.getNomeAgenda())) {
                agendas.set(i, a);
                
                boolean retorno = stmt.executeUpdate() > 0;
        con.close();
        return retorno;
            }
        }
        return false;         
    }
    
    
    @Override
    public boolean deleteAgenda(String nomeAgenda) throws IOException, SQLException, ClassNotFoundException {
        Connection con = ConFactory.getConnection();

        PreparedStatement stmt = con.prepareStatement(
                "DELETE FROM agenda WHERE nome = ?; DELETE FROM compromisso WHERE nome = ?");
        
        stmt.setString(1, nomeAgenda);
        stmt.setString(2, nomeAgenda);
        
        boolean retorno = stmt.executeUpdate() > 0;
        con.close();
        return retorno;

    }

    @Override
    public List<String> retornaNomeAgendas() throws IOException, SQLException, ClassNotFoundException {
 
        Connection con = ConFactory.getConnection();
        
        PreparedStatement stmt = con.prepareStatement(
                "SELECT nome FROM agenda");
        ResultSet rs = stmt.executeQuery();
        List<Agenda> agendas = listarAgendas();
        List<String> nomeAgendas = new ArrayList<>();
        
        for (Agenda a : agendas) {
              if(rs.next() && a.getEmail().equals(TelaInicial.usuarioLogado.getEmail())){ 
                nomeAgendas.add(a.getNomeAgenda());
 
            Agenda agenda = new Agenda();
            agenda.setNomeAgenda(rs.getString("nome")); 
              }
            }
        

        con.close();
        return nomeAgendas;
  
    }

    @Override
    public boolean AualizarNomeAgenda(Agenda a, String novoNome) throws SQLException, IOException, ClassNotFoundException {
      
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "UPDATE agenda SET (nome) = (?) WHERE nome = ?");
        
        stmt.setString(1, novoNome);
        stmt.setString(2, TelaInicial.usuarioLogado.getEmail());
        
        List<Agenda> agendas = listarAgendas();
        
        for (int i = 0; i < agendas.size(); i++) {
            if (agendas.get(i).getNomeAgenda().equals(a.getNomeAgenda())) {
                agendas.get(i).setNomeAgenda(novoNome);
                
              boolean retorno = stmt.executeUpdate() > 0;
              con.close();
              return retorno;
            }
        }
        return false;

    }
    
}
