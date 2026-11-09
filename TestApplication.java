import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class TestApplication {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("===== JOB APPLICATION TRACKER =====");
            System.out.println("1. Add Application");
            System.out.println("2. View Applications");
            System.out.println("3. Search Application");
            System.out.println("4. Update Application");
            System.out.println("5. Delete Application");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {

                System.out.println("Invalid input! Please enter a number from 1 to 6.");

                sc.nextLine();

                continue;
            }

            int choice = sc.nextInt();
            System.out.println("You selected: " + choice);
            switch(choice){
                case 1:
                    sc.nextLine();
                    System.out.print("Enter the Company name: ");
                    String companyName = sc.nextLine();
                    System.out.print("Enter the Jobrole: ");
                    String jobRole = sc.nextLine();
                    
                    System.out.print("Enter the ApplicationDate (dd-MM-yyyy): ");
                    String application_date = sc.nextLine();

                    if (!isValidDate(application_date)) {
                        System.out.println("Invalid application date! Use dd-MM-yyyy, for example 15-01-2027.");
                        break;
                    }

                    System.out.print("Enter the Status: ");
                    String status = sc.nextLine();
                    
                    System.out.print("Enter the InterviewDate (dd-MM-yyyy, or press Enter if not scheduled): ");
                    String interview_date = sc.nextLine();

                    if (!interview_date.trim().isEmpty() && !isValidDate(interview_date)) {
                        System.out.println("Invalid interview date! Use dd-MM-yyyy, for example 20-01-2027.");
                        break;
                    }

                    if(companyName.trim().isEmpty() || jobRole.trim().isEmpty() || application_date.trim().isEmpty()
                     || status.trim().isEmpty()){
                        System.out.println("Fill the requried details ");
                        break;
                    }
                    JobApplication application = new JobApplication(
                                        companyName,
                                        jobRole,
                                        application_date,
                                        status,
                                        interview_date);
                    ApplicationDAO.addApplication(application);
                    break;

                case 2:
                    ApplicationDAO.getAllApplications();
                    break;

                case 3:
                    sc.nextLine();

                    System.out.print("Enter company name or job role to search: ");
                    String keyword = sc.nextLine();

                    ApplicationDAO.searchApplications(keyword);
                    break;

                case 4:
                    System.out.print("Enter application ID: ");

                    if (!sc.hasNextInt()) {

                        System.out.println("Invalid ID! Please enter a number.");

                        sc.nextLine();

                        break;
                    }

                    int id = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter new status: ");
                    String newStatus = sc.nextLine();
                   
                    System.out.print("Enter new interview date (d-M-yyyy, or press Enter if not scheduled): ");
                    String newDate = sc.nextLine();

                    if (!newDate.trim().isEmpty() && !isValidDate(newDate)) {
                        System.out.println("Invalid interview date! Use d-M-yyyy, for example 8-11-2026.");
                        break;
                    }

                    if (newStatus.trim().isEmpty()) {
                        System.out.println("Status cannot be empty!");
                        break;
                    }

                    ApplicationDAO.updateStatus(id, newStatus, newDate);

                    break;
                    
                case 5:

                    System.out.print("Enter application ID to delete: ");
                    int deleteId = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Are you sure you want to delete? (yes/no): ");
                    String confirm = sc.nextLine();

                    if (confirm.equalsIgnoreCase("yes")) {

                        ApplicationDAO.deleteApplication(deleteId);

                    } else {

                        System.out.println("Delete cancelled.");

                    }

                    break;

                case 6:

                    System.out.println("Thank you for using Job Application Tracker!");
                    sc.close();
                    return;
                    
                default:
                    System.out.println("Invalid choice!!!!!");
            }
        }
    } 
    
    public static boolean isValidDate(String date) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-uuuu")
                        .withResolverStyle(ResolverStyle.STRICT);

        try {
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
          
}
