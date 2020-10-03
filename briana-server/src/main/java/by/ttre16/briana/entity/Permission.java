package by.ttre16.briana.entity;

import by.ttre16.briana.entity.base.AbstractNamedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "permissions")
public class Permission extends AbstractNamedEntity { }
