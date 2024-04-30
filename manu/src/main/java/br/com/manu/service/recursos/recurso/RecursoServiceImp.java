package br.com.manu.service.recursos.recurso;

import br.com.manu.model.fichaTecnica.FichaTecnicaRequest;
import br.com.manu.model.recursos.recurso.RecursoDel;
import br.com.manu.model.recursos.recurso.RecursoRequest;
import br.com.manu.model.recursos.recurso.RecursoResponse;
import br.com.manu.persistence.entity.recursos.Recurso;
import br.com.manu.persistence.entity.recursos.GrupoDeRecurso;
import br.com.manu.service.recursos.grupoDeRecurso.GrupoDeRecursoService;
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
    private MongoTemplate mongoTemplate;
    private GrupoDeRecursoService grupoDeRecursoService;

    public RecursoServiceImp(MongoTemplate mongoTemplate, GrupoDeRecursoService grupoDeRecursoService) {
        this.mongoTemplate = mongoTemplate;
        this.grupoDeRecursoService = grupoDeRecursoService;
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
        List<RecursoResponse> recursoResponseList = new ArrayList<>();
        if(recurso.equals("undefined")){
            List<Recurso> recursos = mongoTemplate.findAll(Recurso.class, "recurso");
            recursos.forEach(item -> {
                recursoResponseList.add(generateResponse(item));
            });
            return recursoResponseList;
        }
        List<Recurso> recursos = mongoTemplate.find(new Query().addCriteria(Criteria.where("recurso").regex(recurso)),
                Recurso.class, "recurso");
        recursos.forEach(item -> {
            recursoResponseList.add(generateResponse(item));
        });
        return recursoResponseList;
    }

    /**
     * Função recebe os parametros e atualiza o documento no banco, e retorna um objeto.
     * @param codigo
     * @param request
     * @return
     */

    @Override
    public RecursoResponse edit(int codigo, RecursoRequest request) {
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

                response.setValor(valor);
                response.setValorUnitario(calculo);
                /**
                 * Essa função void é para atualizar a collection de *grupoGrupoDeRecurso*
                 * de acordo com o objeto fornecido
                 */
                grupoDeRecursoService.updateGrupoRecursoByRecurso(response);
                return generateResponse(response);
            }

        }
        return null;
    }

    @Override
    public RecursoDel del(RecursoRequest request, int id) {
        boolean exists = mongoTemplate.exists(Query.query((Criteria.where("recursos")
                .elemMatch(Criteria.where("codigo").is(id)))),
                GrupoDeRecurso.class, "grupoDeRecurso");
        if(exists){
            try {
                throw new DataIntegrityViolationException(request.getRecurso());
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityViolationException(request.getRecurso());
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
        boolean exist = mongoTemplate.exists(Query.query(Criteria.where("recurso").is(request.getRecurso())),
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
