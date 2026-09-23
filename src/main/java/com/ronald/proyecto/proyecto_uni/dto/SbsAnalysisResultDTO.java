package com.ronald.proyecto.proyecto_uni.dto;

import java.util.List;

public class SbsAnalysisResultDTO {

    private String calificacion;
    private Double porcentajeNormal;
    private Double porcentajeCpp;
    private Double porcentajeDeficiente;
    private Double porcentajeDudoso;
    private Double porcentajePerdida;
    private Double deudaTotal;
    private List<String> entidades;
    private Integer numeroEntidades;
    private String semaforo; // VERDE, AMARILLO, ROJO
    private Integer scoreCrediticio;
    private Double limiteSugerido;
    private String nivelRiesgo;
    private String recomendacion;
    private String resumenIa;
    private String documentoUrl;
    private String fechaEvaluacion;

    public SbsAnalysisResultDTO() {
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public Double getPorcentajeNormal() {
        return porcentajeNormal;
    }

    public void setPorcentajeNormal(Double porcentajeNormal) {
        this.porcentajeNormal = porcentajeNormal;
    }

    public Double getPorcentajeCpp() {
        return porcentajeCpp;
    }

    public void setPorcentajeCpp(Double porcentajeCpp) {
        this.porcentajeCpp = porcentajeCpp;
    }

    public Double getPorcentajeDeficiente() {
        return porcentajeDeficiente;
    }

    public void setPorcentajeDeficiente(Double porcentajeDeficiente) {
        this.porcentajeDeficiente = porcentajeDeficiente;
    }

    public Double getPorcentajeDudoso() {
        return porcentajeDudoso;
    }

    public void setPorcentajeDudoso(Double porcentajeDudoso) {
        this.porcentajeDudoso = porcentajeDudoso;
    }

    public Double getPorcentajePerdida() {
        return porcentajePerdida;
    }

    public void setPorcentajePerdida(Double porcentajePerdida) {
        this.porcentajePerdida = porcentajePerdida;
    }

    public Double getDeudaTotal() {
        return deudaTotal;
    }

    public void setDeudaTotal(Double deudaTotal) {
        this.deudaTotal = deudaTotal;
    }

    public List<String> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<String> entidades) {
        this.entidades = entidades;
    }

    public Integer getNumeroEntidades() {
        return numeroEntidades;
    }

    public void setNumeroEntidades(Integer numeroEntidades) {
        this.numeroEntidades = numeroEntidades;
    }

    public String getSemaforo() {
        return semaforo;
    }

    public void setSemaforo(String semaforo) {
        this.semaforo = semaforo;
    }

    public Integer getScoreCrediticio() {
        return scoreCrediticio;
    }

    public void setScoreCrediticio(Integer scoreCrediticio) {
        this.scoreCrediticio = scoreCrediticio;
    }

    public Double getLimiteSugerido() {
        return limiteSugerido;
    }

    public void setLimiteSugerido(Double limiteSugerido) {
        this.limiteSugerido = limiteSugerido;
    }

    public String getNivelRiesgo() {
        return nivelRiesgo;
    }

    public void setNivelRiesgo(String nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public String getResumenIa() {
        return resumenIa;
    }

    public void setResumenIa(String resumenIa) {
        this.resumenIa = resumenIa;
    }

    public String getDocumentoUrl() {
        return documentoUrl;
    }

    public void setDocumentoUrl(String documentoUrl) {
        this.documentoUrl = documentoUrl;
    }

    public String getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(String fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }
}
