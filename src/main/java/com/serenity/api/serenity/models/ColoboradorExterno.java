package com.serenity.api.serenity.models;

import com.serenity.api.serenity.interfaces.IColoborador;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ColoboradorExterno implements IColoborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String regiaoResidencia;
    private Integer idUsuario;
    private String intermitente;
    private Boolean ASO;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getRegiaoResidencia() {
        return regiaoResidencia;
    }

    public void setRegiaoResidencia(String regiaoResidencia) {
        this.regiaoResidencia = regiaoResidencia;
    }

    public String getIntermitente() {
        return intermitente;
    }

    public void setIntermitente(String intermitente) {
        this.intermitente = intermitente;
    }

    public Boolean getASO() {
        return ASO;
    }

    public void setASO(Boolean ASO) {
        this.ASO = ASO;
    }
}
