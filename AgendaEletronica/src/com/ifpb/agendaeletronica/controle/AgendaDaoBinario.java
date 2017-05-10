package com.ifpb.agendaeletronica.controle;

import com.ifpb.agendaeletronica.modelo.Agenda;
import com.ifpb.agendaeletronica.visao.TelaInicial;
import com.ifpb.agendaeletronica.interfaces.AgendaDao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Classe que contém o CRUD em arquivo binário para a classe<code>Agenda<code>
 *
 * @author Jozimar Soares da Costa
 * @author Rômulo Soares Bezerra
 * @see ArrayList
 * @see Agenda
 */
public class AgendaDaoBinario implements AgendaDao {

    private File arquivoAgenda;

    /**
     * Construtor da classe AgendaDaoBinario
     */
    public AgendaDaoBinario() {

        arquivoAgenda = new File("Agendas.bin");

        if (!arquivoAgenda.exists()) {
            try {
                arquivoAgenda.createNewFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                        "Falha na Conexão Com o Arquivo",
                        "Mensagem de Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Método criado para listar todas as agendas presentes no arquivo binário
     *
     * @return uma lista contendo todas as agendas presentes no arquivo
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public List<Agenda> listarAgendas() throws IOException, ClassNotFoundException {

        if (arquivoAgenda.length() > 0) {
            ObjectInputStream input = new ObjectInputStream(
                    new FileInputStream(arquivoAgenda));

            return (List<Agenda>) input.readObject();

        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Método criado para listar todas as agendas de um determinado usuário
     * salvo no arquivo
     *
     * @return uma lista contendo as agenda de um determinado usuário
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public List<Agenda> listarAgendasUsuario() throws IOException, ClassNotFoundException {
        List<Agenda> agendasLogado = new ArrayList<>();
        for (Agenda a : listarAgendas()) {
            if (a.getEmail().equals(TelaInicial.usuarioLogado.getEmail())) {
                agendasLogado.add(a);
            }
        }
        return agendasLogado;
    }

    /**
     * Método criado para adicionar uma agenda no arquivo binário
     *
     * @param a represneta a agenda a ser adicionada
     * @return true caso a agenda seja adicionada , caso contrário false
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public boolean createAgenda(Agenda a) throws IOException, ClassNotFoundException {

        List<Agenda> agendas = listarAgendas();

        for (Agenda agen : agendas) {
            if (agen.getNomeAgenda().equals(a.getNomeAgenda()) && agen.getEmail().equals(TelaInicial.usuarioLogado.getEmail())) {
                return false;
            }
        }
        agendas.add(a);
        atualizarArquivo(agendas);

        return true;
    }

    /**
     * Método criado para retornar uma agenda através do nome , caso a mesma
     * exista
     *
     * @param nomeAgenda representa o nome da agenda a ser adicionada
     * @return agenda caso a mesma esteja presente no arquivo, caso contrário
     * null
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public Agenda readAgenda(String nomeAgenda) throws IOException, ClassNotFoundException {

        List<Agenda> agendas = listarAgendas();

        for (Agenda agen : agendas) {
            if (agen.getNomeAgenda().equals(nomeAgenda) && agen.getEmail().equals(TelaInicial.usuarioLogado.getEmail())) {
                return agen;
            }
        }
        return null;
    }

    /**
     * Método criado para retornar uma lista contendo apenas os nomes das
     * agendas do arquivo
     *
     * @return uma lista contendo o nome das agendas presentes no arquivo
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public List<String> retornaNomeAgendas() throws IOException, ClassNotFoundException {
        List<String> nomesAgendas = new ArrayList();

        for (int i = 0; i < listarAgendas().size(); i++) {
            if (listarAgendas().get(i).getEmail().equals(TelaInicial.usuarioLogado.getEmail())) {
                nomesAgendas.add(listarAgendas().get(i).getNomeAgenda());
            }
        }
        return nomesAgendas;
    }

    /**
     * Método criado para atualizar uma agenda presente no arquivo
     *
     * @param a representa a agenda a ser atualizada
     * @return true caso a agenda seja atualizada, caso contrário false
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public boolean updateAgenda(Agenda a) throws IOException, ClassNotFoundException {

        List<Agenda> agendas = listarAgendas();

        for (int i = 0; i < agendas.size(); i++) {
            if (agendas.get(i).getNomeAgenda().equals(a.getNomeAgenda())) {
                agendas.set(i, a);

                atualizarArquivo(agendas);
                return true;
            }
        }
        return false;
    }

    /**
     * Método criado para atualizar apenas o nome de uma agenda presente no
     * arquivo
     *
     * @param a representa a agenda a ser atualizada
     * @param novoNome representa o novo nome que a agenda receberá
     * @return true caso o nome da agenda seja atualizado, caso contrário false
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public boolean AualizarNomeAgenda(Agenda a, String novoNome) throws IOException, ClassNotFoundException {

        List<Agenda> agendas = listarAgendas();

        for (int i = 0; i < agendas.size(); i++) {
            if (!(a.getNomeAgenda().equals(novoNome)) && agendas.get(i).getNomeAgenda().equals(a.getNomeAgenda()) && a.getEmail().equals(TelaInicial.usuarioLogado.getEmail())) {
                agendas.get(i).setNomeAgenda(novoNome);
                atualizarArquivo(agendas);
                return true;
            }
        }
        return false;
    }

    /**
     * Método criado para deletar uma agenda do arquivo binário
     *
     * @param nomeAgenda represneta o nome da agenda a ser deletada
     * @return true caso a agenda seja deletada, caso contrário retorna false
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public boolean deleteAgenda(String nomeAgenda) throws IOException, ClassNotFoundException {
        List<Agenda> agendas = listarAgendas();

        for (Agenda a : agendas) {

            if (a.getNomeAgenda().equals(nomeAgenda)) {
                agendas.remove(a);
                atualizarArquivo(agendas);
                return true;
            }
        }
        return false;
    }

    /**
     * Método criado para atualizar o arquivo que esta sendo utilizado
     *
     * @param agendas representa a lista de agendas presentes no arquivo
     * @throws IOException
     */
    private void atualizarArquivo(List<Agenda> agendas) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(arquivoAgenda));

        out.writeObject(agendas);
        out.close();
    }

}
