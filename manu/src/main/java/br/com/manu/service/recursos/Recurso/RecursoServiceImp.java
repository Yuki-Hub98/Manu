package br.com.manu.service.recursos.Recurso;

import br.com.manu.model.recursos.cadastroDeRecurso.RecursoDel;
import br.com.manu.model.recursos.cadastroDeRecurso.RecursoRequest;
import br.com.manu.model.recursos.cadastroDeRecurso.RecursoResponse;
import br.com.manu.persistence.entity.recursos.Recurso;
import br.com.manu.persistence.entity.recursos.GrupoDeRecurso;
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
public class RecursoServiceImp implements RecursoService {
    @Autowired
    private final MongoTemplate mongoTemplate;

    public RecursoServiceImp(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public RecursoResponse create(RecursoRequest request) {
        Duplicated(request);
        Recurso recurso = new Recurso();
        recurso.setCodigo(incrementCodigo());
        recurso.setRecurso(request.getRecurso());
        recurso.setTipoRecurso(request.getTipoRecurso());
        recurso.setUnidadeMedida(request.getUnidadeMedida());
        float valor = Float.parseFloat(request.getValor());
        double calculo;
        if(request.getTipoRecurso().equals("MÃO DE OBRA")){
            calculo = (float) Math.round(((valor / 220) / 60) * 100.0) / 100.0;
            recurso.setValorUnitario(calculo);
            recurso.setValor(valor);
        }
        mongoTemplate.save(recurso, "recurso");
        return generateResponse(recurso);
    }

    @Override
    public List<RecursoResponse> getAll() {
        List<Recurso> recurso = mongoTemplate.findAll(Recurso.class);
        List<RecursoResponse> recursoResponseList = new ArrayList<>();
        recurso.forEach(item -> {
            recursoResponseList.add(generateResponse(item));
        });
        return recursoResponseList;
    }

    @Override
    public List<RecursoResponse> searchRecurso(String recurso) {
        List<Recurso> recursos = mongoTemplate.find(new Query().addCriteria(Criteria.where("recurso").regex(recurso)),
                Recurso.class, "recurso");
        List<RecursoResponse> recursoResponseList = new ArrayList<>();
        recursos.forEach(item -> {
            recursoResponseList.add(generateResponse(item));
        });
        return recursoResponseList;
    }

    @Override
    public RecursoResponse edit(int codigo, RecursoRequest request) {
        Duplicated(request);
        Recurso cadastroDeRecurso = mongoTemplate.findOne(Query.query(Criteria.where("codigo").is(codigo)),
                Recurso.class, "recurso");
        Recurso response = new Recurso();
        float valor = Float.parseFloat(request.getValor().replace(",", "."));
        response.setCodigo(codigo);
        response.setRecurso(request.getRecurso());
        response.setUnidadeMedida(request.getUnidadeMedida());
        response.setTipoRecurso(request.getTipoRecurso());

        if(cadastroDeRecurso != null){
            if(request.getTipoRecurso().equals("MÃO DE OBRA")){
                float media = (valor / 220) / 60;
                double calculo = (float) Math.round(media * 100.0) / 100.0;

                mongoTemplate.updateFirst(Query.query(Criteria.where("codigo").is(cadastroDeRecurso.getCodigo())),
                        Update.update("recurso", request.getRecurso()).set("valor", valor).set("valorUnitario", calculo)
                                .set("unidadeMedida", request.getUnidadeMedida()).set("tipoRecurso", request.getTipoRecurso()),
                        Recurso.class, "recurso");

                String calculoForGrupoRecurso = String.valueOf(calculo).replace(".", ",");
                Query queryGrupoRecursos = new Query((Criteria.where("recursos")
                        .elemMatch(Criteria.where("codigo").is(codigo))));
                Update updateInGrupoDeRecursos = new Update().set("recursos.$.tipoRecurso", request.getTipoRecurso())
                        .set("recursos.$.unidadeMedida", request.getUnidadeMedida()).set("recursos.$.recurso", request.getRecurso())
                        .set("recursos.$.valor", request.getValor()).set("recursos.$.valorUnitario", calculoForGrupoRecurso);

                mongoTemplate.updateMulti(queryGrupoRecursos, updateInGrupoDeRecursos, GrupoDeRecurso.class, "grupoDeRecurso");

                List<GrupoDeRecurso> grupoDeRecursos = mongoTemplate.find(Query.query(Criteria.where("recursos").elemMatch(Criteria.where("codigo").is(codigo))),
                        GrupoDeRecurso.class, "grupoDeRecurso");

                final double[] valorUnitario = {0};
                double valorDefinitvo;
                grupoDeRecursos.forEach(item -> {
                    item.getRecursos().forEach(recurso -> {
                        String _valorUnitario = recurso.getValorUnitario().replace(",", ".");
                        valorUnitario[0] += Double.parseDouble(_valorUnitario);
                    });
                });
                valorDefinitvo = (float) Math.round(valorUnitario[0] * 100.0) / 100.0 ;

                grupoDeRecursos.forEach(item -> {
                    mongoTemplate.updateFirst(Query.query(Criteria.where("codigo").is(item.getCodigo())),
                            Update.update("valorTotalUnitario", valorDefinitvo), GrupoDeRecurso.class, "grupoDeRecurso");
                });

                response.setValor(valor);
                response.setValorUnitario(calculo);
                return generateResponse(response);
            }

        }
        return null;
    }

    @Override
    public RecursoDel del(int id) {
        boolean exists = mongoTemplate.exists(Query.query((Criteria.where("recursos")
                .elemMatch(Criteria.where("codigo").is(id)))),
                GrupoDeRecurso.class, "grupoDeRecurso");
        if(exists){
            try {
                throw new DataIntegrityViolationException("codigo " + id);
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityViolationException("codigo " + id);
            }
        }
        RecursoDel recursoDel = new RecursoDel();
        mongoTemplate.remove(Query.query(Criteria.where("codigo").is(id)), Recurso.class, "recurso");
        recursoDel.setCodigo(id);
        recursoDel.setDel("O item: " + id + " foi deletado");
        return recursoDel;

    }


    private RecursoResponse generateResponse(Recurso recurso){
        RecursoResponse recursoResponse = new RecursoResponse();
        recursoResponse.setCodigo(recurso.getCodigo());
        recursoResponse.setTipoRecurso(recurso.getTipoRecurso());
        recursoResponse.setRecurso(recurso.getRecurso());
        recursoResponse.setUnidadeMedida(recurso.getUnidadeMedida());
        recursoResponse.setValor(String.format("%.2f", recurso.getValor()));
        recursoResponse.setValorUnitario(String.format("%.2f", recurso.getValorUnitario()));
        return recursoResponse;

    }

    private void Duplicated (RecursoRequest request){
        boolean exist = mongoTemplate.exists(Query.query(Criteria.where("descricao").is(request.getRecurso())),
                GrupoDeRecurso.class, "grupoDeRecurso");
        if(exist) {
            try {
                throw new InvalidRelationIdException(request.getRecurso());
            } catch (InvalidRelationIdException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private int incrementCodigo () {
        int id = 0;
        List<Recurso> recurso =  mongoTemplate.findAll(Recurso.class, "recurso");
        if(recurso.isEmpty()){
            id ++;
        }else {
            Recurso lastId = mongoTemplate.findOne(new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "_id")),
                    Recurso.class, "recurso");
            assert lastId != null;
            id = (lastId.getCodigo() + 1);
        }
        return id;
    }
}
