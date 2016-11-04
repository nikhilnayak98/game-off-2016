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

public class GameViewTime extends GameViewStandard {
    protected GameEngineTime mGameEngine;

    public GameViewTime(Context c, GameEngineTime gameEngine) {
        super(c, gameEngine);
        mGameEngine = gameEngine;
    }

    @Override
    public void onDrawing(Canvas c) {
        super.onDrawing(c);
        drawTimer(c);
    }

    /**
     * draw time, in red if time < 10 sec else in green
     *
     * @param canvas canvas from View.onDraw method
     */
    protected void drawTimer(Canvas canvas) {
        final long millis = mGameEngine.getCurrentTime();
        final int seconds = (int) (millis / 1000);
        final String remainingTime = String.valueOf(seconds);
        final int radius = Math.max(mTimerBitmap.getWidth(), mTimerBitmap.getHeight()) + (int) mPadding;
        resetPainter();

        //draw transparent overlay
        useTransparentBlackPainter();
        canvas.drawOval(new RectF(-radius, -radius, radius, radius), mPaint);

        //draw icon
        useWhitePainter();
        canvas.drawBitmap(mTimerBitmap, 0, 0, mPaint);

        //draw time
        mPaint.getTextBounds(remainingTime, 0, remainingTime.length(), mBounds);
        canvas.drawText(remainingTime
                , mPadding + radius
                , radius
                , mPaint);

    }

}
