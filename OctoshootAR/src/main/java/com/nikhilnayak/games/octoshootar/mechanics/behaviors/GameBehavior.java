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

package com.nikhilnayak.games.octoshootar.mechanics.behaviors;

import com.nikhilnayak.games.octoshootar.mechanics.informations.GameInformation;
import com.nikhilnayak.games.octoshootar.model.DisplayableItem;

import java.util.List;

import com.nikhilnayak.games.octoshootar.model.TargetableItem;

public interface GameBehavior {

    public void setGameInformation(GameInformation gameInformation);

    public GameInformation getGameInformation();

    public void setCurrentPosition(float posX, float posY, float posZ);

    public float[] getCurrentPosition();

    public List<DisplayableItem> getItemsForDisplay();

    public TargetableItem getCurrentTarget();

    public void setCurrentTarget(TargetableItem t);

    public int getLastScoreAdded();

    public void removeTarget();

    public void setInterface(IGameBehavior iGameBehavior);

    public void onClick();

    public interface IGameBehavior {
        public abstract void stop();

        public abstract void onTargetKilled(TargetableItem target);

        public abstract void onSoundRequest(int soundType);
    }
}
