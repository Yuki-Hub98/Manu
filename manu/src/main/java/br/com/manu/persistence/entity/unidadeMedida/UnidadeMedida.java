package br.com.manu.persistence.entity.unidadeMedida;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class UnidadeMedida {
    @Id
    private String id;
    private String metro;
    private String unidade;
    private String caixa;
    private String milheiro;
    private String pacote;
    private String par;
    private String tonel;
    private String quilograma;
    private String conjunto;
    private String metroQuadrado;
    private String rolo;
    private String metroCubico;
    private String chapa;
    private String cento;
    private String galao;
    private String centimentro;
    private String litro;
    private String mililitro;

    public UnidadeMedida() {
    }

    public UnidadeMedida(String id, String metro, String unidade, String caixa, String milheiro, String pacote, String par, String tonel, String quilograma,
                         String conjunto, String metroQuadrado, String rolo, String metroCubico, String chapa, String cento, String galao, String centimentro,
                         String litro, String mililitro) {
        this.id = id;
        this.metro = metro;
        this.unidade = unidade;
        this.caixa = caixa;
        this.milheiro = milheiro;
        this.pacote = pacote;
        this.par = par;
        this.tonel = tonel;
        this.quilograma = quilograma;
        this.conjunto = conjunto;
        this.metroQuadrado = metroQuadrado;
        this.rolo = rolo;
        this.metroCubico = metroCubico;
        this.chapa = chapa;
        this.cento = cento;
        this.galao = galao;
        this.centimentro = centimentro;
        this.litro = litro;
        this.mililitro = mililitro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMetro() {
        return metro;
    }

    public void setMetro(String metro) {
        this.metro = metro;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getCaixa() {
        return caixa;
    }

    public void setCaixa(String caixa) {
        this.caixa = caixa;
    }

    public String getMilheiro() {
        return milheiro;
    }

    public void setMilheiro(String milheiro) {
        this.milheiro = milheiro;
    }

    public String getPacote() {
        return pacote;
    }

    public void setPacote(String pacote) {
        this.pacote = pacote;
    }

    public String getPar() {
        return par;
    }

    public void setPar(String par) {
        this.par = par;
    }

    public String getTonel() {
        return tonel;
    }

    public void setTonel(String tonel) {
        this.tonel = tonel;
    }

    public String getQuilograma() {
        return quilograma;
    }

    public void setQuilograma(String quilograma) {
        this.quilograma = quilograma;
    }

    public String getConjunto() {
        return conjunto;
    }

    public void setConjunto(String conjunto) {
        this.conjunto = conjunto;
    }

    public String getMetroQuadrado() {
        return metroQuadrado;
    }

    public void setMetroQuadrado(String metroQuadrado) {
        this.metroQuadrado = metroQuadrado;
    }

    public String getRolo() {
        return rolo;
    }

    public void setRolo(String rolo) {
        this.rolo = rolo;
    }

    public String getMetroCubico() {
        return metroCubico;
    }

    public void setMetroCubico(String metroCubico) {
        this.metroCubico = metroCubico;
    }

    public String getChapa() {
        return chapa;
    }

    public void setChapa(String chapa) {
        this.chapa = chapa;
    }

    public String getCento() {
        return cento;
    }

    public void setCento(String cento) {
        this.cento = cento;
    }

    public String getGalao() {
        return galao;
    }

    public void setGalao(String galao) {
        this.galao = galao;
    }

    public String getCentimentro() {
        return centimentro;
    }

    public void setCentimentro(String centimentro) {
        this.centimentro = centimentro;
    }

    public String getLitro() {
        return litro;
    }

    public void setLitro(String litro) {
        this.litro = litro;
    }

    public String getMililitro() {
        return mililitro;
    }

    public void setMililitro(String mililitro) {
        this.mililitro = mililitro;
    }
}
