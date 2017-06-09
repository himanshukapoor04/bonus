package com.mycompany.bonus.controller;

import com.mycompany.bonus.api.BonusApi;
import com.mycompany.bonus.entity.Bonus;
import com.mycompany.bonus.service.BonusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller class for Bonus API
 */
@RestController
@RequestMapping("/bonus")
public class BonusApiRESTController implements BonusApi {

    @Autowired
    private BonusRepository bonusRepository;

    @Override
    @RequestMapping(value =  "/active", method = RequestMethod.GET )
    public List<Bonus> getAllActiveBonus() {
        return bonusRepository.getAllActiveBonuses();
    }

    @Override
    @RequestMapping(value =  "/inactive", method = RequestMethod.GET )
    public List<Bonus> getAllInactiveBonus() {
        return bonusRepository.getAllInactiveBonuses();
    }

    @Override
    @RequestMapping( method = RequestMethod.GET)
    public List<Bonus> getAllBonus() {
        return bonusRepository.findAll();
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public Bonus createBonus(@RequestBody  Bonus bonus) {
        bonus.setStatus(true);
        return bonusRepository.saveAndFlush(bonus);
    }

    @Override
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Bonus updateBonus(@RequestBody Bonus updatedBonus, @PathVariable Integer id) {
        Bonus bonus = bonusRepository.getOne(id);
        bonus.setDescription(updatedBonus.getDescription());
        bonus.setName(updatedBonus.getDescription());
        bonus.setWageringRequirement(updatedBonus.getWageringRequirement());
        return bonusRepository.saveAndFlush(bonus);
    }

    @Override
    @RequestMapping(value =  "/{id}", method = RequestMethod.DELETE)
    public void deleteBonus(@PathVariable Integer id) {
        bonusRepository.delete(id);
    }
}
