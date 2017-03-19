package glen;

import glen.finance.*;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class System_Admin extends javax.swing.JFrame {
    //variable for connect database
    Connection conn=DBconnect.ConnectDB();
    PreparedStatement pstatement=null;
    ResultSet Results =null;
    DBAccess data=new DBAccess();
    
    static String USER;
   
    public System_Admin(String username) {
        
        USER=username;
        initComponents();
        this.setLocationRelativeTo(null); //Setting to display in the center of screen
        clock();
        data.adduserlogs(USER, "System Login", "Login",00000);
        
        welcomeuser.setText(USER);
        togglePanels.setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jDesktopPaneFinance = new javax.swing.JDesktopPane();
        financeManagerPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        welcomeuser = new javax.swing.JLabel();
        welcomeuser1 = new javax.swing.JLabel();
        Time = new javax.swing.JLabel();
        togglePanels = new javax.swing.JPanel();
        buttonIncome = new javax.swing.JButton();
        buttonIncome1 = new javax.swing.JButton();
        buttonIncome2 = new javax.swing.JButton();
        buttonIncome3 = new javax.swing.JButton();
        buttonIncome4 = new javax.swing.JButton();
        buttonIncome5 = new javax.swing.JButton();
        buttonIncome6 = new javax.swing.JButton();
        buttonIncome7 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Glen Hotel Management System");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        setSize(new java.awt.Dimension(1020, 700));

        jDesktopPaneFinance.setBackground(new java.awt.Color(255, 255, 255));
        jDesktopPaneFinance.setForeground(new java.awt.Color(255, 255, 255));
        jDesktopPaneFinance.setPreferredSize(new java.awt.Dimension(1024, 690));
        jDesktopPaneFinance.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        financeManagerPanel.setBackground(new java.awt.Color(255, 255, 255));
        financeManagerPanel.setPreferredSize(new java.awt.Dimension(1024, 690));
        financeManagerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        headerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2));
        headerPanel.setLayout(null);

        welcomeuser.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        welcomeuser.setForeground(new java.awt.Color(0, 153, 255));
        welcomeuser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeuser.setText("Name");
        headerPanel.add(welcomeuser);
        welcomeuser.setBounds(820, 40, 170, 30);

        welcomeuser1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        welcomeuser1.setForeground(new java.awt.Color(0, 153, 255));
        welcomeuser1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeuser1.setText("WELCOME ");
        headerPanel.add(welcomeuser1);
        welcomeuser1.setBounds(820, 10, 170, 30);

        Time.setFont(new java.awt.Font("Electrofied", 0, 12)); // NOI18N
        Time.setForeground(new java.awt.Color(51, 153, 255));
        Time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Time.setText("Time");
        headerPanel.add(Time);
        Time.setBounds(790, 100, 230, 20);

        financeManagerPanel.add(headerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1024, 130));

        togglePanels.setBackground(new java.awt.Color(255, 255, 255));
        togglePanels.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235), 2));
        togglePanels.setPreferredSize(new java.awt.Dimension(832, 552));

        buttonIncome.setBackground(new java.awt.Color(42, 135, 235));
        buttonIncome.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        buttonIncome.setForeground(new java.awt.Color(255, 255, 255));
        buttonIncome.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Income.png")); // NOI18N
        buttonIncome.setText("STOCK MANAGEMENT");
        buttonIncome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonIncome.setMargin(new java.awt.Insets(2, 4, 2, 0));
        buttonIncome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonIncomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonIncomeMouseExited(evt);
            }
        });
        buttonIncome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIncomeActionPerformed(evt);
            }
        });

        buttonIncome1.setBackground(new java.awt.Color(42, 135, 235));
        buttonIncome1.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        buttonIncome1.setForeground(new java.awt.Color(255, 255, 255));
        buttonIncome1.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Income.png")); // NOI18N
        buttonIncome1.setText("EVENT MANAGEMENT");
        buttonIncome1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonIncome1.setMargin(new java.awt.Insets(2, 4, 2, 0));
        buttonIncome1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonIncome1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonIncome1MouseExited(evt);
            }
        });
        buttonIncome1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIncome1ActionPerformed(evt);
            }
        });

        buttonIncome2.setBackground(new java.awt.Color(42, 135, 235));
        buttonIncome2.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        buttonIncome2.setForeground(new java.awt.Color(255, 255, 255));
        buttonIncome2.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Income.png")); // NOI18N
        buttonIncome2.setText("RESTAURANT MANAGEMENT");
        buttonIncome2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonIncome2.setMargin(new java.awt.Insets(2, 4, 2, 0));
        buttonIncome2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonIncome2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonIncome2MouseExited(evt);
            }
        });
        buttonIncome2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIncome2ActionPerformed(evt);
            }
        });

        buttonIncome3.setBackground(new java.awt.Color(42, 135, 235));
        buttonIncome3.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        buttonIncome3.setForeground(new java.awt.Color(255, 255, 255));
        buttonIncome3.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Income.png")); // NOI18N
        buttonIncome3.setText("HR MANAGEMENT");
        buttonIncome3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonIncome3.setMargin(new java.awt.Insets(2, 4, 2, 0));
        buttonIncome3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonIncome3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonIncome3MouseExited(evt);
            }
        });
        buttonIncome3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIncome3ActionPerformed(evt);
            }
        });

        buttonIncome4.setBackground(new java.awt.Color(42, 135, 235));
        buttonIncome4.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        buttonIncome4.setForeground(new java.awt.Color(255, 255, 255));
        buttonIncome4.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Income.png")); // NOI18N
        buttonIncome4.setText("ROOM MANAGEMENT");
        buttonIncome4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonIncome4.setMargin(new java.awt.Insets(2, 4, 2, 0));
        buttonIncome4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonIncome4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonIncome4MouseExited(evt);
            }
        });
        buttonIncome4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIncome4ActionPerformed(evt);
            }
        });

        buttonIncome5.setBackground(new java.awt.Color(42, 135, 235));
        buttonIncome5.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        buttonIncome5.setForeground(new java.awt.Color(255, 255, 255));
        buttonIncome5.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Income.png")); // NOI18N
        buttonIncome5.setText("TRANSPORT MANAGEMENT");
        buttonIncome5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonIncome5.setMargin(new java.awt.Insets(2, 4, 2, 0));
        buttonIncome5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonIncome5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonIncome5MouseExited(evt);
            }
        });
        buttonIncome5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIncome5ActionPerformed(evt);
            }
        });

        buttonIncome6.setBackground(new java.awt.Color(42, 135, 235));
        buttonIncome6.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        buttonIncome6.setForeground(new java.awt.Color(255, 255, 255));
        buttonIncome6.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Income.png")); // NOI18N
        buttonIncome6.setText("FINANCE MANAGEMENT");
        buttonIncome6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonIncome6.setMargin(new java.awt.Insets(2, 4, 2, 0));
        buttonIncome6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonIncome6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonIncome6MouseExited(evt);
            }
        });
        buttonIncome6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIncome6ActionPerformed(evt);
            }
        });

        buttonIncome7.setBackground(new java.awt.Color(42, 135, 235));
        buttonIncome7.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        buttonIncome7.setForeground(new java.awt.Color(255, 255, 255));
        buttonIncome7.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Income.png")); // NOI18N
        buttonIncome7.setText("SYSTEM SETTINGS");
        buttonIncome7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonIncome7.setMargin(new java.awt.Insets(2, 4, 2, 0));
        buttonIncome7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonIncome7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonIncome7MouseExited(evt);
            }
        });
        buttonIncome7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIncome7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout togglePanelsLayout = new javax.swing.GroupLayout(togglePanels);
        togglePanels.setLayout(togglePanelsLayout);
        togglePanelsLayout.setHorizontalGroup(
            togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(togglePanelsLayout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonIncome3, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonIncome1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonIncome2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
                .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonIncome7, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonIncome5, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonIncome6, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonIncome4, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(115, 115, 115))
        );
        togglePanelsLayout.setVerticalGroup(
            togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(togglePanelsLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(togglePanelsLayout.createSequentialGroup()
                        .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonIncome1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonIncome4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addComponent(buttonIncome2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(buttonIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(buttonIncome3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(togglePanelsLayout.createSequentialGroup()
                        .addComponent(buttonIncome5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(buttonIncome6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(buttonIncome7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        financeManagerPanel.add(togglePanels, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 135, 1024, -1));

        jDesktopPaneFinance.add(financeManagerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPaneFinance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPaneFinance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonIncomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonIncomeMouseEntered
        buttonIncome.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonIncomeMouseEntered

    private void buttonIncomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonIncomeMouseExited
        buttonIncome.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_buttonIncomeMouseExited

    private void buttonIncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIncomeActionPerformed
 
    }//GEN-LAST:event_buttonIncomeActionPerformed

    private void buttonIncome1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonIncome1MouseEntered
        buttonIncome1.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonIncome1MouseEntered

    private void buttonIncome1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonIncome1MouseExited
         buttonIncome1.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_buttonIncome1MouseExited

    private void buttonIncome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIncome1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonIncome1ActionPerformed

    private void buttonIncome2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonIncome2MouseEntered
        buttonIncome2.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonIncome2MouseEntered

    private void buttonIncome2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonIncome2MouseExited
        buttonIncome2.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_buttonIncome2MouseExited

    private void buttonIncome2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIncome2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonIncome2ActionPerformed

    private void buttonIncome3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonIncome3MouseEntered
        buttonIncome3.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonIncome3MouseEntered

    private void buttonIncome3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonIncome3MouseExited
        buttonIncome3.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_buttonIncome3MouseExited

    private void buttonIncome3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIncome3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonIncome3ActionPerformed

    private void buttonIncome4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonIncome4MouseEntered
        buttonIncome4.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonIncome4MouseEntered

    private void buttonIncome4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonIncome4MouseExited
        buttonIncome4.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_buttonIncome4MouseExited

    private void buttonIncome4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIncome4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonIncome4ActionPerformed

    private void buttonIncome5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonIncome5MouseEntered
         buttonIncome4.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonIncome5MouseEntered

    private void buttonIncome5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonIncome5MouseExited
        buttonIncome5.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_buttonIncome5MouseExited

    private void buttonIncome5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIncome5ActionPerformed
        
    }//GEN-LAST:event_buttonIncome5ActionPerformed

    private void buttonIncome6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonIncome6MouseEntered
        buttonIncome6.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonIncome6MouseEntered

    private void buttonIncome6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonIncome6MouseExited
        buttonIncome6.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_buttonIncome6MouseExited

    private void buttonIncome6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIncome6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonIncome6ActionPerformed

    private void buttonIncome7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonIncome7MouseEntered
        buttonIncome7.setBackground(new Color(35,171,69));

    }//GEN-LAST:event_buttonIncome7MouseEntered

    private void buttonIncome7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonIncome7MouseExited
        buttonIncome7.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_buttonIncome7MouseExited

    private void buttonIncome7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIncome7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonIncome7ActionPerformed
    public void clock(){
        Thread clock = new Thread(){
            public void run(){
                try{
                    for(;;){
                    Calendar cal=new GregorianCalendar();
                    Time.setText(cal.get(Calendar.HOUR)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND)+" / "+cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DATE));
                    sleep(1000);
                    }
                }catch(Exception e){ 
                    
                }
            }
        };
        clock.start();
    }
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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Finance_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Finance_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Finance_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Finance_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Finance_Main(USER).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JLabel Time;
    javax.swing.JButton buttonIncome;
    javax.swing.JButton buttonIncome1;
    javax.swing.JButton buttonIncome2;
    javax.swing.JButton buttonIncome3;
    javax.swing.JButton buttonIncome4;
    javax.swing.JButton buttonIncome5;
    javax.swing.JButton buttonIncome6;
    javax.swing.JButton buttonIncome7;
    javax.swing.JPanel financeManagerPanel;
    javax.swing.JPanel headerPanel;
    javax.swing.JDesktopPane jDesktopPaneFinance;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JTable jTable1;
    javax.swing.JPanel togglePanels;
    javax.swing.JLabel welcomeuser;
    javax.swing.JLabel welcomeuser1;
    // End of variables declaration//GEN-END:variables
}