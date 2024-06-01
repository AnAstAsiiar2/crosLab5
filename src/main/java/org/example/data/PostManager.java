package org.example.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.adapter.ClassDeserializerAdapter;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PostManager {
    private List<Post> posts;
    private final String FILE_NAME = "posts.json";
    private final Gson gson;

    public PostManager() {
        this.posts = new ArrayList<>();
        this.gson = new Gson();
        loadPosts();
    }

    public void addPost(Post post) {
        posts.add(post);
        savePosts();
    }

    public void removePost(Post post) {
        posts.remove(post);
        savePosts();
    }

    public void editPost(Post old, Post post) {
        int index = posts.indexOf(old);
        posts.set(index, post);
        savePosts();
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Post> filterByType(String type) {
        List<Post> res = new ArrayList<>();
        for (Post a : posts) {
            if (a.getType().equalsIgnoreCase(type)) {
                res.add(a);
            }
        }
        return res;
    }

    private void savePosts() {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(posts, writer);
            System.out.println("Saved posts: " + gson.toJson(posts)); // Debug
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPosts() {
        try (Reader reader = new FileReader(FILE_NAME)) {
            ClassDeserializerAdapter deserializer = new ClassDeserializerAdapter("type");
            deserializer.registerClassType("Tag of Post", TagPost.class);
            deserializer.registerClassType("Category", CategoryPost.class);
            deserializer.registerClassType("Publication post:", PublicationPost.class);
            Gson gson = new GsonBuilder().registerTypeAdapter(Post.class, deserializer).create();
            Type listType = new TypeToken<List<Post>>() {}.getType();
            posts = gson.fromJson(reader, listType);
            System.out.println("Loaded posts: " + gson.toJson(posts)); // Debug
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
