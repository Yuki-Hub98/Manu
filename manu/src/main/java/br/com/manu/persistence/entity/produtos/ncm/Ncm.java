package br.com.manu.persistence.entity.produtos.ncm;

import org.springframework.data.annotation.Id;

public class Ncm {
    @Id
    private String _id;
    private String Codigo;
    private String Descricao;
    private String Data_Inicio;
    private String Data_Fim;
    private String Tipo_Ato_Ini;
    private String Numero_Ato_Ini;
    private String Ano_Ato_Ini;

    public Ncm() {

    }

    public Ncm(String _id, String codigo, String descricao, String data_Inicio, String data_Fim, String tipo_Ato_Ini, String numero_Ato_Ini, String ano_Ato_Ini) {
        this._id = _id;
        Codigo = codigo;
        Descricao = descricao;
        Data_Inicio = data_Inicio;
        Data_Fim = data_Fim;
        Tipo_Ato_Ini = tipo_Ato_Ini;
        Numero_Ato_Ini = numero_Ato_Ini;
        Ano_Ato_Ini = ano_Ato_Ini;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getData_Inicio() {
        return Data_Inicio;
    }

    public void setData_Inicio(String data_Inicio) {
        Data_Inicio = data_Inicio;
    }

    public String getData_Fim() {
        return Data_Fim;
    }

    public void setData_Fim(String data_Fim) {
        Data_Fim = data_Fim;
    }

    public String getTipo_Ato_Ini() {
        return Tipo_Ato_Ini;
    }

    public void setTipo_Ato_Ini(String tipo_Ato_Ini) {
        Tipo_Ato_Ini = tipo_Ato_Ini;
    }

    public String getNumero_Ato_Ini() {
        return Numero_Ato_Ini;
    }

    public void setNumero_Ato_Ini(String numero_Ato_Ini) {
        Numero_Ato_Ini = numero_Ato_Ini;
    }

    public String getAno_Ato_Ini() {
        return Ano_Ato_Ini;
    }

    public void setAno_Ato_Ini(String ano_Ato_Ini) {
        Ano_Ato_Ini = ano_Ato_Ini;
    }
}
