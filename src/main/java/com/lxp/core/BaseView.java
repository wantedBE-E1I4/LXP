package com.lxp.core;

import java.util.List;

public abstract class BaseView {
    public abstract void show();

    // Header Method
    protected void printHeader(String title) {
        System.out.println("== " + title + " ==");
    }

    // Body Method
    protected void printBody(List<String> messages) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < messages.size(); i++) {
            sb.append(i+1).append(".").append(messages.get(i)).append("\n");
        }
        System.out.println(sb);
    }

    // Footer Method
    protected void printFooter(String message) {
        System.out.println(message);
    }

    // Other Method
    protected void closeComment() {
        System.out.println("종료하시려면 'Q'를 눌러죽세요.");
    }
    protected void printBlankLine() {
        System.out.println("");
    }

}
