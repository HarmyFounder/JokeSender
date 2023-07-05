package com.HarmyIndustries.jokeSender;

public class SpecialFeature {

    private String specialFeature;
    private boolean isAttribute;
    private boolean isClass;

    public SpecialFeature() {
    }

    public SpecialFeature(String specialFeature, boolean isAttribute, boolean isClass) {
        this.specialFeature = specialFeature;
        this.isAttribute = isAttribute;
        this.isClass = isClass;
    }

    public String getSpecialFeature() {
        return specialFeature;
    }

    public void setSpecialFeature(String specialFeature) {
        this.specialFeature = specialFeature;
    }

    public boolean isAttribute() {
        return isAttribute;
    }

    public void setAttribute(boolean attribute) {
        isAttribute = attribute;
    }

    public boolean isClass() {
        return isClass;
    }

    public void setClass(boolean aClass) {
        isClass = aClass;
    }
}
