package prizes.getPrizes.successfully

import org.springframework.cloud.contract.spec.Contract

Contract.make {
  request {
    method('GET')
      url('/nobel_prizes/v1/') {
        queryParameters {
          parameter 'category': value(consumer(matching("^[a-zA-Z]+")), producer('physics'))
          parameter 'from': value(consumer(matching("[0-9]+")), producer('2018'))
          parameter 'to': value(consumer(matching("[0-9]+")), producer('2019'))
        }
      }
  }
  response {
    status OK()
    headers {
      contentType(applicationJson())
      header(accessControlAllowOrigin(), '*')
      header(accessControlAllowHeaders(), '*')
      header(accessControlAllowMethods(), '*')
    }
    body(
            from: $(consumer(fromRequest().query('from')), producer(anyDate())),
            to: $(consumer(fromRequest().query('to')), producer(anyDate())),
            category: $(consumer(fromRequest().query('category')), producer(anyNonBlankString())),
            prizes: [
                    $(
                        firstname: $(consumer(anyNonBlankString()), producer(anyNonBlankString())),
                        surname: $(consumer(anyNonBlankString()), producer(anyNonBlankString())),
                        year: $(consumer(fromRequest().query('from')), producer(anyPositiveInt()))
                    ),
                     $(
                       firstname: $(consumer(anyNonBlankString()), producer(anyNonBlankString())),
                       surname: $(consumer(anyNonBlankString()), producer(anyNonBlankString())),
                       year: $(consumer(fromRequest().query('from')), producer(anyPositiveInt()))
                     )
            ]
    )
    bodyMatchers {
      jsonPath('$.from', byRegex(nonEmpty()))
      jsonPath('$.to', byRegex(nonEmpty()))
      jsonPath('$.category', byRegex(nonEmpty()))
      jsonPath('$.prizes[0].firstname', byRegex(nonEmpty()))
      jsonPath('$.prizes[0].surname', byRegex(nonEmpty()))
      jsonPath('$.prizes[0].year', byRegex(nonEmpty()))
    }
  }
}
