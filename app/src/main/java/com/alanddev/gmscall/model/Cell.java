package com.alanddev.gmscall.model;

/**
 * Created by ANLD on 3/3/2017.
 */

public class Cell {
    private int band;
    private int earfcn;
    private int pci;
    private float rsrp;
    private float rsrq;

    public int getBand() {
        return band;
    }

    public void setBand(int band) {
        this.band = band;
    }

    public int getEarfcn() {
        return earfcn;
    }

    public void setEarfcn(int earfcn) {
        this.earfcn = earfcn;
    }

    public int getPci() {
        return pci;
    }

    public void setPci(int pci) {
        this.pci = pci;
    }

    public float getRsrp() {
        return rsrp;
    }

    public void setRsrp(float rsqp) {
        this.rsrp = rsqp;
    }

    public float getRsrq() {
        return rsrq;
    }

    public void setRsrq(float rsrq) {
        this.rsrq = rsrq;
    }
}
