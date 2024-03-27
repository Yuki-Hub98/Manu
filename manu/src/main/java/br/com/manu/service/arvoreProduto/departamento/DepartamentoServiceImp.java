package br.com.manu.service.arvoreProduto.departamento;

import br.com.manu.model.arvoreProduto.departamento.DepartamentoDel;
import br.com.manu.model.arvoreProduto.departamento.DepartamentoRequest;
import br.com.manu.model.arvoreProduto.departamento.DepartamentoResponse;
import br.com.manu.persistence.entity.arvoreProduto.Departamento;
import br.com.manu.persistence.entity.arvoreProduto.Linha;
import br.com.manu.persistence.entity.produtos.produto.Produto;
import br.com.manu.persistence.repository.arvoreProduto.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
public class DepartamentoServiceImp implements DepartamentoService{
    @Autowired
    private DepartamentoRepository repository;

    private final MongoTemplate mongoTemplate;
    public DepartamentoServiceImp(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;

    }

    @Override
    public DepartamentoResponse create(DepartamentoRequest request) {
        Duplicated(request);
        Departamento departamento = new Departamento();
        departamento.setDescricao(request.getDescricao());
        departamento.setCodigo(incrementCodigo());
        repository.save(departamento);
        return createResponse(departamento);
        }

    @Override
    public List<DepartamentoResponse> getAll() {
        List<DepartamentoResponse> responses = new ArrayList<>();
        List<Departamento> departamentos = repository.findAll();
        if(!departamentos.isEmpty()){
            departamentos.forEach(departamento -> responses.add(createResponse(departamento)));
        }
        return responses;
    }

    @Override
    public List<DepartamentoResponse> getDescricao(String request) {
        List<DepartamentoResponse> response = new ArrayList<>();
        List<Departamento> search = repository.findRegex(request);
        if(!search.isEmpty()){
            search.forEach(descricao -> response.add(createResponse(descricao)));
        }
        return response;
    }

    /**
     * Recebe o objeto que precisa ser editado, verifica se existe esse objeto em outras collections @linha e @produto, collections relacionadas
     * com @departamento, executa um updateMulti na collection @linha e @produto com a informação nova  do objeto.
     * @param codigo
     * @param request
     * @return objeto editado
     **/
    @Override
    public DepartamentoResponse edit(int codigo, DepartamentoRequest request) {
        Duplicated(request);
        Departamento newDepartamento = new Departamento();
        newDepartamento.setDescricao(request.getDescricao());
        newDepartamento.setCodigo(codigo);
        Departamento departamentoEdited = mongoTemplate.findOne(Query.query(Criteria.where("codigo").is(codigo)),
                Departamento.class, "departamento");
        Boolean existsInLinha = mongoTemplate.exists(Query.query(Criteria.where("departamento").is(departamentoEdited.getDescricao())),
                Linha.class, "linha");
        Boolean existsInProduto = mongoTemplate.exists(Query.query(Criteria.where("departamento").is(departamentoEdited.getDescricao())),
                Produto.class, "produto");

        editDep(newDepartamento);
        if(existsInLinha){
            mongoTemplate.updateMulti(Query.query(Criteria.where("departamento").is(departamentoEdited.getDescricao())),
                    Update.update("departamento", newDepartamento.getDescricao()),
                    Linha.class, "linha");
            if (existsInProduto) {
                mongoTemplate.updateMulti(Query.query(Criteria.where("departamento").is(departamentoEdited.getDescricao())),
                        Update.update("departamento", newDepartamento.getDescricao()),
                        Produto.class, "produto");
            }
        }

        return createResponse(newDepartamento);
    }

    /**
     * Antes de deletar, verifica na @produto e @linha, duas collections que estõo relacionadas com @departamento, se tiver lança um exeception para
     * Avisar que esse documento está sendo utilizado, tem que excluir primeiro nas collections relacionadas para conseguir excluir.
     * @param codigo
     * @param request
     * @return objeto excluido
     * @throws DataIntegrityViolationException
     **/
    @Override
    public DepartamentoDel del(int codigo, DepartamentoRequest request) throws DataIntegrityViolationException {
        Boolean existsLinha = mongoTemplate.exists(Query.query(Criteria.where("departamento").is(request.getDescricao())),
                Linha.class, "linha");
        Boolean existsItem = mongoTemplate.exists(Query.query(Criteria.where("departamento").is(request.getDescricao())),
                Produto.class, "produto");
        if(existsLinha || existsItem){
            try {
                throw new DataIntegrityViolationException(request.getDescricao());
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityViolationException(request.getDescricao());
            }
        }
        Departamento del = new Departamento();
        del.setDescricao(request.getDescricao());
        del.setCodigo(codigo);
        mongoTemplate.remove(Query.query(Criteria.where("codigo").is(codigo)),
                Departamento.class, "departamento");
        return responseDel(del);
    }


    private DepartamentoResponse createResponse(Departamento departamento) {
        DepartamentoResponse response = new DepartamentoResponse();
        response.setCodigo(departamento.getCodigo());
        response.setDescricao(departamento.getDescricao());
        return response;

    }

    private DepartamentoDel responseDel(Departamento departamento){
        DepartamentoDel response = new DepartamentoDel();
        response.setCodigo(departamento.getCodigo());
        response.setDel(departamento.getDescricao());
        return response;
    }

    /**
     * Verifica se existe o objeto na collection @departamento, se tiver lança exception
     * informando que já existe objeto cadastrado
     * @param request
     **/
    private void Duplicated (DepartamentoRequest request){
        Boolean exist = mongoTemplate.exists(Query.query(Criteria.where("descricao").is(request.getDescricao())),
                Departamento.class, "departamento");
        if(exist) {
            try {
                throw new InvalidRelationIdException();
            } catch (InvalidRelationIdException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void editDep (Departamento newDepartamento){
        mongoTemplate.updateFirst(Query.query(Criteria.where("codigo").is(newDepartamento.getCodigo())),
                Update.update("descricao", newDepartamento.getDescricao()),
                Departamento.class, "departamento");
    }

    private int incrementCodigo () {
        int id = 0;
        List<Departamento> prodIds =  mongoTemplate.findAll(Departamento.class, "departamento");
        if(prodIds.isEmpty()){
            id ++;
        }else {
            Departamento lastId = mongoTemplate.findOne(new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "_id")),
                    Departamento.class, "departamento");
            assert lastId != null;
            id = (int) (lastId.getCodigo() + 1);
        }
        return id;
    }

}
