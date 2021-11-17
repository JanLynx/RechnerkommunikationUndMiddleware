import javax.xml.parsers.*;
import org.w3c.dom.*;

public class DOMBuilder {

	private Document doc;
	
	private String charCounterString;
	private String wordCounterString;
	private String eCounterString;

	public DOMBuilder() {

	}

	public Document buildDocument(String charCounterString, String wordCounterString, String eCounterString) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			doc = builder.newDocument();
			Element textanalyse = doc.createElement("catalog");
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

//	public void saveDocument(String filename) throws TransformerException, IOException {
//		TransformerFactory transformerFactory = TransformerFactory.newInstance();
//		Transformer transformer = transformerFactory.newTransformer();
//		DOMSource source = new DOMSource(doc);
//		StreamResult result = new StreamResult(new File(filename));
//		transformer.transform(source, result);
//		String xmlDocument = Files.readString(Path.of(filename));
//		System.out.println(xmlDocument);
//	}
	
	public void parseDocument(Document doc) {
		try {
//	         File inputFile = new File("input.txt");
//	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         NodeList nList = doc.getElementsByTagName("textanalyse");
	         
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :" + nNode.getNodeName());
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
//	               filename = eElement.getAttribute("Datei"));
	               charCounterString = eElement.getElementsByTagName("charCounter").item(0).getTextContent();
	               wordCounterString = eElement.getElementsByTagName("wordCounter").item(0).getTextContent();
	               eCounterString = eElement.getElementsByTagName("eCounter").item(0).getTextContent();
	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	}
	
	public String getCharCounter() {
		return charCounterString;
	}
	
	public String getWordCounter() {
		return wordCounterString;
	}
	
	public String getECounter() {
		return eCounterString;
	}
}
