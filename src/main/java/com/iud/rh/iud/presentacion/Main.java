package com.iud.rh.iud.presentacion;

import com.iud.rh.iud.controller.Estado_civilController;
import com.iud.rh.iud.controller.FuncionarioController;
import com.iud.rh.iud.controller.Grupo_familiarController;
import com.iud.rh.iud.controller.Tipo_identificacionController;
import com.iud.rh.iud.domain.Estado_civil;
import com.iud.rh.iud.domain.Funcionario;
import com.iud.rh.iud.domain.Grupo_familiar;
import com.iud.rh.iud.domain.Tipo_identificacion;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Main extends javax.swing.JFrame {

    private final FuncionarioController funcionarioController;
    private final Grupo_familiarController grupo_familiarController;
    private static final String [] COLUMNS = {"ID","TIPO","IDENTIFICACIÓN","NOMBRES",
        "APELLIDOS","EST CIVIL","SEXO","DIRECCIÓN","TELÉFONO","F.NACIMIENTO"};
    
    private static final String [] COLPARIENTES = {"TIPO","IDENTIFICACIÓN","NOMBRE COMPLETO",
        "PARENTESCO","DIRECCIÓN","TELÉFONO"};
    private static final String SELECCIONE = "--SELECCIONE--";
    private final Tipo_identificacionController tipo_identificacionController;
    private final Estado_civilController estado_civilController;
    

    public Main() {
        initComponents();
        txtFuncionarioIdEdit.setEditable(false);
        funcionarioController = new FuncionarioController();
        grupo_familiarController = new Grupo_familiarController();
        listFuncionarios();
        tipo_identificacionController = new Tipo_identificacionController();
        listTipo_identificaciones();
        estado_civilController = new Estado_civilController();
        listEstado_civiles();
        String x = String.valueOf(cbxTipo_identificacion.getSelectedIndex());
        System.out.println("SELE "+x);
        addListener();
        
    }
    
    private void listTipo_identificaciones(){
        
        try {
            List<Tipo_identificacion> tipo_identificaciones = tipo_identificacionController.obtenerTipo_identificaciones();
            if (tipo_identificaciones.isEmpty()){
                return;
            }
            cbxTipo_identificacion.addItem(new Tipo_identificacion(0,SELECCIONE));
            cbxTipoIdentificacionEdit.addItem(new Tipo_identificacion(0,SELECCIONE));
            for (Tipo_identificacion tipo_identificacion : tipo_identificaciones){                
                System.out.println("DATOTIPO: "+tipo_identificacion.getNombre());                
                cbxTipo_identificacion.addItem(new Tipo_identificacion(tipo_identificacion.getId(),tipo_identificacion.getNombre()));
                cbxTipoIdentificacionEdit.addItem(new Tipo_identificacion(tipo_identificacion.getId(),tipo_identificacion.getNombre()));
            }    
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }

    private void listEstado_civiles(){
        
        try {
            List<Estado_civil> estado_civiles = estado_civilController.obtenerEstado_civiles();
            if (estado_civiles.isEmpty()){
                return;
            }
            cbxEstado_Civil.addItem(new Estado_civil(0,SELECCIONE));
            cbxEstadoCivilEdit.addItem(new Estado_civil(0,SELECCIONE));
            for (Estado_civil estado_civil : estado_civiles){                
                System.out.println("DATOTIPO: "+estado_civil.getNombre());              
                cbxEstado_Civil.addItem(new Estado_civil(estado_civil.getId(),estado_civil.getNombre()));
                cbxEstadoCivilEdit.addItem(new Estado_civil(estado_civil.getId(),estado_civil.getNombre()));
            }    
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
    }

    
    private void listFuncionarios(){
        cbxFuncionarios.removeAllItems();
        Funcionario funcionarioSel = new Funcionario();
        funcionarioSel.setIdentificacion(SELECCIONE);
        funcionarioSel.setNombretipide("");
        funcionarioSel.setNombres("");
        funcionarioSel.setApellidos("");
        cbxFuncionarios.addItem(funcionarioSel);
 
        int funcprimero = -1;
        
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        for (String COLUMN : COLUMNS){
            defaultTableModel.addColumn(COLUMN);
        }
        tblFuncionarios.setModel(defaultTableModel);        
        try {
            List<Funcionario> funcionarios = funcionarioController.obtenerFuncionarios();
            if (funcionarios.isEmpty()){
                return;
            }
            defaultTableModel.setRowCount(funcionarios.size());
            int row = 0;
            for (Funcionario funcionario : funcionarios){                
                System.out.println("DATO: "+funcionario.getNombretipide());                
                if (funcprimero==-1){
                   funcprimero = funcionario.getId(); 
                }
                
                defaultTableModel.setValueAt(funcionario.getId(), row, 0);
                defaultTableModel.setValueAt(funcionario.getNombretipide(), row, 1);
                defaultTableModel.setValueAt(funcionario.getIdentificacion(), row, 2);
                defaultTableModel.setValueAt(funcionario.getNombres(), row, 3);
                defaultTableModel.setValueAt(funcionario.getApellidos(), row, 4);
                defaultTableModel.setValueAt(funcionario.getNombreestciv(), row, 5);
                defaultTableModel.setValueAt(funcionario.getSexo(), row, 6);
                defaultTableModel.setValueAt(funcionario.getDireccion(), row, 7);
                defaultTableModel.setValueAt(funcionario.getTelefono(), row, 8);
                defaultTableModel.setValueAt(funcionario.getFecha_nacimiento(), row, 9);
                row++;                
                cbxFuncionarios.addItem(funcionario);   
            } 
            if (funcprimero>=0){
                   listParientes(funcprimero);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
   
    }
    
    private void listParientes(int id){
        
        DefaultTableModel defaultTableModelP = new DefaultTableModel();
        for (String COLUMN : COLPARIENTES){
            defaultTableModelP.addColumn(COLUMN);
        }
        tblParientes.setModel(defaultTableModelP);        
        try {
            List<Grupo_familiar> familiares = grupo_familiarController.obtenerGrupo_familiares(id);
;            if (familiares.isEmpty()){
                return;
            }
            defaultTableModelP.setRowCount(familiares.size());
            int row = 0;
            for (Grupo_familiar familiar : familiares){                
                System.out.println("DATO: "+familiar.getNombretipide());                
                defaultTableModelP.setValueAt(familiar.getNombretipide(), row, 0);
                defaultTableModelP.setValueAt(familiar.getIdentificacion(), row, 1);
                defaultTableModelP.setValueAt(familiar.getNombre_completo(), row, 2);
                defaultTableModelP.setValueAt(familiar.getNombreparent(), row, 3);
                defaultTableModelP.setValueAt(familiar.getDireccion(), row, 4);
                defaultTableModelP.setValueAt(familiar.getTelefono(), row, 5);
                row++;                
                //cbxFuncionarios.addItem(funcionario);   
            }           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }        
        
    }   
    
    
    
    
    
    private void addListener(){
        cbxFuncionarios.addItemListener(event -> {
            Funcionario selectedFuncionario = (Funcionario) event.getItem();
            if (selectedFuncionario.getIdentificacion().equals(SELECCIONE)){
                txtFuncionarioIdEdit.setText("");
                cbxTipoIdentificacionEdit.setSelectedIndex(0);
                txtIdentificacionEdit.setText("");
                txtNombresEdit.setText("");
                txtApellidosEdit.setText("");
                cbxEstadoCivilEdit.setSelectedIndex(0);
                cbxSexoEdit.setSelectedItem(SELECCIONE);
                txtDireccionEdit.setText("");
                txtTelefonoEdit.setText("");
                txtFechaNacimientoEdit.setText("");  
                              
            } else {
                txtFuncionarioIdEdit.setText(String.valueOf(selectedFuncionario.getId()));
                cbxTipoIdentificacionEdit.setSelectedIndex(selectedFuncionario.getTipo_identificacion_id());
                txtIdentificacionEdit.setText(String.valueOf(selectedFuncionario.getIdentificacion()));
                txtNombresEdit.setText(String.valueOf(selectedFuncionario.getNombres()));
                txtApellidosEdit.setText(String.valueOf(selectedFuncionario.getApellidos()));
                cbxEstadoCivilEdit.setSelectedIndex(selectedFuncionario.getEstado_civil_id());
                String sexo = selectedFuncionario.getSexo();
                if (sexo.equals("F")){
                   sexo = "Femenino"; 
                } else {
                    sexo = "Masculino";
                }
                cbxSexoEdit.setSelectedItem(sexo);
                txtDireccionEdit.setText(String.valueOf(selectedFuncionario.getDireccion()));
                txtTelefonoEdit.setText(String.valueOf(selectedFuncionario.getTelefono()));
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha = selectedFuncionario.getFecha_nacimiento();
                String fechaCadena = sdf.format(fecha); 
                txtFechaNacimientoEdit.setText(fechaCadena);

            }
        });
        

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
        jPanels = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblTipo_Identificación = new javax.swing.JLabel();
        lblIdentificacion = new javax.swing.JLabel();
        txtIdentificacion = new javax.swing.JTextField();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        lblNombres = new javax.swing.JLabel();
        lblApellidos = new javax.swing.JLabel();
        cbxEstado_Civil = new javax.swing.JComboBox<>();
        lblEstado_civil = new javax.swing.JLabel();
        cbxSexo = new javax.swing.JComboBox<>();
        lblSexo = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        lblFecha_Nacimiento = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtFecha_Nacimiento = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFuncionarios = new javax.swing.JTable();
        btnGuardarFuncionario = new javax.swing.JButton();
        cbxTipo_identificacion = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblParientes = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblFuncionarios = new javax.swing.JLabel();
        cbxFuncionarios = new javax.swing.JComboBox<>();
        lblId = new javax.swing.JLabel();
        txtFuncionarioIdEdit = new javax.swing.JTextField();
        cbxTipoIdentificacionEdit = new javax.swing.JComboBox<>();
        lblIdentificacionEdit = new javax.swing.JLabel();
        txtIdentificacionEdit = new javax.swing.JTextField();
        lblNombresdEdit = new javax.swing.JLabel();
        txtNombresEdit = new javax.swing.JTextField();
        lblApellidosEdit = new javax.swing.JLabel();
        txtApellidosEdit = new javax.swing.JTextField();
        lblEstadoCivilEdit = new javax.swing.JLabel();
        cbxEstadoCivilEdit = new javax.swing.JComboBox<>();
        lblSexoEdit = new javax.swing.JLabel();
        cbxSexoEdit = new javax.swing.JComboBox<>();
        lblDireccionEdit = new javax.swing.JLabel();
        txtDireccionEdit = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTelefonoEdit = new javax.swing.JTextField();
        lblFechaNacimientoEdit = new javax.swing.JLabel();
        txtFechaNacimientoEdit = new javax.swing.JTextField();
        btnActualizarFuncionario = new javax.swing.JButton();
        btnEliminarFuncionario = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("GESTIÓN DE FUNCIONARIOS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 170, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Digite los campos para funcionario"));
        jPanel3.setPreferredSize(new java.awt.Dimension(734, 334));

        lblTipo_Identificación.setText("Tipo_Identificación");

        lblIdentificacion.setText("Identificación");

        txtIdentificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdentificacionActionPerformed(evt);
            }
        });

        txtNombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresActionPerformed(evt);
            }
        });

        txtApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidosActionPerformed(evt);
            }
        });

        lblNombres.setText("Nombres");

        lblApellidos.setText("Apellidos");

        lblEstado_civil.setText("Estado Civil");

        cbxSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE--", "Femenino", "Masculino" }));

        lblSexo.setText("Sexo");

        lblDireccion.setText("Dirección");

        lblTelefono.setText("Teléfono");

        lblFecha_Nacimiento.setText("Fecha Nacimiento");

        txtFecha_Nacimiento.setText("dd/mm/aaaa");

        tblFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblFuncionarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFuncionariosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblFuncionarios);

        btnGuardarFuncionario.setText("GUARDAR");
        btnGuardarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarFuncionarioActionPerformed(evt);
            }
        });

        cbxTipo_identificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipo_identificacionActionPerformed(evt);
            }
        });

        jScrollPane3.setBorder(javax.swing.BorderFactory.createTitledBorder("Familiares"));

        tblParientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblParientes.setPreferredSize(new java.awt.Dimension(200, 80));
        jScrollPane3.setViewportView(tblParientes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardarFuncionario)
                .addGap(316, 316, 316))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cbxSexo, 0, 136, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFecha_Nacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(297, 297, 297)
                                .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFecha_Nacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTipo_Identificación, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cbxTipo_identificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 4, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(98, 98, 98)
                                .addComponent(lblApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblEstado_civil, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(20, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxEstado_Civil, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipo_Identificación)
                    .addComponent(lblIdentificacion)
                    .addComponent(lblNombres)
                    .addComponent(lblApellidos)
                    .addComponent(lblEstado_civil))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxEstado_Civil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTipo_identificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSexo)
                    .addComponent(lblDireccion)
                    .addComponent(lblTelefono)
                    .addComponent(lblFecha_Nacimiento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha_Nacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarFuncionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanels.addTab("Funcionarios", jPanel1);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Digite los campos que desea modificar"));

        lblFuncionarios.setText("Funcionarios");

        cbxFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFuncionariosActionPerformed(evt);
            }
        });

        lblId.setText("ID");

        lblIdentificacionEdit.setText("Identificación");

        lblNombresdEdit.setText("Nombres");

        lblApellidosEdit.setText("Apellidos");

        lblEstadoCivilEdit.setText("Estado Civil");

        cbxEstadoCivilEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxEstadoCivilEditActionPerformed(evt);
            }
        });

        lblSexoEdit.setText("Sexo");

        cbxSexoEdit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SELECCIONE--", "Femenino", "Masculino" }));

        lblDireccionEdit.setText("Dirección");

        jLabel2.setText("Teléfono");

        lblFechaNacimientoEdit.setText("Fecha Nacimiento");

        txtFechaNacimientoEdit.setText("dd/mm/aaaa");
        txtFechaNacimientoEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaNacimientoEditActionPerformed(evt);
            }
        });

        btnActualizarFuncionario.setText("ACTUALIZAR");
        btnActualizarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarFuncionarioActionPerformed(evt);
            }
        });

        btnEliminarFuncionario.setText("ELIMINAR");
        btnEliminarFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarFuncionarioActionPerformed(evt);
            }
        });

        jLabel3.setText("Tipo Identificación");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblSexoEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                    .addComponent(lblId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtFuncionarioIdEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbxTipoIdentificacionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(lblIdentificacionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 7, Short.MAX_VALUE))
                                            .addComponent(txtIdentificacionEdit))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNombresEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblNombresdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(12, 12, 12)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtApellidosEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblApellidosEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblEstadoCivilEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbxEstadoCivilEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(lblDireccionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTelefonoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtFechaNacimientoEdit, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(lblFechaNacimientoEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(btnActualizarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(btnEliminarFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(lblFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(64, 64, 64))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(cbxSexoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDireccionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbxFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFuncionarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxFuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEstadoCivilEdit)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblId)
                        .addComponent(lblIdentificacionEdit)
                        .addComponent(lblNombresdEdit)
                        .addComponent(lblApellidosEdit)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFuncionarioIdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTipoIdentificacionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdentificacionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombresEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidosEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxEstadoCivilEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSexoEdit)
                    .addComponent(lblDireccionEdit)
                    .addComponent(jLabel2)
                    .addComponent(lblFechaNacimientoEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxSexoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccionEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefonoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaNacimientoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizarFuncionario)
                    .addComponent(btnEliminarFuncionario))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jPanels.addTab("Actualizar Eliminar", jPanel2);

        getContentPane().add(jPanels, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 740, 360));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentificacionActionPerformed

    private void txtApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosActionPerformed

    private void txtFechaNacimientoEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaNacimientoEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaNacimientoEditActionPerformed

    private void btnActualizarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarFuncionarioActionPerformed

        if (txtFuncionarioIdEdit.getText().trim().length()==0 ) {
            JOptionPane.showMessageDialog(null, "Seleccione un funcionario para actualizar.");
            txtFuncionarioIdEdit.requestFocus();
            return;
        }
        
        if (cbxTipoIdentificacionEdit.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Seleccione el tipo de identificación.");
            cbxTipoIdentificacionEdit.requestFocus();
            return;
        }        
        if (txtIdentificacionEdit.getText().trim().length()==0 ) {
            JOptionPane.showMessageDialog(null, "Digite la identificación.");
            txtIdentificacionEdit.requestFocus();
            return;
        }
        if (txtNombresEdit.getText().trim().length()==0 ) {
            JOptionPane.showMessageDialog(null, "Digite el nombre.");
            txtNombresEdit.requestFocus();
            return;
        }
        if (txtApellidosEdit.getText().trim().length()==0 ) {
            JOptionPane.showMessageDialog(null, "Digite los apellidos.");
            txtApellidosEdit.requestFocus();
            return;
        }        
        if (cbxEstadoCivilEdit.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Seleccione el estado civil.");
            cbxEstadoCivilEdit.requestFocus();
            return;
        }        
        if (cbxSexoEdit.getSelectedItem().toString().equalsIgnoreCase(SELECCIONE) ) {
            JOptionPane.showMessageDialog(null, "Seleccione el sexo.");
            cbxSexoEdit.requestFocus();
            return;
        }
        if (txtDireccionEdit.getText().trim().length()==0 ) {
            JOptionPane.showMessageDialog(null, "Digite la dirección.");
            txtDireccionEdit.requestFocus();
            return;
        }
        if (txtTelefonoEdit.getText().trim().length()==0 ) {
            JOptionPane.showMessageDialog(null, "Digite el teléfono.");
            txtTelefonoEdit.requestFocus();
            return;
        } 
        System.out.println("antes fecha");
        if (txtFechaNacimientoEdit.getText().trim().length()==0 ) {
            JOptionPane.showMessageDialog(null, "Digite la fecha de nacimiento.");
            txtFechaNacimientoEdit.requestFocus();
            return;
        }   
        System.out.println("después fecha");

        try {
            Funcionario funcionario = new Funcionario();
            funcionario.setTipo_identificacion_id(cbxTipoIdentificacionEdit.getSelectedIndex());
            funcionario.setIdentificacion(txtIdentificacionEdit.getText().trim());
            funcionario.setNombres(txtNombresEdit.getText().trim());
            funcionario.setApellidos(txtApellidosEdit.getText().trim());
            funcionario.setEstado_civil_id(cbxEstadoCivilEdit.getSelectedIndex());
            funcionario.setSexo(cbxSexoEdit.getSelectedItem().toString());
            System.out.println("SEXO  "+cbxSexoEdit.getSelectedItem()+"   "+cbxSexoEdit.getSelectedItem().toString().substring(0, 1));
            funcionario.setSexo(cbxSexoEdit.getSelectedItem().toString().substring(0, 1));
            funcionario.setDireccion(txtDireccionEdit.getText().trim());
            funcionario.setTelefono(txtTelefonoEdit.getText().trim());
            SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
            String dateInString = txtFechaNacimientoEdit.getText();            
            System.out.println("dateInString  "+dateInString);
            
            int opt = JOptionPane.showConfirmDialog(null, "Desea actualizar el funcionario?", "Confirmar Salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opt == 0){
                try {            
                    System.out.println("antes parse fecha");
                    Date date = formatter2.parse(dateInString);
                    System.out.println("después parse fecha");
                    funcionario.setFecha_nacimiento(date);
                } catch (ParseException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "La fecha de nacimiento debe tener el formato dd/mm/aaaa.");
                }
                funcionarioController.actualizarFuncionario(Integer.parseInt(txtFuncionarioIdEdit.getText()),funcionario);

                cbxTipoIdentificacionEdit.setSelectedIndex(0);
                txtIdentificacionEdit.setText("");
                txtNombresEdit.setText("");
                txtApellidosEdit.setText("");
                cbxEstadoCivilEdit.setSelectedIndex(0);
                cbxSexoEdit.setSelectedItem(SELECCIONE);
                txtDireccionEdit.setText("");
                txtTelefonoEdit.setText("");
                txtFechaNacimientoEdit.setText("");    

                listFuncionarios();
                JOptionPane.showMessageDialog(null, "Funcionario actualizado con éxito.");
            }
            } catch(SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al actualizar el funcionario.");

            }        
        
    }//GEN-LAST:event_btnActualizarFuncionarioActionPerformed

    private void btnGuardarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarFuncionarioActionPerformed
        
        System.out.println("INICIA GUARDAR   "+cbxTipo_identificacion.getSelectedItem()+"  "+cbxTipo_identificacion.getSelectedIndex());
        if (cbxTipo_identificacion.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Seleccione el tipo de identificación.");
            cbxTipo_identificacion.requestFocus();
            return;
        }        
        if (txtIdentificacion.getText().trim().length()==0 ) {
            JOptionPane.showMessageDialog(null, "Digite la identificación.");
            txtIdentificacion.requestFocus();
            return;
        }
        if (txtNombres.getText().trim().length()==0 ) {
            JOptionPane.showMessageDialog(null, "Digite el nombre.");
            txtNombres.requestFocus();
            return;
        }
        if (txtApellidos.getText().trim().length()==0 ) {
            JOptionPane.showMessageDialog(null, "Digite los apellidos.");
            txtApellidos.requestFocus();
            return;
        }        
        if (cbxEstado_Civil.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(null, "Seleccione el estado civil.");
            cbxEstado_Civil.requestFocus();
            return;
        }        
        if (cbxSexo.getSelectedItem().toString().equalsIgnoreCase(SELECCIONE) ) {
            JOptionPane.showMessageDialog(null, "Seleccione el sexo.");
            cbxSexo.requestFocus();
            return;
        }
        if (txtDireccion.getText().trim().length()==0 ) {
            JOptionPane.showMessageDialog(null, "Digite la dirección.");
            txtDireccion.requestFocus();
            return;
        }
        if (txtTelefono.getText().trim().length()==0 ) {
            JOptionPane.showMessageDialog(null, "Digite el teléfono.");
            txtTelefono.requestFocus();
            return;
        } 
        System.out.println("antes fecha");
        if (txtFecha_Nacimiento.getText().trim().length()==0 ) {
            JOptionPane.showMessageDialog(null, "Digite la fecha de nacimiento.");
            txtFecha_Nacimiento.requestFocus();
            return;
        }   
        System.out.println("después fecha");
        

        try {
            Funcionario funcionario = new Funcionario();
            funcionario.setTipo_identificacion_id(cbxTipo_identificacion.getSelectedIndex());
            funcionario.setIdentificacion(txtIdentificacion.getText().trim());
            funcionario.setNombres(txtNombres.getText().trim());
            funcionario.setApellidos(txtApellidos.getText().trim());
            funcionario.setEstado_civil_id(cbxEstado_Civil.getSelectedIndex());
            funcionario.setSexo(cbxSexo.getSelectedItem().toString());
            System.out.println("SEXO  "+cbxSexo.getSelectedItem()+"   "+cbxSexo.getSelectedItem().toString().substring(0, 1));
            funcionario.setSexo(cbxSexo.getSelectedItem().toString().substring(0, 1));
            funcionario.setDireccion(txtDireccion.getText().trim());
            funcionario.setTelefono(txtTelefono.getText().trim());
            SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
            String dateInString = txtFecha_Nacimiento.getText();            
            System.out.println("dateInString  "+dateInString);
            try {            
                System.out.println("antes parse fecha");
                Date date = formatter2.parse(dateInString);
                System.out.println("después parse fecha");
                funcionario.setFecha_nacimiento(date);
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "La fecha de nacimiento debe tener el formato dd/mm/aaaa.");
            }
            
        
            funcionarioController.crearFuncionario(funcionario);   

                cbxTipo_identificacion.setSelectedItem(SELECCIONE);
                txtIdentificacion.setText("");
                txtNombres.setText("");
                txtApellidos.setText("");
                cbxEstado_Civil.setSelectedItem(SELECCIONE);
                cbxSexo.setSelectedItem(SELECCIONE);
                txtDireccion.setText("");
                txtTelefono.setText("");
                txtFecha_Nacimiento.setText("");    
                
                listFuncionarios();
                JOptionPane.showMessageDialog(null, "Funcionario creado con éxito.");
            
        } catch(SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al crear funcionario.");
            
        }

        
    }//GEN-LAST:event_btnGuardarFuncionarioActionPerformed

    private void txtNombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresActionPerformed

    private void btnEliminarFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarFuncionarioActionPerformed

        if (txtFuncionarioIdEdit.getText().trim().length()==0 ) {
            JOptionPane.showMessageDialog(null, "Seleccione un funcionario para eliminar.");
            txtFuncionarioIdEdit.requestFocus();
            return;
        }        

            int opt = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el funcionario? Recuerde que esta acción también eliminará los Familiares que dependan del Funcionario. ¿Está seguro?", "Confirmar Salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opt == 0){
                
                try {

                    funcionarioController.eliminarFuncionario(Integer.parseInt(txtFuncionarioIdEdit.getText()));   

                cbxTipo_identificacion.setSelectedIndex(0);
                txtIdentificacion.setText("");
                txtNombres.setText("");
                txtApellidos.setText("");
                cbxEstado_Civil.setSelectedIndex(0);
                cbxSexo.setSelectedItem(SELECCIONE);
                txtDireccion.setText("");
                txtTelefono.setText("");
                txtFecha_Nacimiento.setText("");  

                    listFuncionarios();
                    JOptionPane.showMessageDialog(null, "Funcionario eliminado con éxito.");

                    } catch(SQLException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al eliminar el funcionario.");

                    }
            }


        
    }//GEN-LAST:event_btnEliminarFuncionarioActionPerformed

    private void cbxTipo_identificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipo_identificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipo_identificacionActionPerformed

    private void cbxEstadoCivilEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxEstadoCivilEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxEstadoCivilEditActionPerformed

    private void cbxFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFuncionariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFuncionariosActionPerformed

    private void tblFuncionariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFuncionariosMouseClicked
        // TODO add your handling code here:
        
        int i = tblFuncionarios.getSelectedRow();
        //int j = (int) tblFuncionarios.getValueAt(tblFuncionarios.getSelectedRow(), NORMAL);
        int j = (int) tblFuncionarios.getValueAt(tblFuncionarios.getSelectedRow(), 0);
        System.out.println("seltbl  "+i);
        listParientes(j);
        
    }//GEN-LAST:event_tblFuncionariosMouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarFuncionario;
    private javax.swing.JButton btnEliminarFuncionario;
    private javax.swing.JButton btnGuardarFuncionario;
    private javax.swing.JComboBox<Object> cbxEstadoCivilEdit;
    private javax.swing.JComboBox<Object> cbxEstado_Civil;
    private javax.swing.JComboBox<Funcionario> cbxFuncionarios;
    private javax.swing.JComboBox<String> cbxSexo;
    private javax.swing.JComboBox<String> cbxSexoEdit;
    private javax.swing.JComboBox<Object> cbxTipoIdentificacionEdit;
    private javax.swing.JComboBox<Object> cbxTipo_identificacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jPanels;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblApellidosEdit;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDireccionEdit;
    private javax.swing.JLabel lblEstadoCivilEdit;
    private javax.swing.JLabel lblEstado_civil;
    private javax.swing.JLabel lblFechaNacimientoEdit;
    private javax.swing.JLabel lblFecha_Nacimiento;
    private javax.swing.JLabel lblFuncionarios;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblIdentificacion;
    private javax.swing.JLabel lblIdentificacionEdit;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblNombresdEdit;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblSexoEdit;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTipo_Identificación;
    private javax.swing.JTable tblFuncionarios;
    private javax.swing.JTable tblParientes;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtApellidosEdit;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDireccionEdit;
    private javax.swing.JTextField txtFechaNacimientoEdit;
    private javax.swing.JTextField txtFecha_Nacimiento;
    private javax.swing.JTextField txtFuncionarioIdEdit;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtIdentificacionEdit;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNombresEdit;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefonoEdit;
    // End of variables declaration//GEN-END:variables
}
