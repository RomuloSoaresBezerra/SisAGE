/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.agendaeletronica.interfaces;

import com.ifpb.agendaeletronica.entidades.Agenda;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Familia
 */
public interface AgendaDao {

    public List<Agenda> listarAgendas() throws IOException, SQLException, ClassNotFoundException;

    public boolean createAgenda(Agenda a) throws IOException, SQLException, ClassNotFoundException;

    public Agenda readAgenda(String nomeAgenda) throws IOException, SQLException, ClassNotFoundException;

    public boolean updateAgenda(Agenda a) throws IOException, SQLException, ClassNotFoundException;

    public boolean deleteAgenda(String nomeAgenda) throws IOException, SQLException, ClassNotFoundException;
    
    public List<String> retornaNomeAgendas() throws IOException, SQLException, ClassNotFoundException;
    
    public boolean AualizarNomeAgenda(Agenda a, String novoNome) throws IOException, SQLException, ClassNotFoundException;
    public List<Agenda> listarAgendasUsuario() throws SQLException, IOException, ClassNotFoundException;
}
