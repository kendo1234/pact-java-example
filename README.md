### Simple Example of Contract Driven Testing w/Pact ###

Note: The current state of the repo assumes the pact broker service is running, as provider unit test gets the pact from the broker

PROVIDER: Performs a simple calculation of an integer and returns 'Odd' or 'Even'.

CONSUMER: Performs the same calculation, using the provider endpoint. 

Consumer Unit Test: Creates a mock of the provider using, creates a request-response pact and tests it.
- When run, the test will generate a pact file in the target folder.

Pact Provider Test: 

1. Sets up the JUnit Runner for Pact
2. Designates the location of the pact files (in this case the local pact folder in the Provider module)
3. Designates the target for testing (here it's the provider service)
4. Starts the provider service
5. Verifies the pact between provider and consumer, using the pact file.

To Run: Build the project and gradle test

PACT BROKER: The docker compose will spin up an instance of the pact broker and a postgres instance. 

- The consumer gradle build file has a publish task which will add the pact file from the consumer side to the broker.
- There is an annotation in the providers unit test which points to the location of the pact instance