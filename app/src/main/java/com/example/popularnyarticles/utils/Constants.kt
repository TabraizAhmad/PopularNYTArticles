package com.example.popularnyarticles.utils

object Constants {
    const val POPULAR_ARTICLE_TABLE_NAME: String="popular_article"
    const val ARTICLE_DB: String="articles.db"
    const val API_KEY: String="hjOa0PgKqC7zm86P10F3BQkTuLsEV4wh"
    const val OK_STATUS: String="OK"
    const val SECTION: String = "viewed"

    enum class PeriodFilter(val value: Int) {
        SHOW_DAILY(1),
        SHOW_WEEKLY(7),
        SHOW_MONTHLY(30) }

}