package ch.uzh.ifi.hase.soprafs22.rest.dto;

import java.util.Date;

public class UserPostDTO {

  private String name;

  private String username;

  private Date creationDate;

  //private Birthdate birthdate;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Date getCreationDate() { return creationDate; }

    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    /*
    public Birthdate getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(Birthdate birthdate) {
        this.birthdate = birthdate;
    }

     */
}
