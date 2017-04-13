package com.ifpb.agendaeletronica.entidades;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * A Classe <code>Agenda</code> é uma classe que representa os tipos de agendas 
 * de um usuário, contém a modelagem da mesma com seus métodos elementares e o 
 * <b>CRUD<b> para a Class <code>Compromisso</code>.
 * @author Rômulo Soares Bezerra
 * @author Jozimar Soares da Costa
 * @see Compromisso
 */
public final class Agenda {

    private String nomeAgenda;
    private List<Compromisso> compromisso;
    
    /**
     * Construtor de <code>Agenda</code>
     * @param nomeAgenda tipo da agenda do usuário
     */
    public Agenda(String nomeAgenda) {
        this.nomeAgenda = nomeAgenda;
        this.compromisso = new ArrayList<>();
    }
    
    /**
     * 
     * @return o nome da agenda
     */
    public String getNomeAgenda() {
        return nomeAgenda;
    }
    
    /**
     * 
     * @param nomeAgenda tipo da agenda do usuario 
     */
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
    /**
     * Método que adiciona um compromisso numa lista de compromissos, validando:
     * se existe um compromisso com a mesma data e na mesma hora; se a data é
     * válida e não já se passou; e se a hora de um compromisso de um dia já 
     * passou.
     * @param c compromisso criado para um tipo de agenda
     * @return true se um compromisso foi adicionado, false caso negativo
     */
    public boolean createCompromisso(Compromisso c) {
        for (Compromisso comp : compromisso) {
            if ((comp.getData().equals(c.getData()))
                    && (comp.getHora().equals(c.getHora()))) {
                return false;
            }
        }
        if ((c.getData().isBefore(LocalDate.now()))
                || ((c.getData() == LocalDate.now())
                && (c.getHora().isBefore(LocalTime.now())))) {
            return false;
        }

        return compromisso.add(c);
    }
    
    /**
     * Método que retorna um objeto Compromisso por sua data e hora
     * @param data data de um compromisso.
     * @param hora hora de um compromisso.
     * @return compromisso da respectiva data e hora.
     */
    public Compromisso readCompromisso(LocalDate data, LocalTime hora) {
        for (Compromisso comp : compromisso) {
            if (comp.getData().equals(data) && comp.getHora().equals(hora)) {
                return comp;
            }
        }
        return null;
    }
    
    /**
     * Método criado para retornar uma lista de compromissos.
     * @return uma lista de todos os compromissos 
     */
    public List<Compromisso> ListCompromisso() {
        return compromisso;
    }
    
    /**
     * Método criado para atualizar as informações de um dado compromisso.
     * @param c compromisso a ser atualizado
     * @return true se um compromisso foi encontrado e alterado, 
     * e false caso negativo
     */
    public boolean updateCompromisso(Compromisso c) {
        for (int i = 0; i < compromisso.size(); i++) {
            if (compromisso.get(i).getData().equals(c.getData())
                    && compromisso.get(i).getHora().equals(c.getHora())) {
                compromisso.set(i, c);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Método criado para deletar um compromisso.
     * @param c compromisso a ser deletado
     * @return true se compromisso foi deletado e false caso negativo
     */
    public boolean deleteCompromisso(Compromisso c) {
        return compromisso.remove(c);
    }

    /**
     * Método criado para retornar todos os compromissos dos próximos 30 dias à 
     * data de acesso do sistema de uma determinada agenda.
     * @return lista de compromissos dos próximos 30 dias à data de acesso do 
     * sistema de uma agenda.  
     */
    public List<Compromisso> compromissosProx30DaysAgenda() {
        List<Compromisso> listAux = new ArrayList<>();
        Date dataDoUsuario = java.sql.Date.valueOf(LocalDate.now());
        Calendar c = Calendar.getInstance();
        c.setTime(dataDoUsuario);
        c.add(Calendar.DATE, +30);
        dataDoUsuario = c.getTime();
        LocalDate data = dataDoUsuario.toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();
        if (compromisso.isEmpty()) {
            return null;
        }
        for (Compromisso comp : compromisso) {
            if ((comp.getData().compareTo(LocalDate.now()) >= 0)
                    && (comp.getData().compareTo(data) <= 0)) {
                listAux.add(comp);
            }
        }
        return listAux;
    }

    /**
     * Método criado para retornar todos os compromissos de um intervalo 
     * de datas.
     * @param dataInicio data de inicio
     * @param dataFim data de fim
     * @return lista de compromissos entre um intervalo datado
     */
    public List<Compromisso> compromissosEntreIntervalo(LocalDate dataInicio,
            LocalDate dataFim) {
        List<Compromisso> listaCompIntervalo = new ArrayList<>();
        for (Compromisso comp : compromisso) {
            if ((comp.getData().compareTo(dataInicio) >= 0) && (comp.getData()
                    .compareTo(dataFim) <= 0)) {
                listaCompIntervalo.add(comp);
            }
        }
        return listaCompIntervalo;
    }

    @Override
    public String toString() {
        return "Agenda{" + "nomeAgenda=" + nomeAgenda + ", compromisso="
                + compromisso + '}';
    }

}
