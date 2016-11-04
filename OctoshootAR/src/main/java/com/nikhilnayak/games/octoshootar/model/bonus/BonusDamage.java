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


import android.os.Parcel;
import android.os.Parcelable;

import com.nikhilnayak.games.octoshootar.mechanics.informations.GameInformationStandard;
import com.nikhilnayak.games.octoshootar.model.PlayerProfile;

public class BonusDamage implements Bonus, BonusInventoryItemConsumer {
    private int mBonusDamage;
    private int mInventoryItemType;

    public BonusDamage() {
        mBonusDamage = 0;
        mInventoryItemType = 0;
    }

    public BonusDamage(int inventoryItemType, int bonusDamage) {
        mBonusDamage = bonusDamage;
        mInventoryItemType = inventoryItemType;
    }

    public BonusDamage(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public void apply(GameInformationStandard gameInformation) {
        int currentDamage = gameInformation.getWeapon().getDamage();
        gameInformation.getWeapon().setDamage(currentDamage + mBonusDamage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mBonusDamage);
        out.writeInt(mInventoryItemType);
    }

    private void readFromParcel(Parcel in) {
        mBonusDamage = in.readInt();
        mInventoryItemType = in.readInt();
    }

    public void setBonusDamage(int bonusDamage) {
        mBonusDamage = bonusDamage;
    }

    public int getBonusDamage() {
        return mBonusDamage;
    }

    public static final Parcelable.Creator<BonusDamage> CREATOR = new Parcelable.Creator<BonusDamage>() {
        public BonusDamage createFromParcel(Parcel in) {
            return new BonusDamage(in);
        }

        public BonusDamage[] newArray(int size) {
            return new BonusDamage[size];
        }
    };

    @Override
    public Bonus consume(PlayerProfile playerProfile) {
        final long remainingQuantity = playerProfile.decreaseInventoryItemQuantity(mInventoryItemType, 1);
        if (remainingQuantity > 0) {
            return this;
        } else {
            return new Bonus.DummyBonus();
        }
    }
}
