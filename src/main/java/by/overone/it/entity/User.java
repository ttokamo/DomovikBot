package by.overone.it.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    private String id;

    @Column(unique = true)
    @Setter
    private String username;

    @Setter
    private String name;

    @Setter
    private String secondName;

    @Setter
    private String street;

    @Setter
    private String house;

    @Setter
    private String porchNumber;

    @Setter
    private String floor;

    @Setter
    private String flat;

    @Setter
    private String telephoneNumber;

    @Setter
    private String carNumber;
}
