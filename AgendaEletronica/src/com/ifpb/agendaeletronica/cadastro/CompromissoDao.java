
package com.ifpb.agendaeletronica.cadastro;

import com.ifpb.agendaeletronica.entidades.Compromisso;
import java.util.ArrayList;
import java.util.List;

public class CompromissoDao {
   
     private List<Compromisso> compromisso;

    public CompromissoDao() {
        this.compromisso = new ArrayList<>();
    }
   
    public boolean create(Compromisso c){
        for(Compromisso comp : compromisso){
            if(comp.getLocal().equals(c.getLocal())){
                return false;
            }
        }
        return compromisso.add(c);
    }
    
    public Compromisso read(String local){
        for(Compromisso comp : compromisso){
            if(comp.getLocal().equals(local)) return comp;
        }
        return null;
    }
    
    public boolean update(Compromisso c){
        for(int i=0; i<compromisso.size(); i++){
            if(compromisso.get(i).getLocal().equals(c.getLocal())){
                compromisso.set(i, c);
                return true;
            }
        }
        return false;
    }
    
    public boolean delete (Compromisso c){
        return compromisso.remove(c);
    }
    
}
