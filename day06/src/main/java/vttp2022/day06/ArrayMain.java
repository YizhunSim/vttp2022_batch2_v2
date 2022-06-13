package vttp2022.day06;

public class ArrayMain {
  public static void main(String[] args){
    String[] lines = new String[10];

    float[][] mat2 = new float[3][3];

    float[][] mat = new float[3][];
    for (int i = 0; i < mat.length; i++){
      mat[i] = new float[3];
      mat[i][0] = i;
      mat[i][1] = i;
      mat[i][2] = i;
    }

  }
}
