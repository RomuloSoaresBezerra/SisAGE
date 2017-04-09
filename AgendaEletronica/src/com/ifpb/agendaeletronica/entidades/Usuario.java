
package com.ifpb.agendaeletronica.entidades;

import com.ifpb.agendaeletronica.interfaces.AutenticavelUsuario;
import java.time.LocalDate;
import java.util.Objects;

public final class Usuario implements AutenticavelUsuario{
   
    private String nome;
    private LocalDate nascimento;
    private char sexo;
    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(String nome, LocalDate nascimento, char sexo, String email, 
            String senha) {
        this.nome = nome;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.email = email;
        this.senha = senha;
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
        this.nascimento = nascimento;
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
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", nascimento=" + nascimento + 
                ", sexo=" + sexo + ", email=" + email + '}';
    }
 
    @Override
    public boolean autenticarUsuario(String email, String senha){
        return this.email.equals(email) && this.senha.equals(senha);
    }
   
}
