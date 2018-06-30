# Parking-Manager
A web application for managing the city parking spaces.

| Method | Endpoint | Description
| :--- | :--- |:--- |
| GET | /owner/cars | Show the list of cars. |
| GET | /owner/meters | Show the list of parking meters. |
| GET | /owner/{carNumber}| Show parking meters of certain car. |
| GET | /owner/{carNumber}/sum | Sum the income from the certain car. |
| GET | /owner/{day}/{month}/{year} | Show parking meters the during chosen day. |
| GET | /owner/{day}/{month}/{year}/sum | Sum the income for chosen day. |
| GET | /operator/list | Show all cars on the parking. |
| GET | /operator/cars/{id} | Show info about chosen car. |
| GET | /operator/cars/number/{carNumber} | Show info about chosen car. |
| GET | /driver/{carNumber} | Show all previous driver's parking meters. |
| POST | /driver/{carNumber}/location/inside | Changes car location to INSIDE. |
| POST | /driver/{carNumber}/location/outside | Changes car location to OUTSIDE. |
| POST | /driver/{carNumber}/parkingMeter/on | Turns on parking meter. |
| POST | /driver/{carNumber}/parkingMeter/off | Turns of parking meter. |
| POST | /operator/search?text={searchTerm} | Allows search by the part of car number. |
