package br.com.manu.service.produto;
import br.com.manu.model.arvoreProduto.departamento.DepartamentoRequest;
import br.com.manu.model.produto.*;
import br.com.manu.persistence.entity.arvoreProduto.Departamento;
import br.com.manu.persistence.entity.arvoreProduto.Linha;
import br.com.manu.persistence.entity.produtos.csticms.CstIcms;
import br.com.manu.persistence.entity.produtos.item.Item;
import br.com.manu.persistence.entity.produtos.ncm.Ncm;
import br.com.manu.persistence.entity.produtos.produto.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    private final MongoTemplate mongoTemplate;
    public ProdutoServiceImp (MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Modulo de criação de produto, é muito importante que compreenda que o response está enviando o idItem como codigo
     * Toda manipulação que receber um id é com o idItem que está trabalhando
     * Recebe um objeto produto e uma lista de items a serem adicionadas
     * @param request objeto produto e array de items
     * @return o produto e o item adicionado.
     */
    @Override
    public List<ResponseItem> create(ProdutoRequest request) {
        Produto produto = new Produto();
        List<Item> itemProd = new ArrayList<>();
        List<ResponseItem> responseItems = new ArrayList<>();
        /*
         * Verifica se o produto é duplicado, se o produto for duplicado, só vai adicionar o item que vem na array, evitando que duplique produtos,
         * mas sempre adicione itens
         */
        if (duplicatedProd(request)){
           Produto duplicateProd = mongoTemplate.findOne(Query.query(Criteria.where("descricaoProduto").is(request.getDescricaoProduto())),
                    Produto.class, "produto");
           if (duplicateProd != null) {
               request.getItems().forEach(item -> {
                   itemProd.add(generateItem(item, duplicateProd.getFornecedor()));
               });
               itemProd.forEach(
                       item -> {
                           if (duplicatedItem(item)) {
                               mongoTemplate.remove(Query.query(Criteria.where("idItem").is(item.getIdItem())), Item.class, "item");
                           }
                           Item generetedItem = registerItem(item, duplicateProd.getId());
                           mongoTemplate.save(generetedItem, "item");
                       });
               List<Item> associateItem = mongoTemplate.find(Query.query(Criteria.where("_idProduto").is(duplicateProd.getId())), Item.class, "item");
               duplicateProd.setItems(associateItem);
               mongoTemplate.updateFirst(Query.query(Criteria.where("idProduto").is(duplicateProd.getIdProduto())),
                       Update.update("items", duplicateProd.getItems()), Produto.class, "produto");
               duplicateProd.getItems().forEach(item -> {
                   String _Processado;
                   if (duplicateProd.isProcessado()){
                       _Processado = "SIM";
                   }else {
                       _Processado = "NÃO";
                   }
                   responseItems.add(new ResponseItem(item.getIdItem(),  item.getDescricaoItem(), item.getCodBarra(), duplicateProd.getDepartamento(),
                           duplicateProd.getLinha(), duplicateProd.getFamilia(), duplicateProd.getGrupo(), duplicateProd.getFornecedor(),
                           duplicateProd.getModelo(), duplicateProd.getTipoProduto(), duplicateProd.getUnidadeMedida(), item.getCor(), item.getEspecificacao(), _Processado));
               });
               return responseItems;
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
            itemProd.add(generateItem(item, prodSave.getFornecedor()));
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
        prodSave.getItems().forEach(item -> {
            String _Processado;
            if (prodSave.isProcessado()){
                _Processado = "SIM";
            }else {
                _Processado = "NÃO";
            }
            responseItems.add(new ResponseItem(item.getIdItem(),  item.getDescricaoItem(), item.getCodBarra(), prodSave.getDepartamento(),
                    prodSave.getLinha(), prodSave.getFamilia(), prodSave.getGrupo(), prodSave.getFornecedor(),
                    prodSave.getModelo(), prodSave.getTipoProduto(), prodSave.getUnidadeMedida(), item.getCor(), item.getEspecificacao(), _Processado));
        });
        return responseItems;
    }

    @Override
    public Modelo createModelo(Modelo request) {
        DuplicatedModelo(request);
        Modelo modelo = new Modelo();
        modelo.setCodigo(incrementIdModelo());
        modelo.setDescricao(request.getDescricao());
        mongoTemplate.save(modelo, "modelo");
        return modelo;
    }

    @Override
    public List<Modelo> getAllModelos() {
        return mongoTemplate.findAll(Modelo.class, "modelo");
    }

    @Override
    public Modelo editModelo(Modelo request) {
        DuplicatedModelo(request);
        Modelo newModelo = new Modelo();
        newModelo.setCodigo(request.getCodigo());
        newModelo.setDescricao(request.getDescricao());
        Modelo modeloEdited = mongoTemplate.findOne(Query.query(Criteria.where("codigo").is(request.getCodigo())), Modelo.class, "modelo");
        Boolean exists = mongoTemplate.exists(Query.query(Criteria.where("modelo").is(modeloEdited.getDescricao())), Produto.class, "produto");
        mongoTemplate.updateFirst(Query.query(Criteria.where("codigo").is(request.getCodigo())),
                Update.update("descricao", newModelo.getDescricao()), Modelo.class, "modelo");
        if(exists){
            mongoTemplate.updateMulti(Query.query(Criteria.where("modelo").is(modeloEdited.getDescricao())),
                    Update.update("modelo", newModelo.getDescricao()),
                    Produto.class, "produto");
        }
        return newModelo;
    }

    @Override
    public Modelo delModelo(Modelo request) {
        Boolean exists = mongoTemplate.exists(Query.query(Criteria.where("modelo").is(request.getDescricao())), Produto.class, "produto");
        if (exists){
            try {
                throw new DataIntegrityViolationException(request.getDescricao());
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityViolationException(request.getDescricao());
            }
        }
        Modelo del = new Modelo();
        del.setCodigo(request.getCodigo());
        del.setDescricao(request.getDescricao());
        mongoTemplate.remove(Query.query(Criteria.where("codigo").is(request.getCodigo())), Modelo.class, "modelo");
        return del;
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

    @Override
    public ProdutoResponse getProdutoToEdit(int id) {
        Item item = mongoTemplate.findOne(Query.query(Criteria.where("idItem").is(id)), Item.class, "item");
        if (item != null) {
            Produto produto = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(item.get_idProduto())), Produto.class, "produto");
            assert produto != null;
            return createResponseProduto(produto);
        }
        return null;
    }

    @Override
    public lastIdItem getLastId() {
        lastIdItem lasId = new lastIdItem();
        lasId.setLastIdItem(incrementIdItem());
        return lasId;
    }

    /**
     * Modulo de edit
     * Faz o update do produto, recebe o produto junto com array faz o find pelo id do produto e atualiza ele.
     * @param id id do Produto
     * @param request Produto e array de itens, edita todas as informações
     * @return retorna o produto editado
     */
    @Override
    public List<ResponseItem> edit(int id, ProdutoRequest request) {
        List<Item> itemUpdate = new ArrayList<>();
        List<ResponseItem> responseItems = new ArrayList<>();
        Produto produtoUpdate = mongoTemplate.findOne(Query.query(Criteria.where("idProduto").is(id)
                .and("fornecedor").is(request.getFornecedor())), Produto.class, "produto");

        assert produtoUpdate != null;
        produtoUpdate.setDescricaoProduto(request.getDescricaoProduto());
        produtoUpdate.setDepartamento(request.getDepartamento());
        produtoUpdate.setLinha(request.getLinha());
        produtoUpdate.setFamilia(request.getFamilia());
        produtoUpdate.setGrupo(request.getGrupo());
        produtoUpdate.setFornecedor(request.getFornecedor());
        produtoUpdate.setModelo(request.getModelo());
        produtoUpdate.setTipoProduto(request.getTipoProduto());
        produtoUpdate.setUnidadeMedida(request.getUnidadeMedida());
        request.getItems().forEach(item -> {
            itemUpdate.add(new Item(item.getIdItem(), item.getDescricaoItem(), item.getCodBarra(), item.getCor(),
                    item.getEspecificacao(), produtoUpdate.getFornecedor()));
        });
        produtoUpdate.setItems(itemUpdate);
        mongoTemplate.updateFirst(Query.query(Criteria.where("idProduto").is(id)
                        .and("fornecedor").is(request.getFornecedor())),
                new Update().set("descricaoProduto", produtoUpdate.getDescricaoProduto()).set("departamento", produtoUpdate.getDepartamento()).set("linha", produtoUpdate.getLinha()).set("familia", produtoUpdate.getFamilia())
                        .set("grupo", produtoUpdate.getGrupo()).set("fornecedor", produtoUpdate.getFornecedor()).set("modelo", produtoUpdate.getModelo())
                        .set("tipoProduto", produtoUpdate.getTipoProduto()).set("unidadeMedida", produtoUpdate.getUnidadeMedida()).set("items", produtoUpdate.getItems()),
                    Produto.class, "produto");

        itemUpdate.forEach(item -> {
            mongoTemplate.updateFirst(Query.query( new Criteria("idItem").is(item.getIdItem())),
                    Update.update("descricaoItem", item.getDescricaoItem()).set("cor", item.getCor())
                            .set("especificacao", item.getEspecificacao()).set("fornecedor", item.getFornecedor()), Item.class, "item");
        });

        produtoUpdate.getItems().forEach(item -> {
            String _Processado;
            if (produtoUpdate.isProcessado()){
                _Processado = "SIM";
            }else {
                _Processado = "NÃO";
            }
            responseItems.add(new ResponseItem(item.getIdItem(),  item.getDescricaoItem(), item.getCodBarra(), produtoUpdate.getDepartamento(),
                    produtoUpdate.getLinha(), produtoUpdate.getFamilia(), produtoUpdate.getGrupo(), produtoUpdate.getFornecedor(),
                    produtoUpdate.getModelo(), produtoUpdate.getTipoProduto(), produtoUpdate.getUnidadeMedida(), item.getCor(), item.getEspecificacao(), _Processado));
        });
        return responseItems;
    }

    @Override
    public ProdutoResponse getProduto(String descricaoProduto, String fornecedor) {
        Produto produto = mongoTemplate.findOne(Query.query(Criteria.where("descricaoProduto").is(descricaoProduto)
                .and("fornecedor").is(fornecedor)), Produto.class, "produto");
        if (produto != null) {
            return createResponseProduto(produto);
        }
        return null;
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
    public ResponseItem del(int id, ResponseItem item) {
        Item items = mongoTemplate.findOne(Query.query(Criteria.where("idItem").is(id)), Item.class, "item");
        if(items != null){
            Produto produto = mongoTemplate.findById(items.get_idProduto(), Produto.class, "produto");
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
        return item;
    }

    private Item generateItem(ItemModel item, String fornecedor){
        Item newItem = new Item();
        newItem.setIdItem(item.getIdItem());
        newItem.setDescricaoItem(item.getDescricaoItem());
        newItem.setCodBarra(item.getCodBarra());
        newItem.setEspecificacao(item.getEspecificacao());
        newItem.setCor(item.getCor());
        newItem.setFornecedor(fornecedor);
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
        newItem.setFornecedor(item.getFornecedor());
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
        items.setCodigo(item.getIdItem());
        items.setCodBarra(item.getCodBarra());
        items.setDescricaoItem(item.getDescricaoItem());
        if (produto != null) {
            items.setDepartamento(produto.getDepartamento());
            items.setLinha(produto.getLinha());
            items.setFamilia(produto.getFamilia());
            items.setGrupo(produto.getGrupo());
            items.setFornecedor(produto.getFornecedor());
            items.setModelo(produto.getModelo());
            items.setTipoProduto(produto.getTipoProduto());
            items.setUnidadeMedida(produto.getUnidadeMedida());
            if (produto.isProcessado()) {
                items.setProcessado("SIM");
            } else {
                items.setProcessado("NÃO");
            }
            items.setCor(item.getCor());
            items.setEspecificacao(item.getEspecificacao());
            return items;
        }
        return null;
    }

    /**
     * Cria o response que o usuario precisa, mas com produto como parametro
     * @param produto é o produto de onde vai sair a extração de informações necessarias para criar o response.
     * @return retona o item.
     */
    private ResponseItem createResponseItemByProduto(Produto produto){
        ResponseItem items = new ResponseItem();
        produto.getItems().forEach(item ->
        {   items.setCodigo(item.getIdItem());
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
    private ProdutoResponse createResponseProduto(Produto produto){
        ProdutoResponse response = new ProdutoResponse();
        List<ItemModel> itemModel = new ArrayList<>();
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
        response.setProcessado(produto.isProcessado());
        response.setCstIcmsOrigem(produto.getCstIcmsOrigem());
        response.setCstIcmsCodigo(produto.getCstIcmsCodigo());
        response.setCstIcmsDescricao(produto.getCstIcmsDescricao());
        response.setCstPisConfins(produto.getCstPisConfins());
        response.setNcmCodigo(produto.getNcmCodigo());
        response.setNcmDescricao(produto.getNcmDescricao());
        produto.getItems().forEach(item -> {
            int id = (int) item.getIdItem();
            itemModel.add(new ItemModel(id, item.getDescricaoItem(), item.getCodBarra(), item.getCor(), item.getEspecificacao()));
        });
        response.setItems(itemModel);
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
        Item items = mongoTemplate.findOne(Query.query(Criteria.where("descricaoItem").is(item.getDescricaoItem())
                        .and("codBarra").is(item.getCodBarra()).and("fornecedor").is(item.getFornecedor())),
                Item.class, "item");

        return items != null;
    }

    private void DuplicatedModelo (Modelo request){
        Boolean exist = mongoTemplate.exists(Query.query(Criteria.where("descricao").is(request.getDescricao())),
                Modelo.class, "modelo");
        if(exist) {
            try {
                throw new InvalidRelationIdException();
            } catch (InvalidRelationIdException e) {
                throw new RuntimeException(e);
            }
        }
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

    /**
     * Gerador de codigo, verfica qual o ultimo id que foi cadastrado, se não tiver id ele gera o como 1
     * @return retorna um int id
     */
    private int incrementIdModelo () {
        int id = 0;
        List<Modelo> itemIds =  mongoTemplate.findAll(Modelo.class, "modelo");
        if(itemIds.isEmpty()){
            id ++;
        }else {
            Modelo lastId = mongoTemplate.findOne(new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "_id")),
                    Modelo.class, "modelo");
            assert lastId != null;
            id = (int) (lastId.getCodigo()) + 1;
        }
        return id;
    }
}
