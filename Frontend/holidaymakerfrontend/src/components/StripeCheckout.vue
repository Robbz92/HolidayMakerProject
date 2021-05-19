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
        <button @click="submit">Pay now!</button>
    </div>
</template>

<script>
import { StripeCheckout } from '@vue-stripe/vue-stripe'
export default {
    components:{
        StripeCheckout
    },

    props:['totalPrice'],

    data(){
        this.publishableKey = 'pk_test_51IsnNBFc0ZEqxGhpEsIHDP9Iref72shtu3j1X4vKjyBYW2zsZYovgbMYNYkdLjfZwo7rMfj9IwqBskqChSQlMa0V00oNXRjzCf';
        return{
            loading: false,
            lineItems: [
                {
                    price: 'price_1Isny4Fc0ZEqxGhpxKl5IZgV',
                    quantity: this.totalPrice
                },
                ],
            successURL: 'http://localhost:3000/success',
            cancelURL: 'http://localhost:3000/cancel',
        }
    },
    methods:{
        submit(){
            this.$refs.checkoutRef.redirectToCheckout();
        }
    }
}
</script>