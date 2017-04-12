
package com.ifpb.agendaeletronica.entidades;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public final class Agenda {
    
    private String nomeAgenda;
    private List<Compromisso> compromisso;  

    public Agenda(String nomeAgenda) {
        this.nomeAgenda = nomeAgenda;
        this.compromisso = new ArrayList<>();
    }

    public String getNomeAgenda() {
        return nomeAgenda;
    }

    public void setNomeAgenda(String nomeAgenda) {
        this.nomeAgenda = nomeAgenda;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.nomeAgenda);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Agenda other = (Agenda) obj;
        return Objects.equals(this.nomeAgenda, other.nomeAgenda);
    }

    public boolean createCompromisso(Compromisso c){
        if(c.getData().isBefore(LocalDate.now()))
            return false;
        return compromisso.add(c);
    }
    
    public List<Compromisso> ListCompromisso(){
        return compromisso;
    }
    
    
    public boolean updateCompromisso(Compromisso c){
        for(int i=0; i<compromisso.size(); i++){
            if(compromisso.get(i).getData().equals(c.getData()) 
                    && compromisso.get(i).getHora().equals(c.getHora())){
                compromisso.set(i, c);
                return true;
            }
        }
        return false;
    }
    
    public boolean deleteCompromisso (Compromisso c){
        return compromisso.remove(c);
    }
    //Lista todos os compromissos dos proximos 30 dias de uma dada agenda
    public List<Compromisso> compromissosProx30DaysAgenda(){
        List<Compromisso> listAux =  new ArrayList<>();
        Date dataDoUsuario = java.sql.Date.valueOf(LocalDate.now());
        Calendar c = Calendar.getInstance();
        c.setTime(dataDoUsuario);
        c.add(Calendar.DATE, +30);
        dataDoUsuario = c.getTime();
        LocalDate data =dataDoUsuario.toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();
        if(compromisso.isEmpty()) return null;
        for(Compromisso comp : compromisso)
            if((comp.getData().compareTo(LocalDate.now())>=0)
                    && (comp.getData().compareTo(data)<=0 )) 
                listAux.add(comp); 
        return listAux;
    }       

    //Lista todos os compromissos do intervalo de uma dada agenda
    public List<Compromisso> compromissosEntreIntervalo(LocalDate dataInicio, 
            LocalDate dataFim){
        List<Compromisso> listaCompIntervalo = new ArrayList<>();
        for(Compromisso comp : compromisso)
            if((comp.getData().compareTo(dataInicio)>=0 ) && (comp.getData()
                    .compareTo(dataFim)<=0)) 
                listaCompIntervalo.add(comp); 
        return listaCompIntervalo;
    }
    
    @Override
    public String toString() {
        return "Agenda{" + "nomeAgenda=" + nomeAgenda + ", compromisso=" 
                + compromisso + '}';
    }
   
}
