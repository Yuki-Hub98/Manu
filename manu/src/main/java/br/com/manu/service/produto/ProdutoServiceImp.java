package br.com.manu.service.produto;
import br.com.manu.model.produto.*;
import br.com.manu.persistence.entity.produtos.csticms.CstIcms;
import br.com.manu.persistence.entity.produtos.item.Item;
import br.com.manu.persistence.entity.produtos.ncm.Ncm;
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

    /**
     * Modulo de criação
     * Recebe um objeto produto e uma lista de items a serem adicionadas
     * @param request objeto produto e array de items
     * @return o produto e o item adicionado.
     */
    @Override
    public ProdutoResponse create(ProdutoRequest request) {
        Produto produto = new Produto();
        List<Item> itemProd = new ArrayList<>();
        if (duplicatedProd(request)){
           Produto duplicateProd = mongoTemplate.findOne(Query.query(Criteria.where("descricaoProduto").is(request.getDescricaoProduto())),
                    Produto.class, "produto");
           if (duplicateProd != null) {
               request.getItems().forEach(item -> {
                   itemProd.add(generateItem(item));
               });
               itemProd.forEach(
                       item -> {
                           if (duplicatedItem(item)) {
                               try {
                                   throw new InvalidRelationIdException();
                               } catch (InvalidRelationIdException e) {
                                   mongoTemplate.remove(Query.query(Criteria.where("_id").is(duplicateProd.getId())), Produto.class, "produto");
                                   throw new RuntimeException(e);
                               }
                           }
                           Item generetedItem = registerItem(item, duplicateProd.getId());
                           mongoTemplate.save(generetedItem, "item");
                       });
               List<Item> associateItem = mongoTemplate.find(Query.query(Criteria.where("_idProduto").is(duplicateProd.getId())), Item.class, "item");
               duplicateProd.setItems(associateItem);
               mongoTemplate.updateFirst(Query.query(Criteria.where("idProduto").is(duplicateProd.getIdProduto())),
                       Update.update("items", duplicateProd.getItems()), Produto.class, "produto");

               return createResponseSave(duplicateProd);
           }
        }
        String[] decricaoRequest = request.getCstIcmsDescricao().split(" - ");
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
        produto.setProcessado(request.isProcessado());
        produto.setCstIcmsOrigem(request.getCstIcmsOrigem());
        produto.setCstIcmsCodigo(decricaoRequest[0]);
        produto.setCstIcmsDescricao(decricaoRequest[1]);
        produto.setCstPisConfins(request.getCstPisConfins());
        produto.setNcmCodigo(request.getNcmCodigo());
        produto.setNcmDescricao(request.getNcmDescricao());
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
                    Item generetedItem = registerItem(item, prodSave.getId());
                    mongoTemplate.save(generetedItem, "item");
                });
        /*
         * Esse find é executado na collection Item para pegar o id de produto associado a item (_idProduto), seta a lista com os ids associados a array
         * associateItem e essa lista é seta na lista de items do produto, depois um update executado ness produto.
         **/

        List<Item> associateItem = mongoTemplate.find(Query.query(Criteria.where("_idProduto").is(produto.getId())), Item.class, "item");
        prodSave.setItems(associateItem);
        mongoTemplate.updateFirst(Query.query(Criteria.where("idProduto").is(prodSave.getIdProduto())),
                Update.update("items", prodSave.getItems()), Produto.class, "produto");

        return createResponseSave(prodSave);
    }
    /**
     * Modulo de pesquisa geral
     * O primeiro get é feito com base nessa class ResponseItem, porque as infarmações que importam são os itens em si, o _idProduto consegue trazer as informações
     * associadas, como ResponseItem é o response que vamos disponibilizar para o usuario, então trabalharemos na class Response Item.
     * */
    @Override
    public List<ResponseItem> getAll() {
        List<ResponseItem> responseItems = new ArrayList<>();
        List<Item> items = mongoTemplate.findAll(Item.class);
        items.forEach(item -> responseItems.add(createResponseItem(item)));
        return responseItems;

    }

    /**
     * Modulo pesquisa por parametro
     * Esse modulo verifica se os parametros de item estão vazios, e depois parametro produto, o find e item e produtos e adiciona na array de retorno.
     * @return retorna uma lista de items
     */
    @Override
    public List<ResponseItem> getParams(String idItem, String descricaoItem, String codBarra, String departamento,
                                        String linha, String familia, String grupo, String fornecedor, String modelo,
                                        String tipoProduto, String unidadeMedida, String cor, String especificacao) {

        List<ResponseItem> responseItems = new ArrayList<>();

        if (!descricaoItem.isEmpty() || !codBarra.isEmpty() || !cor.isEmpty() || !especificacao.isEmpty()){
            /*
             * Find executado na collection de Item, adiciona na array responseItem.
             * */
            List<Item> items = mongoTemplate.find(new Query().addCriteria( new Criteria().orOperator(Criteria.where("codBarra").is(codBarra),
                        Criteria.where("descricaoItem").regex(descricaoItem))),
                        Item.class, "item");
            if (!items.isEmpty()){
                items.forEach(item -> responseItems.add(createResponseItem(item)));
            }
        }
        if (!departamento.isEmpty() || !linha.isEmpty() || !familia.isEmpty() || !grupo.isEmpty() ||
                !fornecedor.isEmpty() || !modelo.isEmpty() || !tipoProduto.isEmpty() || !unidadeMedida.isEmpty()){
            /*
             * Find executado na collection de Produto, adiciona na array responseItem.
             * */
            List<Produto> produtos = mongoTemplate.find(new Query().addCriteria(new Criteria().orOperator(Criteria.where("departamento").is(departamento),
                    Criteria.where("linha").is(linha), Criteria.where("familia").is(familia), Criteria.where("grupo").is(grupo),
                    Criteria.where("fornecedor").is(fornecedor), Criteria.where("modelo").is(modelo), Criteria.where("tipoProduto").is(tipoProduto),
                    Criteria.where("unidadeMedida").is(unidadeMedida), Criteria.where("cor").is(cor), Criteria.where("especificacao").is(especificacao))),
                    Produto.class, "produto");
            if (!produtos.isEmpty()){
                produtos.forEach(produto -> {
                    if (!responseItems.contains(createResponseItemByProduto(produto))){
                        responseItems.add(createResponseItemByProduto(produto));
                    }});
            }
        }
        if (!idItem.isEmpty()) {
            if (idItem.equals("undefined") || idItem.equals("")){
                return responseItems;
            }
            long id = Long.parseLong(idItem);
            Item item = mongoTemplate.findOne(new Query().addCriteria( new Criteria().orOperator(Criteria.where("idItem").is(id))),
                    Item.class, "item");
            responseItems.add(0, createResponseItem(item));
        }

        return responseItems;
    }

    /**
     * Pesquisa do modelo do produto.
     * @param linha
     * @return Lista de modelos, daquela linha, daquele produto
     */
    @Override
    public List<ModeloProduto> getModels(String linha) {
        List<ModeloProduto> modeloProdutos = new ArrayList<>();
        List<Produto> produtos = mongoTemplate.find(Query.query(Criteria.where("linha").is(linha)), Produto.class, "produto");
        produtos.forEach(item -> {
            modeloProdutos.add(new ModeloProduto(item.getModelo()));
        });
        return modeloProdutos;
    }

    /**
     * Modulo de edit
     * Faz o update em item e depois em produto, atualiza o documento produto.
     * @param id id do item
     * @param request parametros do item que vai ser editado.
     * @return retorna o item editado
     */
    @Override
    public ResponseItem edit(int id, ItemsRequestParams request) {
        mongoTemplate.updateFirst(Query.query(Criteria.where("idItem").is(id)),
                Update.update("descricaoItem", request.getDescricaoItem()).set("codBarra", request.getCodBarra()).set("cor", request.getCor())
                        .set("especificacao", request.getEspecificacao()), Item.class, "item");

        Item itemUpdate = mongoTemplate.findOne(Query.query(Criteria.where("idItem").is(id)), Item.class, "item");

        Produto produtoUpdate = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(itemUpdate.get_idProduto())), Produto.class, "produto");
        produtoUpdate.setDepartamento(request.getDepartamento());
        produtoUpdate.setLinha(request.getLinha());
        produtoUpdate.setFamilia(request.getFamilia());
        produtoUpdate.setGrupo(request.getGrupo());
        produtoUpdate.setFornecedor(request.getFornecedor());
        produtoUpdate.setModelo(request.getModelo());
        produtoUpdate.setTipoProduto(request.getTipoProduto());
        produtoUpdate.setUnidadeMedida(request.getUnidadeMedida());
        produtoUpdate.getItems().forEach(item -> {
            if(item.getIdItem() == request.getIdItem()){
                item.setDescricaoItem(request.getDescricaoItem());
                item.setCodBarra(request.getCodBarra());
                item.setCor(request.getCor());
                item.setEspecificacao(request.getEspecificacao());
            }
        });

        mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is(itemUpdate.get_idProduto())),
                new Update().set("departamento", produtoUpdate.getDepartamento()).set("linha", produtoUpdate.getLinha()).set("familia", produtoUpdate.getFamilia())
                        .set("grupo", produtoUpdate.getGrupo()).set("fornecedor", produtoUpdate.getFornecedor()).set("modelo", produtoUpdate.getModelo())
                        .set("tipoProduto", produtoUpdate.getTipoProduto()).set("unidadeMedida", produtoUpdate.getUnidadeMedida()).set("items", produtoUpdate.getItems()),
                Produto.class, "produto");

        return createResponseItemByProduto(produtoUpdate);
    }

    @Override
    public List<ProdutoCstIcmsResponse> getCstIcms(String request) {
        List<ProdutoCstIcmsResponse> responses = new ArrayList<>();
        List<CstIcms> cstIcms = mongoTemplate.find(Query.query(Criteria.where("origem").is(request)), CstIcms.class, "csticms");
        cstIcms.forEach(item -> responses.add(createCstIcmsResponse(item)));
        return responses;
    }

    @Override
    public List<ProdutoNcm> getNcm(String request) {
        List<ProdutoNcm> response = new ArrayList<>();
        List<Ncm> ncms;
        ncms = mongoTemplate.find(new Query().addCriteria(Criteria.where("Descricao").regex(request)), Ncm.class, "ncm");
        if(ncms.isEmpty()){
            ncms = mongoTemplate.find(new Query().addCriteria(Criteria.where("Codigo").regex(request)), Ncm.class, "ncm");
        }
        ncms.forEach(ncm -> response.add(responseNcm(ncm)));
        return response;
    }

    /**
     * Modulo delete
     * Recebe um id como parametro, executa um find na collection de Item, se encontrar algo, com o id do produto associado a collection Item(_idProduto)
     * executa o find na collection de Produto, e remove o item da array de items, e depois remove o item da collection de item.
     * @param id é o id de item que será deletado.
     * @return retorna o id e a messagem que foi deletado
     */
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
        newItem.setIdItem(item.getIdItem());
        newItem.setDescricaoItem(item.getDescricaoItem());
        newItem.setCodBarra(item.getCodBarra());
        newItem.setEspecificacao(item.getEspecificacao());
        newItem.setCor(item.getCor());
        return newItem;
    }

    /**
     * Essa parte faz o contrato entre as duas collection items e produtos, por isso está sendo executada de em uma transição.
     * @param item São os items que recebemos para interar no objeto items.
     * @param idProd é o id do produto da collection que será relacionado a item da collectionde Item.
     * @return retorna um novo item
     */
    @Transactional
    private Item registerItem(Item item, String idProd){
        Item newItem = new Item();
        newItem.setIdItem(item.getIdItem());
        newItem.set_idProduto(idProd);
        newItem.setDescricaoItem(item.getDescricaoItem());
        newItem.setCodBarra(item.getCodBarra());
        newItem.setEspecificacao(item.getEspecificacao());
        newItem.setCor(item.getCor());
        return newItem;
    }

    /**
     * Código item é o idItem
     * Response que é entregue ao usuario, aqui é manipulado e retirada alguma informações que o usuario não precisa.
     * @param item é o item que vai ser apresentado para o usuario
     * @return retorna um item novo
     */
    private ResponseItem createResponseItem(Item item){
        ResponseItem items = new ResponseItem();
        Produto produto = mongoTemplate.findById(item.get_idProduto(), Produto.class, "produto");
        items.setCodigoItem(item.getIdItem());
        items.setCodBarra(item.getCodBarra());
        items.setDescricaoItem(item.getDescricaoItem());
        items.setDepartamento(produto.getDepartamento());
        items.setLinha(produto.getLinha());
        items.setFamilia(produto.getFamilia());
        items.setGrupo(produto.getGrupo());
        items.setFornecedor(produto.getFornecedor());
        items.setModelo(produto.getModelo());
        items.setTipoProduto(produto.getTipoProduto());
        items.setUnidadeMedida(produto.getUnidadeMedida());
        if(produto.isProcessado()){
            items.setProcessado("SIM");
        }else{
            items.setProcessado("NÃO");
        }
        items.setCor(item.getCor());
        items.setEspecificacao(item.getEspecificacao());
        return items;
    }

    /**
     * Cria o response que o usuario precisa, mas com produto como parametro
     * @param produto é o produto de onde vai sair a extração de informações necessarias para criar o response.
     * @return retona o item.
     */
    private ResponseItem createResponseItemByProduto(Produto produto){
        ResponseItem items = new ResponseItem();
        produto.getItems().forEach(item ->
        {   items.setCodigoItem(item.getIdItem());
            items.setDescricaoItem(item.getDescricaoItem());
            items.setCodBarra(item.getCodBarra());
            items.setCor(item.getCor());
            items.setEspecificacao(item.getEspecificacao());
        });
        items.setDepartamento(produto.getDepartamento());
        items.setLinha(produto.getLinha());
        items.setFamilia(produto.getFamilia());
        items.setGrupo(produto.getGrupo());
        items.setFornecedor(produto.getFornecedor());
        items.setModelo(produto.getModelo());
        items.setTipoProduto(produto.getTipoProduto());
        items.setUnidadeMedida(produto.getUnidadeMedida());
        if(produto.isProcessado()){
            items.setProcessado("SIM");
        }else{
            items.setProcessado("NÃO");
        }
        return items;
    }

    /**
     * Cria o response quando é salvo o produto
     * @param produto rebece um objeto do tipo Produto
     * @return retorna esse produto como response.
     */
    private ProdutoResponse createResponseSave(Produto produto){
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
        if(produto.isProcessado()){
            response.setProcessado("SIM");
        }else{
            response.setProcessado("NÃO");
        }
        response.setCstIcmsOrigem(produto.getCstIcmsOrigem());
        response.setCstIcmsCodigo(produto.getCstIcmsCodigo());
        response.setCstIcmsDescricao(produto.getCstIcmsDescricao());
        response.setCstPisConfins(produto.getCstPisConfins());
        response.setNcmCodigo(produto.getNcmCodigo());
        response.setNcmDescricao(produto.getNcmDescricao());
        response.setItems(produto.getItems());
        return response;
    }

    private ProdutoCstIcmsResponse createCstIcmsResponse(CstIcms cstIcms){
        ProdutoCstIcmsResponse response = new ProdutoCstIcmsResponse();
        String newDesc = cstIcms.getCodigo() + " - " +cstIcms.getDescricao();
        response.setDescricao(newDesc);
        return response;
    }

    /**
     * Verifcação de na Collection Produto se existe um produto igual o da requisição.
     * @param request rebece o um objeto do tipo ProdutoRequest.
     * @return retorna um booleano se existe na class ou não
     */
    private Boolean duplicatedProd (ProdutoRequest request){
        Produto produtos = mongoTemplate.findOne(Query.query(Criteria.where("descricaoProduto").is(request.getDescricaoProduto())
                .and("departamento").is(request.getDepartamento()).and("linha").is(request.getLinha()).and("familia").is(request.getFamilia())
                .and("grupo").is(request.getGrupo()).and("fornecedor").is(request.getFornecedor()).and("modelo").is(request.getModelo())
                .and("tipoProduto").is(request.getTipoProduto()).and("unidadeMedida").is(request.getUnidadeMedida())),
                Produto.class, "produto");

        return produtos != null;
    }

    private ProdutoNcm responseNcm(Ncm ncm){
        ProdutoNcm response = new ProdutoNcm();
        response.setCodigo(ncm.getCodigo());
        response.setDescricao(ncm.getDescricao());
        return response;
    }

    /**
     * Verificação na collection de Item, verifica se existe item duplicado.
     * @param item Recebe um objeto do tipo Item
     * @return booleano se existe ou não
     */
    private Boolean duplicatedItem (Item item){
        Item items = mongoTemplate.findOne(Query.query(Criteria.where("descricaoItem").is(item.getDescricaoItem()).and("codBarra").is(item.getCodBarra())),
                Item.class, "item");

        return items != null;
    }

    /**
     * Gerador de idProduto, verfica qual o ultimo id que foi cadastrado, se não tiver id ele gera o como 1
     * @return retorna um int id
     */
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

    /**
     * Gerdaor de idItem, verfica qual o ultimo id que foi cadastrado, se não tiver id ele gera o como 1
     * @return retorna um int id
     */
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
