# Homework 1
Complete the unfinished code to pass the tests.
Desired behavior is provided in the test cases. Each test class is worth 10 points.

Right click -> run, to run a specific test. All tests are re-run on github when pushed. Score should be the same as local run.

The last score will be taken (not the highest). 

If compilation fails, all of them will fail.

## Running with server
You can optionally turn on and run as real http server by running the main method in the Server class.

## Overview
You are building a simple chat application with 4 endpoints.
- /createMessage (post) body: MessageDto - creates a message between 2 users
- /createUser (post) body: UserDto - creates a user 
- /getMessages (get) params: fromId, toId- returns a conversation between 2 users
- /getUsers (get) - returns all users


You will need to fill out empty methods to complete the functionality. 
This includes parsing a raw HTTP message, and generating a raw HTTP response.
The test cases can be completed in any order.
