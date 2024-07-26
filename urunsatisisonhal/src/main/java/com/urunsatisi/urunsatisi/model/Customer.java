package com.urunsatisi.urunsatisi.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="customers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Customer  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    @NotBlank
    private String name;

   /* @Column(name = "yearOfBirth")
    private int yearOfBirth;
*/


    @OneToMany(mappedBy = "customer",
    cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    })
    private List<Cart> carts;
}