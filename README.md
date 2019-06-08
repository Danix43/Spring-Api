</------------ Spring API ------------ \>

# Back-End
    
1. Need to implement websockets for realtime communication
  - The current system of transmiting data is ok, but with the system scalling it will be a problem for long term
  - This system based on database stays for long time logging (an update a 20 - 30 minutes is ok)
  - The websocket will be connected with the device witch is requesting the live data
  - The link to the socket will be send with the json payload 
 --------------------------------------------------------------------------------------------------------------------
### The realtime communication was needed for arduino, but now we're moving to Node-Red
    
2. Need to implement a authentication system
  - It isn't a priority right now because the API isn't exposed public yet, the tests are made on localhost or private hosting
  - As a authentication method is good to consider OAuth but it requires mode research to implement
  - The database authentication method is considered but it will be more difficult to implement and encrypt
  - Update (26.04.2019)
    - Currently is considered using an api key somewere in the headers or in the url (More research is required)
--------------------------------------------------------------------------------------------------------------------
### The authentication system is currently in progress, but some tehnical difficulties appeared in the process of development. *

3. Exception handling system added (26.04.2019)
  - Basic exception handling has been created
  - All the GET endpoints have EntityNotFoundedException if the requested resource doesn't exist.
  - The system returns a message and http code in the response
  - The front-end part of the system is not made because of the current html progress

# Front-End 

- Need to create the root webpage
- Need to create a admin console (Spring admin console maybe?) 

# Notes
The JUnit tests sucks and is only one rw :/
Research started for the move into containes. Switching from Apache Tomcat and other separated services to containes, specifically Docker Containers.
The current progress on the authentication system is moved to a special branch. This branch remains for working versions.
