package com.javatpoint.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;


@Entity
public class Category {
    @Id
    @GeneratedValue
    private long id;
    private String nameCategory;
    private String icon;


    @OneToMany (mappedBy = "category") //on one category there are many posts
    @JsonIgnore
    private List<Post> post;

    public Category(long id, String nameCategory, String icon) {
        this.id = id;
        this.nameCategory = nameCategory;
        this.icon = icon;
    }

    public Category() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


}

