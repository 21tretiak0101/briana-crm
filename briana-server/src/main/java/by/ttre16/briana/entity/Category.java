package by.ttre16.briana.entity;

import by.ttre16.briana.entity.base.AbstractDescribedEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@NamedQueries({
    @NamedQuery(
        name = Category.GET_BY_ID,
        query = "select c from Category c " +
                "where c.id =: id and c.organization.id =: organizationId"
    )
})
@Entity
@Getter
@Setter
@Table(name = "categories")
@ToString
public class Category extends AbstractDescribedEntity {
    public static final String GET_BY_ID = "category:getById";
    @Column(name = "image_path")
    private String imagePath;
}
