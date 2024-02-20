package br.com.manu.service.tipoProduto;

import br.com.manu.model.tipoProduto.TipoProdutoRequest;
import br.com.manu.persistence.entity.tipoProduto.TipoProduto;
import br.com.manu.persistence.repository.tipoProduto.TipoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoProdutoServicoImp implements TipoProdutoService{
    @Autowired
    TipoProdutoRepository repository;
    @Override
    public String create(TipoProdutoRequest request) {
        TipoProduto produto = new TipoProduto();
        produto.setAtivoMobiliario(request.getAtivoMobiliario());
        produto.setItemVenda(request.getItemVenda());
        produto.setMateriaPrima(request.getMateriaPrima());
        produto.setUsoConsumo(request.getUsoConsumo());
        repository.save(produto);
        return "realizado com sucesso";
    }
}
