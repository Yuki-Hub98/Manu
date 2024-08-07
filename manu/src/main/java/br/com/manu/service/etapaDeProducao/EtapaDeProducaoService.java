package br.com.manu.service.etapaDeProducao;

import br.com.manu.model.etapaDeProducao.EtapaDeProducaoDel;
import br.com.manu.model.etapaDeProducao.EtapaDeProducaoFichaTecnica;
import br.com.manu.model.etapaDeProducao.EtapaDeProducaoRe;

import java.util.List;

public interface EtapaDeProducaoService {

    EtapaDeProducaoRe crete(EtapaDeProducaoRe request);
    List<EtapaDeProducaoRe> getAll();

    List<EtapaDeProducaoFichaTecnica> getEtapaFichaTecnica();
    EtapaDeProducaoRe edit(int codigo, EtapaDeProducaoRe request);
    EtapaDeProducaoDel del(int codigo, EtapaDeProducaoRe request);
}
