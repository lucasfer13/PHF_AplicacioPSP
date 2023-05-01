package Clases;

import java.util.ArrayList;
import java.util.Date;

public class PicturesGuarderia {
    private int idGuarderia;
    private Date data;
    private ArrayList<Picture> pictures;

    public PicturesGuarderia(int idGuarderia, Date data, ArrayList<Picture> pictures) {
        this.idGuarderia = idGuarderia;
        this.data = data;
        this.pictures = pictures;
    }

    public PicturesGuarderia() {
    }

    public int getIdGuarderia() {
        return idGuarderia;
    }

    public void setIdGuarderia(int idGuarderia) {
        this.idGuarderia = idGuarderia;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public ArrayList<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<Picture> pictures) {
        this.pictures = pictures;
    }

    public String toXML() {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        xml += "<guarderia>\n";
        xml += "\t<id>" + idGuarderia + "</id>\n";
        xml += "\t<date>" + data.getTime() + "</date>\n";
        xml += "\t<pictures>\n";
        for (Picture p : pictures) {
            xml += "\t\t<picture><id>" + p.getIdPicture() + "</id><name>" + p.getName() + "</name></picture>\n";
        }
        xml += "\t</pictures>\n";
        xml += "</guarderia>";
        return xml;
    }

    @Override
    public String toString() {
        return "PicturesGuarderia{" +
                "idGuarderia=" + idGuarderia +
                ", data=" + data +
                ", pictures=" + pictures +
                '}';
    }
}
