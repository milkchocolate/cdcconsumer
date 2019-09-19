package org.mad.cdcconsumer.gift

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class GiftController(val giftService: GiftService) {
    @GetMapping("gifts/{id}")
    fun getGift(@PathVariable id: Int): Gift {
        return giftService.getGift(id)
    }
}