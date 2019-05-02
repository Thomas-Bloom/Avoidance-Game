package com.tombloom.game;

import java.io.*;
import java.util.Properties;

public class PropertiesManager {
    private static final String PATH = "config.properties";
    private int height;
    private int width;
    private float fpsLimit;

    public PropertiesManager(){
        File file = new File(PATH);

        if(file.exists()){
            loadProperties();
        }
        else{
            createProperties();
            loadProperties();
        }
    }

    private void loadProperties(){
        File file = new File(PATH);

        try{
            FileReader reader = new FileReader(file);
            Properties properties = new Properties();
            properties.load(reader);

            height = Integer.valueOf(properties.getProperty("height"));
            width = Integer.valueOf(properties.getProperty("width"));
            fpsLimit = Float.valueOf(properties.getProperty("fpsLimit"));

            reader.close();
        }
        catch(FileNotFoundException e){
            System.err.println("File does not exist");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void createProperties(){
        File file = new File(PATH);

        try{
            Properties properties = new Properties();

            properties.setProperty("width", "1280");
            properties.setProperty("height", "720");
            properties.setProperty("fpsLimit", "60.0");

            FileWriter writer = new FileWriter(file);
            properties.store(writer, "Game Settings");
            writer.close();
        }
        catch(FileNotFoundException e){
            System.err.println("File not found");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }

    float getFpsLimit() {
        return fpsLimit;
    }
}
