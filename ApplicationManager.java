import java.util.ArrayList;
class ApplicationManager{
    ArrayList<JobApplication> jobs = new ArrayList<>();
    void addApplication(JobApplication job){
        jobs.add(job);
    }

    void viewApplication(){
        
        if(jobs.isEmpty()){
            System.out.println("No Application found...");
        }
        else{
            int count =1;
            for(JobApplication job:jobs){
                System.out.println("Application "+count++);
                System.out.println("Company Name : "+job.getCompanyname());
                System.out.println("Application Date : "+job.getApplicationdate());
                System.out.println("Job Role : "+job.getJobrole());
                System.out.println("Status : "+job.getStatus());
                System.out.println("Interview Date : "+job.getInterviewdate());
                System.out.println("\n-----------------------------------------------------\n");
            }
        }
                    
        System.out.println("Successfully Viewed the details");

    }

    void searchApplication(String searchCompany){
        boolean search = false;
        for(JobApplication job:jobs){
            if(searchCompany.equalsIgnoreCase(job.getCompanyname())){
                search=true;
                System.out.println("Company Name : "+job.getCompanyname());
                System.out.println("Application Date : "+job.getApplicationdate());
                System.out.println("Job Role : "+job.getJobrole());
                System.out.println("Status : "+job.getStatus());
                System.out.println("Interview Date : "+job.getInterviewdate());
                System.out.println("\n-----------------------------------------------------\n");
                break;
            }
        }
        if(!search){
            System.out.println("No application found");
        }
    }

    void updateApplication(String sc1,String newStatus){
        boolean Update = false;
        for(JobApplication job:jobs){
            if(sc1.equalsIgnoreCase(job.getCompanyname())){
                
                job.setStatus(newStatus);
                System.out.println("Updated successfully!!!");
                Update=true;
                System.out.println("\n-----------------------------------------------------\n");
                break;
            
            }
        }
        if(!Update){
            System.out.println("Application NOt found!!!");
        }
    }

    void deleteApplication(String delete){
        boolean deleted = false;
        for(int i=0;i<jobs.size(); i++){
            if(jobs.get(i).getCompanyname().equalsIgnoreCase(delete)){
                jobs.remove(i);
                deleted = true;
                System.out.println("Deleted sucessfully!!!");
                break;
            }
        }
        if(!deleted){
            System.out.println("Application not found");
        }
    }
    
}