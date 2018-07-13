

<template>
   <div class="form">
    <form @submit="checkForm">

        <p v-if="errors.length">
            <b>Please correct the following error(s):</b>
            <ul><li v-for="error in errors">{{ error }}</li></ul>
        </p>

        <p>
            <label for="amount">Loan Amount (€)</label>
            <input type="number" name="amount" id="amount" placeholder="0" v-model="amount">
        </p>
        <p>
            <label for="interest">Nominal Interest Rate (%)</label>
            <input type="number" name="interest" id="interest" step=".01" placeholder="0.00" v-model="interest">
        </p>
        <p>
            <label for="duration">Duration (in months)</label>
            <input type="number" name="duration" id="duration" placeholder="0" v-model="duration">
        </p>
        <p>
            <label for="date">Start date of Repayment</label>
            <input type="date" name="date" id="date" v-model="date">
        </p>
        <p>
            <input type="submit" value="Submit">
        </p>

    </form>
    <!--<button v-on:click="calcPay">Test form</button>-->
   </div>
</template>

<script>
    /* eslint-disable */
    export default {
        name: 'RepayForm',
        data() {
            return {amount: null,
                    interest: null,
                    duration: null,
                    date: null,
                    errors: [],
                    postResult: null}
            },
        methods: {
            checkForm: function(e){

                this.errors = [];
                if (!this.amount) {
                    this.errors.push("Please provide a loan amount");
                }
                else  if (this.amount < 0){
                    this.errors.push("Negative numbers not allowed as loan amount");
                 }

                if (!this.interest){
                    this.errors.push("Please provide an interest rate");
                } else if (this.interest < 0){
                    this.errors.push("Negative numbers not allowed as interest rate");
                }


                if(!this.duration){
                    this.errors.push("Please provide a duration");
                } else if (this.duration < 0) {
                    this.errors.push("Negative numbers not allowed as duration");
                }


                 if (!this.date){
                     this.errors.push("Please provide a start date");
                 } else if (Date.parse(this.date) < new Date()) {
                    this.errors.push("Date cannot be today or in the past")
                 }
                if(!this.errors.length) {
                    e.preventDefault();
                    this.calcPay();
                }

                e.preventDefault();
            },

            calcPay: function() {
                let planData = {'loanAmount': this.amount,
                    'nominalRate': Number.parseFloat(this.interest).toFixed(2),
                    'duration': this.duration,
                    'startDate': new Date(this.date).toISOString()}
                const body = JSON.stringify(planData);
                fetch('/generate-plan',
                    {
                        method: 'POST',
                        body: body,
                        mode: 'cors',
                        headers: {
                            'Accept': '*/*',
                            'Content-type': 'application/json; charset=UTF-8',
                            'X-Requested-With': 'XMLHttpRequest',
                        }
                    })
                    .then(response =>  {if (response.ok) {
                        return response.json();
                    } else {
                        throw new Error('Something went wrong, request failed with status ' + response.status);
                    }
                    })
                    .then((myJson) => {
                        this.postResult = myJson;
                        this.$router.push({name: 'Results', params:{postResult: this.postResult}});

                    })
                    .catch(error => {console.log(error)});


            }
        }

    }
</script>

<style scoped>
    ul{
        list-style: none;
    }
    .form{
        background-color: rgba(60,156,235, 0.5);
        padding: 2%;
    }
    input{
        background-color: rgba(225,225,225, 0.4);
        border: none;
        padding: 0.5%;
        border-radius: 4px;
    }
    input[type=submit] {
        padding: 1%;
        padding-left: 2%;
        padding-right: 2%;
        border-radius: 4px;
        border: none;
    }
</style>