
package com.ifpb.agendaeletronica.entidades;

import java.util.Objects;

public final class Agenda {
    
    private String nomeAgenda;

    public Agenda(String nomeAgenda) {
        this.nomeAgenda = nomeAgenda;
    }

    public Agenda() {
        
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

    @Override
    public String toString() {
        return "Agenda{" + "NomeAgenda=" + nomeAgenda + '}';
    }
     
}
