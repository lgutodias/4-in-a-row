/**
 * 
 * @author Luiz Augusto Dias 2018429
 *
 */

public class Player {

	//Set player properties
	
  private String name;
  private String surname;

  private int age;
  int score;
  private String email;

  public void incrementScore() {
    score = score + 1;
  }

  //Getter and Setter
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getScore() {
    return score;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}