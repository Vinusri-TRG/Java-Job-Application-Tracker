import java.util.Scanner;


public class project1{
    public static void main(String args[]){
        ApplicationManager manager = new ApplicationManager();
        FileHandler filehandler = new FileHandler();
        Scanner sc = new Scanner(System.in);
        filehandler.loadApplication(manager.jobs);

        while(true){
            System.out.println("1. Add Application");
            System.out.println("2. View Application");
            System.out.println("3. Search Application");
            System.out.println("4. Update Application");
            System.out.println("5. Delete Application");
            System.out.println("6. Save Application");
            System.out.println("7. Exit ");
            System.out.println("\n-----------------------------------------------------\n");
            System.out.print("Enter the choice :");
            int choice = sc.nextInt();
            System.out.println("\n-----------------------------------------------------\n");
            sc.nextLine();

            switch(choice){
                case 1:
                    System.out.print("Enter the Company Name:");
                    String companyname = sc.nextLine();
                    while(companyname.trim().isEmpty()){
                        System.out.println("Company name cannot be empty");
                        System.out.print("Enter the Company Name again:");
                        companyname = sc.nextLine();
                    }
                    System.out.print("Enter the Application Date:");
                    String applicationdate = sc.nextLine();
                    while(applicationdate.trim().isEmpty()){
                        System.out.println("Aplication date cannot be empty");
                        System.out.print("Enter the Applcation date again:");
                        applicationdate = sc.nextLine();
                    }

                    System.out.print("Enter the Job role:");
                    String jobrole = sc.nextLine();
                    while(jobrole.trim().isEmpty()){
                        System.out.println("Jobrole cannot be empty");
                        System.out.print("Enter the Jobrole again:");
                        jobrole = sc.nextLine();
                    }

                    System.out.print("Enter the Status:");
                    String status = sc.nextLine();
                    while(status.trim().isEmpty()){
                        System.out.println("Status cannot be empty");
                        System.out.print("Enter the Status again:");
                        status = sc.nextLine();
                    }

                    System.out.print("Enter the Interview Date:");
                    String interviewdate = sc.nextLine();
                    while(interviewdate.trim().isEmpty()){
                        System.out.println("Interview date cannot be empty");
                        System.out.print("Enter the Interview date again:");
                        interviewdate = sc.nextLine();
                    }

                    JobApplication job1 = new JobApplication(companyname,jobrole,applicationdate,status,interviewdate);
                    manager.addApplication(job1);
                    System.out.println("Successfully added the details...");
                    break;

                case 2:
                    manager.viewApplication();
                    break;

                case 3:
                    System.out.print("Enter the company name to search:");
                    String searchCompany = sc.nextLine();
                    manager.searchApplication(searchCompany);
                    break;

                case 4:
                    System.out.print("Enter the company name to update the status:");
                    String sc1 = sc.nextLine();
                    System.out.print("Enter the new status:");
                    String newStatus = sc.nextLine();
                    manager.updateApplication(sc1,newStatus);
                    break;

                case 5:
                    System.out.print("Enter the company you want to delete:");
                    String delete = sc.nextLine();
                    manager.deleteApplication(delete);
                    break;

                case 6:
                    filehandler.saveApplication(manager.jobs);
                    break;

                case 7:
                    System.out.println("Exit");
                    return;
            }
        }
        
    }
}