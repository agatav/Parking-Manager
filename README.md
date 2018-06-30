# Parking-Manager
A web application for managing the city parking spaces.

| Method | Endpoint | Description
| :--- | :--- |:--- |
| GET | /owner/cars | Show the list of cars. |
| GET | /owner/meters | Show the list of parking meters. |
| GET | /owner/{carNumber}| Show parking meters of certain car. |
| GET | /owner/{carNumber}/sum | Sum the income from the certain car. |
| GET | /owner/{day}/{month}/{year} | Show parking meters the during choosen day. |
| GET | /owner/{day}/{month}/{year}/sum | Sum the income for choosen day. |
| GET | /operator/list | Show all cars on the parking. |
| GET | /operator/cars/{id} | Show info about choosen car. |
| GET | /operator/cars/number/{carNumber} | Show info about choosen car. |
| GET | /driver/{carNumber} | Show all previous driver's parking meters. |
| POST | /driver/{carNumber}/location | Var. "location" changes location INSIDE/OUTSIDE. |
| POST | /driver/{carNumber}/parkingMeter | Var. "parkingMeter" changes the status of parking meter ON/OFF. |
| POST | /operator/search | Var. "text" allows search by car number, location and car status. |
