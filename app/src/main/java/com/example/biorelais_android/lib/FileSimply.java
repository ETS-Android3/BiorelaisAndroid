package com.example.biorelais_android.lib;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileSimply {

    // ---------------------------------------------
    public static String readAllText(Context act, String filename) throws IOException {
        FileInputStream fIn = act.openFileInput(filename);
        InputStreamReader isr = new InputStreamReader(fIn);
        BufferedReader buffreader = new BufferedReader(isr);

        String text = "", line = "";
        while ((line = buffreader.readLine()) != null) {
            text += line + "\n";
        }

        isr.close();
        return text;
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public static StringBuffer readAllsLines(Context act, String filename) throws IOException {
        FileInputStream fIn = act.openFileInput(filename);
        InputStreamReader isr = new InputStreamReader(fIn);
        BufferedReader buffreader = new BufferedReader(isr);

        StringBuffer buff = new StringBuffer();
        String line = "";
        while ((line = buffreader.readLine()) != null) {
            buff.append(line);
        }

        isr.close();
        return buff;
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public static String readOneLine(Context act, String filename) throws IOException {
        FileInputStream fIn = act.openFileInput(filename);
        InputStreamReader isr = new InputStreamReader(fIn);
        BufferedReader buffreader = new BufferedReader(isr);

        String line = buffreader.readLine();

        isr.close();
        return line;
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public static void writeAllText(String path, String text) throws IOException {
        final File file = new File(path);
        file.createNewFile();
        FileOutputStream fOut = new FileOutputStream(file);
        OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
        myOutWriter.write(text);

        myOutWriter.close();

        fOut.flush();
        fOut.close();
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public static void appendAllText(String path, String text) throws IOException {
        final File file = new File(path);
        file.createNewFile();
        FileOutputStream fOut = new FileOutputStream(file);
        OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
        myOutWriter.append(text);

        myOutWriter.close();

        fOut.flush();
        fOut.close();
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public static boolean fileExist(String path) {
        return (new File(path)).exists();
    }
    // ---------------------------------------------



    // ---------------------------------------------
    public static boolean fileDelete(String path) {
        return (new File(path)).delete();
    }
    // ---------------------------------------------

}
