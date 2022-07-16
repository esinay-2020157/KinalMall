package org.edgarsinay.bean;

public class Cargo {
    //Atributos
    private int codigoCargo;
    private String nombreCargo;
    
    // Costructores

    public Cargo() {
    }

    public Cargo(int codigoCargo, String nombreCargo) {
        this.codigoCargo = codigoCargo;
        this.nombreCargo = nombreCargo;
    }

    public int getCodigoCargo() {
        return codigoCargo;
    }

    public void setCodigoCargo(int codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }
        
    
    @Override
    public String toString(){
        return getCodigoCargo()+ " | " + getNombreCargo();
    
    }
}
