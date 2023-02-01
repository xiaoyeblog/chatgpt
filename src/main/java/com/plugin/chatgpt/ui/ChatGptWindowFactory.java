package com.plugin.chatgpt.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.plugin.chatgpt.ui.window.MyToolWindow;
import org.jetbrains.annotations.NotNull;

/**
 * 主视窗展示工厂
 */
public class ChatGptWindowFactory implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        MyToolWindow noteListWindow = new MyToolWindow();
        ContentFactory instance = ContentFactory.SERVICE.getInstance();
        Content content = instance.createContent(noteListWindow.getContent(), "Search", false);
        toolWindow.getContentManager().addContent(content);
    }
}
