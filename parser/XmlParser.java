package parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XmlParser {
    private DocumentBuilder docBuilder;

    public XmlParser() {
        final DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = dbfac.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void parse(String path) {
        try {
            final Document doc = docBuilder.parse(path);
            final Element mainElement = doc.getDocumentElement();
            parseXmlTree(mainElement);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    /** Проходит рекурсивно по всем элементам XML (DAE) дерева и собирает нужные атрибуты
     *
     * @param e узел XML дерева
     */
    private void parseXmlTree(final Element e) {
        System.out.println(e.getNodeName());
        final NodeList children = e.getChildNodes();

        for (int i = 0; i < children.getLength(); i++) {
            final Node n = children.item(i);
            String text = n.getNodeName().trim();

            if (text.equals("#text") && n.getTextContent().trim().isEmpty())
                continue;

            System.out.println(String.format("%s", text.equals("#text") ? n.getTextContent() : text));

            if (n.getNodeType() == Node.ELEMENT_NODE) {
                parseXmlTree((Element) n);
            }
        }
    }
}
