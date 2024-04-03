package br.com.manu.service.cadastroDeRecurso;

import br.com.manu.model.cadastroDeRecurso.CadastroDeRecursoDel;
import br.com.manu.model.cadastroDeRecurso.CadastroDeRecursoRequest;
import br.com.manu.model.cadastroDeRecurso.CadastroDeRecursoResponse;
import br.com.manu.persistence.entity.cadastroDeRecurso.CadastroDeRecurso;
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
public class CadastroDeRecursoServiceImp implements CadastroDeRecursoService {
    @Autowired
    private final MongoTemplate mongoTemplate;

    public CadastroDeRecursoServiceImp(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public CadastroDeRecursoResponse create(CadastroDeRecursoRequest request) {

        CadastroDeRecurso cadastroDeRecurso = new CadastroDeRecurso();
        cadastroDeRecurso.setCodigo(incrementCodigo());
        cadastroDeRecurso.setRecurso(request.getRecurso());
        cadastroDeRecurso.setTipoRecurso(request.getTipoRecurso());
        cadastroDeRecurso.setUnidadeMedida(request.getUnidadeMedida());
        float valor = Float.parseFloat(request.getValor());
        float calculo;
        if(request.getTipoRecurso().equals("MÃO DE OBRA")){
            calculo = (valor / 220) / 60;
            cadastroDeRecurso.setValorMedida((float) Math.round(calculo * 100.0) / 100.0);
            cadastroDeRecurso.setValor(valor);
        }
        mongoTemplate.save(cadastroDeRecurso, "cadastroDeRecurso");
        return generateResponse(cadastroDeRecurso);
    }

    @Override
    public List<CadastroDeRecursoResponse> getAll() {
        List<CadastroDeRecurso> cadastroDeRecurso = mongoTemplate.findAll(CadastroDeRecurso.class);
        List<CadastroDeRecursoResponse> cadastroDeRecursoResponseList = new ArrayList<>();
        cadastroDeRecurso.forEach(item -> {
            cadastroDeRecursoResponseList.add(generateResponse(item));
        });
        return cadastroDeRecursoResponseList;
    }

    @Override
    public CadastroDeRecursoResponse edit(int codigo, CadastroDeRecursoRequest request) {
        CadastroDeRecurso cadastroDeRecurso = mongoTemplate.findOne(Query.query(Criteria.where("codigo").is(codigo)),
                CadastroDeRecurso.class, "cadastroDeRecurso");
        CadastroDeRecurso response = new CadastroDeRecurso();
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
                        Update.update("recurso", request.getRecurso()).set("valor", valor).set("valorMinuto", calculo)
                                .set("unidadeMedida", request.getUnidadeMedida()).set("tipoRecurso", request.getTipoRecurso()),
                        CadastroDeRecurso.class, "cadastroDeRecurso");

                response.setValor(valor);
                response.setValorMedida(calculo);
                return generateResponse(response);
            }

        }
        return null;
    }

    @Override
    public CadastroDeRecursoDel del(int id) {
        CadastroDeRecursoDel cadastroDeRecursoDel = new CadastroDeRecursoDel();
        mongoTemplate.remove(Query.query(Criteria.where("codigo").is(id)), CadastroDeRecurso.class, "cadastroDeRecurso");
        cadastroDeRecursoDel.setCodigo(id);
        cadastroDeRecursoDel.setDel("O item: " + id + " foi deletado");
        return cadastroDeRecursoDel;

    }


    private CadastroDeRecursoResponse generateResponse(CadastroDeRecurso cadastroDeRecurso){
        CadastroDeRecursoResponse cadastroDeRecursoResponse = new CadastroDeRecursoResponse();
        cadastroDeRecursoResponse.setCodigo(cadastroDeRecurso.getCodigo());
        cadastroDeRecursoResponse.setTipoRecurso(cadastroDeRecurso.getTipoRecurso());
        cadastroDeRecursoResponse.setRecurso(cadastroDeRecurso.getRecurso());
        cadastroDeRecursoResponse.setUnidadeMedida(cadastroDeRecurso.getUnidadeMedida());
        cadastroDeRecursoResponse.setValor(String.format("%.2f", cadastroDeRecurso.getValor()));
        cadastroDeRecursoResponse.setValorMedida(String.format("%.2f", cadastroDeRecurso.getValorMedida()));
        return cadastroDeRecursoResponse;

    }

    private int incrementCodigo () {
        int id = 0;
        List<CadastroDeRecurso> cadastroDeRecurso =  mongoTemplate.findAll(CadastroDeRecurso.class, "cadastroDeRecurso");
        if(cadastroDeRecurso.isEmpty()){
            id ++;
        }else {
            CadastroDeRecurso lastId = mongoTemplate.findOne(new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "_id")),
                    CadastroDeRecurso.class, "cadastroDeRecurso");
            assert lastId != null;
            id = (int) (lastId.getCodigo() + 1);
        }
        return id;
    }
}
