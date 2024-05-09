package br.com.manu.service.administracaoDePreco;

import br.com.manu.model.administracaoDePreco.AdministracaoDePrecoRequest;
import br.com.manu.model.administracaoDePreco.AdministracaoDePrecoResponse;
import br.com.manu.model.produto.ResponseItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdministracaoDePrecoService {

    void generateAdministracaoDePreco(List<ResponseItem> responseItems);
    void updateAdministracaoPrecoByProduto(List<ResponseItem> responseItems);
    List<AdministracaoDePrecoResponse> getParams(String codigo, String descricaoItem, String codBarra,
                                                 String departamento, String linha, String modelo);
    AdministracaoDePrecoResponse updateAdministracaoDePreco(List<AdministracaoDePrecoRequest> request);

}
