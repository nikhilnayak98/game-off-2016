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

package com.nikhilnayak.games.octoshootar.ui.gameviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nikhilnayak.games.octoshootar.mechanics.engine.GameEngineMemorize;
import com.nikhilnayak.games.octoshootar.R;
import com.nikhilnayak.games.octoshootar.mechanics.engine.GameEngineStandard;
import com.nikhilnayak.games.octoshootar.model.DisplayableItemFactory;


public class GameViewMemorize extends GameViewStandard {
    private GameEngineMemorize mGameEngine;
    private TextView mInstructionTextView;
    private ImageView mInstructionImageView;

    public GameViewMemorize(Context c, GameEngineStandard gameEngine) {
        super(c, gameEngine);
        mGameEngine = (GameEngineMemorize) gameEngine;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (mInstructionTextView == null) {
            mInstructionTextView = new TextView(getContext());
            mInstructionTextView.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
            mInstructionTextView.setTextColor(getResources().getColor(R.color.white));
            mInstructionTextView.setTypeface(null, Typeface.BOLD);
            mInstructionTextView.setBackgroundResource(R.color.alpha_shadow);
            mInstructionTextView.setGravity(Gravity.CENTER);
            final int padding = getResources().getDimensionPixelSize(R.dimen.default_padding);
            mInstructionTextView.setPadding(padding, 2 * padding, padding, padding);

            final RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            mInstructionTextView.setLayoutParams(layoutParams);
        }

        if (mInstructionImageView == null) {
            mInstructionImageView = new ImageView(getContext());
            final RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            mInstructionImageView.setLayoutParams(layoutParams);
        }

        mAnimationLayer.addView(mInstructionTextView);
        mAnimationLayer.addView(mInstructionImageView);
        showInstruction(false);
    }

    @Override
    protected void drawCrossHair(Canvas canvas) {
        if (!mGameEngine.isPlayerMemorizing()) {
            super.drawCrossHair(canvas);
        }
    }

    @Override
    protected void drawCombo(Canvas canvas) {
        if (!mGameEngine.isPlayerMemorizing()) {
            super.drawCombo(canvas);
        }
    }

    @Override
    protected void drawScore(Canvas canvas) {
        final int currentWave = mGameEngine.getCurrentWave();
        final String currentWaveStr = getResources().getString(R.string.game_mode_memorize_wave, currentWave);
        resetPainter();
        useGreenPainter();
        mPaint.getTextBounds(currentWaveStr, 0, currentWaveStr.length(), mBounds);
        mPaint.getTextBounds(currentWaveStr, 0, currentWaveStr.length(), mBounds);
        canvas.drawText(currentWaveStr
                , mBounds.width() / 2 + mPadding
                , mScreenHeight - 2 * mPadding
                , mPaint);
    }


    private int getImageResourceByGhostType(int ghostType) {
        int drawableId = R.drawable.ghost;
        switch (ghostType) {
            case DisplayableItemFactory.TYPE_BABY_GHOST:
                drawableId = R.drawable.baby_ghost;
                break;
            case DisplayableItemFactory.TYPE_HIDDEN_GHOST:
                drawableId = R.drawable.hidden_ghost;
                break;
            case DisplayableItemFactory.TYPE_KING_GHOST:
                drawableId = R.drawable.king_ghost;
                break;
            case DisplayableItemFactory.TYPE_BLOND_GHOST:
                drawableId = R.drawable.blond_ghost;
                break;
            case DisplayableItemFactory.TYPE_GHOST_WITH_HELMET:
                drawableId = R.drawable.ghost_with_helmet;
                break;
        }
        return drawableId;
    }

    public void showInstruction(boolean changeInstruction) {
        if (mGameEngine.isPlayerMemorizing()) {
            mInstructionTextView.setVisibility(VISIBLE);
            mInstructionImageView.setVisibility(VISIBLE);
            final String instruction = getResources().getString(R.string.game_mode_memorize_instruction,
                    mGameEngine.getCurrentMemorizationProgress());
            if (changeInstruction) {
                mAnimationLayer.changeTextView(mInstructionTextView, instruction);
            } else {
                mInstructionTextView.setText(instruction);
                mAnimationLayer.showTextView(mInstructionTextView);
            }
            mInstructionImageView.setImageResource(getImageResourceByGhostType(mGameEngine.getCurrentGhostToMemorize()));
            final Animation fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
            mInstructionImageView.startAnimation(fadeIn);
        } else {
            mInstructionTextView.setVisibility(GONE);
            mInstructionImageView.setVisibility(GONE);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        mAnimationLayer.removeView(mInstructionImageView);
        mAnimationLayer.removeView(mInstructionTextView);
        super.onDetachedFromWindow();
    }

}
