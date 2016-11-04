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

import com.nikhilnayak.games.octoshootar.model.mode.GameMode;
import com.nikhilnayak.games.octoshootar.model.weapon.Weapon;


public class GameInformationTwentyInARow extends GameInformationTime {
    protected int mCurrentStack;

    public GameInformationTwentyInARow(GameMode gameMode, Weapon weapon, long currentTime) {
        super(gameMode, weapon, currentTime);
        mCurrentStack = 0;
    }

    public GameInformationTwentyInARow(Parcel in) {
        super(in);
    }

    public int increaseCurrentStack() {
        return ++mCurrentStack;
    }

    public void resetCurrentStack() {
        mCurrentStack = 0;
    }

    public int getCurrentStack() {
        return mCurrentStack;
    }

    @Override
    public void readFromParcel(Parcel in) {
        super.readFromParcel(in);
        mCurrentStack = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        super.writeToParcel(out, i);
        out.writeInt(mCurrentStack);
    }

    public static final Parcelable.Creator<GameInformationTwentyInARow> CREATOR = new Parcelable.Creator<GameInformationTwentyInARow>() {
        public GameInformationTwentyInARow createFromParcel(Parcel in) {
            return new GameInformationTwentyInARow(in);
        }

        public GameInformationTwentyInARow[] newArray(int size) {
            return new GameInformationTwentyInARow[size];
        }
    };
}
