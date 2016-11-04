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

package com.nikhilnayak.games.octoshootar.model.bonus;

import com.nikhilnayak.games.octoshootar.model.inventory.InventoryItemInformation;
import com.nikhilnayak.games.octoshootar.model.inventory.InventoryItemInformationFactory;

public class BonusEntry {
    private long mQuantity;
    private int mEffectResourceId;
    private InventoryItemInformation mInventoryItemInformation;
    private boolean mIsEquipped = false;
    private Bonus mBonus;

    public BonusEntry(int inventoryItemType, long quantity) {
        mInventoryItemInformation = InventoryItemInformationFactory.create(inventoryItemType);
        mQuantity = quantity;
    }

    public void setIsEquipped(boolean isEquipped) {
        mIsEquipped = isEquipped;
    }

    public boolean isEquipped() {
        return mIsEquipped;
    }

    public long getQuantity() {
        return mQuantity;
    }

    public void setTitleResourceId(int resourceId) {
        mInventoryItemInformation.setTitleResourceId(resourceId);
    }

    public int getTitleResourceId() {
        return mInventoryItemInformation.getTitleResourceId();
    }

    public void setEffectResourceId(int resourceId) {
        mEffectResourceId = resourceId;
    }

    public int getEffectResourceId() {
        return mEffectResourceId;
    }

    public int getInventoryItemType() {
        return mInventoryItemInformation.getType();
    }

    public void setBonus(Bonus bonus) {
        mBonus = bonus;
    }

    public Bonus getBonus() {
        return mBonus;
    }

    public int getImageResourceId() {
        return mInventoryItemInformation.getImageResourceId();
    }
}
