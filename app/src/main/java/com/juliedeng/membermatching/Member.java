package com.juliedeng.membermatching;

/**
 * Created by juliedeng on 2/8/18.
 */

public class Member {

    String name;
    int image;

    public Member(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
