### curl samples (application deployed in application context `restorant`).
> For windows use `Git Bash`

#### get All Users
`curl -v http://localhost:8080/restaurant/rest/admin/users`

#### get Users 100001
`curl -v http://localhost:8080/restaurant/rest/admin/users/100001`

#### get All Meals
`curl -v http://localhost:8080/restaurant/rest/profile/meals`

#### get Meals 100003
`curl -v http://localhost:8080/restaurant/rest/profile/meals/100003`

#### filter Meals
`curl -v "http://localhost:8080/restaurant/rest/profile/meals/filter?startDate=2015-05-30&startTime=07:00:00&endDate=2015-05-31&endTime=11:00:00"`

#### get Meals not found
`curl  -v http://localhost:8080/restaurant/rest/profile/meals/100008`

#### delete Meals
`curl -v -X DELETE http://localhost:8080/restaurant/rest/profile/meals/100002`

#### create Meals
`curl -v -X POST -d '{"dateTime":"2015-06-01T12:00","description":"Created lunch","calories":300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurant/rest/profile/meals`

#### update Meals
`curl -v -X PUT -d '{"dateTime":"2015-05-30T07:00", "description":"Updated breakfast", "calories":200}' -H 'Content-Type: application/json' http://localhost:8080/restaurant/rest/profile/meals/100003`