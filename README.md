</------------ Spring API ------------ \>

Back-End
    
- Need to implement websockets for realtime communication
  - The current system of transmiting data is ok, but with the system scalling it will be a problem for long term
  - This system based on database stays for long time logging (an update a 20 - 30 minutes is ok)
  - The websocket will be connected with the device witch is requesting the live data
  - The link to the socket will be send with the json payload 

- Need to implement a authentication system
  - It isn't a priority right now because the API isn't exposed public yet, the tests are made on localhost or private hosting
  - As a authentication method is good to consider OAuth but it requires mode research to implement
  - The database authentication method is considered but it will be more difficult to implement and encrypt

- The system for error handleling is not implement
  - It isn't a priority right now because Spring has a default error handleling method witch is some how effective
  - When the a error is triggred, the webpage will display the error and will be send as well in the response body

Front-End 

- Need to create the root webpage
- Need to create a admin console 
