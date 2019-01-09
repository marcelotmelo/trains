package ca.receptiviti.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"name"})
public class City {

    private final String name;

    public City(String name) {
        this.name = name;
    }

}
