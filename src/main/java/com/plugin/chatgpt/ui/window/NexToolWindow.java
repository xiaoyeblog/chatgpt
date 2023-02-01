package com.plugin.chatgpt.ui.window;

import javax.swing.*;

public class NexToolWindow {

  public NexToolWindow() {

  }

  public JPanel getContent() {
    return new MainPanel().init();
  }
}
