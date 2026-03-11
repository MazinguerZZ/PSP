package com.psp.sftp;

import org.apache.sshd.common.file.virtualfs.VirtualFileSystemFactory;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.auth.AsyncAuthException;
import org.apache.sshd.server.auth.password.PasswordAuthenticator;
import org.apache.sshd.server.auth.password.PasswordChangeRequiredException;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.session.ServerSession;
import org.apache.sshd.sftp.server.SftpSubsystemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

public class ServidorSFTP {
    public static void main(String[] args) throws Exception {

        SshServer sshd= SshServer.setUpDefaultServer();
        sshd.setPort(2222);

        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(Path.of("hostkey.ser")));

        sshd.setPasswordAuthenticator(new PasswordAuthenticator() {
            @Override
            public boolean authenticate(String username, String password, ServerSession serverSession) throws PasswordChangeRequiredException, AsyncAuthException {
                return username.equals("usuario") && password.equals("password");
            }
        });

        sshd.setSubsystemFactories(Collections.singletonList(new SftpSubsystemFactory()));

        Path rootDir= Path.of("data");
        if(!Files.exists(rootDir)) {
            Files.createDirectories(rootDir);
            System.out.println("Carpeta " + rootDir + " creada automáticamente.");
        }

        VirtualFileSystemFactory vfs= new VirtualFileSystemFactory();
        vfs.setDefaultHomeDir(rootDir);
        sshd.setFileSystemFactory(vfs);

        sshd.start();

        Logger log = LoggerFactory.getLogger(ServidorSFTP.class);
        log.info("Servidor SFTP iniciado en puerto 2222");

        Thread.currentThread().join();
    }
}