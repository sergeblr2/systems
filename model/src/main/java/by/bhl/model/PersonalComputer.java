package by.bhl.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.concurrent.ThreadLocalRandom;

@Entity
public class PersonalComputer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    Integer level;
    String status;

    public Long getId() {
        return id;
    }

    public PersonalComputer() {

    }

    public PersonalComputer(String name, Integer level, String status) {
        this.name = name;
        this.level = level;
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        if(level < 0) {
            level = 0;
        } else if (level > 100) {
            level = 100;
        }
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PersonalComputer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", status=" + status +
                '}';
    }
}
