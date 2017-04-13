package com.ifpb.agendaeletronica.entidades;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Classe que representa as informações de um compromisso, contém a modelagem 
 * da mesma com seus métodos elementares.
 * @author Rômulo Soares Bezerra
 * @author Jozimar Soares da Costa
 * @see Agenda
 */
public final class Compromisso {

    private LocalDate data;
    private LocalTime hora;
    private String descricao;
    private String local;
    
    /**
     * Construtor da classe Compromisso
     * @param data data do compromisso
     * @param hora hora do compromisso
     * @param descricao descrição do Compromisso
     * @param local local onde ocorrerá o Compromisso
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
     * 
     * @return data do Compromisso
     */
    public LocalDate getData() {
        return data;
    }
    
    /**
     * 
     * @param data data do compromisso
     * @throws DateTimeException lança uma Exceção de data inválida
     */
    public void setData(LocalDate data) throws DateTimeException {
        if (data.isBefore(LocalDate.now())) {
            throw new DateTimeException("A data é inválida!");
        }
        this.data = data;
    }
    
    /**
     * 
     * @return a hora do Compromisso
     */
    public LocalTime getHora() {
        return hora;
    }

    /**
     * 
     * @param hora hora do Compromisso
     * @throws DateTimeException lança uma Exceção de hora inválida 
     */
    public void setHora(LocalTime hora) throws DateTimeException {
        if (hora.isBefore(LocalTime.now())) {
            throw new DateTimeException("A Hora é Inválida");
        }
        this.hora = hora;
    }

    /**
    * 
    * @return descrição do Compromisso
    */
    public String getDescricao() {
        return descricao;
    }
    
    /**
     * 
     * @param descricao descrição do Compromisso
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
    * 
    * @return local do Compromisso
    */
    public String getLocal() {
        return local;
    }
    
    /**
     * 
     * @param local local do Compromisso
     */
    public void setLocal(String local) {
        this.local = local;
    }
    
    /**
     * 
     * @return hashCode do Compromisso
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
     * @param obj objeto a ser comparado
     * @return número neutro se o objeto comparado for igual, numero negativo 
     * se for maior e numero positovo se for menor
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
     * Método criado para escrever os dados do compromisso
     * @return String de descrição dos dados do compromisso
     */
    @Override
    public String toString() {
        return "Compromisso{" + "data=" + data + ", hora=" + hora
                + ", descricao=" + descricao + ", local=" + local + '}';
    }

}
