package ch.uzh.ifi.hase.soprafs22.rest.dto;


import java.util.Date;

public class UserGetDTO {

  private Long id;
  private String password;
  private String username;
  private Boolean status;
  private Date creationDate;
  private String birthday;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPassword() { return password; }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Boolean getStatus() {
    return status;
  }

    public void setStatus(Boolean status) {
        this.status = status;
    }

  public Date getCreationDate() {return creationDate;}

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    public String getBirthday() { return birthday; }

    public void setBirthday(String birthday) { this.birthday = birthday; }

}
