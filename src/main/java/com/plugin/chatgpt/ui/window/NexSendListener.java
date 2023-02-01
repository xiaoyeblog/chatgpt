package com.plugin.chatgpt.ui.window;

import com.intellij.ui.components.JBScrollPane;
import com.plugin.chatgpt.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NexSendListener implements ActionListener, KeyListener {
    private final MainPanel mainPanel;

    private final ImageIcon loadingImageIcon = new ImageIcon(HttpUtil.class.getResource("/icons/loading.gif"));

    /**
     * 防止enter键重复点击
     */
    private boolean enterKeyEffect = true;

    public NexSendListener(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doActionPerformed();
    }

    public void doActionPerformed() {
        String text = mainPanel.getSearchTextArea().
                getTextArea().getText();
        if (StringUtils.isBlank(text)) {
            return;
        }
        enterKeyEffect = false;
        JButton button = mainPanel.getButton();
        button.setEnabled(false);

        JLabel loadingLabel = new JLabel("Loading...", loadingImageIcon, JLabel.CENTER);
        JPanel mainPanelContent = mainPanel.getMainPanel();
        mainPanelContent.remove(1);
        mainPanelContent.add(loadingLabel, BorderLayout.CENTER);
        mainPanelContent.doLayout();
        new Thread(() -> {
            try {
                JBScrollPane resultPane = new JBScrollPane();
                JTextArea answer = new JTextArea();
                answer.setLineWrap(true);
                answer.setWrapStyleWord(true);
                resultPane.setViewportView(answer);
                mainPanelContent.add(resultPane);
                mainPanelContent.doLayout();
                HttpUtil.showResult(text, mainPanelContent, answer);
            } catch (Exception e) {
                mainPanelContent.remove(1);
                mainPanelContent.remove(1);
                mainPanelContent.add(new JLabel("request timeout,try again later", JLabel.CENTER), BorderLayout.CENTER);
                mainPanelContent.doLayout();
            } finally {
                button.setEnabled(true);
                enterKeyEffect = true;
            }
        }).start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER && enterKeyEffect) {
            e.consume();
            doActionPerformed();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
