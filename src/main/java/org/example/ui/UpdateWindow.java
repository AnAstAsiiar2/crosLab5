package org.example.ui;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.intellij.uiDesigner.core.GridConstraints;
import org.example.data.TagPost;
import org.example.data.Post;
import org.example.data.CategoryPost;
import org.example.data.PublicationPost;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;

public class UpdateWindow extends JFrame {
    private JTextField durationInput;
    private JTextField valueInput;
    private JButton updateButton;
    private JButton cancelButton;
    private JLabel contentLabel;
    private JPanel mainPanel;
    private JPanel bottomPanel;
    private JLabel dateLabel;
    private JTextArea contentArea;

    public UpdateWindow(MainWindow mainWindow, Post p) {
        String type = p.getType();
        setTitle("Update " + type);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DatePicker datePicker = null;
        if (!(p instanceof CategoryPost)) {
            DatePickerSettings settings = new DatePickerSettings();
            settings.setFormatForDatesCommonEra("yyyy-MM-dd");
            datePicker = new DatePicker(settings);
            Date d = p.getDatePosted();
            datePicker.setDate(d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            GridConstraints gridConstraints = new GridConstraints();
            gridConstraints.setRow(4);
            mainPanel.add(datePicker, gridConstraints);
        }

        add(mainPanel);
        UpdateWindow updateWindow = this;

        if (p instanceof TagPost) {
            TagPost i = (TagPost) p;
            contentArea.setText(i.getDescription());
        } else if (p instanceof CategoryPost) {
            CategoryPost t = (CategoryPost) p;
            contentArea.setText(t.getText());
        } else {
            PublicationPost v = (PublicationPost) p;
            contentArea.setText(v.getDescription());
        }

        DatePicker finalDatePicker = datePicker;
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!contentArea.getText().isBlank() && (finalDatePicker == null || !finalDatePicker.getDateStringOrEmptyString().isBlank())) {
                    String content = contentArea.getText();
                    Date date = null;

                    if (finalDatePicker != null) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            date = dateFormat.parse(finalDatePicker.getDateStringOrEmptyString());
                        } catch (ParseException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                    if (type.equalsIgnoreCase("Tag")) {
                        mainWindow.updatePost(p, new TagPost(content, date));
                    } else if (type.equalsIgnoreCase("Category")) {
                        mainWindow.updatePost(p, new CategoryPost(content));
                    } else {
                        mainWindow.updatePost(p, new PublicationPost(content, date));
                    }

                    updateWindow.setVisible(false);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateWindow.setVisible(false);
            }
        });
    }
}
