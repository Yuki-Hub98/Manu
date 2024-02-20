package br.com.manu.persistence.repository.unidadeMedida;

import br.com.manu.persistence.entity.unidadeMedida.UnidadeMedida;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UnidadeMedidaRepository extends MongoRepository<UnidadeMedida, String> {
}
