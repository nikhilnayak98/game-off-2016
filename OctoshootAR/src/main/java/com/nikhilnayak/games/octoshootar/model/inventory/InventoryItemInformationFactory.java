/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016. Nikhil Nayak <nikhilnayak98@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.nikhilnayak.games.octoshootar.model.inventory;

import com.nikhilnayak.games.octoshootar.R;

public class InventoryItemInformationFactory {

    public static InventoryItemInformation create(int inventoryItemType) {
        InventoryItemInformation inventoryItemInformation = new InventoryItemInformation();
        int titleResourceId = 0;
        int descriptionResourceId = 0;
        int imageResourceId = R.drawable.ic_bag;
        ;
        switch (inventoryItemType) {
            case InventoryItemInformation.TYPE_BABY_DROOL:
                titleResourceId = R.plurals.inventory_item_baby_drool_title;
                descriptionResourceId = R.string.inventory_item_baby_drool_description;
                imageResourceId = R.drawable.ic_item_baby_drool;
                break;

            case InventoryItemInformation.TYPE_BROKEN_HELMET_HORN:
                titleResourceId = R.plurals.inventory_item_broken_helmet_horn_title;
                descriptionResourceId = R.string.inventory_item_broken_helmet_horn_description;
                imageResourceId = R.drawable.ic_item_broken_helmet_corn;
                break;

            case InventoryItemInformation.TYPE_COIN:
                titleResourceId = R.plurals.inventory_item_coin_title;
                descriptionResourceId = R.string.inventory_item_coin_description;
                break;

            case InventoryItemInformation.TYPE_KING_CROWN:
                titleResourceId = R.plurals.inventory_item_king_crown_title;
                descriptionResourceId = R.string.inventory_item_king_crown_description;
                imageResourceId = R.drawable.ic_item_king_crown;
                break;

            case InventoryItemInformation.TYPE_STEEL_BULLET:
                titleResourceId = R.plurals.inventory_item_steel_bullet_title;
                descriptionResourceId = R.string.inventory_item_steel_bullet_description;
                imageResourceId = R.drawable.ic_item_steel_bullet;
                break;

            case InventoryItemInformation.TYPE_GOLD_BULLET:
                titleResourceId = R.plurals.inventory_item_gold_bullet_title;
                descriptionResourceId = R.string.inventory_item_gold_bullet_description;
                imageResourceId = R.drawable.ic_item_gold_bullet;
                break;

            case InventoryItemInformation.TYPE_ONE_SHOT_BULLET:
                titleResourceId = R.plurals.inventory_item_one_shot_bullet_title;
                descriptionResourceId = R.string.inventory_item_one_shot_bullet_description;
                imageResourceId = R.drawable.ic_item_one_shot_bullet;
                break;

            case InventoryItemInformation.TYPE_GHOST_TEAR:
                titleResourceId = R.plurals.inventory_item_ghost_tear_title;
                descriptionResourceId = R.string.inventory_item_ghost_tear_description;
                imageResourceId = R.drawable.ic_item_ghost_tear;
                break;

            case InventoryItemInformation.TYPE_SPEED_POTION:
                titleResourceId = R.plurals.inventory_item_speed_potion_title;
                descriptionResourceId = R.string.inventory_item_speed_potion_description;
                imageResourceId = R.drawable.ic_item_speed_potion;
                break;
        }
        inventoryItemInformation.setType(inventoryItemType);
        inventoryItemInformation.setTitleResourceId(titleResourceId);
        inventoryItemInformation.setDescriptionResourceId(descriptionResourceId);
        inventoryItemInformation.setImageResourceId(imageResourceId);
        return inventoryItemInformation;
    }
}
