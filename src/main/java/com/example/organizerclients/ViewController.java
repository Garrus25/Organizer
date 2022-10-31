package com.example.organizerclients;

public abstract class ViewController {
    protected final SceneController sceneController = SceneController.getInstance();

    protected abstract void setFieldParameters();
    protected abstract void setButtonParameters();
    protected abstract void initialize();
}
