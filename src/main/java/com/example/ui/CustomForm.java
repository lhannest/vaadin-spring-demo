package com.example.ui;

import com.vaadin.server.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomForm extends FormLayout {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public CustomForm() {
        super();

        setSizeUndefined();

        TextField nameField = new TextField("Name");
        addComponent(nameField);

        TextField ageField = new TextField("Age");
        addComponent(ageField);

        TextField emailField = new TextField("Email");
        emailField.setRequiredIndicatorVisible(true);
        emailField.addValueChangeListener(event -> {
            if (event.getValue().isEmpty()) {
                emailField.setComponentError(new UserError("Cannot be empty"));
            } else {
                if (validate(event.getValue().trim())) {
                    emailField.setComponentError(null);
                } else {
                    emailField.setComponentError(new UserError("Must be an email"));
                }
            }
        });

        addComponent(emailField);

        Button submitBtn = new Button("Submit");
        addComponent(submitBtn);
        submitBtn.addClickListener(event -> {
            Notification.show(
                    "Name: " + nameField.getValue()
                    + "\nAge: " + ageField.getValue()
                    + "\nEmail: " + emailField.getValue()
            );

            if (emailField.getValue().isEmpty()) {
                emailField.setComponentError(new UserError("Cannot be empty"));
            }
        });
    }

}