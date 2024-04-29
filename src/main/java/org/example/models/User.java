package org.example.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,length = 50)
    private String email;

    @Column(nullable = false,length = 100)
    private String password;

    @Column(nullable = false,length = 50)
    private String firstName;

    @Column(nullable = false,length = 50)
    private String lastName;

    @Column(nullable = false,length = 50)
    private Long phone;

    @Column(nullable = false,length = 50)
    private String role;

/*    @Column(nullable = false,columnDefinition = "boolean default true")
    private Boolean enabled;*/

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt=new Date();

    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt=new Date();


    /*public boolean isUserEnabled(){
        return this.enabled;
    }*/
}
