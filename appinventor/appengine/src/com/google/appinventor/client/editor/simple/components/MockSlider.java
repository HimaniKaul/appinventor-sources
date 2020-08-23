// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2009-2011 Google, All Rights reserved
// Copyright 2011-2012 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.client.editor.simple.components;

import com.google.appinventor.client.editor.simple.SimpleEditor;
import com.google.appinventor.client.editor.simple.components.utils.SVGPanel;
import com.google.appinventor.shared.settings.SettingsConstants;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Mock Slider component.
 *
 * @author M. Hossein Amerkashi - kkashi01@gmail.com
 */
public final class MockSlider extends MockVisibleComponent {

  /**
   * Component type name.
   */
  public static final String TYPE = "Slider";

  //private static final int DEFAULT_WIDTH = 70;

  // Widget for showing the mock slider
  protected final HorizontalPanel panel;
  public String trackColorActive = "orange";
  public String trackColorInactive = "gray";
  private boolean initialized = false;

  public SVGPanel sliderGraphic;
  String phonePreview;

  /**
   * Creates a new MockSlider component.
   *
   * @param editor editor of source file the component belongs to
   */
  public MockSlider(SimpleEditor editor) {
    super(editor, TYPE, images.slider());

    // Initialize mock slider UI
    panel = new HorizontalPanel();
    panel.setStylePrimaryName("ode-SimpleMockComponent");
    phonePreview = editor.getProjectEditor().getProjectSettingsProperty(SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS,
            SettingsConstants.YOUNG_ANDROID_SETTINGS_PHONE_PREVIEW);
    initComponent(panel);
    paintSlider();
  }

  /**
   * Draw the SVG graphic of the slider. It displays the left and
   * right sides of the slider, each with their own colors.
   *
   */
  private void paintSlider() {
    if (initialized) {
      panel.remove(sliderGraphic);
    } else {
      initialized = true;
    }

    sliderGraphic = new SVGPanel();
    int sliderHeight;
    int sliderWidth;

    if(phonePreview.equals("Classic")) {
      sliderHeight = 20;
      sliderWidth = 120;
      classicSlider();
    } else if(phonePreview.equals("Android Material") ) {
      sliderHeight = 20;
      sliderWidth = 80;
      materialSlider();
    } else if (phonePreview.equals("Android Holo") ) {
      sliderHeight = 20;
      sliderWidth = 80;
      holoSlider();
    } else {
      sliderHeight = 30;
      sliderWidth = 80;
      iOSSlider();
    }

    sliderGraphic.setWidth(sliderWidth + "px");
    sliderGraphic.setHeight(sliderHeight + "px");

    panel.add(sliderGraphic);
    panel.setCellWidth(sliderGraphic, sliderWidth + "px");
    panel.setCellHorizontalAlignment(sliderGraphic, HasHorizontalAlignment.ALIGN_LEFT);
    panel.setCellVerticalAlignment(sliderGraphic, HasVerticalAlignment.ALIGN_MIDDLE);
    refreshForm();
  }

   private void classicSlider() {
     sliderGraphic.setInnerSVG("<g id=\"Group_1\" data-name=\"Group 1\" transform=\"translate(-127 -186)\" style=\"isolation: isolate\">\n" +
             "    <path id=\"Rectangle_1\" data-name=\"Rectangle 1\" d=\"M4,0H60a0,0,0,0,1,0,0V14a0,0,0,0,1,0,0H4a4,4,0,0,1-4-4V4A4,4,0,0,1,4,0Z\" transform=\"translate(127 189)\" fill=\"" +trackColorActive+ "\"/>\n" +
             "    <path id=\"Rectangle_2\" data-name=\"Rectangle 2\" d=\"M0,0H56a4,4,0,0,1,4,4v6a4,4,0,0,1-4,4H0a0,0,0,0,1,0,0V0A0,0,0,0,1,0,0Z\" transform=\"translate(187 189)\" fill=\"" +trackColorInactive+ "\"/>\n" +
             "    <rect id=\"Rectangle_3\" data-name=\"Rectangle 3\" width=\"14\" height=\"20\" rx=\"2\" transform=\"translate(180 186)\" fill=\"#848482\"/>\n" +
             "  </g>");
   }

  private void holoSlider() {
    sliderGraphic.setInnerSVG("<defs>\n" +
            "    <filter id=\"Ellipse_1\" x=\"24\" y=\"0\" width=\"32\" height=\"32\" filterUnits=\"userSpaceOnUse\">\n" +
            "      <feOffset dy=\"3\" input=\"SourceAlpha\"/>\n" +
            "      <feGaussianBlur stdDeviation=\"3\" result=\"blur\"/>\n" +
            "      <feFlood flood-color=\"#33b5e5\" flood-opacity=\"0.161\"/>\n" +
            "      <feComposite operator=\"in\" in2=\"blur\"/>\n" +
            "      <feComposite in=\"SourceGraphic\"/>\n" +
            "    </filter>\n" +
            "  </defs>\n" +
            "  <g id=\"Group_1\" data-name=\"Group 1\" transform=\"translate(-455 -294)\">\n" +
            "    <rect id=\"Rectangle_1\" data-name=\"Rectangle 1\" width=\"40\" height=\"4\" transform=\"translate(455 305)\" fill=\"#c54949\"/>\n" +
            "    <rect id=\"Rectangle_2\" data-name=\"Rectangle 2\" width=\"40\" height=\"4\" transform=\"translate(495 305)\" fill=\"#341313\"/>\n" +
            "    <g transform=\"matrix(1, 0, 0, 1, 455, 294)\" filter=\"url(#Ellipse_1)\">\n" +
            "      <circle id=\"Ellipse_1-2\" data-name=\"Ellipse 1\" cx=\"7\" cy=\"7\" r=\"7\" transform=\"translate(33 6)\" fill=\"#2abbf1\" opacity=\"0.64\"/>\n" +
            "    </g>\n" +
            "    <circle id=\"Ellipse_2\" data-name=\"Ellipse 2\" cx=\"2\" cy=\"2\" r=\"2\" transform=\"translate(493 305)\" fill=\"#33b5e5\"/>\n" +
            "  </g>");
  }


   private void materialSlider() {
     sliderGraphic.setInnerSVG("<g id=\"Group_1\" data-name=\"Group 1\" transform=\"translate(-466 -210)\">\n" +
             "<rect id=\"TrackLeft\" x=\"0\" y=\"0\"  width=\"40\" height=\"3\"  transform=\"translate(466 213)\" fill=\"" + trackColorActive + "\"/>\n" +
             "<rect id=\"TrackRight\" width= \"40\" height=\"2\" transform=\"translate(506 213)\" fill=\"" + trackColorInactive +"\"/>\n" +
             "<circle id=\"Thumb\" cx=\"7\" cy=\"7\" r=\"7\" transform=\"translate(503 210)\" fill=\"#80cdc6\"/>\n" +
             "</g>");
   }

   private void iOSSlider() {
     sliderGraphic.setInnerSVG("<defs>\n" +
             "    <filter id=\"Knob\" x=\"22.575\" y=\"0\" width=\"35\" height=\"35\" filterUnits=\"userSpaceOnUse\">\n" +
             "      <feOffset dy=\"3\" input=\"SourceAlpha\"/>\n" +
             "      <feGaussianBlur stdDeviation=\"1\" result=\"blur\"/>\n" +
             "      <feFlood flood-opacity=\"0.078\"/>\n" +
             "      <feComposite operator=\"in\" in2=\"blur\"/>\n" +
             "      <feComposite in=\"SourceGraphic\"/>\n" +
             "    </filter>\n" +
             "  </defs>\n" +
             "  <g id=\"Controls_Sliders_Light_Basic\" data-name=\"Controls/Sliders/Light/Basic\" transform=\"translate(0 0.5)\">\n" +
             "    <rect id=\"Track\" width=\"80\" height=\"1\" rx=\"0.5\" transform=\"translate(0 14)\" fill=\""+ trackColorInactive +"\"/>\n" +
             "    <rect id=\"Fill\" width=\"40\" height=\"1\" rx=\"0.5\" transform=\"translate(0 14)\" fill=\""+ trackColorActive +"\"/>\n" +
             "    <g transform=\"matrix(1, 0, 0, 1, 0, -0.5)\" filter=\"url(#Knob)\">\n" +
             "      <g id=\"Knob-2\" data-name=\"Knob\" transform=\"translate(26.07 0.5)\" fill=\"#fff\" stroke=\"rgba(0,0,0,0.03)\" stroke-miterlimit=\"10\" stroke-width=\"0.5\">\n" +
             "        <circle cx=\"14\" cy=\"14\" r=\"14\" stroke=\"none\"/>\n" +
             "        <circle cx=\"14\" cy=\"14\" r=\"14.25\" fill=\"none\"/>\n" +
             "      </g>\n" +
             "    </g>\n" +
             "  </g>");
   }


  /**
   * Set track color for slider on the left side of the thumb
   * Thumb is the button that slides back and forth on the slider
   *
   */
  private void setTrackColorActiveProperty(String text) {
    if (sliderGraphic != null) {
      trackColorActive = MockComponentsUtil.getColor(text).toString();
      paintSlider();
    }
  }

  /**
   * Set track color for slider on the right side of the thumb
   * Thumb is the button that slides back and forth on the slider
   *
   */
  private void setTrackColorInactiveProperty(String text) {
    if (sliderGraphic != null) {
      trackColorInactive = MockComponentsUtil.getColor(text).toString();
      paintSlider();
    }
   }

  @Override
  protected boolean isPropertyVisible(String propertyName) {
    //We don't want to allow user to change the slider height. S/he can only change the
    //slider width
    if (propertyName.equals(PROPERTY_NAME_HEIGHT)) {
      return false;
    }
    return super.isPropertyVisible(propertyName);
  }

  // PropertyChangeListener implementation
  @Override
  public void onPropertyChange(String propertyName, String newValue) {
    super.onPropertyChange(propertyName, newValue);

    // Apply changed properties to the mock component
    if (propertyName.equals(PROPERTY_NAME_WIDTH)) {
      refreshForm();
    } else if (propertyName.equals(PROPERTY_NAME_COLORLEFT)) {
      setTrackColorActiveProperty(newValue);
      refreshForm();
    } else if (propertyName.equals(PROPERTY_NAME_COLORRIGHT)) {
      setTrackColorInactiveProperty(newValue);
      refreshForm();
    }

  }

  /*
   * We add the DesignPreviewChangeListener here instead of in the
   * constructor because at construction time we do not have a
   * container so getForm() fails.
   */

  @Override
  protected void setContainer(MockContainer container) {
    super.setContainer(container);
    getForm().addDesignPreviewChangeListener(this);
  }

  @Override
  public void onDesignPreviewChanged() {
    phonePreview = editor.getProjectEditor().getProjectSettingsProperty(SettingsConstants.PROJECT_YOUNG_ANDROID_SETTINGS,
      SettingsConstants.YOUNG_ANDROID_SETTINGS_PHONE_PREVIEW);
    paintSlider();
  }

}
