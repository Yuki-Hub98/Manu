package br.com.manu.service.fornecedor;
import br.com.manu.model.fornecedor.FornecedorRequest;
import br.com.manu.model.fornecedor.FornecedorResponse;
import br.com.manu.persistence.entity.fornecedor.Fornecedor;
import br.com.manu.persistence.repository.fornecedor.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.management.relation.InvalidRelationIdException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FornecedorServiceImp implements FornecedorService {
    @Autowired
    private FornecedorRepository repository;
    private MongoTemplate mongoTemplate;

    public FornecedorServiceImp (MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public FornecedorResponse create(FornecedorRequest request) {
        FornecedorResponse response = new FornecedorResponse();
        Fornecedor fornecedor = new Fornecedor();
        if (exist(request.getCpfCnpjFornecedor())){
            try {
                throw new InvalidRelationIdException();
            } catch (InvalidRelationIdException e) {
                throw new RuntimeException(e);
            }
        }
        fornecedor.setIdCad(incrementId());
        fornecedor.setRazaoSocialFornecedor(request.getRazaoSocialFornecedor());
        fornecedor.setNomeFantasiaFornecedor(request.getNomeFantasiaFornecedor());
        fornecedor.setCpfCnpjFornecedor(request.getCpfCnpjFornecedor());
        fornecedor.setIeRgFornecedor(request.getIeRgFornecedor());
        fornecedor.setOrgaoEmissorFornecedor(request.getOrgaoEmissorFornecedor());
        fornecedor.setUfRgFornecedor(request.getUfRgFornecedor());
        fornecedor.setDataEmissaoFornecedor(request.getDataEmissaoFornecedor());
        fornecedor.setCepFornecedor(request.getCepFornecedor());
        fornecedor.setEnderecoFornecedor(request.getEnderecoFornecedor());
        fornecedor.setNumeroFornecedor(request.getNumeroFornecedor());
        fornecedor.setComplementoFornecedor(request.getComplementoFornecedor());
        fornecedor.setBairroFornecedor(request.getBairroFornecedor());
        fornecedor.setCidadeFornecedor(request.getCidadeFornecedor());
        fornecedor.setUfFornecedor(request.getUfFornecedor());
        fornecedor.setContatoFornecedor(request.getContatoFornecedor());
        fornecedor.setTelefoneFornecedor(request.getTelefoneFornecedor());
        fornecedor.setCelularFornecedor(request.getCelularFornecedor());
        fornecedor.setEmailFornecedor(request.getEmailFornecedor());
        fornecedor.setSiteFornecedor(request.getSiteFornecedor());
        fornecedor.setNomeFantasiaRepresentante(request.getNomeFantasiaRepresentante());
        fornecedor.setRazaoSocialRepresentante(request.getRazaoSocialRepresentante());
        fornecedor.setCpfCnpjRepresentante(request.getCpfCnpjRepresentante());
        fornecedor.setIeRgRepresentante(request.getIeRgRepresentante());
        fornecedor.setOrgaoEmissorRepresentante(request.getOrgaoEmissorRepresentante());
        fornecedor.setUfRgRepresentante(request.getUfRgRepresentante());
        fornecedor.setDataEmissaoRepresentante(request.getDataEmissaoRepresentante());
        fornecedor.setCepRepresentante(request.getCepRepresentante());
        fornecedor.setEnderecoRepresentante(request.getEnderecoRepresentante());
        fornecedor.setNumeroRepresentante(request.getNumeroRepresentante());
        fornecedor.setComplementoRepresentante(request.getComplementoRepresentante());
        fornecedor.setBairroRepresentante(request.getBairroRepresentante());
        fornecedor.setCidadeRepresentante(request.getCidadeRepresentante());
        fornecedor.setUfRepresentante(request.getUfRepresentante());
        fornecedor.setContatoRepresentante(request.getContatoRepresentante());
        fornecedor.setTelefoneRepresentante(request.getTelefoneRepresentante());
        fornecedor.setCelularRepresentante(request.getCelularRepresentante());
        fornecedor.setEmailRepresentante(request.getEmailRepresentante());
        fornecedor.setSiteRepresentante(request.getSiteRepresentante());

        fornecedor.setCodBanco(request.getCodBanco());
        fornecedor.setBanco(request.getBanco());
        fornecedor.setAgencia(request.getAgencia());
        fornecedor.setContaBanco(request.getContaBanco());
        fornecedor.setOrgaoEmissorBanco(request.getOrgaoEmissorBanco());
        fornecedor.setPix(request.getPix());

        repository.save(fornecedor);
        response.setIdCad(fornecedor.getIdCad());
        response.setRazaoSocialFornecedor(fornecedor.getRazaoSocialFornecedor());
        return response;
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
    public List<FornecedorResponse> getNameCpf(String requestName, String requestRazao, String requestCpfCnpj){
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
        FornecedorResponse response = new FornecedorResponse();
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setIdCad(id);
        fornecedor = mongoTemplate.findOne(Query.query(Criteria.where("idCad").is(fornecedor.getIdCad())), Fornecedor.class, "fornecedor");
        if (fornecedor != null) {
            fornecedor.setRazaoSocialFornecedor(request.getRazaoSocialFornecedor());
            fornecedor.setNomeFantasiaFornecedor(request.getNomeFantasiaFornecedor());
            fornecedor.setCpfCnpjFornecedor(request.getCpfCnpjFornecedor());
            fornecedor.setIeRgFornecedor(request.getIeRgFornecedor());
            fornecedor.setOrgaoEmissorFornecedor(request.getOrgaoEmissorFornecedor());
            fornecedor.setUfRgFornecedor(request.getUfRgFornecedor());
            fornecedor.setDataEmissaoFornecedor(request.getDataEmissaoFornecedor());
            fornecedor.setCepFornecedor(request.getCepFornecedor());
            fornecedor.setEnderecoFornecedor(request.getEnderecoFornecedor());
            fornecedor.setNumeroFornecedor(request.getNumeroFornecedor());
            fornecedor.setComplementoFornecedor(request.getComplementoFornecedor());
            fornecedor.setBairroFornecedor(request.getBairroFornecedor());
            fornecedor.setCidadeFornecedor(request.getCidadeFornecedor());
            fornecedor.setUfFornecedor(request.getUfFornecedor());
            fornecedor.setContatoFornecedor(request.getContatoFornecedor());
            fornecedor.setTelefoneFornecedor(request.getTelefoneFornecedor());
            fornecedor.setCelularFornecedor(request.getCelularFornecedor());
            fornecedor.setEmailFornecedor(request.getEmailFornecedor());
            fornecedor.setSiteFornecedor(request.getSiteFornecedor());
            fornecedor.setNomeFantasiaRepresentante(request.getNomeFantasiaRepresentante());
            fornecedor.setRazaoSocialRepresentante(request.getRazaoSocialRepresentante());
            fornecedor.setCpfCnpjRepresentante(request.getCpfCnpjRepresentante());
            fornecedor.setIeRgRepresentante(request.getIeRgRepresentante());
            fornecedor.setOrgaoEmissorRepresentante(request.getOrgaoEmissorRepresentante());
            fornecedor.setUfRgRepresentante(request.getUfRgRepresentante());
            fornecedor.setDataEmissaoRepresentante(request.getDataEmissaoRepresentante());
            fornecedor.setCepRepresentante(request.getCepRepresentante());
            fornecedor.setEnderecoRepresentante(request.getEnderecoRepresentante());
            fornecedor.setNumeroRepresentante(request.getNumeroRepresentante());
            fornecedor.setComplementoRepresentante(request.getComplementoRepresentante());
            fornecedor.setBairroRepresentante(request.getBairroRepresentante());
            fornecedor.setCidadeRepresentante(request.getCidadeRepresentante());
            fornecedor.setUfRepresentante(request.getUfRepresentante());
            fornecedor.setContatoRepresentante(request.getContatoRepresentante());
            fornecedor.setTelefoneRepresentante(request.getTelefoneRepresentante());
            fornecedor.setCelularRepresentante(request.getCelularRepresentante());
            fornecedor.setEmailRepresentante(request.getEmailRepresentante());
            fornecedor.setSiteRepresentante(request.getSiteRepresentante());

            fornecedor.setCodBanco(request.getCodBanco());
            fornecedor.setBanco(request.getBanco());
            fornecedor.setAgencia(request.getAgencia());
            fornecedor.setContaBanco(request.getContaBanco());
            fornecedor.setOrgaoEmissorBanco(request.getOrgaoEmissorBanco());
            fornecedor.setPix(request.getPix());

            repository.save(fornecedor);
        }


        return null;
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
