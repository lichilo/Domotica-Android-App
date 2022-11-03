package com.electronicsenterprice.finaltry2_3.db;

public class extrasConfigs {

    private Integer id;
    private String NombreCanal;

    private String CanalCustom ;

    public extrasConfigs(Integer id, String nombreCanal) {
        this.id = id;
        this.NombreCanal = nombreCanal;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreCanal() {
        return NombreCanal;
    }

    public void setNombreCanal(String nombreCanal) {
        NombreCanal = nombreCanal;
    }

    @Override
    public String toString() {

        this.CanalCustom = this.CanalCustom = NombreCanal;
        return CanalCustom;
    }
}
