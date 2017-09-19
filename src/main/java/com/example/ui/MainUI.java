package com.example.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;

import java.time.LocalDate;

@SpringUI
public class MainUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout layout = new VerticalLayout();
        setContent(layout);
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

        layout.addComponent(new Label("Hello world! Please give me your information."));

        layout.addComponent(new CustomForm());

        DateField date = new DateField();
        date.setValue(LocalDate.now());
        layout.addComponent(date);

    }
}