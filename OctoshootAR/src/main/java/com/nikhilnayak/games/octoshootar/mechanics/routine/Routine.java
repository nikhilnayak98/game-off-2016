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

package com.nikhilnayak.games.octoshootar.mechanics.routine;

import android.os.Handler;

public class Routine extends Handler {
    public static final int TYPE_TICKER = 0x00000001;
    public static final int TYPE_RELOADER = 0x00000002;
    public static final int TYPE_SPAWNER = 0x00000003;

    final protected long mTickingTime;
    final protected Runnable mRunnable;
    final protected int mType;
    protected boolean mIsRunning;
    protected IRoutine mIRoutine;


    /**
     * Create a Routine
     *
     * @param tickingTime ticking time in millisecond
     */
    public Routine(int type, long tickingTime) {
        mType = type;
        mTickingTime = tickingTime;
        mIsRunning = false;
        mRunnable = new Runnable() {
            @Override
            public void run() {
                Routine.this.run();
                if (mIsRunning) {
                    postDelayed(this, mTickingTime);
                }
            }
        };
    }

    /**
     * Create a Routine
     *
     * @param tickingTime ticking time in millisecond
     */
    public Routine(int type, long tickingTime, IRoutine iRoutine) {
        this(type, tickingTime);
        mIRoutine = iRoutine;
    }

    public void setIRoutine(IRoutine iRoutine) {
        mIRoutine = iRoutine;
    }

    /**
     * Start the Routine
     */
    public void startRoutine() {
        if (!mIsRunning) {
            mIsRunning = true;
            postDelayed(mRunnable, mTickingTime);
        }
    }

    /**
     * Stop the Routine
     */
    public void stopRoutine() {
        mIsRunning = false;
        removeCallbacks(mRunnable);
    }

    public int getType() {
        return mType;
    }


    protected void run() {
        if (mIRoutine != null) {
            mIRoutine.onRun(getType(), null);
        }
    }

    public interface IRoutine {
        public void onRun(int routineType, Object obj);
    }
}
