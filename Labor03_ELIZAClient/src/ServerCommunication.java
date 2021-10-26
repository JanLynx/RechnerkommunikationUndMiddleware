import java.io.Serializable;

public class ServerCommunication implements Serializable {
	
	private String challenge;

	public void setMessage (String challenge) {
		this.challenge = challenge;
	}
	public String getMessage() {
		return challenge;
	}
}
