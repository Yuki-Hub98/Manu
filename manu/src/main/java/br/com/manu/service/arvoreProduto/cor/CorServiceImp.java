package br.com.manu.service.arvoreProduto.cor;

import br.com.manu.model.arvoreProduto.cor.CorDel;
import br.com.manu.model.arvoreProduto.cor.CorEdit;
import br.com.manu.model.arvoreProduto.cor.CorRequest;
import br.com.manu.model.arvoreProduto.cor.CorResponse;
import br.com.manu.model.arvoreProduto.departamento.DepartamentoResponse;
import br.com.manu.persistence.entity.arvoreProduto.Cor;
import br.com.manu.persistence.entity.arvoreProduto.Departamento;
import br.com.manu.persistence.repository.arvoreProduto.CorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.management.relation.InvalidRelationIdException;
import java.util.ArrayList;
import java.util.List;
@Service
public class CorServiceImp implements CorService{
    @Autowired
    private CorRepository repository;
    private MongoTemplate mongoTemplate;

    public CorServiceImp(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;

    }

    @Override
    public CorResponse create(CorRequest request) {
        Cor cor = new Cor();
        cor.setDescricao(request.getDescricao());
        find(cor).forEach(dep -> {
            if(dep.getDescricao().equals(cor.getDescricao())) {
                try {
                    throw new InvalidRelationIdException();
                } catch (InvalidRelationIdException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        repository.save(cor);
        return createResponse(cor);
    }

    @Override
    public List<CorResponse> getAll() {
        List<CorResponse> responses = new ArrayList<>();
        List<Cor> cors = repository.findAll();
        if(!cors.isEmpty()){
            cors.forEach(cor -> responses.add(createResponse(cor)));
        }
        return responses;
    }

    @Override
    public List<CorResponse> getDescricao(String request) {
        List<CorResponse> response = new ArrayList<>();
        List<Cor> seach = repository.findRegex(request);
        if(!seach.isEmpty()){
            seach.forEach(descricao -> response.add(createResponse(descricao)));
        }
        return response;
    }

    @Override
    public CorResponse edit(CorEdit request) {
        Cor newCor = new Cor();
        newCor.setDescricao(request.getEdit());
        find(newCor).forEach(dep -> {
            if(dep.getDescricao().equals(newCor.getDescricao())) {
                try {
                    throw new InvalidRelationIdException();
                } catch (InvalidRelationIdException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        mongoTemplate.updateFirst(Query.query(Criteria.where("descricao").is(request.getDescricao())),
                Update.update("descricao", request.getEdit()), Cor.class, "cor");
        return createResponse(newCor);
    }

    @Override
    public CorDel del(CorRequest request) {
        Cor del = new Cor();
        del.setDescricao(request.getDescricao());
        mongoTemplate.remove(Query.query(Criteria.where("descricao").is(request.getDescricao())), Cor.class, "cor");
        return responseDel(del);
    }

    private CorResponse createResponse(Cor cor) {
        CorResponse response = new CorResponse();
        response.setDescricao(cor.getDescricao());
        return response;
    }

    private CorDel responseDel(Cor cor){
        CorDel del = new CorDel();
        del.setDel(cor.getDescricao());
        return del;
    }

    private List<Cor> find(Cor campo){
        List<Cor> find = repository.findByname(campo.getDescricao());
        return find;
    }
}
