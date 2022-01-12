
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;



//NOTES:  You will need the import statements that appear at the top of this file, so you should
//leave them in place.  Follow the list of steps on the project writeup to complete the CourseHistory
//class that is started below.  This entire block of comments should be deleted when you are done. 


public class CourseHistory
{  
     private ArrayList <CompletedCourse> courseList ;
  
     public CourseHistory()
     {    courseList = new ArrayList<CompletedCourse>();  
          String department;
          String courseNumber;
          String semesterTaken;
          String credit;
          String gradeEarned;
          String competency;
          String distArea;
        
          try
          {
               FileReader reader = new FileReader("finishedcourses.txt");
               Scanner in = new Scanner(reader);
               // Scanner in = new Scanner( file );
           
               while(in.hasNextLine())   
               {    department = in.nextLine();  
                    courseNumber = in.nextLine();
                    semesterTaken = in.nextLine();
                    credit = in.nextLine();  
                    gradeEarned = in.nextLine();
                    competency = in.nextLine();
                    distArea = in.nextLine();  
                    CompletedCourse theCourse = new CompletedCourse(department, courseNumber, semesterTaken, credit, gradeEarned, competency,  distArea );
                    courseList.add(theCourse);
               }
               in.close();  //Close the file when we are done reading it
          } catch (IOException exception)
          {
            System.out.println("Error processing file: " + exception);
          } 
  
     }
     
     //display the title “Course History” followed by a listing of all of the courses in the ArrayList in the order they 
     //appear in the input file (which is in the order the courses were taken). 
     public void displayCourseHistory()
     {    
          System.out.println("Course History");
          for  (int i=0; i<courseList.size(); i++)
          {    courseList.get(i).displayCourse();
          }
     }
     
     //displays a summary report based on the data in the ArrayList. The summary report should include the title “Summary Report” 
     //followed by the total number of credits earned and the total GPA
     public void summaryReport()
     {    System.out.println("Summary Report");
          double totalGrades=0.0;
          double totalCredits=0.0;
          
           for  (int i=0; i<courseList.size(); i++)
          {    if   (courseList.get(i).getCredit()>0)
               {    totalCredits+=courseList.get(i).getCredit();  
                    totalGrades+=courseList.get(i).getGrade();
               }
          }
          System.out.println("Total Credits:  " + totalCredits);
          System.out.println("Total Grades:  " + totalGrades);
          System.out.println("Final GPA:  " + totalGrades/totalCredits);
     }
     
     //displays a report that shows the user’s status toward meeting distribution area requirements.
     public void allDistributionAreaReport()
     {    System.out.println("Distribution Area Report"); 
          double ct1=helper("AH");
          System.out.println("Courses for AH are: " + ct1);
          System.out.println();
          
          double ct2=helper("SS");
          System.out.println("Courses for SS are: " + ct2);
          System.out.println();
          
          double ct3=helper("SM");
          System.out.println("Courses for SM are: " + ct3);
          System.out.println();
          
          double ct4=helper("LA");
          System.out.println("Courses for LA are: " + ct4); 
          System.out.println();
     }
     
     //Helper method initalized for allDistributionAreaReport method
     public double helper(String x)
     {    double ct=0.0;
          for  (int i=0; i<courseList.size(); i++)
          {    if   (courseList.get(i).getDistArea().equals(x) && courseList.get(i).getGrade()>0)
               {     courseList.get(i).displayCourse();
                     ct=ct+courseList.get(i).getCredit();
               }    
          }
          return ct;
     }
     
     // displays a report that shows the user’s status toward meeting competency (W, Q, S) requirements. 
     public void competencyStatus()
     {    int ctW=0;
          int ctQ=0;
          int ctS=0;
          for  (int i=0; i<courseList.size(); i++)
          {    if   (courseList.get(i).getCompetency().equals('W') && courseList.get(i).getCredit()>0 )
               { ctW++;
               } 
               
               if   (courseList.get(i).getCompetency().equals('Q') && courseList.get(i).getCredit()>0)
               { ctQ++;
               }
               
               if   (courseList.get(i).getCompetency().equals('S') && courseList.get(i).getCredit()>0)
               { ctS++;
               }
          }
         
          if(ctW>0 && ctQ>0 && ctS>0)
          {
             System.out.println("All competencies completed.");
          }
           else if(ctW==0 && ctQ==0 && ctS==0)
          {
             System.out.println("No competencies completed.");
          }
           else
          {  
             System.out.println("Competencies Partially Completed.");
            
              if(ctW>0)
              {System.out.println("W competency completed.");
              }
              else
              {System.out.println("W competency not completed.");
              }
             
              if(ctQ>0)
              {System.out.println("Q competency completed.");
              }
              else
              {System.out.println("Q competency not completed.");
              }
             
              if(ctS>0)
              {System.out.println("S competency completed.");
              }
              else
              {System.out.println("S competency not completed.");
              }
          }
     }
     
     // displays a full report that consists of the title “Full Report” followed by the summary information from step (3) followed by 
     // the distribution area fulfillment information from step (4) followed by the competency status information from step (5).
     public void fullReport()
     {    System.out.println("FULL REPORT");
          summaryReport();
          allDistributionAreaReport();
          competencyStatus();
     }
     
     //Displays a list of all the courses in the ArrayList, such that the courses are sorted by GPA (from highest GPA to lowest GPA).
     public void coursesSortedbyGPA()
     {
          int length = courseList.size();
          for(int i=0; i<length-1;i++)
          { 
               for(int j=0; j<length-1; j++)
               { 
                    if (courseList.get(j).getGrade()< courseList.get(j+1).getGrade())
                    {
                        CompletedCourse temp = courseList.get(j);
                        courseList.set(j, courseList.get(j+1));
                        courseList.set(j+1, temp);
                    }
               }
          }

          for (int i=0; i<courseList.size(); i++)
          {
             courseList.get(i).displayCourse();
          }
         
     }
}





