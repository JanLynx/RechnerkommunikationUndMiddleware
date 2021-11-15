//Name: Jan Schönitz m26336
//Datum: 29.10.2021

import java.io.Serializable;

public class ServerCommunication implements Serializable {
	
	private String message;
	private String name;
	private String[] feelings;
	private String answer;
	
	public String[] splitString(String input) {

		String[] inputArr = input.split(" ");

		return inputArr;
	}
	
	public String getUsername(String input) throws Exception {

		String username = "";
		String[] usernameArr = splitString(input);

		if (usernameArr.length == 1) {
			username = usernameArr[0];
		} else {
			username = usernameArr[usernameArr.length - 1];
		}

		return username;

	}


	public void setMessage (String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	
	public void setNameFromString (String input) throws Exception {
		this.name = getUsername(input);
	}
	public String getName() {
		return name;
	}
	
	public void setAnswer (String answer) throws Exception {
		this.answer = answer;
	}
	public String getAnswer() {
		return answer;
	}
	
	public void setFeelings (String feelings) throws Exception {
		this.feelings = splitString(feelings);
	}
	public String[] getFeelings() {
		return feelings;
	}
}

