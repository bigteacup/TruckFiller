package com.example.truckfiller;

public class Palette {
    public boolean rangLongueur = false;
    public boolean rangLargeur = false;
    public boolean rangBatardLongueurLongueur = false;
    public boolean rangBatardLongueurLargeur = false;
    public boolean rangBatardLargeurLongueur = false;
    public boolean rangBatardLargeurLargeur = false;

    int id;
    int largeur = 0;
    int longueur = 0;
    int hauteur = 0;
    int poids = 0;
    int orientation = 0; //  0 en longueur                   1 en largeur
    int positionX = 0;
    int positionY = 0;
    int positionXb = 0;
    int positionYb = 0;
    int surface = 0;
    int orientationFavorite = 0;


    public int getOrientationFavorite() {
        return orientationFavorite;
    }

    public void setOrientationFavorite(int orientationFavorite) {
        this.orientationFavorite = orientationFavorite;
    }



    public int getSurface() {
        surface = largeur * longueur;
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
        if(orientation == 0) {
            this.positionXb = positionX+largeur;
            this.positionYb = positionY+longueur;
        }else{
            this.positionXb = positionX+longueur;
            this.positionYb = positionY+largeur;
        }
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;

    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getPositionXb() {
        return positionXb;
    }

    public void setPositionXb(int positionXb) {
        this.positionXb = positionXb;
    }

    public int getPositionYb() {
        return positionYb;
    }

    public void setPositionYb(int positionYb) {
        this.positionYb = positionYb;
    }
}
