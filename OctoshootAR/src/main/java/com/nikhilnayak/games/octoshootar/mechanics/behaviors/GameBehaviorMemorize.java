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

package com.nikhilnayak.games.octoshootar.mechanics.behaviors;

import com.nikhilnayak.games.octoshootar.mechanics.informations.GameInformation;
import com.nikhilnayak.games.octoshootar.mechanics.informations.GameInformationMemorize;
import com.nikhilnayak.games.octoshootar.sound.GameSoundManager;
import com.nikhilnayak.games.octoshootar.model.TargetableItem;

public class GameBehaviorMemorize extends GameBehaviorStandard {
    private GameInformationMemorize mGameInformation;
    private float mWorldWindowWidthInDegree;
    private float mWorldWindowHeightInDegree;

    @Override
    public void setGameInformation(GameInformation gameInformation) {
        super.setGameInformation(gameInformation);
        mGameInformation = (GameInformationMemorize) gameInformation;
    }

    public void setWorldWindowSizeInDegress(float horizontalLimitInDegree, float verticalLimitInDegree) {
        mWorldWindowWidthInDegree = horizontalLimitInDegree;
        mWorldWindowHeightInDegree = verticalLimitInDegree;
    }

    @Override
    public void onClick() {
        if (mGameInformation.isPlayerKilling()) {
            super.onClick();
        }
    }

    @Override
    public void spawn(int xRange, int yRange) {
    }

    @Override
    protected void killTarget(TargetableItem currentTarget) {
        super.killTarget(currentTarget);
        if (currentTarget.getType() != mGameInformation.getCurrentGhostType()) {
            mIGameBehavior.stop();
        } else {
            nextTarget();
        }
    }

    private void nextTarget() {
        final int currentCursor = mGameInformation.increaseCursor();
        if (currentCursor == mGameInformation.getWaveSize()) {
            mGameInformation.setState(GameInformationMemorize.STATE_MEMORIZING);
            mGameInformation.generateNextWave();
        }
    }

    public void nextMemorization() {
        if (mGameInformation.isPlayerMemorizing()) {
            final int memorizationSteps = mGameInformation.getWaveSize();
            final int currentStep = mGameInformation.getCursor();
            if (currentStep == memorizationSteps - 1) {
                mGameInformation.setState(GameInformationMemorize.STATE_KILLING);
                mGameInformation.resetCursor();
                summonCurrentWave();
            } else {
                mGameInformation.increaseCursor();
            }
        }
    }

    public boolean isPlayerMemorizing() {
        return mGameInformation.isPlayerMemorizing();
    }

    public int getCurrentMemorizationStep() {
        return mGameInformation.getCursor();
    }

    public int getMemorizationSteps() {
        return mGameInformation.getWaveSize();
    }

    public int getCurrentWave() {
        return mGameInformation.getCurrentWaveNumber();
    }

    public int getCurrentGhostToMemorize() {
        return mGameInformation.getCurrentGhostType();
    }

    private void summonCurrentWave() {
        for (Integer ghostType : mGameInformation.getCurrentWave()) {
            spawnGhost(ghostType, (int) mWorldWindowWidthInDegree / 2, (int) mWorldWindowHeightInDegree / 2);
            mIGameBehavior.onSoundRequest(GameSoundManager.SOUND_TYPE_LAUGH_RANDOM);
        }
    }

}
