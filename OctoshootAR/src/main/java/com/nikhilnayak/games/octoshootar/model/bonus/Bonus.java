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

public interface Bonus extends Parcelable {
    public void apply(GameInformationStandard gameInformation);

    public static class DummyBonus implements Bonus {

        public DummyBonus(Parcel in) {
        }

        public DummyBonus() {
        }

        @Override
        public void apply(GameInformationStandard gameInformation) {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
        }

        public static final Parcelable.Creator<DummyBonus> CREATOR = new Parcelable.Creator<DummyBonus>() {
            public DummyBonus createFromParcel(Parcel in) {
                return new DummyBonus(in);
            }

            public DummyBonus[] newArray(int size) {
                return new DummyBonus[size];
            }
        };
    }
}
