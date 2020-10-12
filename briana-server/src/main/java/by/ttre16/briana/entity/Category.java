package by.ttre16.briana.entity;

import by.ttre16.briana.entity.base.AbstractDescribedEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "categories")
@ToString
public class Category extends AbstractDescribedEntity {
    @Column(name = "image_path")
    private String imagePath;
}
