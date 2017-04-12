
package com.ifpb.agendaeletronica.entidades;

import com.ifpb.agendaeletronica.interfaces.DataValidator;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public final class Compromisso implements DataValidator{
   
    private LocalDate data;
    private LocalTime hora;
    private String descricao;
    private String local;

    public Compromisso(LocalDate data, LocalTime hora, String descricao, 
            String local) { 
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
        this.local = local;
    }
    
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        if(validaData(data)){
            this.data = data;
        }
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.data);
        hash = 83 * hash + Objects.hashCode(this.hora);
        hash = 83 * hash + Objects.hashCode(this.descricao);
        hash = 83 * hash + Objects.hashCode(this.local);
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
        final Compromisso other = (Compromisso) obj;
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.hora, other.hora)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return Objects.equals(this.local, other.local);
    }

    @Override
    public String toString() {
        return "Compromisso{" + "data=" + data + ", hora=" + hora 
                + ", descricao=" + descricao + ", local=" + local + '}';
    }

}
