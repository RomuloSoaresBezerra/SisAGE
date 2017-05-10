package com.ifpb.agendaeletronica.controle;

import com.ifpb.agendaeletronica.conexaobanco.ConFactory;
import com.ifpb.agendaeletronica.modelo.Compromisso;
import com.ifpb.agendaeletronica.visao.TelaInicial;
import com.ifpb.agendaeletronica.interfaces.CompromissoDao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que contém o CRUD em banco de dados´para a classe<code>Compromisso<code>
 *
 * @author Jozimar Soares da Costa
 * @author Rômulo Soares Bezerra
 * @see ArrayList
 * @see Compromisso
 */
public class CompromissoDaoBanco implements CompromissoDao {

    /**
     * Construtor da classe CompromissoDaoBanco
     */
    public CompromissoDaoBanco() {
    }

    /**
     * Método criado para retornar um compromisso através da data e da hora,
     * caso o mesmo exista
     *
     * @param data representa a data em que o compromisso irá ocorrer
     * @param hora representa a hora em que o compromisso irá ocorrer
     * @return o compromisso caso o mesmo exista no banco, caso contrário null
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
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

    /**
     * Método criado para listar os compromissos presentes no Banco de Dados
     *
     * @return uma lista contendo todos os compromissos presentes no banco
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @Override
    public List<Compromisso> listarCompromissos() throws ClassNotFoundException, SQLException, IOException {
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

    /**
     * Método criado para adicionar um compromisso no Banco de Dados
     *
     * @param c represneta o compromisso a ser adicionado
     * @return true caso o compromisso seja adicionado, caso contrário false
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @Override
    public boolean createCompromisso(Compromisso c) throws ClassNotFoundException, SQLException, IOException {
        Connection con = ConFactory.getConnection();
        PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO compromisso (data, hora, descricao,"
                + " local, email, nome) VALUES (?,?,?,?,?,?)");

        List<Compromisso> compromissos = listarCompromissos();

        for (Compromisso comp : compromissos) {
            if ((comp.getData().equals(c.getData()))
                    && (comp.getHora().equals(c.getHora()))) {
                return false;
            }
        }
        if ((c.getData().isBefore(LocalDate.now()))
                || ((c.getData() == LocalDate.now())
                && (c.getHora().isBefore(LocalTime.now())))) {
            return false;
        }

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

    /**
     * Método criado para deletar um compromisso do Banco de Dados
     *
     * @param c represneta o compromisso a ser deletado
     * @return true caso o compromisso seja deletado, caso contrário false
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
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

    /**
     * Método criado para atualizar um compromisso no banco de dados
     *
     * @param c representa o compromisso a ser atualizado
     * @return true caso o compromisso seja atualizado, caso contrário false
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
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

    /**
     * Método criado para retornar uma lista de compromissos de uma agenda em um
     * intervalo de datas
     *
     * @param nomeAgenda represneta o nome da agenda na qual o compromisso se
     * encontra
     * @param dataInicio representa a data inicial do intervalo de dadas
     * @param dataFim representa a data final do intervalo de datas
     * @return os compromissos de uma agenda presentes no intervalo
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
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
        } else {
            for (int i = 0; i < listaCompromissos.size(); i++) {
                if ((listaCompromissos.get(i).getEmail().equals(TelaInicial.usuarioLogado.getEmail()))
                        && listaCompromissos.get(i).getNome().equals(nomeAgenda)
                        && (listaCompromissos.get(i).getData().compareTo(dataInicio) >= 0)
                        && (listaCompromissos.get(i).getData().compareTo(dataFim) <= 0)) {
                    comp.add(listaCompromissos.get(i));
                }
            }
        }
        return comp;
    }

}
