package com.ifpb.agendaeletronica.controle;

import com.ifpb.agendaeletronica.modelo.Compromisso;
import com.ifpb.agendaeletronica.visao.TelaInicial;
import com.ifpb.agendaeletronica.interfaces.CompromissoDao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Classe que contém o CRUD em arquivo binário para a classe<code>Compromisso<code>
 *
 * @author Jozimar Soares da Costa
 * @author Rômulo Soares Bezerra
 * @see ArrayList
 * @see Compromisso
 */
public class CompromissoDaoBinario implements CompromissoDao {

    private File arquivoCompromisso;

    /**
     * Construtor da classe CompromissoDaoBinario
     */
    public CompromissoDaoBinario() {

        arquivoCompromisso = new File("Compromissos.bin");

        if (!arquivoCompromisso.exists()) {
            try {
                arquivoCompromisso.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "Falha na Conexão Com o Arquivo",
                        "Mensagem de Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Método criado para retornar um compromisso através da data e da hora ,
     * caso o mesmo exista
     *
     * @param data representa a data na qual o compromisso foi marcado
     * @param hora represneta a hora na qual o compromisso foi marcado
     * @return compromisso caso o mesmo exista, caso contrário null
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @Override
    public Compromisso readCompromisso(LocalDate data, LocalTime hora) throws ClassNotFoundException, SQLException, IOException {

        List<Compromisso> compromissos = listarCompromissos();

        for (Compromisso comp : compromissos) {
            if (comp.getData().equals(data) && comp.getHora().equals(hora)) {
                return comp;
            }
        }
        return null;

    }

    /**
     * Método criado para listar os compromissos presentes no arquivo binario
     *
     * @return uma lista contendo todos os compromissos de um usuário presentes
     * no arquivo
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @Override
    public List<Compromisso> listarCompromissos() throws ClassNotFoundException, SQLException, IOException {
        if (arquivoCompromisso.length() > 0) {
            ObjectInputStream input = new ObjectInputStream(
                    new FileInputStream(arquivoCompromisso));

            return (List<Compromisso>) input.readObject();

        } else {
            return new ArrayList<>();
        }

    }

    /**
     * Método criado para adicionar um compromisso no arquivo binário
     *
     * @param c representa o compromisso a ser adicionado
     * @return true caso o compromisso seja adiciona, caso contrário false
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @Override
    public boolean createCompromisso(Compromisso c) throws ClassNotFoundException, SQLException, IOException {

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

        compromissos.add(c);
        atualizarArquivo(compromissos);
        return true;

    }

    /**
     * Método criado para deletar um compromisso do arquivo binário
     *
     * @param c representa o compromisso a ser deletado
     * @return true caso o compromisso seja deletado, caso contrário false
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @Override
    public boolean deleteCompromisso(Compromisso c) throws ClassNotFoundException, SQLException, IOException {

        List<Compromisso> compromissos = listarCompromissos();

        for (Compromisso comp : compromissos) {
            if ((comp.getData().equals(c.getData()))
                    && (comp.getHora().equals(c.getHora()))) {
                compromissos.remove(c);
                atualizarArquivo(compromissos);
                return true;
            }

        }

        return false;

    }

    /**
     * Método criado para atualizar um compromisso presente no arquivo
     *
     * @param c representa o compromisso a ser atualizado
     * @return true caso o compromisso seja atualizado, caso contrário false
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    @Override
    public boolean updateCompromisso(Compromisso c) throws ClassNotFoundException, SQLException, IOException {

        List<Compromisso> compromissos = listarCompromissos();

        for (int i = 0; i < compromissos.size(); i++) {
            if (compromissos.get(i).getData().equals(c.getData())
                    && compromissos.get(i).getHora().equals(c.getHora())) {
                compromissos.set(i, c);

                atualizarArquivo(compromissos);
                return true;
            }
        }
        return false;

    }

    /**
     * Método criado para atualizar o arquivo binário que está sendo utilizado
     *
     * @param compromissos presenta a lista de compromissos presentes no arquivo
     * @throws IOException
     */
    private void atualizarArquivo(List<Compromisso> compromissos) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(arquivoCompromisso));

        out.writeObject(compromissos);
        out.close();
    }

    /**
     * Método criado para retornar uma lista de compromissos de uma agenda em um
     * intervalo de datas
     *
     * @param nomeAgenda representa o nome da agenda na qual o compromisso se
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
