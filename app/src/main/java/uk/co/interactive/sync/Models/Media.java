package uk.co.interactive.sync.models;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by javiergonzalezcabezas on 9/6/15.
 */
public class Media implements Serializable {
    @Expose
    private String m;

    /**
     *
     * @return
     * The m
     */
    public String getM() {
        return m;
    }

    /**
     *
     * @param m
     * The m
     */
    public void setM(String m) {
        this.m = m;
    }

}
