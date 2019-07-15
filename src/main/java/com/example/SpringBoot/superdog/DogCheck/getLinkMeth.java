package com.example.springboot.superdog.DogCheck;

import com.sun.jna.Library;
import com.sun.jna.Native;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;


/**
 * 获取dll库文件中的Api函数
 *
 * @author duwenxu
 * @create 2019-07-12 11:07
 */
@Component
public class getLinkMeth {

    static String relativePath = "";

    static {
        String operatingSystem = System.getProperty("os.name");
        String architecture = System.getProperty("os.arch");


        String runtime_library_x86_windows = "dog_windows_3156389.dll";
        String runtime_library_x64_windows = "dog_windows_x64_3156389.dll";

        String runtime_library_x86_linux = "dog_windows_3156389.so";
        String runtime_library_x64_linux = "dog_windows_x64_3156389.so";

        try {
            relativePath = new File("").getCanonicalPath() + "\\src\\main\\kotlin\\com\\waytogalaxy\\display\\superdog\\dlls\\";
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            /* Windows library loading */
            if (operatingSystem.contains("Windows")) {
                if (architecture.equals("x86")) {
                    relativePath = relativePath + runtime_library_x86_windows;
                } else if (architecture.equals("x86_64") || architecture.equals("amd64")) {
                    relativePath = relativePath + runtime_library_x64_windows;
                } else
                    relativePath = relativePath + runtime_library_x86_windows;
            } else {
                /* Linux library loading */
                if (operatingSystem.contains("Linux")) {
                    if (architecture.equals("x86")) {
                        relativePath = relativePath + runtime_library_x86_linux;
                    } else if (architecture.equals("x86_64") || architecture.equals("amd64"))
                        relativePath = relativePath + runtime_library_x64_linux;
                    else {
                        relativePath = relativePath + runtime_library_x86_windows;
                    }
                } else {
                    relativePath = relativePath + runtime_library_x86_windows;
                }
            }
        } catch (UnsatisfiedLinkError e) {
            if (!e.getMessage().contains("already loaded in another classloader")) {
                throw e;
            }
        }
    }


    interface CLibrary extends Library {


        CLibrary INSTANCE_DOG = (CLibrary) Native.loadLibrary(relativePath, CLibrary.class);

        int dog_login(long feature_id, String vendor_code, int handle[]);

        int dog_login_scope(long feature_id, String scope, String vendor_code, int handle[]);

        int dog_logout(int handle);

        int dog_encrypt(int handle, byte buffer[], int length);

        int dog_decrypt(int handle, byte buffer[], int length);

        int dog_get_size(int handle, long fileid, int size[]);

        int dog_read(int handle, long fileid, int offset, int length, byte buffer[]);

        int dog_write(int handle, long fileid, int offset, int length, byte buffer[]);

        int dog_get_time(int handle, long time[]);

        byte[] dog_get_info(String scope, String format, String vendor_code, int status[]);

        byte[] dog_get_sessioninfo(int handle, String format, int status[]);

        String dog_update(String update_data, int status[]);

        void dog_free(long info);

    }
}

