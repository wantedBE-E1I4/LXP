package com.lxp.view;

public class ViewTemplate {
    private String header;
    private String body;
    private String footer;

    public ViewTemplate(String header, String body, String footer) {
        this.header = header;
        this.body = body;
        this.footer = footer;
    }

    @Override
    public String toString() {
        return "\n=== " + header + " ===\n"
                + body + "\n"
                + "=== " + footer + " ===";
    }
}