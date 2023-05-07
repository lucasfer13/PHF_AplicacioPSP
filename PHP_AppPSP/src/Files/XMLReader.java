package Files;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import Clases.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLReader {
    public PicturesGuarderia xmlToGuarderia(File f) {
        int id; Date d; ArrayList<Picture> pictures = new ArrayList<Picture>();
        PicturesGuarderia pg = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(f);
            id = Integer.parseInt(doc.getElementsByTagName("id").item(0).getTextContent());
            d = new Date(Long.parseLong(doc.getElementsByTagName("date").item(0).getTextContent()));
            Element e1 = (Element) doc.getElementsByTagName("pictures").item(0);
            NodeList nl = e1.getElementsByTagName("picture");
            for (int i = 0;i < nl.getLength();i++) {
                Element e = (Element) nl.item(i);
                pictures.add(new Picture(Integer.parseInt(e.getElementsByTagName("id").item(0).getTextContent()), e.getElementsByTagName("name").item(0).getTextContent()));
            }
            pg = new PicturesGuarderia(id, d, pictures);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return pg;
    }

    public void writeXML(File f, String xml)  {
        PrintStream ps = null;
        try {
            ps = new PrintStream(f);
            ps.write(xml.getBytes());
            ps.flush();
            ps.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
