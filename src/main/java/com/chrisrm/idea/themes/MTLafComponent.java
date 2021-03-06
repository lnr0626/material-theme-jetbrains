package com.chrisrm.idea.themes;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.config.ConfigNotifier;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.chrisrm.idea.ui.*;
import com.intellij.ide.ui.LafManager;
import com.intellij.openapi.actionSystem.impl.ActionToolbarImpl;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.impl.ApplicationImpl;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileTypes.ex.FileTypeManagerEx;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.components.JBPanel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class MTLafComponent extends JBPanel implements ApplicationComponent {

  private boolean isMaterialDesign;

  public MTLafComponent(LafManager lafManager) {
    lafManager.addLafManagerListener(source -> installMaterialComponents());
  }

  @Override
  public void initComponent() {
    installMaterialComponents();

    // Listen for changes on the settings
    ApplicationManager.getApplication().getMessageBus().connect()
                      .subscribe(ConfigNotifier.CONFIG_TOPIC, mtConfig -> this.onSettingsChanged());
  }

  @Override
  public void disposeComponent() {

  }

  @NotNull
  @Override
  public String getComponentName() {
    return this.getClass().getName();
  }

  /**
   * Called when MT Config settings are changeds
   */
  private void onSettingsChanged() {
    // Force restart if material design is switched
    restartIdeIfNecessary();

    // Trigger file icons and statuses update
    ApplicationManager.getApplication().runWriteAction(() -> {
      FileTypeManagerEx instanceEx = FileTypeManagerEx.getInstanceEx();
      instanceEx.fireFileTypesChanged();
      ActionToolbarImpl.updateAllToolbarsImmediately();
    });
  }

  /**
   * Restart IDE if necessary (ex: material design components)
   */
  private void restartIdeIfNecessary() {
    MTConfig mtConfig = MTConfig.getInstance();

    // Restart the IDE if changed
    if (mtConfig.isMaterialDesignChanged(this.isMaterialDesign)
        ) {
      String title = MaterialThemeBundle.message("mt.restartDialog.title");
      String message = MaterialThemeBundle.message("mt.restartDialog.content");

      int answer = Messages.showYesNoDialog(message, title, Messages.getQuestionIcon());
      if (answer == Messages.YES) {
        Application application = ApplicationManager.getApplication();
        if (application instanceof ApplicationImpl) {
          ((ApplicationImpl) application).restart(true);
        } else {
          application.restart();
        }
      }
    }
  }

  /**
   * Install Material Design components
   */
  private void installMaterialComponents() {
    MTConfig mtConfig = MTConfig.getInstance();
    this.isMaterialDesign = mtConfig.getIsMaterialDesign();

    if (mtConfig.getIsMaterialDesign()) {
      replaceButtons();
      //      replaceTextFields();
      replaceProgressBar();
      replaceTree();
    }
  }

  /**
   * Replace progress bar (TODO: Material Progress bar)
   */
  private void replaceProgressBar() {
    UIManager.put("ProgressBarUI", MTProgressBarUI.class.getName());
    UIManager.getDefaults().put(MTProgressBarUI.class.getName(), MTProgressBarUI.class);

    UIManager.put("ProgressBar.border", new MTProgressBarBorder());
  }

  /**
   * Replace text fields (TODO: replace password fields)
   */
  private void replaceTextFields() {
    UIManager.put("TextFieldUI", MTTextFieldUI.class.getName());
    UIManager.getDefaults().put(MTTextFieldUI.class.getName(), MTTextFieldUI.class);
  }

  /**
   * Replace buttons
   */
  private void replaceButtons() {
    UIManager.put("ButtonUI", MTButtonUI.class.getName());
    UIManager.getDefaults().put(MTButtonUI.class.getName(), MTButtonUI.class);

    UIManager.put("Button.border", new MTButtonPainter());

  }

  private void replaceTree() {
    UIManager.put("TreeUI", MTTreeUI.class.getName());
    UIManager.getDefaults().put(MTTreeUI.class.getName(), MTTreeUI.class);
  }
}
