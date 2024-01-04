package demo.ecommerce.service;

import demo.ecommerce.dto.CartDTO;
import demo.ecommerce.dto.ItemDTO;
import demo.ecommerce.dto.PersonDTO;
import demo.ecommerce.entity.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EcommerceService {
    ResponseEntity<PersonDTO> addUser(PersonDTO personDTO) ;

    ResponseEntity<PersonDTO> isUser(String user_name) ;

    ResponseEntity<ItemDTO> addItemToCart(Long user_id, Long item_id) ;

    ResponseEntity<String> removeItemFromCart(Long cart_id, Long item_id) ;

    ResponseEntity<List<ItemDTO>> getItems();

    ResponseEntity<List<ItemDTO>> getPersonCartItems(Long user_id);
}
