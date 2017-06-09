package com.mycompany.bonus.builders;

import com.mycompany.bonus.entity.Bonus;

import java.util.Date;

/**
 * Builder class for bonus test.
 */
public class BonusBuilder {
    public Bonus bonus = new Bonus();

    public BonusBuilder setId(int id) {
        bonus.setId(id);
        return this;
    }

    public BonusBuilder setCreatedDate(Date date) {
        bonus.setCreatedDate(date);
        return this;
    }

    public BonusBuilder setComments (String comments) {
        bonus.setComments(comments);
        return this;
    }

    public BonusBuilder setWageringRequirement(float wageringRequirements) {
        bonus.setWageringRequirement(wageringRequirements);
        return this;
    }

    public BonusBuilder setName(String name) {
        bonus.setName(name);
        return this;
    }

    public BonusBuilder setDescription(String description) {
        bonus.setDescription(description);
        return this;
    }

    public BonusBuilder setStatus(boolean status) {
        bonus.setStatus(status);
        return this;
    }

    public Bonus build() {
        return bonus;
    }

}
