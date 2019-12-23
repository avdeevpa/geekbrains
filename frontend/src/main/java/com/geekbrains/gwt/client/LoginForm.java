package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.dtos.JwtAuthRequestDto;
import com.geekbrains.gwt.common.dtos.JwtAuthResponseDto;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

public class LoginForm extends Composite {
    @UiField
    FormPanel form;

    @UiField
    TextBox textUsername;

    @UiField
    TextBox textPassword;

    @UiField
    VerticalPanel formLogin;

    @UiField
    VerticalPanel formLogout;

    @UiTemplate("LoginForm.ui.xml")
    interface LoginFormBinder extends UiBinder<Widget, LoginForm> {
    }

    private TaskTableWidget taskTableWidget;
    private FilterTaskFormWidget filterTaskFormWidget;
    private TabLayoutPanel tabPanel;

    private static LoginForm.LoginFormBinder uiBinder = GWT.create(LoginForm.LoginFormBinder.class);

    public LoginForm(TabLayoutPanel tabPanel, TaskTableWidget taskTableWidget, FilterTaskFormWidget filterTaskFormWidget) {
        this.initWidget(uiBinder.createAndBindUi(this));
        this.form.setAction(Defaults.getServiceRoot().concat("items"));

        String token = Storage.getLocalStorageIfSupported().getItem("jwt");
        if (token == null) {
            this.formLogin.setVisible(true);
            this.formLogout.setVisible(false);
        } else {
            this.formLogin.setVisible(false);
            this.formLogout.setVisible(true);
        }

        this.taskTableWidget = taskTableWidget;
        this.filterTaskFormWidget = filterTaskFormWidget;
        this.tabPanel = tabPanel;
    }

    @UiHandler("form")
    public void onSubmit(FormPanel.SubmitEvent event) {
    }

    @UiHandler("form")
    public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
        Window.alert(event.getResults());
    }

    @UiHandler("btnSubmit")
    public void submitClick(ClickEvent event) {
        JwtAuthRequestDto jwtAuthRequestDto = new JwtAuthRequestDto(textUsername.getValue(), textPassword.getValue());
        AuthClient authClient = GWT.create(AuthClient.class);
        authClient.authenticate(jwtAuthRequestDto, new MethodCallback<JwtAuthResponseDto>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(method.getResponse().getText());
            }

            @Override
            public void onSuccess(Method method, JwtAuthResponseDto jwtAuthResponseDto) {
                GWT.log(jwtAuthResponseDto.getToken());
                Storage.getLocalStorageIfSupported().setItem("jwt", "Bearer " +  jwtAuthResponseDto.getToken());

                formLogin.setVisible(false);
                formLogout.setVisible(true);

                taskTableWidget.refresh();
                filterTaskFormWidget.init();
                tabPanel.selectTab(1);
            }
        });
    }

    @UiHandler("btnLogout")
    public void logoutClick(ClickEvent event) {
        AuthClient authClient = GWT.create(AuthClient.class);
        authClient.logout(new MethodCallback<JwtAuthResponseDto>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(method.getResponse().getText());
                Storage.getLocalStorageIfSupported().clear();
                formLogin.setVisible(true);
                formLogout.setVisible(false);
            }

            @Override
            public void onSuccess(Method method, JwtAuthResponseDto jwtAuthResponseDto) {
                GWT.log(method.getResponse().getText());
                Storage.getLocalStorageIfSupported().clear();
                formLogin.setVisible(true);
                formLogout.setVisible(false);
            }
        });
    }
}