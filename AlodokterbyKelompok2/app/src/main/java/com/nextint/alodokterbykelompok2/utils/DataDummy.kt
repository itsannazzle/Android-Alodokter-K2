package com.nextint.alodokterbykelompok2.utils

import com.nextint.alodokterbykelompok2.data.remote.response.article.Article
import com.nextint.alodokterbykelompok2.data.remote.response.doctor.Doctor

object DataDummy {
    fun generateDummyDoctor(): List<Doctor>{
        val doctor = ArrayList<Doctor>()
        doctor.add(
            Doctor(
                "https://res.cloudinary.com/dk0z4ums3/image/upload/w_100,h_100,c_thumb,g_face,dpr_2.0/v1558683445/image_doctor/Dr.%20Ari%20Djatikusumo%2C%20SpM.jpg.jpg",
                "123",
                "2021-09-01",
                "08123",
                "dr. Ari Djatikusumo, Sp.M",
                "2021-09-01",
                "Dokter Mata",
                "Bogor",
                1,
                "Rabu",
                "Ini dokter mata"
            )
        )
        doctor.add(
            Doctor(
                "https://res.cloudinary.com/dk0z4ums3/image/upload/w_100,h_100,c_thumb,g_face,dpr_2.0/v1558683704/image_doctor/Dr.%20Eko%20F.%20Karim%2C%20SpM.jpg.jpg",
                "124",
                "2021-09-01",
                "08291",
                "dr. Eko Firdianto Karim, Sp.M",
                "2021-09-01",
                "Dokter Mata",
                "Jakarta",
                2,
                "Kamis",
                "Ini dokter mata juga",
            )
        )
        return doctor
    }

    fun generateDummyArticle(): List<Article>{
        val article = ArrayList<Article>()
        article.add(
            Article(
                "Lora Kerluke",
                "http://placeimg.com/640/480",
            "2021-12-07T17:02:22.041Z",
                "2021-12-07T17:02:22.041Z",
            "Carbonite web goalkeeper gloves are ergonomically designed to give easy fit",
            "7673f424-07cf-4e98-90b9-7c061aa42894",
            1,
            "Global Creative Manager")
        )
        article.add(
            Article(
                "Terrell Jakubowski",
                "http://placeimg.com/640/480",
                "2021-12-07T17:02:22.041Z",
                "2021-12-07T17:02:22.041Z",
                "Ergonomic executive chair upholstered in bonded black leather and PVC padded seat and back for all-day comfort and support",
                "235c0d8f-9dfc-445d-9378-7d2c02c1863a",
                2,
                "Senior Directives Administrator")
        )
        return article
    }
}