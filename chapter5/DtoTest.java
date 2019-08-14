package chapter5;
/**
 * @Author: michael
 * @Description:
 * @Date: 2019-07-27 13:05
 */

/**
 * @author : michael
 * @description:
 * @date : 2019-07-27 13:05
 */
public class DtoTest {
  private String name;
  private Integer age;

  public DtoTest(String name, Integer age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "name=" + this.name + ",age=" + this.age;
  }
}
