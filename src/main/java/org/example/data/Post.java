package org.example.data;

import java.text.SimpleDateFormat;
import java.util.Date;

// Базовий клас для постів
public abstract class Post {
    private final String type;
    private final Date datePosted;

    public Post(String type, Date d) {
        this.type = type;
        this.datePosted = d;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return type +
                ", " + format.format(datePosted) +
                ".";
    }

    public String getType() {
        return type;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public abstract String getPostInfo();
}
