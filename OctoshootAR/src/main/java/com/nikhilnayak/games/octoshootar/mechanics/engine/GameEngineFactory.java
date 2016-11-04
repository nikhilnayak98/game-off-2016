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

package com.nikhilnayak.games.octoshootar.mechanics.engine;


import android.content.Context;

import com.nikhilnayak.games.octoshootar.mechanics.behaviors.GameBehaviorFactory;
import com.nikhilnayak.games.octoshootar.mechanics.behaviors.GameBehaviorMemorize;
import com.nikhilnayak.games.octoshootar.mechanics.behaviors.GameBehaviorSurvival;
import com.nikhilnayak.games.octoshootar.mechanics.behaviors.GameBehaviorTutorial;
import com.nikhilnayak.games.octoshootar.mechanics.behaviors.GameBehaviorTwentyInARow;
import com.nikhilnayak.games.octoshootar.mechanics.informations.GameInformation;
import com.nikhilnayak.games.octoshootar.mechanics.informations.GameInformationDeathToTheKing;
import com.nikhilnayak.games.octoshootar.mechanics.informations.GameInformationMemorize;
import com.nikhilnayak.games.octoshootar.mechanics.informations.GameInformationSurvival;
import com.nikhilnayak.games.octoshootar.mechanics.informations.GameInformationTime;
import com.nikhilnayak.games.octoshootar.mechanics.informations.GameInformationTutorial;
import com.nikhilnayak.games.octoshootar.mechanics.informations.GameInformationTwentyInARow;
import com.nikhilnayak.games.octoshootar.mechanics.routine.RoutineTicker;
import com.nikhilnayak.games.octoshootar.model.mode.GameMode;
import com.nikhilnayak.games.octoshootar.model.mode.GameModeDeathToTheKing;
import com.nikhilnayak.games.octoshootar.model.weapon.Weapon;
import com.nikhilnayak.games.octoshootar.model.weapon.WeaponFactory;
import com.nikhilnayak.games.octoshootar.ui.gameviews.GameViewDeathToTheKing;
import com.nikhilnayak.games.octoshootar.ui.gameviews.GameViewMemorize;
import com.nikhilnayak.games.octoshootar.ui.gameviews.GameViewTime;
import com.nikhilnayak.games.octoshootar.ui.gameviews.GameViewTimeDecreasing;
import com.nikhilnayak.games.octoshootar.ui.gameviews.GameViewTutorial;
import com.nikhilnayak.games.octoshootar.ui.gameviews.GameViewTwentyInARow;
import com.nikhilnayak.games.octoshootar.mechanics.behaviors.GameBehaviorDeathToTheKing;
import com.nikhilnayak.games.octoshootar.mechanics.behaviors.GameBehaviorTime;
import com.nikhilnayak.games.octoshootar.mechanics.routine.Routine;
import com.nikhilnayak.games.octoshootar.model.mode.GameModeFactory;

public class GameEngineFactory {
    private static final long DEFAULT_SPAWNING_TIME = 1000;
    private static final long TWENTY_IN_A_ROW_SPAWNING_TIME = 800;
    private static final long DEFAULT_TICKING_TIME = 1000;
    private static final long DEFAULT_STARTING_TIME = 30000;

    public static GameEngine create(final Context context, GameEngine.IGameEngine iGameEngine, GameMode gameMode) {
        GameEngine gameEngine = null;
        switch (gameMode.getType()) {
            case GameModeFactory.GAME_TYPE_REMAINING_TIME:
                gameEngine = createSprintOrMarathon(context, gameMode, iGameEngine);
                break;

            case GameModeFactory.GAME_TYPE_DEATH_TO_THE_KING:
                gameEngine = createDeathToTheKing(context, (GameModeDeathToTheKing) gameMode, iGameEngine);
                break;

            case GameModeFactory.GAME_TYPE_SURVIVAL:
                gameEngine = createSurvival(context, gameMode, iGameEngine);
                break;

            case GameModeFactory.GAME_TYPE_TUTORIAL:
                gameEngine = createTutorial(context, gameMode, iGameEngine);
                break;

            case GameModeFactory.GAME_TYPE_TWENTY_IN_A_ROW:
                gameEngine = createTwentyInARow(context, gameMode, iGameEngine);
                break;

            case GameModeFactory.GAME_TYPE_MEMORIZE:
                gameEngine = createMemorize(context, gameMode, iGameEngine);
                break;

        }
        return gameEngine;
    }

    public static GameEngine restore(final Context context, GameEngine.IGameEngine iGameEngine, GameInformation gameInformation) {
        GameEngine gameEngine = null;
        final int gameModeType = gameInformation.getGameMode().getType();
        switch (gameModeType) {
            case GameModeFactory.GAME_TYPE_REMAINING_TIME:
                gameEngine = createSprintOrMarathon(context, iGameEngine, (GameInformationTime) gameInformation);
                break;

            case GameModeFactory.GAME_TYPE_DEATH_TO_THE_KING:
                gameEngine = createDeathToTheKing(context, iGameEngine, (GameInformationTime) gameInformation);
                break;

            case GameModeFactory.GAME_TYPE_SURVIVAL:
                gameEngine = createSurvival(context, iGameEngine, (GameInformationTime) gameInformation);
                break;

            case GameModeFactory.GAME_TYPE_TUTORIAL:
                gameEngine = createTutorial(context, iGameEngine, (GameInformationTutorial) gameInformation);
                break;

            case GameModeFactory.GAME_TYPE_TWENTY_IN_A_ROW:
                gameEngine = createTwentyInARow(context, iGameEngine, (GameInformationTwentyInARow) gameInformation);
                break;

            case GameModeFactory.GAME_TYPE_MEMORIZE:
                gameEngine = createMemorize(context, iGameEngine, (GameInformationMemorize) gameInformation);
                break;

        }
        return gameEngine;
    }

    private static GameEngine createMemorize(final Context context, final GameMode gameMode
            , final GameEngine.IGameEngine iGameEngine) {
        //Weapon
        final Weapon weapon = WeaponFactory.createBasicWeapon();

        //Game Information
        final GameInformationMemorize gameInformation = new GameInformationMemorize(gameMode, weapon);

        return createMemorize(context, iGameEngine, gameInformation);
    }


    private static GameEngine createMemorize(final Context context,
                                             final GameEngine.IGameEngine iGameEngine,
                                             GameInformationMemorize gameInformation) {
        //Game Behavior
        final GameBehaviorMemorize gameBehavior = GameBehaviorFactory.createMemorize();
        gameBehavior.setGameInformation(gameInformation);

        //Game Engine & Game Behavior
        final GameEngineMemorize gameEngine = new GameEngineMemorize(context, iGameEngine, gameBehavior);
        gameEngine.addRoutine(new Routine(Routine.TYPE_RELOADER, gameInformation.getWeapon().getReloadingTime()));
        gameEngine.addRoutine(new RoutineTicker(2000));
        gameBehavior.setInterface(gameEngine);

        //Game View
        final GameViewMemorize gameView = new GameViewMemorize(context, gameEngine);
        gameEngine.setGameView(gameView);

        return gameEngine;
    }


    private static GameEngine createTwentyInARow(final Context context, final GameMode gameMode
            , final GameEngine.IGameEngine iGameEngine) {
        //Weapon
        final Weapon weapon = WeaponFactory.createBasicWeapon();

        //Game Information
        final GameInformationTwentyInARow gameInformation = new GameInformationTwentyInARow(gameMode,
                weapon, 1);

        return createTwentyInARow(context, iGameEngine, gameInformation);
    }


    private static GameEngine createTwentyInARow(final Context context,
                                                 final GameEngine.IGameEngine iGameEngine,
                                                 GameInformationTime gameInformation) {
        //Game Behavior
        final GameBehaviorTwentyInARow gameBehavior = GameBehaviorFactory.createTwentyInARow();
        gameBehavior.setGameInformation(gameInformation);

        //Game Engine & Game Behavior
        final GameEngineTwentyInARow gameEngine = new GameEngineTwentyInARow(context, iGameEngine, gameBehavior);
        gameEngine.addRoutine(new Routine(Routine.TYPE_RELOADER, gameInformation.getWeapon().getReloadingTime()));
        gameEngine.addRoutine(new Routine(Routine.TYPE_SPAWNER, TWENTY_IN_A_ROW_SPAWNING_TIME));
        gameEngine.addRoutine(new RoutineTicker(DEFAULT_TICKING_TIME));
        gameBehavior.setInterface(gameEngine);

        //Game View
        final GameViewTwentyInARow gameView = new GameViewTwentyInARow(context, gameEngine);
        gameEngine.setGameView(gameView);

        return gameEngine;
    }

    private static GameEngine createTutorial(final Context context, final GameMode gameMode
            , final GameEngine.IGameEngine iGameEngine) {
        //Weapon
        final Weapon weapon = WeaponFactory.createBasicWeapon();

        //Game Information
        final GameInformationTutorial gameInformation = new GameInformationTutorial(gameMode, weapon);

        return createTutorial(context, iGameEngine, gameInformation);
    }


    private static GameEngine createTutorial(final Context context,
                                             final GameEngine.IGameEngine iGameEngine,
                                             final GameInformationTutorial gameInformation) {
        //Game Behavior
        final GameBehaviorTutorial gameBehavior = GameBehaviorFactory.createTutorial();
        gameBehavior.setGameInformation(gameInformation);

        //Game Engine & Game Behavior
        final GameEngineTutorial gameEngine = new GameEngineTutorial(context, iGameEngine, gameBehavior) {
            @Override
            public void onRun(int routineType, Object obj) {
                switch (routineType) {
                    case Routine.TYPE_RELOADER:
                        mGameBehavior.reload();
                        break;
                }
            }
        };
        gameEngine.addRoutine(new Routine(Routine.TYPE_RELOADER, gameInformation.getWeapon().getReloadingTime()));
        gameBehavior.setInterface(gameEngine);

        //Game View
        final GameViewTutorial gameView = new GameViewTutorial(context, gameEngine);
        gameEngine.setGameView(gameView);

        return gameEngine;
    }

    private static GameEngine createDeathToTheKing(final Context context, final GameModeDeathToTheKing gameMode
            , final GameEngine.IGameEngine iGameEngine) {
        //Weapon
        final Weapon weapon = WeaponFactory.createBasicWeapon();

        //Game Information
        final GameInformationDeathToTheKing gameInformation = new GameInformationDeathToTheKing(gameMode,
                weapon, 0);


        return createDeathToTheKing(context, iGameEngine, gameInformation);
    }

    private static GameEngine createDeathToTheKing(final Context context,
                                                   final GameEngine.IGameEngine iGameEngine,
                                                   GameInformationTime gameInformation) {
        //Game Behavior
        final GameBehaviorDeathToTheKing gameBehavior = GameBehaviorFactory.createDeathToTheKing();
        gameBehavior.setGameInformation(gameInformation);

        //Game Engine & Game Behavior
        final GameEngineDeathToTheKing gameEngine = new GameEngineDeathToTheKing(context, iGameEngine, gameBehavior);
        gameEngine.addRoutine(new Routine(Routine.TYPE_RELOADER, gameInformation.getWeapon().getReloadingTime()));
        gameEngine.addRoutine(new RoutineTicker(DEFAULT_TICKING_TIME));
        gameBehavior.setInterface(gameEngine);

        //Game View
        final GameViewDeathToTheKing gameView = new GameViewDeathToTheKing(context, gameEngine);
        gameEngine.setGameView(gameView);

        return gameEngine;
    }

    private static GameEngine createSurvival(final Context context, final GameMode gameMode
            , final GameEngine.IGameEngine iGameEngine) {
        //Weapon
        final Weapon weapon = WeaponFactory.createBasicWeapon();

        //Game Information
        final GameInformationSurvival gameInformation = new GameInformationSurvival(gameMode,
                weapon, DEFAULT_STARTING_TIME);

        return createSurvival(context, iGameEngine, gameInformation);
    }


    private static GameEngine createSurvival(final Context context,
                                             final GameEngine.IGameEngine iGameEngine,
                                             GameInformationTime gameInformation) {
        //Game Behavior
        final GameBehaviorSurvival gameBehavior = GameBehaviorFactory.createSurvival();
        gameBehavior.setGameInformation(gameInformation);

        //Game Engine & Game Behavior
        final GameEngineTime gameEngine = new GameEngineTime(context, iGameEngine, gameBehavior) {
            @Override
            public void onRun(int routineType, Object obj) {
                switch (routineType) {
                    case Routine.TYPE_RELOADER:
                        mGameBehavior.reload();
                        break;
                    case Routine.TYPE_SPAWNER:
                        final float[] cameraAngle = mGameView.getCameraAngleInDegree();
                        mGameBehavior.spawn((int) cameraAngle[0], (int) cameraAngle[1]);
                        break;
                    case Routine.TYPE_TICKER:
                        mGameBehavior.tick((Long) obj);
                        break;

                }
            }
        };
        gameEngine.addRoutine(new Routine(Routine.TYPE_RELOADER, gameInformation.getWeapon().getReloadingTime()));
        gameEngine.addRoutine(new Routine(Routine.TYPE_SPAWNER, DEFAULT_SPAWNING_TIME));
        gameEngine.addRoutine(new RoutineTicker(DEFAULT_TICKING_TIME));
        gameBehavior.setInterface(gameEngine);

        //Game View
        final GameViewTime gameView = new GameViewTimeDecreasing(context, gameEngine);
        gameEngine.setGameView(gameView);

        return gameEngine;
    }

    private static GameEngine createSprintOrMarathon(final Context context, final GameMode gameMode
            , final GameEngine.IGameEngine iGameEngine) {
        //Weapon
        final Weapon weapon = WeaponFactory.createBasicWeapon();

        //Game Information
        final GameInformationTime gameInformation = new GameInformationTime(gameMode,
                weapon, DEFAULT_STARTING_TIME * gameMode.getLevel());

        return createSprintOrMarathon(context, iGameEngine, gameInformation);
    }

    private static GameEngine createSprintOrMarathon(final Context context,
                                                     final GameEngine.IGameEngine iGameEngine,
                                                     GameInformationTime gameInformation) {
        //Game Behavior
        GameBehaviorTime gameBehavior = GameBehaviorFactory.createSprint();
        if (gameInformation.getGameMode().getLevel() > 1) {
            gameBehavior = GameBehaviorFactory.createMarathon();
        }

        gameBehavior.setGameInformation(gameInformation);

        //Game Engine & Game Behavior
        final GameEngineTime gameEngine = new GameEngineTime(context, iGameEngine, gameBehavior) {
            @Override
            public void onRun(int routineType, Object obj) {
                switch (routineType) {
                    case Routine.TYPE_RELOADER:
                        mGameBehavior.reload();
                        break;
                    case Routine.TYPE_SPAWNER:
                        final float[] cameraAngle = mGameView.getCameraAngleInDegree();
                        mGameBehavior.spawn((int) cameraAngle[0], (int) cameraAngle[1]);
                        break;
                    case Routine.TYPE_TICKER:
                        mGameBehavior.tick((Long) obj);
                        break;

                }
            }
        };
        gameEngine.addRoutine(new Routine(Routine.TYPE_RELOADER, gameInformation.getWeapon().getReloadingTime()));
        gameEngine.addRoutine(new Routine(Routine.TYPE_SPAWNER, DEFAULT_SPAWNING_TIME));
        gameEngine.addRoutine(new RoutineTicker(DEFAULT_TICKING_TIME));
        gameBehavior.setInterface(gameEngine);

        //Game View
        final GameViewTime gameView = new GameViewTimeDecreasing(context, gameEngine);
        gameEngine.setGameView(gameView);

        return gameEngine;
    }
}
