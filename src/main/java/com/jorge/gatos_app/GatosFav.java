/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jorge.gatos_app;

/**
 *
 * @author antonioportada
 */
public class GatosFav {
    
    private String id;
    private String image_id;
    private String apikey = "";
    private Imagex imag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public Imagex getImag() {
        return imag;
    }

    public void setImag(Imagex imag) {
        this.imag = imag;
    }
}
