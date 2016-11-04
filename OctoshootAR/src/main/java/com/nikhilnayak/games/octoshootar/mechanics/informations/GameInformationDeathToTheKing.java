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

import com.nikhilnayak.games.octoshootar.model.DisplayableItemFactory;
import com.nikhilnayak.games.octoshootar.model.TargetableItem;
import com.nikhilnayak.games.octoshootar.model.mode.GameModeDeathToTheKing;
import com.nikhilnayak.games.octoshootar.model.weapon.Weapon;

public class GameInformationDeathToTheKing extends GameInformationTime {
    private boolean mIsKingSummoned;

    public GameInformationDeathToTheKing(GameModeDeathToTheKing gameMode, Weapon weapon, long currentTime) {
        super(gameMode, weapon, currentTime);
        mIsKingSummoned = false;
    }

    public GameInformationDeathToTheKing(Parcel in) {
        super(in);
    }

    public boolean isKingSummoned() {
        return mIsKingSummoned;
    }

    public void summonKing() {
        if (mIsKingSummoned) return;
        for (int i = 0; i < 100; i++) {
            if (i != 50) {
                addTargetableItem(DisplayableItemFactory.createGhostWithRandomCoordinates(
                        TargetableItem.randomGhostTypeWithoutKing()));
            } else {
                addTargetableItem(DisplayableItemFactory.createKingGhostForDeathToTheKing());
            }

        }
        mCurrentTime = 0;
        mStartingTimeInMillis = System.currentTimeMillis();
        mIsKingSummoned = true;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        super.writeToParcel(out, i);
        out.writeByte((byte) (mIsKingSummoned ? 1 : 0));
    }

    @Override
    public void readFromParcel(Parcel in) {
        super.readFromParcel(in);
        mIsKingSummoned = in.readByte() == 1;
    }

    public static final Parcelable.Creator<GameInformationDeathToTheKing> CREATOR = new Parcelable.Creator<GameInformationDeathToTheKing>() {
        public GameInformationDeathToTheKing createFromParcel(Parcel in) {
            return new GameInformationDeathToTheKing(in);
        }

        public GameInformationDeathToTheKing[] newArray(int size) {
            return new GameInformationDeathToTheKing[size];
        }
    };

}
