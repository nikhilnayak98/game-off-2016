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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nikhilnayak.games.octoshootar.R;
import com.nikhilnayak.games.octoshootar.mechanics.engine.GameEngineTime;

public class GameViewTimeDecreasing extends GameViewTime {
    protected TextView mRedCountDownTextView;
    protected RelativeLayout.LayoutParams mRedCountDownLayoutParam;

    public GameViewTimeDecreasing(Context c, GameEngineTime gameEngine) {
        super(c, gameEngine);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (mRedCountDownLayoutParam == null) {
            mRedCountDownTextView = new TextView(getContext());
            mRedCountDownTextView.setGravity(Gravity.CENTER_HORIZONTAL);
            mRedCountDownTextView.setTypeface(null, Typeface.BOLD);
            mRedCountDownTextView.setTextSize((int) (mFontSize * 1.25));
            mRedCountDownTextView.setTextColor(getResources().getColor(R.color.holo_red));
        }

        mAnimationLayer.addView(mRedCountDownTextView);
    }

    @Override
    protected void onDetachedFromWindow() {
        mAnimationLayer.removeView(mRedCountDownTextView);
        super.onDetachedFromWindow();
    }


    /**
     * draw time, in red if time < 10 sec else in green
     *
     * @param canvas canvas from View.onDraw method
     */
    protected void drawTimer(Canvas canvas) {
        final long millis = mGameEngine.getCurrentTime();
        final int seconds = (int) (millis / 1000) - 1;
        final String remainingTime = String.format(mTimeString, seconds);
        resetPainter();
        if (seconds > 5) {
            if (!mRedCountDownTextView.getText().equals("")) mRedCountDownTextView.setText("");
            super.drawTimer(canvas);
        } else if (seconds >= 0) {
            final Animation currentAnimation = mRedCountDownTextView.getAnimation();
            final String mayBeANewValue = String.valueOf(seconds);
            final String currentValue = String.valueOf(mRedCountDownTextView.getText());
            if (currentValue != null && !currentValue.equals(mayBeANewValue) && (currentAnimation == null || currentAnimation.hasEnded())) {
                if (mRedCountDownLayoutParam == null) {
                    mRedCountDownLayoutParam = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    mRedCountDownLayoutParam.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    mRedCountDownLayoutParam.setMargins(0,
                            mScreenHeight / 2 - mRedCountDownTextView.getHeight() - mCrossHairs.getHeight() / 2, 0, 0);
                    mRedCountDownTextView.setLayoutParams(mRedCountDownLayoutParam);
                }
                mAnimationLayer.changeTextView(mRedCountDownTextView, mayBeANewValue);
            }
        }
    }
}
