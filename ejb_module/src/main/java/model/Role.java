 package model;

import javax.persistence.*;
import java.io.Serializable;


 @Entity
 public class Role implements Serializable {

     private static Long serialVersionUID = 1L;

     @Id
     @GeneratedValue(strategy =  GenerationType.IDENTITY)
     @Column(name = "role_id")
     private Long id;

     @Enumerated(EnumType.STRING)
     private Type type;

     @Column(name = "discount_multiplier")
     private double discountMultiplier;




     public Type getType() {
         return type;
     }

     public void setType(Type type) {
         this.type = type;
     }

     public double getDiscountMultiplier() {
         return discountMultiplier;
     }


     public void setId(Long id) {
         this.id = id;
     }

     //Inner class (enum) for tracking role
     public enum Type {
         REGULAR_USER,
         PREMIUM_USER,
         ADMIN_USER
     }
 }