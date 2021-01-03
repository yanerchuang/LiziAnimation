package com.ywj.lizianimation;

public class ParticleItem {
    private int x;//坐标点
    private int y;//坐标点
    private int rotateX;//中心坐标点
    private int rotateY;//中心坐标点
    private int radius;//视图半径
    private int rotateLength;//视图旋转半径
    private float alpha;//透明度
    private int currentDegree;//当前角度
    private boolean isForward;//是否顺时针旋转

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRotateX() {
        return rotateX;
    }

    public void setRotateX(int rotateX) {
        this.rotateX = rotateX;
    }

    public int getRotateY() {
        return rotateY;
    }

    public void setRotateY(int rotateY) {
        this.rotateY = rotateY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getRotateLength() {
        return rotateLength;
    }

    public void setRotateLength(int rotateLength) {
        this.rotateLength = rotateLength;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public int getCurrentDegree() {
        return currentDegree;
    }

    public void setCurrentDegree(int currentDegree) {
        this.currentDegree = currentDegree;
        calculatePosition();
    }

    public boolean isForward() {
        return isForward;
    }

    public void setForward(boolean forward) {
        isForward = forward;
    }


    /**
     * 计算位置
     */
    private void calculatePosition() {
        int rotateX = getRotateX();
        int rotateY = getRotateY();
        int rotateLength = getRotateLength();
        int currentDegree = getCurrentDegree();
        double angle = Math.toRadians(currentDegree);
        double cos = Math.cos(angle) * (isForward ? 1 : -1);
        double sin = Math.sin(angle) * (isForward ? 1 : -1);
//        Log.e("xxx", "sin " + sin+",currentDegree "+currentDegree);
        x = (int) (rotateX + rotateLength * cos);
        y = (int) (rotateY + rotateLength * sin);
    }
}
