package br.com.manu.service.recursos.grupoDeRecurso;

import br.com.manu.model.recursos.cadastroDeRecurso.RecursoRequest;
import br.com.manu.model.recursos.grupoDeRecurso.*;
import br.com.manu.persistence.entity.recursos.GrupoDeRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GrupoDeRecursoServiceImp implements GrupoDeRecursoService {
    @Autowired
    private final MongoTemplate mongoTemplate;
    public GrupoDeRecursoServiceImp(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public GrupoDeRecursoResponse create(GrupoDeRecursoRequestSave request) {
        GrupoDeRecurso newGrupoRecurso = new GrupoDeRecurso();
        List<RecursoRequest> cadastroDeRecursos = new ArrayList<>();
        final double[] valor = {0};
        double valorDefinitvo;
        newGrupoRecurso.setCodigo(incrementCodigo());
        newGrupoRecurso.setGrupoRecurso(request.getGrupoRecurso());
        request.getRecursos().forEach(item ->  {
            cadastroDeRecursos.add(item);
        });

        cadastroDeRecursos.forEach(item ->{
            String _valorUnitario = item.getValorUnitario().replace(",", ".");
            valor[0] += Double.parseDouble(_valorUnitario);
        });
        valorDefinitvo = (float) Math.round(valor[0] * 100.0) / 100.0 ;
        newGrupoRecurso.setRecursos(cadastroDeRecursos);

        newGrupoRecurso.setValorTotalUnitario(valorDefinitvo);

        mongoTemplate.save(newGrupoRecurso, "grupoDeRecurso");
        return generateResponse(newGrupoRecurso);
    }

    @Override
    public List<GrupoDeRecursoResponse> getAll() {
        List<GrupoDeRecurso> allGrupoRecurso = mongoTemplate.findAll(GrupoDeRecurso.class, "grupoDeRecurso");
        List<GrupoDeRecursoResponse> grupoDeRecursoResponses = new ArrayList<>();
        allGrupoRecurso.forEach(item -> {
            grupoDeRecursoResponses.add(generateResponse(item));
        });
        return grupoDeRecursoResponses;
    }

    @Override
    public List<GrupoDeRecursoResponse> searchGrupoRecurso(String grupoRecurso) {
        List<GrupoDeRecurso> grupoDeRecursos = mongoTemplate.find(new Query().addCriteria(Criteria.where("grupoRecurso").regex(grupoRecurso)),
                GrupoDeRecurso.class, "grupoDeRecurso");
        List<GrupoDeRecursoResponse> grupoDeRecursoResponses = new ArrayList<>();
        grupoDeRecursos.forEach(item -> {
            grupoDeRecursoResponses.add(generateResponse(item));
        });
        return grupoDeRecursoResponses;
    }

    @Override
    public GrupoDeRecursoResponse edit(int id, GrupoDeRecursoRequestSave request) {
        GrupoDeRecurso editGrupoDeRecurso = new GrupoDeRecurso();
        final double[] valor = {0};
        double valorDefinitvo;
        editGrupoDeRecurso.setCodigo(id);
        editGrupoDeRecurso.setGrupoRecurso(request.getGrupoRecurso());
        editGrupoDeRecurso.setRecursos(request.getRecursos());
        editGrupoDeRecurso.getRecursos().forEach(item -> {
            String _valorUnitario = item.getValorUnitario().replace(",", ".");
            valor[0] += Double.parseDouble(_valorUnitario);
        });
        valorDefinitvo = (float) Math.round(valor[0] * 100.0) / 100.0 ;
        editGrupoDeRecurso.setValorTotalUnitario(valorDefinitvo);
        mongoTemplate.updateFirst(Query.query(Criteria.where("codigo").is(editGrupoDeRecurso.getCodigo())),
                Update.update("grupoDeRecurso", editGrupoDeRecurso.getGrupoRecurso()).set("valorTotalUnitario", editGrupoDeRecurso.getValorTotalUnitario())
                        .set("recursos", editGrupoDeRecurso.getRecursos()), GrupoDeRecurso.class, "grupoDeRecurso");

        return generateResponse(editGrupoDeRecurso);
    }

    @Override
    public GrupoDeRecursoDel dell(int id, GrupoDeRecursoResponseSimplified request) {
        GrupoDeRecursoDel del = new GrupoDeRecursoDel();
        del.setCodigo(request.getCodigo());
        del.setDel("Deletado com sucesso");
        mongoTemplate.remove(Query.query(Criteria.where("codigo").is(id)), GrupoDeRecurso.class, "grupoDeRecurso");
        return del;
    }

    private GrupoDeRecursoResponse generateResponse(GrupoDeRecurso grupoDeRecurso){
        GrupoDeRecursoResponse response = new GrupoDeRecursoResponse();
        GrupoDeRecursoResponseSimplified grupoDeRecursoSummary = new GrupoDeRecursoResponseSimplified();
        List<RecursoRequest> list = new ArrayList<>();
        GrupoDeRecursoRequestSave grupoRecursoAllItems = new GrupoDeRecursoRequestSave();
        grupoDeRecursoSummary.setGrupoRecurso(grupoDeRecurso.getGrupoRecurso());
        grupoDeRecursoSummary.setCodigo(grupoDeRecurso.getCodigo());
        grupoDeRecursoSummary.setQuantidadeRecursos(grupoDeRecurso.getRecursos().size());
        grupoDeRecursoSummary.setValorTotalUnitario(grupoDeRecurso.getValorTotalUnitario());
        grupoRecursoAllItems.setCodigo(grupoDeRecurso.getCodigo());
        grupoRecursoAllItems.setGrupoRecurso(grupoDeRecurso.getGrupoRecurso());
        grupoDeRecurso.getRecursos().forEach(item -> {
            list.add(item);
        });
        grupoRecursoAllItems.setRecursos(list);
        response.addAllItems(grupoRecursoAllItems);
        response.addSummaryItems(grupoDeRecursoSummary);

        return response;
    }

    private int incrementCodigo () {
        int id = 0;
        List<GrupoDeRecurso> cadastroDeRecurso =  mongoTemplate.findAll(GrupoDeRecurso.class, "grupoDeRecurso");
        if(cadastroDeRecurso.isEmpty()){
            id ++;
        }else {
            GrupoDeRecurso lastId = mongoTemplate.findOne(new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "_id")),
                    GrupoDeRecurso.class, "grupoDeRecurso");
            assert lastId != null;
            id = (int) (lastId.getCodigo() + 1);
        }
        return id;
    }

}
