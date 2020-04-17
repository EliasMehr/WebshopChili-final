package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
    User entity representing the USER table in the SQL database

    @Column shows which column name is used in DB table
    @JoinColumn shows the name of the foreign key column in DB table
    @GeneratedValue(strategy =  GenerationType.IDENTITY) seems to work better with Hibernate than AUTO

    (fetch = FetchType.LAZY) means the objects will only be taken from the database when called upon
    FetchType.EAGER would mean all related data is loaded from DB whether it is used or not

    @OneToMany means one entity owns a collection of another, for example User owns List<Order> orders
    Here a bidirectional relationship seems to work better, which requires add and remove methods
    and there must be a reference in the related entity

    @ManyToOne means many of the entity type can only have one of the related entity
    For example a User may only have one Role, but many users can have the same role
    Here a unidirectional relationship is enough, because we don't need a list of users per role
    This means User has a reference to Role, but Role has no reference to User
 */

@Entity
@NamedQuery(name = "getUserByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
public class User implements Serializable {

    private static Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String address;

    private String city;

    private String phone;

    private String email;

    private String password;

    //Unidirectional relationship, Role has no reference to User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    //Bidirectional relationship, use methods addOrder and removeOrder
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL, //We may want to change this
            orphanRemoval = true
    )
    private List<Order> orders = new ArrayList<>();


    public void addOrder(Order order){
        orders.add(order);
        order.setUser(this);
    }

    public void removeOrder(Order order){
        orders.remove(order);
        order.setUser(null);
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
