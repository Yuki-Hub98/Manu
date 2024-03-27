package br.com.manu.service.cadastroDeFuncao;

import br.com.manu.model.cadastroDeFuncao.CadastroDeFuncao;
import br.com.manu.model.cadastroDeFuncao.CadastroDeFuncaoRequest;
import br.com.manu.model.cadastroDeFuncao.CadastroDeFuncaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CadastroDeFuncaoServiceImp implements CadastroDeFuncaoService {
    @Autowired
    private final MongoTemplate mongoTemplate;

    public CadastroDeFuncaoServiceImp(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public CadastroDeFuncaoResponse create(CadastroDeFuncaoRequest request) {
        br.com.manu.persistence.entity.cadastroDeFuncao.CadastroDeFuncao cadastroDeFuncao = new br.com.manu.persistence.entity.cadastroDeFuncao.CadastroDeFuncao();
        Float valor = Float.parseFloat(request.getSalario());
        cadastroDeFuncao.setCodigo(incrementCodigo());
        cadastroDeFuncao.setFuncao(request.getFuncao());
        cadastroDeFuncao.setSalario(valor);
        Float calculo = (valor / 220) / 60;
        cadastroDeFuncao.setValorMinuto((float) Math.round(calculo * 100.0) / 100.0);
        mongoTemplate.save(cadastroDeFuncao, "cadastroDeFuncao");

        return generateResponse(cadastroDeFuncao);
    }

    @Override
    public List<CadastroDeFuncaoResponse> getAll() {
        List<br.com.manu.persistence.entity.cadastroDeFuncao.CadastroDeFuncao> cadastroDeFuncao = mongoTemplate.findAll(br.com.manu.persistence.entity.cadastroDeFuncao.CadastroDeFuncao.class);
        List<CadastroDeFuncaoResponse> cadastroDeFuncaoResponseList = new ArrayList<>();
        cadastroDeFuncao.forEach(item -> {
            cadastroDeFuncaoResponseList.add(generateResponse(item));
        });
        return cadastroDeFuncaoResponseList;
    }

    @Override
    public CadastroDeFuncaoResponse edit(int codigo, CadastroDeFuncaoRequest request) {
        br.com.manu.persistence.entity.cadastroDeFuncao.CadastroDeFuncao cadastroDeFuncao = mongoTemplate.findOne(Query.query(Criteria.where("codigo").is(codigo)), br.com.manu.persistence.entity.cadastroDeFuncao.CadastroDeFuncao.class, "cadastroDeFuncao");
        br.com.manu.persistence.entity.cadastroDeFuncao.CadastroDeFuncao response = new br.com.manu.persistence.entity.cadastroDeFuncao.CadastroDeFuncao();
        if(cadastroDeFuncao != null){
            float media = (cadastroDeFuncao.getSalario() / 220) / 60;
            double calculo = (float) Math.round(media * 100.0) / 100.0;
            float salario = Float.parseFloat(request.getSalario().replace(",", "."));
            mongoTemplate.updateFirst(Query.query(Criteria.where("codigo").is(cadastroDeFuncao.getCodigo())),
                  Update.update("funcao", request.getFuncao()).set("salario", salario).set("valorMinuto",calculo),
                    br.com.manu.persistence.entity.cadastroDeFuncao.CadastroDeFuncao.class, "cadastroDeFuncao");
            response.setCodigo(codigo);
            response.setSalario(salario);
            response.setFuncao(request.getFuncao());
            response.setValorMinuto(calculo);
            return generateResponse(response);
        }
        return null;
    }

    @Override
    public CadastroDeFuncao del(int id) {
        CadastroDeFuncao cadastroDeFuncao = new CadastroDeFuncao();
        mongoTemplate.remove(Query.query(Criteria.where("codigo").is(id)), br.com.manu.persistence.entity.cadastroDeFuncao.CadastroDeFuncao.class, "cadastroDeFuncao");
        cadastroDeFuncao.setId(id);
        cadastroDeFuncao.setDel("O item: " + id + " foi deletado");
        return cadastroDeFuncao;
    }


    private CadastroDeFuncaoResponse generateResponse(br.com.manu.persistence.entity.cadastroDeFuncao.CadastroDeFuncao cadastroDeFuncao){
        CadastroDeFuncaoResponse cadastroDeFuncaoResponse = new CadastroDeFuncaoResponse();
        cadastroDeFuncaoResponse.setCodigo(cadastroDeFuncao.getCodigo());
        cadastroDeFuncaoResponse.setFuncao(cadastroDeFuncao.getFuncao());
        cadastroDeFuncaoResponse.setSalario(String.format("%.2f", cadastroDeFuncao.getSalario()));
        cadastroDeFuncaoResponse.setValorMinuto(String.format("%.2f", cadastroDeFuncao.getValorMinuto()));
        return cadastroDeFuncaoResponse;

    }

    private int incrementCodigo () {
        int id = 0;
        List<br.com.manu.persistence.entity.cadastroDeFuncao.CadastroDeFuncao> cadastroDeFuncao =  mongoTemplate.findAll(br.com.manu.persistence.entity.cadastroDeFuncao.CadastroDeFuncao.class, "cadastroDeFuncao");
        if(cadastroDeFuncao.isEmpty()){
            id ++;
        }else {
            br.com.manu.persistence.entity.cadastroDeFuncao.CadastroDeFuncao lastId = mongoTemplate.findOne(new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "_id")),
                    br.com.manu.persistence.entity.cadastroDeFuncao.CadastroDeFuncao.class, "cadastroDeFuncao");
            assert lastId != null;
            id = (int) (lastId.getCodigo() + 1);
        }
        return id;
    }
}
