package mas.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class CollectionDTO {
    private Long id;
    private String name;
    private CollectionStatus status;
    private Book cover;
    private Double finalPrice;
    private Integer itemsNumber;

    public CollectionDTO(Long id, String name, CollectionStatus status, Book cover, Double finalPrice, Integer itemsNumber) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.cover = cover;
        this.finalPrice = finalPrice;
        this.itemsNumber = itemsNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CollectionStatus getStatus() {
        return status;
    }

    public void setStatus(CollectionStatus status) {
        this.status = status;
    }

    public Book getCover() {
        return cover;
    }

    public void setCover(Book cover) {
        this.cover = cover;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Integer getItemsNumber() {
        return itemsNumber;
    }

    public void setItemsNumber(Integer itemsNumber) {
        this.itemsNumber = itemsNumber;
    }
}
