
# Docker and kuberneters is not needed at the time because of the current step of developent. Decoupled system are the way to go right now keeping in mind the still early stage of developent.
# More simple, the transition to containers and orchestration is too hard and useless for the complexity of the current api  

# Back-End
    ### The realtime communication was needed for arduino, but now we're moving to Node-Red
    ### Exception handling added but needs more refinement.
1. Need to implement a authentication system
  - It isn't a priority right now because the API isn't exposed public yet, the tests are made on localhost or private hosting
  - As a authentication method is good to consider OAuth but it requires mode research to implement
  - The database authentication method is considered but it will be more difficult to implement and encrypt
  - Update (26.04.2019)
    - Currently is considered using an api key somewere in the headers or in the url (More research is required)
--------------------------------------------------------------------------------------------------------------------
### The authentication system is currently in progress, but some tehnical difficulties appeared in the process of development. *

# Front-End 

- Need to create the root webpage
- Need to create a admin console (Spring admin console maybe?) 

# Notes
- The JUnit tests sucks and is only one rw :/
- Research started for the move into containes. Switching from Apache Tomcat and other separated services to containes, specifically Docker Containers.
- The current progress on the authentication system is moved to a special branch. This branch remains for working versions.
