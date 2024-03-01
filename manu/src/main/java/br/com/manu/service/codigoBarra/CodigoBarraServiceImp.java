package br.com.manu.service.codigoBarra;

import br.com.manu.model.codigoBarra.CodigoBarraRequest;
import br.com.manu.model.codigoBarra.CodigoBarraResponse;
import br.com.manu.persistence.entity.fornecedor.Fornecedor;
import br.com.manu.persistence.entity.produtos.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CodigoBarraServiceImp implements CodigoBarraService {
    @Autowired
    MongoTemplate mongoTemplate;

    public CodigoBarraServiceImp(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public CodigoBarraResponse createCodigoBarra(CodigoBarraRequest request) {
        CodigoBarraResponse newCod = new CodigoBarraResponse();
        newCod.setLastId(request.getIdItem());

        newCod.setCodBarra(generateCod(request.getFornecedor(), newCod.getLastId()));
        return newCod;
    }

    private String generateCod(String request, int cod){

        Fornecedor fornecedor = mongoTemplate.findOne(Query.query(Criteria.where("nomeFantasiaFornecedor").is(request)),
                Fornecedor.class, "fornecedor");

        assert fornecedor != null;

        String _cod = formatValueId(cod);

        String oitoPrimeiroDigitos = "789" +  fornecedor.getCpfCnpjFornecedor().substring(0, 6).replace(".", "");

        return calcCodigoBar(oitoPrimeiroDigitos, _cod);
    }

    private String formatValueId(int cod){
        String _cod = String.valueOf(cod);
        if (_cod.length() == 1){
            _cod = "000" + _cod;
        }else if(_cod.length() == 2){
            _cod = "00" + _cod;
        }else if(_cod.length() == 3){
            _cod = "0" + _cod;
        }

        return _cod;
    }

    private String calcCodigoBar(String oitoDigitos, String cod){
        int juncaoImparPar = 0;
        int impares = 0;
        int pares = 0;
        String codBar = oitoDigitos + cod;
        for (int index = 1; index < codBar.length(); index += 2) {
            impares += Integer.parseInt(Character.toString(codBar.charAt(index))) * 3;
        }
        for (int index = 0; index < codBar.length(); index += 2) {
            pares += Integer.parseInt(Character.toString(codBar.charAt(index)));
        }
        juncaoImparPar = pares + impares;
        if (juncaoImparPar % 10 == 0) {
            juncaoImparPar += 1;
        }
        int calcBar = 10 - (juncaoImparPar % 10);
        String _calcBar = String.valueOf(calcBar);
        return codBar + _calcBar;
    }

    private int findId(){
        int cod = 0;
        List<Item> items = mongoTemplate.findAll(Item.class, "item");
        if (items.isEmpty()) {
            cod ++;
        }else {
            Item lastId = mongoTemplate.findOne(new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "_id")),
                    Item.class, "item");
            assert lastId != null;
            cod = (int) lastId.getIdItem();
        }
        return cod;
    }

}
