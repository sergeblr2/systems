package by.bhl;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.concurrent.ThreadLocalRandom;

@Entity
public class PersonalComputer {

    @Id
    Long id;
    String name;
    Integer level;
    Byte status;

    public Long getId() {
        return id;
    }

    public PersonalComputer() {

    }

    public PersonalComputer(String name, Integer level, Byte status) {
        this.id = Math.abs(ThreadLocalRandom.current().nextLong());
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
        this.level = level;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
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
