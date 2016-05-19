package praktikum.versuch4;

class Mensch {
  protected String name;
  protected double size;
  protected int age;
  
  Mensch() {
  }
  
  Mensch(String name, double size, int age) {
      this.name = name;
      this.size = size;
      this.age = age;
  }
  public String getName() {
      return name;
    }
  public void setName(String name) {
      this.name = name;
  }
  public double getSize() {
      return size;
  }
  public void setSize(double size) {
      this.size = size;
  }
  public int getAge() {
      return age;
  }
  public void setAge(int age) {
      this.age = age;
  }
  String print() {
      return this.name + "\n" + this.size + "\n" + this.age;
  }
}