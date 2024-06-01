package org.example.ui;

import org.example.data.Post;
import org.example.data.PostManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainWindow extends JFrame {
    private JPanel mainPanel;
    private JList postList;
    private JButton addButton;
    private JButton detailButton;
    private JButton editButton;
    private JButton deleteButton;
    private JPanel bottomPanel;
    private JScrollPane scrollPane;
    private final PostManager postCollection;

    public MainWindow(PostManager postCollection) {
        this.postCollection = postCollection;
        MainWindow mainWindow = this;
        setTitle("All Posts");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        refreshList();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddWindow addWindow = new AddWindow(mainWindow);
                addWindow.setVisible(true);
            }
        });
        detailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DetailWindow detailWindow = new DetailWindow(postCollection);
                detailWindow.setVisible(true);

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Post p = (Post) postList.getSelectedValue();
                deletePost(p);
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Post p = (Post) postList.getSelectedValue();
                UpdateWindow updateWindow = new UpdateWindow(mainWindow, p);
                updateWindow.setVisible(true);
            }
        });
        add(mainPanel);
    }
    public void addPost(Post p){
        postCollection.addPost(p);
        refreshList();
    }
    public void refreshList(){
        List<Post> list = postCollection.getPosts();
        Post[] posts = new Post[list.size()];
        for (int i = 0; i < list.size(); i++){
            posts[i] = list.get(i);
        }
        postList.setListData(posts);
        if(list.size() == 0)
            postList.setListData(new Object[]{"No elements."});
    }
    private void deletePost(Post p){
        postCollection.removePost(p);
        refreshList();
    }
    public void updatePost(Post old, Post newP){
        postCollection.editPost(old, newP);
        refreshList();
    }
}
