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

package com.nikhilnayak.games.octoshootar.model.weapon;

/**
 * Every {@link Weapon} should be instantiated from this factory
 */
public class WeaponFactory {
    private static final int BASIC_WEAPON_DAMAGE = 1;
    private static final int BASIC_WEAPON_AMMUNITION_LIMIT = 8;
    private static final long BASIC_WEAPON_RELOADING_TIME = 1000;

    /**
     * Create a very basic {@link Weapon}
     *
     * @return a basic {@link Weapon}
     */
    public static Weapon createBasicWeapon() {
        Weapon basicWeapon = new Weapon();
        basicWeapon.setDamage(BASIC_WEAPON_DAMAGE);
        basicWeapon.setAmmunitionLimit(BASIC_WEAPON_AMMUNITION_LIMIT);
        basicWeapon.setReloadingTime(BASIC_WEAPON_RELOADING_TIME);
        basicWeapon.reload(BASIC_WEAPON_AMMUNITION_LIMIT);
        return basicWeapon;
    }
}
