import Connection.Connection;
import Files.TXTReader;
import Files.XMLReader;

import java.io.File;

import Clases.*;
import org.apache.commons.net.ftp.FTPClient;

public class Ppal {
	private static FTPClient ftp;
	private static TXTReader txt;
	private static XMLReader xml;
	private static Connection c;
	private static int id;
	public static void main(String[] args) {
		id = Integer.parseInt(args[0]);
		init();
		PicturesGuarderia pg = txt.txtToGuarderia(Constanes.RUTA_TXT + "/" + id + ".txt");
		if (c.directoryExists(ftp, "/public_html/"+id)) {
			File f = new File(Constanes.RUTA_XML + "/" + id + ".xml");
			c.downloadFile(ftp, "/public_html/"+id+"/version.xml", Constanes.RUTA_XML + "/" + id+".xml");
			deleteRemote(xml.xmlToGuarderia(new File(Constanes.RUTA_XML + "/" + id+".xml")));
			xml.writeXML(f, pg.toXML());
			insertRemote(pg, f);
		} else {
			createRemote(pg);
		}
		c.disconect(ftp);
	}

	private static void init() {
		txt = new TXTReader();
		xml = new XMLReader();
		c = new Connection();
		ftp = c.conect();
	}

	private static void createRemote(PicturesGuarderia pg) {
		File f = new File(Constanes.RUTA_XML + "/" + id + ".xml");
		xml.writeXML(f, pg.toXML());
		c.makeDirectory("/public_html/"+id, ftp);
		insertRemote(pg, f);
	}

	private static void insertRemote(PicturesGuarderia pg, File xml) {
		c.pushFile(xml, ftp,"/public_html/"+id+"/version.xml");
		for (Picture p : pg.getPictures()) {
			c.pushFile(new File(Constanes.RUTA_IMATGES+"/"+id+"/"+p.getName()), ftp, "/"+id+"/"+p.getName());
		}
	}

	private static void deleteRemote(PicturesGuarderia pg) {
		for (Picture p : pg.getPictures()) {
			c.deleteFile(ftp, "/public_html/"+id+"/"+p.getName());
		}
	}
}
