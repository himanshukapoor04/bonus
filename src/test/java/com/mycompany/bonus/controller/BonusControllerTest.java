package com.mycompany.bonus.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.mycompany.bonus.builders.BonusBuilder;
import com.mycompany.bonus.entity.Bonus;
import com.mycompany.bonus.service.BonusRepository;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;

/**
 * Test Class for Bonus
 */
@RunWith(MockitoJUnitRunner.class)
public class BonusControllerTest {

    @InjectMocks
    private BonusApiRESTController controller;

    @Mock
    private BonusRepository bonusRepository;

    private static final Bonus INACTIVE_BONUS = new BonusBuilder().setId(1).setStatus(false).
            setName("History").setComments("First Bonus").setDescription("Bonus").setCreatedDate(new Date()).build();

    private static final Bonus ACTIVE_BONUS = new BonusBuilder().setId(2).setStatus(true).
            setName("Active").setComments("Second Bonus").setDescription("Bonus").setCreatedDate(new Date()).build();

    private static final Bonus NEW_BONUS = new BonusBuilder().
            setName("Active").setComments("Second Bonus").setDescription("Bonus").setCreatedDate(new Date()).build();

    @Test
    public void shouldReturnActiveBonus() {
        given(bonusRepository.getAllActiveBonuses()).willReturn(Arrays.asList(ACTIVE_BONUS));
        assertThat(controller.getAllActiveBonus()).containsOnly(ACTIVE_BONUS);
    }

    @Test
    public void shouldReturnInActiveBonus() {
        given(bonusRepository.getAllActiveBonuses()).willReturn(Arrays.asList(INACTIVE_BONUS));
        assertThat(controller.getAllActiveBonus()).containsOnly(INACTIVE_BONUS);
    }

    @Test
    public void shouldReturnAllBonuses() {
        given(bonusRepository.getAllActiveBonuses()).willReturn(Arrays.asList(ACTIVE_BONUS,INACTIVE_BONUS));
        assertThat(controller.getAllActiveBonus()).containsOnly(ACTIVE_BONUS, INACTIVE_BONUS);
    }

    @Test
    public void shouldSaveTheNewItem() {
        given(bonusRepository.saveAndFlush(NEW_BONUS)).willReturn(ACTIVE_BONUS);
        assertThat(controller.createBonus(NEW_BONUS)).isSameAs(ACTIVE_BONUS);
    }

    @Test
    public void shouldUpdateTheBonus() {
        given(bonusRepository.getOne(2)).willReturn(ACTIVE_BONUS);
        given(bonusRepository.saveAndFlush(ACTIVE_BONUS)).willReturn(ACTIVE_BONUS);
        assertThat(controller.updateBonus(ACTIVE_BONUS, 2)).isSameAs(ACTIVE_BONUS);
    }

    @Test
    public void shouldUseRepoWhenDeletingItem() {
        controller.deleteBonus(2);
        verify(bonusRepository).delete(2);
    }



}
