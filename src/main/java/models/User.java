package models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class User
{
   private int id;
   private String firstName;
   private String uname;
   private Integer age;
   private String  password;



}
//TODO MAKE A DATABASE FILE FOR THIS