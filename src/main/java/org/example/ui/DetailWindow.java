package org.example.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import org.example.data.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DetailWindow extends JFrame {
    private JList<Object> postList;
    private JComboBox<String> postDropDown;
    private JButton OKButton;
    private JButton cancelButton;
    private JPanel bottomPanel;
    private JPanel mainPanel;
    private JScrollPane listScroll;
    private JPanel parametersPanel;
    private List<Post> list;

    public DetailWindow(PostManager postCollection) {
        DetailWindow detailWindow = this;
        setTitle("Detail post");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridConstraints gridConstraints = new GridConstraints();
        gridConstraints.setRow(1);

        add(mainPanel);
        postList.setListData(new Object[]{"No elements."});

        postDropDown.addItem("Tag of Post");
        postDropDown.addItem("Category");
        postDropDown.addItem("Publication post:");

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = (String) postDropDown.getSelectedItem();
                list = postCollection.filterByType(item);
                refreshList();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailWindow.setVisible(false);
            }
        });
    }

    private void refreshList() {
        String[] activities = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Post p = list.get(i);
            if (p instanceof TagPost) {
                TagPost c = (TagPost) p;
                activities[i] = c.getPostInfo();
            } else if (p instanceof CategoryPost) {
                CategoryPost t = (CategoryPost) p;
                activities[i] = t.getPostInfo();
            } else if (p instanceof PublicationPost) {
                PublicationPost v = (PublicationPost) p;
                activities[i] = v.getPostInfo();
            }
        }
        postList.setListData(activities);
        if (list.size() == 0) {
            postList.setListData(new Object[]{"No elements."});
        }
    }
}
