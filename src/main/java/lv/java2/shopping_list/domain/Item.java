package lv.java2.shopping_list.domain;

import javax.persistence.*;

@Entity
@Table(schema = "java2", name = "items")
public class Item {

    @Id
    @Column(name = "ITEM_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;


    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
