package com.chrisrm.idea.config.ui;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.MTTheme;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.intellij.ide.highlighter.HtmlFileType;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import com.intellij.openapi.ui.*;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.intellij.images.fileTypes.ImageFileTypeManager;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MTForm implements MTFormUI {
  private SpinnerModel highlightSpinnerModel;

  @Override
  public void init() {
    MTConfig config = MTConfig.getInstance();
    highlightSpinnerModel = new SpinnerNumberModel(config.getHighlightThickness(), 1, 5, 1);
    highlightSpinner.setModel(highlightSpinnerModel);
  }

  @Override
  public JComponent getContent() {
    return content;
  }

  @Override
  public void afterStateSet() {

  }

  @Override
  public void dispose() {
    checkBoxWithColorChooserImpl.dispose();
  }

  public Color getHighlightColor() {
    return checkBoxWithColorChooserImpl.getColor();
  }

  public void setHighlightColor(@NotNull Color highlightColor) {
    checkBoxWithColorChooserImpl.setColor(highlightColor);
  }

  public boolean getHighlightColorEnabled() {
    return checkBoxWithColorChooserImpl.isSelected();
  }

  public void setHighlightColorEnabled(boolean enabled) {
    checkBoxWithColorChooserImpl.setSelected(enabled);
  }

  public Integer getHighlightThickness() {
    return (Integer) highlightSpinnerModel.getValue();
  }

  public void setHighlightThickness(Integer highlightThickness) {
    highlightSpinnerModel.setValue(highlightThickness);
  }

  public boolean getIsContrastMode() {
    return isContrastModeCheckbox.isSelected();
  }

  public void setIsContrastMode(boolean isContrastMode) {
    isContrastModeCheckbox.setSelected(isContrastMode);
  }

  public boolean getIsMaterialDesign() {
    return isMaterialDesignCheckbox.isSelected();
  }

  public void setIsMaterialDesign(boolean isMaterialDesign) {
    this.isMaterialDesignCheckbox.setSelected(isMaterialDesign);
  }

  public boolean getIsBoldTabs() {
    return this.boldTabs.isSelected();
  }

  public void setIsBoldTabs(boolean isBold) {
    this.boldTabs.setSelected(isBold);
  }

  public void setCustomWallpaper(String wallpaper) {
    this.customBgChooser.setText(wallpaper);
  }

  public String getWallpaper() {
    return this.customBgChooser.getText();
  }

  public void setIsUseMaterialIcons(boolean useMaterialIcons) {
    this.isMaterialIconsCheckbox.setSelected(useMaterialIcons);
    enableDisableFileIcons(useMaterialIcons);
  }

  public boolean isUseMaterialIcons() {
    return this.isMaterialIconsCheckbox.isSelected();
  }

  public void setUseProjectViewDecorators(boolean useProjectViewDecorators) {
    this.isProjectViewDecoratorsCheckbox.setSelected(useProjectViewDecorators);
  }

  public boolean getUseProjectViewDecorators() {
    return this.isProjectViewDecoratorsCheckbox.isSelected();
  }

  public void setHideFileIcons(boolean hideFileIcons) {
    this.hideFileIconsCheckbox.setSelected(hideFileIcons);
  }

  public boolean getHideFileIcons() {
    return hideFileIconsCheckbox.isSelected();
  }

  public boolean getIsWallpaperSet() {
    return this.isWallpaperSetCheckbox.isSelected();
  }

  public void setIsWallpaperSet(boolean isWallpaperSet) {
    this.isWallpaperSetCheckbox.setSelected(isWallpaperSet);
    enableDisableCustomBg(isWallpaperSet);
  }

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner Evaluation license - Mario Smilax
  private JPanel content;
  private CheckBoxWithColorChooserImpl checkBoxWithColorChooserImpl;
  private JSpinner highlightSpinner;
  private JButton resetTabDefaultsBtn;
  private JCheckBox boldTabs;
  private JCheckBox isContrastModeCheckbox;
  private JCheckBox hideFileIconsCheckbox;
  private JCheckBox isCompactSidebarCheckbox;
  private JCheckBox isWallpaperSetCheckbox;
  private JLabel customBgLabel;
  private TextFieldWithBrowseButton customBgChooser;
  private JButton customBgRestoreButton;
  private JCheckBox isMaterialDesignCheckbox;
  private JCheckBox isMaterialIconsCheckbox;
  private JCheckBox isProjectViewDecoratorsCheckbox;
  private JCheckBox isThemeInStatusCheckbox;
  // JFormDesigner - End of variables declaration  //GEN-END:variables

  public MTForm() {

    initComponents();

    // Reset tab defaults
    resetTabDefaultsBtn.addActionListener(e -> {
      final MTTheme mtTheme = MTConfig.getInstance().getSelectedTheme();
      Color borderColor = mtTheme.getBorderColor();
      int thickness = mtTheme.getBorderThickness();

      this.setHighlightColor(borderColor);
      this.setHighlightColorEnabled(false);
      this.setHighlightThickness(thickness);
      this.setIsBoldTabs(false);
    });

    // Image file chooser
    customBgChooser.addBrowseFolderListener(MaterialThemeBundle.message("mt.customBgChooser.title"),
        MaterialThemeBundle.message("mt.customBgChooser.message"),
        null,
        FileChooserDescriptorFactory.createSingleFileDescriptor(ImageFileTypeManager.getInstance().getImageFileType()),
        TextComponentAccessor.TEXT_FIELD_SELECTED_TEXT);
  }

  public void setIsCompactSidebar(boolean compactSidebar) {
    this.isCompactSidebarCheckbox.setSelected(compactSidebar);
  }

  public boolean isCompactSidebar() {
    return this.isCompactSidebarCheckbox.isSelected();
  }

  public void setIsStatusBarTheme(boolean statusBarTheme) {
    this.isThemeInStatusCheckbox.setSelected(statusBarTheme);
  }

  public boolean isStatusBarTheme() {
    return this.isThemeInStatusCheckbox.isSelected();
  }

  private void enableDisableCustomBg(boolean isWallpaperSet) {
    this.customBgLabel.setEnabled(isWallpaperSet);
    this.customBgChooser.setEnabled(isWallpaperSet);
    this.customBgRestoreButton.setEnabled(isWallpaperSet);
  }

  private void enableDisableFileIcons(boolean isMaterialIconsSet) {
    this.hideFileIconsCheckbox.setEnabled(isMaterialIconsSet);
  }


  private void createUIComponents() {
    checkBoxWithColorChooserImpl = new CheckBoxWithColorChooserImpl(MaterialThemeBundle.message("mt.activetab.text"));
  }

  //region Events - Actions Listeners

  /**
   * Enable/disable custom background section
   *
   * @param e
   */
  private void isWallpaperSetCheckboxActionPerformed(ActionEvent e) {
    this.enableDisableCustomBg(this.isWallpaperSetCheckbox.isSelected());
  }

  private void customBgRestoreButtonActionPerformed(ActionEvent e) {
    this.customBgChooser.setText(MTConfig.DEFAULT_BG);
  }

  /**
   * @param e
   */
  private void isMaterialIconsCheckboxActionPerformed(ActionEvent e) {
    this.enableDisableFileIcons(this.isMaterialIconsCheckbox.isSelected());
  }
  //endregion

  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - Mario Smilax
    createUIComponents();

    ResourceBundle bundle = ResourceBundle.getBundle("messages.MaterialThemeBundle");
    content = new JPanel();
    JPanel panel1 = new JPanel();
    Spacer hSpacer1 = new Spacer();
    JLabel label1 = new JLabel();
    highlightSpinner = new JSpinner();
    Spacer vSpacer1 = new Spacer();
    resetTabDefaultsBtn = new JButton();
    boldTabs = new JCheckBox();
    Spacer vSpacer2 = new Spacer();
    JPanel panel2 = new JPanel();
    isContrastModeCheckbox = new JCheckBox();
    Spacer hSpacer2 = new Spacer();
    hideFileIconsCheckbox = new JCheckBox();
    isCompactSidebarCheckbox = new JCheckBox();
    Spacer vSpacer3 = new Spacer();
    JPanel panel3 = new JPanel();
    isWallpaperSetCheckbox = new JCheckBox();
    customBgLabel = new JLabel();
    customBgChooser = new TextFieldWithBrowseButton();
    customBgRestoreButton = new JButton();
    isMaterialDesignCheckbox = new JCheckBox();
    isMaterialIconsCheckbox = new JCheckBox();
    isProjectViewDecoratorsCheckbox = new JCheckBox();
    isThemeInStatusCheckbox = new JCheckBox();

    //======== content ========
    {
      content.setAutoscrolls(true);
      content.setRequestFocusEnabled(false);
      content.setVerifyInputWhenFocusTarget(false);
      content.setBorder(null);

      // JFormDesigner evaluation mark
      content.setBorder(new javax.swing.border.CompoundBorder(
          new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
              "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
              javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
              java.awt.Color.red), content.getBorder()));

      content.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));

      //======== panel1 ========
      {
        panel1.setBorder(new TitledBorder(new EtchedBorder(), bundle.getString("mt.activetab")));
        panel1.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));

        //---- checkBoxWithColorChooserImpl ----
        checkBoxWithColorChooserImpl.setDoubleBuffered(true);
        checkBoxWithColorChooserImpl.setToolTipText(bundle.getString("mt.activetab.highlight.tooltip"));
        panel1.add(checkBoxWithColorChooserImpl, new GridConstraints(0, 0, 1, 1,
            GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
            null, new Dimension(204, 18), null));
        panel1.add(hSpacer1, new GridConstraints(0, 1, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK,
            null, new Dimension(14, 28), null));

        //---- label1 ----
        label1.setHorizontalTextPosition(SwingConstants.LEADING);
        label1.setLabelFor(highlightSpinner);
        label1.setText(bundle.getString("mt.border.thickness"));
        label1.setToolTipText(bundle.getString("mt.border.thickness.tooltip"));
        panel1.add(label1, new GridConstraints(1, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED,
            GridConstraints.SIZEPOLICY_FIXED,
            null, new Dimension(204, 18), null));
        panel1.add(highlightSpinner, new GridConstraints(1, 1, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, new Dimension(89, 29), null));
        panel1.add(vSpacer1, new GridConstraints(4, 0, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
            GridConstraints.SIZEPOLICY_CAN_SHRINK,
            GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
            null, null, null));

        //---- resetTabDefaultsBtn ----
        resetTabDefaultsBtn.setText(bundle.getString("mt.resetdefaults"));
        resetTabDefaultsBtn.setToolTipText(bundle.getString("mt.resetdefaults.tooltip"));
        panel1.add(resetTabDefaultsBtn, new GridConstraints(3, 0, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED,
            null, null, null));

        //---- boldTabs ----
        boldTabs.setLabel(bundle.getString("mt.boldtabs"));
        boldTabs.setText(bundle.getString("mt.boldtabs"));
        boldTabs.setToolTipText(bundle.getString("mt.boldtabs.tooltip"));
        panel1.add(boldTabs, new GridConstraints(2, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED,
            null, null, null));
      }
      content.add(panel1, new GridConstraints(0, 0, 1, 1,
          GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          null, null, null));
      content.add(vSpacer2, new GridConstraints(3, 0, 1, 1,
          GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
          GridConstraints.SIZEPOLICY_CAN_SHRINK,
          GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
          null, null, null));

      //======== panel2 ========
      {
        panel2.setBorder(new TitledBorder(new EtchedBorder(), bundle.getString("mt.panels.section")));
        panel2.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));

        //---- isContrastModeCheckbox ----
        isContrastModeCheckbox.setLabel(bundle.getString("mt.contrast"));
        isContrastModeCheckbox.setText(bundle.getString("mt.contrast"));
        isContrastModeCheckbox.setToolTipText(bundle.getString("mt.contrast.tooltip"));
        panel2.add(isContrastModeCheckbox, new GridConstraints(0, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED,
            null, null, null));
        panel2.add(hSpacer2, new GridConstraints(0, 1, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK,
            null, null, null));

        //---- hideFileIconsCheckbox ----
        hideFileIconsCheckbox.setText(bundle.getString("MTForm.hideFileIcons"));
        hideFileIconsCheckbox.setToolTipText(bundle.getString("MTForm.hideFileIcons.tooltip"));
        panel2.add(hideFileIconsCheckbox, new GridConstraints(1, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- isCompactSidebarCheckbox ----
        isCompactSidebarCheckbox.setText(bundle.getString("MTForm.isCompactSidebarCheckbox.text"));
        isCompactSidebarCheckbox.setToolTipText(bundle.getString("MTForm.isCompactSidebarCheckbox.toolTipText"));
        panel2.add(isCompactSidebarCheckbox, new GridConstraints(2, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
        panel2.add(vSpacer3, new GridConstraints(3, 0, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
            GridConstraints.SIZEPOLICY_CAN_SHRINK,
            GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
            null, null, null));
      }
      content.add(panel2, new GridConstraints(1, 0, 1, 1,
          GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          null, null, null));

      //======== panel3 ========
      {
        panel3.setBorder(new TitledBorder(new EtchedBorder(), bundle.getString("MTForm.panel3.border")));
        panel3.setLayout(new GridLayoutManager(6, 3, new Insets(0, 0, 0, 0), -1, -1));

        //---- isWallpaperSetCheckbox ----
        isWallpaperSetCheckbox.setLabel(bundle.getString("MTForm.isWallpaperSetCheckbox.label"));
        isWallpaperSetCheckbox.setText(bundle.getString("MTForm.isWallpaperSetCheckbox.text"));
        isWallpaperSetCheckbox.setToolTipText(bundle.getString("MTForm.customBg.label"));
        isWallpaperSetCheckbox.setAlignmentY(0.0F);
        isWallpaperSetCheckbox.addActionListener(e -> isWallpaperSetCheckboxActionPerformed(e));
        panel3.add(isWallpaperSetCheckbox, new GridConstraints(0, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED,
            null, null, null));

        //---- customBgLabel ----
        customBgLabel.setText(bundle.getString("MTForm.customBg.label2"));
        customBgLabel.setToolTipText(bundle.getString("MTForm.customBg.label2.tooltip"));
        customBgLabel.setAlignmentX(2.0F);
        panel3.add(customBgLabel, new GridConstraints(1, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null, 2));
        panel3.add(customBgChooser, new GridConstraints(1, 1, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- customBgRestoreButton ----
        customBgRestoreButton.setText(bundle.getString("MTForm.customBg.restoreBtn"));
        customBgRestoreButton.setToolTipText(bundle.getString("MTForm.customBg.restoreBtn.tooltip"));
        customBgRestoreButton.addActionListener(e -> customBgRestoreButtonActionPerformed(e));
        panel3.add(customBgRestoreButton, new GridConstraints(1, 2, 1, 1,
            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- isMaterialDesignCheckbox ----
        isMaterialDesignCheckbox.setLabel(bundle.getString("MTForm.isMaterialDesignCheckbox.label"));
        isMaterialDesignCheckbox.setText(bundle.getString("MTForm.isMaterialDesignCheckbox.text"));
        isMaterialDesignCheckbox.setToolTipText(bundle.getString("MTForm.isMaterialDesignCheckbox.toolTipText"));
        panel3.add(isMaterialDesignCheckbox, new GridConstraints(2, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED,
            null, null, null));

        //---- isMaterialIconsCheckbox ----
        isMaterialIconsCheckbox.setText(bundle.getString("MTForm.isMaterialIconsCheckbox.text"));
        isMaterialIconsCheckbox.setToolTipText(bundle.getString("MTForm.materialIcons.tooltip"));
        isMaterialIconsCheckbox.addActionListener(e -> isMaterialIconsCheckboxActionPerformed(e));
        panel3.add(isMaterialIconsCheckbox, new GridConstraints(3, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- isProjectViewDecoratorsCheckbox ----
        isProjectViewDecoratorsCheckbox.setText(bundle.getString("MTForm.projectViewDecorators"));
        isProjectViewDecoratorsCheckbox.setToolTipText(bundle.getString("MTForm.projectViewDecorators.tooltip"));
        panel3.add(isProjectViewDecoratorsCheckbox, new GridConstraints(4, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));

        //---- isThemeInStatusCheckbox ----
        isThemeInStatusCheckbox.setText(bundle.getString("MTForm.themeStatus"));
        isThemeInStatusCheckbox.setToolTipText(bundle.getString("MTForm.themeStatus.tooltip"));
        panel3.add(isThemeInStatusCheckbox, new GridConstraints(5, 0, 1, 1,
            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            null, null, null));
      }
      content.add(panel3, new GridConstraints(2, 0, 1, 1,
          GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
          null, null, null));
    }
    // JFormDesigner - End of component initialization  //GEN-END:initComponents
  }
}
