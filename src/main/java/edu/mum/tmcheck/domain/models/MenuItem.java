package edu.mum.tmcheck.domain.models;

public class MenuItem implements Comparable<MenuItem>{
    String key;
    String icon;
    String label;
    String link;
    boolean isActive = false;
    boolean isDisabled = false;

    public MenuItem(String key, String label, String link) {
        this.key = key;
        this.label = label;
        this.link = link;
    }

    public String getKey() {
        return key;
    }

    public MenuItem setKey(String key) {
        this.key = key;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public MenuItem setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public MenuItem setLabel(String label) {
        this.label = label;
        return this;
    }

    public String getLink() {
        return link;
    }

    public MenuItem setLink(String link) {
        this.link = link;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public MenuItem setActive(boolean active) {
        isActive = active;
        return this;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public MenuItem setDisabled(boolean disabled) {
        isDisabled = disabled;
        return this;
    }

    @Override
    public int compareTo(MenuItem o) {
        if (o == null) return -1;

        return this.key.compareTo(o.key);
    }
}
