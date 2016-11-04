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

import com.nikhilnayak.games.octoshootar.model.DisplayableItem;
import com.nikhilnayak.games.octoshootar.model.weapon.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.nikhilnayak.games.octoshootar.mechanics.informations.score.ScoreInformation;
import com.nikhilnayak.games.octoshootar.model.TargetableItem;
import com.nikhilnayak.games.octoshootar.model.mode.GameMode;


public class GameInformationStandard extends GameInformation {
    protected Weapon mWeapon;
    protected TargetableItem mCurrentTarget;
    protected List<TargetableItem> mTargetableItems;
    protected List<DisplayableItem> mDisplayableItems;
    protected ScoreInformation mScoreInformation;

    /**
     * Create a new GameInformation
     *
     * @param weapon weapon used for this game
     */
    public GameInformationStandard(GameMode gameMode, Weapon weapon) {
        super(gameMode);
        mScoreInformation = new ScoreInformation();
        mWeapon = weapon;
        mCurrentTarget = null;
        mTargetableItems = new ArrayList<TargetableItem>();
        mDisplayableItems = new ArrayList<DisplayableItem>();
        gameMode.getBonus().apply(this);
    }

    protected GameInformationStandard(Parcel in) {
        super(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void readFromParcel(Parcel in) {
        super.readFromParcel(in);
        mScoreInformation = in.readParcelable(ScoreInformation.class.getClassLoader());
        mWeapon = in.readParcelable(Weapon.class.getClassLoader());
        mCurrentTarget = in.readParcelable(TargetableItem.class.getClassLoader());
        mTargetableItems = new ArrayList<TargetableItem>();
        in.readTypedList(mTargetableItems, TargetableItem.CREATOR);
        mDisplayableItems = new ArrayList<DisplayableItem>();
        in.readTypedList(mDisplayableItems, DisplayableItem.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        super.writeToParcel(out, i);
        out.writeParcelable(mScoreInformation, i);
        out.writeParcelable(mWeapon, i);
        out.writeParcelable(mCurrentTarget, i);
        out.writeTypedList(mTargetableItems);
        out.writeTypedList(mDisplayableItems);
    }

    public static final Parcelable.Creator<GameInformationStandard> CREATOR = new Parcelable.Creator<GameInformationStandard>() {
        public GameInformationStandard createFromParcel(Parcel in) {
            return new GameInformationStandard(in);
        }

        public GameInformationStandard[] newArray(int size) {
            return new GameInformationStandard[size];
        }
    };

    /**
     * Getters & Setters
     */
    public Weapon getWeapon() {
        return mWeapon;
    }

    public void addTargetableItem(TargetableItem item) {
        mTargetableItems.add(item);
    }

    public void addDisplayableItem(DisplayableItem item) {
        mDisplayableItems.add(item);
    }

    public List<DisplayableItem> getItemsForDisplay() {
        final ArrayList<DisplayableItem> displayAll = new ArrayList<DisplayableItem>();
        displayAll.addAll(mDisplayableItems);
        displayAll.addAll(mTargetableItems);
        return displayAll;
    }


    /**
     * get current target
     *
     * @return current target
     */
    public TargetableItem getCurrentTarget() {
        return mCurrentTarget;
    }

    /**
     * set current target
     *
     * @param t current TargetableItem targeted
     */
    public void setCurrentTarget(TargetableItem t) {
        mCurrentTarget = t;
    }

    /**
     * set current target to null
     */
    public void removeTarget() {
        mCurrentTarget = null;
    }

    /**
     * increase targets killed number
     */
    public void targetKilled() {
        mTargetableItems.remove(mCurrentTarget);
        mScoreInformation.increaseNumberOfTargetsKilled();
        mScoreInformation.addLoots(mCurrentTarget.getDrop());
        mCurrentTarget = null;
    }

    public void addLoots(ArrayList<Integer> loots) {
        mScoreInformation.addLoots(loots);
    }

    /**
     * used to know frag number
     *
     * @return number of frag
     */
    public int getFragNumber() {
        return mScoreInformation.getNumberOfTargetsKilled();
    }

    /**
     * increase bullets fired number
     */
    public void bulletFired() {
        mScoreInformation.increaseNumberOfBulletsFired();
    }

    public void bulletMissed() {
        resetCombo();
        mScoreInformation.increaseNumberOfBulletsMissed();
    }

    public void earnExp(int expEarned) {
        mScoreInformation.increaseExpEarned(expEarned);
    }

    /**
     * used to get combo number
     *
     * @return current combo
     */
    public int getCurrentCombo() {
        return mScoreInformation.getCurrentCombo();
    }

    /**
     * return maximum combo during the game
     *
     * @return max combo number
     */
    public int getMaxCombo() {
        return mScoreInformation.getMaxCombo();
    }

    /**
     * increase combo if conditions are filled
     */
    public void stackCombo() {
        mScoreInformation.increaseCurrentCombo();
    }

    /**
     * reset current combo counter
     */
    public void resetCombo() {
        mScoreInformation.resetCurrentCombo();
    }

    /**
     * increase score
     *
     * @param amount score you want to add to the current one
     */
    public void increaseScore(int amount) {
        mScoreInformation.increaseScore(amount);
    }

    /**
     * get current score
     *
     * @return current score
     */
    public int getCurrentScore() {
        return mScoreInformation.getScore();
    }

    /**
     * set  score
     */
    public void setScore(int score) {
        mScoreInformation.setScore(score);
    }


    public int getBulletsFired() {
        return mScoreInformation.getmNumberOfBulletsFired();
    }

    public int getBulletsMissed() {
        return mScoreInformation.getNumberOfBulletsMissed();
    }

    public int getExpEarned() {
        return mScoreInformation.getExpEarned();
    }

    public int getCurrentTargetsNumber() {
        return mTargetableItems.size();
    }

    public ScoreInformation getScoreInformation() {
        return mScoreInformation;
    }

    public int getCurrentAmmunition() {
        return mWeapon.getCurrentAmmunition();
    }


    public HashMap<Integer, Integer> getLoot() {
        final HashMap<Integer, Integer> lootQuantities = new HashMap<Integer, Integer>();
        for (Integer inventoryItemType : mScoreInformation.getLoot()) {
            int oldValue = 0;
            if (lootQuantities.containsKey(inventoryItemType)) {
                oldValue = lootQuantities.get(inventoryItemType);
            }
            lootQuantities.put(inventoryItemType, oldValue + 1);
        }
        return lootQuantities;
    }

    public int getNumberOfLoots() {
        return mScoreInformation.getLoot().size();
    }

    public int getLastScoreAdded() {
        return mScoreInformation.getLastScoreAdded();
    }

}
