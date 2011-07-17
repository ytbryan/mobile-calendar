package nus.icreate.mtimetable.client;

public class Result {
	private String info;
    private String success;
    private String token;
    private String validtill;
    
    private Result(String s1, String s2, String s3, String s4)
    {
    	this.info = s1;
    	this.success=s2;
    	this.token =s3;
    	this.validtill = s4;
    }
   
    public String getSuccess()
    {
    	return this.success;
    }
}
