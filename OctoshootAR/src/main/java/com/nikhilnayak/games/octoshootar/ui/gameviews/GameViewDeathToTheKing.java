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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nikhilnayak.games.octoshootar.mechanics.engine.GameEngineDeathToTheKing;
import com.nikhilnayak.games.octoshootar.R;
import com.nikhilnayak.games.octoshootar.mechanics.engine.GameEngineTime;

public class GameViewDeathToTheKing extends GameViewTime {
    private GameEngineDeathToTheKing mGameEngine;
    private TextView mInstruction;

    public GameViewDeathToTheKing(Context c, GameEngineTime gameEngine) {
        super(c, gameEngine);
        mGameEngine = (GameEngineDeathToTheKing) gameEngine;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (mGameEngine.hasTheKingAlreadyBeenSummoned()) return;

        //Create a text view supposed to display tuto messages
        if (mInstruction == null) {
            mInstruction = new TextView(getContext());
            mInstruction.setTextAppearance(getContext(), android.R.style.TextAppearance_Large);
            mInstruction.setTextColor(getResources().getColor(R.color.white));
            mInstruction.setTypeface(null, Typeface.BOLD);
            mInstruction.setBackgroundResource(R.color.alpha_shadow);
            mInstruction.setGravity(Gravity.CENTER);
            final int padding = getResources().getDimensionPixelSize(R.dimen.default_padding);
            mInstruction.setPadding(padding, 2 * padding, padding, padding);

            final RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            mInstruction.setLayoutParams(layoutParams);
            mInstruction.setText(R.string.game_mode_death_to_the_king_instruction);
        }

        mAnimationLayer.addView(mInstruction);
        mAnimationLayer.showTextView(mInstruction);
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mInstruction != null) mAnimationLayer.removeView(mInstruction);
        super.onDetachedFromWindow();
    }

    public void hideInstruction() {
        if (mInstruction != null) {
            mAnimationLayer.hideTextView(mInstruction);
        }
    }

    @Override
    protected void drawTimer(Canvas canvas) {
        if (mGameEngine.hasTheKingAlreadyBeenSummoned()) {
            super.drawTimer(canvas);
        }
    }
}


