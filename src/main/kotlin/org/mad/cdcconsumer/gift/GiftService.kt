package org.mad.cdcconsumer.gift

import org.mad.cdcconsumer.item.ItemService
import org.springframework.stereotype.Service

@Service
class GiftService(private val itemService: ItemService) {
    fun getGift(id: Int): Gift {
        return Gift("Red", itemService.getItemById(id))
    }
}