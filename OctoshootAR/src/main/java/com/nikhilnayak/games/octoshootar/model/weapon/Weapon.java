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

package com.nikhilnayak.games.octoshootar.model.weapon;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * A model for all the weapons used in this game.
 * A Weapon has a cartridge clip capacity {@code mAmmunitionLimit}
 * A Weapon deals {@code mDamage} on each shot.
 * A Weapon should regain one ammunition every {@code mReloadingTime} millisecond.
 */
public class Weapon implements Parcelable {
    //the damage done when firing
    private int mDamage;
    //the current number of ammunition in the cartridge clip
    private int mCurrentAmmunition;
    //the cartridge clip capacity
    private int mAmmunitionLimit;
    //the reloading interval in millisecond
    private long mReloadingTime;
    private boolean mHasRunOutOfAmmo;

    public Weapon() {
        mDamage = 0;
        mCurrentAmmunition = 0;
        mAmmunitionLimit = 1;
        mReloadingTime = 1;
        mHasRunOutOfAmmo = false;
    }

    public Weapon(Parcel in) {
        readFromParcel(in);
    }

    /**
     * Try to add one ammunition to the cartridge clip.
     */
    public void reload() {
        if (mCurrentAmmunition < mAmmunitionLimit) {
            mCurrentAmmunition += 1;
        }
    }

    /**
     * Try to add {@code ammoAmount} ammunition to the cartridge clip
     *
     * @param ammoAmount the number of ammunition added to the cartridge clip
     */
    public void reload(int ammoAmount) {
        if (ammoAmount <= mAmmunitionLimit) {
            mCurrentAmmunition = ammoAmount;
        }
    }

    /**
     * Fire a bullet.
     *
     * @return the damage done during the shot or 0 if there is no ammunition
     */
    public int fire() {
        if (mCurrentAmmunition > 0) {
            mCurrentAmmunition -= 1;
            return mDamage;
        }
        mHasRunOutOfAmmo = true;
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeInt(mDamage);
        out.writeInt(mCurrentAmmunition);
        out.writeInt(mAmmunitionLimit);
        out.writeByte((byte) (mHasRunOutOfAmmo ? 1 : 0));
    }

    public void readFromParcel(Parcel in) {
        mDamage = in.readInt();
        mCurrentAmmunition = in.readInt();
        mAmmunitionLimit = in.readInt();
        mHasRunOutOfAmmo = in.readByte() == 1;
    }

    public static final Parcelable.Creator<Weapon> CREATOR = new Parcelable.Creator<Weapon>() {
        public Weapon createFromParcel(Parcel in) {
            return new Weapon(in);
        }

        public Weapon[] newArray(int size) {
            return new Weapon[size];
        }
    };

    /**
     * Getters & Setters
     */
    public void setDamage(int damage) {
        mDamage = damage;
    }

    public int getDamage() {
        return mDamage;
    }

    public int getCurrentAmmunition() {
        return mCurrentAmmunition;
    }

    public void setAmmunitionLimit(int ammunitionLimit) {
        mAmmunitionLimit = ammunitionLimit;
    }

    public void setCurrentAmmunition(int currentAmmunition) {
        mCurrentAmmunition = currentAmmunition;
    }

    public int getAmmunitionLimit() {
        return mAmmunitionLimit;
    }

    public void setReloadingTime(long reloadingTime) {
        mReloadingTime = reloadingTime;
    }

    public long getReloadingTime() {
        return mReloadingTime;
    }

    public boolean hasRunOutOfAmmo() {
        return mHasRunOutOfAmmo;
    }

}
