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
import com.nikhilnayak.games.octoshootar.mechanics.informations.GameInformationTutorial;
import com.nikhilnayak.games.octoshootar.model.DisplayableItemFactory;
import com.nikhilnayak.games.octoshootar.model.TargetableItem;

public class GameBehaviorTutorial extends GameBehaviorStandard {

    private IGameBehaviorTutorial mIGameBehaviorTutorial;

    protected GameInformationTutorial mGameInformation;

    @Override
    public void setGameInformation(GameInformation gameInformation) {
        super.setGameInformation(gameInformation);
        mGameInformation = (GameInformationTutorial) gameInformation;
    }


    @Override
    public void setInterface(IGameBehavior iGameBehavior) {
        super.setInterface(iGameBehavior);
        mIGameBehaviorTutorial = (IGameBehaviorTutorial) iGameBehavior;
    }

    @Override
    public void setCurrentPosition(float posX, float posY, float posZ) {
        mGameInformation.setCurrentPosition(posX, posY, posZ);
    }

    @Override
    public void spawn(int xRange, int yRange) {
    }

    public int getCurrentStep() {
        return mGameInformation.getCurrentStep();
    }

    public void onClick() {
        final int currentStep = mGameInformation.getCurrentStep();

        if (currentStep == GameInformationTutorial.STEP_KILL || currentStep == GameInformationTutorial.STEP_KILL_2) {
            fire();
        } else {
            final int nextStep = nextStep();

            if (nextStep == GameInformationTutorial.STEP_END) {
                mGameInformation.earnExp(8);
                mIGameBehavior.stop();
            }

            if (nextStep == GameInformationTutorial.STEP_AMMO_2) {
                mGameInformation.getWeapon().setCurrentAmmunition(1);
            }

            if (nextStep == GameInformationTutorial.STEP_TARGET || nextStep == GameInformationTutorial.STEP_TARGET_2) {
                if (mGameInformation.getCurrentStep() == GameInformationTutorial.STEP_TARGET) {
                    final float[] currentPosition = mGameInformation.getCurrentPosition();
                    final TargetableItem easyGhost = DisplayableItemFactory.createEasyGhost();
                    easyGhost.setX((int) currentPosition[0] + 5);
                    easyGhost.setY((int) currentPosition[1] + 7);
                    mGameInformation.addTargetableItem(easyGhost);
                } else if (mGameInformation.getCurrentStep() == GameInformationTutorial.STEP_TARGET_2) {
                    final float[] currentPosition = mGameInformation.getCurrentPosition();
                    final TargetableItem easyGhost = DisplayableItemFactory.createGhostWithHelmet();
                    easyGhost.setX((int) currentPosition[0] - 5);
                    easyGhost.setY((int) currentPosition[1] + 7);
                    mGameInformation.addTargetableItem(easyGhost);
                }
            }
        }
    }

    @Override
    protected void killTarget(TargetableItem currentTarget) {
        super.killTarget(currentTarget);
        nextStep();
    }

    private int nextStep() {
        final int nextStep = mGameInformation.nextStep();
        mIGameBehaviorTutorial.onNextStep();
        return nextStep;
    }

    public interface IGameBehaviorTutorial extends IGameBehavior {
        public void onNextStep();
    }

}
