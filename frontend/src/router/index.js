/* eslint-disable */

import Vue from 'vue'
import Router from 'vue-router'
import RepayForm from '@/components/RepayForm'
import Results from '@/components/Results'

Vue.use(Router)

export default new Router({
  routes: [
      {
      path: '/',
      name: 'RepayForm',
      component: RepayForm

      },
      {
       path: '/results',
       name: 'Results',
       props: true,
       component: Results

      }
  ]
})
