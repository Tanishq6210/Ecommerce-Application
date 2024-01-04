package demo.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import demo.ecommerce.entity.Cart;

public class ItemDTO {
    private Long item_id;
    private String item_name;

    private Double item_price;

    @JsonIgnore
    private Cart cart;
    public ItemDTO() {
    }

    public ItemDTO(Long item_id, String item_name, Double item_price) {
        this.item_id = item_id;
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

    @Override
    public String toString() {
        return "ItemDTO{" +
                "item_id=" + item_id +
                ", item_name='" + item_name + '\'' +
                ", item_price=" + item_price +
                '}';
    }
}
