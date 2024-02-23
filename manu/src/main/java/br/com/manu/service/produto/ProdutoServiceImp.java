package br.com.manu.service.produto;
import br.com.manu.model.produto.*;
import br.com.manu.persistence.entity.produtos.item.Item;
import br.com.manu.persistence.entity.produtos.produto.Produto;
import br.com.manu.persistence.repository.item.ItemRepository;
import br.com.manu.persistence.repository.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.InvalidRelationIdException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoServiceImp implements ProdutoService {
    @Autowired
    private ProdutoRepository repositoryProduto;
    private ItemRepository repositoryItem;
    private final MongoTemplate mongoTemplate;
    public ProdutoServiceImp (MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public ProdutoResponse create(ProdutoRequest request) {
        Produto produto = new Produto();
        List<Item> itemProd = new ArrayList<>();
        if(duplicatedProd(request)){
            try {
                throw new InvalidRelationIdException();
            } catch (InvalidRelationIdException e) {
                throw new RuntimeException(e);
            }
        }
        produto.setIdProduto(incrementIdProd());
        produto.setDescricaoProduto(request.getDescricaoProduto());
        produto.setDepartamento(request.getDepartamento());
        produto.setLinha(request.getLinha());
        produto.setFamilia(request.getFamilia());
        produto.setGrupo(request.getGrupo());
        produto.setFornecedor(request.getFornecedor());
        produto.setModelo(request.getModelo());
        produto.setTipoProduto(request.getTipoProduto());
        produto.setUnidadeMedida(request.getUnidadeMedida());
        Produto prodSave = mongoTemplate.save(produto, "produto");

        request.getItems().forEach(item -> {
            itemProd.add(generateItem(item));
        });
        itemProd.forEach(
                item -> {
                    if(duplicatedItem(item)){
                        try {
                            throw new InvalidRelationIdException();
                        } catch (InvalidRelationIdException e) {
                            mongoTemplate.remove(Query.query(Criteria.where("_id").is(prodSave.getId())), Produto.class, "produto");
                            throw new RuntimeException(e);
                        }
                    }
                    Item generetedItem = registerItem(item, produto.getId());
                    mongoTemplate.save(generetedItem, "item");
                });

        List<Item> associateItem = mongoTemplate.find(Query.query(Criteria.where("_idProduto").is(produto.getId())), Item.class, "item");
        prodSave.setItems(associateItem);
        mongoTemplate.updateFirst(Query.query(Criteria.where("idProduto").is(prodSave.getIdProduto())),
                Update.update("items", prodSave.getItems()), Produto.class, "produto");

        return createResponse(prodSave);
    }
    /*O primeiro get é feito com base nessa class ResponseItem, porque as infarmações que importam são os itens em si, o _idProduto consegue trazer as informações
      associadas, então essa class vai ser a base da edição e delete,  a não que adicione outra funcionalidade futuramente.*/

    @Override
    public List<ResponseItem> getAll() {
        List<ResponseItem> responseProdutos = new ArrayList<>();
        List<Item> items = mongoTemplate.findAll(Item.class);
        items.forEach(item -> responseProdutos.add(responseTeste(item)));
        return responseProdutos;

    }

    @Override
    public List<ProdutoResponse> getParams(ProdutoRequestParams request) {
        List<ProdutoResponse> responses = new ArrayList<>();
        return null;
    }


    @Override
    public ProdutoDel del(int id) {
        ProdutoDel del = new ProdutoDel();
        Item item = mongoTemplate.findOne(Query.query(Criteria.where("idItem").is(id)), Item.class, "item");
        if(item != null){
            Produto produto = mongoTemplate.findById(item.get_idProduto(), Produto.class, "produto");
            assert produto != null;
            produto.getItems().forEach( itemRemove -> {
                if(id == itemRemove.getIdItem()){
                    mongoTemplate.updateFirst(new Query().addCriteria(Criteria.where("items.idItem").is(itemRemove.getIdItem())),
                            new Update().pull("items", Query.query(Criteria.where("idItem").is(itemRemove.getIdItem()))),
                            Produto.class, "produto");
                }
            });
            mongoTemplate.remove(Query.query(Criteria.where("idItem").is(id)), Item.class, "item");
        }
        String _id = String.valueOf(id);
        del.setDel("idCad: " + _id);
        del.setMessage("Deletado com sucesso");
        return del;
    }

    private Item generateItem(ItemModel item){
        Item newItem = new Item();
        newItem.setDescricaoItem(item.getDescricaoItem());
        newItem.setCodBarra(item.getCodBarra());
        newItem.setEspecificacao(item.getEspecificacao());
        newItem.setCor(item.getCor());
        return newItem;
    }
    @Transactional
    private Item registerItem(Item item, String idProd){
        Item newItem = new Item();
        newItem.setIdItem(incrementIdItem());
        newItem.set_idProduto(idProd);
        newItem.setDescricaoItem(item.getDescricaoItem());
        newItem.setCodBarra(item.getCodBarra());
        newItem.setEspecificacao(item.getEspecificacao());
        newItem.setCor(item.getCor());
        return newItem;
    }

    private ResponseItem responseTeste(Item item){
        ResponseItem teste = new ResponseItem();
        Produto produto = mongoTemplate.findById(item.get_idProduto(), Produto.class, "produto");
        teste.setIdItem(item.getIdItem());
        teste.setCodBarra(item.getCodBarra());
        teste.setDescricaoItem(item.getDescricaoItem());
        teste.setDepartamento(produto.getDepartamento());
        teste.setLinha(produto.getLinha());
        teste.setFamilia(produto.getFamilia());
        teste.setGrupo(produto.getGrupo());
        teste.setFornecedor(produto.getFornecedor());
        teste.setModelo(produto.getModelo());
        teste.setTipoProduto(produto.getTipoProduto());
        teste.setUnidadeMedida(produto.getUnidadeMedida());
        teste.setCor(item.getCor());
        teste.setEspecificacao(item.getEspecificacao());

        return teste;

    }
    private ProdutoResponse createResponse(Produto produto){
        ProdutoResponse response = new ProdutoResponse();
        response.setIdProduto(produto.getIdProduto());
        response.setDescricaoProduto(produto.getDescricaoProduto());
        response.setDepartamento(produto.getDepartamento());
        response.setLinha(produto.getLinha());
        response.setFamilia(produto.getFamilia());
        response.setGrupo(produto.getGrupo());
        response.setFornecedor(produto.getFornecedor());
        response.setModelo(produto.getModelo());
        response.setTipoProduto(produto.getTipoProduto());
        response.setUnidadeMedida(produto.getUnidadeMedida());
        response.setItems(produto.getItems());
        return response;
    }

    private Boolean duplicatedProd (ProdutoRequest request){
        Produto produtos = mongoTemplate.findOne(Query.query(Criteria.where("descricaoProduto").is(request.getDescricaoProduto())
                .and("departamento").is(request.getDepartamento()).and("linha").is(request.getLinha()).and("familia").is(request.getFamilia())
                .and("grupo").is(request.getGrupo()).and("fornecedor").is(request.getFornecedor()).and("modelo").is(request.getModelo())
                .and("tipoProduto").is(request.getTipoProduto()).and("unidadeMedida").is(request.getUnidadeMedida())),
                Produto.class, "produto");

        return produtos != null;
    }

    private Boolean duplicatedItem (Item item){
        Item items = mongoTemplate.findOne(Query.query(Criteria.where("descricaoItem").is(item.getDescricaoItem()).and("codBarra").is(item.getCodBarra())),
                Item.class, "item");

        return items != null;
    }

    private int incrementIdProd () {
        int id = 0;
        List<Produto> prodIds =  mongoTemplate.findAll(Produto.class, "produto");
        if(prodIds.isEmpty()){
            id ++;
        }else {
            Produto lastId = mongoTemplate.findOne(new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "_id")),
                    Produto.class, "produto");
            assert lastId != null;
            id = (int) (lastId.getIdProduto() + 1);
        }
        return id;
    }
    private int incrementIdItem () {
        int id = 0;
        List<Item> itemIds =  mongoTemplate.findAll(Item.class, "item");
        if(itemIds.isEmpty()){
            id ++;
        }else {
            Item lastId = mongoTemplate.findOne(new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "_id")),
                    Item.class, "item");
            assert lastId != null;
            id = (int) (lastId.getIdItem() + 1);
        }
        return id;
    }
}
