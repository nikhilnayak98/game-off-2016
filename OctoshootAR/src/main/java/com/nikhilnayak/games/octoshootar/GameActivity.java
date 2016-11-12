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

package com.nikhilnayak.games.octoshootar;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.ViewGroup;

import com.nikhilnayak.games.octoshootar.mechanics.informations.GameInformation;
import com.nikhilnayak.games.octoshootar.mechanics.engine.GameEngine;
import com.nikhilnayak.games.octoshootar.mechanics.engine.GameEngineFactory;
import com.nikhilnayak.games.octoshootar.model.mode.GameMode;
import com.nikhilnayak.games.octoshootar.ui.fragments.GameScoreFragment;

public class GameActivity extends ARActivity implements GameEngine.IGameEngine {

    private static final String BUNDLE_GAME_INFORMATION = "GameActivity.Bundle.GameInformation";
    public static final String EXTRA_GAME_MODE = "ExtraGameModeFromChooser";
    private final ViewGroup.LayoutParams mLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
            , ViewGroup.LayoutParams.WRAP_CONTENT);

    private GameEngine mGameEngine;
    private GameInformation mLastGameInformationSaved;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        if (savedInstanceState != null && savedInstanceState.containsKey(BUNDLE_GAME_INFORMATION)) {
            mLastGameInformationSaved = savedInstanceState.getParcelable(BUNDLE_GAME_INFORMATION);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mGameEngine != null) {
            outState.putParcelable(BUNDLE_GAME_INFORMATION, mGameEngine.getGameInformation());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mGameEngine != null) {
            mGameEngine.pause();
        }
    }

    @Override
    void onSmoothCoordinateChanged(float[] smoothCoordinate) {
        mGameEngine.changePosition((float) Math.toDegrees(smoothCoordinate[0]),
                (float) Math.toDegrees(smoothCoordinate[2]), (float) Math.toDegrees(smoothCoordinate[1]));
    }

    @Override
    void onCameraReady(float horizontal, float vertical) {
        //if no gameBehavior choose the one corresponding to the right gameMode
        final Intent intent = getIntent();
        if (mGameEngine != null) {
            configureGameEngine(horizontal, vertical);
            mGameEngine.resume();
        } else if (mLastGameInformationSaved != null) {
            mGameEngine = GameEngineFactory.restore(this, this, mLastGameInformationSaved);
            configureGameEngine(horizontal, vertical);
            mGameEngine.resume();
        } else if (intent != null && intent.hasExtra(EXTRA_GAME_MODE)) {
            mGameEngine = GameEngineFactory.create(this, this, (GameMode) intent.getParcelableExtra(EXTRA_GAME_MODE));
            configureGameEngine(horizontal, vertical);
            mGameEngine.start();
        } else {
            finish();
        }

    }

    private void configureGameEngine(float horizontal, float vertical) {
        mGameEngine.setCameraAngle(horizontal, vertical);
        addContentView(mGameEngine.getGameView(), mLayoutParams);
        addContentView(mGameEngine.getAnimationLayer(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void onGameEngineStop() {
        final Intent scoreIntent = new Intent(this, HomeActivity.class);
        scoreIntent.putExtra(GameScoreFragment.EXTRA_GAME_INFORMATION, mGameEngine.getGameInformation());
        setResult(RESULT_OK, scoreIntent);
        finish();
    }

}
