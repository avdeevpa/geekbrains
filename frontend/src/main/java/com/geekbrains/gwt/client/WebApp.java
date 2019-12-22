package com.geekbrains.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.*;

public class WebApp implements EntryPoint {
    public void onModuleLoad() {
        Defaults.setServiceRoot("http://localhost:8189/gwt-rest");
        TabLayoutPanel tabPanel = new TabLayoutPanel(2.5, Style.Unit.EM);
        tabPanel.setAnimationDuration(100);
        tabPanel.getElement().getStyle().setMarginBottom(10.0, Style.Unit.PX);

        //Tasks
        TaskTableWidget taskTableWidget = new TaskTableWidget();
        FilterTaskFormWidget filterTaskFormWidget = new FilterTaskFormWidget(taskTableWidget);
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(filterTaskFormWidget);
        verticalPanel.add(taskTableWidget);

        // Login
        LoginForm loginForm = new LoginForm(tabPanel, taskTableWidget, filterTaskFormWidget);

        // Users
        UserTableWidget userTableWidget = new UserTableWidget();

        // Composition
        tabPanel.add(loginForm, "Session");

        tabPanel.add(verticalPanel, "Task control");
        tabPanel.setHeight("800px");

        tabPanel.add(userTableWidget, "User control");
        tabPanel.setHeight("800px");

        tabPanel.selectTab(0);
        tabPanel.ensureDebugId("cwTabPanel");

        RootPanel.get().add(tabPanel);
    }
}