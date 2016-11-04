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

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseIntArray;

public class DroppedByList implements Parcelable {
    public static final int DROP_RATE_BABY_DROOL = 50;
    public static final int DROP_RATE_BROKEN_HELMET_HORN = 100;
    public static final int DROP_RATE_COIN = 75;
    public static final int DROP_RATE_KING_CROWN = 75;
    public static final int DROP_RATE_GHOST_TEAR = 50;

    private final SparseIntArray mMonstersAndPercents;

    public DroppedByList() {
        mMonstersAndPercents = new SparseIntArray();
    }

    public DroppedByList(Parcel in) {
        mMonstersAndPercents = new SparseIntArray();
        readFromParcel(in);
    }

    public void addMonster(Integer monsterNameResourceId, Integer percent) {
        percent += mMonstersAndPercents.get(monsterNameResourceId);
        mMonstersAndPercents.put(monsterNameResourceId, percent);
    }

    public SparseIntArray getMonstersAndPercents() {
        return mMonstersAndPercents;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        final int size = mMonstersAndPercents.size();
        dest.writeInt(size);
        for (int i = 0; i < size; i++) {
            dest.writeInt(mMonstersAndPercents.keyAt(i));
            dest.writeInt(mMonstersAndPercents.valueAt(i));
        }
    }

    public void readFromParcel(Parcel in) {
        final int size = in.readInt();
        for (int i = 0; i < size; i++) {
            final int key = in.readInt();
            final int value = in.readInt();
            mMonstersAndPercents.put(key, value);
        }
    }

    public static final Parcelable.Creator<DroppedByList> CREATOR = new Parcelable.Creator<DroppedByList>() {
        public DroppedByList createFromParcel(Parcel in) {
            return new DroppedByList(in);
        }

        public DroppedByList[] newArray(int size) {
            return new DroppedByList[size];
        }
    };
}
