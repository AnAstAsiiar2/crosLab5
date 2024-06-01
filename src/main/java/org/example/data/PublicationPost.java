package org.example.data;

import java.util.Date;

public class PublicationPost extends Post {
    private final String description;

    public PublicationPost(String desc, Date d) {
        super("Publication post:", d);
        this.description = desc;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getPostInfo() {
        return "Publication: " + description + ".";
    }
}
