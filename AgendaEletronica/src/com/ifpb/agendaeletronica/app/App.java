
package com.ifpb.agendaeletronica.app;

import com.ifpb.agendaeletronica.cadastro.UsuarioDao;
import com.ifpb.agendaeletronica.entidades.Agenda;
import com.ifpb.agendaeletronica.entidades.Compromisso;
import com.ifpb.agendaeletronica.entidades.Usuario;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class App {
   
    public static void main(String[] args) {
    
        UsuarioDao cadUser = new UsuarioDao();
        
        cadUser.create(new Usuario("Jozimar",LocalDate.of(2017, 2, 30),'M', "jozimar@gmail.com", "12345"));
//        cadUser.read("jozimar@gmail.com").createAgenda(new Agenda("estudos"));
//        cadUser.read("jozimar@gmail.com").createAgenda(new Agenda("diversao"));
//        cadUser.read("jozimar@gmail.com").createAgenda(new Agenda("trabalho"));
//        cadUser.read("jozimar@gmail.com").readAgenda("estudos").createCompromisso(new Compromisso(LocalDate.of(2017, 04, 11), LocalTime.of(12, 10), "PDS","casa"));
//        cadUser.read("jozimar@gmail.com").readAgenda("estudos").createCompromisso(new Compromisso(LocalDate.of(2017, 04, 12), LocalTime.of(12, 5), "banco","casa"));
//        cadUser.read("jozimar@gmail.com").readAgenda("diversao").createCompromisso(new Compromisso(LocalDate.of(2017, 04, 12), LocalTime.of(12, 8), "jogos","casa"));
//        cadUser.read("jozimar@gmail.com").readAgenda("trabalho").createCompromisso(new Compromisso(LocalDate.of(2017, 04, 25), LocalTime.of(12, 17), "plantar","roça"));
//        cadUser.read("jozimar@gmail.com").readAgenda("estudos").createCompromisso(new Compromisso(LocalDate.of(2017, 05, 11), LocalTime.of(12, 22), "POO","casa"));
//        System.out.println(cadUser.read("jozimar@gmail.com").ListarCompromissosAgendas());
        //System.out.println(cadUser.read("jozimar@gmail.com").compromissosProx30DaysAgendasUser());
        //System.out.println(cadUser.read("ozimarsoares@gmailcom").readAgenda("estudos").compromissosEntreIntervalo(LocalDate.now(), LocalDate.of(2017, 05, 11)));
    }  
}       
      


