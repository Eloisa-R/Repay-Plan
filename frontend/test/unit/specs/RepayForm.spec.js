import Vue from 'vue'
import RepayForm from '@/components/RepayForm'

    describe('RepayForm.vue', () => {
        it('should render correct contents', () => {
            const Constructor = Vue.extend(RepayForm)
                const vm = new Constructor().$mount()
                expect(vm.$el.querySelector('.amount').textContent)
                .to.equal('Loan Amount (€)')
            })
        })