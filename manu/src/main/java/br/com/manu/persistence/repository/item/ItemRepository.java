package br.com.manu.persistence.repository.item;


import br.com.manu.persistence.entity.produtos.item.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {

}
