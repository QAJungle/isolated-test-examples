import { pactWith } from 'jest-pact';
import { Matchers } from '@pact-foundation/pact';
import { findByCategoryAndYears } from '../src/services/NobelPrizeService';

export const PRIZES_DATA = 
    {
      prizes:[
          {
             firstname: Matchers.like('test1'),
             year: Matchers.like('2019'),
             surname: Matchers.like('test1')
          },
          {
             firstname: Matchers.like('test2'),
             year: Matchers.like('2019'),
             surname: Matchers.like('test2')
          }
       ],
       from: Matchers.like('2019'),
       to: Matchers.like('2020'),
       category: Matchers.like('physics')
    }


export const successResponse = {
    status: 200,
    headers: {
        "Content-Type": "application/json",
    },
    body: PRIZES_DATA,
}

export const request = {
    uponReceiving: "a request for nobel prize winners",
    withRequest: {
        method: "GET",
        path: "/nobel_prizes/v1",
        query: {
            category: 'physics',
            from: '2019',
            to: '2020'
        }
    }
}

pactWith({ consumer: "NobelPrizeConsumer", provider: "NobelPrizeProvider" }, provider => {
  
    describe("FindByCategoryAndYears API", () => {
    
        beforeEach(() => {
          const interaction = {
            state: "i have a list of nobel prize winners",
            ...request,
            willRespondWith: successResponse,
          }
          return provider.addInteraction(interaction)
        })
    
        // add expectations
        it("returns a sucessful body", () => {
            const payload = {
                category: 'physics',
                from: '2019',
                to: '2020'
            }

            return findByCategoryAndYears({
                url: provider.mockService.baseUrl,
                payload: payload
            }).then(actual => {
                expect(actual.from).toEqual('2019')
                expect(actual.to).toEqual('2020')
                expect(actual.category).toEqual('physics')
                expect(actual.prizes[0].firstname).toEqual('test1')
                expect(actual.prizes[0].surname).toEqual('test1')
                expect(actual.prizes[0].year).toEqual('2019')
                expect(actual.prizes[1].firstname).toEqual('test2')
                expect(actual.prizes[1].surname).toEqual('test2')
                expect(actual.prizes[1].year).toEqual('2019')
            })
        })
      })
})