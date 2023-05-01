package Clases;

public class Picture {
    private int idPicture;
    private String name;

    public Picture(int idPicture, String name) {
        this.idPicture = idPicture;
        this.name = name;
    }

    public Picture() {
    }

    public int getIdPicture() {
        return idPicture;
    }

    public void setIdPicture(int idPicture) {
        this.idPicture = idPicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
