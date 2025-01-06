package nl.avans.rentmycar.rental.presentation

import nl.avans.rentmycar.R
import nl.avans.rentmycar.rental.domain.Rental

val RentalList = listOf(
    Rental(
        id = 1,
        name = "Ford Ka",
        description = "Mooi Koekblik",
        imageRes = R.drawable.logo
    ),
    Rental(
        id = 2,
        name = "Dacia Duster",
        description = "Tank",
        imageRes = R.drawable.logo
    )
)