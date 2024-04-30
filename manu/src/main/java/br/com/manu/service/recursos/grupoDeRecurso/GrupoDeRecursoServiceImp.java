package br.com.manu.service.recursos.grupoDeRecurso;

import br.com.manu.model.recursos.recurso.RecursoRequest;
import br.com.manu.model.recursos.grupoDeRecurso.*;
import br.com.manu.persistence.entity.fichaTenica.FichaTecnica;
import br.com.manu.persistence.entity.recursos.GrupoDeRecurso;
import br.com.manu.persistence.entity.recursos.Recurso;
import br.com.manu.service.fichaTecnica.FichaTecnicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.management.relation.InvalidRelationIdException;
import java.util.*;

@Service
public class GrupoDeRecursoServiceImp implements GrupoDeRecursoService {
    @Autowired
    private MongoTemplate mongoTemplate;
    FichaTecnicaService fichaTecnicaService;
    public GrupoDeRecursoServiceImp(MongoTemplate mongoTemplate, FichaTecnicaService fichaTecnicaService) {
        this.mongoTemplate = mongoTemplate;
        this.fichaTecnicaService = fichaTecnicaService;
    }

    @Override
    public GrupoDeRecursoResponse create(GrupoDeRecursoRequestSave request) {
        Duplicated(request);
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
        List<GrupoDeRecursoResponse> grupoDeRecursoResponses = new ArrayList<>();
        if(grupoRecurso.equals("undefined")){
            List<GrupoDeRecurso> grupoDeRecursos = mongoTemplate.findAll(GrupoDeRecurso.class, "grupoDeRecurso");
            grupoDeRecursos.forEach(item -> {
                grupoDeRecursoResponses.add(generateResponse(item));
            });
            return grupoDeRecursoResponses;
        }
        List<GrupoDeRecurso> grupoDeRecursos = mongoTemplate.find(new Query().addCriteria(Criteria.where("grupoRecurso").regex(grupoRecurso)),
                GrupoDeRecurso.class, "grupoDeRecurso");

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

        Query updateGrupoRecurso = new Query().addCriteria(Criteria.where("grupoRecurso").is(request.getGrupoRecurso()));
        mongoTemplate.updateFirst(updateGrupoRecurso,
                Update.update("grupoDeRecurso", editGrupoDeRecurso.getGrupoRecurso()).set("valorTotalUnitario", editGrupoDeRecurso.getValorTotalUnitario())
                        .set("recursos", editGrupoDeRecurso.getRecursos()), GrupoDeRecurso.class, "grupoDeRecurso");

        fichaTecnicaService.updateFichaTecnicaByGrupoRecusos(updateGrupoRecurso);

        return generateResponse(editGrupoDeRecurso);
    }

    @Override
    public GrupoDeRecursoDel dell(int id, GrupoDeRecursoResponseSimplified request) {
        Query existsFichaTecnica = new Query((Criteria.where("etapas.etapaDeProducaoRecursos")
                .elemMatch(Criteria.where("grupoRecurso").is(request.getGrupoRecurso()))));
        boolean exists = mongoTemplate.exists(existsFichaTecnica, FichaTecnica.class, "fichaTecnica");
        if (exists){
            try {
                throw new DataIntegrityViolationException(request.getGrupoRecurso());
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityViolationException(request.getGrupoRecurso());
            }
        }
        GrupoDeRecursoDel del = new GrupoDeRecursoDel();
        del.setCodigo(request.getCodigo());
        del.setDel("Deletado com sucesso");
        mongoTemplate.remove(Query.query(Criteria.where("codigo").is(id)), GrupoDeRecurso.class, "grupoDeRecurso");
        return del;
    }

    /**
     * Essa função recebe um objeto de tipo recurso, caso esse recurso tenha sido atualizado,
     * os valores vão ser atualizados de acordo com o valor objeto fornecido
     * @param recurso
     */
    @Override
    public void updateGrupoRecursoByRecurso(Recurso recurso) {

        String valor = String.valueOf(recurso.getValor()).replace(".", ",");
        String calculoForGrupoRecurso = String.valueOf(recurso.getValorUnitario()).replace(".", ",");

        Query queryGrupoRecursos = new Query((Criteria.where("recursos")
                .elemMatch(Criteria.where("codigo").is(recurso.getCodigo()))));

        Update updateInGrupoDeRecursos = new Update().set("recursos.$.tipoRecurso", recurso.getTipoRecurso())
                .set("recursos.$.unidadeMedida", recurso.getUnidadeMedida()).set("recursos.$.recurso", recurso.getRecurso())
                .set("recursos.$.valor", valor).set("recursos.$.valorUnitario", calculoForGrupoRecurso);
        mongoTemplate.updateMulti(queryGrupoRecursos, updateInGrupoDeRecursos, GrupoDeRecurso.class, "grupoDeRecurso");

        List<GrupoDeRecurso> grupoDeRecursos = mongoTemplate.find(queryGrupoRecursos, GrupoDeRecurso.class, "grupoDeRecurso");
        final double[] valorUnitario = {0};
        final double[] valorDefinitvo = {0};
        grupoDeRecursos.forEach(item -> {
            item.getRecursos().forEach(rec -> {
                String _valorUnitario = rec.getValorUnitario().replace(",", ".");
                valorUnitario[0] += Double.parseDouble(_valorUnitario);
            });
            valorDefinitvo[0] = (float) Math.round(valorUnitario[0] * 100.0) / 100.0 ;
            mongoTemplate.updateFirst(Query.query(Criteria.where("codigo").is(item.getCodigo())),
                    Update.update("valorTotalUnitario", valorDefinitvo[0]), GrupoDeRecurso.class, "grupoDeRecurso");
            valorUnitario[0] = 0;
        });

        /**
         * Essa função void ficha tecnica é para atualizar a collection de *fichaTecnica*
         * Envia a querry feita de acordo com o recurso recebido.
         */
        fichaTecnicaService.updateFichaTecnicaByGrupoRecusos(queryGrupoRecursos);

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

    private void Duplicated (GrupoDeRecursoRequestSave request){
        boolean exist = mongoTemplate.exists(Query.query(Criteria.where("grupoRecurso").is(request.getGrupoRecurso())),
                GrupoDeRecurso.class, "grupoDeRecurso");
        if(exist) {
            try {
                throw new InvalidRelationIdException(request.getGrupoRecurso());
            } catch (InvalidRelationIdException e) {
                throw new RuntimeException(e);
            }
        }
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
