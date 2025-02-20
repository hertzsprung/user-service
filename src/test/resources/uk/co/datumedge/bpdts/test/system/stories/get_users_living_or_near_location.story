Scenario: Get all users living or

Given the user service connects to http://bpdts-test-app-v4.herokuapp.com/
When I GET /users/living-or-currently-near/London?within-miles=60
Then the JSON response body is:
[
  {
    "id": 135,
    "email": "mboam3q@thetimes.co.uk",
    "latitude": -6.5115909,
    "longitude": 105.652983,
    "first_name": "Mechelle",
    "last_name": "Boam",
    "ip_address": "113.71.242.187"
  },
  {
    "id": 396,
    "email": "tstowgillaz@webeden.co.uk",
    "latitude": -6.7098551,
    "longitude": 111.3479498,
    "first_name": "Terry",
    "last_name": "Stowgill",
    "ip_address": "143.190.50.240"
  },
  {
    "id": 520,
    "email": "aseabrockeef@indiegogo.com",
    "latitude": 27.69417,
    "longitude": 109.73583,
    "first_name": "Andrew",
    "last_name": "Seabrocke",
    "ip_address": "28.146.197.176"
  },
  {
    "id": 658,
    "email": "smapstonei9@bandcamp.com",
    "latitude": -8.1844859,
    "longitude": 113.6680747,
    "first_name": "Stephen",
    "last_name": "Mapstone",
    "ip_address": "187.79.141.124"
  },
  {
    "id": 688,
    "email": "tcolbertsonj3@vimeo.com",
    "latitude": 37.13,
    "longitude": -84.08,
    "first_name": "Tiffi",
    "last_name": "Colbertson",
    "ip_address": "141.49.93.0"
  },
  {
    "id": 794,
    "email": "kgopsallm1@cam.ac.uk",
    "latitude": 5.7204203,
    "longitude": 10.901604,
    "first_name": "Katee",
    "last_name": "Gopsall",
    "ip_address": "203.138.133.164"
  },
  {
    "id": 266,
    "email": "agarnsworthy7d@seattletimes.com",
    "latitude": 51.6553959,
    "longitude": 0.0572553,
    "first_name": "Ancell",
    "last_name": "Garnsworthy",
    "ip_address": "67.4.69.137"
  },
  {
    "id": 322,
    "email": "hlynd8x@merriam-webster.com",
    "latitude": 51.6710832,
    "longitude": 0.8078532,
    "first_name": "Hugo",
    "last_name": "Lynd",
    "ip_address": "109.0.153.166"
  },
  {
    "id": 554,
    "email": "phebbsfd@umn.edu",
    "latitude": 51.5489435,
    "longitude": 0.3860497,
    "first_name": "Phyllys",
    "last_name": "Hebbs",
    "ip_address": "100.89.186.13"
  }
]