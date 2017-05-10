package com.ifpb.agendaeletronica.modelo;

import com.ifpb.agendaeletronica.excecoes.DescricaoInvalidaException;
import com.ifpb.agendaeletronica.excecoes.LocalInvalidoException;
import com.ifpb.agendaeletronica.excecoes.NomeInvalidoException;
import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public final class Compromisso implements Serializable {

    private LocalDate data;
    private LocalTime hora;
    private String descricao;
    private String local;
    private String email;
    private String nome;

    public Compromisso(LocalDate data, LocalTime hora, String descricao, String local, String email, String nome) throws DateTimeException {
        if (data.isBefore(LocalDate.now())) {
            throw new DateTimeException("A Data é Inválida");
        }
        this.data = data;
        if (data.equals(LocalDate.now()) && hora.isBefore(LocalTime.now())) {
            throw new DateTimeException("A Hora é Inválida");
        }
        this.hora = hora;
        this.descricao = descricao;
        this.local = local;
        this.email = email;
        this.nome = nome;
    }

    public Compromisso() {

    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) throws DateTimeException {
        if (data.isBefore(LocalDate.now())) {
            throw new DateTimeException("A Data é Inválida");
        }
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) throws DateTimeException {
        if (data.equals(LocalDate.now()) && hora.isBefore(LocalTime.now())) {
            throw new DateTimeException("A Hora é Inválida");
        }
        this.hora = hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) throws DescricaoInvalidaException {
        if (descricao.equals("")) {
            throw new DescricaoInvalidaException();
        }
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) throws LocalInvalidoException {
        if (local.equals("")) {
            throw new LocalInvalidoException();
        }
        this.local = local;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws NomeInvalidoException {
        if (nome.equals("") || nome.equals(null)) {
            throw new NomeInvalidoException();
        }
        this.nome = nome;
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
        return "Compromisso{" + "data=" + data + ", hora=" + hora + ", descricao=" + descricao + ", local=" + local + ", email=" + email + ", nome=" + nome + '}';
    }

}
