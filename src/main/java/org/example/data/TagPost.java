package org.example.data;

import java.util.Date;

public class TagPost extends Post {
    private String description;

    public TagPost(String desc, Date d) {
        super("Tag of Post", d);
        this.description = desc;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getPostInfo() {
        return "Tag of Post: " + description + ".";
    }
}
