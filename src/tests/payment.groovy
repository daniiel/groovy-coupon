package tests

import static utilities.CouponUtils.*
import groovy.json.JsonSlurper


JsonSlurper jsonSlurper = new JsonSlurper()
def payResponse = jsonSlurper.parse(new File('../data/payment-13820782718.json'))

Long packId = 2000002600836522
def items = [
        MLB1782638242: ["quantity": 13, "price": 50],
        MLB1782793659: ["quantity": 1, "price": 15.85],
        MLB1693619644: ["quantity": 1, "price": 7.81],
        MLB1772009286: ["quantity": 2, "price": 1005],
        MLB1770855070: ["quantity": 2, "price": 280]
]
def pack = [
        ["itemId": "MLB1772009286", "quantity": 1, "couponPack": 0]
]

Set discountedProducts = []   // cpgItems

payResponse.charges_details.each { cd ->
    if (!isValidCoupon(cd.name)) {
        return
    }
    boolean hasItemsGroup = cd.metadata.items_group
    if (hasItemsGroup) {
        def response = jsonSlurper.parse(new File("../data/ig-${cd.metadata.items_group}.json"))
        def buyingFlow = response.content.discount_volume
        calculateCoupon(discountedProducts, buyingFlow?.total_amount.sum(), cd.amounts.original, buyingFlow.item_id)
    } else {
        def itemId = cd.metadata.item_id
        if (items.containsKey(itemId)) {
            def totalAmount = items[itemId].quantity * items[itemId].price
            calculateCoupon(discountedProducts, totalAmount, cd.amounts.original, Collections.singletonList(itemId))
        }
    }
}

pack.each { item ->
    def hasDiscountItem = discountedProducts?.find { it.first() == item.itemId }
    if (hasDiscountItem) {
        def price = items[item.itemId].price
        item.couponPack = new BigDecimal(item.quantity * price * hasDiscountItem[1])
                .setScale(2, BigDecimal.ROUND_HALF_EVEN)
    }
}

println discountedProducts
println pack

/**
 *
 * @param discountedProducts lista de productos con descuento
 * @param totalAmount sum of all (price * quantity) of the items
 * @param discount coupon value
 * @param items list of items
 * @return
 */
def calculateCoupon(discountedProducts, totalAmount, discount, items) {
    def discountPercentage = calculateDiscountPercentage(totalAmount, discount)
    items.each { item ->
        discountedProducts << [item, discountPercentage]
    }
}

