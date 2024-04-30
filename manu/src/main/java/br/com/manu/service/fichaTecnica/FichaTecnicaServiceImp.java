package br.com.manu.service.fichaTecnica;

import br.com.manu.model.fichaTecnica.*;
import br.com.manu.model.recursos.grupoDeRecurso.GrupoModelFichaTecnica;
import br.com.manu.persistence.entity.fichaTenica.FichaTecnica;
import br.com.manu.persistence.entity.recursos.GrupoDeRecurso;
import br.com.manu.service.etapaDeProducao.EtapaDeProducaoService;
import br.com.manu.service.produto.ProdutoService;
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
public class FichaTecnicaServiceImp implements FichaTecnicaService {
    @Autowired
    MongoTemplate mongoTemplate;
    ProdutoService produtoService;
    EtapaDeProducaoService etapaDeProducaoService;

    public FichaTecnicaServiceImp (MongoTemplate mongoTemplate, ProdutoService produtoService, EtapaDeProducaoService etapaDeProducaoService){
        this.mongoTemplate = mongoTemplate;
        this.produtoService = produtoService;
        this.etapaDeProducaoService = etapaDeProducaoService;
    }

    @Override
    public FichaTecnicaResponse create(FichaTecnicaRequest request) {
        Duplicated(request);
        FichaTecnica fichaTecnica = new FichaTecnica();
        List<ModelEtapa> modelEtapaList = new ArrayList<>();
        final double[] totalDeRecurso = {0};
        final double[] totalDeItem = {0};
        final int[] quantidadeTempo = {0};
        fichaTecnica.setCodigo(incrementCodigo());
        fichaTecnica.setFichaTecnica(request.getFichaTecnica());
        fichaTecnica.setEtapas(request.getEtapas());
        request.getEtapas().forEach(item -> {
            modelEtapaList.add(item);
        });
        modelEtapaList.forEach(item -> {
            item.getEtapaDeProducaoRecursos().forEach(grupoDeRecurso -> {
              totalDeRecurso[0] +=  grupoDeRecurso.getValorTotalUnitario() * grupoDeRecurso.getQuantidade();
              quantidadeTempo[0] +=  grupoDeRecurso.getQuantidade();
            });
            item.getEtapaDeProducaoItems().forEach(items -> {
               totalDeItem[0] += items.getValorItem() * items.getQuantidade();
            });
        });
        double _totalDeRecurso = (float) Math.round(totalDeRecurso[0] * 100.0) / 100.0;
        fichaTecnica.setValorTotalRecursos(_totalDeRecurso);
        fichaTecnica.setValorTotalItens(totalDeItem[0]);
        fichaTecnica.setTempoEstimadoEmMinutos(quantidadeTempo[0]);

        mongoTemplate.save(fichaTecnica, "fichaTecnica");

        return createResponse(fichaTecnica);
    }

    @Override
    public List<FichaTecnicaResponse> getAll() {
        List<FichaTecnica> list = mongoTemplate.findAll(FichaTecnica.class, "fichaTecnica");
        List<FichaTecnicaResponse> fichaTecnicaResponseList = new ArrayList<>();
        list.forEach(item -> {
            fichaTecnicaResponseList.add(createResponse(item));
        });

        return fichaTecnicaResponseList;
    }

    @Override
    public FichaTecnicaResponse edit(FichaTecnicaRequest request, int codigo) {
        Query queryFichaTecnica = new Query().addCriteria(Criteria.where("codigo").is(codigo));
        FichaTecnica fichaTecnicaEdit = new FichaTecnica();
        List<ModelEtapa> modelEtapaList = new ArrayList<>();
        fichaTecnicaEdit.setCodigo(request.getCodigo());
        fichaTecnicaEdit.setFichaTecnica(request.getFichaTecnica());
        fichaTecnicaEdit.setEtapas(request.getEtapas());
        final double[] totalDeRecurso = {0};
        final double[] totalDeItem = {0};
        final int[] quantidadeTempo = {0};

        request.getEtapas().forEach(item -> {
            modelEtapaList.add(item);
        });

        modelEtapaList.forEach(item -> {
            item.getEtapaDeProducaoRecursos().forEach(grupoDeRecurso -> {
                totalDeRecurso[0] +=  grupoDeRecurso.getValorTotalUnitario() * grupoDeRecurso.getQuantidade();
                quantidadeTempo[0] +=  grupoDeRecurso.getQuantidade();
            });
            item.getEtapaDeProducaoItems().forEach(items -> {
                totalDeItem[0] += items.getValorItem() * items.getQuantidade();
            });
        });

        double _totalDeRecurso = (float) Math.round(totalDeRecurso[0] * 100.0) / 100.0;
        fichaTecnicaEdit.setValorTotalRecursos(_totalDeRecurso);
        fichaTecnicaEdit.setValorTotalItens(totalDeItem[0]);
        fichaTecnicaEdit.setTempoEstimadoEmMinutos(quantidadeTempo[0]);

        Update updateFichaTecnica = Update.update("fichaTecnica", fichaTecnicaEdit.getFichaTecnica())
                .set("valorTotalRecursos", fichaTecnicaEdit.getValorTotalRecursos()).set("valorTotalItens", fichaTecnicaEdit.getValorTotalItens())
                .set("tempoEstimadoEmMinutos", fichaTecnicaEdit.getTempoEstimadoEmMinutos()).set("etapas", fichaTecnicaEdit.getEtapas());
        mongoTemplate.updateFirst(queryFichaTecnica, updateFichaTecnica, FichaTecnica.class, "fichaTecnica");

        return createResponse(fichaTecnicaEdit);
    }

    @Override
    public List<FichaTecnicaResponse> getParams(String fichaTecnica) {
        List<FichaTecnicaResponse> fichaTecnicaResponseList = new ArrayList<>();
        List<FichaTecnica> list = new ArrayList<>();
        if (fichaTecnica.equals("undefined")){
             list = mongoTemplate.findAll(FichaTecnica.class, "fichaTecnica");
            list.forEach(item -> {
                fichaTecnicaResponseList.add(createResponse(item));
            });

            return fichaTecnicaResponseList;
        }
        list = mongoTemplate.find(new Query().addCriteria(Criteria.where("fichaTecnica").regex(fichaTecnica)), FichaTecnica.class, "fichaTecnica");

        list.forEach(item -> {
            fichaTecnicaResponseList.add(createResponse(item));
        });

        return fichaTecnicaResponseList;
    }

    /**
     * Requisição para os selects de etapas itens recursos, para montar as opções de escolha
     * @return
     */
    @Override
    public FichaTecnicaModalItemRecursoEtapa getModalItemRecursoEtapa() {
        FichaTecnicaModalItemRecursoEtapa fichaTecnicaModalItemRecursoEtapa = new FichaTecnicaModalItemRecursoEtapa();
        List<GrupoModelFichaTecnica> listGrupoRecurso = new ArrayList<>();
        List<GrupoDeRecurso> grupoDeRecursos = mongoTemplate.findAll(GrupoDeRecurso.class, "grupoDeRecurso");
        grupoDeRecursos.forEach(item -> {
            listGrupoRecurso.add(new GrupoModelFichaTecnica(item.getCodigo(), item.getGrupoRecurso(), item.getValorTotalUnitario()));
        });

        fichaTecnicaModalItemRecursoEtapa.setItens(produtoService.getItemFichaTecnicaMateriaPrima());
        fichaTecnicaModalItemRecursoEtapa.setRecursos(listGrupoRecurso);
        fichaTecnicaModalItemRecursoEtapa.setEtapas(etapaDeProducaoService.getEtapaFichaTecnica());

        return fichaTecnicaModalItemRecursoEtapa;
    }

    @Override
    public FichaTecnicaDel del(FichaTecnicaRequest request, int codigo) {
        FichaTecnicaDel fichaTecnicaDel = new FichaTecnicaDel();
        fichaTecnicaDel.setDel(request.getFichaTecnica());
        fichaTecnicaDel.setCodigo(request.getCodigo());
        mongoTemplate.remove(Query.query(Criteria.where("codigo").is(codigo)), FichaTecnica.class, "fichaTecnica");
        return fichaTecnicaDel;
    }

    /**
     * Essa função é para atualizar, o grupo de recurso que é utilizado na array etapaGrupoDeRecursos, caso esse grupo de recurso seja atualizado,
     * recebe uma query como parâmetro.
     * newGrupoRecurso é a lista que recebe a quantidade de grupo de recurso vai ser atualizado, depois um filtro é feito na fichaTecnica, dai os valores
     * são adicionados em uma array para filtrar os valores e depois atualizar a collection
     * @param queryGrupoRecursos
     */
    @Override
    public void updateFichaTecnicaByGrupoRecusos(Query queryGrupoRecursos) {
        List<GrupoDeRecurso> newGrupoRecurso = mongoTemplate.find(queryGrupoRecursos, GrupoDeRecurso.class, "grupoDeRecurso");
        List<FichaTecnica> fichasTenicas = mongoTemplate.findAll(FichaTecnica.class, "fichaTecnica");
        List<ModelGrupoDeRecurso> modelGrupoDeRecursos = new ArrayList<>();

        newGrupoRecurso.forEach(grupoDeRecurso -> {
            fichasTenicas.forEach(fichaTecnica -> {
                fichaTecnica.getEtapas().forEach(etapas -> {
                    etapas.getEtapaDeProducaoRecursos().stream().filter(grupoDeRecursoModel -> grupoDeRecursoModel.getGrupoRecurso().equals(grupoDeRecurso.getGrupoRecurso())).forEach(
                            grupoDeRecursoModel -> {
                            modelGrupoDeRecursos.add(grupoDeRecursoModel);
                    });
                });
            });
        });

        modelGrupoDeRecursos.forEach(grupoRecursoEdit -> {
            newGrupoRecurso.stream().filter(grupoDeRecurso -> grupoDeRecurso.getGrupoRecurso().equals(grupoRecursoEdit.getGrupoRecurso())).forEach(
                    grupoDeRecurso -> {
                        grupoRecursoEdit.setValorTotalUnitario(grupoDeRecurso.getValorTotalUnitario());
                    }
            );
            grupoRecursoEdit.setValorTotalRecurso(grupoRecursoEdit.getValorTotalUnitario() * grupoRecursoEdit.getQuantidade());
        });

        modelGrupoDeRecursos.forEach(grupoDeRecursoEdited -> {
            fichasTenicas.forEach(fichaTecnica -> {
                fichaTecnica.getEtapas().forEach(etapas -> {
                    etapas.getEtapaDeProducaoRecursos().stream().filter(codigo -> codigo.getGrupoRecurso().equals(grupoDeRecursoEdited.getGrupoRecurso())).forEach(
                            newEtapa -> {
                                double valorDefinitvo;
                                valorDefinitvo = (float) Math.round(grupoDeRecursoEdited.getValorTotalRecurso() * 100.0) / 100.0 ;
                                newEtapa.setValorTotalRecurso(valorDefinitvo);
                                newEtapa.setValorTotalUnitario(grupoDeRecursoEdited.getValorTotalUnitario());
                            }
                    );
                });
            });
        });

        modelGrupoDeRecursos.forEach(grupoDeRecursoEdited -> {
            Query fichaTenicaQuery = new Query((Criteria.where("etapas.etapaDeProducaoRecursos")
                    .elemMatch(Criteria.where("grupoRecurso").is(grupoDeRecursoEdited.getGrupoRecurso()))));
            Update updateFichaTecnicaByGrupoRecurso = new Update()
                    .set("etapas.$[elemento].etapaDeProducaoRecursos.$.valorTotalUnitario", grupoDeRecursoEdited.getValorTotalUnitario())
                    .set("etapas.$[elemento].etapaDeProducaoRecursos.$.valorTotalRecurso", grupoDeRecursoEdited.getValorTotalRecurso());
            updateFichaTecnicaByGrupoRecurso.filterArray(Criteria.where("elemento.etapaDeProducaoRecursos.grupoRecurso").is(grupoDeRecursoEdited.getGrupoRecurso()));
            mongoTemplate.updateMulti(fichaTenicaQuery, updateFichaTecnicaByGrupoRecurso, FichaTecnica.class, "fichaTecnica");

            FichaTecnica fichaTecnicaEdited = mongoTemplate.findOne(fichaTenicaQuery, FichaTecnica.class, "fichaTecnica");
            final double[] totalDeRecurso = {0};
            double valorDefinitvo;
            fichaTecnicaEdited.getEtapas().forEach(fichaTecnica -> {
                fichaTecnica.getEtapaDeProducaoRecursos().forEach(etapa -> {
                    totalDeRecurso[0] += etapa.getValorTotalRecurso();
                });
            });
            valorDefinitvo = (float) Math.round(totalDeRecurso[0] * 100.0) / 100.0 ;
            fichaTecnicaEdited.setValorTotalRecursos(valorDefinitvo);
            mongoTemplate.updateFirst(fichaTenicaQuery,
                    Update.update("valorTotalRecursos", fichaTecnicaEdited.getValorTotalRecursos()), FichaTecnica.class, "fichaTecnica");
        });
    }


    private FichaTecnicaResponse createResponse(FichaTecnica fichaTecnica){
        List<ModelEtapa> modelEtapaList = new ArrayList<>();
        FichaTecnica newFichaTecnica = new FichaTecnica();
        FichaTecnicaResponse fichaTecnicaResponse = new FichaTecnicaResponse();
        FichaTecnicaSummary fichaTecnicaSummary = generateSummary(fichaTecnica);
        newFichaTecnica.setCodigo(fichaTecnica.getCodigo());
        newFichaTecnica.setEtapas(fichaTecnica.getEtapas());
        newFichaTecnica.setFichaTecnica(fichaTecnica.getFichaTecnica());
        newFichaTecnica.setValorTotalItens(fichaTecnica.getValorTotalItens());
        newFichaTecnica.setValorTotalRecursos(fichaTecnica.getValorTotalRecursos());
        newFichaTecnica.setTempoEstimadoEmMinutos(fichaTecnica.getTempoEstimadoEmMinutos());
        fichaTecnica.getEtapas().forEach(item -> {
            modelEtapaList.add(item);
        });

        fichaTecnicaResponse.addSummaryItems(fichaTecnicaSummary);
        fichaTecnicaResponse.addAllItems(newFichaTecnica);

        return fichaTecnicaResponse;
    }

    private FichaTecnicaSummary generateSummary (FichaTecnica fichaTecnica){
        FichaTecnicaSummary fichaTecnicaSummary = new FichaTecnicaSummary();
        fichaTecnicaSummary.setCodigo(fichaTecnica.getCodigo());
        fichaTecnicaSummary.setFichaTecnica(fichaTecnica.getFichaTecnica());
        fichaTecnicaSummary.setValorTotalItem(fichaTecnica.getValorTotalItens());
        fichaTecnicaSummary.setValorTotalRecurso(fichaTecnica.getValorTotalRecursos());
        fichaTecnicaSummary.setTempoEstimadoEmMinutos(fichaTecnica.getTempoEstimadoEmMinutos());
        fichaTecnicaSummary.setQuantidadeEtapas(fichaTecnica.getEtapas().size());
        return fichaTecnicaSummary;
    }

    private void Duplicated (FichaTecnicaRequest request){
        boolean exist = mongoTemplate.exists(Query.query(Criteria.where("fichaTecnica").is(request.getFichaTecnica())),
                FichaTecnica.class, "fichaTecnica");
        if(exist) {
            try {
                throw new InvalidRelationIdException(request.getFichaTecnica());
            } catch (InvalidRelationIdException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private int incrementCodigo () {
        int id = 0;
        List<FichaTecnica> recurso =  mongoTemplate.findAll(FichaTecnica.class, "fichaTecnica");
        if(recurso.isEmpty()){
            id ++;
        }else {
            FichaTecnica lastId = mongoTemplate.findOne(new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "_id")),
                    FichaTecnica.class, "fichaTecnica");
            assert lastId != null;
            id = (lastId.getCodigo() + 1);
        }
        return id;
    }
}
