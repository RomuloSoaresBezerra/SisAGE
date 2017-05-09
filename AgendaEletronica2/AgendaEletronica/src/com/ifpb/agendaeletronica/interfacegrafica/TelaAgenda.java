/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.agendaeletronica.interfacegrafica;

import com.ifpb.agendaeletronica.cadastro.AgendaDaoBanco;
import com.ifpb.agendaeletronica.cadastro.AgendaDaoBinario;
import com.ifpb.agendaeletronica.cadastro.UsuarioDaoBanco;
import com.ifpb.agendaeletronica.cadastro.UsuarioDaoBinario;
import com.ifpb.agendaeletronica.entidades.Agenda;
import com.ifpb.agendaeletronica.entidades.Usuario;
import com.ifpb.agendaeletronica.excecoes.NomeInvalidoException;
import com.ifpb.agendaeletronica.interfaces.AgendaDao;
import com.ifpb.agendaeletronica.interfaces.UsuarioDao;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author romulo
 */
public class TelaAgenda extends javax.swing.JFrame {

    /**
     * Creates new form TelaAgenda
     */
    private UsuarioDao dao;
    private AgendaDao dao2;
    public TelaAgenda() {
        dao = new UsuarioDaoBinario();
        dao2 = new AgendaDaoBinario();
        initComponents();
        
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
        campoNomeAgenda = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        botaoCriarAgenda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Nova Agenda");

        campoNomeAgenda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        campoNomeAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNomeAgendaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nome");

        botaoCriarAgenda.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botaoCriarAgenda.setText("Criar");
        botaoCriarAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCriarAgendaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(campoNomeAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(botaoCriarAgenda)))))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(campoNomeAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(41, 41, 41)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(botaoCriarAgenda)
                .addGap(65, 65, 65))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoNomeAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNomeAgendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNomeAgendaActionPerformed

    private void botaoCriarAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCriarAgendaActionPerformed
       
        Agenda agenda = new Agenda();
        
        try {
            
            agenda.setNomeAgenda(campoNomeAgenda.getText());
            agenda.setEmail(TelaInicial.usuarioLogado.getEmail());

                if (dao2.createAgenda(agenda)) {
                    
                    JOptionPane.showMessageDialog(null,
                            "Agenda Cadastrada Com Sucesso");
                    TelaInicial.inicializarComponentes();
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Já Existe Uma Agenda Com Esse Nome",
                            "Mensagem de Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,
                    "Falha na Conexão",
                    "Mensagem de Erro",
                    JOptionPane.ERROR_MESSAGE);
        } catch(NomeInvalidoException ex){
            JOptionPane.showMessageDialog(null,
                    "Nome não pode ser vazio", "Mensagem de erro",
                    JOptionPane.ERROR_MESSAGE);
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null,
                    "Já existe uma agenda com esse nome",
                    "Mensagem de Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
               
    }//GEN-LAST:event_botaoCriarAgendaActionPerformed
   
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
            java.util.logging.Logger.getLogger(TelaAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaAgenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCriarAgenda;
    private javax.swing.JTextField campoNomeAgenda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
