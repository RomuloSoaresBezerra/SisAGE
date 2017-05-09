package com.ifpb.agendaeletronica.entidades;

import com.ifpb.agendaeletronica.cadastro.CompromissoDaoBanco;
import com.ifpb.agendaeletronica.cadastro.CompromissoDaoBinario;
import com.ifpb.agendaeletronica.excecoes.NomeInvalidoException;
import com.ifpb.agendaeletronica.interfaces.AutenticavelUsuario;
import com.ifpb.agendaeletronica.excecoes.EmailInvalidoException;
import com.ifpb.agendaeletronica.excecoes.SenhaInvalidaException;
import com.ifpb.agendaeletronica.interfacegrafica.TelaInicial;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa as informações de um usuário, contém a modelagem da
 * mesma com seus métodos elementares e o CRUD para a Classe
 * <code>Agenda</code>.
 *
 * @author Rômulo Soares Bezerra
 * @author Jozimar Soares da Costa
 * @see Agenda
 */
public class Usuario implements Serializable, AutenticavelUsuario {

    private String nome;
    private LocalDate nascimento;
    private char sexo;
    private String email;
    private String senha;

    /**
     * Construtor de <code>Usuario</code>
     *
     * @param nome nome do usuário
     * @param nascimento data de nascimento do usuário
     * @param sexo tipo do sexo do usuário
     * @param email email do usuário
     * @param senha senha do usuário
     * @throws DateTimeException lança uma Exceção de data inválida
     */
    public Usuario(String nome, LocalDate nascimento, char sexo, String email,
            String senha) throws DateTimeException {
        this.nome = nome;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.email = email;
        this.senha = senha;

    }

    public Usuario() {

    }

    /**
     *
     * @return nome do usuário
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome nome do usuário
     */
    public void setNome(String nome) throws NomeInvalidoException {
        if (nome.equals("")) {
            throw new NomeInvalidoException();
        }
        this.nome = nome;
    }

    /**
     *
     * @return data de nascimento do usuário
     */
    public LocalDate getNascimento() {
        return nascimento;
    }

    /**
     *
     * @param nascimento data de nascimento do usuário
     * @throws java.time.DateTimeException gera exceção caso data inválida
     */
    public void setNascimento(LocalDate nascimento) throws DateTimeException {
        if (nascimento.isAfter(LocalDate.now())) {
            throw new DateTimeException("A Data é Inválida");
        }
        this.nascimento = nascimento;
    }

    /**
     *
     * @return sexo do usuário
     */
    public char getSexo() {
        return sexo;
    }

    /**
     *
     * @param sexo sexo do usuário
     */
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    /**
     *
     * @return email do usuário
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email email do usuário
     */
    public void setEmail(String email) throws EmailInvalidoException {
        if (email.equals("")) {
            throw new EmailInvalidoException();
        }
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    /**
     *
     * @param senha senha do usuário
     */
    public void setSenha(String senha) throws SenhaInvalidaException {
        if (senha.equals("")) {
            throw new SenhaInvalidaException();
        }
        this.senha = senha;
    }

    /**
     *
     * @return hashCode do usuario
     */
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

    /**
     *
     * @param obj objeto a ser comparado
     * @return número neutro se o objeto comparado for igual, numero negativo se
     * for maior e numero positovo se for menor
     */
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

    /**
     * Método da interface <code>AutenticavelUsuario</code> criado para
     * autenticar o email e a senha de um usuário.
     *
     * @param email email do usuário
     * @param senha senha do usuário
     * @return true se email e senha forem autenticados e false caso negativo
     */
    @Override
    public boolean autenticarUsuario(String email, String senha) {
        return (this.email != null && this.email.equals(email))
                && (this.senha != null && this.senha.equals(senha));
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", nascimento=" + nascimento + ", sexo=" + sexo + ", email=" + email + ", senha=" + senha + '}';
    }

    /**
     * Método criado para adicionar uma agenda à uma lista de agendas de um
     * usuário, verificando se não haverá duplicata de agendas com o mesmo nome.
     *
     * @param a agenda a ser adicionada a lista de agendas de um usuário
     * @return true se a agenda for adicionada a lista de agendas do usuário e
     * falso caso negativo
     */
    /**
     * Método criado para retonar uma agenda dado seu nome.
     *
     * @param nomeAgenda nome da agenda
     * @return uma agenda passada o nome ou null caso não exista
     */
    /**
     * Método criado para retornar a lista de todas as agendas de um usuário.
     *
     * @return lista de agendas - vazia ou cheia
     */
    /**
     * Método criado para retornar uma lista contendo apenas os nomes de todas
     * as agendas de um usuário.
     *
     * @return lista de nomes de todas as agendas de um usuário
     */
    /**
     * Método criado para atualizar as informações de uma agenda.
     *
     * @param a agenda a ser atualizada
     * @return true se a agenda foi encontrada e atualizada, falso caso negativo
     */
    /**
     * Método criado para deletar uma dada agenda de um usuário.
     *
     * @param a agenda a ser deletada
     * @return true se a agenda foi deletada e falso caso negativo
     */
    /**
     * Método criado para retornar uma lista de todos os compromissos dos
     * próximos 30 dias à data de acesso do sistema de todas as agendas de um
     * usuário.
     *
     * @return lista de todos os compromissos dos próximos 30 dias à data de
     * acesso do sistema de todas as agendas de um usuário
     */
//    public static List compProx30DiasAgendasUser() {
//       
//        List listaAgen = new ArrayList<>();
//        if (agendas.isEmpty()) {
//            return null;
//        }
//        for (Agenda a : agendas) {
//            listaAgen.add(a.compProx30DiasAgenda());
//        }
//        return listaAgen;
//    }
    

    
/**
 * Método criado para retornar todos os compromissos de um intervalo de tempo de
 * datas de todas as agendas de um usuário.
 *
 * @param dataInicio data de inicio
 * @param dataFim data de fim
 * @return lista de todos os compromissos de todas as agendas de um usuário por
 * um intervalo datado
 */
//    public List<Compromisso> compromissosEntreIntervaloDeAgendas(
//            LocalDate dataInicio, LocalDate dataFim) {
//        List listComp = new ArrayList<>();
//        for (Agenda a : agendas) {
//            listComp.add(a.compromissosEntreIntervalo(dataInicio, dataFim));
//        }
//        return listComp;
//    }
/**
 * Método criado para escrever as informações do usuário com todas as suas
 * agendas.
 *
 * @return String de dados do usuário e de todas as suas agendas
 */
}
