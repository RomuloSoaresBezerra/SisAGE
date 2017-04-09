
package com.ifpb.agendaeletronica.app;

import com.ifpb.agendaeletronica.cadastro.CompromissoDao;
import com.ifpb.agendaeletronica.cadastro.UsuarioDao;
import com.ifpb.agendaeletronica.entidades.Compromisso;
import com.ifpb.agendaeletronica.entidades.Usuario;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {

   
    public static void main(String[] args) {
        
        UsuarioDao cadUser = new UsuarioDao();
        CompromissoDao cadComp = new CompromissoDao();
        Scanner scanner = new Scanner(System.in);
        int escolha;
        
        while(true){
            
            System.out.println("Tela de Login");
            System.out.println("Digite seu email: ");
            String email = scanner.nextLine();
            System.out.println("Digite sua senha: ");
            String senha = scanner.nextLine();
            
            //Ao invés de 'true' é o metodo autenticarUsuario();
            if(true){
                System.out.println("Você entrou com sucesso!");
                System.out.println("\n1 > para adicionar um novo compromisso?");
                escolha = scanner.nextInt();
                if(escolha==1){
                    System.out.println("Digite a data do compromisso: ");
                    String data = scanner.next();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("DD/mm/yyyy");
                    LocalDate date = LocalDate.parse(data,dtf);
                    System.out.println("Digitea a hora do compromisso: ");
                    String hora = scanner.next();
                    DateTimeFormatter hour = DateTimeFormatter.ofPattern("hh:mm");
                    LocalTime horario = LocalTime.parse(hora,hour);
                    System.out.println("Escreva uma breve descrição: ");
                    String descricao = scanner.next();
                    System.out.println("Digite o local: ");
                    String local = scanner.next();
                    System.out.println("Digite o tipo da agenda: ");
                    String tipoAgenda = scanner.next();
                    
                    Compromisso compromisso = new Compromisso(date, horario, descricao, local, tipoAgenda);
                    if(cadComp.create(compromisso))
                        System.out.println("Compromisso agendado com sucesso!");    
                }
            }
            
            else{
                System.out.println("Deseja se cadastrar?");
                System.out.println("Digite: \n1 > para cadastro\n2 > para sair");
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
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("DD/mm/yyyy");
                        LocalDate date = LocalDate.parse(nascimento,dtf);
                        System.out.println("Digite o a inicial do sexo: ");
                        char sexo = scanner.next().charAt(0);
                        Usuario usuario = new Usuario(nome, date, sexo, email, senha);
                     
                        if(cadUser.create(usuario)) 
                            System.out.println("\nUsuário cadastrado com sucesso\n");
                        break;
                    case 2:
                        System.exit(0);
                        break;
                }  
            }    
        }       
    }    
}