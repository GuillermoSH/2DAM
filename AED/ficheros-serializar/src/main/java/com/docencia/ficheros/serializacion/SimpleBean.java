package com.docencia.ficheros.serializacion;

import java.io.Serializable;

public class SimpleBean implements Serializable{
    private int x = 1;
    private int y = 2;
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}
