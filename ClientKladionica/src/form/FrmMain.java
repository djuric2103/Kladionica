/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import domain.User;
import form.businessresults.FrmBusinessResults;
import form.match.FrmMatch;
import form.ticket.FrmTicket;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import session.Session;
import usecase.UseCase;

/**
 *
 * @author not-sure
 */
public class FrmMain extends javax.swing.JFrame {
    private JFrame parent;
    private List<JDialog> forms = new LinkedList<>();
    /**
     * Creates new form FrmMain
     */
    public FrmMain(JFrame parent) {
        this.parent = parent;
        initComponents();
        setTitle("Main form");
        setScreenCenter();
        populateCurrentUser();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        pnlCurrnetUser = new javax.swing.JPanel();
        lblCurrentUser = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmTicket = new javax.swing.JMenu();
        jmiNewTicket = new javax.swing.JMenuItem();
        jmiDeleteTicket = new javax.swing.JMenuItem();
        jmiCheckTicket = new javax.swing.JMenuItem();
        jmiPayTicket = new javax.swing.JMenuItem();
        jmMatch = new javax.swing.JMenu();
        jmiNewMatch = new javax.swing.JMenuItem();
        jmiInsertResult = new javax.swing.JMenuItem();
        jmReport = new javax.swing.JMenu();
        jmiNewReport = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar2.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar2.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlCurrnetUser.setBorder(javax.swing.BorderFactory.createTitledBorder("Current User"));

        javax.swing.GroupLayout pnlCurrnetUserLayout = new javax.swing.GroupLayout(pnlCurrnetUser);
        pnlCurrnetUser.setLayout(pnlCurrnetUserLayout);
        pnlCurrnetUserLayout.setHorizontalGroup(
            pnlCurrnetUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCurrnetUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCurrentUser, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlCurrnetUserLayout.setVerticalGroup(
            pnlCurrnetUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCurrnetUserLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblCurrentUser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jmTicket.setText("Ticket");

        jmiNewTicket.setText("New");
        jmiNewTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNewTicketActionPerformed(evt);
            }
        });
        jmTicket.add(jmiNewTicket);

        jmiDeleteTicket.setText("Delete");
        jmiDeleteTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiDeleteTicketActionPerformed(evt);
            }
        });
        jmTicket.add(jmiDeleteTicket);

        jmiCheckTicket.setText("Check");
        jmiCheckTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCheckTicketActionPerformed(evt);
            }
        });
        jmTicket.add(jmiCheckTicket);

        jmiPayTicket.setText("Pay");
        jmiPayTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPayTicketActionPerformed(evt);
            }
        });
        jmTicket.add(jmiPayTicket);

        jMenuBar1.add(jmTicket);

        jmMatch.setText("Match");

        jmiNewMatch.setText("New");
        jmiNewMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNewMatchActionPerformed(evt);
            }
        });
        jmMatch.add(jmiNewMatch);

        jmiInsertResult.setText("Insert Score");
        jmiInsertResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiInsertResultActionPerformed(evt);
            }
        });
        jmMatch.add(jmiInsertResult);

        jMenuBar1.add(jmMatch);

        jmReport.setText("Bussiness Results");

        jmiNewReport.setText("View");
        jmiNewReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNewReportActionPerformed(evt);
            }
        });
        jmReport.add(jmiNewReport);

        jMenuBar1.add(jmReport);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCurrnetUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnLogout)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(pnlCurrnetUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiNewMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNewMatchActionPerformed
       JDialog frmMatch = new FrmMatch(this, true, FrmMode.NEW);
       
       frmMatch.setVisible(true);
       forms.add(frmMatch);
       Session.getInstance().setCurrentUseCase(UseCase.UC_NEW_MATCH);
    }//GEN-LAST:event_jmiNewMatchActionPerformed

    private void jmiDeleteTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiDeleteTicketActionPerformed
        JDialog frmTicket = new FrmTicket(this, true, FrmMode.DELETE);
       
        forms.add(frmTicket);
        frmTicket.setVisible(true);
        Session.getInstance().setCurrentUseCase(UseCase.UC_DELETE_TICKET);
    }//GEN-LAST:event_jmiDeleteTicketActionPerformed

    private void jmiCheckTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCheckTicketActionPerformed
        JDialog frmTicket = new FrmTicket(this, true, FrmMode.CHECK);
        frmTicket.setVisible(true);
        
        Session.getInstance().setCurrentUseCase(UseCase.UC_CHECK_TICKET);
    }//GEN-LAST:event_jmiCheckTicketActionPerformed

    private void jmiPayTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPayTicketActionPerformed
        JDialog frmTicket = new FrmTicket(this, true, FrmMode.PAYOFF);
        frmTicket.setVisible(true);
        forms.add(frmTicket);
        Session.getInstance().setCurrentUseCase(UseCase.UC_PAY_TICKET);
    }//GEN-LAST:event_jmiPayTicketActionPerformed

    private void jmiInsertResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiInsertResultActionPerformed
        JDialog frmMatch = new FrmMatch(this, true, FrmMode.INSERTRESULT);
        frmMatch.setVisible(true);
        forms.add(frmMatch);
        Session.getInstance().setCurrentUseCase(UseCase.UC_INSERT_MATCH_RESULT);
    }//GEN-LAST:event_jmiInsertResultActionPerformed

    private void jmiNewTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNewTicketActionPerformed
        JDialog frmTicket = new FrmTicket(this, true, FrmMode.NEW);
        frmTicket.setVisible(true);
        forms.add(frmTicket);
        Session.getInstance().setCurrentUseCase(UseCase.UC_NEW_TICKET);
    }//GEN-LAST:event_jmiNewTicketActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        parent.setVisible(true);
        dispose();
        Session.getInstance().setCurreUser(null);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void jmiNewReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNewReportActionPerformed
        JDialog frmBusinessResults = new FrmBusinessResults(this, true, FrmMode.VIEW);
        frmBusinessResults.setVisible(true);
        forms.add(frmBusinessResults);
        Session.getInstance().setCurrentUseCase(UseCase.UC_VIEW_BUSINESS_RESULTS);
    }//GEN-LAST:event_jmiNewReportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenu jmMatch;
    private javax.swing.JMenu jmReport;
    private javax.swing.JMenu jmTicket;
    private javax.swing.JMenuItem jmiCheckTicket;
    private javax.swing.JMenuItem jmiDeleteTicket;
    private javax.swing.JMenuItem jmiInsertResult;
    private javax.swing.JMenuItem jmiNewMatch;
    private javax.swing.JMenuItem jmiNewReport;
    private javax.swing.JMenuItem jmiNewTicket;
    private javax.swing.JMenuItem jmiPayTicket;
    private javax.swing.JLabel lblCurrentUser;
    private javax.swing.JPanel pnlCurrnetUser;
    // End of variables declaration//GEN-END:variables

    private void setScreenCenter() {
        setLocationRelativeTo(null);
    }
    
    private void populateCurrentUser() {
        User u = Session.getInstance().getCurreUser();
        lblCurrentUser.setText(u.toString());
    }

    void shutDown() {
        for (JDialog forma : forms) {
            if (forma != null) {
                forma.dispose();
            }
        }
        dispose();
    }
}
