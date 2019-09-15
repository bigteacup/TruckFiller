package com.example.truckfiller;

import java.util.ArrayList;

public class Rang {
    int idDuRang = 0;
    int NombreDEmplacements;
    ArrayList<Palette> listePaletteDuRang = new ArrayList<Palette>();
    int surface = 0;

    public int getSurface() {
        surface = 0;
        for(int i = 0; i < listePaletteDuRang.size(); i++){
            surface = surface + (listePaletteDuRang.get(i).getSurface());
        }
            //surface = largeur * hauteur;
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }




    public int getIdDuRang() {
        return idDuRang;
    }

    public void setIdDuRang(int idDuRang) {
        this.idDuRang = idDuRang;
    }

    public int getNombreDEmplacements() {
        return NombreDEmplacements;
    }

    public void setNombreDEmplacements(int nombreDEmplacements) {
        NombreDEmplacements = nombreDEmplacements;
    }

    public ArrayList<Palette> getListePaletteDuRang() {
        return listePaletteDuRang;
    }

    public void setListePaletteDuRang(ArrayList<Palette> listePaletteDuRang) {
        this.listePaletteDuRang = listePaletteDuRang;
    }




}
