package br.com.manu.service.unidadeMedida;

import br.com.manu.model.unidadeMedida.UnidadeMedidaRequest;
import br.com.manu.model.unidadeMedida.UnidadeMedidaResponse;
import br.com.manu.persistence.entity.unidadeMedida.UnidadeMedida;
import br.com.manu.persistence.repository.unidadeMedida.UnidadeMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnidadeMedidaServiceImp implements UnidadeMedidaService {
    @Autowired
    UnidadeMedidaRepository repository;
    @Override
    public String create(UnidadeMedidaRequest unidade) {
        UnidadeMedida uni = new UnidadeMedida();
        uni.setMetro(unidade.getMetro());
        uni.setUnidade(unidade.getUnidade());
        uni.setCaixa(unidade.getCaixa());
        uni.setMilheiro(unidade.getMilheiro());
        uni.setPacote(unidade.getPacote());
        uni.setPar(unidade.getPar());
        uni.setTonel(unidade.getTonel());
        uni.setQuilograma(unidade.getQuilograma());
        uni.setConjunto(unidade.getConjunto());
        uni.setMetroQuadrado(unidade.getMetroQuadrado());
        uni.setRolo(unidade.getRolo());
        uni.setMetroCubico(unidade.getMetroCubico());
        uni.setChapa(unidade.getChapa());
        uni.setCento(unidade.getCento());
        uni.setGalao(unidade.getGalao());
        uni.setCentimentro(unidade.getCentimentro());
        uni.setLitro(unidade.getUnidade());
        uni.setMilheiro(unidade.getMilheiro());
        repository.save(uni);
        UnidadeMedidaResponse ok = new UnidadeMedidaResponse();
        ok.setStatus("finalizado");
        return ok.getStatus();
    }

}
