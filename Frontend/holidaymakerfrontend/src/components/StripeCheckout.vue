<template>
    <div>
        <StripeCheckout
            ref="checkoutRef"
            mode="payment"
            :pk="publishableKey"
            :line-items="lineItems"
            :success-url="successURL"
            :cancel-url="cancelURL"
            @loading="v => loading = v"
        />
        <!-- The almighty "Pay now!" button -->
        <button @click="submit">Pay now!</button>
    </div>
</template>

<script>
import { StripeCheckout } from '@vue-stripe/vue-stripe'

export default {
    components:{
        StripeCheckout
    },

    props:['totalPrice', "fromMyBookings", "bookingID"],

    data(){
        //Assigning the test API key connected to HolidayMaker
        this.publishableKey = 'pk_test_51IsnNBFc0ZEqxGhpEsIHDP9Iref72shtu3j1X4vKjyBYW2zsZYovgbMYNYkdLjfZwo7rMfj9IwqBskqChSQlMa0V00oNXRjzCf';
        return{
            loading: false,
            //Defining products, which in this case is only one
            lineItems: [
                {
                    //The ID for said product
                    price: 'price_1Isny4Fc0ZEqxGhpxKl5IZgV',
                    //Using quantity instead of price, for simplicity
                    quantity: this.totalPrice
                },
                ],
            //Redirecting you to the 'Success' page, if payment is successful
            successURL: 'http://localhost:3000/myPage',
            //Redirecting you to the 'Cancel' page, if payment is declined or canceled
            cancelURL: 'http://localhost:3000/cancel',
        }
    },
    methods:{
        submit(){
            if(this.fromMyBookings === true){
                this.$parent.paynow()
            }

            if(this.fromMyBookings === false) {
                this.$parent.updateBooking(this.bookingID)
            }
            
            //Sending you to the checkout page from stripe
            this.$refs.checkoutRef.redirectToCheckout();
        }
    },
}
</script>