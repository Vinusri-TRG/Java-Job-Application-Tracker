class JobApplication{
    private String companyname;
    private String jobrole;
    private String applicationdate;
    private String status;
    private String interviewdate;
    JobApplication(String companyname,String jobrole,String applicationdate,String status,String interviewdate){
        this.companyname = companyname;
        this.jobrole = jobrole;
        this.applicationdate = applicationdate;
        this.status = status;
        this.interviewdate = interviewdate;
        
    }
    String getCompanyname(){
        return companyname;
    }
    String getJobrole(){
        return jobrole;
    }
    String getApplicationdate(){
        return applicationdate;
    }
    String getStatus(){
        return status;
    }
    String getInterviewdate(){
        return interviewdate;
    }
    void setStatus(String status){
        this.status = status;
    }
}
