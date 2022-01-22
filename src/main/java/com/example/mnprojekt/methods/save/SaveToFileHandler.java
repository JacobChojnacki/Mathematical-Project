package com.example.mnprojekt.methods.save;

import com.example.mnprojekt.methods.ConsolHandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveToFileHandler extends ConsolHandler {

    private String filename;

    public void saveToFileHandler(String filename) {
        this.filename = filename;
    }

    public void saveToFile(){
        File filetoWrite = new File(filename);
        BufferedWriter bufferedWriter = null;
        try{
            FileWriter fileWriter = new FileWriter(filetoWrite);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (int i=0; i<getNumberOfPoints(); i++){
                bufferedWriter.write(getTValue(i)+"\t"+getXValue(i));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}