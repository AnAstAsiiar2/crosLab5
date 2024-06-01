package org.example.ui;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.intellij.uiDesigner.core.GridConstraints;
import org.example.data.TagPost;
import org.example.data.CategoryPost;
import org.example.data.PublicationPost;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddWindow extends JFrame{
    private JTextField valueInput;
    private JButton addButton;
    private JButton cancelButton;
    private JComboBox<String> postDropDown;
    private JLabel contentLabel;
    private JPanel mainPanel;
    private JPanel bottomPanel;
    private JLabel dateLabel;
    private JTextArea contentArea;

    public AddWindow(MainWindow mainWindow) {
        setTitle("Add post");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AddWindow addWindow = this;
        DatePickerSettings settings = new DatePickerSettings();
        settings.setFormatForDatesCommonEra("yyyy-MM-dd");

        // Створення DatePicker
        DatePicker datePicker = new DatePicker(settings);
        datePicker.setDateToToday();
        //datePicker.setBounds(220,350,120,30);
        // Додавання вибору дати до головної панелі
        GridConstraints gridConstraints = new GridConstraints();
        gridConstraints.setRow(7);
        mainPanel.add(datePicker, gridConstraints);
        add(mainPanel);
        postDropDown.addItem("Tag");
        postDropDown.addItem("Category");
        postDropDown.addItem("Publication");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!contentArea.getText().isBlank() && !datePicker.getDateStringOrEmptyString().isBlank()){
                    String content = contentArea.getText();
                    String item = (String) postDropDown.getSelectedItem();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date;
                    try {
                        date = dateFormat.parse(datePicker.getDateStringOrEmptyString());
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                    if (item.equalsIgnoreCase("Tag")){
                        mainWindow.addPost(new TagPost(content, date));
                    }
                    else if (item.equalsIgnoreCase("Category")) {
                        mainWindow.addPost(new CategoryPost(content));
                    }
                    else{
                        mainWindow.addPost(new PublicationPost(content, date));
                    }
                    addWindow.setVisible(false);
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addWindow.setVisible(false);
            }
        });
    }
}
