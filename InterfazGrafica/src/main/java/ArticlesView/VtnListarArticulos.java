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
        tableModel.addColumn("Conferencia");
        tableModel.addColumn("Estado de Revision");
        tableModel.addColumn("Archivo");
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
        try {
            jTableListarArticulos.setModel(tableModel);
            String[][] articles = objArticleService.getArticles();
            tableModel.setRowCount(0);

            for (String[] article : articles) {
                Object[] rowData = {
                    article[0],
                    article[1],
                    article[2],
                    article[3],
                    article[4],
                    "Abrir PDF", // Texto del botón en la última columna                    
                };
                tableModel.addRow(rowData);
            }

            // Agregar un botón en la columna de "Abrir PDF"
            jTableListarArticulos.getColumnModel().getColumn(5).setCellRenderer((TableCellRenderer) new ButtonRenderer());
            jTableListarArticulos.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));

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

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Roboto Condensed", 1, 18)); // NOI18N
        jLabel1.setText("Gestionar articulos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(jLabel1)
                .addContainerGap(232, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setForeground(new java.awt.Color(0, 0, 153));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 586, Short.MAX_VALUE)
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

        jButtonRegistrar.setText("Registrar");
        jButtonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarActionPerformed(evt);
            }
        });

        jButtonActalizar.setText("Actualizar");
        jButtonActalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(76, 76, 76))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jButtonActalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(198, 198, 198)
                .addComponent(jButtonRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(119, 119, 119))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonActalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActalizar;
    private javax.swing.JButton jButtonRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableListarArticulos;
    // End of variables declaration//GEN-END:variables
}
