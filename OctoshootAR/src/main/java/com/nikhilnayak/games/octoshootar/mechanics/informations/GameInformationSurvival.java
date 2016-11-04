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

public class GameInformationSurvival extends GameInformationTime {

    public static final int DIFFICULTY_EASY = 0x00000001;
    public static final int DIFFICULTY_HARD = 0x00000002;
    public static final int DIFFICULTY_HARDER = 0x00000003;
    public static final int DIFFICULTY_HARDEST = 0x00000004;

    private static final long TIME_EASY = 30000;
    private static final long TIME_HARD = 60000;
    private static final long TIME_HARDER = 90000;

    public GameInformationSurvival(GameMode gameMode, Weapon weapon, long currentTime) {
        super(gameMode, weapon, currentTime);
    }

    public GameInformationSurvival(Parcel in) {
        super(in);
    }

    public static final Parcelable.Creator<GameInformationSurvival> CREATOR = new Parcelable.Creator<GameInformationSurvival>() {
        public GameInformationSurvival createFromParcel(Parcel in) {
            return new GameInformationSurvival(in);
        }

        public GameInformationSurvival[] newArray(int size) {
            return new GameInformationSurvival[size];
        }
    };

    public int getDifficulty() {
        final long timePlayed = System.currentTimeMillis() - mStartingTimeInMillis;
        int difficulty = DIFFICULTY_HARDEST;

        if (timePlayed < TIME_EASY) {
            difficulty = DIFFICULTY_EASY;
        } else if (timePlayed < TIME_HARD) {
            difficulty = DIFFICULTY_HARD;
        } else if (timePlayed < TIME_HARDER) {
            difficulty = DIFFICULTY_HARDER;
        }

        return difficulty;
    }

}
