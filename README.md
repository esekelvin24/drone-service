####### Implementation covered ####################

- registering a drone (maximum serial number character is 100 );
- creating a medication (Medication name allows only  letters, numbers, '-','_')/(Medication code allows only upper case letters, numbers, '-','_')
- loading a drone with medication items;
- checking loaded medication items for a given drone;
- checking available drones for loading;
- check drone battery level for a given drone;
- Prevent the drone from being loaded with more weight that it can carry;
- Prevent the drone from being in LOADING state if the battery level is **below 25%**;
- Introduce a periodic task to check drones battery levels and create history/audit event log for this.
- The periodic task is a timer class that discharges IDLE drones by 5% and when the drone is one Delivering state it discharges by 20% and logs it

################### Security ######################## JWT/ Auth0 for signing the token
1) Created Roles
- ROLE_USER
- ROLE_ADMIN
- ROLE_SUPER_ADMIN
- ROLE_MANAGER
2) List of users Created
- username = user      password = 123456 (ROLE_USER)
- username = admin     password = 123456 (ROLE_ADMIN)
- username = ese       password = 12345  (ROLE_SUPER_ADMIN


Download and Install Postman API Client 

######### GETTING AUTHORIZATION TOKEN #############

1) Get an authorization and refresh Token using the steps below
- Open Postman client
- request URL: http://localhost:8080/api/v1/login
- On the postman "Body Tab" click x-www-form-urlencoded radio button
- enter the following in the KEY (username and password)  and VALUE ("user" and "123456") without the double quotes field 
- change the request type from GET to POST on the dropdown menu close to the request header
- Click Send to get the sample  JSON response as shown below

{
"access_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvdjEvbG9naW4iLCJleHAiOjE2NTA5MjA3MDF9.J5FcXWhAsGr1FwPk4ZVZxP0aZb1i2CIvlPX5WP_fQNM",
"refresh_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL2FwaS92MS9sb2dpbiIsImV4cCI6MTY1MDkyMTkwMX0.urMPGDleCgQ7-Lse4lYYNuNJ_f0T2Rb6InzH2yBZ9to"
}

2) All on request to any API endpoints, kindly ensure your enter this authorization token on the header as shown below
- KEY => Authorization VALUE => "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvdjEvbG9naW4iLCJleHAiOjE2NTA5MjA3MDF9.J5FcXWhAsGr1FwPk4ZVZxP0aZb1i2CIvlPX5WP_fQNM"

############ GETTING LIST OF DRONE STATE ################
1) By default, I created all the drone state  "in memory database" and the following endpoints will fetch the list of the created drones
- Open Postman client
- request URL: http://localhost:8080/api/v1/states
2) Click on the header tab and input the authorization token gotten in the KEY AND VALUE field as shown on the example below
- KEY => Authorization VALUE => "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvdjEvbG9naW4iLCJleHAiOjE2NTA5MjA3MDF9.J5FcXWhAsGr1FwPk4ZVZxP0aZb1i2CIvlPX5WP_fQNM"
3) Ensure the request type is GET and Click on the send button.
- The response from this API endpoint should look like the following below

[
{
"id": 5,
"name": "IDLE"
},
{
"id": 6,
"name": "LOADING"
},
{
"id": 7,
"name": "LOADED"
},
{
"id": 8,
"name": "DELIVERING"
},
{
"id": 9,
"name": "DELIVERED"
},
{
"id": 10,
"name": "RETURNING"
}
]


############ GETTING LIST OF MODELS #####################
1) By default, I created all the drone models  "in memory database" and the following endpoints will fetch the list of the created drones
- Open Postman client
- request URL: http://localhost:8080/api/v1/models
2) Click on the header tab and input the authorization token gotten in the KEY AND VALUE field as shown on the example below
- KEY => Authorization VALUE => "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvdjEvbG9naW4iLCJleHAiOjE2NTA5MjA3MDF9.J5FcXWhAsGr1FwPk4ZVZxP0aZb1i2CIvlPX5WP_fQNM"
3) Ensure the request type is GET and Click on the send button.
- The response from this API endpoint should look like the following below

[
{
"id": 1,
"name": "Lightweight"
},
{
"id": 2,
"name": "Middleweight"
},
{
"id": 3,
"name": "Cruiserweight"
},
{
"id": 4,
"name": "Heavyweight"
}
]


######### GETTING LIST OF DRONES #############
1) By default, I created 4 Drones in the "in memory database" and the following endpoints will fetch the list of the created drones
- Open Postman client
- request URL: http://localhost:8080/api/v1/drones
2) Click on the header tab and input the authorization token gotten in the KEY AND VALUE field as shown on the example below
- KEY => Authorization VALUE => "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvdjEvbG9naW4iLCJleHAiOjE2NTA5MjA3MDF9.J5FcXWhAsGr1FwPk4ZVZxP0aZb1i2CIvlPX5WP_fQNM"
3) Ensure the request type is GET and Click on the send button.
- The response from this API endpoint should look like the following below 

[
{
"id": 11,
"sn": "14921",
"weight": 500,
"battery": 75,
"state": {
"id": 6,
"name": "LOADING"
},
"model": {
"id": 4,
"name": "Heavyweight"
}
},
{
"id": 12,
"sn": "14928",
"weight": 400,
"battery": 75,
"state": {
"id": 5,
"name": "IDLE"
},
"model": {
"id": 3,
"name": "Cruiserweight"
}
},
{
"id": 13,
"sn": "15928",
"weight": 300,
"battery": 75,
"state": {
"id": 5,
"name": "IDLE"
},
"model": {
"id": 2,
"name": "Middleweight"
}
},
{
"id": 14,
"sn": "16928",
"weight": 250,
"battery": 75,
"state": {
"id": 5,
"name": "IDLE"
},
"model": {
"id": 1,
"name": "Lightweight"
}
}
]

######### GETTING LIST OF CREATED MEDICATION #############
1) By default, I created 5 Medications with different weights in the "in memory database" and the following endpoints will fetch the list of the created medications
- Open Postman client
- request URL: http://localhost:8080/api/v1/medications
2) Click on the header tab and input the authorization token gotten in the KEY AND VALUE field as shown on the example below
- KEY => Authorization VALUE => "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvdjEvbG9naW4iLCJleHAiOjE2NTA5MjA3MDF9.J5FcXWhAsGr1FwPk4ZVZxP0aZb1i2CIvlPX5WP_fQNM"
3) Ensure the request type is GET and Click on the send button.
- The response from this API endpoint should look like the following below 

[
{
"id": 15,
"name": "Medication For_Cough",
"weight": 130,
"code": "A4829113",
"image": "https://www.assetpharmacy.com/wp-content/uploads/2019/08/D-KOFF-Cough-Expectorant-Syrup-100ml-1.jpg"
},
{
"id": 16,
"name": "Medication for Blood Pressure",
"weight": 500,
"code": "5299113",
"image": "https://www.sciencenews.org/wp-content/uploads/2020/04/042320_ac_covid-ace_feat.jpg"
},
{
"id": 17,
"name": "Medication for Cancer",
"weight": 350,
"code": "5399115",
"image": "https://pharmaceutical-journal.com/wp-content/uploads/2021/03/How-to-counsel-cancer-patients-about-their-oral-chemotherapy.jpg"
},
{
"id": 18,
"name": "Medication for Body Pains",
"weight": 50,
"code": "4899115",
"image": "https://pharmaceutical-journal.com/wp-content/uploads/2021/03/How-to-counsel-cancer-patients-about-their-oral-chemotherapy.jpg"
},
{
"id": 19,
"name": "Medication for Ulcer",
"weight": 120,
"code": "9899115",
"image": "https://pharmaceutical-journal.com/wp-content/uploads/2021/03/How-to-counsel-cancer-patients-about-their-oral-chemotherapy.jpg"
}
]

############ GETTING AVAILABLE DRONES FOR LOADING #####################
1) Only drone is IDLE STATE and LOADING state will be displayed
- Open Postman client
- request URL: http://localhost:8080/api/v1/drones/loading/available
2) Click on the header tab and input the authorization token gotten in the KEY AND VALUE field as shown on the example below
- KEY => Authorization VALUE => "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvdjEvbG9naW4iLCJleHAiOjE2NTA5MjA3MDF9.J5FcXWhAsGr1FwPk4ZVZxP0aZb1i2CIvlPX5WP_fQNM"
3) Ensure the request type is GET and Click on the send button.
- The response from this API endpoint should look like the following below

[
{
"id": 12,
"sn": "14928",
"weight": 400,
"battery": 5,
"state": {
"id": 5,
"name": "IDLE"
},
"model": {
"id": 3,
"name": "Cruiserweight"
}
},
{
"id": 13,
"sn": "15928",
"weight": 300,
"battery": 5,
"state": {
"id": 5,
"name": "IDLE"
},
"model": {
"id": 2,
"name": "Middleweight"
}
},
{
"id": 14,
"sn": "16928",
"weight": 250,
"battery": 5,
"state": {
"id": 5,
"name": "IDLE"
},
"model": {
"id": 1,
"name": "Lightweight"
}
},
{
"id": 65,
"sn": "38377182",
"weight": 200,
"battery": 5,
"state": {
"id": 5,
"name": "IDLE"
},
"model": {
"id": 4,
"name": "Heavyweight"
}
},
{
"id": 11,
"sn": "14921",
"weight": 500,
"battery": 5,
"state": {
"id": 6,
"name": "LOADING"
},
"model": {
"id": 4,
"name": "Heavyweight"
}
}
]

######### LOADING MEDICATIONS ON A DRONE #############
1) You can be able to load created medications on a drone by sending the medication code and drone serial number as a JSON string. this will change its state from IDLE to loading
-Open Postman client
- request URL: http://localhost:8080/api/v1/drone/load
2) Click on the header tab and input the authorization token gotten in the KEY AND VALUE field as shown on the example below
- KEY => Authorization VALUE => "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvdjEvbG9naW4iLCJleHAiOjE2NTA5MjA3MDF9.J5FcXWhAsGr1FwPk4ZVZxP0aZb1i2CIvlPX5WP_fQNM"
3) Ensure the request type is POST.
4) ON the BODY tab on your postman client select the raw radio button
- Change the select menu FROM text to JSON
- On the body input the request payload shown in the example below

{
"droneSn" : "14921",
"medicationCode": "4899115"
}

5) Click on the send button
6) A response will be displayed as shown below
- Kind note you can be able to load as many medications to a drone until the drone exceeds the limit it can carry

{
"id": 32,
"drone": {
"id": 11,
"sn": "14921",
"weight": 500,
"battery": 95,
"state": {
"id": 6,
"name": "LOADING"
},
"model": {
"id": 4,
"name": "Heavyweight"
}
},
"medication": {
"id": 18,
"name": "Medication for Body Pains",
"weight": 50,
"code": "4899115",
"image": "https://pharmaceutical-journal.com/wp-content/uploads/2021/03/How-to-counsel-cancer-patients-about-their-oral-chemotherapy.jpg"
},
"createdAt": "2022-04-26T08:22:18.975+00:00",
"transactionStatus": "OPEN"
}

########## CREATE A NEW DRONE  ######################## 
1) You can be able to create  a drone using the JSON string as shown below
-Open Postman client
- request URL: http://localhost:8080/api/v1/drone/save
2) Click on the header tab and input the authorization token gotten in the KEY AND VALUE field as shown on the example below
- KEY => Authorization VALUE => "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvdjEvbG9naW4iLCJleHAiOjE2NTA5MjA3MDF9.J5FcXWhAsGr1FwPk4ZVZxP0aZb1i2CIvlPX5WP_fQNM"
3) Ensure the request type is POST.
4) ON the BODY tab on your postman client select the "raw" radio button
- Change the select menu FROM text to JSON
- On the body input the request payload shown in the example below

{
"sn" : "38377182",
"weight": 200,
"battery":100,
"model":"Heavyweight"
}

5) A header response 201 will be returned with a body response of

{
"id": 65,
"sn": "38377182",
"weight": 200,
"battery": 100,
"state": {
"id": 5,
"name": "IDLE"
},
"model": {
"id": 4,
"name": "Heavyweight"
}
}

################ CREATE NEW MEDICATION ################
1) You can be able to create  a medication using the JSON string as shown below
-Open Postman client
- request URL: http://localhost:8080/api/v1/medication/save
2) Click on the header tab and input the authorization token gotten in the KEY AND VALUE field as shown on the example below
- KEY => Authorization VALUE => "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvdjEvbG9naW4iLCJleHAiOjE2NTA5MjA3MDF9.J5FcXWhAsGr1FwPk4ZVZxP0aZb1i2CIvlPX5WP_fQNM"
3) Ensure the request type is POST.
4) ON the BODY tab on your postman client select the "raw" radio button
- Change the select menu FROM text to JSON
- On the body input the request payload shown in the example below

{
"medicationName" : "Medications for Facial disorder",
"medicationCode": "5938499",
"medicationImageUrl":"https://www.sciencenews.org/wp-content/uploads/2020/04/042320_ac_covid-ace_feat.jpg",
"medicationWeight": 23
}

5) Click the send button
6) A header response 201 will be returned with a body response of

{
"id": 36,
"name": "Medications for Facial disorder",
"weight": 23,
"code": "5938499",
"image": "https://www.sciencenews.org/wp-content/uploads/2020/04/042320_ac_covid-ace_feat.jpg"
}


################## GET LOADED MEDICATION ON A DRONE #######################

1) You can be able to get loaded medication on a drone by sending the drone serial number and transactionStatus as a JSON string
- Open Postman client
- request URL: http://localhost:8080/api/v1/drone/loaded/medications
2) Click on the header tab and input the authorization token gotten in the KEY AND VALUE field as shown on the example below
- KEY => Authorization VALUE => "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvdjEvbG9naW4iLCJleHAiOjE2NTA5MjA3MDF9.J5FcXWhAsGr1FwPk4ZVZxP0aZb1i2CIvlPX5WP_fQNM"
3) Ensure the request type is POST.
4) ON the BODY tab on your postman client select the raw radio button
- Change the select menu FROM text to JSON
- On the body input the request payload shown in the example below

{
"droneSn": "14921",
"transactionStatus": "OPEN"
}

5)The response from this API endpoint should look like the following below 

[
{
"id": 20,
"drone": {
"id": 11,
"sn": "14921",
"weight": 500,
"battery": 5,
"state": {
"id": 6,
"name": "LOADING"
},
"model": {
"id": 4,
"name": "Heavyweight"
}
},
"medication": {
"id": 17,
"name": "Medication for Cancer",
"weight": 350,
"code": "5399115",
"image": "https://pharmaceutical-journal.com/wp-content/uploads/2021/03/How-to-counsel-cancer-patients-about-their-oral-chemotherapy.jpg"
},
"createdAt": "2022-04-26T09:14:33.896+00:00",
"transactionStatus": "OPEN"
}
]


################## GET DRONE BATTERY PERCENTAGE #######################

1) You can be able to get battery percentage of a drone by sending the drone serial number as a JSON string
- Open Postman client
- request URL: http://localhost:8080/api/v1/drone/battery
2) Click on the header tab and input the authorization token gotten in the KEY AND VALUE field as shown on the example below
- KEY => Authorization VALUE => "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvdjEvbG9naW4iLCJleHAiOjE2NTA5MjA3MDF9.J5FcXWhAsGr1FwPk4ZVZxP0aZb1i2CIvlPX5WP_fQNM"
3) Ensure the request type is POST.
4) ON the BODY tab on your postman client select the raw radio button
- Change the select menu FROM text to JSON
- On the body input the request payload shown in the example below
  
- {
  "droneSn": "14921"
  }

5)The response from this API endpoint should look like the following below 

{
"id": 11,
"sn": "14921",
"weight": 500,
"battery": 5,
"state": {
"id": 6,
"name": "LOADING"
},
"model": {
"id": 4,
"name": "Heavyweight"
}
}

###################### GET HISTORY/AUDIT EVENT LOG FOR DRONE BATTERY  #############

1) You can be able to get battery history/audit event log of a drone by sending the drone serial number as a JSON string
- Open Postman client
- request URL: http://localhost:8080/api/v1/drone/battery
2) Click on the header tab and input the authorization token gotten in the KEY AND VALUE field as shown on the example below
- KEY => Authorization VALUE => "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwicm9sZXMiOlsiUk9MRV9VU0VSIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9hcGkvdjEvbG9naW4iLCJleHAiOjE2NTA5MjA3MDF9.J5FcXWhAsGr1FwPk4ZVZxP0aZb1i2CIvlPX5WP_fQNM"
3) Ensure the request type is POST.
4) ON the BODY tab on your postman client select the raw radio button
- Change the select menu FROM text to JSON
- On the body input the request payload shown in the example below

- {
  "droneSn": "14921"
  }

5)The response from this API endpoint should look like the following below

[
{
"drone": {
"id": 11,
"sn": "14921",
"weight": 500,
"battery": 5,
"state": {
"id": 6,
"name": "LOADING"
},
"model": {
"id": 4,
"name": "Heavyweight"
}
},
"processType": "DISCHARGE",
"state": {
"id": 6,
"name": "LOADING"
},
"previousBatteryPercentage": 100,
"currentBatteryPercentage": 95,
"timeStamp": "2022-04-26T09:14:34.304+00:00",
"id": 28
},
{
"drone": {
"id": 11,
"sn": "14921",
"weight": 500,
"battery": 5,
"state": {
"id": 6,
"name": "LOADING"
},
"model": {
"id": 4,
"name": "Heavyweight"
}
},
"processType": "DISCHARGE",
"state": {
"id": 6,
"name": "LOADING"
},
"previousBatteryPercentage": 95,
"currentBatteryPercentage": 90,
"timeStamp": "2022-04-26T09:15:34.282+00:00",
"id": 32
},
{
"drone": {
"id": 11,
"sn": "14921",
"weight": 500,
"battery": 5,
"state": {
"id": 6,
"name": "LOADING"
},
"model": {
"id": 4,
"name": "Heavyweight"
}
},
"processType": "DISCHARGE",
"state": {
"id": 6,
"name": "LOADING"
}
]

################## OTHER REQUEST ENDPOINTS  ###########################

1) To get list of loaded medication transactions of all drones
- request URL: http://localhost:8080/api/v1/transactions

