
package com.ifpb.agendaeletronica.cadastro;

import com.ifpb.agendaeletronica.entidades.Agenda;
import java.util.ArrayList;
import java.util.List;

public class AgendaDao {
    
    private List<Agenda> agenda;

    public AgendaDao() {
        this.agenda = new ArrayList<>();
    }
   
    public boolean create(Agenda a){
        for(Agenda agen : agenda){
            if(agen.getNomeAgenda().equals(a.getNomeAgenda())){
                return false;
            }
        }
        return agenda.add(a);
    }
    
    public Agenda read(String NomeAgenda){
        for(Agenda agen : agenda){
            if(agen.getNomeAgenda().equals(NomeAgenda)) return agen;
        }
        return null;
    }
    
    public boolean update(Agenda a){
        for(int i=0; i<agenda.size(); i++){
            if(agenda.get(i).getNomeAgenda().equals(a.getNomeAgenda())){
                agenda.set(i, a);
                return true;
            }
        }
        return false;
    }
    
    public boolean delete (Agenda a){
        return agenda.remove(a);
    }
    
}
