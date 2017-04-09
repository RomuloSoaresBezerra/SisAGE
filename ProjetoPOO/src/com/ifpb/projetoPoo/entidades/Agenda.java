
package com.ifpb.projetoPoo.entidades;

import java.util.Objects;

public class Agenda {
    
    private String NomeAgenda;

    public Agenda(String NomeAgenda) {
        this.NomeAgenda = NomeAgenda;
    }

    public String getNomeAgenda() {
        return NomeAgenda;
    }

    public void setNomeAgenda(String NomeAgenda) {
        this.NomeAgenda = NomeAgenda;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.NomeAgenda);
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
        if (!Objects.equals(this.NomeAgenda, other.NomeAgenda)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Agenda{" + "NomeAgenda=" + NomeAgenda + '}';
    }
     
}
