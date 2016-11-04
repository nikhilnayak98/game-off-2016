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

import com.nikhilnayak.games.octoshootar.mechanics.behaviors.GameBehaviorDeathToTheKing;
import com.nikhilnayak.games.octoshootar.mechanics.behaviors.GameBehaviorTime;
import com.nikhilnayak.games.octoshootar.mechanics.routine.Routine;
import com.nikhilnayak.games.octoshootar.ui.gameviews.GameView;
import com.nikhilnayak.games.octoshootar.ui.gameviews.GameViewDeathToTheKing;

public class GameEngineDeathToTheKing extends GameEngineTime implements GameBehaviorDeathToTheKing.IGameBehaviorDeathToTheKing {
    private GameBehaviorDeathToTheKing mGameBehavior;
    private GameViewDeathToTheKing mGameView;

    public GameEngineDeathToTheKing(Context context, IGameEngine iGameEngine, GameBehaviorTime gameBehavior) {
        super(context, iGameEngine, gameBehavior);
        mGameBehavior = (GameBehaviorDeathToTheKing) gameBehavior;
    }

    @Override
    protected void setGameView(GameView gameView) {
        super.setGameView(gameView);
        mGameView = (GameViewDeathToTheKing) gameView;
    }

    @Override
    public void onRun(int routineType, Object obj) {
        switch (routineType) {
            case Routine.TYPE_RELOADER:
                mGameBehavior.reload();
                break;
            case Routine.TYPE_TICKER:
                mGameBehavior.tick((Long) obj);
                break;

        }
    }

    public boolean hasTheKingAlreadyBeenSummoned() {
        return mGameBehavior.hasKingAlreadyBeenSummoned();
    }

    @Override
    public void onKingSummon() {
        mGameView.hideInstruction();
    }
}
