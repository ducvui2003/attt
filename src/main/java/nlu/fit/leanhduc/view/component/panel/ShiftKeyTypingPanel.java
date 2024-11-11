package nlu.fit.leanhduc.view.component.panel;

import nlu.fit.leanhduc.controller.MainController;
import nlu.fit.leanhduc.service.ISubstitutionCipher;
import nlu.fit.leanhduc.service.key.ShiftKey;
import nlu.fit.leanhduc.view.component.SwingComponentUtil;

import javax.swing.*;
import java.awt.*;


public class ShiftKeyTypingPanel extends KeyTypingPanel<ShiftKey> {
    JFormattedTextField inputKeyLength;

    public ShiftKeyTypingPanel(MainController controller) {
        super(controller);
    }

    @Override
    public void init() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        inputKeyLength = SwingComponentUtil.createFormatTextFieldNumber(
                3, 1, null, 1,
                false, true);
        this.add(new JLabel("Độ dài khóa:"));
        this.add(inputKeyLength);
        this.setSize(400, 200);
    }

    @Override
    public ShiftKey getKey() {
        return new ShiftKey(Integer.parseInt(inputKeyLength.getText()));
    }

    @Override
    public void setKey(ShiftKey key) {
        inputKeyLength.setValue(key.getKey());
    }
}