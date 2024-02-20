package br.com.manu.persistence.repository.tipoProduto;

import br.com.manu.persistence.entity.tipoProduto.TipoProduto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TipoProdutoRepository extends MongoRepository<TipoProduto, String> {
}
