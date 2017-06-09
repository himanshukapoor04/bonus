package com.mycompany.bonus.service;

import com.mycompany.bonus.entity.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * REPO class for DB operations
 */
public interface BonusRepository extends JpaRepository<Bonus, Integer> {
    /**
     * Get all the active bonuses from the system.
     * @return
     */
    @Query("SELECT b from Bonus b where b.status = 1 ")
    List<Bonus> getAllActiveBonuses();

    /**
     * Get all the future bonuses in the system.
     * @return
     */
    @Query("SELECT b from Bonus b where b.status = 0 ")
    List<Bonus> getAllInactiveBonuses();



}
