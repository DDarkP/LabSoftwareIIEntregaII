/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package AdminView;

import ArticlesView.ArticleService;
import ArticlesView.VtnListarArticulos;
import ConferenceView.VtnListarConferencias;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public final class VtnPrincipalAdmin extends javax.swing.JFrame {

    private VtnListarArticulos objVtnListarArticulos;
    private VtnListarConferencias objVtnListarConferencias;
    private ArticleService objArticleService;
    
    public VtnPrincipalAdmin() {
        initComponents();
        establecerIconoOrganización();
        asociarServiciosAlmacenamiento(objArticleService);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void asociarServiciosAlmacenamiento(ArticleService servicioArticulo) {

        this.objArticleService = servicioArticulo;
        relacionarInternalFrameConJdesptokPane();
    }
//    public void asociarServiciosAlmacenamiento(
//            ServicioAlmacenamientoConferencias objServicio,
//            ServicioAlmacenamientoArticulos objServicio1,
//            ServicioAlmacenamientoOrganizadores objServicio2,
//            ServicioAlmacenamientoUsuarios objServicio3) {
//        this.objServicio = objServicio;
//        this.objServicio1 = objServicio1;
//        this.objServicio2 = objServicio2;
//        this.objServicio3 = objServicio3;
//        relacionarInternalFrameConJdesptokPane();
//    }

//    public void asociarServicioAlmacenamientoConferencias(ServicioAlmacenamientoConferencias objServicio) {
//        this.objServicio = objServicio;
//        relacionarInternalFrameConJdesptokPane();
//    }

//    public void asociarServicioAlmacenamientoArticulos(ArticleService objServicio1) {
//        this.objServicio1 = objServicio1;
//        relacionarInternalFrameConJdesptokPane();
//    }

//    public void asociarServicioAlmacenamientoOrganizadores(ServicioAlmacenamientoOrganizadores objServicio2) {
//        this.objServicio2 = objServicio2;
//        relacionarInternalFrameConJdesptokPane();
//    }
    
//    public void asociarServicioAlmacenamientoUsuarios(ServicioAlmacenamientoUsuarios objServicio3) {
//        this.objServicio3 = objServicio3;
//        relacionarInternalFrameConJdesptokPane();
//    }

    private void relacionarInternalFrameConJdesptokPane() {
//        this.objVtnVerEstadisticas = new VtnVerEstadisticas();

        this.objVtnListarConferencias = new VtnListarConferencias();
        this.jDesktopPanelPrincipal.add(this.objVtnListarConferencias);

        this.objVtnListarArticulos = new VtnListarArticulos();
        this.jDesktopPanelPrincipal.add(this.objVtnListarArticulos);

//        this.objListarOrganizadores = new VtnListarOrganizadores(this.objServicio2);
//        this.jDesktopPanelPrincipal.add(this.objListarOrganizadores);
    }

    private void establecerIconoOrganización() {
        Image img1 = new ImageIcon(getClass().getResource("/recursos/logo.png")).getImage();
        ImageIcon img2 = new ImageIcon(img1.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        this.jLabelImagenOrganizacion.setIcon(img2);
        this.jLabelImagenOrganizacion.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelSuperior = new javax.swing.JPanel();
        jLabelImagenOrganizacion = new javax.swing.JLabel();
        jPanelMenu = new javax.swing.JPanel();
        jButtonGestionarConferencias = new javax.swing.JButton();
        jButtonGestionarArticulos = new javax.swing.JButton();
        jPanelInferior = new javax.swing.JPanel();
        jPanelCentral = new javax.swing.JPanel();
        jDesktopPanelPrincipal = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelSuperior.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelImagenOrganizacion.setText("jLabel1");

        jPanelMenu.setBackground(new java.awt.Color(0, 102, 153));

        jButtonGestionarConferencias.setBackground(new java.awt.Color(0, 102, 153));
        jButtonGestionarConferencias.setForeground(new java.awt.Color(255, 255, 255));
        jButtonGestionarConferencias.setText("Gestionar conferencias");
        jButtonGestionarConferencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGestionarConferenciasActionPerformed(evt);
            }
        });

        jButtonGestionarArticulos.setBackground(new java.awt.Color(0, 102, 153));
        jButtonGestionarArticulos.setForeground(new java.awt.Color(255, 255, 255));
        jButtonGestionarArticulos.setText("Gestionar Articulos");
        jButtonGestionarArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGestionarArticulosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelMenuLayout = new javax.swing.GroupLayout(jPanelMenu);
        jPanelMenu.setLayout(jPanelMenuLayout);
        jPanelMenuLayout.setHorizontalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(205, 205, 205)
                .addComponent(jButtonGestionarConferencias)
                .addGap(31, 31, 31)
                .addComponent(jButtonGestionarArticulos)
                .addContainerGap(285, Short.MAX_VALUE))
        );
        jPanelMenuLayout.setVerticalGroup(
            jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMenuLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonGestionarConferencias)
                    .addComponent(jButtonGestionarArticulos))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelSuperiorLayout = new javax.swing.GroupLayout(jPanelSuperior);
        jPanelSuperior.setLayout(jPanelSuperiorLayout);
        jPanelSuperiorLayout.setHorizontalGroup(
            jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabelImagenOrganizacion)
                .addContainerGap(784, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuperiorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelSuperiorLayout.setVerticalGroup(
            jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelImagenOrganizacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(jPanelSuperior, java.awt.BorderLayout.PAGE_START);

        jPanelInferior.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanelInferiorLayout = new javax.swing.GroupLayout(jPanelInferior);
        jPanelInferior.setLayout(jPanelInferiorLayout);
        jPanelInferiorLayout.setHorizontalGroup(
            jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 875, Short.MAX_VALUE)
        );
        jPanelInferiorLayout.setVerticalGroup(
            jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelInferior, java.awt.BorderLayout.PAGE_END);

        jPanelCentral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jDesktopPanelPrincipalLayout = new javax.swing.GroupLayout(jDesktopPanelPrincipal);
        jDesktopPanelPrincipal.setLayout(jDesktopPanelPrincipalLayout);
        jDesktopPanelPrincipalLayout.setHorizontalGroup(
            jDesktopPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 863, Short.MAX_VALUE)
        );
        jDesktopPanelPrincipalLayout.setVerticalGroup(
            jDesktopPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 236, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelCentralLayout = new javax.swing.GroupLayout(jPanelCentral);
        jPanelCentral.setLayout(jPanelCentralLayout);
        jPanelCentralLayout.setHorizontalGroup(
            jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCentralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPanelPrincipal)
                .addContainerGap())
        );
        jPanelCentralLayout.setVerticalGroup(
            jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCentralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPanelPrincipal)
                .addContainerGap())
        );

        getContentPane().add(jPanelCentral, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonGestionarConferenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGestionarConferenciasActionPerformed
        this.objVtnListarArticulos.setVisible(false);
        this.objVtnListarConferencias.setVisible(true);
//        this.objVtnVerEstadisticas.setVisible(false);
    }//GEN-LAST:event_jButtonGestionarConferenciasActionPerformed

    private void jButtonGestionarArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGestionarArticulosActionPerformed
        this.objVtnListarArticulos.setVisible(true);
        this.objVtnListarConferencias.setVisible(false);
//        this.objListarOrganizadores.setVisible(false);
//        this.objVtnVerEstadisticas.setVisible(false);
    }//GEN-LAST:event_jButtonGestionarArticulosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGestionarArticulos;
    private javax.swing.JButton jButtonGestionarConferencias;
    private javax.swing.JDesktopPane jDesktopPanelPrincipal;
    private javax.swing.JLabel jLabelImagenOrganizacion;
    private javax.swing.JPanel jPanelCentral;
    private javax.swing.JPanel jPanelInferior;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelSuperior;
    // End of variables declaration//GEN-END:variables
}
