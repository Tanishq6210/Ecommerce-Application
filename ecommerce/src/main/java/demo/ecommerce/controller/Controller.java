package demo.ecommerce.controller;

import demo.ecommerce.dto.ItemDTO;
import demo.ecommerce.dto.PersonDTO;
import demo.ecommerce.entity.Item;
import demo.ecommerce.service.serviceimpl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("ecommerce")
public class Controller {

    private ServiceImpl service;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public Controller(ServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<PersonDTO> savePerson(@RequestBody PersonDTO personDTO) {
        return service.addUser(personDTO);
    }

    @GetMapping("/login")
    public ResponseEntity<PersonDTO> isUser(@RequestParam String user_name) {
        return service.isUser(user_name);
    }

    @GetMapping("/users/{user_id}/items")
    public ResponseEntity<List<ItemDTO>> getPersonItems(@PathVariable Long user_id) {
        return service.getPersonCartItems(user_id);
    }

    @PostMapping("/users/{user_id}/items/{item_id}/save")
    public ResponseEntity<ItemDTO> savePersonItem(@PathVariable Long user_id, @PathVariable Long item_id) {
        logger.info("UserID: {} and ItemID: {}", user_id, item_id);
        return service.addItemToCart(user_id, item_id);
    }

    @DeleteMapping("/users/{user_id}/items/{item_id}/remove")
    public ResponseEntity<String> removePersonItem(@PathVariable Long user_id, @PathVariable Long item_id) {
        return service.removeItemFromCart(user_id, item_id);
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        return service.getItems();
    }
}
