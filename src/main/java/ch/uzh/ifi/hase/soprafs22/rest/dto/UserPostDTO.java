package ch.uzh.ifi.hase.soprafs22.rest.dto;


import java.util.Date;

public class UserPostDTO {

  private String password;

  private String username;

  private Date creationDate;

  private Boolean status;

  private String birthday;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Date getCreationDate() { return creationDate; }

    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean logged_in) {
        this.status = status;
    }

    public String getBirthday() { return birthday;}
    public void setBirthday(String birthday) {this.birthday = birthday;}

}
