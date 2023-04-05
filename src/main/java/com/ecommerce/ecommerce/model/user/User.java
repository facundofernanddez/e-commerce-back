package com.ecommerce.ecommerce.model.user;

import javax.persistence.*;
import lombok.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  private String id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String token;
  
}
