import java.io.Serializable;

public class ServerChallengeInfo implements Serializable {
	
	private String handler;

	public void setString(String handler) {
		this.handler = handler;
	}
	public String getString() {
		return handler;
	}
}
