package ArticlesView;

import Utilidades.Utilidades;
import java.awt.Component;
import java.awt.Desktop;
import java.io.File;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

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
        tableModel.addColumn("Actualizar");
        tableModel.addColumn("Eliminar");
    }

    public void limpiarTabla() {

        DefaultTableModel modelo = (DefaultTableModel) this.jTableListarArticulos.getModel();
        int filas = this.jTableListarArticulos.getRowCount();
        for (int i = 0; filas > i; i++) {
            modelo.removeRow(0);
        }
    }

    // Renderer para mostrar el botón en la celda
    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setText("Abrir archivo");
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus,
                int row, int column) {
            return this;
        }
    }

    // Editor para manejar el clic del botón
    class ButtonEditor extends DefaultCellEditor {

        private String pdfPath;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            JButton button = new JButton("Abrir PDF");
            button.addActionListener(e -> openPdf(pdfPath)); // Llama al método de abrir PDF
            this.editorComponent = button;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            pdfPath = (String) table.getModel().getValueAt(row, 4); // Obtiene la ruta del PDF de la columna 4
            return (Component) editorComponent;
        }

        @Override
        public Object getCellEditorValue() {
            return pdfPath;
        }
    }

    private void openPdf(String pdfPath) {
        try {
            File pdfFile = new File(pdfPath);
            if (pdfFile.exists()) {
                Desktop.getDesktop().open(pdfFile);
            } else {
                JOptionPane.showMessageDialog(this, "El archivo no existe: " + pdfPath);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al abrir el PDF: " + e.getMessage());
        }
    }

    public void llenarTabla() {
        jTableListarArticulos.setModel(tableModel);
        tableModel.setRowCount(0);
        try {
            String[][] articles = objArticleService.getArticles();

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
        String[][] articles = objArticleService.getArticlesByID(articuloID);

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

        // Configuración de la columna para el botón "Abrir PDF" (si se necesita)
//        jTableListarArticulos.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
//        jTableListarArticulos.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));

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
        jButtonActalizar = new javax.swing.JButton();
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
        jTableListarArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListarArticulosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableListarArticulos);

        jButtonRegistrar.setBackground(new java.awt.Color(0, 102, 153));
        jButtonRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRegistrar.setText("Registrar");
        jButtonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarActionPerformed(evt);
            }
        });

        jButtonActalizar.setBackground(new java.awt.Color(0, 102, 153));
        jButtonActalizar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonActalizar.setText("Actualizar");
        jButtonActalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActalizarActionPerformed(evt);
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
                        .addComponent(jButtonActalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jButtonActalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldArticuloID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonActalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActalizarActionPerformed
        llenarTabla();
    }//GEN-LAST:event_jButtonActalizarActionPerformed

    private void jButtonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarActionPerformed
        VtnRegistrarArticulo objVtnRegistrarArticulo = new VtnRegistrarArticulo();
        objVtnRegistrarArticulo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        objVtnRegistrarArticulo.setVisible(true);
    }//GEN-LAST:event_jButtonRegistrarActionPerformed

    private void jTableListarArticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListarArticulosMouseClicked

//        int column = this.jTableListarArticulos.getColumnModel().getColumnIndexAtX(evt.getX());
//        int row = evt.getY() / jTableListarArticulos.getRowHeight();
//
//        if (row < jTableListarArticulos.getRowCount() && row >= 0 && column < jTableListarArticulos.getColumnCount() && column >= 0) {
//            Object value = jTableListarArticulos.getValueAt(row, column);
//
//            if (value instanceof JButton) {
//                ((JButton) value).doClick();
//                JButton boton = (JButton) value;
//
//                String idArticulo = jTableListarArticulos.getValueAt(row, 0).toString();
//                int idArticuloConvertido = Integer.parseInt(idArticulo);
//
//                if (boton.getName().equals("Eliminar")) {
//                    try {
//                        if (Utilidades.mensajeConfirmacion("¿Estás seguro de que quieres eliminar el artículo con identificador " + idArticulo + "?", "Confirmación") == 0) {
//                            boolean bandera = this.objArticleService.deleteArticle(idArticuloConvertido);
//                            if (bandera) {
//                                Utilidades.mensajeExito("El artículo con identificador " + idArticuloConvertido + " fue eliminado exitosamente", "Artículo eliminado");
//                                llenarTabla();
//                            } else {
//                                Utilidades.mensajeAdvertencia("El artículo con identificador " + idArticuloConvertido + " no fue eliminado", "Error al eliminar");
//                            }
//                        }
//                    } catch (Exception ex) {
//                        Utilidades.mensajeError("Error al eliminar artículo. Inténtelo de nuevo más tarde", "Error");
//                    }
//                } else if (boton.getName().equals("Actualizar")) {
////                    VtnActualizarArticulo objVtnActualizarArticulo = new VtnActualizarArticulo(objServicio, objServicio2);
////                    objVtnActualizarArticulo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
////                    objVtnActualizarArticulo.cargarDatos(idArticuloConvertido);
////                    objVtnActualizarArticulo.setVisible(true);
//                } else if (boton.getName().equals("PDF")) {
////                    // Obtener el artículo correspondiente
////                    Articulo articulo = this.objServicio.obtenerArticuloPorId(idArticuloConvertido);
////
////                    // Verifica que el artículo no sea nulo
////                    if (articulo != null) {
////                        File pdfFile = articulo.getArchivoPdf();
////
////                        // Verifica que el archivo PDF no sea nulo
////                        if (pdfFile != null) {
////                            if (pdfFile.exists()) {
////                                try {
////                                    // Abrir el archivo PDF usando la clase Desktop
////                                    Desktop desktop = Desktop.getDesktop();
////                                    if (desktop.isSupported(Desktop.Action.OPEN)) {
////                                        desktop.open(pdfFile);
////                                    } else {
////                                        Utilidades.mensajeAdvertencia("No se soporta la acción de abrir archivos en este sistema.", "Acción no soportada");
////                                    }
////                                } catch (IOException e) {
////                                    Utilidades.mensajeError("Error al intentar abrir el archivo PDF.", "Error");
////                                    e.printStackTrace();
////                                }
////                            } else {
////                                Utilidades.mensajeAdvertencia("El archivo PDF no existe.", "Archivo no encontrado");
////                            }
////                        } else {
////                            Utilidades.mensajeAdvertencia("No se encontró el archivo PDF asociado al artículo.", "Archivo no encontrado");
////                        }
////                    } else {
////                        Utilidades.mensajeAdvertencia("No se encontró el artículo con el ID especificado.", "Artículo no encontrado");
////                    }
//                }
//            }
//        }
    }//GEN-LAST:event_jTableListarArticulosMouseClicked

    private void jButtonConsultarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultarArticuloActionPerformed
        llenarTablaById();
    }//GEN-LAST:event_jButtonConsultarArticuloActionPerformed

    private void jTextFieldArticuloIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldArticuloIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldArticuloIDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActalizar;
    private javax.swing.JButton jButtonConsultarArticulo;
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
