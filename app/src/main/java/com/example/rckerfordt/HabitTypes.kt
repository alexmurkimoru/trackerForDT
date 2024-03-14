package com.example.rckerfordt

import androidx.annotation.DrawableRes

enum class HabitTypes(val habit: String, @DrawableRes val res: Int) {
    WATER("Вода", R.drawable.bottle_of_water_icon_icons_com_68778),
    FOOD("Еда", R.drawable.strawberry_icon_icons_com_68687),
    WORKOUT("Спорт", R.drawable.sport_basketball_football_balls_soccer_icon_149677),
    EDUCATION("Образование", R.drawable.science_books_education_school_learning_study_book_chemistry_laboratory_icon_226286),
    SLEEP("Сон",R.drawable._561857_bedroom_emoji_emoticon_rest_sleep_sleeping_107895),
    MEDITATION("Медитация", R.drawable.meditation_icon_icons_com_60009),
    PILLS("Медикаменты", R.drawable.pills_pill_bottle_medicine_medical_healthcare_icon_141993),
    HOUSEWORK("Домашние дела", R.drawable.hand_washing_wash_clean_hands_hygiene_icon_142824),
    WORK("Работа", R.drawable.workspace_table_study_work_furniture_icon_209731),
    USER_TYPE("Другое", R.drawable.ingenuity_wit_book_idea_light_bulb_learn_learning_icon_149691),
}