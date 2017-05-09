package com.ifpb.agendaeletronica.entidades;

import com.ifpb.agendaeletronica.excecoes.NomeInvalidoException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa os tipos de agendas de um usuário, contém a modelagem 
 * da mesma com seus métodos elementares e o CRUD para a Classe 
 * <code>Compromisso</code>.
 * @author Rômulo Soares Bezerra
 * @author Jozimar Soares da Costa
 * @see Compromisso
 * @see ArrayList
 */
public class Agenda implements  Serializable{

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String nomeAgenda;
    private String email;
    
    /**
     * Construtor de Agenda
     * @param nomeAgenda tipo da agenda do usuário
     */
    public Agenda(String nomeAgenda) {
        this.nomeAgenda = nomeAgenda;
       
    }

    public Agenda() {
       
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
    public void setNomeAgenda(String nomeAgenda) throws NomeInvalidoException{
        if(nomeAgenda.equals("")){
            throw new NomeInvalidoException();
        }
        this.nomeAgenda = nomeAgenda;
    }


    /**
     * 
     * @return o hashCode da agenda 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.nomeAgenda);
        return hash;
    }

    /**
     * 
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
        final Agenda other = (Agenda) obj;
        return Objects.equals(this.nomeAgenda, other.nomeAgenda);
    }

    @Override
    public String toString() {
        return "Agenda{" + "nomeAgenda=" + nomeAgenda + ", email=" + email + '}';
    }
    
    /**
     * Método criado para adicionar um compromisso numa lista de compromissos, 
     * validando: se existe um compromisso com a mesma data e na mesma hora; se 
     * a data é válida e não já se passou; e se a hora de um compromisso de um 
     * dia já passou.
     * @param c compromisso criado para um tipo de agenda
     * @return true se um compromisso foi adicionado, false caso negativo
     */
    
    
    /**
     * Método que retorna um objeto Compromisso por sua data e hora ou null em 
     * caso de inexistência.
     * @param data data de um compromisso.
     * @param hora hora de um compromisso.
     * @return compromisso da respectiva data e hora ou null caso não exista.
     */
    
    
    /**
     * Método criado para retornar uma lista de compromissos.
     * @return uma lista de todos os compromissos - vazia ou cheia
     */
    
    
    /**
     * Método criado para atualizar as informações de um dado compromisso.
     * @param c compromisso a ser atualizado
     * @return true se um compromisso foi encontrado e alterado, 
     * e false caso negativo
     */
   
    /**
     * Método criado para deletar um compromisso.
     * @param c compromisso a ser deletado
     * @return true se compromisso foi deletado e false caso negativo
     */
    

    /**
     * Método criado para retornar todos os compromissos dos próximos 30 dias à 
     * data de acesso do sistema de uma determinada agenda.
     * @return lista de compromissos dos próximos 30 dias à data de acesso do 
     * sistema de uma agenda ou null em caso de lista vazia.  
     */
    

    /**
     * Método criado para retornar todos os compromissos de um intervalo 
     * de datas.
     * @param dataInicio data de inicio
     * @param dataFim data de fim
     * @return lista de compromissos entre um intervalo datado
     */
//    public static List<Compromisso> compromissosEntreIntervalo(LocalDate dataInicio,
//            LocalDate dataFim) {
//        List<Compromisso> listaCompIntervalo = new ArrayList<>();
//        for (Compromisso comp : compromisso) {
//            if ((comp.getData().compareTo(dataInicio) >= 0) && (comp.getData()
//                    .compareTo(dataFim) <= 0)) {
//                listaCompIntervalo.add(comp);
//            }
//        }
//        return listaCompIntervalo;
//    }
    
    /**
     * Método criado para escrever os dados de agenda e seus compromissos
     * @return String com os dados da agenda e seus compromissos
     */
    
}
