package com.plugin.chatgpt.ui.window;

import com.intellij.find.SearchTextArea;
import com.intellij.ui.components.JBTextArea;
import com.intellij.util.ui.UIUtil;
import com.plugin.chatgpt.util.HttpUtil;

import javax.swing.*;
import java.awt.*;

public class MainPanel {

    private final JPanel mainPanel;

    private final SearchTextArea searchTextArea;
    private final JButton button;

    private final JLabel loadingLabel;

    private final ImageIcon loadingImageIcon = new ImageIcon(HttpUtil.class.getResource("/icons/loading.gif"));

    public MainPanel() {
        SendListener listener = new SendListener(this);

        searchTextArea = new SearchTextArea(new JBTextArea(), true, true);
        searchTextArea.getTextArea().addKeyListener(listener);

        button = new JButton("Send");
        button.addActionListener(listener);

        JPanel top = new JPanel(new BorderLayout());
        top.add(searchTextArea, BorderLayout.CENTER);
        top.add(button, BorderLayout.EAST);
        top.setBorder(UIUtil.getTextFieldBorder());
//        JBHtmlEditorKit jbHtmlEditorKit = new JBHtmlEditorKit();
//        JCEFHtmlPanel jcefHtmlPanel = new JCEFHtmlPanel();
//        MarkdownUtil markdownUtil = new MarkdownUtil();
//        jcefHtmlPanel.setHtml();
//        contentPanel = new JTextPane();
//        String usage = ("欢迎使用ChatGpt");
//        contentPanel.setText(usage);

        //JScrollPane jScrollPane = new JScrollPane();

        //jScrollPane.add(contentPanel);
        //jScrollPane.add(contentPanel);
        //jScrollPane.add(loadingLabel);

        loadingLabel = new JLabel("Loading...", loadingImageIcon, JLabel.CENTER);


        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(top, BorderLayout.NORTH);
        mainPanel.add(new JLabel("Welcome to ChatGPT!", JLabel.CENTER), BorderLayout.CENTER);
    }

    public SearchTextArea getSearchTextArea() {
        return searchTextArea;
    }

    public JPanel init() {
        return mainPanel;
    }

    public JButton getButton() {
        return button;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JLabel getLoadingLabel() {
        return loadingLabel;
    }
}
