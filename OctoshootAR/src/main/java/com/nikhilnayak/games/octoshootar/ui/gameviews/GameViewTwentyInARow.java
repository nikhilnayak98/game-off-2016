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
import android.graphics.RectF;

import com.nikhilnayak.games.octoshootar.mechanics.engine.GameEngineTime;
import com.nikhilnayak.games.octoshootar.mechanics.engine.GameEngineTwentyInARow;

public class GameViewTwentyInARow extends GameViewTime {

    public GameViewTwentyInARow(Context c, GameEngineTime gameEngine) {
        super(c, gameEngine);
    }

    @Override
    public void onDrawing(Canvas c) {
        super.onDrawing(c);
        drawCurrentStack(c);
    }

    @Override
    protected void drawScore(Canvas canvas) {
        //don't draw score  
    }

    protected void drawCurrentStack(Canvas canvas) {
        final int currentStack = ((GameEngineTwentyInARow) mGameEngine).getCurrentStack();
        final String currentStackStr = String.valueOf(currentStack);
        final int stackLength = currentStackStr.length();
        final int radius = Math.max(mScreenWidth / (12 - stackLength), mScreenHeight / (12 - stackLength));

        //draw transparent overlay
        useTransparentBlackPainter();
        canvas.drawOval(new RectF(-radius, mScreenHeight - radius, radius, mScreenHeight + radius), mPaint);

        //draw Score
        useWhitePainter();
        mPaint.getTextBounds(currentStackStr, 0, currentStackStr.length(), mBounds);
        canvas.drawText(currentStackStr
                , mBounds.width() / 2 + radius / 4
                , mScreenHeight - radius / 4
                , mPaint);

    }

}
