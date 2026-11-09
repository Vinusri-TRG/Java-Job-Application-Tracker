import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

class FileHandler{
    void saveApplication(ArrayList<JobApplication> jobs){
        try{
            FileWriter writer = new FileWriter("application.txt");
            for(JobApplication job:jobs){
                writer.write(job.getCompanyname()+", "+
                    job.getJobrole()+", "+
                    job.getApplicationdate()+", "+
                    job.getStatus()+", "+
                    job.getInterviewdate()+"\n");
            }
            writer.close();
            System.out.println("Application Saved Successfully!!!");
            System.out.println("\n-----------------------------------------------------\n");
        }
        catch(IOException e){
            System.out.println("Errors saving applications");
        }
    }

    void loadApplication(ArrayList<JobApplication> jobs){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("application.txt"));
            String line;
            while((line=reader.readLine())!=null){
                String[] data = line.split(", ");
                JobApplication job = new JobApplication(
                    data[0],
                    data[1],
                    data[2],
                    data[3],
                    data[4]
                );
                jobs.add(job);
            }
            reader.close();
            System.out.println("Application loaded Successfully!!!");
        }catch(IOException e){
            System.out.println("Loaded application appears error");
        }
    }
    
}
