package Connection;

import org.apache.commons.net.ProtocolCommandEvent;
import org.apache.commons.net.ProtocolCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;

public class Connection {
    private static final String SERVER = "files.000webhost.com";
    private static final int PORT = 21;
    private static final String USER_NAME = "pethotelfinder";
    private static final String PASS = "LuElFo2023";

    // Funcio per conectar al FTP
    public FTPClient conect() {
        FTPClient ftp = new FTPClient();
        try {
            ftp.addProtocolCommandListener(new ProtocolCommandListener() {
                @Override
                public void protocolCommandSent(ProtocolCommandEvent protocolCommandEvent) {
                    System.out.printf("[%s][%d] Command sent : [%s]-%s", Thread.currentThread().getName(),
                            System.currentTimeMillis(), protocolCommandEvent.getCommand(),
                            protocolCommandEvent.getMessage());
                }

                @Override
                public void protocolReplyReceived(ProtocolCommandEvent protocolCommandEvent) {
                    System.out.printf("[%s][%d] Reply received : %s", Thread.currentThread().getName(),
                            System.currentTimeMillis(), protocolCommandEvent.getMessage());
                }
            });
            ftp.connect(SERVER, PORT);
            ftp.login(USER_NAME, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ftp;
    }

    // Funcio per desconectar el FTP
    public void disconect(FTPClient ftp) {
        try {
            ftp.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Per pujar un fitxer
    public void pushFile(File f, FTPClient ftp, String remotePath) {
        try {
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            InputStream fis = new FileInputStream(f.getAbsolutePath());
            ftp.storeFile(remotePath, fis);
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Per descargar un fitxer
    public void downloadFile(FTPClient ftp, String pathRemote, String localPath) {
        try {
            FileOutputStream fos = new FileOutputStream(localPath);
            ftp.retrieveFile(pathRemote, fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Per comprovar si un directori existeix
    public boolean directoryExists(FTPClient ftp, String path) {
        try {
            if (ftp.changeWorkingDirectory(path)) {
                ftp.changeWorkingDirectory("..");
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    // Per crear el directory
    public void makeDirectory(String directory, FTPClient ftp) {
        try {
            ftp.makeDirectory(directory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // S'elimina l'arxiu de la ruta ftp
    public void deleteFile(FTPClient ftp, String file) {
        try {
            ftp.deleteFile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}