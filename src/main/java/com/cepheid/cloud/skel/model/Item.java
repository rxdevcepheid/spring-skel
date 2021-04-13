package com.cepheid.cloud.skel.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Item extends AbstractEntity {

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private ItemState state;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "desc_id", referencedColumnName = "mid")
    private Set<Description> descriptions;

    public Item() {}
    public Item(String name, ItemState state, Set<Description> descriptions) {
        this.name = name;
        this.state = state;
        this.descriptions = descriptions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemState getState() {
        return state;
    }

    public void setState(ItemState state) {
        this.state = state;
    }

    public Set<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Set<Description> descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name) && state == item.state && Objects.equals(descriptions, item.descriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, state, descriptions);
    }

    @Override
    public String toString() {
        return "Item{" +
                "mId=" + mId +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", descriptions=" + descriptions +
                '}';
    }
}
