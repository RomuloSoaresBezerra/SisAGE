/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.agendaeletronica.interfaces;

import com.ifpb.agendaeletronica.entidades.Compromisso;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author Familia
 */
public interface CompromissoDao {
   
    public Compromisso readCompromisso(LocalDate data, LocalTime hora) throws ClassNotFoundException, SQLException, IOException;

    public List<Compromisso> listarCompromissos() throws ClassNotFoundException, SQLException, IOException;

    public boolean createCompromisso(Compromisso compromisso) throws ClassNotFoundException, SQLException, IOException;

    public boolean deleteCompromisso(Compromisso compromisso) throws ClassNotFoundException, SQLException, IOException;

    public boolean updateCompromisso(Compromisso compromisso) throws ClassNotFoundException, SQLException, IOException;
    
}
