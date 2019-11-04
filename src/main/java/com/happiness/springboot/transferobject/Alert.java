package com.happiness.springboot.transferobject;

public class Alert {

    private Double TVIX;
    private Double UVXY;
    private Double VXX;
    private Double SVXY;
    private Boolean action;

    public Alert() {
    }

    public Alert(Double TVIX, Double UVXY, Double VXX, Double SVXY, Boolean action) {
        this.TVIX = TVIX;
        this.UVXY = UVXY;
        this.VXX = VXX;
        this.SVXY = SVXY;
        this.action = action;
    }

    public Double getTVIX() {
        return TVIX;
    }

    public void setTVIX(Double TVIX) {
        this.TVIX = TVIX;
    }

    public Double getUVXY() {
        return UVXY;
    }

    public void setUVXY(Double UVXY) {
        this.UVXY = UVXY;
    }

    public Double getVXX() {
        return VXX;
    }

    public void setVXX(Double VXX) {
        this.VXX = VXX;
    }

    public Double getSVXY() {
        return SVXY;
    }

    public void setSVXY(Double SVXY) {
        this.SVXY = SVXY;
    }

    public Boolean getAction() {
        return action;
    }

    public void setAction(Boolean action) {
        this.action = action;
    }
}
