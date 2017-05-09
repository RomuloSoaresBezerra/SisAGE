/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.agendaeletronica.cadastro;

import com.ifpb.agendaeletronica.entidades.Agenda;
import com.ifpb.agendaeletronica.entidades.Compromisso;
import com.ifpb.agendaeletronica.interfacegrafica.TelaInicial;
import com.ifpb.agendaeletronica.interfaces.AgendaDao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author romulo
 */
public class AgendaDaoBinario implements AgendaDao {

    private File arquivoAgenda;

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

    private void atualizarArquivo(List<Agenda> agendas) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(arquivoAgenda));

        out.writeObject(agendas);
        out.close();
    }

    
}
