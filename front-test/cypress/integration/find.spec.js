/// <reference types="Cypress" />

context('Navigation', () => {

  beforeEach(() => {
    cy.visit('http://localhost:3000')
  })

  it('search a nobel prize by category and years', () => {
    // given
    const category = 'physics'
    const from = '2018'
    const to = '2019'

    // when
    cy.get('[data-test=search-category]').type(category)
    cy.get('[data-test=search-from]').type(from)
    cy.get('[data-test=search-to]').type(to)
    cy.get('[data-test=search-button]').click()

    // Then
    cy.get('[data-test=result-text]')
    .should('have.text', `Nobel prizes of ${category} category from ${from} to ${to} year.`)
    
    cy.get('[data-test=result-list-year]')
    .should($year => {
       expect($year).to.have.length.greaterThan(0)
       expect($year).not.to.be.empty
    })

    cy.get('[data-test=result-list-firstname]')
    .should($firstname => {
      expect($firstname).to.have.length.greaterThan(0)
      expect($firstname).not.to.be.empty
    })  

    cy.get('[data-test=result-list-surname]')
    .should($surname => {
      expect($surname).to.have.length.greaterThan(0)
      expect($surname).not.to.be.empty
    })  
  })

})
