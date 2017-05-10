package com.ifpb.agendaeletronica.interfaces;

import com.ifpb.agendaeletronica.modelo.Compromisso;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CompromissoDao {

    public Compromisso readCompromisso(LocalDate data, LocalTime hora) throws ClassNotFoundException, SQLException, IOException;

    public List<Compromisso> listarCompromissos() throws ClassNotFoundException, SQLException, IOException;

    public boolean createCompromisso(Compromisso compromisso) throws ClassNotFoundException, SQLException, IOException;

    public boolean deleteCompromisso(Compromisso compromisso) throws ClassNotFoundException, SQLException, IOException;

    public boolean updateCompromisso(Compromisso compromisso) throws ClassNotFoundException, SQLException, IOException;

}
