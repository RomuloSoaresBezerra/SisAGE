/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.agendaeletronica.visao;

import com.ifpb.agendaeletronica.controle.CompromissoDaoBanco;
import com.ifpb.agendaeletronica.excecoes.IntervaloInvalidoException;
import com.ifpb.agendaeletronica.controle.CompromissoDaoBinario;
import com.ifpb.agendaeletronica.controle.UsuarioDaoBanco;
import com.ifpb.agendaeletronica.controle.UsuarioDaoBinario;
import com.ifpb.agendaeletronica.modelo.Compromisso;
import com.ifpb.agendaeletronica.interfaces.UsuarioDao;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Familia
 */
public class TelaGerenciarCompromisso extends javax.swing.JFrame {

    /**
     * Creates new form TelaGerenciarCompromisso
     */
    private static UsuarioDao dao;
    private CompromissoDaoBanco compromissoDao;

    public TelaGerenciarCompromisso() {
        compromissoDao = new CompromissoDaoBanco();
        dao = new UsuarioDaoBinario();
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

        jColorChooser1 = new javax.swing.JColorChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        campoDataInicio = new com.toedter.calendar.JDateChooser();
        campoDataFim = new com.toedter.calendar.JDateChooser();
        botaoAtuliazar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCompromisso = new javax.swing.JTable();
        botaoEditarCompromisso = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(java.awt.Color.white);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Gerenciar Compromissos");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Início");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Fim");

        campoDataInicio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        campoDataFim.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        botaoAtuliazar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        botaoAtuliazar.setText("Atualizar");
        botaoAtuliazar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAtuliazarActionPerformed(evt);
            }
        });

        tabelaCompromisso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tabelaCompromisso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabelaCompromisso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data", "Hora", "Compromisso", "Local"
            }
        ));
        tabelaCompromisso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaCompromissoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaCompromisso);

        botaoEditarCompromisso.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        botaoEditarCompromisso.setText("Editar");
        botaoEditarCompromisso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEditarCompromissoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(botaoAtuliazar)
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botaoEditarCompromisso, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(157, 157, 157))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel3});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoDataInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campoDataFim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botaoAtuliazar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(49, 49, 49)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(botaoEditarCompromisso, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoAtuliazarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAtuliazarActionPerformed

        try {
            LocalDate dataInicio = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(campoDataInicio.getDate()));
            LocalDate dataFim = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(campoDataFim.getDate()));

            List<Compromisso> compromissos = new ArrayList<>();
            if (dataFim.isBefore(dataInicio)) {
                throw new IntervaloInvalidoException("Interevalo Invalido");
            }
            for (Compromisso comp : compromissoDao.compIntervaloDataAgendas(
                    "Todas", dataInicio,
                    dataFim)) {
                if (comp.getEmail().equals(TelaInicial.usuarioLogado.getEmail())) {
                    compromissos.add(comp);
                }
            }

            String[] titulos = {"Data", "Hora", "Compromisso", "Local"};
            String[][] tabela = new String[compromissos.size()][4];
            for (int i = 0; i < compromissos.size(); i++) {
                Compromisso comp = compromissos.get(i);
                tabela[i][0] = comp.getData().toString();
                tabela[i][1] = comp.getHora().toString();
                tabela[i][2] = comp.getDescricao();
                tabela[i][3] = comp.getLocal();

            }

            tabelaCompromisso.removeAll();
            DefaultTableModel modelo = new DefaultTableModel(tabela, titulos);
            tabelaCompromisso.setModel(modelo);

        } catch (ClassNotFoundException | IOException | SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Falha na Conexão",
                    "Mensagem de Erro",
                    JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Sem Compromissos Neste Intervalo");
            JOptionPane.showMessageDialog(null, "Verifique as Datas Informadas");
        } catch (IntervaloInvalidoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }//GEN-LAST:event_botaoAtuliazarActionPerformed

    private void botaoEditarCompromissoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEditarCompromissoActionPerformed
        if (tabelaCompromisso.getSelectedRow() != -1) {
            try {
                LocalDate dataInicio = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(campoDataInicio.getDate()));
                LocalDate dataFim = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(campoDataFim.getDate()));
                List<Compromisso> compromissos = new ArrayList<>();
                for (Compromisso comp : compromissoDao.compIntervaloDataAgendas(
                        "Todas", dataInicio,
                        dataFim)) {
                    if (comp.getEmail().equals(TelaInicial.usuarioLogado.getEmail())) {
                        compromissos.add(comp);
                    }
                }
                TelaEditarCompromisso telaEditarCompromisso = new TelaEditarCompromisso(compromissos.get(tabelaCompromisso.getSelectedRow()));
                telaEditarCompromisso.setVisible(true);
            } catch (ClassNotFoundException | IOException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Falha na conexão");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum compromisso selecionado");
        }


    }//GEN-LAST:event_botaoEditarCompromissoActionPerformed

    private void tabelaCompromissoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCompromissoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaCompromissoMouseClicked

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
            java.util.logging.Logger.getLogger(TelaGerenciarCompromisso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaGerenciarCompromisso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaGerenciarCompromisso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaGerenciarCompromisso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaGerenciarCompromisso().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAtuliazar;
    private javax.swing.JButton botaoEditarCompromisso;
    private com.toedter.calendar.JDateChooser campoDataFim;
    private com.toedter.calendar.JDateChooser campoDataInicio;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private static javax.swing.JTable tabelaCompromisso;
    // End of variables declaration//GEN-END:variables
}
