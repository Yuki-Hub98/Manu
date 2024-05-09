package br.com.manu.service.administracaoDePreco;

import br.com.manu.model.administracaoDePreco.AdministracaoDePrecoRequest;
import br.com.manu.model.administracaoDePreco.AdministracaoDePrecoResponse;
import br.com.manu.model.produto.ResponseItem;
import br.com.manu.persistence.entity.administracaoDePreco.AdministracaoDePreco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdministracaoDePrecoImp implements AdministracaoDePrecoService{
    @Autowired
    private MongoTemplate mongoTemplate;
    public AdministracaoDePrecoImp (MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * O objeto administracao de preço é gerado a partir da criação de um item na criação de produto, depois de gerado o item,
     * é criado a administracao de preco tudo zerado;
     * @param responseItems
     */
    @Override
    public void generateAdministracaoDePreco(List<ResponseItem> responseItems) {
        List<AdministracaoDePreco> generateAdministracaoDePreco = new ArrayList<>();
        responseItems.forEach(item -> {
            generateAdministracaoDePreco.add(new AdministracaoDePreco(item.getCodigo(), item.getDescricaoItem(),item.getDepartamento(), item.getCodBarra(),
                    item.getLinha(),  item.getModelo(), 0, 0, 0, 0, 0,0,0));
        });

        generateAdministracaoDePreco.forEach(administracaoDePreco -> {
            mongoTemplate.save(administracaoDePreco, "administracaoDePreco");
        });

    }

    @Override
    public void updateAdministracaoPrecoByProduto(List<ResponseItem> responseItems) {
        responseItems.forEach(item -> {
            mongoTemplate.updateFirst(Query.query(Criteria.where("codigo").is(item.getCodigo())), Update.update("descricaoItem", item.getDescricaoItem())
                    .set("departamento", item.getDepartamento()).set("codBarra", item.getCodBarra()).set("linha", item.getLinha())
                    .set("modelo", item.getModelo()), AdministracaoDePreco.class, "administracaoDePreco");
        });
    }

    @Override
    public List<AdministracaoDePrecoResponse> getParams(String codigo, String descricaoItem, String codBarra,
                                                        String departamento, String linha, String modelo) {
        List<AdministracaoDePreco> administracaoDePrecoList = new ArrayList<>();
        List<AdministracaoDePrecoResponse> response = new ArrayList<>();

        Query query = new Query().addCriteria(new Criteria().orOperator(Criteria.where("descricaoItem").regex(descricaoItem),
                        Criteria.where("codBarra").is(codBarra), Criteria.where("departamento").is(departamento),
                        Criteria.where("linha").is(linha),Criteria.where("modelo").is(modelo)));

        if (codigo.equals("undefined") && descricaoItem.equals("undefined") && codBarra.equals("undefined") && departamento.equals("undefined") &&
                linha.equals("undefined") && modelo.equals("undefined")){
            administracaoDePrecoList = mongoTemplate.findAll(AdministracaoDePreco.class, "administracaoDePreco");
            administracaoDePrecoList.forEach(administracaoDePreco -> {
                response.add(generateResponse(administracaoDePreco));
            });
            return response;
        }

        if (!codigo.equals("undefined")){
            long _codigo = Long.parseLong(codigo);
            administracaoDePrecoList = mongoTemplate.find(Query.query(Criteria.where("codigo").is(_codigo)),
                    AdministracaoDePreco.class, "administracaoDePreco");
            administracaoDePrecoList.forEach(administracaoDePreco -> {
                response.add(generateResponse(administracaoDePreco));
            });
            return response;
        }

        if (!descricaoItem.isEmpty() || !codBarra.isEmpty() || !departamento.isEmpty() ||
                !linha.isEmpty() || !modelo.isEmpty()){
            administracaoDePrecoList = mongoTemplate.find(query, AdministracaoDePreco.class, "administracaoDePreco");
            administracaoDePrecoList.forEach(administracaoDePreco -> {
                response.add(generateResponse(administracaoDePreco));
            });
            return response;
        }

        return null;
    }

    @Override
    public AdministracaoDePrecoResponse updateAdministracaoDePreco(List<AdministracaoDePrecoRequest> request) {
        AdministracaoDePreco administracaoDePreco = new AdministracaoDePreco();
       request.forEach( item -> {
           mongoTemplate.updateFirst(Query.query(Criteria.where("codigo").is(item.getCodigo())),
                   Update.update("precoAtual",item.getPrecoProposto()).set("valorCusto", item.getValorCusto()).set("margemAtual", item.getMargemProposta())
                           .set("margemProposta", item.getMargemProposta()).set("precoProposto", item.getPrecoProposto()),
                   AdministracaoDePreco.class, "administracaoDePreco");
           administracaoDePreco.setCodigo(item.getCodigo());
           administracaoDePreco.setDescricaoItem(item.getDescricaoItem());
           administracaoDePreco.setPrecoAtual(item.getPrecoProposto());
           administracaoDePreco.setValorCusto(item.getValorCusto());
           administracaoDePreco.setMargemAtual(item.getMargemProposta());
           administracaoDePreco.setMargemProposta(item.getMargemProposta());
           administracaoDePreco.setPrecoProposto(item.getPrecoProposto());
       });

       return generateResponse(administracaoDePreco);
    }

    AdministracaoDePrecoResponse generateResponse (AdministracaoDePreco administracaoDePreco){
        AdministracaoDePrecoResponse administracaoDePrecoResponse = new AdministracaoDePrecoResponse();
        administracaoDePrecoResponse.setCodigo(administracaoDePreco.getCodigo());
        administracaoDePrecoResponse.setDescricaoItem(administracaoDePreco.getDescricaoItem());
        administracaoDePrecoResponse.setPrecoAtual(administracaoDePreco.getPrecoAtual());
        administracaoDePrecoResponse.setPrecoProposto(administracaoDePreco.getPrecoProposto());
        administracaoDePrecoResponse.setMargemAtual(administracaoDePreco.getMargemAtual());
        administracaoDePrecoResponse.setMargemProposta(administracaoDePreco.getMargemProposta());
        administracaoDePrecoResponse.setValorCusto(administracaoDePreco.getValorCusto());
        return administracaoDePrecoResponse;
    }


}
