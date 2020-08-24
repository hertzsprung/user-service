Scenario: Get all users living or

Given the user service connects to http://bpdts-test-app-v4.herokuapp.com/
When I GET /users/live-or-currently-near/London?within-miles=60
Then the JSON response is
[
    {
        "id": 135,
        "first_name": "Mechelle",
        "last_name": "Boam",
        "email": "mboam3q@thetimes.co.uk",
        "ip_address": "113.71.242.187",
        "latitude": -6.5115909,
        "longitude": 105.652983
      }
]