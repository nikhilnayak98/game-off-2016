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

package com.nikhilnayak.games.octoshootar.mechanics.engine;

import android.content.Context;

import com.nikhilnayak.games.octoshootar.mechanics.behaviors.GameBehaviorMemorize;
import com.nikhilnayak.games.octoshootar.mechanics.behaviors.GameBehaviorStandard;
import com.nikhilnayak.games.octoshootar.mechanics.routine.Routine;
import com.nikhilnayak.games.octoshootar.ui.gameviews.GameView;
import com.nikhilnayak.games.octoshootar.ui.gameviews.GameViewMemorize;

public class GameEngineMemorize extends GameEngineStandard {
    private GameBehaviorMemorize mGameBehavior;
    private GameViewMemorize mGameView;

    public GameEngineMemorize(Context context, IGameEngine iGameEngine, GameBehaviorStandard gameBehavior) {
        super(context, iGameEngine, gameBehavior);
        mGameBehavior = (GameBehaviorMemorize) gameBehavior;
    }

    @Override
    protected void setGameView(GameView gameView) {
        super.setGameView(gameView);
        mGameView = (GameViewMemorize) gameView;
    }

    @Override
    public void setCameraAngle(float horizontal, float vertical) {
        super.setCameraAngle(horizontal, vertical);
        mGameBehavior.setWorldWindowSizeInDegress(horizontal, vertical);
    }

    @Override
    public void onRun(int routineType, Object obj) {
        switch (routineType) {
            case Routine.TYPE_RELOADER:
                mGameBehavior.reload();
                break;
            case Routine.TYPE_TICKER:
                mGameBehavior.nextMemorization();
                mGameView.showInstruction(true);
                break;

        }
    }

    public boolean isPlayerMemorizing() {
        return mGameBehavior.isPlayerMemorizing();
    }

    public String getCurrentMemorizationProgress() {
        return String.valueOf(mGameBehavior.getCurrentMemorizationStep() + 1) + "/" +
                String.valueOf(mGameBehavior.getMemorizationSteps());
    }

    public int getCurrentWave() {
        return mGameBehavior.getCurrentWave();
    }

    public int getCurrentGhostToMemorize() {
        return mGameBehavior.getCurrentGhostToMemorize();
    }

}
