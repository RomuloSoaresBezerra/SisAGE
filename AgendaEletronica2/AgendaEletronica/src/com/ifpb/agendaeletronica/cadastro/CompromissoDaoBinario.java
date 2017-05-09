/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.agendaeletronica.cadastro;

import com.ifpb.agendaeletronica.entidades.Compromisso;
import com.ifpb.agendaeletronica.interfacegrafica.TelaInicial;
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
 *
 * @author Familia
 */
public class CompromissoDaoBinario implements CompromissoDao{
 
    private File arquivoCompromisso;
    
    public CompromissoDaoBinario(){
         
        arquivoCompromisso = new File("Compromissos.bin");

        if (!arquivoCompromisso.exists()) {
            try{
                arquivoCompromisso.createNewFile();
            }catch(IOException ex){
                JOptionPane.showMessageDialog(null,
                    "Falha na Conexão Com o Arquivo",
                    "Mensagem de Erro",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

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
    
    private void atualizarArquivo(List<Compromisso> compromissos) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(arquivoCompromisso));

        out.writeObject(compromissos);
        out.close();
    }    
    
    public List<Compromisso> compIntervaloDataAgendas(String nomeAgenda, LocalDate dataInicio, LocalDate dataFim)
            throws IOException, ClassNotFoundException, SQLException {
        
//        Para torná-la para #Banco#
//        CompromissoDaoBanco compDaoBanco = new CompromissoDaoBanco();
//        List<Compromisso> listaCompromissos = compDaoBanco.listarCompromissos();
        
        
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
