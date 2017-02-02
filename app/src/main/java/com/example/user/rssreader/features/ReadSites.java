package com.example.user.rssreader.features;

import java.util.ArrayList;

/**
 * Created by User on 01.02.2017.
 */

public class ReadSites {

    /**
     * Class containing the read lines
     * */

    private ArrayList<String> readSitesBuffer;

    public ReadSites(){
        this.readSitesBuffer = new ArrayList<String>();
    }

    public void addReadTitle(String name){
        readSitesBuffer.add(name);
    }

    public void clearReadTitles(){
        readSitesBuffer.clear();
    }

    public ArrayList<String> getReadSitesBuffer(){
        return readSitesBuffer;
    }

    public boolean contains(String in){
        for(String s: readSitesBuffer){
            if(s.equals(in)){
                return true;
            }
        }
        return false;
    }

    public void setReadSitesBuffer(ArrayList<String> readSitesBuffer) {
        this.readSitesBuffer = readSitesBuffer;
    }
}
