package br.com.manu.persistence.entity;

import org.springframework.data.annotation.Id;

public class Familia {
    @Id
    private String id;
    private String linha;
    private String descricao;
}
