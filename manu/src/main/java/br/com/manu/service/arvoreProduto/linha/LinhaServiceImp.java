package br.com.manu.service.arvoreProduto.linha;

import br.com.manu.model.arvoreProduto.departamento.DepartamentoEdit;
import br.com.manu.model.arvoreProduto.familia.FamiliaEdit;
import br.com.manu.model.arvoreProduto.linha.LinhaDel;
import br.com.manu.model.arvoreProduto.linha.LinhaEdit;
import br.com.manu.model.arvoreProduto.linha.LinhaRequest;
import br.com.manu.model.arvoreProduto.linha.LinhaResponse;
import br.com.manu.persistence.entity.arvoreProduto.Departamento;
import br.com.manu.persistence.entity.arvoreProduto.Familia;
import br.com.manu.persistence.entity.arvoreProduto.Linha;
import br.com.manu.persistence.repository.arvoreProduto.LinhaRepository;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.management.relation.InvalidRelationIdException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LinhaServiceImp implements LinhaService {
    @Autowired
    private LinhaRepository repository;
    private final MongoTemplate mongoTemplate;
    private Departamento departamento;
    public LinhaServiceImp(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;

    }
    @Override
    public LinhaResponse create(LinhaRequest request) {
        Linha linha = new Linha();
        linha.setDepartamento(request.getDepartamento());
        linha.setDescricao(request.getDescricao());
        find(linha).forEach((li) -> {
            if (li.getDescricao().equals(linha.getDescricao()) && li.getDepartamento().equals(linha.getDepartamento())) {
                try {
                    throw new InvalidRelationIdException();
                } catch (InvalidRelationIdException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        repository.save(linha);
        return createResponse(linha);

    }

    @Override
    public List<LinhaResponse> getAll() {
        List<LinhaResponse> responses = new ArrayList<>();
        List<Linha> linhas = repository.findAll();
        if(!linhas.isEmpty()){
            linhas.forEach(linha -> responses.add(createResponse(linha)));
        }
        return responses;
    }

    @Override
    public List<LinhaResponse> getDescricao(String request) {
        List<LinhaResponse> response = new ArrayList<>();
        List<Linha> search = repository.findRegex(request);
        if(!search.isEmpty()){
            search.forEach(descricao -> response.add(createResponse(descricao)));
        }
        return response;
    }

    @Override
    public LinhaResponse edit(LinhaEdit request) {
        Linha newLinha = new Linha();
        newLinha.setDepartamento(request.getDepartamento());
        newLinha.setDescricao(request.getEditDescricao());
        find(newLinha).forEach(newDep -> {
            if(newDep.getDescricao().equals(newLinha.getDescricao())){
                try {
                    throw new InvalidRelationIdException();
                } catch (InvalidRelationIdException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        List<Familia> familias = mongoTemplate.find(Query.query(Criteria.where("linha").is(request.getDescricao())),
                Familia.class, "familia");

        if(!familias.isEmpty()){
            editLin(request);
            mongoTemplate.updateMulti(Query.query(Criteria.where("linha").is(request.getDescricao())),
                    Update.update("linha", request.getEditDescricao()),
                    Familia.class, "familia");
        }else {
            editLin(request);
        }

        return createResponse(newLinha);
    }

    @Override
    public LinhaDel del(String descricao, LinhaRequest request) {
        Linha del = new Linha();
        del.setDepartamento(request.getDepartamento());
        del.setDescricao(request.getDescricao());
        List<Familia> familias = mongoTemplate.find(Query.query(Criteria.where("linha").is(descricao)), Familia.class, "familia");
        if (!familias.isEmpty()){
            try {
                throw new DataIntegrityViolationException(descricao);
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityViolationException(descricao);
            }
        }else{
            mongoTemplate.remove(Query.query(Criteria.where("departamento").is(request.getDepartamento()).and("descricao").is(request.getDescricao())),
                    Linha.class, "linha");
        }
        return responseDel(del);
    }

    private LinhaResponse createResponse(Linha linha) {
        LinhaResponse response = new LinhaResponse();
        response.setDepartamento(linha.getDepartamento());
        response.setDescricao(linha.getDescricao());
        return response;
    }

    private LinhaDel responseDel(Linha linha){
        LinhaDel response = new LinhaDel();
        response.setDepDel(linha.getDepartamento());
        response.setDel(linha.getDescricao());
        return response;
    }

    private List<Linha> find(Linha campo){
        List<Linha> find = repository.findByname(campo.getDepartamento(), campo.getDescricao());
        return find;
    }

    private void editLin (LinhaEdit request){
        mongoTemplate.updateFirst(Query.query(Criteria.where("descricao").is(request.getDescricao())),
                Update.update("descricao", request.getEditDescricao()).set("departamento", request.getEditDepartamento()),
                Linha.class, "linha");
    }
}
