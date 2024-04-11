package br.com.manu.service.etapaDeProducao;

import br.com.manu.model.etapaDeProducao.EtapaDeProducaoDel;
import br.com.manu.model.etapaDeProducao.EtapaDeProducaoRe;
import br.com.manu.persistence.entity.etapaDeProducao.EtapaDeProducao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.management.relation.InvalidRelationIdException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EtapaDeProducaoImp implements EtapaDeProducaoService{
    @Autowired
    MongoTemplate mongoTemplate;

    public EtapaDeProducaoImp (MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public EtapaDeProducaoRe crete(EtapaDeProducaoRe request) {
        Duplicated(request);
        EtapaDeProducao etapaDeProducao = new EtapaDeProducao();
        etapaDeProducao.setCodigo(incrementCodigo());
        etapaDeProducao.setEtapaDeProducao(request.getEtapaDeProducao());
        mongoTemplate.save(etapaDeProducao, "etapaDeProducao");

        return createReponse(etapaDeProducao);
    }

    @Override
    public List<EtapaDeProducaoRe> getAll() {
        List<EtapaDeProducaoRe> etapaDeProducaoRes = new ArrayList<>();
        List<EtapaDeProducao> etapaDeProducaoList = mongoTemplate.findAll(EtapaDeProducao.class, "etapaDeProducao");
        etapaDeProducaoList.forEach(item -> {
            etapaDeProducaoRes.add(new EtapaDeProducaoRe(item.getCodigo(), item.getEtapaDeProducao()));
        });

        return etapaDeProducaoRes;
    }

    @Override
    public EtapaDeProducaoRe edit(int codigo, EtapaDeProducaoRe request) {
        Duplicated(request);
        mongoTemplate.updateFirst(Query.query(Criteria.where("codigo").is(request.getCodigo())),
                Update.update("etapaDeProducao", request.getEtapaDeProducao()), EtapaDeProducao.class, "etapaDeProducao");

        return request;
    }

    @Override
    public EtapaDeProducaoDel del(int codigo, EtapaDeProducaoRe request) {
        EtapaDeProducaoDel del = new EtapaDeProducaoDel();
        del.setDel(request.getEtapaDeProducao());
        del.setCodigo(request.getCodigo());
        return del;
    }

    EtapaDeProducaoRe createReponse(EtapaDeProducao etapaDeProducao){
        EtapaDeProducaoRe etapaDeProducaoRe = new EtapaDeProducaoRe();
        etapaDeProducaoRe.setEtapaDeProducao(etapaDeProducao.getEtapaDeProducao());
        etapaDeProducaoRe.setCodigo(etapaDeProducao.getCodigo());
        return etapaDeProducaoRe;
    }

    private void Duplicated (EtapaDeProducaoRe request){
        boolean exist = mongoTemplate.exists(Query.query(Criteria.where("descricao").is(request.getEtapaDeProducao())),
                EtapaDeProducao.class, "etapaDeProducao");
        if(exist) {
            try {
                throw new InvalidRelationIdException(request.getEtapaDeProducao());
            } catch (InvalidRelationIdException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private int incrementCodigo () {
        int id = 0;
        List<EtapaDeProducao> cadastroDeRecurso =  mongoTemplate.findAll(EtapaDeProducao.class, "etapaDeProducao");
        if(cadastroDeRecurso.isEmpty()){
            id ++;
        }else {
            EtapaDeProducao lastId = mongoTemplate.findOne(new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "_id")),
                    EtapaDeProducao.class, "etapaDeProducao");
            assert lastId != null;
            id = (lastId.getCodigo() + 1);
        }
        return id;
    }
}
