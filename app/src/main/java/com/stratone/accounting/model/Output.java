package com.stratone.accounting.model;

public class Output {
    String itemMenuOutput;
    int imageMenuOutput;

    public Output(String itemMenuOutput, int imageMenuOutput) {
        this.itemMenuOutput = itemMenuOutput;
        this.imageMenuOutput = imageMenuOutput;
    }

    public String getItemMenuOutput() {
        return itemMenuOutput;
    }

    public void setItemMenuOutput(String itemMenuOutput) {
        this.itemMenuOutput = itemMenuOutput;
    }

    public int getImageMenuOutput() {
        return imageMenuOutput;
    }

    public void setImageMenuOutput(int imageMenuOutput) {
        this.imageMenuOutput = imageMenuOutput;
    }
}
