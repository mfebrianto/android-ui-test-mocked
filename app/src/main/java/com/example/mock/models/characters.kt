package com.example.mock.models

import com.google.gson.annotations.SerializedName

data class Characters(@SerializedName("data") val characterCollection: CharacterCollection) {

    data class CharacterCollection(@SerializedName("characters") val collection: Array<Character>)

    data class Character(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String
    )
}