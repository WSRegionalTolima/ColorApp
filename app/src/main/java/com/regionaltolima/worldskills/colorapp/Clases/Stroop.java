package com.regionaltolima.worldskills.colorapp.Clases;

public class Stroop {

    private String nombreColor;
    private int colorPalabra;

    public Stroop(String nombreColor, int colorPalabra) {
        this.nombreColor = nombreColor;
        this.colorPalabra = colorPalabra;
    }

    public String getNombreColor() {
        return nombreColor;
    }

    public void setNombreColor(String nombreColor) {
        this.nombreColor = nombreColor;
    }

    public int getColorPalabra() {
        return colorPalabra;
    }

    public void setColorPalabra(int colorPalabra) {
        this.colorPalabra = colorPalabra;
    }


}
