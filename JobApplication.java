public class JobApplication {

    private String companyname;
    private String jobrole;
    private String applicationdate;
    private String status;
    private String interviewdate;

    public JobApplication(String companyname, String jobrole,
                          String applicationdate, String status,
                          String interviewdate) {

        this.companyname = companyname;
        this.jobrole = jobrole;
        this.applicationdate = applicationdate;
        this.status = status;
        this.interviewdate = interviewdate;
    }

    public String getCompanyname() {
        return companyname;
    }

    public String getJobrole() {
        return jobrole;
    }

    public String getApplicationdate() {
        return applicationdate;
    }

    public String getStatus() {
        return status;
    }

    public String getInterviewdate() {
        return interviewdate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}