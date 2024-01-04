package demo.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import demo.ecommerce.entity.Item;
import demo.ecommerce.entity.Person;
import jakarta.persistence.OneToMany;

import java.util.List;

public class CartDTO {
    private Long cart_id;

    @JsonIgnore
    private Person user;

    private List<Item> items;

    public CartDTO(Long cart_id) {
        this.cart_id = cart_id;
    }

    public Long getCart_id() {
        return cart_id;
    }

    public void setCart_id(Long cart_id) {
        this.cart_id = cart_id;
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "cart_id=" + cart_id +
                '}';
    }
}
