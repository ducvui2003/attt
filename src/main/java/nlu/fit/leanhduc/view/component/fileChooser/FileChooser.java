package nlu.fit.leanhduc.view.component.fileChooser;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import nlu.fit.leanhduc.util.Constraint;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileChooser extends JPanel {
    JFileChooser fileChooser;
    JLabel label;
    JButton button;
    FileChooserEvent event;
    boolean onlyBtn;
    String textBtn = "Chọn file";
    Border combinedBorder;

    public FileChooser(Border borderFactory) {
        this.combinedBorder = borderFactory;
        this.onlyBtn = false;
        init();
    }

    public FileChooser() {
        combinedBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );
        this.onlyBtn = false;
        init();
    }

    public FileChooser(boolean onlyBtn, String textBtn) {
        this.onlyBtn = true;
        this.textBtn = textBtn;
        init();
    }

    private void init() {
        this.setLayout(new BorderLayout(5, 5));
        createButton();
        if (!onlyBtn) {
            createLabel();
            this.add(label, BorderLayout.CENTER);
        }
        this.add(button, BorderLayout.EAST);
        this.setBorder(combinedBorder);
    }

    private void createLabel() {
        label = new JLabel();
        label.setPreferredSize(new Dimension(100, 30));
        label.setFont(Constraint.FONT_MEDIUM);
    }

    private void createButton() {
        button = new JButton(textBtn);
        button.addActionListener(e -> click());
    }

    private void click() {
        if (!this.event.onBeforeFileSelected()) {
            return;
        }
        fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);

        switch (option) {
            case JFileChooser.APPROVE_OPTION:
                File selectedFile = fileChooser.getSelectedFile();
                String name = selectedFile.getName();
                if (label != null)
                    label.setText(name);
                this.event.autoAddExtension(selectedFile);
                this.event.onFileSelected(selectedFile);
                break;
            case JFileChooser.CANCEL_OPTION:
                this.event.onFileUnselected();
                break;
            case JFileChooser.ERROR_OPTION:
                this.event.onError("Error");
                break;
        }
    }

    public void cancel() {
        label.setText("");
    }
}