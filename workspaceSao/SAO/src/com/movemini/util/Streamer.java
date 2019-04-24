package com.movemini.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


public class Streamer {


    public static void streamFile(String outputFilePath, HttpServletResponse response, String fileNameToDownload) throws Exception {

        File file = new File(outputFilePath);

        response.setContentType("application/x-unknown; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileNameToDownload);
        response.setContentLength((int) file.length());

        ServletOutputStream out = response.getOutputStream();

        InputStream in2 = new FileInputStream(file);
        int length = (int) file.length();
        int bufferSize = 2048;//cambio de 1024
        byte[] buffer = new byte[bufferSize];
        while ((length = in2.read(buffer)) != -1) {
            out.write(buffer, 0, length);
        }
        in2.close();

        out.flush();
    }
}
