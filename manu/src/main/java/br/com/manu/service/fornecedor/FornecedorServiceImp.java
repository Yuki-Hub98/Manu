package br.com.manu.service.fornecedor;
import br.com.manu.model.fornecedor.FornecedorRequest;
import br.com.manu.model.fornecedor.FornecedorResponse;
import br.com.manu.persistence.entity.fornecedor.Fornecedor;
import br.com.manu.persistence.repository.fornecedor.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

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
        fornecedor.setIdCad(incrementId());
        fornecedor.setRazaoSocialFornecedor(request.getRazaoSocialFornecedor());
        fornecedor.setNomeFantasiaFornecedor(request.getNomeFantasiaFornecedor());
        fornecedor.setCpfCnpjFornecedor(request.getCpfCnpjFornecedor());
        fornecedor.setIeRgFornecedor(request.getIeRgFornecedor());
        fornecedor.setOrgaoEmissorFornecedor(request.getOrgaoEmissorFornecedor());
        fornecedor.setUfFornecedor(request.getUfFornecedor());
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
        fornecedor.setUfRepresentante(request.getUfRepresentante());
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
        response.setRazaoSocialFornecedor(request.getRazaoSocialFornecedor());
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
}
