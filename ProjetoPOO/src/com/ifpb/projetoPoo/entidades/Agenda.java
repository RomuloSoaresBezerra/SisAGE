
package com.ifpb.projetoPoo.entidades;

import java.util.Objects;

public class Agenda {
    
    private String tipoAgenda;

    public Agenda(String tipoAgenda) {
        this.tipoAgenda = tipoAgenda;
    }

    public String getTipoAgenda() {
        return tipoAgenda;
    }

    public void setTipoAgenda(String tipoAgenda) {
        this.tipoAgenda = tipoAgenda;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.tipoAgenda);
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
        if (!Objects.equals(this.tipoAgenda, other.tipoAgenda)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Agenda{" + "tipoAgenda=" + tipoAgenda + '}';
    }
    
    
}
