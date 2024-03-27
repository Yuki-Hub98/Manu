package br.com.manu.service.maoDeObra;

import br.com.manu.model.maoDeObra.MaoDeObraDel;
import br.com.manu.model.maoDeObra.MaoDeObraRequest;
import br.com.manu.model.maoDeObra.MaoDeObraResponse;
import br.com.manu.persistence.entity.maoDeObra.MaoDeObra;
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
public class MaoDeObraServiceImp implements MaoDeObraService {
    @Autowired
    private final MongoTemplate mongoTemplate;

    public MaoDeObraServiceImp(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public MaoDeObraResponse create(MaoDeObraRequest request) {
        MaoDeObra maoDeObra = new MaoDeObra();
        Float valor = Float.parseFloat(request.getSalario());
        maoDeObra.setCodigo(incrementCodigo());
        maoDeObra.setFuncao(request.getFuncao());
        maoDeObra.setSalario(valor);
        Float calculo = (valor / 220) / 60;
        maoDeObra.setValorMinuto((float) Math.round(calculo * 100.0) / 100.0);
        mongoTemplate.save(maoDeObra, "maoDeObra");

        return generateResponse(maoDeObra);
    }

    @Override
    public List<MaoDeObraResponse> getAll() {
        List<MaoDeObra> maoDeObra = mongoTemplate.findAll(MaoDeObra.class);
        List<MaoDeObraResponse> maoDeObraResponseList = new ArrayList<>();
        maoDeObra.forEach(item -> {
            maoDeObraResponseList.add(generateResponse(item));
        });
        return maoDeObraResponseList;
    }

    @Override
    public MaoDeObraResponse edit(int codigo, MaoDeObraRequest request) {
        MaoDeObra maoDeObra = mongoTemplate.findOne(Query.query(Criteria.where("codigo").is(codigo)), MaoDeObra.class, "maoDeObra");
        MaoDeObra response = new MaoDeObra();
        if(maoDeObra != null){
            float media = (maoDeObra.getSalario() / 220) / 60;
            double calculo = (float) Math.round(media * 100.0) / 100.0;
            float salario = Float.parseFloat(request.getSalario().replace(",", "."));
            mongoTemplate.updateFirst(Query.query(Criteria.where("codigo").is(maoDeObra.getCodigo())),
                  Update.update("funcao", request.getFuncao()).set("salario", salario).set("valorMinuto",calculo),
                    MaoDeObra.class, "maoDeObra");
            response.setCodigo(codigo);
            response.setSalario(salario);
            response.setFuncao(request.getFuncao());
            response.setValorMinuto(calculo);
            return generateResponse(response);
        }
        return null;
    }

    @Override
    public MaoDeObraDel del(int id) {
        MaoDeObraDel maoDeObraDel = new MaoDeObraDel();
        mongoTemplate.remove(Query.query(Criteria.where("codigo").is(id)), MaoDeObra.class, "maoDeObra");
        maoDeObraDel.setId(id);
        maoDeObraDel.setDel("O item: " + id + " foi deletado");
        return maoDeObraDel;
    }


    private MaoDeObraResponse generateResponse(MaoDeObra maoDeObra){
        MaoDeObraResponse maoDeObraResponse = new MaoDeObraResponse();
        maoDeObraResponse.setCodigo(maoDeObra.getCodigo());
        maoDeObraResponse.setFuncao(maoDeObra.getFuncao());
        maoDeObraResponse.setSalario(String.format("%.2f", maoDeObra.getSalario()));
        maoDeObraResponse.setValorMinuto(String.format("%.2f", maoDeObra.getValorMinuto()));
        return maoDeObraResponse;

    }

    private int incrementCodigo () {
        int id = 0;
        List<MaoDeObra> maoDeObra =  mongoTemplate.findAll(MaoDeObra.class, "maoDeObra");
        if(maoDeObra.isEmpty()){
            id ++;
        }else {
            MaoDeObra lastId = mongoTemplate.findOne(new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "_id")),
                    MaoDeObra.class, "maoDeObra");
            assert lastId != null;
            id = (int) (lastId.getCodigo() + 1);
        }
        return id;
    }
}
