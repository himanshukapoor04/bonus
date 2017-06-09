package com.mycompany.bonus.api;

import com.mycompany.bonus.entity.Bonus;


import java.util.List;

/**
 *  API to create bonuses in the system.
 */
public interface BonusApi {

    /**
     * Get all active bonuses in the system.
     * @return Bonus
     */
    List<Bonus> getAllActiveBonus();

    /**
     * Get all Inactive bonuses from the system.
     * @return
     */
    List<Bonus> getAllInactiveBonus();

    /**
     * Get all bonuses from the system
     * @return
     */
    List<Bonus> getAllBonus();

    /**
     * Create new bonus in the system.
     * @param bonus
     * @return
     */
    Bonus createBonus(Bonus bonus);

    /**
     * Update a bonus in the system.
     * @param bonus
     * @param id
     * @return
     */
    Bonus updateBonus(Bonus bonus, Integer id);

    /**
     * Delete a bonus from the system.
     * @param id
     */
    void deleteBonus(Integer id);
}
