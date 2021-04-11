package com.cepheid.cloud.skel.model;

import javassist.runtime.Desc;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Description extends AbstractEntity{

    @Column
    private String description;

    public Description(){}
    public Description(String description){
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Description that = (Description) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description);
    }

    @Override
    public String toString() {
        return "Description{" +
                "mId=" + mId +
                ", description='" + description + '\'' +
                '}';
    }
}
