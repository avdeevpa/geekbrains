package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.dtos.RoleDTO;
import com.geekbrains.gwt.common.dtos.TaskDTO;
import com.geekbrains.gwt.common.dtos.UserDTO;
import com.geekbrains.gwt.common.entities.Task;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.CompositeCell;
import com.google.gwt.cell.client.HasCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class UserTableWidget extends Composite {
    @UiField
    CellTable<UserDTO> table;

    @UiTemplate("UserTable.ui.xml")
    interface UserTableBinder extends UiBinder<Widget, UserTableWidget> {
    }

    private UserClient userClient;

    private static UserTableBinder uiBinder = GWT.create(UserTableBinder.class);

    private UserTableWidget userTableWidget;

    private String token;

    public UserTableWidget() {
        String token =
                Storage.getLocalStorageIfSupported().getItem("jwt") != null ?
                        Storage.getLocalStorageIfSupported().getItem("jwt") :
                "empty";
        this.token = token;
        GWT.log("STORAGE: " + token);

        userTableWidget = this;
        initWidget(uiBinder.createAndBindUi(this));

        userClient = GWT.create(UserClient.class);

        TextColumn<UserDTO> idColumn = new TextColumn<UserDTO>() {
            @Override
            public String getValue(UserDTO userDTO) {
                return userDTO.getId().toString();
            }
        };
        table.addColumn(idColumn, "ID");

        TextColumn<UserDTO> usernameColumn = new TextColumn<UserDTO>() {
            @Override
            public String getValue(UserDTO userDTO) {
                return userDTO.getUsername();
            }
        };
        table.addColumn(usernameColumn, "Username");

        TextColumn<UserDTO> rolesColumn = new TextColumn<UserDTO>() {
            @Override
            public String getValue(UserDTO userDTO) {
                String roles = "";
                for (RoleDTO r : userDTO.getRoles()) {
                    roles += r.getName() + "; ";
                }
                return roles;
            }
        };
        table.addColumn(rolesColumn, "Roles");

        table.setColumnWidth(idColumn, 50, Style.Unit.PX);
        table.setColumnWidth(usernameColumn, 200, Style.Unit.PX);
        table.setColumnWidth(rolesColumn, 100, Style.Unit.PX);

        refresh();
    }

    public void refresh() {
        String token =
                Storage.getLocalStorageIfSupported().getItem("jwt") != null ?
                        Storage.getLocalStorageIfSupported().getItem("jwt") :
                        "empty";
        GWT.log("STORAGE: " + token);

        userClient.getAllUsers(token, new MethodCallback<List<UserDTO>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
            }

            @Override
            public void onSuccess(Method method, List<UserDTO> userDTOS) {
                GWT.log("Received " + userDTOS.size() + " users");
                List<UserDTO> users = new ArrayList<>();
                users.addAll(userDTOS);
                table.setRowData( users );
            }
        });
    }

}
