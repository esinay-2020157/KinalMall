package org.edgarsinay.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import org.edgarsinay.system.Principal;

public class MenuPrincipalController implements Initializable{
    private Principal escenarioPrincipal;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
       
   public void ventanaProgramador(){
       escenarioPrincipal.ventanaProgramador();
    }
    
   public void ventanaAdministracion(){
       escenarioPrincipal.ventanaAdministracion();
    }
   
   public void ventanaTipoCliente(){
       escenarioPrincipal.ventanaTipoCliente();
    }
   
   public void ventanaLocales(){
    escenarioPrincipal.ventanaLocales();
    }

   public void ventanaClientes(){ 
       escenarioPrincipal.ventanaClientes();
    }

   public void ventanaDepartamento(){
       escenarioPrincipal.ventanaDepartamento();
    }
   
   public void ventanaProveedores(){
       escenarioPrincipal.ventanaProveedores();
    } 
   
   public void ventanaCuentasPorPagar(){
       escenarioPrincipal.ventanaCuentasPorPagar();
    }
   
   public void ventanaHorario(){
       escenarioPrincipal.ventanaHorario();
    }
   
   public void ventanaCargo(){
       escenarioPrincipal.ventanaCargo();
   }
   
   public void ventanaLogin(){
       escenarioPrincipal.ventanaLogin();   
   }
   
   public void ventanaUsuario(){
       escenarioPrincipal.ventanaUsuario();
   }
   
   public void ventanaCuentasPorCobrar(){
       escenarioPrincipal.ventanaCuentasPorCobrar();
   }
   
   public void ventanaEmpleado(){
       escenarioPrincipal.ventanaEmpleado();
   }
}


