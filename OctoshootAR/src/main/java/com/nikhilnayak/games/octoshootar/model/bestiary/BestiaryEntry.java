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

package com.nikhilnayak.games.octoshootar.model.bestiary;

import com.nikhilnayak.games.octoshootar.model.TargetableItem;

public class BestiaryEntry {
    private int mTitleResourceId;
    private TargetableItem mTargetableItem;
    private int mImageResourceId;


    public void setTargetableItem(TargetableItem targetableItem) {
        mTargetableItem = targetableItem;
    }

    public String getHealth() {
        return String.valueOf(mTargetableItem.getHealth());
    }

    public String getExpValue() {
        return String.valueOf(mTargetableItem.getExpPoint());
    }

    public String getPointValue() {
        return String.valueOf(mTargetableItem.getBasePoint());
    }

    public void setImageResourceId(int resourceId) {
        mImageResourceId = resourceId;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public void setTitleResourceId(int resourceId) {
        mTitleResourceId = resourceId;
    }

    public int getTitleResourceId() {
        return mTitleResourceId;
    }
}
