<template>
    <div class="table-container">
        <router-link :to="{name: 'RepayForm'}" class="back">Back to form</router-link>
        <table>
            <thead>
            <tr>
                <th>Date</th>
                <th>Annuity (Borrower Payment Amount)</th>
                <th>Principal</th>
                <th>Interest</th>
                <th>Initial Outstanding Principal</th>
                <th>Remaining Outstanding Principal</th>
            </tr>
            </thead>
            <tbody>
                <tr v-for="item in postResult">
                    <td>{{ new Date(item.date).getDate()}}-{{ new Date(item.date).getMonth()+1}}-{{ new Date(item.date).getFullYear()}}</td>
                    <td>{{ item.borrowerPaymentAmount }} €</td>
                    <td>{{ item.principal }}  €</td>
                    <td>{{ item.interest }} €</td>
                    <td>{{ item.initialOutstandingPrincipal }}  €</td>
                    <td>{{ item.remainingOutstandingPrincipal }}  €</td>
                </tr>
            </tbody>
        </table>

    </div>
</template>

<script>
    export default {
      name: 'Results',
      props: ['postResult']
    }
</script>

<style scoped>
    .table-container{
        background-color: rgba(60,156,235, 0.5);
        padding: 2%;

    }

    table{
        border-collapse: collapse;
        width: 100%;
    }


    td, th {
        border: 1px solid;
        padding: 1%;

        }
    .back{
        position: absolute;
        top: 15%;
        margin-left: 30%;
        text-decoration: none;
        color: black;
        background-color: rgba(225,2225,225,0.4);
        padding: 0.75%;
        border-radius: 4px;
    }

</style>