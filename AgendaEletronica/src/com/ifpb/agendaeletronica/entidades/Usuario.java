
package com.ifpb.agendaeletronica.entidades;

import com.ifpb.agendaeletronica.interfaces.AutenticavelUsuario;
import com.ifpb.agendaeletronica.interfaces.DataValidator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Usuario implements AutenticavelUsuario, DataValidator{
   
    private String nome;
    private LocalDate nascimento;
    private char sexo;
    private String email;
    private String senha;
    private List<Agenda> agendas;
    
    public Usuario(String nome, LocalDate nascimento, char sexo, String email, 
            String senha) {
        this.nome = nome;       
        if(validaData(nascimento))
            this.nascimento = nascimento;        
        this.sexo = sexo;
        this.email = email;
        this.senha = senha;
        this.agendas = new ArrayList<>();       

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        if(validaData(nascimento)){
           this.nascimento = nascimento; 
        }
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.nome);
        hash = 67 * hash + Objects.hashCode(this.nascimento);
        hash = 67 * hash + this.sexo;
        hash = 67 * hash + Objects.hashCode(this.email);
        hash = 67 * hash + Objects.hashCode(this.senha);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.nascimento, other.nascimento)) {
            return false;
        }
        if (this.sexo != other.sexo) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.senha, other.senha);
    }

   
    @Override
    public boolean autenticarUsuario(String email, String senha){
        return (this.email != null && this.email.equals(email)) 
                && (this.senha != null && this.senha.equals(senha));
    }
    
    public boolean createAgenda(Agenda a){
        for(Agenda agen : agendas){
            if(agen.getNomeAgenda().equals(a.getNomeAgenda())){
                return false;
            }
        }
        return agendas.add(a);
    }
    
    public Agenda readAgenda(String nomeAgenda){
        for(Agenda agen : agendas){
            if(agen.getNomeAgenda().equals(nomeAgenda)) return agen;
        }
        return null;
    }
    
    public List<Agenda> ListarAgendas(){
        return agendas;
    }
    
    public List retornaNomeAgendas(){
        List<Agenda> agen = new ArrayList<>();
        for( Agenda a : agendas){
            agen.add(a);
        }
        return agen;
        
    }
    
    public boolean updateAgenda(Agenda a){
        for(int i=0; i<agendas.size(); i++){
            if(agendas.get(i).getNomeAgenda().equals(a.getNomeAgenda())){
                agendas.set(i, a);
                return true;
            }
        }
        return false;
    }
    
    public boolean deleteAgenda (Agenda a){
        return agendas.remove(a);
    }
    
    //Lista todos os compromissos de todas as agendas de um usuário
    public List<Compromisso> ListarCompromissosAgendas(){
        List compromissos = new ArrayList<>();
        for(Agenda a : agendas)
            compromissos.add(a.ListCompromisso());
        return compromissos;
    }
    
    //Lista todos os compromissos dos próximos 30 dias 
    //de todas as agenda do usuário
    public List compromissosProx30DaysAgendasUser(){
        List listaAgen = new ArrayList<>();
        if(agendas.isEmpty()) return null;
        for(Agenda a : agendas)
            listaAgen.add(a.compromissosProx30DaysAgenda());
        return listaAgen;
    }
    
    //Lista todos os compromissos de um intervalo de todas as agendas
    public List<Compromisso> compromissosEntreIntervaloDeAgendas
        (LocalDate dataInicio, LocalDate dataFim){
        List listComp = new ArrayList<>();
        for(Agenda a : agendas)
            listComp.add(a.compromissosEntreIntervalo(dataInicio, dataFim));
        return listComp;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", nascimento=" + nascimento 
                + ", sexo=" + sexo + ", email=" + email + ", senha=" + senha 
                + ", agenda=" + agendas + '}';
    }
   
    
}
