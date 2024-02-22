package br.com.manu.service.fornecedor;
import br.com.manu.model.fornecedor.FornecedorDel;
import br.com.manu.model.fornecedor.FornecedorRequest;
import br.com.manu.model.fornecedor.FornecedorResponse;
import br.com.manu.persistence.entity.fornecedor.Fornecedor;
import br.com.manu.persistence.repository.fornecedor.FornecedorRepository;
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
public class FornecedorServiceImp implements FornecedorService {
    @Autowired
    private FornecedorRepository repository;
    private final MongoTemplate mongoTemplate;

    public FornecedorServiceImp (MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public FornecedorResponse create(FornecedorRequest request) {
        Fornecedor fornecedor = new Fornecedor();
        if (exist(request.getCpfCnpjFornecedor())){
            try {
                throw new InvalidRelationIdException();
            } catch (InvalidRelationIdException e) {
                throw new RuntimeException(e);
            }
        }
        fornecedor.setIdCad(incrementId());
        fornecedor = createFornecedor(fornecedor.getIdCad(), request);

        repository.save(fornecedor);
        return createResponse(fornecedor);
    }

    @Override
    public List<FornecedorResponse> getAll() {
        List<FornecedorResponse> fornecedorResponses = new ArrayList<>();
        List<Fornecedor> fornecedors = repository.findAll();
        if(!fornecedors.isEmpty()) {
          fornecedors.forEach(fornecedor -> fornecedorResponses.add(createResponse(fornecedor)));
        }
        return  fornecedorResponses;
    }

    @Override
    public List<FornecedorResponse> getParams(String requestName, String requestRazao, String requestCpfCnpj){
        List<FornecedorResponse> fornecedorResponses = new ArrayList<>();
        if(requestName.isEmpty() || requestCpfCnpj.isEmpty() || requestRazao.isEmpty()){
            List<Fornecedor> fornecedors = mongoTemplate.find(Query.query(
                    new Criteria().orOperator(Criteria.where("nomeFantasiaFornecedor").is(requestName),
                            Criteria.where("razaoSocialFornecedor").is(requestRazao), Criteria.where("cpfCnpjFornecedor").is(requestCpfCnpj))),
                    Fornecedor.class, "fornecedor");
            if(!fornecedors.isEmpty()){
                fornecedors.forEach(fornecedor -> fornecedorResponses.add(createResponse(fornecedor)));
            }
        }else {
            List<Fornecedor> fornecedors = mongoTemplate.find(Query.query(Criteria.where("nomeFantasiaFornecedor")
                            .is(requestName).and("razaoSocialFornecedor").is(requestRazao).and("cpfCnpjFornecedor").is(requestCpfCnpj)),
                    Fornecedor.class, "fornecedor");
            if(!fornecedors.isEmpty()){
                fornecedors.forEach(fornecedor -> fornecedorResponses.add(createResponse(fornecedor)));
            }
        }
        
        return fornecedorResponses;
    }

    @Override
    public FornecedorResponse edit(int id, FornecedorRequest request) {
        editTemplete(id, request);
        Fornecedor fornecedor = createFornecedor(id, request);
        return createResponse(fornecedor);
    }

    @Override
    public FornecedorDel del(int id) {
        FornecedorDel del = new FornecedorDel();
        mongoTemplate.remove(Query.query(Criteria.where("idCad").is(id)), Fornecedor.class, "fornecedor");
        String id_ = String.valueOf(id);
        del.setDel("idCad: " + id_);
        del.setMessage("Excluido com sucesso");
        return del;
    }

    private void editTemplete (int id, FornecedorRequest request){
        mongoTemplate.updateFirst(Query.query(Criteria.where("idCad").is(id)),
                Update.update("razaoSocialFornecedor", request.getRazaoSocialFornecedor()).
                        set("nomeFantasiaFornecedor", request.getNomeFantasiaFornecedor()).
                        set("cpfCnpjFornecedor", request.getCpfCnpjFornecedor()).
                        set("ieRgFornecedor", request.getIeRgFornecedor()).
                        set("orgaoEmissorFornecedor", request.getOrgaoEmissorFornecedor()).
                        set("ufRgFornecedor", request.getUfRgFornecedor()).
                        set("dataEmissaoFornecedor", request.getDataEmissaoFornecedor()).
                        set("cepFornecedor", request.getCepFornecedor()).
                        set("enderecoFornecedor", request.getEnderecoFornecedor()).
                        set("numeroFornecedor", request.getNumeroFornecedor()).
                        set("complementoFornecedor", request.getComplementoFornecedor()).
                        set("bairroFornecedor", request.getBairroFornecedor()).
                        set("cidadeFornecedor", request.getCidadeFornecedor()).
                        set("ufFornecedor", request.getUfFornecedor()).
                        set("contatoFornecedor", request.getContatoFornecedor()).
                        set("telefoneFornecedor", request.getTelefoneFornecedor()).
                        set("celularFornecedor", request.getCelularFornecedor()).
                        set("emailFornecedor", request.getEmailFornecedor()).
                        set("siteFornecedor", request.getSiteFornecedor()).
                        set("razaoSocialRepresentante", request.getRazaoSocialRepresentante()).
                        set("nomeFantasiaRepresentante", request.getNomeFantasiaRepresentante()).
                        set("cpfCnpjRepresentante", request.getCpfCnpjRepresentante()).
                        set("ieRgRepresentante", request.getIeRgRepresentante()).
                        set("orgaoEmissorRepresentante", request.getOrgaoEmissorRepresentante()).
                        set("ufRgRepresentante", request.getUfRgRepresentante()).
                        set("dataEmissaoRepresentante", request.getDataEmissaoRepresentante()).
                        set("cepRepresentante", request.getCepRepresentante()).
                        set("enderecoRepresentante", request.getEnderecoRepresentante()).
                        set("numeroRepresentante", request.getNumeroRepresentante()).
                        set("complementoRepresentante", request.getComplementoRepresentante()).
                        set("bairroRepresentante", request.getBairroRepresentante()).
                        set("cidadeRepresentante", request.getCidadeRepresentante()).
                        set("ufRepresentante", request.getUfRepresentante()).
                        set("contatoRepresentante", request.getContatoRepresentante()).
                        set("telefoneRepresentante", request.getTelefoneRepresentante()).
                        set("celularRepresentante", request.getCelularRepresentante()).
                        set("emailRepresentante", request.getEmailRepresentante()).
                        set("codBanco", request.getSiteRepresentante()).
                        set("banco", request.getSiteRepresentante()).
                        set("agencia", request.getSiteRepresentante()).
                        set("contaBanco", request.getSiteRepresentante()).
                        set("orgaoEmissorBanco", request.getOrgaoEmissorBanco()).
                        set("pix", request.getPix()),
                Fornecedor.class, "fornecedor");
    }
    private Fornecedor createFornecedor (int idCad, FornecedorRequest request){
        Fornecedor newFornecedor = new Fornecedor();
        newFornecedor.setIdCad(idCad);
        newFornecedor.setRazaoSocialFornecedor(request.getRazaoSocialFornecedor());
        newFornecedor.setNomeFantasiaFornecedor(request.getNomeFantasiaFornecedor());
        newFornecedor.setCpfCnpjFornecedor(request.getCpfCnpjFornecedor());
        newFornecedor.setIeRgFornecedor(request.getIeRgFornecedor());
        newFornecedor.setOrgaoEmissorFornecedor(request.getOrgaoEmissorFornecedor());
        newFornecedor.setUfRgFornecedor(request.getUfRgFornecedor());
        newFornecedor.setDataEmissaoFornecedor(request.getDataEmissaoFornecedor());
        newFornecedor.setCepFornecedor(request.getCepFornecedor());
        newFornecedor.setEnderecoFornecedor(request.getEnderecoFornecedor());
        newFornecedor.setNumeroFornecedor(request.getNumeroFornecedor());
        newFornecedor.setComplementoFornecedor(request.getComplementoFornecedor());
        newFornecedor.setBairroFornecedor(request.getBairroFornecedor());
        newFornecedor.setCidadeFornecedor(request.getCidadeFornecedor());
        newFornecedor.setUfFornecedor(request.getUfFornecedor());
        newFornecedor.setContatoFornecedor(request.getContatoFornecedor());
        newFornecedor.setTelefoneFornecedor(request.getTelefoneFornecedor());
        newFornecedor.setCelularFornecedor(request.getCelularFornecedor());
        newFornecedor.setEmailFornecedor(request.getEmailFornecedor());
        newFornecedor.setSiteFornecedor(request.getSiteFornecedor());
        newFornecedor.setNomeFantasiaRepresentante(request.getNomeFantasiaRepresentante());
        newFornecedor.setRazaoSocialRepresentante(request.getRazaoSocialRepresentante());
        newFornecedor.setCpfCnpjRepresentante(request.getCpfCnpjRepresentante());
        newFornecedor.setIeRgRepresentante(request.getIeRgRepresentante());
        newFornecedor.setOrgaoEmissorRepresentante(request.getOrgaoEmissorRepresentante());
        newFornecedor.setUfRgRepresentante(request.getUfRgRepresentante());
        newFornecedor.setDataEmissaoRepresentante(request.getDataEmissaoRepresentante());
        newFornecedor.setCepRepresentante(request.getCepRepresentante());
        newFornecedor.setEnderecoRepresentante(request.getEnderecoRepresentante());
        newFornecedor.setNumeroRepresentante(request.getNumeroRepresentante());
        newFornecedor.setComplementoRepresentante(request.getComplementoRepresentante());
        newFornecedor.setBairroRepresentante(request.getBairroRepresentante());
        newFornecedor.setCidadeRepresentante(request.getCidadeRepresentante());
        newFornecedor.setUfRepresentante(request.getUfRepresentante());
        newFornecedor.setContatoRepresentante(request.getContatoRepresentante());
        newFornecedor.setTelefoneRepresentante(request.getTelefoneRepresentante());
        newFornecedor.setCelularRepresentante(request.getCelularRepresentante());
        newFornecedor.setEmailRepresentante(request.getEmailRepresentante());
        newFornecedor.setSiteRepresentante(request.getSiteRepresentante());

        newFornecedor.setCodBanco(request.getCodBanco());
        newFornecedor.setBanco(request.getBanco());
        newFornecedor.setAgencia(request.getAgencia());
        newFornecedor.setContaBanco(request.getContaBanco());
        newFornecedor.setOrgaoEmissorBanco(request.getOrgaoEmissorBanco());
        newFornecedor.setPix(request.getPix());

        return newFornecedor;
    }
    private FornecedorResponse createResponse(Fornecedor fornecedor){
        FornecedorResponse response = new FornecedorResponse();
        response.setIdCad(fornecedor.getIdCad());
        response.setRazaoSocialFornecedor(fornecedor.getRazaoSocialFornecedor());
        response.setNomeFantasiaFornecedor(fornecedor.getNomeFantasiaFornecedor());
        response.setCpfCnpjFornecedor(fornecedor.getCpfCnpjFornecedor());
        response.setIeRgFornecedor(fornecedor.getIeRgFornecedor());
        response.setOrgaoEmissorFornecedor(fornecedor.getOrgaoEmissorFornecedor());
        response.setUfRgFornecedor(fornecedor.getUfRgFornecedor());
        response.setDataEmissaoFornecedor(fornecedor.getDataEmissaoFornecedor());
        response.setCepFornecedor(fornecedor.getCepFornecedor());
        response.setEnderecoFornecedor(fornecedor.getEnderecoFornecedor());
        response.setNumeroFornecedor(fornecedor.getNumeroFornecedor());
        response.setComplementoFornecedor(fornecedor.getComplementoFornecedor());
        response.setBairroFornecedor(fornecedor.getBairroFornecedor());
        response.setCidadeFornecedor(fornecedor.getCidadeFornecedor());
        response.setUfFornecedor(fornecedor.getUfFornecedor());
        response.setContatoFornecedor(fornecedor.getContatoFornecedor());
        response.setTelefoneFornecedor(fornecedor.getTelefoneFornecedor());
        response.setCelularFornecedor(fornecedor.getCelularFornecedor());
        response.setEmailFornecedor(fornecedor.getEmailFornecedor());
        response.setSiteFornecedor(fornecedor.getSiteFornecedor());
        response.setNomeFantasiaRepresentante(fornecedor.getNomeFantasiaRepresentante());
        response.setRazaoSocialRepresentante(fornecedor.getRazaoSocialRepresentante());
        response.setCpfCnpjRepresentante(fornecedor.getCpfCnpjRepresentante());
        response.setIeRgRepresentante(fornecedor.getIeRgRepresentante());
        response.setOrgaoEmissorRepresentante(fornecedor.getOrgaoEmissorRepresentante());
        response.setUfRgRepresentante(fornecedor.getUfRgRepresentante());
        response.setDataEmissaoRepresentante(fornecedor.getDataEmissaoRepresentante());
        response.setCepRepresentante(fornecedor.getCepRepresentante());
        response.setEnderecoRepresentante(fornecedor.getEnderecoRepresentante());
        response.setNumeroRepresentante(fornecedor.getNumeroRepresentante());
        response.setComplementoRepresentante(fornecedor.getComplementoRepresentante());
        response.setBairroRepresentante(fornecedor.getBairroRepresentante());
        response.setCidadeRepresentante(fornecedor.getCidadeRepresentante());
        response.setUfRepresentante(fornecedor.getUfRepresentante());
        response.setContatoRepresentante(fornecedor.getContatoRepresentante());
        response.setTelefoneRepresentante(fornecedor.getTelefoneRepresentante());
        response.setCelularRepresentante(fornecedor.getCelularRepresentante());
        response.setEmailRepresentante(fornecedor.getEmailRepresentante());
        response.setSiteRepresentante(fornecedor.getSiteRepresentante());

        response.setCodBanco(fornecedor.getCodBanco());
        response.setBanco(fornecedor.getBanco());
        response.setAgencia(fornecedor.getAgencia());
        response.setContaBanco(fornecedor.getContaBanco());
        response.setOrgaoEmissorBanco(fornecedor.getOrgaoEmissorBanco());
        response.setPix(fornecedor.getPix());

        return response;
    }

    private int incrementId () {
        int id = 0;
        List<Fornecedor> fornecedorIds =  mongoTemplate.findAll(Fornecedor.class, "fornecedor");
        if(fornecedorIds.isEmpty()){
            id ++;
        }else {
            Fornecedor lastId = mongoTemplate.findOne(new Query().limit(1).with(Sort.by(Sort.Direction.DESC, "_id")),
                    Fornecedor.class, "fornecedor");
            assert lastId != null;
            id = lastId.getIdCad() + 1;
        }
      return id;
    }

    private Boolean exist (String cpfCnpj) {
        List<Fornecedor> exi = mongoTemplate.find(Query.query(Criteria.where("cpfCnpjFornecedor").is(cpfCnpj)),
                Fornecedor.class, "fornecedor");
        return !exi.isEmpty();
    }
}
