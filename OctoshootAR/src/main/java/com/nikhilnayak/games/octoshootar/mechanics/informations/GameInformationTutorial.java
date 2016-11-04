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

public class GameInformationTutorial extends GameInformationStandard {

    public static final int STEP_WELCOME = 0;
    public static final int STEP_UI_WELCOME = 1;
    public static final int STEP_CROSSHAIR = 2;
    public static final int STEP_COMBO = 3;
    public static final int STEP_AMMO = 4;
    public static final int STEP_AMMO_2 = 5;
    public static final int STEP_SCORE = 6;
    public static final int STEP_SERIOUS_THINGS = 7;
    public static final int STEP_TARGET = 8;
    public static final int STEP_KILL = 9;
    public static final int STEP_CONGRATULATION = 10;
    public static final int STEP_TARGET_2 = 11;
    public static final int STEP_KILL_2 = 12;
    public static final int STEP_CONGRATULATION_2 = 13;
    public static final int STEP_END = 14;

    private int mCurrentStep;

    public GameInformationTutorial(GameMode gameMode, Weapon weapon) {
        super(gameMode, weapon);
        mCurrentStep = STEP_WELCOME;
    }

    protected GameInformationTutorial(Parcel in) {
        super(in);
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        super.writeToParcel(out, i);
        out.writeInt(mCurrentStep);
    }

    @Override
    public void readFromParcel(Parcel in) {
        super.readFromParcel(in);
        mCurrentStep = in.readInt();
    }

    public int nextStep() {
        mScoreInformation.increaseScore(20);
        return ++mCurrentStep;
    }

    public void setCurrentStep(int step) {
        mCurrentStep = step;
    }

    public int getCurrentStep() {
        return mCurrentStep;
    }

    public static final Parcelable.Creator<GameInformationTutorial> CREATOR = new Parcelable.Creator<GameInformationTutorial>() {
        public GameInformationTutorial createFromParcel(Parcel in) {
            return new GameInformationTutorial(in);
        }

        public GameInformationTutorial[] newArray(int size) {
            return new GameInformationTutorial[size];
        }
    };
}
