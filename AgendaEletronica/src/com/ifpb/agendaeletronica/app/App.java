
package com.ifpb.agendaeletronica.app;

import com.ifpb.agendaeletronica.cadastro.AgendaDao;
import com.ifpb.agendaeletronica.cadastro.CompromissoDao;
import com.ifpb.agendaeletronica.cadastro.UsuarioDao;
import com.ifpb.agendaeletronica.entidades.Agenda;
import com.ifpb.agendaeletronica.entidades.Compromisso;
import com.ifpb.agendaeletronica.entidades.Usuario;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {

   
    public static void main(String[] args) {
        Usuario usuario = new Usuario();
        Compromisso compromisso = new Compromisso();
        Agenda agenda = new Agenda();
        UsuarioDao cadUser = new UsuarioDao();
        CompromissoDao cadComp = new CompromissoDao();
        AgendaDao cadAgen = new AgendaDao();
        Scanner scanner = new Scanner(System.in);
        int escolha;
        
        System.out.println("Tela de Login");
            System.out.println("Digite seu email: ");
            String email = scanner.next();
            System.out.println("Digite sua senha: ");
            String senha = scanner.next();
        
        while(email !=null && senha != null){
           
            if(usuario.autenticarUsuario(email, senha) == true){
                System.out.println("Você entrou com sucesso!");
                System.out.println("Deseja criar uma agenda?");
                System.out.println("\n1 > para adicionar uma nova agenda\n2 > Para outra opção\n3 > para sair");
                escolha = scanner.nextInt();
                switch(escolha){
                    case 1:
                        System.out.println("Digite o nome da sua Agenda");
                        String nomeAgenda = scanner.next();
                        cadAgen.create(new Agenda(nomeAgenda));
                        System.out.println("Agenda criada com sucesso");
                    break;
                    case 2:
                        
                    break;
                    case 3:
                        System.exit(0);
                    break;
                    
                }
                System.out.println("Deseja buscar sua agenda pelo nome?");
                System.out.println("\n1 > para buscar agenda\n2 > Para outra opção\n3 > para sair");
                escolha = scanner.nextInt();
                switch(escolha){
                    case 1:
                        System.out.println("Por favor digite o nome da agenda que deseja buscar:");
                        String nomeAgenda = scanner.next();
                        System.out.println("Sua agenda está da seguinte forma:" + cadAgen.read(nomeAgenda));
                        
                    break;
                    case 2:
                        
                    break;
                    case 3:
                    System.exit(0);
                    break;
                }
                System.out.println("\n1 > para atualizar agenda\n2 > Para outra opção\n3 > para sair");
                escolha = scanner.nextInt();
                switch(escolha){
                    case 1:
                        System.out.println("Digite o nome da agenda que deseja atualizar:");
                        String nomeAgenda = scanner.next();
                        
                        System.out.println("Digite o novo nome da agenda");
                        nomeAgenda = scanner.next();
                        cadAgen.update(agenda = new Agenda(nomeAgenda));
                        System.out.println("\nAgenda atualizada com sucesso\n");
                        System.out.println(agenda);
                    break;
                    case 2:
                        
                    break;
                    case 3:
                        System.exit(0);
                    break;
                }
                System.out.println("\n1 > para deletar agenda\n2 > Para outra opção\n3 > para sair");
                escolha = scanner.nextInt();
                switch(escolha){
                    case 1:
                        System.out.println("Para excluir sua agenda será preciso informar os seguintes dados:");
                        System.out.println("Digite o nome da sua Agenda");
                        String nomeAgenda = scanner.next();
                        
                        cadAgen.delete(new Agenda(nomeAgenda));
                        System.out.println("Agenda excluida com sucesso!");
                    break;
                    case 2:
                        
                    break;
                    case 3:
                        System.exit(0);
                    break;
                }
                System.out.println("\n1 > para adicionar um novo compromisso\n2 > Para outra opção\n3 > para sair");
                escolha = scanner.nextInt();
                switch(escolha){
                    case 1:
                    System.out.println("Digite a data do compromisso: ");
                    String data = scanner.next();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = LocalDate.parse(data,dtf);
                    System.out.println("Digite a hora do compromisso: ");
                    String hora = scanner.next();
                    DateTimeFormatter hour = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime horario = LocalTime.parse(hora,hour);
                    System.out.println("Escreva uma breve descrição: ");
                    String descricao = scanner.next();
                    System.out.println("Digite o local: ");
                    String local = scanner.next();
                    System.out.println("Digite o tipo da agenda: ");
                    String tipoAgenda = scanner.next();
                    
                    cadComp.create(new Compromisso(date, horario, descricao, local, tipoAgenda));
 
                        System.out.println("Compromisso agendado com sucesso!");  
                    break;
                    case 2:
                         
                    break;
                    case 3:
                    System.exit(0);
                    break;
                   }
                System.out.println("\n1 > para ver seu compromisso\n2 > Para outra opção\n3 > para sair");
                escolha = scanner.nextInt();
                switch(escolha){
                    case 1:
                        System.out.println("Por favor digite o local onde deseja ver seu compromisso:");
                        String local = scanner.next();
                        System.out.println("Seus Compromissos neste local são:" + cadComp.read(local));
                    break;
                    case 2:
                        
                    break;
                    case 3:
                    System.exit(0);
                    break;
                }
                System.out.println("\n1 > para atualizar seu compromisso\n2 > Para outra opção\n3 > para sair");
                escolha = scanner.nextInt();
                switch(escolha){
                    case 1:
                      System.out.println("Por favor digite o local onde deseja atualizar seu compromisso:");
                        String local = scanner.next();
                        
                    System.out.println("Digite a nova data do compromisso: ");
                    String data = scanner.next();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = LocalDate.parse(data,dtf);
                    System.out.println("Digite a nova hora do compromisso: ");
                    String hora = scanner.next();
                    DateTimeFormatter hour = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime horario = LocalTime.parse(hora,hour);
                    System.out.println("Escreva uma breve descrição atualizada: ");
                    String descricao = scanner.next();
                    System.out.println("Digite o novo tipo da agenda: ");
                    String tipoAgenda = scanner.next();
                    
                    cadComp.update(compromisso = new Compromisso(date, horario, descricao, local, tipoAgenda));
                    System.out.println("\nCompromisso atualizado com sucesso\n");
                        System.out.println(compromisso);
                }
                
                System.out.println("\n1 > para deletar seu compromisso\n2 > Para outra opção\n3 > para sair");
                escolha = scanner.nextInt();
                switch(escolha){
                    case 1:
                        System.out.println("Para excluir seu compromisso será necessário informar os seguintes dados:");
                        System.out.println("Digite a data do compromisso: ");
                        String data = scanner.next();
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate date = LocalDate.parse(data,dtf);
                        System.out.println("Digite a hora do compromisso: ");
                        String hora = scanner.next();
                        DateTimeFormatter hour = DateTimeFormatter.ofPattern("HH:mm:ss");
                        LocalTime horario = LocalTime.parse(hora,hour);
                        System.out.println("Escreva uma breve descrição: ");
                        String descricao = scanner.next();
                        System.out.println("Digite o local: ");
                        String local = scanner.next();
                        System.out.println("Digite o tipo da agenda: ");
                        String tipoAgenda = scanner.next();
                        cadComp.delete(new Compromisso(date, horario, descricao, local, tipoAgenda));
                        System.out.println("Compromisso deletado com  sucesso");
                    break;
                    case 2:
                        
                    break;
                    case 3:
                        System.exit(0);
                    break;
                }
                }
           
            else{
                
                System.out.println("Deseja se cadastrar?");
                System.out.println("Digite: \n1 > para cadastro\n2 > para outra opção\n3 > para sair");
                escolha = scanner.nextInt();
                switch(escolha){
                    case 1:
                        
                        //Lendo Valores de cadastro do Usuário
                        System.out.println("Digite seu email: ");
                        email = scanner.next();
                        System.out.println("Digite sua senha: ");
                        senha = scanner.next();
                        System.out.println("Digite seu nome: ");
                        String nome = scanner.next();
                        System.out.println("Digite sua data de nascimento: ");
                        String nascimento = scanner.next();
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate date = LocalDate.parse(nascimento,dtf);
                        System.out.println("Digite a inicial do sexo: ");
                        char sexo = scanner.next().charAt(0);
                        cadUser.create(usuario = new Usuario(nome, date, sexo, email, senha));
                         
                            System.out.println("\nUsuário cadastrado com sucesso\n");
                        break;
                    case 2:
                        break;
                    case 3:
                        System.exit(0);
                        break;
                }
                System.out.println("Deseja Recuperar os dados de sua Conta?");
                System.out.println("Digite: \n1 > para recuperar\n2 > para outra opção\n3 > para sair");
                escolha = scanner.nextInt();
                switch(escolha){
                    case 1:
                        System.out.println("Por Favor Digite seu email para recuperação:");
                        email = scanner.next();
                        System.out.println("Seus dados são:" + cadUser.read(email));
                    break;
                    case 2:
                       
                    break;
                    case 3:
                         System.exit(0); 
                    break;
                    
                }
                System.out.println("Deseja Atualizar Sua Conta?");
                System.out.println("Digite: \n1 > para Atualizar\n2 > para outra opção\n3 > para sair");
                escolha = scanner.nextInt();
                switch(escolha){
                    case 1:
                        System.out.println("Por Favor Digite o email que está vinculado ao seu cadastro:");
                        email = scanner.next();
                        
                        System.out.println("Digite sua nova senha: ");
                        senha = scanner.next();
                        System.out.println("Digite seu novo nome: ");
                        String nome = scanner.next();
                        System.out.println("Digite sua data de nascimento atualizada: ");
                        String nascimento = scanner.next();
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate date = LocalDate.parse(nascimento,dtf);
                        System.out.println("Digite a inicial do seu sexo: ");
                        char sexo = scanner.next().charAt(0);
                        cadUser.update(usuario = new Usuario(nome, date, sexo, email, senha));
                        
                        System.out.println("\nUsuário atualizado com sucesso\n");
                        System.out.println(usuario);
                        break;
                    case 2:
                        break;
                    case 3:
                    System.exit(0);
                        break;
                }
                System.out.println("Deseja Excluir sua Conta?");
                System.out.println("Digite: \n1 > para excluir\n2 > para outra opção\n3 > para sair");
                escolha = scanner.nextInt();
                switch(escolha){
                    case 1:
                        System.out.println("Para Excluir seu cadastro será necessario informar seus dados:");
                        System.out.println("Digite seu email: ");
                        email = scanner.next();
                        System.out.println("Digite sua senha: ");
                        senha = scanner.next();
                        System.out.println("Digite seu nome: ");
                        String nome = scanner.next();
                        System.out.println("Digite sua data de nascimento: ");
                        String nascimento = scanner.next();
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate date = LocalDate.parse(nascimento,dtf);
                        System.out.println("Digite a inicial do sexo: ");
                        char sexo = scanner.next().charAt(0);
                        cadUser.delete(usuario = new Usuario(nome, date, sexo, email, senha));
                        System.out.println("Cadastro Deletado com sucesso");
                    break;
                    case 2:
                        
                    break;
                    case 3:
                    System.exit(0);
                    break;
                }
            }  
            
            System.out.println("Tela de Login");
            System.out.println("Digite seu email: ");
            email = scanner.next();
            System.out.println("Digite sua senha: ");
            senha = scanner.next();
    
        }       
    }
    
}

