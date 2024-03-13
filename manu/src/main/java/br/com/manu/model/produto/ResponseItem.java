package br.com.manu.model.produto;
import java.util.Objects;

public class ResponseItem {
    private long codigoItem;
    private String descricaoItem;
    private String codBarra;
    private String departamento;
    private String linha;
    private String familia;
    private String grupo;
    private String fornecedor;
    private String modelo;
    private String tipoProduto;
    private String unidadeMedida;
    private String cor;
    private String especificacao;
    private String processado;

    public ResponseItem() {

    }

    public ResponseItem(long codigoItem, String descricaoItem, String codBarra, String departamento, String linha, String familia, String grupo, String fornecedor,
                        String modelo, String tipoProduto, String unidadeMedida, String cor, String especificacao, String processado) {
        this.codigoItem = codigoItem;
        this.descricaoItem = descricaoItem;
        this.codBarra = codBarra;
        this.departamento = departamento;
        this.linha = linha;
        this.familia = familia;
        this.grupo = grupo;
        this.fornecedor = fornecedor;
        this.modelo = modelo;
        this.tipoProduto = tipoProduto;
        this.unidadeMedida = unidadeMedida;
        this.cor = cor;
        this.especificacao = especificacao;
        processado = processado;
    }

    public long getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(long codigoItem) {
        this.codigoItem = codigoItem;
    }

    public String getDescricaoItem() {
        return descricaoItem;
    }

    public void setDescricaoItem(String descricaoItem) {
        this.descricaoItem = descricaoItem;
    }

    public String getCodBarra() {
        return codBarra;
    }

    public void setCodBarra(String codBarra) {
        this.codBarra = codBarra;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
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

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public String getProcessado() {
        return processado;
    }

    public void setProcessado(String processado) {
        this.processado = processado;
    }

    /**
    * Implementação do método hashCode() e equals() para o response não repetir os objetos.
    * */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseItem item = (ResponseItem) o;
        return codigoItem == item.codigoItem &&
                Objects.equals(descricaoItem, item.descricaoItem) &&
                Objects.equals(codBarra, item.codBarra) &&
                Objects.equals(departamento, item.departamento) &&
                Objects.equals(linha, item.linha) &&
                Objects.equals(familia, item.familia) &&
                Objects.equals(grupo, item.grupo) &&
                Objects.equals(fornecedor, item.fornecedor) &&
                Objects.equals(modelo, item.modelo) &&
                Objects.equals(tipoProduto, item.tipoProduto) &&
                Objects.equals(unidadeMedida, item.unidadeMedida) &&
                Objects.equals(cor, item.cor) &&
                Objects.equals(especificacao, item.especificacao) &&
                Objects.equals(processado, item.processado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoItem, descricaoItem, codBarra, departamento, linha, familia, grupo, fornecedor,
                modelo, tipoProduto, unidadeMedida, cor, especificacao, processado);
    }
}
