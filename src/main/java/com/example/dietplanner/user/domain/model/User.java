package com.example.dietplanner.user.domain.model;

import com.example.dietplanner.favourites.adapters.persistence.entity.FavouriteEntity;
import com.example.dietplanner.foods.adapters.persistence.entity.FoodEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        }
)
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Length(min = 3, max = 20)
    private String username;

    @NotBlank
    @Length(min = 4, max = 50)
    private String email;

    @NotBlank
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<FoodEntity> foods;

    @OneToMany(cascade = CascadeType.ALL)
    private List<FavouriteEntity> favouriteFoods;

    public User() {

    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public boolean addFood(Object object) {
        if (object.getClass() == FoodEntity.class) {
            foods.add((FoodEntity) object);
            return true;
        }
        if (object.getClass() == FavouriteEntity.class) {
            favouriteFoods.add((FavouriteEntity) object);
            return true;
        }
        return false;
    }


    public boolean deleteFood(FoodEntity foodEntity) {
        return foods.remove(foodEntity);
    }

}
