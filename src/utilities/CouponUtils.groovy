package utilities

class CouponUtils {

    static int ROUND_PRECISION = 2
    /**
     * Gets the percentage discount for discount per volume products.
     * @param totalAmount Sum of all total_amount.
     * @param originalAmount charges_details.amounts.original.
     * @return if totalAmount is more than zero returns percentage discount (0.1, 0.15, etc), otherwise returns 0.
     */
    static calculateDiscountPercentage(Double totalAmount, Double originalAmount) {
        if ( totalAmount  > 0 ) {
            return originalAmount / totalAmount
        }
        return 0
    }

    static isValidCoupon(String name) {
        return name && name.startsWith("coupon_")
    }

}
