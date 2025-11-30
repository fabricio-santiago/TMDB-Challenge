# TMDB Challenge App

This project was created to reinforce knowledge in Kotlin + Compose, using a free API from TMDB (The Movie Database)

I utilized Jetpack Compose in this project and followed the best practices to developer the app's behaviors.

## MoviesScreen

- List popular movies with infinite scroll and increasing the page parameter.
- Show poster image, title, rating and release date.
- Button to add / remove movie in favorite list.

## FavoritesScreen

- List all favorites movies.
- Show poster image and title.
- Button to remove movie from favorite list.

## SearchScreen

- List filtered movie after search.
- Show poster image, title, rating and release date.
- Button to add / remove movie in favorite list.


### Core Libraries

#### Retrofit: For RESTful API communication.
#### Hilt: For dependency injection.
#### Coil: For image loading.
#### Kotlin Flow: For reactive streams.
#### Paging 3: For pagination of data.
#### Jetpack Compose: For building UI screens declaratively.
#### JUnit, Mockito: For unit testing.
#### All Screens contains accessibility.
#### Supports Dark and Light theme
