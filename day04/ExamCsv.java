package day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamCsv {
  private static final String COMMA_DELIMITER = ",";

  public static void main(String[] args){
    List<List<String>> records = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader("exams.csv"))) {
    String line;

    while ((line = br.readLine()) != null) {
        String[] values = line.split(COMMA_DELIMITER);
        records.add(Arrays.asList(values));
    }

    Map<String, Float> examMapScoring = new HashMap<>();
    examMapScoring.put("group A - math score", 0f);
    examMapScoring.put("group A - reading score", 0f);
    examMapScoring.put("group A - writing score", 0f);

    examMapScoring.put("group B - math score", 0f);
    examMapScoring.put("group B - reading score", 0f);
    examMapScoring.put("group B - writing score", 0f);

    examMapScoring.put("group C - math score", 0f);
    examMapScoring.put("group C - reading score", 0f);
    examMapScoring.put("group C - writing score", 0f);

    examMapScoring.put("group D - math score", 0f);
    examMapScoring.put("group D - reading score", 0f);
    examMapScoring.put("group D - writing score", 0f);

    examMapScoring.put("group E - math score", 0f);
    examMapScoring.put("group E - reading score", 0f);
    examMapScoring.put("group E - writing score", 0f);

    int groupATotalCount = 0;
    int groupBTotalCount = 0;
    int groupCTotalCount = 0;
    int groupDTotalCount = 0;
    int groupETotalCount = 0;

    for (int i = 1; i < records.size(); i++){
      List<String> record = records.get(i);

      String raceOrEthnicityGroup = record.get(1).substring(1, record.get(1).length() - 1);
      int currentRecordMathScore = Integer.parseInt(record.get(5).substring(1, record.get(5).length() - 1));
      int currentRecordReadingScore = Integer.parseInt(record.get(6).substring(1, record.get(6).length() - 1));
      int currentRecordWritingScore = Integer.parseInt(record.get(7).substring(1, record.get(7).length() - 1));

      if (raceOrEthnicityGroup.equals("group A")){
        groupATotalCount += 1;
      } else if (raceOrEthnicityGroup.equals("group B")){
        groupBTotalCount += 1;
      } else if (raceOrEthnicityGroup.equals("group C")){
        groupCTotalCount += 1;
      } else if (raceOrEthnicityGroup.equals("group D")){
        groupDTotalCount += 1;
      } else if (raceOrEthnicityGroup.equals("group E")){
        groupETotalCount += 1;
      }

      String mathKey = raceOrEthnicityGroup + " - math score";
      String readingKey = raceOrEthnicityGroup + " - reading score";
      String writingKey = raceOrEthnicityGroup + " - writing score";

      // Math calculation
      float currentMathScore = examMapScoring.get(mathKey);
      float newMathScore = currentMathScore + currentRecordMathScore;
      examMapScoring.put(mathKey, newMathScore);

      // Reading calculation
      float currentReadingScore = examMapScoring.get(readingKey);
      float newReadingScore = currentReadingScore + currentRecordReadingScore;
      examMapScoring.put(readingKey, newReadingScore);

      // Writing calculation
      float currentWritingScore = examMapScoring.get(writingKey);
      float newWritingScore = currentWritingScore + currentRecordWritingScore;
      examMapScoring.put(writingKey, newWritingScore);

    }

    // Calculate Average Scores for Group A and Update the values in the map
    float averageScoreGroupAMath = examMapScoring.get("group A - math score") / groupATotalCount;
    examMapScoring.put("group A - math score", averageScoreGroupAMath);

    float averageScoreGroupAReading = examMapScoring.get("group A - reading score") / groupATotalCount;
    examMapScoring.put("group A - reading score", averageScoreGroupAReading);

    float averageScoreGroupAWriting = examMapScoring.get("group A - writing score") / groupATotalCount;
    examMapScoring.put("group A - writing score", averageScoreGroupAWriting);

    // Calculate Average Scores for Group B and Update the values in the map
    float averageScoreGroupBMath = examMapScoring.get("group B - math score") / groupBTotalCount;
    examMapScoring.put("group B - math score", averageScoreGroupBMath);

    float averageScoreGroupBReading = examMapScoring.get("group B - reading score") / groupBTotalCount;
    examMapScoring.put("group B - reading score", averageScoreGroupBReading);

    float averageScoreGroupBWriting = examMapScoring.get("group B - writing score") / groupBTotalCount;
    examMapScoring.put("group B - writing score", averageScoreGroupBWriting);

    // Calculate Average Scores for Group C and Update the values in the map
    float averageScoreGroupCMath = examMapScoring.get("group C - math score") / groupCTotalCount;
    examMapScoring.put("group C - math score", averageScoreGroupCMath);

    float averageScoreGroupCReading = examMapScoring.get("group C - reading score") / groupCTotalCount;
    examMapScoring.put("group C - reading score", averageScoreGroupCReading);

    float averageScoreGroupCWriting = examMapScoring.get("group C - writing score") / groupCTotalCount;
    examMapScoring.put("group C - writing score", averageScoreGroupCWriting);

    // Calculate Average Scores for Group D and Update the values in the map
    float averageScoreGroupDMath = examMapScoring.get("group D - math score") / groupDTotalCount;
    examMapScoring.put("group D - math score", averageScoreGroupDMath);

    float averageScoreGroupDReading = examMapScoring.get("group D - reading score") / groupDTotalCount;
    examMapScoring.put("group D - reading score", averageScoreGroupDReading);

    float averageScoreGroupDWriting = examMapScoring.get("group D - writing score") / groupDTotalCount;
    examMapScoring.put("group D - writing score", averageScoreGroupDWriting);

      // Calculate Average Scores for Group E and Update the values in the map
    float averageScoreGroupEMath = examMapScoring.get("group E - math score") / groupETotalCount;
    examMapScoring.put("group E - math score", averageScoreGroupEMath);

    float averageScoreGroupEReading = examMapScoring.get("group E - reading score") / groupETotalCount;
    examMapScoring.put("group E - reading score", averageScoreGroupEReading);

    float averageScoreGroupEWriting = examMapScoring.get("group E - writing score") / groupETotalCount;
    examMapScoring.put("group E - writing score", averageScoreGroupEWriting);


    System.out.println("group A - math score: "+ examMapScoring.get("group A - math score"));
    System.out.println("group A - reading score: "+ examMapScoring.get("group A - reading score"));
    System.out.println("group A - writing score: "+ examMapScoring.get("group A - writing score"));

    System.out.println("group B - math score: "+examMapScoring.get("group B - math score"));
    System.out.println("group B - reading score: "+examMapScoring.get("group B - reading score"));
    System.out.println("group B - writing score: "+examMapScoring.get("group B - writing score"));

    System.out.println("group C - math score: "+examMapScoring.get("group C - math score"));
    System.out.println("group C - reading score: "+examMapScoring.get("group C - reading score"));
    System.out.println("group C - writing score: "+examMapScoring.get("group C - writing score"));

    System.out.println("group D - math score: "+examMapScoring.get("group D - math score"));
    System.out.println("group D - reading score: "+examMapScoring.get("group D - reading score"));
    System.out.println("group D - writing score: "+examMapScoring.get("group D - writing score"));

    System.out.println("group E - math score: "+examMapScoring.get("group E - math score"));
    System.out.println("group E - reading score: "+examMapScoring.get("group E - reading score"));
    System.out.println("group E - writing score: "+examMapScoring.get("group E - writing score"));

    // System.out.println("Group A Total Count: " + groupATotalCount);
    }catch(IOException ex){
      System.out.println("Error occured" + ex.getMessage());
    }
  }

}
