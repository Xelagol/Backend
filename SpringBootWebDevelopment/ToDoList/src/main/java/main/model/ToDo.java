package main.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String toDoList;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setToDoList(String toDoList) {
        this.toDoList = toDoList;
    }
    public String getToDoList() {
        return toDoList;
    }

    @Override
    public String toString() {
        return "{\n" +
                "id: " + id +
                ", \nname: \"" + name + '\"' +
                ", \ntoDoList: \"" + toDoList + '\"' +
                '}';
    }
}
