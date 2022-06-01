package day03.exercises;

public class Car {
  /* Private variables and properties */
  private String colour;
  private String make;
  private Integer engineCapacity;
  private Boolean started = false;
  private long startedSince;

  public Car (){
    System.out.println("*** This is a default constructor. Initiating a new object");
    this.colour = "red";
  }

  /* Methods */
  public void start(){
    if (this.started){
      System.out.println("The car is running");
    } else{
      System.out.println("Vroom");
      this.started = true;
      this.startedSince = System.currentTimeMillis();
    }
  }

  public void stop(){
    if (this.started){
      this.started = false;
      System.out.println("Turning off car");
    } else{
      System.out.println("Car has already been turned off");
    }
  }

  public long getDrivingDuration(){
    if (isStarted()){
       return (System.currentTimeMillis() - this.startedSince) / 1000 ;
    } else{
      return -1L;
    }

  }

  /* Getters and Setters */
  public String getMake() {
    return make;
  }
  public Boolean isStarted() {
    return started;
  }
  public void setStarted(Boolean started) {
    this.started = started;
  }
  public Integer getEngineCapacity() {
    return engineCapacity;
  }
  public void setEngineCapacity(Integer engineCapacity) {
    this.engineCapacity = engineCapacity;
  }
  public String getColour() {
    return colour;
  }
  public void setColour(String colour) {
    this.colour = colour;
  }
  public void setMake(String make) {
    this.make = make;
  }

}
