<template>
    <div v-if="flightQuoteOutbound != ''">
        <div class="flightResponse">
            {{getFlightQuoteOutbound.Quotes[0].OutboundLeg.DepartureDate}} <br>
            From: {{getFlightQuoteOutbound.Places[0].CityName}}, {{getFlightQuoteOutbound.Places[0].CountryName}}<br>
            To: {{getFlightQuoteOutbound.Places[1].CityName}}, {{getFlightQuoteOutbound.Places[1].CountryName}} <br>
            Cheapest flight: {{getFlightQuoteOutbound.Quotes[0].MinPrice}}:- <br>
            {{getFlightQuoteOutbound.Carriers[0].Name}} <br>

            <br><br>

            {{getFlightQuoteInbound.Quotes[0].OutboundLeg.DepartureDate}} <br>
            From: {{getFlightQuoteInbound.Places[1].CityName}}, {{getFlightQuoteInbound.Places[1].CountryName}}<br>
            To: {{getFlightQuoteInbound.Places[0].CityName}}, {{getFlightQuoteInbound.Places[0].CountryName}} <br>
            Cheapest flight: {{getFlightQuoteInbound.Quotes[0].MinPrice}}:- <br>
            {{getFlightQuoteInbound.Carriers[0].Name}} <br>
        </div>
    </div>
</template>

<script>
import axios from "axios"

export default {
    data(){
        return{
            flightQuoteOutbound: '',
            flightQuoteInbound: ''
        }
    },

    computed:{
        getFlightQuoteOutbound(){
            return this.flightQuoteOutbound
        },

        getFlightQuoteInbound(){
            return this.flightQuoteInbound
        },
    },

    methods:{
        async getNewQuote(from, to, date1, date2){
            const options1 = {method: 'GET',
                url: 'https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsequotes/v1.0/SE/SEK/en-US/' + from + '/' + to + '/' + date1,
                headers: {
                    'x-rapidapi-key': 'aa011c24famshb6f81c530bbdc86p175b77jsn4330d8b88e74',
                    'x-rapidapi-host': 'skyscanner-skyscanner-flight-search-v1.p.rapidapi.com'
                }
            }
            const options2 = {method: 'GET',
                url: 'https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browsequotes/v1.0/SE/SEK/en-US/' + to + '/' + from + '/' + date2,
                headers: {
                    'x-rapidapi-key': 'aa011c24famshb6f81c530bbdc86p175b77jsn4330d8b88e74',
                    'x-rapidapi-host': 'skyscanner-skyscanner-flight-search-v1.p.rapidapi.com'
                }
            }

            let thisObject = this

            await axios.request(options1).then(function(response){
                thisObject.flightQuoteOutbound = response.data
            }).catch(function(error){
                console.error(error)
            })

            await axios.request(options2).then(function(response){
                thisObject.flightQuoteInbound = response.data
            }).catch(function(error){
                console.error(error)
            })
        }
    },

    mounted(){
        this.getNewQuote('CPH-sky', 'SVO-sky', '2021-06-01', '2021-06-08')
    }
}
</script>

<style scoped>
    .flightResponse{
        font-size: smaller;
        font-weight: 700;
    }
</style>