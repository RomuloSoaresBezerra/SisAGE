/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.agendaeletronica.interfacegrafica;

import com.ifpb.agendaeletronica.cadastro.AgendaDaoBanco;
import com.ifpb.agendaeletronica.cadastro.AgendaDaoBinario;
import com.ifpb.agendaeletronica.cadastro.CompromissoDaoBanco;
import com.ifpb.agendaeletronica.cadastro.CompromissoDaoBinario;
import com.ifpb.agendaeletronica.cadastro.UsuarioDaoBanco;
import com.ifpb.agendaeletronica.cadastro.UsuarioDaoBinario;
import com.ifpb.agendaeletronica.entidades.Agenda;
import com.ifpb.agendaeletronica.entidades.Compromisso;
import com.ifpb.agendaeletronica.entidades.Usuario;
import com.ifpb.agendaeletronica.interfaces.AgendaDao;
import com.ifpb.agendaeletronica.interfaces.UsuarioDao;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Familia
 */
public class TelaInicial extends javax.swing.JFrame {

    /**
     * Creates new form TelaInicial
     */
    public static Usuario usuarioLogado;
    private UsuarioDao dao;
    private static AgendaDao dao2;
    private CompromissoDaoBinario compromissoDao;
    public TelaInicial(Usuario u) {
        dao = new UsuarioDaoBinario();
        dao2 = new AgendaDaoBinario();
        usuarioLogado = u;
        compromissoDao = new CompromissoDaoBinario();

        initComponents();
        inicializarComponentes();

    }

    public static void inicializarComponentes() {
        List<String> vetor = new ArrayList<>();

        try {
            vetor = dao2.retornaNomeAgendas();
        } catch (IOException ex) {
            Logger.getLogger(TelaCompromisso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaCompromisso.class.getName()).log(Level.SEVERE, null, ex);
        }catch(SQLException ex){
             JOptionPane.showMessageDialog(null,
                    "já exite uma agenda com esse nome",
                    "Mensagem de Erro",
                    JOptionPane.ERROR_MESSAGE);
         }
        comboNomeAgenda.removeAllItems();
        comboNomeAgenda.addItem("Todas");
        for (int i = 0; i < vetor.size(); i++) {
            comboNomeAgenda.addItem(vetor.get(i));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        botaoCriarCompromisso = new javax.swing.JButton();
        botaoCriarAgendas = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        comboNomeAgenda = new javax.swing.JComboBox();
        botaoAtualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaInicial = new javax.swing.JTable();
        botaoGerenciarAgendas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Página Inicial");

        botaoCriarCompromisso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botaoCriarCompromisso.setText("Novo Compromisso");
        botaoCriarCompromisso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCriarCompromissoActionPerformed(evt);
            }
        });

        botaoCriarAgendas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botaoCriarAgendas.setText("Nova Agenda");
        botaoCriarAgendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCriarAgendasActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Gerenciar Compromissos");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Proximos Compromissos");

        comboNomeAgenda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboNomeAgenda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todas" }));
        comboNomeAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboNomeAgendaActionPerformed(evt);
            }
        });

        botaoAtualizar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botaoAtualizar.setText("Atualizar");
        botaoAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAtualizarActionPerformed(evt);
            }
        });

        tabelaInicial.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tabelaInicial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabelaInicial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data", "Hora", "Compromisso", "Local"
            }
        ));
        jScrollPane1.setViewportView(tabelaInicial);

        botaoGerenciarAgendas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botaoGerenciarAgendas.setText("Gerenciar Agenda");
        botaoGerenciarAgendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGerenciarAgendasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel2)
                        .addGap(42, 42, 42)
                        .addComponent(comboNomeAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(botaoAtualizar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(botaoCriarCompromisso)
                                    .addComponent(botaoCriarAgendas))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(70, 70, 70)
                                        .addComponent(jLabel1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(botaoGerenciarAgendas)
                                        .addGap(28, 28, 28)
                                        .addComponent(jButton3)))))))
                .addContainerGap(140, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoCriarAgendas, botaoCriarCompromisso, botaoGerenciarAgendas, jButton3});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoAtualizar, comboNomeAgenda});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1)
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoGerenciarAgendas, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(botaoCriarCompromisso, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoCriarAgendas, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboNomeAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoAtualizar))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoCriarCompromissoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCriarCompromissoActionPerformed
        TelaCompromisso telaCompromisso = new TelaCompromisso();
        telaCompromisso.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoCriarCompromissoActionPerformed

    private void botaoCriarAgendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCriarAgendasActionPerformed
        TelaAgenda telaAgenda = new TelaAgenda();
        telaAgenda.setVisible(true);
    }//GEN-LAST:event_botaoCriarAgendasActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        TelaGerenciarCompromisso telaGerenciarCompromisso = new TelaGerenciarCompromisso();
        telaGerenciarCompromisso.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void botaoAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtualizarActionPerformed

        

        List<Compromisso> compromissos30Dias = new ArrayList<>();
        try {
            for(Compromisso comp : compromissoDao.compIntervaloDataAgendas(
                    comboNomeAgenda.getSelectedItem().toString(), LocalDate.now(), 
                    LocalDate.now().plusDays(30))){
                if(comp.getEmail().equals(TelaInicial.usuarioLogado.getEmail())){
                    compromissos30Dias.add(comp);
                }
            }
           
            String[] titulos = {"Data", "Hora", "Compromisso", "Local"};
            String[][] tabela = new String[compromissos30Dias.size()][4];
            for (int i = 0; i < compromissos30Dias.size(); i++) {
                Compromisso comp = compromissos30Dias.get(i);
                tabela[i][0] = comp.getData().toString();
                tabela[i][1] = comp.getHora().toString();
                tabela[i][2] = comp.getDescricao();
                tabela[i][3] = comp.getLocal();

            }
            System.out.println(compromissos30Dias);
            tabelaInicial.removeAll();
            DefaultTableModel modelo = new DefaultTableModel(tabela, titulos);
            tabelaInicial.setModel(modelo);

        } catch (ClassNotFoundException | IOException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha na conexão");
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Sem compromissos");
        }
    }//GEN-LAST:event_botaoAtualizarActionPerformed

    private void comboNomeAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboNomeAgendaActionPerformed

    }//GEN-LAST:event_comboNomeAgendaActionPerformed

    private void botaoGerenciarAgendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerenciarAgendasActionPerformed
        TelaGerenciarAgenda telaGerenciarAgenda = new TelaGerenciarAgenda();
        telaGerenciarAgenda.setVisible(true);
    }//GEN-LAST:event_botaoGerenciarAgendasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaInicial(usuarioLogado).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAtualizar;
    private javax.swing.JButton botaoCriarAgendas;
    private javax.swing.JButton botaoCriarCompromisso;
    private javax.swing.JButton botaoGerenciarAgendas;
    private static javax.swing.JComboBox comboNomeAgenda;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaInicial;
    // End of variables declaration//GEN-END:variables
}