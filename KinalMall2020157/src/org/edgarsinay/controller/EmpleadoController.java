package org.edgarsinay.controller;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;
import org.edgarsinay.bean.Administracion;
import org.edgarsinay.bean.Cargo;
import org.edgarsinay.bean.Departamento;
import org.edgarsinay.bean.Empleado;
import org.edgarsinay.bean.Horario;
import org.edgarsinay.db.Conexion;
import org.edgarsinay.report.GenerarReporte;
import org.edgarsinay.system.Principal;


public class EmpleadoController implements Initializable{
    private enum operaciones {NUEVO, GUARDAR, ELIMINAR, ACTUALIZAR, CANCELAR, NINGUNO}
    private operaciones tipoDeOperacion = operaciones.NINGUNO;
    public ObservableList<Empleado> listaEmpleado;
    public ObservableList<Departamento> listaDepartamento;
    public ObservableList<Cargo> listaCargo;
    public ObservableList<Horario> listaHorario;
    public ObservableList<Administracion> listaAdministracion;
    private DatePicker fechaContratacion;
    private  Principal escenarioPrincipal;
    
    
    @FXML private Button btnNuevo;
    @FXML private Button btnEliminar;
    @FXML private Button btnEditar;
    @FXML private Button btnReporte;
    @FXML private TextField txtCodigoEmpleado;
    @FXML private TextField txtNombreEmpleado;
    @FXML private TextField txtApellidoEmpleado;
    @FXML private TextField txtCorreoElectronico;
    @FXML private TextField txtTelefonoEmpleado;
    @FXML private TextField txtSueldo;
    @FXML private ComboBox cmbCodigoDepartamento;
    @FXML private ComboBox cmbCodigoCargo;
    @FXML private ComboBox cmbCodigoHorario;
    @FXML private ComboBox cmbCodigoAdministracion;
    @FXML private TableView tblEmpleados;
    @FXML private TableColumn colCodigoEmpleado;
    @FXML private TableColumn colNombreEmpleado;
    @FXML private TableColumn colApellidoEmpleado;
    @FXML private TableColumn colCorreoElectronico;
    @FXML private TableColumn colTelefonoEmpleado;
    @FXML private TableColumn colFechaContratacion;
    @FXML private TableColumn colSueldo;
    @FXML private TableColumn colCodigoDepartamento;
    @FXML private TableColumn colCodigoCargo;
    @FXML private TableColumn colCodigoHorario;
    @FXML private TableColumn colCodigoAdministracion;
    @FXML private ImageView imgNuevo;
    @FXML private ImageView imgEliminar;
    @FXML private ImageView imgEditar;
    @FXML private ImageView imgReporte;
    @FXML private GridPane grpFechaContratacion;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fechaContratacion = new DatePicker(Locale.ENGLISH);
        fechaContratacion.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        fechaContratacion.getCalendarView().todayButtonTextProperty().set("Today");
        fechaContratacion.getCalendarView().setShowWeeks(false);
        grpFechaContratacion.add(fechaContratacion, 5, 1);
        fechaContratacion.getStylesheets().add("/org/edgarsinay/resource/DatePicker.css");
        cargarDatos();
    }
    
    public void cargarDatos(){
        tblEmpleados.setItems(getEmpleado());
        colCodigoEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado,Integer>("codigoEmpleado"));
        colNombreEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado,String>("nombreEmpleado"));
        colApellidoEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado,String>("apellidoEmpleado"));
        colCorreoElectronico.setCellValueFactory(new PropertyValueFactory<Empleado,String>("correoElectronico"));
        colTelefonoEmpleado.setCellValueFactory(new PropertyValueFactory<Empleado,String>("telefonoEmpleado"));
        colFechaContratacion.setCellValueFactory(new PropertyValueFactory<Empleado,Date>("fechaContratacion"));
        colSueldo.setCellValueFactory(new PropertyValueFactory<Empleado,Double>("sueldo"));
        colCodigoDepartamento.setCellValueFactory(new PropertyValueFactory<Departamento,Integer>("codigoDepartamento"));
        colCodigoCargo.setCellValueFactory(new PropertyValueFactory<Cargo,Integer>("codigoCargo"));
        colCodigoHorario.setCellValueFactory(new PropertyValueFactory<Horario,Integer>("codigoHorario"));
        colCodigoAdministracion.setCellValueFactory(new PropertyValueFactory<Administracion,Integer>("codigoAdministracion"));
        
        cmbCodigoDepartamento.setItems(getDepartamento());
        cmbCodigoCargo.setItems(getCargo());
        cmbCodigoHorario.setItems(getHorario());
        cmbCodigoAdministracion.setItems(getAdministracion());
    }
    
    public ObservableList<Empleado> getEmpleado(){
        ArrayList<Empleado> lista = new ArrayList<Empleado>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("call sp_ListarEmpleados()");
            ResultSet resultado = procedimiento.executeQuery();
            while(resultado.next()){
                lista.add(new Empleado(resultado.getInt("codigoEmpleado"),
                                                resultado.getString("nombreEmpleado"),
                                                resultado.getString("apellidoEmpleado"),
                                                resultado.getString("correoElectronico"),
                                                resultado.getString("telefonoEmpleado"),
                                                resultado.getDate("fechaContratacion"),
                                                resultado.getDouble("sueldo"),
                                                resultado.getInt("codigoDepartamento"),
                                                resultado.getInt("codigoCargo"),
                                                resultado.getInt("codigoHorario"),
                                                resultado.getInt("codigoAdministracion")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaEmpleado = FXCollections.observableArrayList(lista);
    }
    
    public ObservableList<Departamento> getDepartamento(){
        ArrayList<Departamento> lista = new ArrayList<Departamento>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarDepartamentos()}");
            ResultSet resultado = procedimiento.executeQuery();
            while(resultado.next()){
                lista.add(new Departamento(resultado.getInt("codigoDepartamento"),
                                           resultado.getString("nombreDepartamento")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaDepartamento = FXCollections.observableArrayList(lista);
    }
     
    public Departamento buscarDepartamento(int codigoDepartamento){
           Departamento resultado = null;
           
           try{
               PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{ call sp_BuscarDepartamentos (?)}");
               procedimiento.setInt(1, codigoDepartamento);
               ResultSet registro = procedimiento.executeQuery();
               while (registro.next()){
                   resultado = new Departamento(registro.getInt("codigoDepartamento"),
                           registro.getString("nombreDepartamento") 
                   );
               }
           }catch(Exception e){
               e.printStackTrace();
           }   
                return resultado;
       }         
    
    public ObservableList<Cargo> getCargo(){
        ArrayList<Cargo> lista = new ArrayList<Cargo>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarCargos()}");
            ResultSet resultado = procedimiento.executeQuery();
            while(resultado.next()){
                lista.add(new Cargo(resultado.getInt("codigoCargo"),
                                    resultado.getString("nombreCargo")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaCargo = FXCollections.observableArrayList(lista);
    }
   
    public Cargo buscarCargo(int codigoCargo){
           Cargo resultado = null;
           try{
               PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{ call sp_BuscarCargos(?)}");
               procedimiento.setInt(1, codigoCargo);
               ResultSet registro = procedimiento.executeQuery();
               while (registro.next()){
                   resultado = new Cargo(registro.getInt("codigoCargo"),
                           registro.getString("nombreCargo")
                   );
               }
           }catch(Exception e){
               e.printStackTrace();
           }
                return resultado;
       }   
   
    public ObservableList<Horario> getHorario(){
       ArrayList<Horario> lista = new ArrayList<Horario>();
       try{
           PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarHorarios()}");
           ResultSet resultado = procedimiento.executeQuery();
           while (resultado.next()){
               lista.add(new Horario(resultado.getInt("codigoHorario"),
                                        resultado.getString("horaEntrada"),
                                        resultado.getString("horaSalida"),
                                        resultado.getBoolean("lunes"),
                                        resultado.getBoolean("martes"),
                                        resultado.getBoolean("miercoles"),
                                        resultado.getBoolean("jueves"),
                                        resultado.getBoolean("viernes")));    
           }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaHorario = FXCollections.observableArrayList(lista);
    }    
   
    public Horario buscarHorario(int codigoHorario){
           Horario resultado = null;
           
           try{
               PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{ call sp_BuscarHorarios(?)}");
               procedimiento.setInt(1, codigoHorario);
               ResultSet registro = procedimiento.executeQuery();
               while (registro.next()){
                   resultado = new Horario(registro.getInt("codigoHorario"),
                           registro.getString("horaEntrada"),
                           registro.getString("horaSalida"),
                           registro.getBoolean("lunes"),
                           registro.getBoolean("martes"),
                           registro.getBoolean("miercoles"),
                           registro.getBoolean("jueves"),
                           registro.getBoolean("viernes"));
               } 
           }catch(Exception e){
               e.printStackTrace();
           }  
                   return resultado;
       }         
   
    public ObservableList<Administracion> getAdministracion(){
        ArrayList<Administracion> lista = new ArrayList<Administracion>();
        try {
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_ListarAdministracion()}");
            ResultSet resultado = procedimiento.executeQuery();
            while(resultado.next()){
                lista.add(new Administracion(resultado.getInt("codigoAdministracion"),
                                                resultado.getString("direccion"),
                                                resultado.getString("telefono")));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaAdministracion = FXCollections.observableArrayList(lista);
    }
                 
   public Administracion buscarAdministracion(int codigoAdministracion){
           Administracion resultado = null;
           try{
               PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{ call sp_BuscarAdministracion (?)}");
               procedimiento.setInt(1, codigoAdministracion);
               ResultSet registro = procedimiento.executeQuery();
               while (registro.next()){
                   resultado = new Administracion(registro.getInt("codigoAdministracion"),
                           registro.getString("direccion"),
                           registro.getString("telefono"));
               }  
           }catch(Exception e){
               e.printStackTrace();
           }    
                   return resultado;
       }
   
    public void seleccionarElemento(){
       if(tblEmpleados.getSelectionModel().getSelectedItem() != null){
           txtCodigoEmpleado.setText(String.valueOf(((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getCodigoEmpleado()));
           txtNombreEmpleado.setText(String.valueOf(((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getNombreEmpleado()));
           txtApellidoEmpleado.setText(String.valueOf(((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getApellidoEmpleado()));
           txtCorreoElectronico.setText(String.valueOf(((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getCorreoElectronico()));
           txtTelefonoEmpleado.setText(String.valueOf(((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getTelefonoEmpleado()));
           fechaContratacion.selectedDateProperty().set(((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getFechaContratacion());
           txtSueldo.setText(String.valueOf(((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getSueldo()));
           cmbCodigoDepartamento.getSelectionModel().select(buscarDepartamento(((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getCodigoDepartamento()));
           cmbCodigoCargo.getSelectionModel().select(buscarCargo(((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getCodigoCargo()));
           cmbCodigoHorario.getSelectionModel().select(buscarHorario(((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getCodigoHorario()));
           cmbCodigoAdministracion.getSelectionModel().select(buscarAdministracion(((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getCodigoAdministracion()));
       } 
    }
   
    public void nuevo(){
        switch(tipoDeOperacion){
            case NINGUNO:
                activarControles();
                activarComboBox();
                limpiarControles();
                btnNuevo.setText("Guardar");
                btnEliminar.setText("Cancelar");
                btnEditar.setDisable(true);
                btnReporte.setDisable(true);
                imgNuevo.setImage(new Image("/org/edgarsinay/images/guardar.png"));
                imgEliminar.setImage(new Image("/org/edgarsinay/images/cancelar.png"));
                tipoDeOperacion = operaciones.GUARDAR;
                break;
            case GUARDAR:
                guardar();
                desactivarControles();
                limpiarControles();
                btnNuevo.setText("Nuevo");
                btnEliminar.setText("Eliminar");
                btnEditar.setDisable(false);
                btnReporte.setDisable(false);
                imgNuevo.setImage(new Image("/org/edgarsinay/images/Add.png"));
                imgEliminar.setImage(new Image("/org/edgarsinay/images/Delete.png"));
                tipoDeOperacion = operaciones.NINGUNO;
                cargarDatos();
                break;
        }
    }
    
    public void guardar(){
        Empleado registro = new Empleado();
        registro.setNombreEmpleado(txtNombreEmpleado.getText());
        registro.setApellidoEmpleado(txtApellidoEmpleado.getText());
        registro.setCorreoElectronico(txtCorreoElectronico.getText());
        registro.setTelefonoEmpleado(txtTelefonoEmpleado.getText());
        registro.setFechaContratacion(fechaContratacion.getSelectedDate());
        registro.setSueldo(Double.parseDouble(txtSueldo.getText()));
        registro.setCodigoDepartamento(((Departamento)cmbCodigoDepartamento.getSelectionModel().getSelectedItem()).getCodigoDepartamento());
        registro.setCodigoCargo(((Cargo)cmbCodigoCargo.getSelectionModel().getSelectedItem()).getCodigoCargo());
        registro.setCodigoHorario(((Horario)cmbCodigoHorario.getSelectionModel().getSelectedItem()).getCodigoHorario());
        registro.setCodigoAdministracion(((Administracion)cmbCodigoAdministracion.getSelectionModel().getSelectedItem()).getCodigoAdministracion());
            try{
                PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarEmpleados(?,?,?,?,?,?,?,?,?,?)}");
                procedimiento.setString(1, registro.getNombreEmpleado());
                procedimiento.setString(2, registro.getApellidoEmpleado());
                procedimiento.setString(3, registro.getCorreoElectronico());
                procedimiento.setString(4, registro.getTelefonoEmpleado());
                procedimiento.setDate(5, new java.sql.Date(registro.getFechaContratacion().getTime()));
                procedimiento.setDouble(6, registro.getSueldo());
                procedimiento.setInt(7, registro.getCodigoDepartamento());
                procedimiento.setInt(8, registro.getCodigoCargo());
                procedimiento.setInt(9, registro.getCodigoHorario());
                procedimiento.setInt(10, registro.getCodigoAdministracion());
                procedimiento.execute();
                listaEmpleado.add(registro);
           }catch(Exception e){
               e.printStackTrace();
           }
      }    
    
    public void eliminar(){
        switch(tipoDeOperacion){
            case GUARDAR:
                desactivarControles();
                limpiarControles();
                btnNuevo.setText("Nuevo");
                btnEliminar.setText("Eliminar");
                imgNuevo.setImage(new Image("/org/edgarsinay/images/Add.png"));
                imgEliminar.setImage(new Image("/org/edgarsinay/images/Delete.png"));
                btnEditar.setDisable(false);
                btnReporte.setDisable(false);
                tipoDeOperacion = operaciones.NINGUNO;
                break;
            default:
                if(tblEmpleados.getSelectionModel().getSelectedItem()!= null){
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar el registro?","Eliminar Empleados", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(respuesta == JOptionPane.YES_OPTION){
                       try{
                           PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EliminarEmpleados(?)}");
                           procedimiento.setInt(1,((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getCodigoEmpleado()); 
                           procedimiento.execute();
                           listaEmpleado.remove(tblEmpleados.getSelectionModel().getSelectedIndex());
                           limpiarControles();
                       }catch(Exception e){
                           e.printStackTrace();
                       }
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento");
            }
        }
    }
    
    public void editar(){
        switch(tipoDeOperacion){
            case NINGUNO:
                if(tblEmpleados.getSelectionModel().getSelectedItem() != null){
                 btnEditar.setText("Actualizar");
                    btnReporte.setText("Cancelar");
                    btnNuevo.setDisable(true);
                    btnEliminar.setDisable(true);
                    imgEditar.setImage(new Image("/org/edgarsinay/images/Actualizar.png"));
                    imgReporte.setImage(new Image("/org/edgarsinay/images/cancelar.png"));
                    activarControles();
                    tipoDeOperacion = operaciones.ACTUALIZAR;
                
                }else{JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento.");
                
                }
                break;
                
            case ACTUALIZAR:
                actualizar();
                btnNuevo.setDisable(false);
                btnEliminar.setDisable(false);
                btnEditar.setText("Editar");
                btnReporte.setText("Reporte");
                imgEditar.setImage(new Image("/org/edgarsinay/images/Edit.png"));
                imgReporte.setImage(new Image("/org/edgarsinay/images/Report.png"));
                limpiarControles();
                desactivarControles();
                cargarDatos();
                tipoDeOperacion = operaciones.NINGUNO;
                break;
        }
    }
    
    public void actualizar(){
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_EditarEmpleados(?,?,?,?,?,?,?)}");
            Empleado registro = (Empleado)tblEmpleados.getSelectionModel().getSelectedItem();
            registro.setNombreEmpleado(txtNombreEmpleado.getText());
            registro.setApellidoEmpleado(txtApellidoEmpleado.getText());
            registro.setCorreoElectronico(txtCorreoElectronico.getText());
            registro.setTelefonoEmpleado(txtTelefonoEmpleado.getText());
            registro.setFechaContratacion(fechaContratacion.getSelectedDate());
            registro.setSueldo(Double.parseDouble(txtSueldo.getText()));
            procedimiento.setInt(1, registro.getCodigoEmpleado());
            procedimiento.setString(2, registro.getNombreEmpleado());
            procedimiento.setString(3, registro.getApellidoEmpleado());
            procedimiento.setString(4, registro.getCorreoElectronico());
            procedimiento.setString(5, registro.getTelefonoEmpleado());        
            procedimiento.setDate(6, new java.sql.Date(registro.getFechaContratacion().getTime()));
            procedimiento.setDouble(7, registro.getSueldo());      
            procedimiento.execute();
            cargarDatos();
            tipoDeOperacion = operaciones.NINGUNO;    
           }catch(Exception e){
               e.printStackTrace();
        }
    }
    
    public void generarReporte(){
        switch (tipoDeOperacion){
            case ACTUALIZAR:
                desactivarControles();
                limpiarControles();
                btnEditar.setText("Editar");
                btnReporte.setText("Reporte");
                imgEditar.setImage(new Image("/org/edgarsinay/images/Edit.png"));
                imgReporte.setImage(new Image("/org/edgarsinay/images/Report.png"));
                btnNuevo.setDisable(false);
                btnEliminar.setDisable(false);
                tipoDeOperacion = operaciones.NINGUNO;
                break;
                
            case NINGUNO:
               imprimirReporte();
                break;
        }
    }
    
    public void imprimirReporte(){
        Map parametros = new HashMap();
        int codEmpleado = ((Empleado)tblEmpleados.getSelectionModel().getSelectedItem()).getCodigoEmpleado();
        parametros.put("codEmpleado", codEmpleado);
        GenerarReporte.mostrarReporte("ReporteEmpleado.jasper", "Reporte Empleado", parametros);
    
    }
    
    public void desactivarControles(){
       txtCodigoEmpleado.setEditable(false);
       txtNombreEmpleado.setEditable(false);
       txtApellidoEmpleado.setEditable(false);
       txtCorreoElectronico.setEditable(false);
       txtTelefonoEmpleado.setEditable(false);
       fechaContratacion.setDisable(true);
       txtSueldo.setEditable(false);
       cmbCodigoDepartamento.setDisable(true);
       cmbCodigoCargo.setDisable(true);
       cmbCodigoHorario.setDisable(true);
       cmbCodigoAdministracion.setDisable(true);
   }
   
   public void activarControles(){
       txtCodigoEmpleado.setEditable(false);
       txtNombreEmpleado.setEditable(true);
       txtApellidoEmpleado.setEditable(true);
       txtCorreoElectronico.setEditable(true);
       txtTelefonoEmpleado.setEditable(true);
       fechaContratacion.setDisable(false);
       txtSueldo.setEditable(true);
   }
   
   public void activarComboBox(){
       cmbCodigoDepartamento.setDisable(false);
       cmbCodigoCargo.setDisable(false);
       cmbCodigoHorario.setDisable(false);
       cmbCodigoAdministracion.setDisable(false);  
   }
   
   public void limpiarControles(){
       txtCodigoEmpleado.clear();
       txtNombreEmpleado.clear();
       txtApellidoEmpleado.clear();
       txtCorreoElectronico.clear();
       txtTelefonoEmpleado.clear();
       fechaContratacion.setSelectedDate(null);
       txtSueldo.clear();
         cmbCodigoDepartamento.setValue(null);
       cmbCodigoCargo.setValue(null);
       cmbCodigoHorario.setValue(null);
       cmbCodigoAdministracion.setValue(null);
   }
    
public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }    
    
    public void menuPrincipal(){
        escenarioPrincipal.menuPrincipal();
    }
    
    public void ventanaCargo(){
       escenarioPrincipal.ventanaCargo();
   }
}
