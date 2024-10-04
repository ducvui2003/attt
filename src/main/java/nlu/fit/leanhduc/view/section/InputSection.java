package nlu.fit.leanhduc.view.section;

import nlu.fit.leanhduc.component.fileChooser.FileChooser;
import nlu.fit.leanhduc.component.fileChooser.FileChooserEvent;
import nlu.fit.leanhduc.controller.EncryptPublisher;
import nlu.fit.leanhduc.controller.TaskOperation;
import nlu.fit.leanhduc.controller.TaskPublisher;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;

public class InputSection extends JPanel implements FileChooserEvent {
    FileChooser fileChooser;
    TaskPublisher encryptPublisher;

    public InputSection() {
        init();
    }

    private void init() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        fileChooser = new FileChooser();
        fileChooser.setEvent(this);
        this.add(fileChooser);
        Border border = BorderFactory.createTitledBorder("Input");
        this.setBorder(border);
        encryptPublisher = new EncryptPublisher();
    }

    @Override
    public void onFileSelected(File file) {
        encryptPublisher.doTask();
    }

    @Override
    public void onFileUnselected() {
        fileChooser.cancel();
    }

    @Override
    public void onError(String message) {

    }

}
