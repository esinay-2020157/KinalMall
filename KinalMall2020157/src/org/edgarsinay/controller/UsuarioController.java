package org.edgarsinay.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.edgarsinay.bean.Usuario;
import org.edgarsinay.db.Conexion;
import org.edgarsinay.system.Principal;

public class UsuarioController implements Initializable {
        private Principal escenarioPrincipal;
        private enum operaciones {GUARDAR, NINGUNO,ACTUALIZAR};
        private operaciones tipoDeOperacion = operaciones.NINGUNO;
        @FXML private TextField txtCodigoUsuario;
        @FXML private TextField txtNombreUsuario;
        @FXML private TextField txtApellidoUsuario;
        @FXML private TextField txtUsuario;
        @FXML private TextField txtPassword;
        @FXML private Button btnNuevo;
        @FXML private Button btnEliminar;
        @FXML private ImageView imgNuevo;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
   
    }
    
    public void nuevo(){
        switch (tipoDeOperacion){
            case NINGUNO:
                activarControles();
                limpiarControles();
                btnNuevo.setText("Guardar");
                imgNuevo.setImage(new Image("/org/edgarsinay/images/guardar.png"));
                tipoDeOperacion = operaciones.GUARDAR;
                break;
            case GUARDAR:
                guardar();
                desactivarControles();
                limpiarControles();
                btnNuevo.setText("Nuevo");
                imgNuevo.setImage(new Image("/org/edgarsinay/images/Add.png"));
                tipoDeOperacion = operaciones.NINGUNO;
                break;       
        }
    
    }
    
    public void guardar(){
        Usuario registro = new Usuario();
        registro.setNombreUsuario(txtNombreUsuario.getText());
        registro.setApellidoUsuario(txtApellidoUsuario.getText());
        registro.setUsuarioLogin(txtUsuario.getText());
        registro.setContrasena(txtPassword.getText());
        try{
            PreparedStatement procedimiento = Conexion.getInstance().getConexion().prepareCall("{call sp_AgregarUsuario(?,?,?,?)}");
            procedimiento.setString(1, registro.getNombreUsuario());
            procedimiento.setString(2, registro.getApellidoUsuario());
            procedimiento.setString(3, registro.getUsuarioLogin());
            procedimiento.setString(4, registro.getContrasena());
            procedimiento.execute();
            ventanaLogin();
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }
    
    public void desactivarControles(){
        txtCodigoUsuario.setEditable(false);
        txtNombreUsuario.setEditable(false);
        txtApellidoUsuario.setEditable(false);
        txtUsuario.setEditable(false);
        txtPassword.setEditable(false);    
    }
    
    public void activarControles(){
        txtCodigoUsuario.setEditable(false);
        txtNombreUsuario.setEditable(true);
        txtApellidoUsuario.setEditable(true);
        txtUsuario.setEditable(true);
        txtPassword.setEditable(true);   
    }
    
    public void limpiarControles(){
        txtNombreUsuario.clear();
        txtApellidoUsuario.clear();
        txtUsuario.clear();
        txtPassword.clear();
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
    
    public void ventanaLogin(){
        escenarioPrincipal.ventanaLogin();
    }
}
