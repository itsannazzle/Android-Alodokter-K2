package com.nextint.alodokterbykelompok2.utils

class SessionRepository(private val sesi : SessionManager) {
    companion object {
        @Volatile
        private var instance: SessionRepository? = null

        fun getInstance(sesi: SessionManager): SessionRepository =
            instance ?: synchronized(this) {
                instance ?: SessionRepository(sesi)
            }
    }

    fun loginUser(username: String, TOKEN : String, exp : String) {
        sesi.createLoginSession()
        sesi.saveToPreference(SessionManager.KEY_USERNAME, username)
        sesi.saveToPreference(SessionManager.KEY_TOKEN,TOKEN)
        sesi.saveToPreference(SessionManager.KEY_EXP,exp)
    }

    fun getUsername() = sesi.getFromPreference(SessionManager.KEY_USERNAME)

    fun getToken() = sesi.getFromPreference(SessionManager.KEY_TOKEN)

    fun getExp() = sesi.getFromPreference(SessionManager.KEY_EXP)

    fun isUserLogin() = sesi.isLogin

    fun logoutUser() = sesi.logout()
}