package day03.exercises;

public class Main {
  public static void main(String[] args){
    Car myCar = new Car();
    myCar.setColour("red");
    myCar.setMake("toyata");
    myCar.setEngineCapacity(2000);

    myCar.start();

    Car anotherCar = new Car();
    anotherCar.setColour("silver");
    anotherCar.setMake("mercedes");
    anotherCar.setEngineCapacity(3000);

    anotherCar.stop();
  }

}
