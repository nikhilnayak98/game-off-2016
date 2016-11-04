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

public class GameInformationTime extends GameInformationStandard {
    protected long mCurrentTime;
    protected long mStartingTimeInMillis;
    protected long mEndingTimeInMillis;

    public GameInformationTime(GameMode gameMode, Weapon weapon, long currentTime) {
        super(gameMode, weapon);
        mCurrentTime = currentTime;
        mStartingTimeInMillis = 0;
        mEndingTimeInMillis = System.currentTimeMillis();
    }

    public GameInformationTime(Parcel in) {
        super(in);
    }

    @Override
    public void readFromParcel(Parcel in) {
        super.readFromParcel(in);
        mCurrentTime = in.readLong();
        mStartingTimeInMillis = in.readLong();
        mEndingTimeInMillis = in.readLong();

    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        super.writeToParcel(out, i);
        out.writeLong(mCurrentTime);
        out.writeLong(mStartingTimeInMillis);
        out.writeLong(mEndingTimeInMillis);
    }

    public static final Parcelable.Creator<GameInformationTime> CREATOR = new Parcelable.Creator<GameInformationTime>() {
        public GameInformationTime createFromParcel(Parcel in) {
            return new GameInformationTime(in);
        }

        public GameInformationTime[] newArray(int size) {
            return new GameInformationTime[size];
        }
    };

    public long getCurrentTime() {
        return mCurrentTime;
    }

    public void setCurrentTime(long currentTime) {
        mCurrentTime = currentTime;
    }

    public void setStartingTime() {
        //TODO use nanoTime
        mStartingTimeInMillis = System.currentTimeMillis();
    }

    public void setEndingTime() {
        //TODO use nanoTime
        mEndingTimeInMillis = System.currentTimeMillis();
    }

    public long getPlayingTime() {
        return mEndingTimeInMillis - mStartingTimeInMillis;
    }
}
