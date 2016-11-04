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
import com.nikhilnayak.games.octoshootar.mechanics.informations.GameInformationDeathToTheKing;
import com.nikhilnayak.games.octoshootar.model.DisplayableItemFactory;
import com.nikhilnayak.games.octoshootar.model.TargetableItem;

public class GameBehaviorDeathToTheKing extends GameBehaviorTimeIncreasing {
    private GameInformationDeathToTheKing mGameInformation;
    private IGameBehaviorDeathToTheKing mIGameBehavior;

    public boolean hasKingAlreadyBeenSummoned() {
        return mGameInformation.isKingSummoned();
    }

    @Override
    public void setInterface(IGameBehavior iGameBehavior) {
        super.setInterface(iGameBehavior);
        mIGameBehavior = (IGameBehaviorDeathToTheKing) iGameBehavior;
    }

    @Override
    public void setGameInformation(GameInformation gameInformation) {
        super.setGameInformation(gameInformation);
        mGameInformation = (GameInformationDeathToTheKing) gameInformation;
    }

    @Override
    public void spawn(int xRange, int yRange) {
    }

    @Override
    public void onClick() {
        super.onClick();
        if (mGameInformation.isKingSummoned()) {
            fire();
        } else {
            mGameInformation.summonKing();
            mIGameBehavior.onKingSummon();
        }
    }


    @Override
    protected void killTarget(TargetableItem currentTarget) {
        super.killTarget(currentTarget);
        if (currentTarget.getType() == DisplayableItemFactory.TYPE_KING_GHOST) {
            mIGameBehavior.stop();
        }
    }

    public interface IGameBehaviorDeathToTheKing extends IGameBehavior {
        public void onKingSummon();
    }
}
