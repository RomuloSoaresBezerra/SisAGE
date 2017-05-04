/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.agendaeletronica.cadastro;

import com.ifpb.agendaeletronica.entidades.Agenda;
import com.ifpb.agendaeletronica.entidades.Compromisso;
import com.ifpb.agendaeletronica.interfacegrafica.TelaInicial;
import static com.ifpb.agendaeletronica.interfacegrafica.TelaInicial.usuarioLogado;
import com.ifpb.agendaeletronica.interfaces.AgendaDao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author romulo
 */
public class AgendaDaoBinario implements AgendaDao{
   
    private File arquivoAgenda;
    private final List<Compromisso> compromissos;
    
    public AgendaDaoBinario(){
         
        arquivoAgenda = new File("Agendas.bin");
        compromissos = new ArrayList<>();
        
        if (!arquivoAgenda.exists()) {
            try{
                arquivoAgenda.createNewFile();
            }catch(IOException ex){
                JOptionPane.showMessageDialog(null,
                    "Falha na conexão com o arquivo",
                    "Mensagem de Erro",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
     public List<Agenda> listarAgendas() throws IOException, ClassNotFoundException{
        
         if (arquivoAgenda.length() > 0) {
            ObjectInputStream input = new ObjectInputStream(
                    new FileInputStream(arquivoAgenda));

            return (List<Agenda>) input.readObject();

        } else {
            return new ArrayList<>();
        }
    } 
     
    public boolean createAgenda(Agenda a) throws IOException, ClassNotFoundException{
        
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
     
     public Agenda readAgenda(String nomeAgenda) throws IOException, ClassNotFoundException{
        
         List<Agenda> agendas = listarAgendas();
        
         for (Agenda agen : agendas) {
            if (agen.getNomeAgenda().equals(nomeAgenda) && agen.getEmail().equals(TelaInicial.usuarioLogado.getEmail())) {
                return agen;
            }
        }
        return null;
    }

    public List<Compromisso> getCompromissos() {
        return compromissos;
    }
     

//     public List retornaNomeAgendas() throws IOException, ClassNotFoundException{
//        List<Agenda> agendas = new ArrayList();
//        for (Agenda a : agendas) {
//            agendas.add(a);
//        }
//        return agendas;
//
//    }
   
     public List<String> retornaNomeAgendas() throws IOException, ClassNotFoundException{
        List<Agenda> agendas = listarAgendas();
        List<String> nomesAgendas = new ArrayList();
        for (Agenda a : agendas) {
            if(a.getEmail().equals(TelaInicial.usuarioLogado.getEmail()))
                nomesAgendas.add(a.getNomeAgenda());
        }
        return nomesAgendas;
    }
     
     public boolean updateAgenda(Agenda a) throws IOException, ClassNotFoundException{
        
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
     
     public boolean deleteAgenda(String nomeAgenda) throws IOException, ClassNotFoundException{
         List<Agenda> agendas = listarAgendas();

        for(Agenda a : agendas){
        
            if(a.getNomeAgenda().equals(nomeAgenda)){
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
