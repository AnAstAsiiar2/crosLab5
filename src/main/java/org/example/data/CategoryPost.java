package org.example.data;

import java.util.Date;

// Клас для категорійних постів
public class CategoryPost extends Post {
    private final String text;

    // Конструктор без параметра дати
    public CategoryPost(String text) {
        // Використовуємо поточну дату
        super("Category", new Date());
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String getPostInfo() {
        return "Category of Post: " + text;
    }
}
