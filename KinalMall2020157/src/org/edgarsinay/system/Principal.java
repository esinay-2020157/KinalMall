/*
    Nombre del programador: Edgar Josué Sinay Medina
    Codigo Técnico: IN5AV
    Carné: 2020157
    Fecha de creación:
    05-05-2021
    Modificaciones:
    05-05-2021
    06-05-2021
    09-05-2021
    12-05-2021
    13-05-2021
    26-05-2021
    02-06-2021
    03-06-2021
    04-06-2021
    08-06-2021
    09-06-2021
    10-06-2021
    15-06-2021
    16-06-2021
    17-06-2021
    01-07-2021
    02-07-2021
    04-07-2021
    05-07-2021
    08-07-2021
    05/08/2021
    06/08/2021
    07/08/2021
    08/08/2021
    11/08/2021
    12/08/2021
 */

package org.edgarsinay.system;

import java.io.IOException;
import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.edgarsinay.bean.CuentasPorPagar;
import org.edgarsinay.controller.AdministracionController;
import org.edgarsinay.controller.CargoController;
import org.edgarsinay.controller.ClienteController;
import org.edgarsinay.controller.CuentasPorCobrarController;
import org.edgarsinay.controller.CuentasPorPagarController;
import org.edgarsinay.controller.DepartamentoController;
import org.edgarsinay.controller.EmpleadoController;
import org.edgarsinay.controller.HorarioController;
import org.edgarsinay.controller.LocalesController;
import org.edgarsinay.controller.LoginController;
import org.edgarsinay.controller.MenuPrincipalController;
import org.edgarsinay.controller.ProgramadorController;
import org.edgarsinay.controller.ProveedorController;
import org.edgarsinay.controller.TipoClienteController;
import org.edgarsinay.controller.UsuarioController;

/**
 *
 * @author Josue
 */
public class Principal extends Application {
    
    
    
    private Stage escenarioPrincipal;
    private Scene escena;
    private final String PAQUETE_VISTA = "/org/edgarsinay/view/";
    @Override
    public void start(Stage escenarioPrincipal) throws IOException {
        this.escenarioPrincipal = escenarioPrincipal;
        this.escenarioPrincipal.setTitle("Kinal Mall 2021");
        escenarioPrincipal.getIcons().add(new Image("/org/edgarsinay/images/Logo.png"));
         ventanaLogin();
          //ventanaUsuario();
          //menuPrincipal();
        escenarioPrincipal.show();
    }
    
    public void menuPrincipal(){
        try{
        MenuPrincipalController menuPrincipal = (MenuPrincipalController)cambiarEscena("MenuPrincipalView.fxml", 459, 379);
        menuPrincipal.setEscenarioPrincipal(this);
            }catch(Exception e){
                e.printStackTrace();
        }
    }
    
    public void ventanaProgramador(){
        try{
        ProgramadorController programador = (ProgramadorController)cambiarEscena("ProgramadorView.fxml", 600, 400);
        programador.setEscenarioPrincipal(this);
            }catch(Exception e){
                e.printStackTrace();
        }        
    }
    
    public void ventanaAdministracion(){
        try{
        AdministracionController administracion = (AdministracionController)cambiarEscena("AdministracionView.fxml", 800, 500);
        administracion.setEscenarioPrincipal(this);
           }catch(Exception e){
                e.printStackTrace();   
        }  
    }
          
    public void ventanaTipoCliente(){
        try{
        TipoClienteController tipoCliente = (TipoClienteController)cambiarEscena("TipoClienteView.fxml", 800, 500);
        tipoCliente.setEscenarioPrincipal(this);
            }catch(Exception e){
                e.printStackTrace(); 
        }    
    }
        
    public void ventanaLocales(){
        try{
        LocalesController locales = (LocalesController) cambiarEscena("LocalesView.fxml", 1000, 500);
        locales.setEscenarioPrincipal(this);
            }catch(Exception e){
                e.printStackTrace();
        }
    }
    
    public void ventanaClientes(){
        try{
        ClienteController cliente = (ClienteController) cambiarEscena ("ClienteView.fxml", 1220, 500);
        cliente.setEscenarioPrincipal(this);            
            }catch(Exception e){
                e.printStackTrace();
        }
    }
        
        
    public void ventanaDepartamento(){
        try{
        DepartamentoController departamento = (DepartamentoController) cambiarEscena("DepartamentoView.fxml",800, 500);
        departamento.setEscenarioPrincipal(this);         
            }catch(Exception e){
                e.printStackTrace();
        }
    }
    
    public void ventanaProveedores(){
        try{
        ProveedorController proveedor = (ProveedorController)cambiarEscena("ProveedorView.fxml", 1220, 500);
        proveedor.setEscenarioPrincipal(this); 
            }catch(Exception e){
                e.printStackTrace();
        }
    }
        
    public void ventanaCuentasPorPagar(){
        try{
        CuentasPorPagarController cuentasPorPagar = (CuentasPorPagarController)cambiarEscena("CuentasPorPagarView.fxml", 1220, 500);
        cuentasPorPagar.setEscenarioPrincipal(this);
            }catch(Exception e){
                e.printStackTrace();
        }
    }
    
    public void ventanaHorario(){
        try{
        HorarioController horario = (HorarioController)cambiarEscena("HorarioView.fxml", 1220, 500);
        horario.setEscenarioPrincipal(this);
            }catch(Exception e){
                e.printStackTrace();
        }
    }
    
    public void ventanaUsuario(){
        try{
            UsuarioController usuario =  (UsuarioController)cambiarEscena("UsuarioView.fxml", 662, 500);
            usuario.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaCargo(){
        try{
            CargoController cargo = (CargoController)cambiarEscena("CargoView.fxml", 800, 500);
            cargo.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaLogin(){
        try{
            LoginController login = (LoginController)cambiarEscena("LoginView.fxml", 600, 400);
            login.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaCuentasPorCobrar(){
        try{
            CuentasPorCobrarController cuentasPorCobrar = (CuentasPorCobrarController)cambiarEscena("CuentasPorCobrarView.fxml", 1220, 500);
            cuentasPorCobrar.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void ventanaEmpleado(){
        try{
            EmpleadoController empleado = (EmpleadoController)cambiarEscena("EmpleadoView.fxml", 1255, 500);
            empleado.setEscenarioPrincipal(this);
        }catch(Exception e){
            e.printStackTrace();
        
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
   
    public Initializable cambiarEscena(String fxml, int ancho, int alto) throws IOException{
    Initializable resultado = null;
    FXMLLoader cargadorFXML = new FXMLLoader();
    InputStream archivo = Principal.class.getResourceAsStream(PAQUETE_VISTA+fxml);
    cargadorFXML.setBuilderFactory(new JavaFXBuilderFactory());
    cargadorFXML.setLocation(Principal.class.getResource(PAQUETE_VISTA+fxml));
    escena = new Scene((AnchorPane)cargadorFXML.load(archivo), ancho, alto);
    escenarioPrincipal.setScene(escena);
    escenarioPrincipal.sizeToScene();
    resultado = (Initializable)cargadorFXML.getController();
    return resultado;
    }
   
}




