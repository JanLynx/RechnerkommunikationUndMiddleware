import javax.xml.parsers.*;
import org.w3c.dom.*;

public class DOMBuilder {

	private Document doc;
	private String charCounterString;
	private String wordCounterString;
	private String eCounterString;

	public Document buildDocument(String charCounterString, String wordCounterString, String eCounterString) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			doc = builder.newDocument();
			Element textanalyse = doc.createElement("textanalyse");
			doc.appendChild(textanalyse);
			Element charCounter = doc.createElement("charCounter");
			charCounter.appendChild(doc.createTextNode(charCounterString));
			textanalyse.appendChild(charCounter);
			Element wordCounter = doc.createElement("wordCounter");
			wordCounter.appendChild(doc.createTextNode(wordCounterString));
			textanalyse.appendChild(wordCounter);
			Element eCounter = doc.createElement("eCounter");
			eCounter.appendChild(doc.createTextNode(eCounterString));
			textanalyse.appendChild(eCounter);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}

	public void parseDocument(Document doc) {
		try {
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("textanalyse");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					charCounterString = eElement.getElementsByTagName("charCounter").item(0).getTextContent();
					wordCounterString = eElement.getElementsByTagName("wordCounter").item(0).getTextContent();
					eCounterString = eElement.getElementsByTagName("eCounter").item(0).getTextContent();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getCharCounter() {
		return Integer.parseInt(charCounterString);
	}

	public int getWordCounter() {
		return Integer.parseInt(wordCounterString);
	}

	public int getECounter() {
		return Integer.parseInt(eCounterString);
	}
}
