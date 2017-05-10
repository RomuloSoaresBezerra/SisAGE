package com.ifpb.agendaeletronica.modelo;

import com.ifpb.agendaeletronica.excecoes.NomeInvalidoException;
import java.io.Serializable;
import java.util.Objects;

public class Agenda implements Serializable {

    private String nomeAgenda;
    private String email;

    public Agenda(String nomeAgenda, String email) {
        this.nomeAgenda = nomeAgenda;
        this.email = email;

    }

    public Agenda() {

    }

    public String getNomeAgenda() {
        return nomeAgenda;
    }

    public void setNomeAgenda(String nomeAgenda) throws NomeInvalidoException {
        if (nomeAgenda.equals("")) {
            throw new NomeInvalidoException();
        }
        this.nomeAgenda = nomeAgenda;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return "Agenda{" + "nomeAgenda=" + nomeAgenda + ", email=" + email + '}';
    }

}
