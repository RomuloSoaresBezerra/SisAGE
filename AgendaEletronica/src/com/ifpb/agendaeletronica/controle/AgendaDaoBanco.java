package com.ifpb.agendaeletronica.controle;

import com.ifpb.agendaeletronica.conexaobanco.ConFactory;
import com.ifpb.agendaeletronica.modelo.Agenda;
import com.ifpb.agendaeletronica.visao.TelaInicial;
import com.ifpb.agendaeletronica.interfaces.AgendaDao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que contém o CRUD em banco de dados para a classe<code>Agenda<code>
 *
 * @author Jozimar Soares da Costa
 * @author Rômulo Soares Bezerra
 * @see ArrayList
 * @see Agenda
 */
public class AgendaDaoBanco implements AgendaDao {

    /**
     * Construtor da classe AgendaDaoBanco
     */
    public AgendaDaoBanco() {
    }

    /**
     * Método criado para listar as agendas do banco de dados
     *
     * @return as agendas que estão no banco
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
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

    /**
     * Método criado para listar as agendas de um determinado usuário do banco
     * de dados
     *
     * @return as agendas de um usuário do banco
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public List<Agenda> listarAgendasUsuario() throws SQLException, IOException, ClassNotFoundException {
        Connection con = ConFactory.getConnection();

        PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM agenda");

        ResultSet rs = stmt.executeQuery();
        List<Agenda> agendas = new ArrayList<>();

        while (rs.next()) {

            Agenda agenda = new Agenda();
            if (rs.getString("email").equals(TelaInicial.usuarioLogado.getEmail())) {
                agenda.setNomeAgenda(rs.getString("nome"));
                agenda.setEmail(rs.getString("email"));
                agendas.add(agenda);
            }
        }

        con.close();
        return agendas;
    }

    /**
     * Método criado para adicionar uma agenda no banco de dados
     *
     * @param a agenda a ser adicionada no banco
     * @return true caso a agenda seja adicionada e caso contrário false
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
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

    /**
     * Método criado para retornar uma agenda do banco de dados através do nome,
     * caso a mesma exista
     *
     * @param nomeAgenda nome da agenda a ser retornada
     * @return agenda caso a mesma exista no banco, caso contrário null
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
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

    /**
     * Método criado para atualizar uma agenda no banco de dados
     *
     * @param a agenda a ser atualizada no banco
     * @return true caso a agenda seja atualizada, caso contrário false
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
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

    /**
     * Método criado para deletar uma agenda do banco de dados
     *
     * @param nomeAgenda nome da agenda a ser deletada
     * @return true caso a agenda seja deletada, caso contrário false
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
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

    /**
     * Método criado para retornar apenas os nomes das agendas do banco de dados
     *
     * @return uma lista contendo o nome das agendas presentes no banco
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public List<String> retornaNomeAgendas() throws IOException, SQLException, ClassNotFoundException {

        Connection con = ConFactory.getConnection();

        PreparedStatement stmt = con.prepareStatement(
                "SELECT nome FROM agenda");
        ResultSet rs = stmt.executeQuery();
        List<Agenda> agendas = listarAgendas();
        List<String> nomeAgendas = new ArrayList<>();

        for (Agenda a : agendas) {
            if (rs.next() && a.getEmail().equals(TelaInicial.usuarioLogado.getEmail())) {
                nomeAgendas.add(a.getNomeAgenda());

                Agenda agenda = new Agenda();
                agenda.setNomeAgenda(rs.getString("nome"));
            }
        }

        con.close();
        return nomeAgendas;

    }

    /**
     * Método criado para atualizar apenas o nome de uma agenda no banco de
     * dados
     *
     * @param a representa a agenda a ser atualizada
     * @param novoNome representa o novo nome que a agenda receberá
     * @return true caso o nome da agenda seja atualizado, caso contrário false
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
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
