package by.ttre16.briana.entity;

import by.ttre16.briana.entity.base.AbstractDescribedEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@NamedQueries({
    @NamedQuery(
        name = Product.GET_BY_ID,
        query = "select p from Product p " +
                "where p.id =: id and p.organization.id =: organizationId"
    ),
    @NamedQuery(
        name = Product.GET_ALL,
        query = "select p from Product p " +
                "where p.organization.id =: organizationId"
    ),
    @NamedQuery(
        name = Product.GET_BY_CATEGORY_ID,
        query = "select p from Product p " +
                "where p.organization.id =: organizationId " +
                "and p.category.id =: categoryId"
    ),
    @NamedQuery(
            name = Product.DELETE_BY_ID,
            query = "delete from Product p " +
                    "where p.id =: id and p.organization.id =: organizationId"
    )
})
@Entity
@Getter
@Setter
@Table(name = "products")
@ToString
public class Product extends AbstractDescribedEntity {
    public static final String GET_BY_ID = "product:getById";

    public static final String GET_ALL = "product:getAll";

    public static final String GET_BY_CATEGORY_ID = "product:getByCategoryId";

    public static final String DELETE_BY_ID = "product:deleteById";

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "image_path")
    private String imagePath;
}
