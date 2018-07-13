<template>
  <div id="app">
    <div class="title">Repayment Plan</div>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-left: 10%;
  margin-right: 10%;
}

body{
  background-color: rgba(119,119,119,0.5)
}
  .title{
    font-size: 4em;
    padding: 4%;
    background-color:#4183c4;
    color: #ffffff;
  }
</style>
