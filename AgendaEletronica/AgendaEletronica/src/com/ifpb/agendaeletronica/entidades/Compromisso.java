package com.ifpb.agendaeletronica.entidades;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
/**
 * Essa Classe representa todas as informações 
 * de um Compromisso 
 * @author Jozimar Soares
 * @author Rômulo Bezerra
 */
public final class Compromisso {

    private LocalDate data;
    private LocalTime hora;
    private String descricao;
    private String local;
/**
 * Construtor da classe Compromisso
 * @param data representa a data do Compromisso
 * @param hora representa a hora do Compromisso
 * @param descricao representa uma breve descrição do Compromisso
 * @param local representa o local onde ocorrerá o Compromisso
 * @throws DateTimeException lança uma Exceção de data e hora inválidas
 */
    public Compromisso(LocalDate data, LocalTime hora, String descricao,
            String local) throws DateTimeException {
        if (data.isBefore(LocalDate.now())) {
            throw new DateTimeException("A Data é Inválida!");
        }
        this.data = data;
        if (data.equals(LocalDate.now()) && hora.isBefore(LocalTime.now())) {
            throw new DateTimeException("A Hora é Inválida");
        }
        this.hora = hora;
        this.descricao = descricao;
        this.local = local;
    }
/**
 * getData do Compromisso
 * @return a data do Compromisso
 */
    public LocalDate getData() {
        return data;
    }
/**
 * setData do Compromisso
 * @param data representa a data do compromisso
 * @throws DateTimeException lança uma Exceção de data inválida
 */
    public void setData(LocalDate data) throws DateTimeException {
        if (data.isBefore(LocalDate.now())) {
            throw new DateTimeException("A data é inválida!");
        }
        this.data = data;
    }
/**
 * getHora do Compromisso
 * @return a hora do Compromisso
 */
    public LocalTime getHora() {
        return hora;
    }
/**
 * setHora do Compromisso
 * @param hora representa a hora do Compromisso
 * @throws DateTimeException lança uma Exceção de hora inválida 
 */
    public void setHora(LocalTime hora) throws DateTimeException {
        if (hora.isBefore(LocalTime.now())) {
            throw new DateTimeException("A Hora é Inválida");
        }
        this.hora = hora;
    }
/**
 * getDescricao do Compromisso
 * @return a descrição do Compromisso
 */
    public String getDescricao() {
        return descricao;
    }
/**
 * setDescricao do Compromisso
 * @param descricao representa a descrição do Compromisso
 */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
/**
 * getLocal do Compromisso
 * @return o local do Compromisso
 */
    public String getLocal() {
        return local;
    }
/**
 * setLocal do Compromisso
 * @param local representa o local do Compromisso
 */
    public void setLocal(String local) {
        this.local = local;
    }
/**
 * hashCode do Compromisso
 * @return o hash do Compromisso
 */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.data);
        hash = 83 * hash + Objects.hashCode(this.hora);
        hash = 83 * hash + Objects.hashCode(this.descricao);
        hash = 83 * hash + Objects.hashCode(this.local);
        return hash;
    }
/**
 * equals do Compromisso
 * @param obj representa um objeto
 * @return o equals do Compromisso 
 */
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
/**
 * toString do Compromisso
 * @return a String formatada do Compromisso
 */
    @Override
    public String toString() {
        return "Compromisso{" + "data=" + data + ", hora=" + hora
                + ", descricao=" + descricao + ", local=" + local + '}';
    }

}
