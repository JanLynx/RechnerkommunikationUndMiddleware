import java.io.Serializable;

public class ServerChallengeInfo implements Serializable {
	
	private double solution;
	private String challenge;

	public void setSolution (double solution) {
		this.solution = solution;
	}
	public double getSolution() {
		return solution;
	}
	public void setChallenge (String challenge) {
		this.challenge = challenge;
	}
	public String getChallenge() {
		return challenge;
	}
}
