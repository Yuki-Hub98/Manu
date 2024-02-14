package br.com.manu.model.fornecedor;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

public class FornecedorResponse {

    private String razaoSocialFornecedor;
    private String nomeFantasiaFornecedor;
    private String cpfCnpjFornecedor;
    private String ieRgFornecedor;
    private String orgaoEmissorFornecedor;
    private String ufRgFornecedor;
    private String dataEmissaoFornecedor;
    private String cepFornecedor;
    private String enderecoFornecedor;
    private String numeroFornecedor;
    private String complementoFornecedor;
    private String bairroFornecedor;
    private String cidadeFornecedor;
    private String ufFornecedor;
    private String contatoFornecedor;
    private String telefoneFornecedor;
    private String celularFornecedor;
    private String emailFornecedor;
    private String siteFornecedor;
    private String razaoSocialRepresentante;
    private String nomeFantasiaRepresentante;
    private String cpfCnpjRepresentante;
    private String ieRgRepresentante;
    private String orgaoEmissorRepresentante;
    private String ufRgRepresentante;
    private String dataEmissaoRepresentante;
    private String cepRepresentante;
    private String enderecoRepresentante;
    private String numeroRepresentante;
    private String complementoRepresentante;
    private String bairroRepresentante;
    private String cidadeRepresentante;
    private String ufRepresentante;
    private String contatoRepresentante;
    private String telefoneRepresentante;
    private String celularRepresentante;
    private String emailRepresentante;
    private String siteRepresentante;

    private String codBanco;
    private String banco;
    private String agencia;
    private String contaBanco;
    private String orgaoEmissorBanco;
    private String pix;
    @CreatedDate
    private Date dataCriacao;
    @LastModifiedDate
    private Date dataModificacao;

    public FornecedorResponse() {

    }

    public FornecedorResponse(String razaoSocialFornecedor, String nomeFantasiaFornecedor, String cpfCnpjFornecedor, String ieRgFornecedor, String orgaoEmissorFornecedor,
                              String ufRgFornecedor, String dataEmissaoFornecedor, String cepFornecedor, String enderecoFornecedor, String numeroFornecedor,
                              String complementoFornecedor, String bairroFornecedor, String cidadeFornecedor, String ufFornecedor, String contatoFornecedor,
                              String telefoneFornecedor, String celularFornecedor, String emailFornecedor, String siteFornecedor, String razaoSocialRepresentante,
                              String nomeFantasiaRepresentante, String cpfCnpjRepresentante, String ieRgRepresentante, String orgaoEmissorRepresentante,
                              String ufRgRepresentante, String dataEmissaoRepresentante, String cepRepresentante, String enderecoRepresentante, String numeroRepresentante,
                              String complementoRepresentante, String bairroRepresentante, String cidadeRepresentante, String ufRepresentante, String contatoRepresentante,
                              String telefoneRepresentante, String celularRepresentante, String emailRepresentante, String siteRepresentante, String codBanco, String banco,
                              String agencia, String contaBanco, String orgaoEmissorBanco, String pix, Date dataCriacao, Date dataModificacao) {
        this.razaoSocialFornecedor = razaoSocialFornecedor;
        this.nomeFantasiaFornecedor = nomeFantasiaFornecedor;
        this.cpfCnpjFornecedor = cpfCnpjFornecedor;
        this.ieRgFornecedor = ieRgFornecedor;
        this.orgaoEmissorFornecedor = orgaoEmissorFornecedor;
        this.ufRgFornecedor = ufRgFornecedor;
        this.dataEmissaoFornecedor = dataEmissaoFornecedor;
        this.cepFornecedor = cepFornecedor;
        this.enderecoFornecedor = enderecoFornecedor;
        this.numeroFornecedor = numeroFornecedor;
        this.complementoFornecedor = complementoFornecedor;
        this.bairroFornecedor = bairroFornecedor;
        this.cidadeFornecedor = cidadeFornecedor;
        this.ufFornecedor = ufFornecedor;
        this.contatoFornecedor = contatoFornecedor;
        this.telefoneFornecedor = telefoneFornecedor;
        this.celularFornecedor = celularFornecedor;
        this.emailFornecedor = emailFornecedor;
        this.siteFornecedor = siteFornecedor;
        this.razaoSocialRepresentante = razaoSocialRepresentante;
        this.nomeFantasiaRepresentante = nomeFantasiaRepresentante;
        this.cpfCnpjRepresentante = cpfCnpjRepresentante;
        this.ieRgRepresentante = ieRgRepresentante;
        this.orgaoEmissorRepresentante = orgaoEmissorRepresentante;
        this.ufRgRepresentante = ufRgRepresentante;
        this.dataEmissaoRepresentante = dataEmissaoRepresentante;
        this.cepRepresentante = cepRepresentante;
        this.enderecoRepresentante = enderecoRepresentante;
        this.numeroRepresentante = numeroRepresentante;
        this.complementoRepresentante = complementoRepresentante;
        this.bairroRepresentante = bairroRepresentante;
        this.cidadeRepresentante = cidadeRepresentante;
        this.ufRepresentante = ufRepresentante;
        this.contatoRepresentante = contatoRepresentante;
        this.telefoneRepresentante = telefoneRepresentante;
        this.celularRepresentante = celularRepresentante;
        this.emailRepresentante = emailRepresentante;
        this.siteRepresentante = siteRepresentante;
        this.codBanco = codBanco;
        this.banco = banco;
        this.agencia = agencia;
        this.contaBanco = contaBanco;
        this.orgaoEmissorBanco = orgaoEmissorBanco;
        this.pix = pix;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }

    public String getRazaoSocialFornecedor() {
        return razaoSocialFornecedor;
    }

    public void setRazaoSocialFornecedor(String razaoSocialFornecedor) {
        this.razaoSocialFornecedor = razaoSocialFornecedor;
    }

    public String getNomeFantasiaFornecedor() {
        return nomeFantasiaFornecedor;
    }

    public void setNomeFantasiaFornecedor(String nomeFantasiaFornecedor) {
        this.nomeFantasiaFornecedor = nomeFantasiaFornecedor;
    }

    public String getCpfCnpjFornecedor() {
        return cpfCnpjFornecedor;
    }

    public void setCpfCnpjFornecedor(String cpfCnpjFornecedor) {
        this.cpfCnpjFornecedor = cpfCnpjFornecedor;
    }

    public String getIeRgFornecedor() {
        return ieRgFornecedor;
    }

    public void setIeRgFornecedor(String ieRgFornecedor) {
        this.ieRgFornecedor = ieRgFornecedor;
    }

    public String getOrgaoEmissorFornecedor() {
        return orgaoEmissorFornecedor;
    }

    public void setOrgaoEmissorFornecedor(String orgaoEmissorFornecedor) {
        this.orgaoEmissorFornecedor = orgaoEmissorFornecedor;
    }

    public String getUfRgFornecedor() {
        return ufRgFornecedor;
    }

    public void setUfRgFornecedor(String ufRgFornecedor) {
        this.ufRgFornecedor = ufRgFornecedor;
    }

    public String getDataEmissaoFornecedor() {
        return dataEmissaoFornecedor;
    }

    public void setDataEmissaoFornecedor(String dataEmissaoFornecedor) {
        this.dataEmissaoFornecedor = dataEmissaoFornecedor;
    }

    public String getCepFornecedor() {
        return cepFornecedor;
    }

    public void setCepFornecedor(String cepFornecedor) {
        this.cepFornecedor = cepFornecedor;
    }

    public String getEnderecoFornecedor() {
        return enderecoFornecedor;
    }

    public void setEnderecoFornecedor(String enderecoFornecedor) {
        this.enderecoFornecedor = enderecoFornecedor;
    }

    public String getNumeroFornecedor() {
        return numeroFornecedor;
    }

    public void setNumeroFornecedor(String numeroFornecedor) {
        this.numeroFornecedor = numeroFornecedor;
    }

    public String getComplementoFornecedor() {
        return complementoFornecedor;
    }

    public void setComplementoFornecedor(String complementoFornecedor) {
        this.complementoFornecedor = complementoFornecedor;
    }

    public String getBairroFornecedor() {
        return bairroFornecedor;
    }

    public void setBairroFornecedor(String bairroFornecedor) {
        this.bairroFornecedor = bairroFornecedor;
    }

    public String getCidadeFornecedor() {
        return cidadeFornecedor;
    }

    public void setCidadeFornecedor(String cidadeFornecedor) {
        this.cidadeFornecedor = cidadeFornecedor;
    }

    public String getUfFornecedor() {
        return ufFornecedor;
    }

    public void setUfFornecedor(String ufFornecedor) {
        this.ufFornecedor = ufFornecedor;
    }

    public String getContatoFornecedor() {
        return contatoFornecedor;
    }

    public void setContatoFornecedor(String contatoFornecedor) {
        this.contatoFornecedor = contatoFornecedor;
    }

    public String getTelefoneFornecedor() {
        return telefoneFornecedor;
    }

    public void setTelefoneFornecedor(String telefoneFornecedor) {
        this.telefoneFornecedor = telefoneFornecedor;
    }

    public String getCelularFornecedor() {
        return celularFornecedor;
    }

    public void setCelularFornecedor(String celularFornecedor) {
        this.celularFornecedor = celularFornecedor;
    }

    public String getEmailFornecedor() {
        return emailFornecedor;
    }

    public void setEmailFornecedor(String emailFornecedor) {
        this.emailFornecedor = emailFornecedor;
    }

    public String getSiteFornecedor() {
        return siteFornecedor;
    }

    public void setSiteFornecedor(String siteFornecedor) {
        this.siteFornecedor = siteFornecedor;
    }

    public String getRazaoSocialRepresentante() {
        return razaoSocialRepresentante;
    }

    public void setRazaoSocialRepresentante(String razaoSocialRepresentante) {
        this.razaoSocialRepresentante = razaoSocialRepresentante;
    }

    public String getNomeFantasiaRepresentante() {
        return nomeFantasiaRepresentante;
    }

    public void setNomeFantasiaRepresentante(String nomeFantasiaRepresentante) {
        this.nomeFantasiaRepresentante = nomeFantasiaRepresentante;
    }

    public String getCpfCnpjRepresentante() {
        return cpfCnpjRepresentante;
    }

    public void setCpfCnpjRepresentante(String cpfCnpjRepresentante) {
        this.cpfCnpjRepresentante = cpfCnpjRepresentante;
    }

    public String getIeRgRepresentante() {
        return ieRgRepresentante;
    }

    public void setIeRgRepresentante(String ieRgRepresentante) {
        this.ieRgRepresentante = ieRgRepresentante;
    }

    public String getOrgaoEmissorRepresentante() {
        return orgaoEmissorRepresentante;
    }

    public void setOrgaoEmissorRepresentante(String orgaoEmissorRepresentante) {
        this.orgaoEmissorRepresentante = orgaoEmissorRepresentante;
    }

    public String getUfRgRepresentante() {
        return ufRgRepresentante;
    }

    public void setUfRgRepresentante(String ufRgRepresentante) {
        this.ufRgRepresentante = ufRgRepresentante;
    }

    public String getDataEmissaoRepresentante() {
        return dataEmissaoRepresentante;
    }

    public void setDataEmissaoRepresentante(String dataEmissaoRepresentante) {
        this.dataEmissaoRepresentante = dataEmissaoRepresentante;
    }

    public String getCepRepresentante() {
        return cepRepresentante;
    }

    public void setCepRepresentante(String cepRepresentante) {
        this.cepRepresentante = cepRepresentante;
    }

    public String getEnderecoRepresentante() {
        return enderecoRepresentante;
    }

    public void setEnderecoRepresentante(String enderecoRepresentante) {
        this.enderecoRepresentante = enderecoRepresentante;
    }

    public String getNumeroRepresentante() {
        return numeroRepresentante;
    }

    public void setNumeroRepresentante(String numeroRepresentante) {
        this.numeroRepresentante = numeroRepresentante;
    }

    public String getComplementoRepresentante() {
        return complementoRepresentante;
    }

    public void setComplementoRepresentante(String complementoRepresentante) {
        this.complementoRepresentante = complementoRepresentante;
    }

    public String getBairroRepresentante() {
        return bairroRepresentante;
    }

    public void setBairroRepresentante(String bairroRepresentante) {
        this.bairroRepresentante = bairroRepresentante;
    }

    public String getCidadeRepresentante() {
        return cidadeRepresentante;
    }

    public void setCidadeRepresentante(String cidadeRepresentante) {
        this.cidadeRepresentante = cidadeRepresentante;
    }

    public String getUfRepresentante() {
        return ufRepresentante;
    }

    public void setUfRepresentante(String ufRepresentante) {
        this.ufRepresentante = ufRepresentante;
    }

    public String getContatoRepresentante() {
        return contatoRepresentante;
    }

    public void setContatoRepresentante(String contatoRepresentante) {
        this.contatoRepresentante = contatoRepresentante;
    }

    public String getTelefoneRepresentante() {
        return telefoneRepresentante;
    }

    public void setTelefoneRepresentante(String telefoneRepresentante) {
        this.telefoneRepresentante = telefoneRepresentante;
    }

    public String getCelularRepresentante() {
        return celularRepresentante;
    }

    public void setCelularRepresentante(String celularRepresentante) {
        this.celularRepresentante = celularRepresentante;
    }

    public String getEmailRepresentante() {
        return emailRepresentante;
    }

    public void setEmailRepresentante(String emailRepresentante) {
        this.emailRepresentante = emailRepresentante;
    }

    public String getSiteRepresentante() {
        return siteRepresentante;
    }

    public void setSiteRepresentante(String siteRepresentante) {
        this.siteRepresentante = siteRepresentante;
    }

    public String getCodBanco() {
        return codBanco;
    }

    public void setCodBanco(String codBanco) {
        this.codBanco = codBanco;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getContaBanco() {
        return contaBanco;
    }

    public void setContaBanco(String contaBanco) {
        this.contaBanco = contaBanco;
    }

    public String getOrgaoEmissorBanco() {
        return orgaoEmissorBanco;
    }

    public void setOrgaoEmissorBanco(String orgaoEmissorBanco) {
        this.orgaoEmissorBanco = orgaoEmissorBanco;
    }

    public String getPix() {
        return pix;
    }

    public void setPix(String pix) {
        this.pix = pix;
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
