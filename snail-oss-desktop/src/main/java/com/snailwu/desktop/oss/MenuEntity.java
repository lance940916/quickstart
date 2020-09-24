package com.snailwu.desktop.oss;

/**
 * @author 吴庆龙
 * @date 2020/8/21 11:04 上午
 */
public class MenuEntity {

    private String name;
    private MenuItemEntity[] childItem;

    public MenuEntity() {
    }

    public MenuEntity(String name, MenuItemEntity[] childItem) {
        this.name = name;
        this.childItem = childItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuItemEntity[] getChildItem() {
        return childItem;
    }

    public void setChildItem(MenuItemEntity[] childItem) {
        this.childItem = childItem;
    }
}
