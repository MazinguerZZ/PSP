package psp.sec;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class AccessControlManager {

    private final static String filename = "data/secretos.txt";
    private final static String filename2 = "data/secretos2.txt";

    public static void setPrivileges() throws IOException {

        Path path = Path.of(filename);

        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("linux")) {

            Set<PosixFilePermission> perms = Files.getPosixFilePermissions(path);
            System.out.println(perms);

            perms = PosixFilePermissions.fromString("rw-------");
            Files.setPosixFilePermissions(path, perms);

            perms = Files.getPosixFilePermissions(path);
            System.out.println(perms);

        } else {

            AclFileAttributeView aclView = Files.getFileAttributeView(path, AclFileAttributeView.class);
            UserPrincipal user = path.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("alumno");

            AclEntry entry = AclEntry.newBuilder()
                    .setType(AclEntryType.ALLOW)
                    .setPrincipal(user)
                    .setPermissions(AclEntryPermission.READ_DATA)
                    .setPermissions(AclEntryPermission.READ_ATTRIBUTES)
                    .setPermissions(AclEntryPermission.READ_ACL)
                    .setPermissions(AclEntryPermission.WRITE_DATA)
                    .setPermissions(AclEntryPermission.APPEND_DATA)
                    .setPermissions(AclEntryPermission.READ_NAMED_ATTRS)
                    .setPermissions(AclEntryPermission.WRITE_NAMED_ATTRS)
                    .setPermissions(AclEntryPermission.DELETE_CHILD)
                    .setPermissions(AclEntryPermission.WRITE_ACL)
                    .setPermissions(AclEntryPermission.DELETE)
                    .setPermissions(AclEntryPermission.WRITE_OWNER)
                    .setPermissions(AclEntryPermission.SYNCHRONIZE)
                    .build();
            List<AclEntry> acls = new ArrayList<>();
            acls.add(entry);

            aclView.setAcl(acls);

        }
    }

    public static void createPrivilegedFile() throws IOException {

        Path path = Path.of(filename2);
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("linux")) {

            Set<PosixFilePermission> perms =  PosixFilePermissions.fromString("rw-------");

            FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);

            Files.createFile(path, attr);

        }else {
            UserPrincipal user = path.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("alumno");
            Set<AclEntryPermission> permissions = EnumSet.of(
                    AclEntryPermission.READ_DATA
                    ,AclEntryPermission.READ_ATTRIBUTES
                    ,AclEntryPermission.READ_ACL
                    ,AclEntryPermission.WRITE_DATA
                    ,AclEntryPermission.APPEND_DATA
                    ,AclEntryPermission.READ_NAMED_ATTRS
                    ,AclEntryPermission.WRITE_NAMED_ATTRS
                    ,AclEntryPermission.DELETE_CHILD
                    ,AclEntryPermission.WRITE_ACL
                    ,AclEntryPermission.DELETE
                    ,AclEntryPermission.WRITE_OWNER
                    ,AclEntryPermission.SYNCHRONIZE
            );


            AclEntry entry = AclEntry.newBuilder()
                    .setType(AclEntryType.ALLOW)
                    .setPrincipal(user)
                    .setPermissions(permissions)
                    .build();
            List<AclEntry> acls = new ArrayList<>();
            acls.add(entry);

            FileAttribute<List<AclEntry>> attr = new FileAttribute<>() {
                @Override
                public String name() {
                    return "acl:acl";
                }

                @Override
                public List<AclEntry> value() {
                    return acls;
                }
            };

            Files.createFile(path, attr);

        }
    }

    public static void main(String[] args) throws IOException {
        AccessControlManager.setPrivileges();
        AccessControlManager.createPrivilegedFile();
    }
}
