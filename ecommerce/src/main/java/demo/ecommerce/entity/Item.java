package demo.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long item_id;

    private String item_name;

    private Double item_price;

    @JsonIgnore
    @ManyToMany
    private List<Cart> carts;
    public Item() {
    }

    public Item(String item_name, Double item_price) {
        this.item_name = item_name;
        this.item_price = item_price;
    }

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Double getItem_price() {
        return item_price;
    }

    public void setItem_price(Double item_price) {
        this.item_price = item_price;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> cart) {
        this.carts = cart;
    }

    public void addCart(Cart cart) {
        this.carts.add(cart);
    }

    public void removeCart(Cart cart) {
        this.carts.remove(cart);
    }

    @Override
    public String toString() {
        return "Item{" +
                "item_id=" + item_id +
                ", item_name='" + item_name + '\'' +
                ", item_price=" + item_price +
                '}';
    }
}
