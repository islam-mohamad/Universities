
# Universities - Android coding challenge

A simple universities data app 


## API Reference

#### Fetch universites list

``http
  GET /http://universities.hipolabs.com/search?country={country_name}
``

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `country_name` | `string` | **Required**. Name of the country |
## Tech Stack

**Architecture:** MVVM, Clean Architecture, Modularization by Feature

**Dependency Injection:** Dagger 2

**Threading:** Kotlin Coroutines

**Database:** Room Database

**Networking:** Retrofit

**UI:** XML and View Binding

**Navigation:** Jetpack Navigation Component, navArgs

**Unit Testing:** Junit, Mockito

