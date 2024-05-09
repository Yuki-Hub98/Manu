package br.com.manu.persistence.entity.administracaoDePreco;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

public class AdministracaoDePreco {
    @Id
    private String id;
    private long codigo;
    private String descricaoItem;
    private String departamento;
    private String codBarra;
    private String linha;
    private String modelo;
    private double precoAtual;
    private double valorCusto;
    private double margemAtual;
    private double margemProposta;
    private double precoProposto;
    private double margemProgramada;
    private double precoProgramado;
    @CreatedDate
    private Date dataCriacao;
    @LastModifiedDate
    private Date dataModificacao;

    public AdministracaoDePreco() {

    }

    public AdministracaoDePreco(long codigo, String descricaoItem, String departamento, String codBarra, String linha, String modelo,
                                double precoAtual, double valorCusto, double margemAtual, double margemProposta, double precoProposto,
                                double margemProgramada, double precoProgramado) {
        this.codigo = codigo;
        this.descricaoItem = descricaoItem;
        this.departamento = departamento;
        this.codBarra = codBarra;
        this.linha = linha;
        this.modelo = modelo;
        this.precoAtual = precoAtual;
        this.valorCusto = valorCusto;
        this.margemAtual = margemAtual;
        this.margemProposta = margemProposta;
        this.precoProposto = precoProposto;
        this.margemProgramada = margemProgramada;
        this.precoProgramado = precoProgramado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public double getPrecoAtual() {
        return precoAtual;
    }

    public void setPrecoAtual(double precoAtual) {
        this.precoAtual = precoAtual;
    }

    public double getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(double valorCusto) {
        this.valorCusto = valorCusto;
    }

    public double getMargemAtual() {
        return margemAtual;
    }

    public void setMargemAtual(double margemAtual) {
        this.margemAtual = margemAtual;
    }

    public double getMargemProposta() {
        return margemProposta;
    }

    public void setMargemProposta(double margemProposta) {
        this.margemProposta = margemProposta;
    }

    public double getPrecoProposto() {
        return precoProposto;
    }

    public void setPrecoProposto(double precoProposto) {
        this.precoProposto = precoProposto;
    }

    public double getMargemProgramada() {
        return margemProgramada;
    }

    public void setMargemProgramada(double margemProgramada) {
        this.margemProgramada = margemProgramada;
    }

    public double getPrecoProgramado() {
        return precoProgramado;
    }

    public void setPrecoProgramado(double precoProgramado) {
        this.precoProgramado = precoProgramado;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(Date dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
}
