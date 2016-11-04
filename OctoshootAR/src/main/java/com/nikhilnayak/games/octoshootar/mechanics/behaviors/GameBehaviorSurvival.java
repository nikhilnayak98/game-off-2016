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
import com.nikhilnayak.games.octoshootar.sound.GameSoundManager;
import com.nikhilnayak.games.octoshootar.mechanics.informations.GameInformationSurvival;
import com.nikhilnayak.games.octoshootar.model.TargetableItem;

public class GameBehaviorSurvival extends GameBehaviorTimeDecreasing {
    private static final int MAX_TARGET_EASY = 10;
    private static final int MAX_TARGET_HARD = 6;
    private static final int TIME_GAIN_EASY = 1000;
    private static final int TIME_GAIN_HARD = 800;
    private GameInformationSurvival mGameInformation;

    @Override
    public void setGameInformation(GameInformation gameInformation) {
        super.setGameInformation(gameInformation);
        mGameInformation = (GameInformationSurvival) gameInformation;
    }

    @Override
    public void spawn(int xRange, int yRange) {
        final int currentTargetNumber = mGameInformation.getCurrentTargetsNumber();
        final int difficulty = mGameInformation.getDifficulty();

        if (difficulty == GameInformationSurvival.DIFFICULTY_EASY && currentTargetNumber < MAX_TARGET_EASY) {
            spawnStandardBehavior(xRange / 2 + xRange / 10, yRange / 2 + yRange / 10);
            mIGameBehavior.onSoundRequest(GameSoundManager.SOUND_TYPE_LAUGH_RANDOM);
        } else if (difficulty == GameInformationSurvival.DIFFICULTY_HARD && currentTargetNumber < MAX_TARGET_HARD) {
            spawnHardBehavior(xRange / 2 + xRange / 10, yRange / 2 + yRange / 10);
            mIGameBehavior.onSoundRequest(GameSoundManager.SOUND_TYPE_LAUGH_RANDOM);
        } else if (difficulty == GameInformationSurvival.DIFFICULTY_HARDER && currentTargetNumber < MAX_TARGET_HARD) {
            spawnHarderBehavior(xRange / 2 + 2 * xRange / 10, yRange / 2 + 2 * yRange / 10);
            mIGameBehavior.onSoundRequest(GameSoundManager.SOUND_TYPE_LAUGH_RANDOM);
        } else if (currentTargetNumber < MAX_TARGET_HARD) {
            spawnHardestBehavior(xRange / 2 + 2 * xRange / 10, yRange / 2 + 2 * yRange / 10);
            mIGameBehavior.onSoundRequest(GameSoundManager.SOUND_TYPE_LAUGH_RANDOM);
        }
    }

    @Override
    protected void killTarget(TargetableItem currentTarget) {
        super.killTarget(currentTarget);
        final int difficulty = mGameInformation.getDifficulty();
        int bonusTime = TIME_GAIN_EASY;

        if (difficulty != GameInformationSurvival.DIFFICULTY_EASY) {
            bonusTime = TIME_GAIN_HARD;
        }

        mGameInformation.setCurrentTime(mGameInformation.getCurrentTime() + bonusTime);
    }
}
