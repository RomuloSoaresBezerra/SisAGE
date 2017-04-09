
package com.ifpb.projetoPoo.entidades;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Objects;

public class Compromisso {
   
    private LocalDate data;
    private Time hora;
    private String descricao;
    private String local;
    
    public Compromisso(LocalDate data, Time hora, String descricao, String local, String tipoAgenda) {
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
        this.local = local;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
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
        hash = 41 * hash + Objects.hashCode(this.data);
        hash = 41 * hash + Objects.hashCode(this.hora);
        hash = 41 * hash + Objects.hashCode(this.descricao);
        hash = 41 * hash + Objects.hashCode(this.local);
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
        if (!Objects.equals(this.local, other.local)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Compromisso{" + "data=" + data + ", hora=" + hora + ", descricao=" + descricao + ", local=" + local + '}';
    }
   
}
