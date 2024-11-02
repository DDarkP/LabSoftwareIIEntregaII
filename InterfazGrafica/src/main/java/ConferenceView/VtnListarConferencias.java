package ConferenceView;

import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VtnListarConferencias extends javax.swing.JInternalFrame {

    private ConferenceService objConferenceService;
    private DefaultTableModel tableModel;

    public VtnListarConferencias() {
        initComponents();
        objConferenceService = new ConferenceService(); // Inicializar la instancia de ArticleService
        iniciarlizarTabla();// Inicializa el modelo de tabla
        jTableListadoConferencias.setModel(tableModel); // Vincula el modelo a la tabla
    }

    private void iniciarlizarTabla() {
        // Inicializa el modelo de tabla con las columnas deseadas
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Id");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Articulos");
        tableModel.addColumn("Tope de Articulos");
        tableModel.addColumn("N. articulos registrados");
        
    }

    public void limpiarTabla() {

        DefaultTableModel modelo = (DefaultTableModel) this.jTableListadoConferencias.getModel();
        int filas = this.jTableListadoConferencias.getRowCount();
        for (int i = 0; filas > i; i++) {
            modelo.removeRow(0);
        }
    }

    private void llenarTabla() {
        try {
            // Asignar el modelo a la tabla antes de cargar datos
            jTableListadoConferencias.setModel(tableModel);

            // Obtener las conferencias
            String[][] conferencias = objConferenceService.getConferences();

            // Mostrar el tamaño del arreglo para depuración
            System.out.println("Número de conferencias: " + conferencias.length);

            // Asegúrate de que el tableModel esté limpio antes de agregar nuevas filas
            tableModel.setRowCount(0);
            
            // Comprobar si hay conferencias y el tamaño de cada fila
            for (String[] conferencia : conferencias) {
                System.out.println("Tamaño de la fila: " + conferencia.length); // Imprimir el tamaño de cada fila para depuración

                // Depuración: mostrar contenido de cada fila
                System.out.println("Contenido de la fila: " + Arrays.toString(conferencia));
                
                // Asegúrate de que la fila tenga el tamaño esperado (ajusta según tu modelo de datos)
//                if (conferencia.length != 5) {
//                    throw new IllegalArgumentException("Cada conferencia debe tener 5 atributos.");
//                }

                Object[] rowData = {
                    conferencia[0],
                    conferencia[1],
                    conferencia[2],
                    conferencia[3]
                };
                tableModel.addRow(rowData);
            }

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos: " + ex.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las Conferencias: " + e.getMessage());
        }
    }

//    private void llenarTabla() {
//        try {
//            jTableListadoConferencias.setModel(tableModel);
//            String[][] conferencias = objConferenceService.getConferences();
//            tableModel.setRowCount(0);
//
//            for (String[] conferencia : conferencias) {
//                Object[] rowData = {
//                    conferencia[0],
//                    conferencia[1],
//                    conferencia[2],
//                    conferencia[3],
//                    conferencia[4]                   
//                };
//                tableModel.addRow(rowData);
//            }
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Error al cargar las Conferencias: " + e.getMessage());
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelSuperior = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jPanelInferior = new javax.swing.JPanel();
        jPanelCentral = new javax.swing.JPanel();
        jButtonRegistrar = new javax.swing.JButton();
        jButtonActualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableListadoConferencias = new javax.swing.JTable();

        jPanelSuperior.setBackground(new java.awt.Color(0, 102, 153));
        jPanelSuperior.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelTitulo.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitulo.setText("Listado de conferencias");

        javax.swing.GroupLayout jPanelSuperiorLayout = new javax.swing.GroupLayout(jPanelSuperior);
        jPanelSuperior.setLayout(jPanelSuperiorLayout);
        jPanelSuperiorLayout.setHorizontalGroup(
            jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(364, 364, 364))
        );
        jPanelSuperiorLayout.setVerticalGroup(
            jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuperiorLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addGap(27, 27, 27))
        );

        getContentPane().add(jPanelSuperior, java.awt.BorderLayout.PAGE_START);

        jPanelInferior.setBackground(new java.awt.Color(0, 102, 153));
        jPanelInferior.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanelInferiorLayout = new javax.swing.GroupLayout(jPanelInferior);
        jPanelInferior.setLayout(jPanelInferiorLayout);
        jPanelInferiorLayout.setHorizontalGroup(
            jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 936, Short.MAX_VALUE)
        );
        jPanelInferiorLayout.setVerticalGroup(
            jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelInferior, java.awt.BorderLayout.PAGE_END);

        jPanelCentral.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCentral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButtonRegistrar.setBackground(new java.awt.Color(0, 102, 153));
        jButtonRegistrar.setFont(new java.awt.Font("Roboto Condensed", 0, 12)); // NOI18N
        jButtonRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/agregar.png"))); // NOI18N
        jButtonRegistrar.setText("Registrar conferencia");
        jButtonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarActionPerformed(evt);
            }
        });

        jButtonActualizar.setBackground(new java.awt.Color(0, 102, 153));
        jButtonActualizar.setFont(new java.awt.Font("Roboto Condensed", 0, 12)); // NOI18N
        jButtonActualizar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/print.png"))); // NOI18N
        jButtonActualizar.setText("Actualizar");
        jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarActionPerformed(evt);
            }
        });

        jTableListadoConferencias.setAutoCreateRowSorter(true);
        jTableListadoConferencias.setBackground(new java.awt.Color(153, 204, 255));
        jTableListadoConferencias.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableListadoConferencias);

        javax.swing.GroupLayout jPanelCentralLayout = new javax.swing.GroupLayout(jPanelCentral);
        jPanelCentral.setLayout(jPanelCentralLayout);
        jPanelCentralLayout.setHorizontalGroup(
            jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCentralLayout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jButtonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 329, Short.MAX_VALUE)
                .addComponent(jButtonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
            .addGroup(jPanelCentralLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jScrollPane1)
                .addGap(54, 54, 54))
        );
        jPanelCentralLayout.setVerticalGroup(
            jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCentralLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanelCentralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                .addGap(65, 65, 65))
        );

        getContentPane().add(jPanelCentral, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
        llenarTabla();
    }//GEN-LAST:event_jButtonActualizarActionPerformed

    private void jButtonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarActionPerformed
        VtnRegistrarConferencia objVtnRegistrarConferencia = new VtnRegistrarConferencia();
        objVtnRegistrarConferencia.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        objVtnRegistrarConferencia.setVisible(true);
    }//GEN-LAST:event_jButtonRegistrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonRegistrar;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelCentral;
    private javax.swing.JPanel jPanelInferior;
    private javax.swing.JPanel jPanelSuperior;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableListadoConferencias;
    // End of variables declaration//GEN-END:variables
}
