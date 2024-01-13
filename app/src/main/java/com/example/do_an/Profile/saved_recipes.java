package com.example.do_an.Profile;

public class saved_recipes {
    private int ResourceImage;
    private String name;

    public saved_recipes(int ResourceImage, String name) {
        this.ResourceImage = ResourceImage;
        this.name = name;
    }

    public int getResourceImage() {
        return ResourceImage;
    }

    public void setResourceImage(int ResourceImage) {
        this.ResourceImage = ResourceImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
