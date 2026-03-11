package com.psp.sftp;

import com.jcraft.jsch.*;

import java.io.FileInputStream;
import java.nio.file.Path;

public class ClienteSFTP {


    public static void main(String[] args) {

        Path srcDir= Path.of("localFiles");
        String filename= "ejemplo.txt";
        String filename_dst= "ejemplo_subido.txt";

        JSch jsch= new JSch();
        try {
            Session sesion= jsch.getSession("usuario", "localhost", 2222);
            sesion.setPassword("password");

            sesion.setConfig("StrictHostKeyChecking", "no");

            sesion.connect();

            ChannelSftp sftp= (ChannelSftp) sesion.openChannel("sftp");
            sftp.connect();

            for(int i=0; i<10; i++) {
                sftp.put(srcDir + "/" + filename, filename_dst + i);

                System.out.printf("Archivo %s subido correctamente al servidor FTP.", filename);

                Thread.sleep(2000);
            }
            sftp.disconnect();
            sesion.disconnect();
        } catch (JSchException e) {
            throw new RuntimeException(e);
        } catch (SftpException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
