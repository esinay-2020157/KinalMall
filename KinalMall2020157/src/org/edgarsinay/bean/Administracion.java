package org.edgarsinay.bean;


public class Administracion {
   //Atributos 
    private int codigoAdministracion;
    private String direccion;
    private String telefono;
    
    
   //Constructores
        public Administracion() {
    }
        public Administracion(int codigoAdministracion, String direccion, String telefono) {
        this.codigoAdministracion = codigoAdministracion;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    
   // Modos de Accesos

    public int getCodigoAdministracion() {
        return codigoAdministracion;
    }

    public void setCodigoAdministracion(int codigoAdministracion) {
        this.codigoAdministracion = codigoAdministracion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

@Override 
public String toString(){
    return getCodigoAdministracion() + " | " + getTelefono();
    }

}
