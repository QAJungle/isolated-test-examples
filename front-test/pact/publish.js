let publisher = require('@pact-foundation/pact-node');
let path = require('path');

let opts = {
    pactFilesOrDirs: [path.resolve(process.cwd(), 'pact/pacts')],
    pactBroker: 'http://localhost',
    consumerVersion: '0.0.1'
};

publisher.publishPacts(opts).then(
  () => console.log("Pacts successfully published"));