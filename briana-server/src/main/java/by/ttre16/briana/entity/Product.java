package by.ttre16.briana.entity;

import by.ttre16.briana.entity.base.AbstractDescribedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product extends AbstractDescribedEntity {
    @Column(name = "price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "image_path")
    private String imagePath;
}
