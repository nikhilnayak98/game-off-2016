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
import android.view.View;

import com.nikhilnayak.games.octoshootar.mechanics.behaviors.GameBehavior;
import com.nikhilnayak.games.octoshootar.mechanics.informations.GameInformation;
import com.nikhilnayak.games.octoshootar.model.DisplayableItem;
import com.nikhilnayak.games.octoshootar.sound.GameSoundManager;

import java.util.ArrayList;
import java.util.List;

import com.nikhilnayak.games.octoshootar.mechanics.routine.Routine;
import com.nikhilnayak.games.octoshootar.model.TargetableItem;
import com.nikhilnayak.games.octoshootar.ui.AnimationLayer;
import com.nikhilnayak.games.octoshootar.ui.gameviews.GameView;

public abstract class GameEngine implements View.OnClickListener, GameBehavior.IGameBehavior, Routine.IRoutine {
    public static final int STATE_STOP = 0x00000001;
    public static final int STATE_RUNNING = 0x00000002;
    public static final int STATE_PAUSED = 0x00000003;

    protected int mCurrentState;

    final protected GameSoundManager mGameSoundManager;
    final protected IGameEngine mInterface;
    final protected GameBehavior mGameBehavior;
    final protected ArrayList<Routine> mRoutines;
    private GameView mGameView;
    protected AnimationLayer mAnimationLayer;

    public GameEngine(final Context context, IGameEngine iGameEngine, GameBehavior gameBehavior) {
        mGameSoundManager = new GameSoundManager(context);
        mRoutines = new ArrayList<Routine>();
        mCurrentState = STATE_STOP;
        mInterface = iGameEngine;
        mGameBehavior = gameBehavior;
        mAnimationLayer = new AnimationLayer(context);
    }

    @Override
    public void onClick(View v) {
        mGameBehavior.onClick();
    }

    /**
     * start the game, should be called only at the beginning once.
     */
    public void start() {
        startRoutines();
        mCurrentState = STATE_RUNNING;
    }

    /**
     * pause the game.
     */
    public void pause() {
        mGameSoundManager.stopAllSounds();
        stopRoutines();
        mCurrentState = STATE_PAUSED;
    }

    /**
     * resume the game
     */
    public void resume() {
        startRoutines();
        mCurrentState = STATE_RUNNING;
    }

    /**
     * stop the game, should not be called manually
     */
    public void stop() {
        mGameSoundManager.stopAllSounds();
        stopRoutines();
        mCurrentState = STATE_STOP;
        mInterface.onGameEngineStop();
    }

    protected void setGameView(GameView gameView) {
        mGameView = gameView;
        mGameView.setOnClickListener(this);
        mGameView.setAnimationLayer(mAnimationLayer);
    }

    protected void startRoutines() {
        for (Routine routine : mRoutines) {
            routine.startRoutine();
        }
    }

    protected void stopRoutines() {
        for (Routine routine : mRoutines) {
            routine.stopRoutine();
        }
    }

    protected void addRoutine(Routine routine) {
        routine.setIRoutine(this);
        mRoutines.add(routine);
    }

    public void changePosition(float posX, float posY, float posZ) {
        mGameBehavior.setCurrentPosition(posX, posY, posZ);
        mGameView.invalidate();
    }

    public GameInformation getGameInformation() {
        return mGameBehavior.getGameInformation();
    }

    public void setCameraAngle(float horizontal, float vertical) {
        mGameView.setCameraAngleInDegree(horizontal, vertical);
    }

    public float[] getCurrentPosition() {
        return mGameBehavior.getCurrentPosition();
    }

    public List<DisplayableItem> getItemsForDisplay() {
        return mGameBehavior.getItemsForDisplay();
    }


    public TargetableItem getCurrentTarget() {
        return mGameBehavior.getCurrentTarget();
    }

    public void setCurrentTarget(TargetableItem t) {
        mGameBehavior.setCurrentTarget(t);
    }

    public void removeTarget() {
        mGameBehavior.removeTarget();
    }

    public int getLastScoreAdded() {
        return mGameBehavior.getLastScoreAdded();
    }

    public GameView getGameView() {
        return mGameView;
    }

    public AnimationLayer getAnimationLayer() {
        return mAnimationLayer;
    }

    @Override
    public void onSoundRequest(int soundType) {
        mGameSoundManager.requestSound(soundType);
    }

    public interface IGameEngine {
        abstract public void onGameEngineStop();
    }
}
