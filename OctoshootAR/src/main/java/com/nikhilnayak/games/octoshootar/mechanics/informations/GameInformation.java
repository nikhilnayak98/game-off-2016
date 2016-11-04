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

package com.nikhilnayak.games.octoshootar.mechanics.informations;

import android.os.Parcel;
import android.os.Parcelable;

import com.nikhilnayak.games.octoshootar.model.bonus.BonusInventoryItemConsumer;
import com.nikhilnayak.games.octoshootar.model.PlayerProfile;
import com.nikhilnayak.games.octoshootar.model.bonus.Bonus;
import com.nikhilnayak.games.octoshootar.model.mode.GameMode;

abstract public class GameInformation implements Parcelable {
    protected float mCurrentX;
    protected float mCurrentY;
    protected float mCurrentZ;
    protected GameMode mGameMode;

    protected GameInformation(GameMode gameMode) {
        mGameMode = gameMode;
    }

    protected GameInformation(Parcel in) {
        readFromParcel(in);
    }

    protected void readFromParcel(Parcel in) {
        mCurrentX = in.readFloat();
        mCurrentY = in.readFloat();
        mCurrentZ = in.readFloat();
        mGameMode = in.readParcelable(GameMode.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeFloat(mCurrentX);
        out.writeFloat(mCurrentY);
        out.writeFloat(mCurrentZ);
        out.writeParcelable(mGameMode, i);
    }

    public void setCurrentPosition(float x, float y, float z) {
        mCurrentX = x;
        mCurrentY = y;
        mCurrentZ = z;
    }

    public float[] getCurrentPosition() {
        return new float[]{mCurrentX, mCurrentY, mCurrentZ};
    }

    public GameMode getGameMode() {
        return mGameMode;
    }

    public Bonus getBonus() {
        return mGameMode.getBonus();
    }

    public void useBonus(PlayerProfile playerProfile) {
        final Bonus currentBonus = mGameMode.getBonus();
        if (currentBonus instanceof BonusInventoryItemConsumer) {
            mGameMode.setBonus(((BonusInventoryItemConsumer) currentBonus).consume(playerProfile));
        }
    }

    /**
     * retrieve grade for the current informations
     *
     * @return
     */
    public int getRank() {
        return mGameMode.getRank(this);
    }

}