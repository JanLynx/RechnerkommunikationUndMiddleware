import static java.lang.System.out;

import java.io.Serializable;

public class ServerCommunication implements Serializable {
	
	private String message;
	private String name;
	private String feelings;
	
	public String[] splitInputString() {

		String input = comm.getMessage();
		out.println("Eingabe: " + input);
		String[] inputArr = input.split(" ");

		return inputArr;
	}
	
	public String getUsername() throws Exception {

		String username = "";
		String[] usernameArr = splitInputString();

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
	
	public void setName (String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setFeelings (String feelings) {
		this.feelings = feelings;
	}
	public String getFeelings() {
		return feelings;
	}
}
