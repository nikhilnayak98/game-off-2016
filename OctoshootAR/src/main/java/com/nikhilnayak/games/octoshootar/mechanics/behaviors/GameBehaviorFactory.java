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

import com.nikhilnayak.games.octoshootar.sound.GameSoundManager;
import com.nikhilnayak.games.octoshootar.model.TargetableItem;

public class GameBehaviorFactory {
    public static final int DEFAULT_MAX_TARGET = 10;

    public static GameBehaviorTimeDecreasing createSprint() {
        return new GameBehaviorTimeDecreasing() {
            @Override
            public void spawn(int xRange, int yRange) {
                if (mGameInformation.getCurrentTargetsNumber() < GameBehaviorFactory.DEFAULT_MAX_TARGET) {
                    final int ghostType = TargetableItem.randomGhostTypeEasy();
                    spawnGhost(ghostType, xRange / 2 + xRange / 10, yRange / 2 + yRange / 10);
                    mIGameBehavior.onSoundRequest(GameSoundManager.SOUND_TYPE_LAUGH_RANDOM);
                }
            }
        };
    }

    public static GameBehaviorTimeDecreasing createMarathon() {
        return new GameBehaviorTimeDecreasing() {
            @Override
            public void spawn(int xRange, int yRange) {
                if (mGameInformation.getCurrentTargetsNumber() < GameBehaviorFactory.DEFAULT_MAX_TARGET) {
                    final int ghostType = TargetableItem.randomGhostType();
                    spawnGhost(ghostType, xRange / 2 + xRange / 10, yRange / 2 + yRange / 10);
                    mIGameBehavior.onSoundRequest(GameSoundManager.SOUND_TYPE_LAUGH_RANDOM);
                }
            }
        };
    }

    public static GameBehaviorTutorial createTutorial() {
        return new GameBehaviorTutorial();
    }

    public static GameBehaviorSurvival createSurvival() {
        return new GameBehaviorSurvival();
    }

    public static GameBehaviorDeathToTheKing createDeathToTheKing() {
        return new GameBehaviorDeathToTheKing();
    }

    public static GameBehaviorTwentyInARow createTwentyInARow() {
        return new GameBehaviorTwentyInARow();
    }

    public static GameBehaviorMemorize createMemorize() {
        return new GameBehaviorMemorize();
    }
}
