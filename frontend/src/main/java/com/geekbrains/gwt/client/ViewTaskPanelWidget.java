package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.dtos.TaskDTO;
import com.geekbrains.gwt.common.dtos.UserDTO;
import com.geekbrains.gwt.common.entities.Task;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ViewTaskPanelWidget extends Composite {
    @UiField
    FormPanel form;

    @UiField
    TextBox idText;

    @UiField
    Label idLabel;

    @UiField
    TextBox captionText;

    @UiField(provided = true)
    ListBox ownerList = new ListBox();

    @UiField(provided = true)
    ListBox assignedList = new ListBox();

    @UiField(provided = true)
    ValueListBox<String> statusList = new ValueListBox<String>(new Renderer<String>() {
        @Override
        public String render(String object) {
            return object;
        }

        @Override
        public void render(String object, Appendable appendable)
                throws IOException {
            if (object != null) {
                render(object);
            }
        }
    });

    @UiField
    TextBox descriptionText;

    @UiField
    Button saveButton;

    @UiTemplate("ViewTaskPanelWidget.ui.xml")
    interface ViewTaskPanelBinder extends UiBinder<Widget, ViewTaskPanelWidget> {
    }

    PopupPanel popup = new PopupPanel(true);

    private UserClient userClient;

    private TaskTableWidget taskTableWidget;

    private static ViewTaskPanelWidget.ViewTaskPanelBinder uiBinder = GWT.create(ViewTaskPanelWidget.ViewTaskPanelBinder.class);

    public ViewTaskPanelWidget(TaskDTO task, boolean readOnly, TaskTableWidget taskTableWidget) {
        String token = Storage.getLocalStorageIfSupported().getItem("jwt");
        GWT.log("STORAGE: " + token);

        this.taskTableWidget = taskTableWidget;
        this.initWidget(uiBinder.createAndBindUi(this));

        this.idText.setReadOnly(true);

        if (readOnly) {
            this.captionText.setReadOnly(true);
            this.ownerList.setEnabled(false);
            this.assignedList.setEnabled(false);
            this.statusList.setEnabled(false);
            this.descriptionText.setReadOnly(true);
            this.saveButton.setVisible(false);
        }

        if (task.getId() != null) {

            this.idText.setValue(task.getId().toString());
            this.captionText.setValue(task.getCaption());
            this.statusList.setValue(task.getStatus().toString());
            this.descriptionText.setValue(task.getDescription());
            this.form.setAction(Defaults.getServiceRoot().concat("tasks").concat("/put"));
            this.form.setMethod("POST");
        } else {
            this.idLabel.setVisible(false);
            this.idText.setVisible(false);
            this.form.setAction(Defaults.getServiceRoot().concat("tasks").concat("/post"));
            this.form.setMethod("POST");
        }

        List<String> statusVals = Stream.of(Task.Status.values())
                .map(Task.Status::name)
                .collect(Collectors.toList());
        this.statusList.setValue("");
        this.statusList.setAcceptableValues(statusVals);

        userClient = GWT.create(UserClient.class);

        userClient.getInitiators(token, new MethodCallback<List<UserDTO>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
                Window.alert("Невозможно получить список Initiators: Сервер не отвечает");
            }

            @Override
            public void onSuccess(Method method, List<UserDTO> i) {
                GWT.log("Received " + i.size() + " Initiators");
                ownerList.addItem(" - Не выбрано - ", "-1");
                for(UserDTO userDTO : i) {
                    ownerList.addItem(userDTO.getUsername(), userDTO.getId().toString());
                    for (int j = 0; j < ownerList.getItemCount(); j++) {
                        if (ownerList.getValue(j).equals(task.getOwner().toString())) {
                            GWT.log(j + " : " + ownerList.getValue(j));
                            ownerList.setSelectedIndex(j);
                            break;
                        }
                    }
                }
            }
        });

        userClient.getExecutors(token, new MethodCallback<List<UserDTO>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
                Window.alert("Невозможно получить список Initiators: Сервер не отвечает");
            }

            @Override
            public void onSuccess(Method method, List<UserDTO> i) {
                GWT.log("Received " + i.size() + " Initiators");
                assignedList.addItem(" - Не выбрано - ", "-1");
                for(UserDTO userDTO : i) {
                    assignedList.addItem(userDTO.getUsername(), userDTO.getId().toString());
                    for (int j = 0; j < assignedList.getItemCount(); j++) {
                        if (assignedList.getValue(j).equals(task.getAssigned().toString())) {
                            assignedList.setSelectedIndex(j);
                            break;
                        }
                    }
                }
            }
        });

    }

    public void show () {
        popup.clear();
        popup.add(this);
        popup.show();
        popup.center();
    }

    public void hide () {
        popup.hide();
    }

    @UiHandler("closeButton")
    public void closeButtonClick(ClickEvent event) {
        this.hide();
    }

    @UiHandler("saveButton")
    public void saveButtonClick(ClickEvent event) {
        this.ownerList.setName("owner");
        this.assignedList.setName("assigned");
        this.form.submit();
    }

    @UiHandler("form")
    public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
        taskTableWidget.refresh();
        this.hide();
    }
}
