package com.santiago.fabricio.tmdbchallenge.core.domain.model

import com.santiago.fabricio.tmdbchallenge.core.data.remote.response.characters.CharactersResponse

class CharactersFactory {

    companion object {
        fun create() = run {
            CharactersResponse(
                info = Info(
                    count = 10,
                    pages = 1,
                    next = "",
                    prev = ""
                ),
                results = listOf(
                    Character(
                        id = 361,
                        name = "Toxic Rick",
                        status = "Dead",
                        species = "Humanoid",
                        type = "Rick's Toxic Side",
                        gender = "Male",
                        origin = OriginLocation(
                            name = "Alien Spa",
                            url = "https://tmdbchallengeapi.com/api/location/64"
                        ),
                        location = OriginLocation(
                            name = "Earth",
                            url = "https://tmdbchallengeapi.com/api/location/20"
                        ),
                        image = "https://tmdbchallengeapi.com/api/character/avatar/361.jpeg",
                        episode = listOf(
                            "https://tmdbchallengeapi.com/api/episode/27"
                        ),
                        url = "https://tmdbchallengeapi.com/api/character/361",
                        created = "2018-01-10T18:20:41.703Z"

                    )
                )
            )
        }
    }
}