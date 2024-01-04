package demo.ecommerce.service.serviceimpl;


import demo.ecommerce.dto.CartDTO;
import demo.ecommerce.dto.ItemDTO;
import demo.ecommerce.dto.PersonDTO;
import demo.ecommerce.entity.Cart;
import demo.ecommerce.entity.Item;
import demo.ecommerce.entity.Person;
import demo.ecommerce.repository.CartRepository;
import demo.ecommerce.repository.ItemRepository;
import demo.ecommerce.repository.PersonRepository;
import demo.ecommerce.service.EcommerceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServiceImpl implements EcommerceService {

    private PersonRepository personRepository;
    private ItemRepository itemRepository;
    private CartRepository cartRepository;

    private ModelMapper modelmapper;

    public ServiceImpl(PersonRepository personRepository, ItemRepository itemRepository, CartRepository cartRepository, ModelMapper modelmapper) {
        this.personRepository = personRepository;
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
        this.modelmapper = modelmapper;
    }

    private PersonDTO convertToPersonDTO(Person person) {
        return modelmapper.map(person, PersonDTO.class);
    }

    private ItemDTO convertToItemDTO(Item item) {
        return modelmapper.map(item, ItemDTO.class);
    }

    private CartDTO conertToCartDTO(Cart cart) {
        return modelmapper.map(cart, CartDTO.class);
    }

    private Person convertToPerson(PersonDTO personDTO) {
        return modelmapper.map(personDTO, Person.class);
    }

    private Item convertToItem(ItemDTO itemDTO) {
        return modelmapper.map(itemDTO, Item.class);
    }

    private Cart convertToCart(CartDTO cartDTO) {
        return modelmapper.map(cartDTO, Cart.class);
    }
    @Override
    public ResponseEntity<PersonDTO> addUser(PersonDTO personDTO) {
        Person person = convertToPerson(personDTO);
        Person savedPerson = personRepository.save(person);

        Cart cart = new Cart();
        cart.setUser(savedPerson);
        person.setCart(cart);

        cartRepository.save(cart);
        return ResponseEntity.ok(convertToPersonDTO(person));
    }

    @Override
    public ResponseEntity<PersonDTO> isUser(String user_name) {
        Person person = personRepository.findByUserName(user_name);
        if(person == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(convertToPersonDTO(person));
    }


    @Override
    public ResponseEntity<ItemDTO> addItemToCart(Long user_id, Long item_id) {
        Optional<Person> optionalPerson = personRepository.findById(user_id);
        Person person = optionalPerson.get();
        Cart cart = person.getCart();

        Item item = itemRepository.findById(item_id).get();
        cart.addItem(item);

        Cart savedCart = cartRepository.save(cart);
        item.addCart(savedCart);

        ItemDTO savedItemDTO = convertToItemDTO(itemRepository.save(item));
        return ResponseEntity.ok(savedItemDTO);
    }

    @Override
    public ResponseEntity<String> removeItemFromCart(Long user_id, Long item_id) {
        Person person = personRepository.findById(user_id).get();
        Cart cart = person.getCart();
        Item item = itemRepository.findById(item_id).get();
        cart.removeItem(item);
        item.removeCart(cart);
        cartRepository.save(cart);
        return ResponseEntity.ok("Item Deleted");
    }

    @Override
    public ResponseEntity<List<ItemDTO>> getItems() {
        List<Item> items = itemRepository.findAll();
        return ResponseEntity.ok(items.stream().map(this::convertToItemDTO).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<List<ItemDTO>> getPersonCartItems(Long user_id) {
        Person person = personRepository.findById(user_id).get();
        List<Item> items = person.getCart().getItems();
        return ResponseEntity.ok(items.stream().map(this::convertToItemDTO).collect(Collectors.toList()));
    }
}
