package Files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import Clases.*;

public class TXTReader {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public PicturesGuarderia txtToGuarderia(String path) {
        PicturesGuarderia pg = null;
        try {
            FileReader fr = new FileReader(path);
            int id;
            int cant;
            Date date;
            ArrayList<Picture> pictures = new ArrayList<Picture>();
            BufferedReader br = new BufferedReader(fr);
            String line[] = br.readLine().split("#");

            id = Integer.parseInt(line[0]);
            date = sdf.parse(line[1]);
            cant = Integer.parseInt(line[2]);
            for (int i = 0;i < cant;i++) {
                line = br.readLine().split("#");
                pictures.add(new Picture(Integer.parseInt(line[0]), line[1]));
            }
            pg = new PicturesGuarderia(id, date, pictures);
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return pg;
    }
}
