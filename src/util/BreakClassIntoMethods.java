package util;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;

public class BreakClassIntoMethods {
    public static void breakClassIntoMethods(String sourcePath,String targetPath) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        File dir = new File(sourcePath);
        for(File xmlFile : dir.listFiles()) {
            FileInputStream fin = new FileInputStream(xmlFile.getPath());
            String fileName = xmlFile.getName().substring(0,xmlFile.getName().indexOf("."));

            String xpathContent = "//function";
            String xpathName = "//function/name";

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            org.w3c.dom.Document xmlDocument = builder.parse(fin);
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList listContent = (NodeList) xPath.compile(xpathContent).evaluate(xmlDocument, XPathConstants.NODESET);
            NodeList listName = (NodeList) xPath.compile(xpathName).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < listContent.getLength(); i++) {
                Node nodeContent = listContent.item(i);
                Node nodeName = listName.item(i);
                String methodName = nodeName.getTextContent();
                String methodContent = nodeContent.getTextContent();

                BufferedWriter bw = new BufferedWriter(new FileWriter(targetPath+File.separator+fileName+"_"+methodName+".txt"));
                bw.write(methodContent);
                bw.close();

            }
        }
    }
}
