package com.plugin.chatgpt.ui.window;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * java类简单作用描述
 */
public class ChatGptListWindow {
    private JTextField searchText;
    private JPanel contentPanel;
    private JButton searchButton;
    private JTextPane searchResultTextPane;

    public ChatGptListWindow(Project project, ToolWindow toolWindow) {

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // todo 增加chatGPT的对接
                try {
                    //String answer = HttpUtil.get(searchText.getText());
                    //searchResultTextPane.setText(searchText.getText() + "搜索结果：" + answer);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    public JTextField getSearchText() {
        return searchText;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
