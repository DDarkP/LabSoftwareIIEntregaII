package ArticlesView;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

public class VtnListarArticulos extends javax.swing.JInternalFrame {

    private final ArticleService objArticleService;
    private DefaultTableModel tableModel;

    public VtnListarArticulos() {
        initComponents();
        objArticleService = new ArticleService(); // Inicializar la instancia de ArticleService
        initializeTableModel(); // Inicializa el modelo de tabla
        jTableListarArticulos.setModel(tableModel); // Vincula el modelo a la tabla
    }

    private void initializeTableModel() {
        // Inicializa el modelo de tabla con las columnas deseadas
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Id");
        tableModel.addColumn("Titulo");
        tableModel.addColumn("Autores");
        tableModel.addColumn("Cantidad Autores");
        tableModel.addColumn("Estado de Revision");

        // Agregar el MouseListener a la tabla
        jTableListarArticulos.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = jTableListarArticulos.rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        // Selecciona la fila
                        jTableListarArticulos.setRowSelectionInterval(row, row);
                        // Muestra el menú contextual
                        showPopupMenu(e.getComponent(), e.getX(), e.getY(), row);
                    }
                }
            }
        });
    }

    public void limpiarTabla() {

        DefaultTableModel modelo = (DefaultTableModel) this.jTableListarArticulos.getModel();
        int filas = this.jTableListarArticulos.getRowCount();
        for (int i = 0; filas > i; i++) {
            modelo.removeRow(0);
        }
    }

    private void showPopupMenu(Component invoker, int x, int y, int row) {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem updateItem = new JMenuItem("Actualizar");
        updateItem.addActionListener(e -> llenarCamposVentanaActualizar(row));
        popupMenu.add(updateItem);

        JMenuItem deleteItem = new JMenuItem("Eliminar");
        deleteItem.addActionListener(e -> {
            try {
                deleteArticle(row);
            } catch (Exception ex) {
                Logger.getLogger(VtnListarArticulos.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        popupMenu.add(deleteItem);

        popupMenu.show(invoker, x, y);
    }

    private void llenarCamposVentanaActualizar(int row) {
        String articleTitulo = tableModel.getValueAt(row, 1).toString();
        String articleAutores = tableModel.getValueAt(row, 2).toString();
        String articleCantidadAutores = tableModel.getValueAt(row, 3).toString();
//        String articleEstadoRevision = tableModel.getValueAt(row, 4).toString();
        // Abre la ventana de actualización
        VtnActualizarArticulo objActualizarArticulo = new VtnActualizarArticulo();
        objActualizarArticulo.loadArticleData(articleTitulo, articleAutores, articleCantidadAutores);
        objActualizarArticulo.setVisible(true);
        
        String articleId = tableModel.getValueAt(row, 0).toString(); // Obtiene el ID del artículo
        // Aquí puedes abrir un diálogo o realizar la acción de actualización
        JOptionPane.showMessageDialog(this, "Actualizar artículo con ID: " + articleId);
    }
//    private void updateArticle(int row) {
//        // Implementa la lógica para actualizar el artículo en la fila especificada
//        String articleId = tableModel.getValueAt(row, 0).toString(); // Obtiene el ID del artículo
//        // Aquí puedes abrir un diálogo o realizar la acción de actualización
//        JOptionPane.showMessageDialog(this, "Actualizar artículo con ID: " + articleId);
//    }
    
    private void deleteArticle(int row) throws Exception {
        // Implementa la lógica para eliminar el artículo en la fila especificada
        int articleId = Integer.parseInt(tableModel.getValueAt(row, 0).toString());// Obtiene el ID del artículo
        // Aquí puedes confirmar la eliminación y luego eliminar el artículo
        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres eliminar el artículo con ID: " + articleId + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Lógica para eliminar el artículo
            objArticleService.deleteArticle(articleId);
            tableModel.removeRow(row); // Elimina la fila de la tabla
            // Aquí también deberías agregar la lógica para eliminarlo del backend
        }
    }
        
    public void llenarTabla() {
        jTableListarArticulos.setModel(tableModel);
        tableModel.setRowCount(0);
        try {
            String[][] articles = objArticleService.listarArticulos();

            for (String[] article : articles) {
                Object[] rowData = {
                    article[0],
                    article[1],
                    article[2],
                    article[3],
                    article[4],};
                tableModel.addRow(rowData);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los artículos: " + e.getMessage());
        }
    }

    public void llenarTablaById() {
        // Configuración inicial de la tabla
        jTableListarArticulos.setModel(tableModel);
        tableModel.setRowCount(0);  // Limpiar la tabla

        try {
            // Verificar si el campo de texto tiene un valor
            String idTexto = jTextFieldArticuloID.getText();
            if (idTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un ID de artículo.");
                return;
            }

            // Convertir el texto a entero para buscar el ID
            int articuloID = Integer.parseInt(idTexto);

            // Llamar al método para obtener los artículos por ID
            String[][] articles = objArticleService.listarArticulosPorID(articuloID);

            // Llenar la tabla con los datos obtenidos
            for (String[] article : articles) {
                Object[] rowData = {
                    article[0],
                    article[1],
                    article[2],
                    article[3],
                    article[4]
                };
                tableModel.addRow(rowData);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los artículos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListarArticulos = new javax.swing.JTable();
        jButtonRegistrar = new javax.swing.JButton();
        jButtonRefrescarTabla = new javax.swing.JButton();
        jButtonConsultarArticulo = new javax.swing.JButton();
        jTextFieldArticuloID = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Roboto Condensed", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Gestionar articulos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(345, 345, 345)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(423, 423, 423))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(0, 102, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setForeground(new java.awt.Color(0, 0, 153));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 953, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setForeground(new java.awt.Color(0, 0, 153));

        jTableListarArticulos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableListarArticulos);

        jButtonRegistrar.setBackground(new java.awt.Color(0, 102, 153));
        jButtonRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRegistrar.setText("Registrar");
        jButtonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarActionPerformed(evt);
            }
        });

        jButtonRefrescarTabla.setBackground(new java.awt.Color(0, 102, 153));
        jButtonRefrescarTabla.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRefrescarTabla.setText("Refrescar tabla");
        jButtonRefrescarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefrescarTablaActionPerformed(evt);
            }
        });

        jButtonConsultarArticulo.setBackground(new java.awt.Color(0, 102, 153));
        jButtonConsultarArticulo.setForeground(new java.awt.Color(255, 255, 255));
        jButtonConsultarArticulo.setText("Consultar por ID");
        jButtonConsultarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultarArticuloActionPerformed(evt);
            }
        });

        jTextFieldArticuloID.setFont(new java.awt.Font("Roboto Light", 2, 12)); // NOI18N
        jTextFieldArticuloID.setToolTipText("");
        jTextFieldArticuloID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldArticuloIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButtonConsultarArticulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldArticuloID, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(166, 166, 166)
                        .addComponent(jButtonRefrescarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(255, 255, 255)
                        .addComponent(jButtonRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(76, 76, 76))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonConsultarArticulo, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jButtonRefrescarTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldArticuloID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRefrescarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefrescarTablaActionPerformed
        llenarTabla();
    }//GEN-LAST:event_jButtonRefrescarTablaActionPerformed

    private void jButtonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarActionPerformed
        try {
            VtnRegistrarArticulo objVtnRegistrarArticulo = new VtnRegistrarArticulo();
            objVtnRegistrarArticulo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            objVtnRegistrarArticulo.setVisible(true);
        } catch (URISyntaxException ex) {
            Logger.getLogger(VtnListarArticulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonRegistrarActionPerformed

    private void jButtonConsultarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultarArticuloActionPerformed
        llenarTablaById();
    }//GEN-LAST:event_jButtonConsultarArticuloActionPerformed

    private void jTextFieldArticuloIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldArticuloIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldArticuloIDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConsultarArticulo;
    private javax.swing.JButton jButtonRefrescarTabla;
    private javax.swing.JButton jButtonRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableListarArticulos;
    private javax.swing.JTextField jTextFieldArticuloID;
    // End of variables declaration//GEN-END:variables
}
