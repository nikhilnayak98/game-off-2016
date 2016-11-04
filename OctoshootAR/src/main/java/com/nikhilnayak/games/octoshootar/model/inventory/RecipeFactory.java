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

package com.nikhilnayak.games.octoshootar.model.inventory;


public class RecipeFactory {
    public static Recipe create(int inventoryItemType) {
        Recipe recipe = new Recipe();
        switch (inventoryItemType) {

            case InventoryItemInformation.TYPE_BABY_DROOL:
                recipe.addIngredient(InventoryItemInformationFactory.create(InventoryItemInformation.TYPE_COIN), 12);
                break;

            case InventoryItemInformation.TYPE_GHOST_TEAR:
                recipe.addIngredient(InventoryItemInformationFactory.create(InventoryItemInformation.TYPE_COIN), 12);
                break;

            case InventoryItemInformation.TYPE_SPEED_POTION:
                recipe.addIngredient(InventoryItemInformationFactory.create(InventoryItemInformation.TYPE_COIN), 100);
                recipe.addIngredient(InventoryItemInformationFactory.create(InventoryItemInformation.TYPE_GHOST_TEAR), 15);
                recipe.addIngredient(InventoryItemInformationFactory.create(InventoryItemInformation.TYPE_BABY_DROOL), 15);
                break;

            case InventoryItemInformation.TYPE_BROKEN_HELMET_HORN:
                recipe.addIngredient(InventoryItemInformationFactory.create(InventoryItemInformation.TYPE_COIN), 10);
                break;

            case InventoryItemInformation.TYPE_KING_CROWN:
                recipe.addIngredient(InventoryItemInformationFactory.create(InventoryItemInformation.TYPE_COIN), 95);
                break;

            case InventoryItemInformation.TYPE_STEEL_BULLET:
                recipe.addIngredient(InventoryItemInformationFactory.create(InventoryItemInformation.TYPE_COIN), 50);
                recipe.addIngredient(InventoryItemInformationFactory.create(InventoryItemInformation.TYPE_BROKEN_HELMET_HORN), 10);
                break;

            case InventoryItemInformation.TYPE_GOLD_BULLET:
                recipe.addIngredient(InventoryItemInformationFactory.create(InventoryItemInformation.TYPE_COIN), 100);
                recipe.addIngredient(InventoryItemInformationFactory.create(InventoryItemInformation.TYPE_KING_CROWN), 1);
                break;

            case InventoryItemInformation.TYPE_ONE_SHOT_BULLET:
                recipe.addIngredient(InventoryItemInformationFactory.create(InventoryItemInformation.TYPE_COIN), 150);
                recipe.addIngredient(InventoryItemInformationFactory.create(InventoryItemInformation.TYPE_KING_CROWN), 2);
                recipe.addIngredient(InventoryItemInformationFactory.create(InventoryItemInformation.TYPE_BROKEN_HELMET_HORN), 20);
                break;

        }

        return recipe;
    }
}
