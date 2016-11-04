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

package com.nikhilnayak.games.octoshootar.ui.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nikhilnayak.games.octoshootar.model.mode.GameMode;
import com.nikhilnayak.games.octoshootar.R;
import com.nikhilnayak.games.octoshootar.ui.adapter.GameModeViewAdapter;

public class GameModeView extends RelativeLayout {

    private final TextView mGameModeDescription;
    private final ImageView mGameModeImage;
    private GameMode mModel;

    public GameModeView(Context context) {
        this(context, null);
    }

    public GameModeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setBackgroundResource(R.drawable.card_shadow);
        setClickable(true);

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_game_mode, this, true);

        mGameModeImage = (ImageView) getChildAt(0);
        mGameModeDescription = (TextView) getChildAt(1);
    }

    /**
     * get GameMode
     *
     * @return mGameEngine
     */
    public GameMode getModel() {
        return mModel;
    }

    /**
     * set model to the view to configure rules and image
     *
     * @param model GameMode
     */
    public void setModel(GameMode model) {
        mModel = model;
        mGameModeImage.setImageResource(mModel.getImage());
    }

    public void setModelForLeaderboard(GameMode model) {
        mModel = model;
        mGameModeImage.setImageResource(mModel.getImage());
        mGameModeDescription.setText(mModel.getLeaderboardDescriptionStringId());
    }

    /**
     * set a listener for game mode selection
     *
     * @param listener
     */
    public void setGameModeSelectedListener(final GameModeViewAdapter.Listener listener) {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onGameModeSelected(GameModeView.this);
            }
        });
    }

    public void setGameModeEnabled(boolean isAllowed) {
        this.setEnabled(isAllowed);
        this.setClickable(isAllowed);
        this.setBackgroundResource(isAllowed ? R.drawable.card_shadow : R.drawable.card_shadow_disable);
        mGameModeDescription.setText(isAllowed ? mModel.getTitle() : mModel.getRequiredMessage());
    }
}
